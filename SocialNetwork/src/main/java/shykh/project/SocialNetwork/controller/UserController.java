package shykh.project.SocialNetwork.controller;

import com.google.gson.Gson;
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
import java.io.IOException;
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

        if (!Objects.isNull(user)) {
            log.error("User with email {} already exists!", user.getEmail());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String password = req.getParameter("password1");

        if (!password.equals(user.getPassword())) {
            log.error("The password is incorrect!");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        UserLogin userLogin = new UserLogin(user);

        String json = new Gson().toJson(userLogin);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){
        log.info("Looking for all user");
        List<User> users = service.getAllUsers();
        if (users.isEmpty()){
            log.info("No records found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id){
        log.info("Looking for a user by id {}", id);
        User user = service.getById(id);
        if (Objects.isNull(user)){
            log.error("No user by id {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
