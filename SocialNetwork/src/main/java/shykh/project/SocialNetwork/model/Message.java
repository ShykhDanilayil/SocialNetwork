package shykh.project.SocialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "message_id")
    private String id;
    @Column(name = "id_user_from")
    private String idUserFrom;
    @Column(name = "id_user_to")
    private String idUserTo;
    @Column(name = "text")
    private String text;
    @Column(name = "date")
    private String date;
    @Column(name = "time")
    private String time;
    @ManyToMany(mappedBy = "messages")
    private List<User> users;

    public Message(String idUserFrom, String idUserTo, String text) {
        this.idUserFrom = idUserFrom;
        this.idUserTo = idUserTo;
        this.text = text;
        this.date = new SimpleDateFormat ("dd.MM.yyyy").format(new Date());
        this.time = new SimpleDateFormat ("hh:mm:ss").format(new Date());
    }

    public void addUser(User user){
        users.add(user);
    }
}
