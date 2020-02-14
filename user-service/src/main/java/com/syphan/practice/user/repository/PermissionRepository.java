package com.syphan.practice.user.repository;

import com.syphan.practice.user.model.Permission;
import com.syphan.pratice.common.dao.JpaQueryRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaQueryRepository<Permission, Integer> {
    Permission findByCode(String code);
}
