package com.syphan.practice.user.service;

import com.syphan.practice.user.dto.PermissionCreateDto;
import com.syphan.practice.user.model.Permission;
import com.syphan.pratice.common.base.BaseService;

public interface PermissionService extends BaseService<Permission, Integer> {

    Permission create(PermissionCreateDto data);
}
