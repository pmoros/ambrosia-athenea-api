package com.ambrosia.athenea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ambrosia.athenea.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByCode(String code);
}