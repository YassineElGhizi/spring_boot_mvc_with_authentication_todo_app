package com.advanced_to_list.advanced_to_list.repositories;


import com.advanced_to_list.advanced_to_list.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
