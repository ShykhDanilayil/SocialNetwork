package shykh.project.SocialNetwork.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.service.impl.FollowersServiceImpl;

import java.util.Base64;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {

    private String id;

    private String name;

    private String lastName;

    private int age;

    private String email;

    private String isFriend;

    private String base64Image;

    public UserLogin(User user, String isFriend) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.isFriend = isFriend;
        this.base64Image = Base64.getEncoder().encodeToString(user.getPhoto());
    }
}
