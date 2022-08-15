package com.advanced_to_list.advanced_to_list.FormValidation;

import org.springframework.web.multipart.MultipartFile;

public class CreateTodo {

    public Long user_id;
    public String name;

    public MultipartFile image;

    @Override
    public String toString() {
        return "CreateTodo{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", image=" + image +
                '}';
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public MultipartFile getImage() {
        return image;
    }

    public CreateTodo() {
    }

    public CreateTodo(Long user_id, String name, MultipartFile image) {
        this.user_id = user_id;
        this.name = name;
        this.image = image;
    }
}
