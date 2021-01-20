package service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shykh.project.SocialNetwork.SocialNetworkApplication;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.repository.UserRepository;
import shykh.project.SocialNetwork.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialNetworkApplication.class)
public class UserServiceImplTest {

    public User user;

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @Before
    public void init() {
        user = new User("name", "Last Name", 25, "email@test.com", "12345678", new byte[]{1, 2, 3});
    }

    @Test
    public void getByEmail() {
        User expected = user;
        service.create(expected);
        User actual = service.getByEmail(expected.getEmail());

        Assertions.assertEquals(expected, actual);
        repository.delete(actual);
    }

    @Test
    public void getById() {
        service.create(user);

        User expected = service.getByEmail(user.getEmail());
        User actual = service.getById(expected.getId());

        Assertions.assertEquals(expected, actual);
        repository.delete(actual);
    }
}