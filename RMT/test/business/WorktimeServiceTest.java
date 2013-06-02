/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Client;
import domain.Worktime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
public class WorktimeServiceTest {
    
    private WorktimeService worktimeService;
    
    private static EJBContainer container;
    
    private static EntityManager em;
        
    @BeforeClass
    public static void setUp() {
        container = EJBContainer.createEJBContainer();
        em = Persistence.createEntityManagerFactory("RMTPU").createEntityManager();
    }

    public WorktimeServiceTest() {
        try {   
            Context ctx = container.getContext();
            worktimeService = (WorktimeService) ctx.lookup("java:global/classes/worktimeService");
         } catch (NamingException e) {
            e.printStackTrace();
            Assert.fail("Failed. Exception thrown.");
        }
    }
    
    
    @Test
    public void testAddWorktime(){
        final EntityTransaction et = em.getTransaction();
        et.begin();
        Client client = new Client();    
        client.setName("test");
        em.persist(client);
        et.commit();
        
        Worktime wkTime = new Worktime();
        final Calendar c = Calendar.getInstance();
        wkTime.setBeginDate(c.getTime());
        c.add(Calendar.MONTH, 1);
        wkTime.setEndDate(c.getTime());
        wkTime.setClient(client);
        
        final Worktime result = worktimeService.addWorktime(wkTime);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
        Assert.assertNotNull(result.getClient());
        
        et.begin();
        
        wkTime = em.getReference(Worktime.class, result.getId());
        em.remove(wkTime);
        client = em.getReference(Client.class, client.getId());
        em.remove(client);
        et.commit();
    }
    
    @Test
    public void testFindAll(){
        final Long nbClientExisted = 
             (Long) em.createQuery("SELECT COUNT(c) FROM Worktime c").getSingleResult();
        
        
        final int nbTestClients = 2;
        final int nbClientsTot = nbTestClients + nbClientExisted.intValue();
        final List<Worktime> worktimes = new ArrayList<Worktime>();
        final EntityTransaction et = em.getTransaction();
        et.begin();
        
        Client c = new Client();
        c.setName("test");
        em.persist(c);
        
        for(int i=0; i<nbTestClients; i++){
            final Worktime w = new Worktime();
            w.setBeginDate(new Date());
            w.setClient(c);
            em.persist(w);
            worktimes.add(w);
        }
        et.commit();
        
        final List<Worktime> result = worktimeService.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(nbClientsTot, result.size());
        
        et.begin();
        for(int i=0; i<nbTestClients; i++){
            final Worktime worktime = em.getReference(Worktime.class, worktimes.get(i).getId());
            em.remove(worktime);
        }
        c = em.getReference(Client.class, c.getId());
        em.remove(c);
        et.commit();
    }
    
    @Test
    public void testDelete(){
        final EntityTransaction et = em.getTransaction();
        et.begin();
        Client client = new Client();    
        client.setName("test");
        em.persist(client);
        
        Worktime wkTime = new Worktime();
        final Calendar c = Calendar.getInstance();
        wkTime.setBeginDate(c.getTime());
        c.add(Calendar.MONTH, 1);
        wkTime.setEndDate(c.getTime());
        wkTime.setClient(client);
        em.persist(wkTime);
        et.commit();
        
        final boolean result = worktimeService.delete(wkTime.getId());
        
        et.begin();
        Assert.assertTrue(result);
        
        client = em.getReference(Client.class, client.getId());
        em.remove(client);
        et.commit();
    }

    
    @AfterClass
    public static void tearDown() {
        em.close();
        container.close();
    }
}