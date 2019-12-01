package ru.innopolis.stc21.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc21.med.model.UsersEntity;
import ru.innopolis.stc21.med.repository.UsersRepository;
import ru.innopolis.stc21.med.service.UsersService;


@Controller
public class SecurityController {


    @GetMapping("/login")
    public String login(@RequestParam(name="name", required=false, defaultValue="Username") String name, Model model) {
        model.addAttribute("name", name);
        return "login";
    }

    @Autowired
    private UsersRepository usersRepo;
    @Autowired
    private UsersService usersService;

    @GetMapping("/registration")
    public String registration() {
      //  UsersEntity userFromDB = usersService.getUserByName(usersEntity.getUsername());
     //   if(userFromDB != null){
           // model.
     //   }
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam(name="username", required=false, defaultValue="Username") String username,
                          @RequestParam(name="password", required=false, defaultValue="password") String password,
                          Model model) {

        UsersEntity usersEntity = usersRepo.findByUsername(username);
        if(usersEntity != null){
            usersEntity.setUsername(username);
            usersEntity.setPassword(password);
            usersService.save(usersEntity);
            return "redirect:/operations";
        } else
                usersService.create(username, password);
        return "redirect:/operations";
    }
}
