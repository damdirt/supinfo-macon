/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package faces.controller;

import business.EmployeeService;
import domain.Employee;
import domain.Manager;
import faces.FacesUtils;
import faces.model.UserSessionBean;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author patrice
 */
@ManagedBean
@ViewScoped
public class EmployeeController implements Serializable{
    
    private Employee employee = new Employee();
    
    @EJB
    private EmployeeService employeeService;
    
    @ManagedProperty("#{userSession}")
    private UserSessionBean userSession;
        
    public String addEmployee() {
        String outcome = null;
        
        if(isNotEmpty(employee.getFirstName())
            && isNotEmpty(employee.getLastName())
            && isNotEmpty(employee.getEmail())
            && isNotEmpty(employee.getPassword())){
            
            if(employee.getUsername() == null){
                employee.setUsername(employee.getEmail());
            }
            
            employee.setManager((Manager)userSession.getUser());
            
            final Employee employeeAdded = employeeService.addEmployee(employee);
            if(employeeAdded != null && employeeAdded.getId() != null){
                userSession.setUser(employeeAdded.getManager());
                return "manager_home";
            }else{
                FacesUtils.addFacesErrorMessage("Something went wrong, please re-submit", "An error with your form occured, please re-submit it.");
            }
            
        }else{
            FacesUtils.addFacesErrorMessage("Missing mandatory values", "Please, fill out every mandatory fields.");
        }
        
        return outcome;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    

    public void setUserSession(UserSessionBean userSession) {
        this.userSession = userSession;
    }
    
    private boolean isNotEmpty(String s){
        return s != null && s.length() > 0;
    }
    
}
