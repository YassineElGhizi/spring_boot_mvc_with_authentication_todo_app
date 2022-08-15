package com.advanced_to_list.advanced_to_list.FormValidation;

import org.springframework.web.multipart.MultipartFile;

public class UpdateTodo {

    public Long user_id;
    public String name;
    public MultipartFile image;
    public Long todo_id;

    @Override
    public String toString() {
        return "UpdateTodo{" + "todo_id=" + todo_id + ", user_id=" + user_id + ", name='" + name + '\'' + ", image=" + image + '}';
    }

    public void setTodo_id(Long todo_id) {
        this.todo_id = todo_id;
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

    public Long getTodo_id() {
        return todo_id;
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

    public UpdateTodo(Long todo_id, Long user_id, String name, MultipartFile image) {
        this.todo_id = todo_id;
        this.user_id = user_id;
        this.name = name;
        this.image = image;
    }

}
