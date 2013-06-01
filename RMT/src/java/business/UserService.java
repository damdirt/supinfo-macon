/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.User;
import javax.ejb.Remote;

/**
 *
 * @author patrice
 */
@Remote
public interface UserService {
    
    User findUserByLogin(String login);
}
