package com.dev2win.iniciativas.data.ideas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  
}
