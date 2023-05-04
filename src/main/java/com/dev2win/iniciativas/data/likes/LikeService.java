package com.dev2win.iniciativas.data.likes;

import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Like addLike(Like like) { return likeRepository.save(like); }

    public List<Like> getInitiativeLikes(Initiative initiative) {
        return likeRepository.findByInitiative(initiative.getInitiativeId());
    }

    public int getInitiativeLikeCount(Initiative initiative) { return getInitiativeLikes(initiative).size(); }

    public List<Like> getUserLikes(User user) {
        return likeRepository.findByUser(user.getUserId());
    }

}
