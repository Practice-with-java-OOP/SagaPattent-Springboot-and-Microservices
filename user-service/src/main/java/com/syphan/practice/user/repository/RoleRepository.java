package com.syphan.practice.user.repository;

import com.syphan.practice.user.model.Role;
import com.syphan.pratice.common.dao.JpaQueryRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaQueryRepository<Role, Integer> {
    Role findByCode(String code);
}
