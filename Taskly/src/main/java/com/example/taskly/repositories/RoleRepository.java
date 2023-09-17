package com.example.taskly.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskly.models.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
	Optional<RoleModel> findByAuthority(String authority);
}
