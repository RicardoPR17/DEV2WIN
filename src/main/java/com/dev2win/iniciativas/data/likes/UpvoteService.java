package com.dev2win.iniciativas.data.likes;

import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpvoteService {

    private final UpvoteRepository upvoteRepository;

    @Autowired
    public UpvoteService(UpvoteRepository upvoteRepository) {
        this.upvoteRepository = upvoteRepository;
    }

    public Upvote addUpvote(Upvote upvote) { return upvoteRepository.save(upvote); }

    public List<Upvote> getInitiativeUpvotes(Initiative initiative) {
        return upvoteRepository.findByInitiative(initiative.getInitiativeId());
    }

    public int getInitiativeUpvoteCount(Initiative initiative) { return getInitiativeUpvotes(initiative).size(); }

    public List<Upvote> getUserUpvotes(User user) {
        return upvoteRepository.findByUser(user.getUserId());
    }

    public boolean isUpvoted(Initiative initiative, User user) {
        return upvoteRepository.findUpvote(initiative.getInitiativeId(), user.getUserId()).size() > 0;
    }

    public List<Upvote> getUpvote(Initiative initiative, User user) {
        return upvoteRepository.findUpvote(initiative.getInitiativeId(), user.getUserId());
    }

    public void delete(Upvote upvote) { upvoteRepository.delete(upvote); }

}
