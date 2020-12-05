package shykh.project.SocialNetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.model.response.UserAndLastMessage;
import shykh.project.SocialNetwork.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ChatController {

    @Autowired
    UserServiceImpl service;

    @GetMapping("/chatPeople")
    public ResponseEntity<List<UserAndLastMessage>> getUsersInChat() {
        log.info("Looking for all users");
        List<User> users = service.getAllUsers();
        if (users.isEmpty()){
            log.info("No records found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UserAndLastMessage> usersChat = new ArrayList<>();
        for (User user:
                users) {
            usersChat.add(new UserAndLastMessage(user));
        }
        return new ResponseEntity<>(usersChat, HttpStatus.OK);
    }
}
