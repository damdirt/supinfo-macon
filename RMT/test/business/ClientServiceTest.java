/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Client;
import domain.Employee;
import domain.Manager;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author patrice
 */
public class ClientServiceTest {
    
    private ClientService clientService;
    
    private static EJBContainer container;
    
    private static EntityManager em;
        
    @BeforeClass
    public static void setUp() {
        container = EJBContainer.createEJBContainer();
        em = Persistence.createEntityManagerFactory("RMTPU").createEntityManager();
    }

    public ClientServiceTest() {
        try {   
            Context ctx = container.getContext();
            clientService = (ClientService) ctx.lookup("java:global/classes/clientService");
        } catch (NamingException e) {
            e.printStackTrace();
            Assert.fail("Failed. Exception thrown.");
        }
    }
    
    @Test
    public void testAddClient(){
        final Client client = new Client();
        client.setName("test");
        final Client clientAdded = clientService.addClient(client);
        Assert.assertNotNull(clientAdded);
        Assert.assertNotNull(clientAdded.getId());
        final EntityTransaction et = em.getTransaction();
        et.begin();
        final Client clientToClean = em.find(Client.class, clientAdded.getId());
        em.remove(clientToClean);
        et.commit();
    }
    
    @AfterClass
    public static void tearDown() {
        em.close();
        container.close();
    }
}