package com.dev2win.iniciativas.data.ideas;

import com.dev2win.iniciativas.data.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitiativeService {

    private final InitiativeRepository initiativeRepository;

    @Autowired
    public InitiativeService(InitiativeRepository initiativeRepository) {
        this.initiativeRepository = initiativeRepository;
    }

    public Initiative addInitiative(Initiative initiative) {
        return initiativeRepository.save(initiative);
    }

    public List<Initiative> getAllInitiatives() {
        return initiativeRepository.findAll();
    }

    public void deleteInitiative(Long initiativeId) {
        initiativeRepository.deleteById(initiativeId);
    }

    public Initiative updateInitiative(Initiative initiative) {
        if (initiativeRepository.existsById(initiative.getInitiativeId())) {
            return initiativeRepository.save(initiative);
        }
        return null;
    }

    public void deleteAll() {
        initiativeRepository.deleteAll();
    }

    public List<Initiative> getUserInitiatives(User user) {
        return initiativeRepository.findByUser(user.getUserId());
    }

    public Long countByState(String requiredState){
        return initiativeRepository.countByState(requiredState);
    }
}
