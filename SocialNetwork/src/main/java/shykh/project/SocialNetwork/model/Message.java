package shykh.project.SocialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "message_id")
    @Setter
    @Getter
    private String id;
    @Column(name = "id_user_from")
    @Setter
    @Getter
    private String idUserFrom;
    @Column(name = "id_user_to")
    @Setter
    @Getter
    private String idUserTo;
    @Column(name = "text")
    @Setter
    @Getter
    private String text;
    @Column(name = "date_time")
    @Setter
    @Getter
    private Date dateAndTime;
    @Column(name = "date")
    @Setter
    @Getter
    private String date;
    @Column(name = "time")
    @Getter
    @Setter
    private String time;
    @ManyToMany(mappedBy = "messages")
    private List<User> users = new ArrayList<>();

    public Message(String idUserFrom, String idUserTo, String text) {
        this.idUserFrom = idUserFrom;
        this.idUserTo = idUserTo;
        this.text = text;
        this.dateAndTime = new Date();
        this.date = new SimpleDateFormat ("dd.MM.yyyy").format(new Date());
        this.time = new SimpleDateFormat ("kk:mm:ss").format(new Date());
    }

    public void addUser(User user){
        users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(idUserFrom, message.idUserFrom) &&
                Objects.equals(idUserTo, message.idUserTo) &&
                Objects.equals(text, message.text) &&
                Objects.equals(date, message.date) &&
                Objects.equals(time, message.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUserFrom, idUserTo, text, date, time);
    }

    @Override
    public String toString() {
        return "Message{" +
                "idUserFrom='" + idUserFrom + '\'' +
                ", idUserTo='" + idUserTo + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
