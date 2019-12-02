package ru.innopolis.stc21.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.stc21.med.exception.RecordNotFoundException;
import ru.innopolis.stc21.med.model.MedicalHistoryEntity;
import ru.innopolis.stc21.med.model.UsersEntity;
import ru.innopolis.stc21.med.service.MedicalHistoryService;
import ru.innopolis.stc21.med.service.UsersService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UsersService usersService;
    @Autowired
    private MedicalHistoryService medicalHistoryService;

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @GetMapping({"/history"})
    public String history(Model model,
                        @RequestParam(value="name", required=false, defaultValue="World") String name) throws RecordNotFoundException {

        UsersEntity currentUser = usersService.getUserByName(getCurrentUsername());
        List<MedicalHistoryEntity> medHistories = medicalHistoryService.getAllByUser(currentUser);

        model.addAttribute("medHistories", medHistories);
        return "history";
    }



    @GetMapping({"/hello"})
    public String hello(Model model,
        @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
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
