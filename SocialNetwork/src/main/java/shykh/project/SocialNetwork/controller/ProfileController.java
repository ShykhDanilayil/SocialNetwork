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
public class ProfileController {

    @Autowired
    UserServiceImpl service;

    @GetMapping("/prof")
    public ResponseEntity<UserLogin> getUserById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (Objects.nonNull(req.getSession().getAttribute("idOtherUser"))) {
            String id = req.getSession().getAttribute("idOtherUser").toString();
            log.info("Looking for a user by id {}", id);
            User user = service.getById(id);
            if (Objects.isNull(user)) {
                log.error("No user by id {}", id);
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