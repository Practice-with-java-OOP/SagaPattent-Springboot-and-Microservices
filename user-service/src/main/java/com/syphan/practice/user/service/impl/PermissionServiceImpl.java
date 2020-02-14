package com.syphan.practice.user.service.impl;

import com.syphan.practice.user.dto.PermissionCreateDto;
import com.syphan.practice.user.model.Permission;
import com.syphan.practice.user.repository.PermissionRepository;
import com.syphan.practice.user.service.PermissionService;
import com.syphan.pratice.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Integer> implements PermissionService {

    private PermissionRepository repository;

    @Autowired
    protected PermissionServiceImpl(PermissionRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Transactional
    @Override
    public Permission create(PermissionCreateDto data) {
        Permission permission = new Permission();
        permission.setName(data.getName());
        permission.setCode(data.getCode());
        return repository.save(permission);
    }
}
