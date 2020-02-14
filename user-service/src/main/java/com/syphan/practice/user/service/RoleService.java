package com.syphan.practice.user.service;

import com.syphan.practice.user.dto.RoleCreateDto;
import com.syphan.practice.user.model.Role;
import com.syphan.pratice.common.base.BaseService;

public interface RoleService extends BaseService<Role, Integer> {

    Role create(RoleCreateDto data);
}
