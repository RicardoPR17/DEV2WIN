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

  public List<Initiative> getByKeyword(String word) {
    List<Initiative> listOne = initiativeRepository.findByKeyword1(word);
    List<Initiative> listTwo = initiativeRepository.findByKeyword2(word);
    List<Initiative> listThree = initiativeRepository.findByKeyword3(word);
    return Stream.of(listOne, listTwo, listThree).flatMap(Collection::stream).collect(Collectors.toList());
  }
}
