/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Employee;
import domain.Manager;
import domain.User;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author patrice
 */
public class UserServiceTest {
    
    private UserService userService;
    
    private static EJBContainer container;
        
    @BeforeClass
    public static void setUp() {
        container = EJBContainer.createEJBContainer();
    }

    public UserServiceTest() {
        try {   
            Context ctx = container.getContext();
            userService = (UserService) ctx.lookup("java:global/classes/userService");
        } catch (NamingException e) {
            e.printStackTrace();
            Assert.fail("Failed. Exception thrown.");
        }
    }
    
    
    
    
    @Test
    public void testFindManagerByLogin(){
        final Manager manager = (Manager) userService.findUserByLogin("manager@supinfo");
        Assert.assertNotNull(manager);
    }
    
    @Test
    public void testFindEmployeeByLogin(){
        final Employee employee = (Employee) userService.findUserByLogin("employee@supinfo");
        Assert.assertNotNull(employee);
    }
    
    @Test
    public void testUserNotExistsByLogin(){
        final User user = userService.findUserByLogin("manager");
        Assert.assertNull(user);
    }
    
    @AfterClass
    public static void tearDown() {
        container.close();
    }
}