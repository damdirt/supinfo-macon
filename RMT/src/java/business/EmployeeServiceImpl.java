/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Employee;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author patrice
 */
@Singleton(name = "employeeService")
public class EmployeeServiceImpl implements EmployeeService{
    
    @PersistenceContext(unitName = "RMTPU")
    private EntityManager em;

    @Override
    @TransactionAttribute
    public Employee addEmployee(Employee employee) {
//       Manager manager = null;
//        
//        
//        if(employee.getManager() != null && employee.getManager().getId() != null){
//            manager = em.find(Manager.class, employee.getManager().getId());
//            employee.setManager(manager);
//        }
       
        em.persist(employee);
        
        
//        if(manager != null){
//            if(manager.getEmployees() == null){
//                manager.setEmployees(new ArrayList<Employee>());
//            }   
//        
//            manager.getEmployees().add(employee);
//            em.flush();
//        }
        
        
        return employee;
    }
    
    
    
}
