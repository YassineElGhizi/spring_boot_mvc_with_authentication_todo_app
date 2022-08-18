package com.advanced_to_list.advanced_to_list.controllers;

import com.advanced_to_list.advanced_to_list.FormValidation.SearchForm;
import com.advanced_to_list.advanced_to_list.entities.Todo;
import com.advanced_to_list.advanced_to_list.repositories.TodoRepository;
import com.advanced_to_list.advanced_to_list.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Controller
public class SearchController {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Autowired
    public SearchController(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String search_get(Model model) {
        return "search";
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public String search_post(Model model, @ModelAttribute("search_form") SearchForm searchForm, HttpSession session) {
        @SuppressWarnings("unchecked") Long user_id = (Long) session.getAttribute("id");
        List<Todo> lt = todoRepository.findByNameIsContaining(searchForm.getName().strip());

        Predicate<Todo> by_user = todo -> Objects.equals(todo.getUser().getId(), user_id);
        List<Todo> flt = lt.stream().filter(by_user).collect(Collectors.toList());

        model.addAttribute("user_name", userRepository.findUserById(user_id).getName());
        Integer r_size = flt.size();
        model.addAttribute("msg", "Results Found : " + r_size.toString());
        model.addAttribute("todos", flt);

        return "search";
    }


}
