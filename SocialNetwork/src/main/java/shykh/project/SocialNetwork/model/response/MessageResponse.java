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

    private String idUserFrom;

    private String text;

    private String date;

    private String time;

    private String base64Image;

    public MessageResponse(Message message) {
        this.idUserFrom = message.getIdUserFrom();
        this.text = message.getText();
        this.date = message.getDate();
        this.time = message.getTime();
    }

    public void addPhotoOtherUser(User user){
        this.base64Image = Base64.getEncoder().encodeToString(user.getPhoto());
    }
}
