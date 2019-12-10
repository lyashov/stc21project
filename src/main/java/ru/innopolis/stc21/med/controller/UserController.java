package ru.innopolis.stc21.med.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc21.med.exception.RecordNotFoundException;
import ru.innopolis.stc21.med.model.MedicalHistoryEntity;
import ru.innopolis.stc21.med.model.UsersEntity;
import ru.innopolis.stc21.med.service.UsersService;

import java.io.FileNotFoundException;
import java.util.List;

public class UserController {

    private UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @GetMapping({"/userProfile"})
    public String userProfile(Model model) throws RecordNotFoundException {

        UsersEntity currentUser = usersService.getUserByName(getCurrentUsername());
        model.addAttribute("user", currentUser);
        return "userProfile";
    }
}
