package shykh.project.SocialNetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@RestController
public class LogInController {

    @Autowired
    UserServiceImpl service;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest req) {
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
        session.setAttribute("email", user.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}