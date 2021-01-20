package shykh.project.SocialNetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shykh.project.SocialNetwork.model.Followers;
import shykh.project.SocialNetwork.model.User;

import java.util.Optional;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, String> {

    void deleteAllByFromIdAndToId(User fromUser, User tOUser);

    Optional<Followers> findByFromIdAndToId(User fromUser, User tOUser);
}
