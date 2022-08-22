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
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.time.LocalDate;


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
        return "filter";
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public String search_post(Model model, @ModelAttribute("search_form") SearchForm searchForm, HttpSession session) {
        @SuppressWarnings("unchecked") Long user_id = (Long) session.getAttribute("id");

        if (!searchForm.before.isEmpty() && !searchForm.name.isEmpty()) {
            List<Todo> lt = todoRepository.findByNameIsContaining(searchForm.getName().strip());

            Predicate<Todo> by_user = todo -> Objects.equals(todo.getUser().getId(), user_id);
            List<Todo> flt_tmp = lt.stream().filter(by_user).collect(Collectors.toList());

            Predicate<Todo> by_creartedAt = todo -> todo.getCreatedAt().isAfter(LocalDate.parse(searchForm.getBefore(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            List<Todo> flt = flt_tmp.stream().filter(by_creartedAt).collect(Collectors.toList());

            model.addAttribute("user_name", userRepository.findUserById(user_id).getName());
            Integer r_size = flt.size();
            model.addAttribute("msg", "Results Found : " + r_size.toString());
            model.addAttribute("todos", flt);

            return "filter";

        } else if (!searchForm.before.isEmpty()) {
            LocalDate date = LocalDate.parse(searchForm.before, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            List<Todo> lt = todoRepository.findByCreatedAtGreaterThanEqual(date);

            Predicate<Todo> by_user = todo -> Objects.equals(todo.getUser().getId(), user_id);
            List<Todo> flt = lt.stream().filter(by_user).collect(Collectors.toList());

            model.addAttribute("user_name", userRepository.findUserById(user_id).getName());
            Integer r_size = flt.size();
            model.addAttribute("msg", "Results Found : " + r_size.toString());
            model.addAttribute("todos", flt);

            return "filter";
        } else if (!searchForm.name.isEmpty()) {

            List<Todo> lt = todoRepository.findByNameIsContaining(searchForm.getName().strip());

            Predicate<Todo> by_user = todo -> Objects.equals(todo.getUser().getId(), user_id);
            List<Todo> flt = lt.stream().filter(by_user).collect(Collectors.toList());

            model.addAttribute("user_name", userRepository.findUserById(user_id).getName());
            Integer r_size = flt.size();
            model.addAttribute("msg", "Results Found : " + r_size.toString());
            model.addAttribute("todos", flt);

            return "filter";
        }

        return "filter";
    }


}
