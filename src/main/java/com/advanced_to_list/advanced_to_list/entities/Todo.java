package com.advanced_to_list.advanced_to_list.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todos")
public class Todo {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String image;

    @CreationTimestamp
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Todo(String image, String name, User user) {
        this.image = image;
        this.name = name;
        this.user = user;
    }

    public Todo(Long id, String name, String image, User user) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.user = user;
        this.createdAt = java.time.LocalDate.now();
    }

}
