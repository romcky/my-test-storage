package my.test.storage.controller;

import my.test.storage.service.StorageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FilesController {
    @Autowired
    private StorageFileService storageFileService;

    @GetMapping("/files")
    public String getFiles(Model model) {
        boolean manager = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("MANAGER"));
        model.addAttribute("manager", manager);
        model.addAttribute("files", storageFileService.getFiles());
        return "files";
    }

    @PostMapping("/files/add")
    public String addFile(@RequestParam("file")MultipartFile file, @RequestParam("info") String info) {
        try {
            storageFileService.addFile(file.getOriginalFilename(), info, file.getBytes());
        } catch (IOException e) {
            // can't upload file exception
            throw new RuntimeException();
        }
        return "redirect:/files";
    }

    @GetMapping("/files/get/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        var file = storageFileService.getFileById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }

    @PostMapping("/files/delete/{id}")
    public String deleteFile(@PathVariable Long id) {
        storageFileService.deleteFile(id);
        return "redirect:/files";
    }
}
