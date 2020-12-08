package shykh.project.SocialNetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shykh.project.SocialNetwork.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

    List<Message> findAllByIdUserFromAndIdUserToOrIdUserFromAndIdUserToOrderByDateAndTime(String idUserFrom, String idUserTo, String idUserFrom2, String idUserTo2);
}
