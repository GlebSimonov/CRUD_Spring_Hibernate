package web.controller;

import org.springframework.validation.BindingResult;
import web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.serice.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private static final String GOHOME = "redirect:/";

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/index";
    }
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/show";
    }
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getFormForCreate(@ModelAttribute("user") User user) {
        return "users/new";
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/new";
        userService.addUser(user);
        return GOHOME;
    }
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String getFormForUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/edit";
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") @Valid User user,BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "users/edit";
        userService.editUser(user, id);
        return GOHOME;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return GOHOME;
    }
}

