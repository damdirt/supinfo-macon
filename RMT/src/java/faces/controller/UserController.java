/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package faces.controller;

import business.UserService;
import domain.Employee;
import domain.User;
import faces.FacesUtils;
import faces.model.UserSessionBean;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author patrice
 */
@ManagedBean
@ViewScoped
public class UserController implements Serializable {
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    @ManagedProperty("#{userSession}")
    private UserSessionBean userSession;
    
    @EJB
    private UserService service;    
    
    public String signIn(){
        
        String outcome = null;
        
        final User user = service.findUserByLogin(username);
        if(user != null
           && user.getPassword() != null
           && user.getPassword().equals(password)){
            outcome = (user instanceof Employee) ? "employee_home" : "manager_home";
            userSession.setUser(user);
        }else{
            FacesUtils.addFacesErrorMessage("Bad credentials", "You 're not registered, please register first before sign in.");
        }
        
        return outcome;
    }
    
    public String signOut(){
        userSession.setUser(null);
        return "login";
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

    public void setUserSession(UserSessionBean userSession) {
        this.userSession = userSession;
    }
    
    
}
