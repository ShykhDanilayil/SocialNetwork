package shykh.project.SocialNetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shykh.project.SocialNetwork.model.Message;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.model.response.UserAndLastMessage;
import shykh.project.SocialNetwork.service.impl.MessageServiceImpl;
import shykh.project.SocialNetwork.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class ChatController {

    @Autowired
    UserServiceImpl service;

    @Autowired
    MessageServiceImpl messageService;

    @GetMapping("/chatPeople")
    public ResponseEntity<List<UserAndLastMessage>> getUsersInChat(HttpServletRequest req) {
        User iUser = service.getByEmail(req.getSession().getAttribute("email").toString());
        log.info("Looking for all users");
        List<User> users = service.getAllUsers();
        users.remove(iUser);
        if (users.isEmpty()){
            log.info("No records found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Message> messages = null;
        List<UserAndLastMessage> usersChat = new ArrayList<>();
        for (User user:
                users) {
            UserAndLastMessage userAndLastMessage = new UserAndLastMessage(user);
            if (messageService.getMyMessagesAndResponse(iUser.getId(), user.getId()).size() != 0){
                messages = messageService.getMyMessagesAndResponse(iUser.getId(), user.getId());
            userAndLastMessage.addLastMess(messages.get(messages.size() - 1));
        }
            usersChat.add(userAndLastMessage);
        }
        return new ResponseEntity<>(usersChat, HttpStatus.OK);
    }
}
