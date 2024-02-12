package web.controller;

import web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.serice.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String getFormForCreate(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user) {
        userService.addUser(user);
        return "redirect:/users/";
    }

    @GetMapping("/{id}/edit")
    public String getFormForUpdate(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/edit";
    }

    @PutMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, @PathVariable("id") Integer id) {
        userService.editUser(user, id);
        return "redirect:/users/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/users/";
    }
}

