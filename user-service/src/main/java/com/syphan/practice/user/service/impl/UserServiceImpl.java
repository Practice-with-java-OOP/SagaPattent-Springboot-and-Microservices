package com.syphan.practice.user.service.impl;

import com.syphan.practice.user.dto.AdminCreateUserDto;
import com.syphan.practice.user.dto.HouseCreateLoggerDto;
import com.syphan.practice.user.dto.UserCreateDto;
import com.syphan.practice.user.model.Role;
import com.syphan.practice.user.model.User;
import com.syphan.practice.user.processor.UserProcessor;
import com.syphan.practice.user.repository.RoleRepository;
import com.syphan.practice.user.repository.UserRepository;
import com.syphan.practice.user.service.UserService;
import com.syphan.practice.user.util.Utils;
import com.syphan.pratice.common.base.BaseServiceImpl;
import com.syphan.pratice.common.core.GenerateAvatarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserProcessor userProcessor;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public String sendUserSignUpMailCaptcha(String email) {
        return null;
    }

    @Transactional
    @Override
    public User signUp(UserCreateDto data) {
        User user = generalCreateUser(data.getUsername(), data.getEmail(), data.getPhoneNum(), data.getPassword(),
                data.getAvatar(), data.getFullName());

        Set<Role> roles = new HashSet<>();
        roles.add(getDefaultUserRole());
        user.setRoles(roles);
        return repository.save(user);
    }

    @Transactional
    @Override
    public User adminCreateUser(AdminCreateUserDto data) {
        User user = generalCreateUser(data.getUsername(), data.getEmail(), data.getPhoneNum(), data.getPassword(),
                data.getAvatar(), data.getFullName());
        Set<Role> roles = new HashSet<>();
        if (!data.getRoleIds().isEmpty()) {
            List<Role> roleList = roleRepository.findAllById(data.getRoleIds());
            roles.addAll(roleList);
        } else {
            roles.add(getDefaultUserRole());
        }
        user.setRoles(roles);
        return repository.save(user);
    }

    private User generalCreateUser(String userName, String email, String phoneNum, String password, String avatar, String fullName) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setFullName(fullName);
        user.setAvatar(avatar != null ? avatar : GenerateAvatarUtils.generate(email));
        user.setEmail(email);
        user.setPhoneNum(phoneNum);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void checkUserExisted(HouseCreateLoggerDto dto) {
        try {
            Optional<User> userOptional = repository.findById(dto.getId());
            throw new RuntimeException("test nem exception nhe!");
        } catch (Exception e) {
            userProcessor.output().send(MessageBuilder.withPayload(HouseCreateLoggerDto.builder()
                    .id(dto.getId()).transactionId(dto.getTransactionId()).username(dto.getUsername())
                    .className("BoardingHouse").build())
                    .setHeader("type", "ROLLBACK_CREATED_HOUSE")
                    .build());
        }
    }

    private Role getDefaultUserRole() {
        Role role = roleRepository.findByCode(Utils.DEFAULT_USER_ROLE);
        if (role != null) {
            return role;
        } else {
            role = new Role();
            role.setName("User");
            role.setCode(Utils.DEFAULT_USER_ROLE);
            return roleRepository.save(role);
        }
    }
}
