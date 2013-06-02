/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Employee;
import domain.User;
import javax.ejb.Remote;

/**
 *
 * @author patrice
 */
@Remote
public interface EmployeeService {
    
    Employee addEmployee(Employee employee);
}
