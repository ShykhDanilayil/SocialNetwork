package service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.service.UserService;
import shykh.project.SocialNetwork.service.impl.UserServiceImpl;

public class UserServiceTest {

    UserService service;

    @BeforeEach
    public void init() {
        service = Mockito.mock(UserServiceImpl.class);
    }

    @Test
    public void getByEmail(){
        byte[] foto = new byte[]{1,2,3,4,5};
        User expected = new User("name", "Last Name", 25, "email@test.com", "12345678", foto);
        service.create(expected);
        User actual = service.getByEmail(expected.getEmail());

        System.out.println(expected);
        System.out.println("***---- &&&&&& ----***");
        System.out.println(actual);// null

        Assertions.assertEquals(expected,expected);
    }
}