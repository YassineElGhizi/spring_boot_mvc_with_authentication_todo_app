package com.advanced_to_list.advanced_to_list.repositories;


import com.advanced_to_list.advanced_to_list.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByNameIsContaining(String name);

    List<Todo> findByCreatedAtGreaterThanEqual(LocalDate date);
}
