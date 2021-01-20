package shykh.project.SocialNetwork.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "user_id")
    @Setter
    @Getter
    private String id;
    @Column(name = "name")
    @Setter
    @Getter
    private String name;
    @Column(name = "lastName")
    @Setter
    @Getter
    private String lastName;
    @Column(name = "age")
    @Setter
    @Getter
    private int age;
    @Column(name = "email")
    @Setter
    @Getter
    private String email;
    @Column(name = "password")
    @Setter
    @Getter
    private String password;
    @Lob
    @Column(name = "photo")
    @Setter
    @Getter
    private byte[] photo;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_massage", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id"))
    @Getter
    private List<Message> messages = new ArrayList<>();
    @OneToMany(mappedBy = "toId")
    private Set<Followers> followers = new HashSet<>();
    @OneToMany(mappedBy = "fromId")
    private Set<Followers> following = new HashSet<>();

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
