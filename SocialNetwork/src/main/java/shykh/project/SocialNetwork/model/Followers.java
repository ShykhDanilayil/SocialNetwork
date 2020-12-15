package shykh.project.SocialNetwork.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


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
}
