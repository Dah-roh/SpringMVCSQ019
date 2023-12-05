package com.decagon.springmvcsq019.repositories;

import com.decagon.springmvcsq019.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepositories extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
