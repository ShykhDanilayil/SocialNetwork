package shykh.project.SocialNetwork.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "followers")
public class Followers {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "follower_id")
    @Getter
    @Setter
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_fr_from")
    private User fromId;
    @ManyToOne
    @JoinColumn(name = "user_fr_to")
    private User toId;

    public Followers(User from, User to) {
        this.fromId = from;
        this.toId = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Followers followers = (Followers) o;
        return Objects.equals(id, followers.id) &&
                Objects.equals(fromId, followers.fromId) &&
                Objects.equals(toId, followers.toId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromId, toId);
    }
}
