/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

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
public class EmployeeServiceTest {
    
    private EmployeeService employeeService;
    
    private static EJBContainer container;
    
    private static EntityManager em;
        
    @BeforeClass
    public static void setUp() {
        container = EJBContainer.createEJBContainer();
        em = Persistence.createEntityManagerFactory("RMTPU").createEntityManager();
    }

    public EmployeeServiceTest() {
        try {   
            Context ctx = container.getContext();
            employeeService = (EmployeeService) ctx.lookup("java:global/classes/employeeService");
        } catch (NamingException e) {
            e.printStackTrace();
            Assert.fail("Failed. Exception thrown.");
        }
    }
    
    @Test
    public void testAddEmployeeWithoutManager(){
        final Employee employeeToAdd = new Employee();
        employeeToAdd.setFirstName("employeeTest");
        employeeToAdd.setLastName("employeeTest");
        employeeToAdd.setUsername("employeeTest@supinfo");
        employeeToAdd.setPassword("employeeTest");
        final Employee employeeAdded = employeeService.addEmployee(employeeToAdd);
        Assert.assertNotNull(employeeAdded);
        Assert.assertNotNull(employeeAdded.getId());
        final EntityTransaction et = em.getTransaction();
        et.begin();
        final Employee employeeToClean = em.find(Employee.class, employeeAdded.getId());
        em.remove(employeeToClean);
        et.commit();
    }
    
    @Test
    public void testAddEmployeeWithManager(){
        EntityTransaction et = em.getTransaction();
        et.begin();  
        
        final Manager managerToAssociate = new Manager();
        managerToAssociate.setFirstName("managerTest");
        managerToAssociate.setLastName("managerTest");
        managerToAssociate.setUsername("managerTest");
        em.persist(managerToAssociate);
        et.commit();
        
        final Employee employeeToAdd = new Employee();
        employeeToAdd.setFirstName("employeeTest");
        employeeToAdd.setLastName("employeeTest");
        employeeToAdd.setUsername("employeeTest@supinfo");
        employeeToAdd.setPassword("employeeTest");
        employeeToAdd.setManager(managerToAssociate);
        
        final Employee employeeAdded = employeeService.addEmployee(employeeToAdd);
        
        Assert.assertNotNull(employeeAdded);
        Assert.assertNotNull(employeeAdded.getId());
        
        et = em.getTransaction();
        et.begin();  
        
        final Employee employeeToClean = em.find(Employee.class, employeeAdded.getId());
        final Manager managerToClean = em.find(Manager.class, employeeToClean.getManager().getId());
        
        Assert.assertNotNull(managerToClean);
        Assert.assertNotNull(managerToClean.getId());
        Assert.assertNotNull(managerToClean.getEmployees());
        Assert.assertEquals(managerToClean.getEmployees().size(), 1);
        
        em.remove(employeeToClean);
        em.remove(managerToClean);
        
        et.rollback();
    }
    
    @AfterClass
    public static void tearDown() {
        em.close();
        container.close();
    }
}