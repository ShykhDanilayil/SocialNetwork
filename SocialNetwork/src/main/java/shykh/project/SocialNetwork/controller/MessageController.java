package shykh.project.SocialNetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shykh.project.SocialNetwork.model.Message;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.service.impl.MessageServiceImpl;
import shykh.project.SocialNetwork.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class MessageController {

    @Autowired
    MessageServiceImpl service;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/message")
    public ResponseEntity<Message> createMessage(HttpServletRequest req){
        User user = userService.getByEmail(req.getSession().getAttribute("email").toString());

        Message message = new Message(user.getId(), req.getSession().getAttribute("idOtherUser").toString(), req.getParameter("text"));

        System.out.println("message : " + message);

        message.addUser(user);
        user.addMessage(message);
        service.create(message);

        log.info("Message {} was added", message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/messages")
    public ResponseEntity<List<Message>> showAllMessages( HttpServletRequest req){
        User iUser = userService.getByEmail(req.getSession().getAttribute("email").toString());
        String idOtherUser = req.getParameter("idOtherUser");
        System.out.println("id : " + idOtherUser);
        User otherUser = userService.getById(idOtherUser);
        log.info("Looking for history messages");
        List<Message> messages = null;
        if (iUser.getMessages().size() != 0){
            messages = service.getMyMessagesAndResponse(iUser.getId(), otherUser.getId());
            messages.forEach(System.out::println);
            log.info("Message history was load");
        }
        //MessageResponse messageResponses = new MessageResponse(messages, otherUser);
        req.getSession().setAttribute("idOtherUser",otherUser.getId());
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
