package shykh.project.SocialNetwork.service;

import shykh.project.SocialNetwork.model.Message;

import java.util.List;

public interface MessageService {

    Message create(Message message);

    List<Message>  getMyMessagesAndResponse(String idUserFrom, String idUserTo);
}
