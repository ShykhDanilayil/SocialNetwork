package shykh.project.SocialNetwork.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shykh.project.SocialNetwork.model.Message;
import shykh.project.SocialNetwork.model.User;

import java.util.Base64;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private List<Message> messages;

    private String base64Image;

    public MessageResponse(List<Message> messages, User user) {
        this.messages = messages;
        this.base64Image = Base64.getEncoder().encodeToString(user.getPhoto());
    }
}
