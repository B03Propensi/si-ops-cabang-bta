package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.service.RoleService;
import bta.cabang.operasional.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    private String addUserForm(Model model) {
        model.addAttribute("listRole", roleService.findAll());
        return "form-addUser";
    }

    @PostMapping("/add")
    private String addUserSubmit(@ModelAttribute UserModel user) {
        userService.addUser(user);
        return "redirect:/";
    }
}