/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Manager;
import domain.User;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author patrice
 */
@Singleton(name = "userService")
public class UserServiceImpl implements UserService{
    
    @PersistenceContext(unitName = "RMTPU")
    private EntityManager em;
    
    @Override
    public User findUserByLogin(String login){
        try{
            return em.createNamedQuery("User.byLogin", User.class).setParameter("username", login).getSingleResult();
            //return new Manager();
        }catch (final NoResultException userNotFoundEx){
            return null;
        }
        
    }

}
