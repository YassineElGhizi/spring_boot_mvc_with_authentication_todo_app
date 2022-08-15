package com.advanced_to_list.advanced_to_list.repositories;

import com.advanced_to_list.advanced_to_list.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    Integer countByEmail(String email);
    User findUserById(Long id);
}
