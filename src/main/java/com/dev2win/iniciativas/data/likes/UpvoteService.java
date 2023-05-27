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

    public Upvote addUpvote(Upvote upvote) { 
        return upvoteRepository.save(upvote); 
    }

    //Recibe una iniciativa pasada por parámetro y devuelve una lista con todos sus likes
    public List<Upvote> getInitiativeUpvotes(Initiative initiative) {
        return upvoteRepository.findByInitiative(initiative.getInitiativeId());
    }

    //Recibe una iniciativa pasada por parámetro y devuelve la cantidad de likes que tiene
    public int getInitiativeUpvoteCount(Initiative initiative) { 
        return getInitiativeUpvotes(initiative).size(); 
    }

    //Recibe un usuario por parámetro y devuelve una lista con todos los likes que ha dado a iniciativas
    public List<Upvote> getUserUpvotes(User user) {
        return upvoteRepository.findByUser(user.getUserId());
    }

    //Recibe una iniciativa y un usuario como parámetros, y devuelve True si ese usuario votó
    //por esa iniciativa - devuelve False de lo contrario
    public boolean isUpvoted(Initiative initiative, User user) {
        return upvoteRepository.findUpvote(initiative.getInitiativeId(), user.getUserId()).size() > 0;
    }

    //Recibe una iniciativa y un usuario como parámetros y devuelve una lista de likes
    //que debería tener un único elemento si ese usuario votó por ella, o estar vacía de lo contrario
    public List<Upvote> getUpvote(Initiative initiative, User user) {
        return upvoteRepository.findUpvote(initiative.getInitiativeId(), user.getUserId());
    }

    //Elimina el like emitido por un usuario hacia una iniciativa.
    public void delete(Upvote upvote) { 
        upvoteRepository.delete(upvote); 
    }

    //Vacía el repositorio (elimina todos los likes, de todos los usuarios, a todas las iniciativas).
    public void deleteAll() {
        upvoteRepository.deleteAll();
    }

}
