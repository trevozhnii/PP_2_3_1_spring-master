package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.exception.ErrorMessage;
import project.exception.UserNotFoundException;
import project.model.User;
import project.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUser(Model users) {
        users.addAttribute("users", userService.getAllUser());
        return "home";
    }

    @GetMapping("/saveget")
    public String saveUserGet(@ModelAttribute("user") User user) {
        return "newuser";
    }

    @PostMapping("/savepost")
    public String saveUserPost(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
        userService.deleteUserById(id);
        return "redirect:/users";}
        else {
            throw new UserNotFoundException("User not found with id " + id);
        }
    }
    @GetMapping("/usereditorget/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        return user;
    }

    @GetMapping("/usereditorget/{id}")
    public String userEditorGet(Model user, @PathVariable("id") Long id) {
        user.addAttribute("user", userService.getUserById(id));
        return "usereditor";
    }

    @PatchMapping("/{id}")
    public String userEditorPatch(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.userEditor(user, id);
        return "redirect:/users";
    }
}

