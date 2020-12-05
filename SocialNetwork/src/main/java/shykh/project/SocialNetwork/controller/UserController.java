package shykh.project.SocialNetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.service.impl.UserServiceImpl;
import shykh.project.SocialNetwork.model.response.UserLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class UserController {

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

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email1");
        User user = service.getByEmail(email);

        if (Objects.isNull(user)) {
            log.error("User with email {} don't exist!", email);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String password = req.getParameter("password1");

        if (!password.equals(user.getPassword())) {
            log.error("The password is incorrect!");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("email",user.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserLogin>> getAllUsers(){
        log.info("Looking for all users");
        List<User> users = service.getAllUsers();
        if (users.isEmpty()){
            log.info("No records found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UserLogin> usersLogin = new ArrayList<>();
        for (User user:
             users) {
            usersLogin.add(new UserLogin(user));
        }

        return new ResponseEntity<>(usersLogin, HttpStatus.OK);
    }
}