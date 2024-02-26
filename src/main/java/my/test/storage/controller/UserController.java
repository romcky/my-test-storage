package my.test.storage.controller;

import my.test.storage.repository.StorageRoleRepository;
import my.test.storage.repository.StorageUserRepository;
import my.test.storage.service.StorageRoleService;
import my.test.storage.service.StorageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    @Autowired
    private StorageUserService storageUserService;

    @PostMapping("/users/add")
    public String postAdd(@RequestParam String name, @RequestParam String pwd, @RequestParam String role) {
        storageUserService.addUser(name, pwd, role);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getList(Model model) {
        model.addAttribute("users", storageUserService.getUsers());
        return "users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        storageUserService.deleteUser(id);
        return "redirect:/users";
    }
}
