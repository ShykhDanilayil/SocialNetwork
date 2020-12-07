package shykh.project.SocialNetwork.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "user_id")
    @Getter
    @Setter
    private String id;
    @Column(name = "name")
    @Getter
    @Setter
    private String name;
    @Column(name = "lastName")
    @Getter
    @Setter
    private String lastName;
    @Column(name = "age")
    @Getter
    @Setter
    private int age;
    @Column(name = "email")
    @Getter
    @Setter
    private String email;
    @Column(name = "password")
    @Getter
    @Setter
    private String password;
    @Lob
    @Column(name = "photo")
    @Getter
    @Setter
    private byte[] photo;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_massage", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id"))
    @Getter
    private List<Message> messages = new ArrayList<>();

    public User(String name, String lastName, int age, String email, String password, byte[] photo) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                id.equals(user.id) &&
                name.equals(user.name) &&
                lastName.equals(user.lastName) &&
                email.equals(user.email) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, age, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", photo=" + Arrays.toString(photo) +
                '}';
    }
}
