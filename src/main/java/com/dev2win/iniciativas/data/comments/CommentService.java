package com.dev2win.iniciativas.data.comments;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
  
  private final CommentRepository commentRepository;

  @Autowired
  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public Comment addComment(Comment commentary) {
    commentary.setDate(LocalDate.now());
    return commentRepository.save(commentary);
  }

  /**
   * Obtiene todos los comentarios de una iniciativa
   * @param initiativeId identificador de la iniciativa
   * @return Lista de comentarios de la iniciativa
   */
  public List<Comment> getCommentsOfInitiative(Long initiativeId) {
    return commentRepository.findByInitiative(initiativeId);
  }

  /**
   * Cantidad de comentarios en una iniciativa
   * @param initiativeId identificador de la iniciativa
   * @return Numero de comentarios que tiene la iniciativa seleccionada
   */
  public int getNumberCommentsOfInitiative(Long initiativeId) {
    return commentRepository.findByInitiative(initiativeId).size();
  }

  public void deleteComment(Long commentId) {
    commentRepository.deleteById(commentId);
  }

  public void deleteAll() {
    commentRepository.deleteAll();
  }

}
