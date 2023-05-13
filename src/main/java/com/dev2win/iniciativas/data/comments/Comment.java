package com.dev2win.iniciativas.data.comments;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.users.User;

@Entity
public class Comment {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column (name = "commentId")
  private Long commentId;

  @Column(name = "dateComment")
  private LocalDate date;

  @Column(name = "comment_initiative")
  private String commentary;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "initiative_id")
  private Initiative initiative;

  public Comment(String commentary, User user, Initiative initiative) {
    this.date = LocalDate.now();
    this.commentary = commentary;
    this.user = user;
    this.initiative = initiative;
  }

  public Comment() {

  }

  public Long getCommentId() {
    return commentId;
  }

  public void setCommentId(Long commentId) {
    this.commentId = commentId;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getCommentary() {
    return commentary;
  }

  public void setCommentary(String commentary) {
    this.commentary = commentary;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Initiative getInitiative() {
    return initiative;
  }

  public void setInitiative(Initiative initiative) {
    this.initiative = initiative;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((commentId == null) ? 0 : commentId.hashCode());
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + ((commentary == null) ? 0 : commentary.hashCode());
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    result = prime * result + ((initiative == null) ? 0 : initiative.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Comment other = (Comment) obj;
    if (commentId == null) {
      if (other.commentId != null)
        return false;
    } else if (!commentId.equals(other.commentId))
      return false;
    if (date == null) {
      if (other.date != null)
        return false;
    } else if (!date.equals(other.date))
      return false;
    if (commentary == null) {
      if (other.commentary != null)
        return false;
    } else if (!commentary.equals(other.commentary))
      return false;
    if (user == null) {
      if (other.user != null)
        return false;
    } else if (!user.equals(other.user))
      return false;
    if (initiative == null) {
      if (other.initiative != null)
        return false;
    } else if (!initiative.equals(other.initiative))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Comment [commentId=" + commentId + ", date=" + date + ", commentary=" + commentary + ", user=" + user
        + ", initiative=" + initiative + "]";
  }

}
