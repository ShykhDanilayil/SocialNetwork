package service.impl;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shykh.project.SocialNetwork.SocialNetworkApplication;
import shykh.project.SocialNetwork.model.Followers;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.repository.FollowersRepository;
import shykh.project.SocialNetwork.repository.UserRepository;
import shykh.project.SocialNetwork.service.FollowersService;
import shykh.project.SocialNetwork.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialNetworkApplication.class)
public class FollowersServiceImplTest {

    @Autowired
    private FollowersService service;

    @Autowired
    private FollowersRepository rep;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;

    @Test
    public void getMessage() {
        User us1 = new User("name", "Last Name", 25, "email@test.com", "12345678", new byte[]{1, 2, 3});
        User us2 = new User("name2", "Last Name2", 25, "email2@test.com", "12345678", new byte[]{1, 2, 3});
        userService.create(us1);
        userService.create(us2);

        User user1 = userService.getByEmail(us1.getEmail());
        User user2 = userService.getByEmail(us2.getEmail());

        Followers expected = new Followers(user1, user2);
        service.save(expected);

        Followers actual = service.getFollowers(user1, user2);

        Assertions.assertEquals(expected, actual);

        rep.delete(actual);
        repository.delete(user1);
        repository.delete(user2);
    }
}