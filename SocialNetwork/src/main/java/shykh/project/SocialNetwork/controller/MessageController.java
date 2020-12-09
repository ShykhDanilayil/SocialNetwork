package shykh.project.SocialNetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shykh.project.SocialNetwork.model.Message;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.model.response.MessageResponse;
import shykh.project.SocialNetwork.service.impl.MessageServiceImpl;
import shykh.project.SocialNetwork.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class MessageController {

    @Autowired
    MessageServiceImpl service;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/message")
    public ResponseEntity<User> createMessage(HttpServletRequest req){
        User user = userService.getByEmail(req.getSession().getAttribute("email").toString());
        User otherUser = userService.getById(req.getSession().getAttribute("idOtherUser").toString());

        Message message = new Message(user.getId(), otherUser.getId(), req.getParameter("text"));

        message.addUser(user);
        user.addMessage(message);
        service.create(message);

        log.info("Message {} was added", message);
        return new ResponseEntity<>(otherUser, HttpStatus.CREATED);
    }

    @PostMapping("/messages")
    public ResponseEntity<List<MessageResponse>> showAllMessages( HttpServletRequest req){
        User iUser = userService.getByEmail(req.getSession().getAttribute("email").toString());
        String idOtherUser = req.getParameter("idOtherUser");
        System.out.println("id : " + idOtherUser);
        User otherUser = userService.getById(idOtherUser);
        log.info("Looking for history messages");
        List<MessageResponse> messageResponses = new ArrayList<>();
        if (iUser.getMessages().size() != 0){
            List<Message> messages = service.getMyMessagesAndResponse(iUser.getId(), otherUser.getId());
            log.info("Message history was load");
            for (Message message:
                    messages) {
                messageResponses.add(new MessageResponse(message));
                for (MessageResponse mess:
                        messageResponses) {
                    if (message.getIdUserTo().equals(otherUser.getId())){
                        mess.addPhotoOtherUser(otherUser);
                    }
                }
            }
        }
        req.getSession().setAttribute("idOtherUser",otherUser.getId());
        return new ResponseEntity<>(messageResponses, HttpStatus.OK);
    }
}
