package shykh.project.SocialNetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@RestController
public class LogOutController {

    @GetMapping("/logOut")
    protected void doGet(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (Objects.nonNull(session)) session.invalidate();
        log.info("User log out");
    }
}