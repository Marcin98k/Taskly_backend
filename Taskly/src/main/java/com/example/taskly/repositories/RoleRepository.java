package com.example.taskly.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskly.models.UserRoleModel;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleModel, Long> {
	Optional<UserRoleModel> findByAuthority(String authority);
}
