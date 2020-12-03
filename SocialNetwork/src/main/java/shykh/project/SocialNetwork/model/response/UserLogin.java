package shykh.project.SocialNetwork.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shykh.project.SocialNetwork.model.User;

import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {

    private String name;

    private String lastName;

    private int age;

    private String email;

    private String base64Image;

    public UserLogin(User user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.base64Image = Base64.getEncoder().encodeToString(user.getPhoto());
    }
}
