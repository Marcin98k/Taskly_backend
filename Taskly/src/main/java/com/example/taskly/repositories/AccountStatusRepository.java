package com.example.taskly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskly.models.AccountStatusModel;

public interface AccountStatusRepository extends JpaRepository<AccountStatusModel, Long>{

}
