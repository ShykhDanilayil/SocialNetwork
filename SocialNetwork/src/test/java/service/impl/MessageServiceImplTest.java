package service.impl;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shykh.project.SocialNetwork.SocialNetworkApplication;
import shykh.project.SocialNetwork.model.Message;
import shykh.project.SocialNetwork.repository.MessageRepository;
import shykh.project.SocialNetwork.service.MessageService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialNetworkApplication.class)
public class MessageServiceImplTest {

    @Autowired
    private MessageService service;

    @Autowired
    private MessageRepository repository;

    @Test
    public void getMessage() {
        Message expected = new Message("1", "2", "hi !!");
        service.create(expected);

        Message actual = service.getMyMessagesAndResponse("1","2").get(0);
        expected.setTime(actual.getTime());


        Assertions.assertEquals(expected, actual);
        repository.delete(actual);
    }
}