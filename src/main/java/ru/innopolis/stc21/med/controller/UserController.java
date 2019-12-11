package ru.innopolis.stc21.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc21.med.exception.RecordNotFoundException;
import ru.innopolis.stc21.med.model.UsersEntity;
import ru.innopolis.stc21.med.repository.UsersRepository;
import ru.innopolis.stc21.med.service.UsersService;

@Controller
public class UserController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersRepository usersRepository;

    public UserController() {
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @GetMapping("/userProfile")
    public String userProfile(Model model) throws RecordNotFoundException {

        UsersEntity currentUser = usersService.getUserByName(getCurrentUsername());
        model.addAttribute("user", currentUser);
        return "userProfile";
    }

    @GetMapping("/userEdit")
    public String userEdit(Model model) throws RecordNotFoundException {

        UsersEntity currentUser = usersService.getUserByName(getCurrentUsername());
        model.addAttribute("user", currentUser);
        return "userEdit";
    }

    @PostMapping("/userSave")
    public String addUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String first_name ,
            @RequestParam String second_name,
            @RequestParam String last_name,
            @RequestParam String email,
            @RequestParam String snils) {

        usersService.create(username, password, first_name, second_name, last_name, email, snils);
        return "redirect:/userProfile";
    }
}
