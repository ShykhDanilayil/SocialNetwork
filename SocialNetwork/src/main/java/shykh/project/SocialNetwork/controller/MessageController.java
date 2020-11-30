package shykh.project.SocialNetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shykh.project.SocialNetwork.model.Message;
import shykh.project.SocialNetwork.service.impl.MessageServiceImpl;
import shykh.project.SocialNetwork.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class MessageController {

    @Autowired
    MessageServiceImpl service;
    UserServiceImpl userService;

    @PostMapping("/message")
    public ResponseEntity<?> createMessage(@RequestBody Message message, HttpServletRequest req){
        service.create(message);
        message.addUser(userService.getByEmail(req.getSession().getAttribute("email").toString()));
        log.info("Message {} was added", message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/message")
    public ResponseEntity<List<Message>> getAllMessages(){
        log.info("Looking for all messages");
        List<Message> messages = service.getAllMessages();
        if (messages.isEmpty()){
            log.info("No records found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("id") String id){
        log.info("Looking for a message by id {}", id);
        Message message = service.getById(id);
        if (Objects.isNull(message)){
            log.error("No message by id {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
