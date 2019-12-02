package ru.innopolis.stc21.med.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class MainController {
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping({"/hello"})
    public String hello(Model model,
        @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        System.out.printf("1");
        return "hello";

    }


    @PostMapping({"/hello"})
    public String hello(Model model,
                        @RequestParam(value="name", required=false, defaultValue="World") String name,
                        @RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            String imgPath = System.getProperty("user.dir").concat(uploadPath);
            File uploadDir = new File(imgPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String fullPath = imgPath + "/" + file.getOriginalFilename();
            file.transferTo(new File(fullPath));
        }
        model.addAttribute("name", "Ура!!! файл " + file.getOriginalFilename() + " добавлен на сервер!");
        return "hello";
    }
}
