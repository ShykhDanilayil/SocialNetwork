package shykh.project.SocialNetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shykh.project.SocialNetwork.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {


}
