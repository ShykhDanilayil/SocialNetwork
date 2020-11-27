package shykh.project.SocialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message")
public class Message {

    @Column(name = "message_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(name = "user_from")
    private User fromUser;
    @Column(name = "user_to")
    private User toUser;
    @Column(name = "text")
    private String text;
    @Column(name = "date")
    private String date;
    @Column(name = "time")
    private String time;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_massage", joinColumns = @JoinColumn(name = "message_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Message(User fromUser, User toUser, String text) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.text = text;
        this.date = dateMessage.format(dNow);
        this.time = timeMessage.format(dNow);
    }

    Date dNow = new Date();
    SimpleDateFormat dateMessage = new SimpleDateFormat ("dd.MM.yyyy");
    SimpleDateFormat timeMessage = new SimpleDateFormat ("hh:mm:ss");
}
