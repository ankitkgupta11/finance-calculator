package com.finapp.finance_calculator.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finapp.finance_calculator.vo.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}