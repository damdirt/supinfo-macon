/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.faces;

import business.UserService;
import domain.Manager;
import domain.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author patrice
 */
@ManagedBean
@ViewScoped
public class UserController implements Serializable {
    
    private String username;
    
    private String password;
    
    @EJB
    private UserService service;

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }
    
    
    public String signIn(){
        
        String outcome = null;
        
        final User user = service.findUserByLogin(username);
        if(user != null
           && user.getPassword() != null
           && user.getPassword().equals(password)){
            outcome = 
                (user instanceof Manager) ? "manager_home" : "employee_home";
        }else{
            final FacesContext context = FacesContext.getCurrentInstance();
            final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Bad credentials", "You 're not registered, please register first before sign in.");
            context.addMessage(null, msg);
        }
        
        return outcome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setService(UserService service) {
        this.service = service;
    }
    
}
