package shykh.project.SocialNetwork.service;

import shykh.project.SocialNetwork.model.User;

import java.util.List;

public interface UserService {

    User created(User user);

    User getById(String id);

    List<User> getAllUsers();
}
