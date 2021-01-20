package shykh.project.SocialNetwork.service;

import shykh.project.SocialNetwork.model.Followers;
import shykh.project.SocialNetwork.model.User;

public interface FollowersService {

    void save(Followers follower);

    void delete(User fromUser, User tOUser);

    Followers getFollowers(User fromUser, User tOUser);
}
