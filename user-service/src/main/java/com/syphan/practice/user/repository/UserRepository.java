package com.syphan.practice.user.repository;

import com.syphan.practice.user.model.User;
import com.syphan.pratice.common.dao.JpaQueryRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaQueryRepository<User, Integer> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByPhoneNum(String phoneNum);

    @Query("select u from User u join fetch u.roles")
    List<User> findAllByLazy();
}
