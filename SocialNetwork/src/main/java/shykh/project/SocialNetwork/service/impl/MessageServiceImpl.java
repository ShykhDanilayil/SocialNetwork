package shykh.project.SocialNetwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shykh.project.SocialNetwork.model.Message;
import shykh.project.SocialNetwork.repository.MessageRepository;
import shykh.project.SocialNetwork.service.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository repository;

    @Override
    public Message create(Message message) {
        return repository.save(message);
    }

    @Override
    public Message getById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Message> getMyMessagesAndResponse(String idUserFrom, String idUserTo) {
        return repository.findAllByIdUserFromAndIdUserToOrIdUserFromAndIdUserTo(idUserFrom, idUserTo, idUserTo, idUserFrom);
    }
}
