package shykh.project.SocialNetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.model.response.UserLogin;
import shykh.project.SocialNetwork.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RestController
public class CabinetController {

    @Autowired
    UserServiceImpl service;

    @GetMapping("/cab")
    public ResponseEntity<UserLogin> getUserByEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (Objects.nonNull(req.getSession().getAttribute("email"))) {
            String email = req.getSession().getAttribute("email").toString();
            log.info("Looking for a user by email {}", email);
            User user = service.getByEmail(email);
            if (Objects.isNull(user)) {
                log.error("No user by email {}", email);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            UserLogin userLogin = new UserLogin(user, "false");
            return new ResponseEntity<>(userLogin, HttpStatus.OK);
        } else {
            log.info("User is not logged in. Redirection to /logIn page");
            resp.setContentType("text");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("login-failed");
            return null;
        }
    }
}