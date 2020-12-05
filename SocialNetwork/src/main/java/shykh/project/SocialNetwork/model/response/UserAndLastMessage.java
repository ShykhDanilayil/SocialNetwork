package shykh.project.SocialNetwork.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
        this.lastText = getMessage(user);
        this.date = getData(user);
    }
    private String getMessage(User user) {
        if (user.getMessages().size() != 0) {
            return user.getMessages().get(user.getMessages().size() - 1).getText();
        }
        return "";
    }
    private String getData(User user) {
        if (user.getMessages().size() != 0) {
            return user.getMessages().get(user.getMessages().size() - 1).getDate();
        }
        return "";
    }
}
