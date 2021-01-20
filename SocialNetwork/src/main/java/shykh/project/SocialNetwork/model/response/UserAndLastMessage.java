package shykh.project.SocialNetwork.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shykh.project.SocialNetwork.model.Message;
import shykh.project.SocialNetwork.model.User;

import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndLastMessage {

    private String id;

    private String name;

    private String lastName;

    private String base64Image;

    private String lastText;

    private  String date;

    public UserAndLastMessage(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.base64Image = Base64.getEncoder().encodeToString(user.getPhoto());
    }
    public void addLastMess(Message message){
        this.lastText = message.getText();
        this.date = message.getDate();
    }
}
