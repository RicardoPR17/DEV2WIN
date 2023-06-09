package com.dev2win.iniciativas.faces;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.dev2win.iniciativas.data.comments.Comment;
import com.dev2win.iniciativas.data.comments.CommentService;

import com.dev2win.iniciativas.data.ideas.Initiative;
import com.dev2win.iniciativas.data.ideas.InitiativeService;
import com.dev2win.iniciativas.data.ideas.State;
import com.dev2win.iniciativas.data.likes.Upvote;
import com.dev2win.iniciativas.data.likes.UpvoteService;
import com.dev2win.iniciativas.data.users.User;
import com.dev2win.iniciativas.data.users.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@ManagedBean(name = "initiativeBean")
@SessionScope
public class InitiativeBean {
    @Autowired
    InitiativeService initiativeService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    FacesContextWrapper facesContextWrapper;

    @Autowired
    PrimeFacesWrapper primeFacesWrapper;

    @Autowired
    UpvoteService upvoteService;

    private String description;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String userName;
    private List<Initiative> initiatives = new ArrayList<>();
    private List<Initiative> selectedInitiatives;
    private Initiative selectedInitiative;
    private Comment comment;
    private String commentary;

    private static final String INITIATIVES_MENU_MESSAGES = "initiatives-menu:messages";
    private static final String INITIATIVES_MENU_INITIATIVES_LIST = "initiatives-menu:initiatives-list";

    private boolean loggedUserInitiatives = false;

    public boolean isLoggedUserInitiatives() {
        return loggedUserInitiatives;
    }

    public void setLoggedUserInitiatives(boolean loggedUserInitiatives) {
        this.loggedUserInitiatives = loggedUserInitiatives;
    }

    /**
     * Empty contructor
     */
    public InitiativeBean() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateInitiative() {
        return selectedInitiative.getDateText();
    }

    public String getKeysWordsInitiative() {
        return selectedInitiative.getKeywords();
    }

    public String getStateInitiative() {
        return selectedInitiative.getState();
    }

    public String getNameUserInitiative() {
        return selectedInitiative.getUser().getName();
    }

    public String getMailUserInitiative() {
        return selectedInitiative.getUser().getMail();
    }

    public String getDescriptionInitiative() {
        return selectedInitiative.getDescription();
    }

    public String getAreaInitiative() {
        return selectedInitiative.getArea();
    }

    public String getProfileUser() {
        return selectedInitiative.getUser().getProfile();
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Initiative> getInitiatives() {
        return initiatives;
    }

    public void setInitiatives(List<Initiative> initiatives) {
        this.initiatives = initiatives;
    }

    public List<Initiative> getSelectedInitiatives() {
        return selectedInitiatives;
    }

    public void setSelectedInitiatives(List<Initiative> selectedInitiatives) {
        this.selectedInitiatives = selectedInitiatives;
    }

    public List<Initiative> getAll() {
        return initiativeService.getAllInitiatives();
    }

    public List<Comment> getAllComments() {
        return commentService.getCommentsOfInitiative(selectedInitiative.getInitiativeId());
    }

    public Initiative getSelectedInitiative() {
        return selectedInitiative;
    }

    public void setSelectedInitiative(Initiative selectedInitiative) {
        this.selectedInitiative = selectedInitiative;
    }

    //Construye la vista de las iniciativas según si se ha seleccionado que se muestren las del usuario logueado o no,
    //en caso contrario muestra todas.
    public void onDatabaseLoaded(String userName) {
        if (loggedUserInitiatives) {
            User user = userService.getUserByMail(userName);
            this.initiatives = initiativeService.getUserInitiatives(user);
        } else {
            this.initiatives = initiativeService.getAllInitiatives();
        }
    }

    //Crea una nueva iniciativa vacía y la asigna como seleccionada en el bean.
    public void newInitiative() {
        this.selectedInitiative = new Initiative();
    }

    public void newComment() {
        this.comment = new Comment();
    }

    //Guarda los cambios subidos al repositorio de iniciativas, tanto para registrar una nueva o como para
    //actualizar los datos de una iniciativa ya existente.
    public int saveInitiative(String userName) {
        int flag = -1;
        if (this.selectedInitiative.getUser() == null) {
            User userOwner = userService.getUserByMail(userName);
            this.selectedInitiative.setUser(userOwner);
            this.selectedInitiative.setDate(LocalDate.now());

            this.selectedInitiative.setState(State.OPEN.getValue());

            this.selectedInitiative.setNumberLikes("0");

            initiativeService.addInitiative(this.selectedInitiative);
            facesContextWrapper.getCurrentInstance().addMessage(null, new FacesMessage("Initiative Added"));
            flag = 1;
        } else {
            initiativeService.updateInitiative(this.selectedInitiative);
            facesContextWrapper.getCurrentInstance().addMessage(null, new FacesMessage("Initiative Updated"));
            flag = 0;
        }

        primeFacesWrapper.current().executeScript("PF('manageIdeaDialog').hide()");
        primeFacesWrapper.current().executeScript("PF('manageStateDialog').hide()");
        primeFacesWrapper.current().ajax().update(INITIATIVES_MENU_MESSAGES, INITIATIVES_MENU_INITIATIVES_LIST);
        return flag;
    }

    //Elimina la iniciativa seleccionada del repositorio de iniciativas de la aplicación.
    public void deleteInitiative() {
        facesContextWrapper.getCurrentInstance().addMessage(null, new FacesMessage("Initiative Removed"));
        primeFacesWrapper.current().ajax().update(INITIATIVES_MENU_MESSAGES, INITIATIVES_MENU_INITIATIVES_LIST);
        initiativeService.deleteInitiative(this.selectedInitiative.getInitiativeId());
    }

    //Devuelve un mensaje mostrando la cantidad de iniciativas seleccionadas para mostrar al usuario
    //por pantalla.
    public String getUpdateButtonMessage() {
        if (hasSelectedInitiatives()) {
            int size = this.selectedInitiatives.size();
            return size > 1 ? size + " initiatives selected" : "1 initiative selected";
        }

        return "Update";
    }

    //Devuelve un valor booleano indicando si se han seleccionado iniciativas.
    public boolean hasSelectedInitiatives() {
        return this.selectedInitiatives != null && !this.selectedInitiatives.isEmpty();
    }

    //Valida que el usuario sólo pueda realizar las acciones permitidas sobre sus propias iniciativas,
    //y no sobre iniciativas propuestas por otros usuarios de la aplicación.
    public void isYourInitiative(String userName, String dialogType) {
        if (this.selectedInitiative.getUser().getMail().equals(userName)) {
            if (dialogType.equals("delete")) {
                primeFacesWrapper.current().executeScript("PF('deleteInitiativeDialog').show()");
            } else if (dialogType.equals("edit")) {
                primeFacesWrapper.current().executeScript("PF('manageIdeaDialog').show()");
            }
        } else {
            facesContextWrapper.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Is not your initiative", "Error"));
            primeFacesWrapper.current().ajax().update(INITIATIVES_MENU_MESSAGES, INITIATIVES_MENU_INITIATIVES_LIST);
        }
    }

    //Registra un nuevo like hacia una iniciativa.
    public void upvoteInitiative() {
        if (this.selectedInitiative != null) {
            primeFacesWrapper.current().executeScript("PF('upvoteInitiativeDialog').show()");
            primeFacesWrapper.current().ajax().update("dialogs:upvote-content");
        }
    }

    //Devuelve un valor booleano indicado si el usuario logueado le ha dado like a la iniciativa seleccionada o no.
    public boolean isUpvoted(String userName) {
        User user = userService.getUserByMail(userName);
        if (this.selectedInitiative != null) {
            if (!upvoteService.getUpvote(this.selectedInitiative, user).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    //Devuelve un valor para mostrar al usuario por pantalla cuando este quiera votar por una iniciativa
    //o quitar su voto anteriormente registrado.
    public String upvoteMessage(String userName) {
        String message = "Do you want to upvote this initiative?";
        if (isUpvoted(userName)) {
            message = "Do you want to remove your upvote?";
        }
        return message;
    }

    //Registra un nuevo voto por una iniciativa, o quita el voto anteriormente registrado por un usuario hacia una
    //iniciativa, y actualiza el número de likes de la misma.
    public void changeVote(String userName) {
        User user = userService.getUserByMail(userName);
        if (this.selectedInitiative != null) {
            if (isUpvoted(userName)) {
                Upvote upvote = upvoteService.getUpvote(this.selectedInitiative, user).get(0);
                upvoteService.delete(upvote);
            } else {
                Upvote newUpvote = new Upvote(this.selectedInitiative, user);
                upvoteService.addUpvote(newUpvote);
            }
        }
        String counts = Integer.toString(upvoteService.getInitiativeUpvoteCount(this.selectedInitiative));
        this.selectedInitiative.setNumberLikes(counts);
        initiativeService.updateInitiative(this.selectedInitiative);
        primeFacesWrapper.current().ajax().update("initiatives-menu:initiatives-list");
        primeFacesWrapper.current().ajax().update("comments-menu");
    }

    //Actualiza la vista de las iniciativas - este evento es llamado cuando se cambia la opción de mostrar sólo
    //las del usuario logueado o no.
    public void changeLoggedInitiativesView() {
        primeFacesWrapper.current().ajax().update(INITIATIVES_MENU_MESSAGES, INITIATIVES_MENU_INITIATIVES_LIST);
    }

    //Registra un nuevo comentario sobre una iniciativa.
    public void saveComment(String userName) {
        User userOpinion = userService.getUserByMail(userName);
        this.comment.setCommentary(commentary);
        this.comment.setInitiative(selectedInitiative);
        this.comment.setUser(userOpinion);
        commentService.addComment(comment);
        setCommentary("");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Comment Added"));
        primeFacesWrapper.current().executeScript("PF('manageCommentDialog').hide()");
        primeFacesWrapper.current().ajax().update("comments-menu:messages", "comments-menu:comments-list");
    }

    //Redirige a la página de vista de iniciativas en el frontend.
    public String redirectToNewPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        try {
            externalContext.redirect("viewInitiatives.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Redirige a la página de vista de iniciativas según si el usuario es administrador o proponente.
    public void backPage(String userName) {
        try {
            ExternalContext ec = facesContextWrapper.getCurrentInstance().getExternalContext();
            User userToLogin = userService.getUserByMail(userName);
            String redirectPath = getRedirectPath(userToLogin);
            ec.redirect(ec.getRequestContextPath() + redirectPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Devuelve la ruta a la cual redirigir al usuario según si este es un administrador o un proponente.
    private String getRedirectPath(User user) {
        if (user.getRole().equals("Administrador")) {
            return "../pages/welcomeAdmin.xhtml";
        } else {
            return "../pages/welcomeProponent.xhtml";
        }
    }

}
