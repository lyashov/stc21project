package ru.innopolis.stc21.med.controller;

import com.rabbitmq.client.*;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.stc21.med.exception.RecordNotFoundException;
import ru.innopolis.stc21.med.model.MedicalHistoryEntity;
import ru.innopolis.stc21.med.model.UsersEntity;
import ru.innopolis.stc21.med.service.MedicalHistoryService;
import ru.innopolis.stc21.med.service.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Controller
public class MedicalHistoryController {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private Environment env;

    @Autowired
    private UserService usersService;
    @Autowired
    private MedicalHistoryService medicalHistoryService;

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @GetMapping({"/"})
    public String historymain(Model model,
                              @RequestParam(value = "name", required = false, defaultValue = "World") String name) throws RecordNotFoundException, FileNotFoundException {

        UsersEntity currentUser = usersService.getUserByName(getCurrentUsername());
        List<MedicalHistoryEntity> medHistories = medicalHistoryService.getAllByUser(currentUser);
        //System.out.println("");
        model.addAttribute("medHistories", medHistories);
        return "history";
    }

    @GetMapping({"/history"})
    public String history(Model model,
                          @RequestParam(value = "name", required = false, defaultValue = "World") String name) throws RecordNotFoundException, FileNotFoundException {

        UsersEntity currentUser = usersService.getUserByName(getCurrentUsername());
        List<MedicalHistoryEntity> medHistories = medicalHistoryService.getAllByUser(currentUser);

        model.addAttribute("medHistories", medHistories);
        return "history";
    }

    @PostMapping({"/history"})
    public String historyPost(Model model,
                              @RequestBody MultiValueMap<String, String> formData) throws RecordNotFoundException {
        formData.toSingleValueMap();
        return "redirect:/history";
    }

    @GetMapping({"/addRequest"})
    public String hello(Model model,
                        @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        // model.addAttribute("name", name);
        return "addRequest";
    }


    private void sender(String message) throws IOException, TimeoutException {
        String QUEUE_NAME = env.getProperty("rabbit.queueToPyhton");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(env.getProperty("rabbit.user"));
        factory.setPassword(env.getProperty("rabbit.password"));
        factory.setHost(env.getProperty("rabbit.host"));
        factory.setPort(Integer.parseInt(env.getProperty("rabbit.port")));
        factory.setVirtualHost(env.getProperty("rabbit.virtualHost"));
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }


    @PostMapping({"/addRequest"})
    public String hello(Model model,
                        @RequestParam(value = "name", required = false, defaultValue = "World") String name,
                        @RequestParam String comment,
                        @RequestParam("file") MultipartFile file) throws IOException, RecordNotFoundException, TimeoutException {
        Long iid = 0L;
        String fullPath = "";
        if (!file.isEmpty()) {
            String imgPath = uploadPath;
            //URL imgPath = getClass().getResource("/static/uploads");
            //File imgPath = ResourceUtils.getFile("classpath:uploads");
            File uploadDir = new File(imgPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            UsersEntity currentUser = usersService.getUserByName(getCurrentUsername());

            MedicalHistoryEntity mHistory = medicalHistoryService.create(new Date(), currentUser);
            mHistory.setComment(comment == null? "" : comment);

            String fileName = file.getOriginalFilename();
            String extension = "";

            int i = fileName.lastIndexOf('.');
            if (i > 0) {
                extension = fileName.substring(i + 1);
            }

            String fName = mHistory.getId() + "." + extension;
            fullPath = imgPath + "/" + fName; //file.getOriginalFilename();
            mHistory.setImgName(fName);
            medicalHistoryService.save(mHistory);
            file.transferTo(new File(fullPath));
            iid = mHistory.getId();
        }

        name = "";

        byte[] fileContent = FileUtils.readFileToByteArray(new File(fullPath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        JSONObject obj = new JSONObject();
        obj.put("id", String.valueOf(iid));
        obj.put("picture", encodedString);
        sender(obj.toJSONString());
        return "redirect:/history";
    }
}
