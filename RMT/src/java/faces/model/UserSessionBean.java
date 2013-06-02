/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package faces.model;

import domain.Manager;
import domain.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author patrice
 */
@ManagedBean(name = "userSession")
@SessionScoped
public class UserSessionBean implements Serializable{
    
    private User user;

    /**
     * Creates a new instance of UserSessionBean
     */
    public UserSessionBean() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    
    public Manager getUserAsManager(){
        return (Manager) user;
    }
}
