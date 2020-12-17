package shykh.project.SocialNetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.model.response.UserLogin;
import shykh.project.SocialNetwork.service.impl.FollowersServiceImpl;
import shykh.project.SocialNetwork.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class UserController {

    @Autowired
    UserServiceImpl service;

    @Autowired
    FollowersServiceImpl followersService;

    @GetMapping("/users")
    public ResponseEntity<List<UserLogin>> getAllUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (Objects.nonNull(req.getSession().getAttribute("email"))) {
            User iUser = service.getByEmail(req.getSession().getAttribute("email").toString());
            log.info("Looking for all users");
            List<User> users = service.getAllUsers();
            users.remove(iUser);
            if (users.isEmpty()) {
                log.info("No records found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<UserLogin> usersLogin = new ArrayList<>();
            for (User user :
                    users) {
                if (Objects.nonNull(followersService.getFollowers(iUser, user)) && Objects.isNull(followersService.getFollowers(user, iUser))) {
                    log.info("I followers other user");
                    usersLogin.add(new UserLogin(user, "following"));
                } else if (Objects.nonNull(followersService.getFollowers(user, iUser)) && Objects.isNull(followersService.getFollowers(iUser, user))) {
                    log.info("Other user followers me");
                    usersLogin.add(new UserLogin(user, "followers"));
                } else if (Objects.nonNull(followersService.getFollowers(iUser, user)) && Objects.nonNull(followersService.getFollowers(user, iUser))) {
                    log.info("Other user is my friend");
                    usersLogin.add(new UserLogin(user, "true"));
                } else {
                    usersLogin.add(new UserLogin(user, "false"));
                }
            }

            return new ResponseEntity<>(usersLogin, HttpStatus.OK);
        } else {
            log.info("User is not logged in. Redirection to /logIn page");
            resp.setContentType("text");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("login-failed");
            return null;
        }
    }
}