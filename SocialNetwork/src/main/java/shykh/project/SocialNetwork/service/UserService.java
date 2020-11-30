package shykh.project.SocialNetwork.service;

import shykh.project.SocialNetwork.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User getById(String id);

    User getByEmail(String email);

    List<User> getAllUsers();
}
