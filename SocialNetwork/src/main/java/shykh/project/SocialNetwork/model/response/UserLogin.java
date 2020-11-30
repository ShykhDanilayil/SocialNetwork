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

    private String password;

    private String email;

    private String photo;

    public UserLogin(User user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.photo = Base64.getEncoder().encodeToString(user.getPhoto());
    }
}
