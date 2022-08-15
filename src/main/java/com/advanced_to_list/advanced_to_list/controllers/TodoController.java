package com.advanced_to_list.advanced_to_list.controllers;


import com.advanced_to_list.advanced_to_list.Config.StaticFilesPath;
import com.advanced_to_list.advanced_to_list.FormValidation.CreateTodo;
import com.advanced_to_list.advanced_to_list.entities.Todo;
import com.advanced_to_list.advanced_to_list.entities.User;
import com.advanced_to_list.advanced_to_list.repositories.TodoRepository;
import com.advanced_to_list.advanced_to_list.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Controller
public class TodoController {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = {"/todo"}, method = RequestMethod.POST)
    public String save_user_to_db(Model model, @ModelAttribute("todo") CreateTodo createTodo) throws IOException {
        String image_path = "";
        if (!createTodo.image.isEmpty()) {
            StaticFilesPath staticFilesPath = new StaticFilesPath();
            System.out.println("FUCK :" + staticFilesPath.path + createTodo.image.getOriginalFilename());
            image_path = String.valueOf((int) (new Date().getTime() / 1000)) + createTodo.image.getOriginalFilename();
            FileOutputStream output = new FileOutputStream(staticFilesPath.path + image_path);
            output.write(createTodo.image.getBytes());
        }

        User user = userRepository.findUserById(createTodo.user_id);
        Todo t = new Todo(image_path, createTodo.name, user);
        todoRepository.save(t);
        model.addAttribute("user_name", user.getName());
        model.addAttribute("user_id", user.getId());
        model.addAttribute("msg", "To Has Been Created Successfully");
        return "todo";
    }


}
