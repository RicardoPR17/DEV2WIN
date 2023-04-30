package com.dev2win.iniciativas.data.ideas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<Initiative> getByKeyword(String word) {
        return initiativeRepository.findByKeyword(word);
    }
    
    public void deleteAll() {
        initiativeRepository.deleteAll();
    }
}
