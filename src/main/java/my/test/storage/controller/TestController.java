package my.test.storage.controller;

import my.test.storage.entity.StorageFile;
import my.test.storage.repository.StorageRoleRepository;
import my.test.storage.repository.StorageUserRepository;
import my.test.storage.service.StorageFileService;
import my.test.storage.service.StorageRoleService;
import my.test.storage.service.StorageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.nio.file.Path;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Controller
public class TestController {
    @Autowired
    StorageRoleService storageRoleService;
    @Autowired
    StorageFileService storageFileService;



    @GetMapping("/public")
    public String testPublic(Model model) {
        return "public";
    }


    @GetMapping("/private")
    public String testPrivate(Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("auths", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return "private";
    }

    @GetMapping("/")
    public String testMain(Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return "main";
    }

    @GetMapping("/admin")
    public String testAdmin() {
        return "admin";
    }

    @GetMapping("/manager")
    public String testManager() {
        return "manager";
    }


    @GetMapping("/roles/add")
    public String getAdd() {
        return "roles/add";
    }

    @PostMapping("/roles/add")
    public String postAdd(@RequestParam String role) {
        storageRoleService.addRole(role);
        return "redirect:/roles/list";
    }

    @GetMapping("/roles/list")
    public String getList(Model model) {
        model.addAttribute("roles", storageRoleService.getRoles());
        return "roles/list";
    }

    @PostMapping("/roles/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        storageRoleService.deleteRole(id);
        return "redirect:/roles/list";
    }


}
