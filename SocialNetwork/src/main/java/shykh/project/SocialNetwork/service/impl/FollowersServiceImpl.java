package shykh.project.SocialNetwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shykh.project.SocialNetwork.model.Followers;
import shykh.project.SocialNetwork.model.User;
import shykh.project.SocialNetwork.repository.FollowersRepository;
import shykh.project.SocialNetwork.service.FollowersService;

@Service
public class FollowersServiceImpl implements FollowersService {

    @Autowired
    FollowersRepository repository;

    @Override
    public void save(Followers follower) {
        repository.save(follower);
    }

    @Override
    public void delete(User fromUser, User tOUser) {
        repository.deleteAllByFromIdAndToId(fromUser, tOUser);
    }

    @Override
    public Followers getFollowers(User fromUser, User tOUser) {
        return repository.findByFromIdAndToId(fromUser, tOUser).orElse(null);
    }
}
