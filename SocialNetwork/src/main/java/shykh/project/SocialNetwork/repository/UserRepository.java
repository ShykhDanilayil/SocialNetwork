package shykh.project.SocialNetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shykh.project.SocialNetwork.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
