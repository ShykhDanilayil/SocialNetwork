package shykh.project.SocialNetwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.repository.UserRepository;
import shykh.project.SocialNetwork.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User created(User user) {
        return repository.save(user);
    }

    @Override
    public User getById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
