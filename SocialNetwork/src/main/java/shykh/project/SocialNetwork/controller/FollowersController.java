package shykh.project.SocialNetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import shykh.project.SocialNetwork.model.Followers;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.service.impl.FollowersServiceImpl;
import shykh.project.SocialNetwork.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Transactional
@RestController
public class FollowersController {

    @Autowired
    FollowersServiceImpl followersService;

    @Autowired
    UserServiceImpl service;

    @PostMapping("/following")
    public void addFriend(HttpServletRequest req) {
        log.info("Looking for a user by email");
        User iUser = service.getByEmail(req.getSession().getAttribute("email").toString());
        if (Objects.nonNull(req.getParameter("idOtherUser"))) {
            User otherUser = service.getById(req.getParameter("idOtherUser"));
            log.info("Other user email : {}", otherUser.getEmail());

            Set<Followers> followingSet = new HashSet<>();
            Followers following = new Followers(iUser, otherUser);

            followingSet.add(following);

            if (!iUser.getFollowing().contains(following)) {
                iUser.setFollowing(followingSet);
            }
            if (!otherUser.getFollowers().contains(following)) {
                otherUser.setFollowers(followingSet);
            }

            followersService.save(following);
            log.info("Following save");
        }
    }

    @PostMapping("/follow")
    public void deleteFriend(HttpServletRequest req) {
        log.info("Looking for a user by email");
        User iUser = service.getByEmail(req.getSession().getAttribute("email").toString());
        if (Objects.nonNull(req.getParameter("idOtherUser"))) {
            User otherUser = service.getById(req.getParameter("idOtherUser"));
            log.info("Other user email : {}", otherUser.getEmail());

            Followers following = new Followers(iUser, otherUser);

            if (iUser.getFollowing().contains(following)) {
                iUser.getFollowing().remove(following);
            }
            if (otherUser.getFollowers().contains(following)) {
                otherUser.getFollowers().remove(following);
            }

            followersService.delete(iUser, otherUser);
            log.info("Following delete");
        }
    }
}