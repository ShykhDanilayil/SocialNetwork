package shykh.project.SocialNetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RestController
public class RegisterController {

    @Autowired
    UserServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestParam("photo") MultipartFile file, HttpServletRequest req) throws IOException {

        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        int age = Integer.parseInt(req.getParameter("age"));
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        byte[] data = file.getBytes();

        if (!name.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            if (!Objects.isNull(service.getByEmail(email))) {
                log.error("User with email {} already exists!", email);
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            User user = new User(name, lastName, age, email, password, data);
            service.create(user);
            log.info("User {} was added", user.getName());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}