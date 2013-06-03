/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@DiscriminatorValue(value = "E")
public class Employee extends User {
    
    @ManyToOne
    private Manager manager;
    
    @Temporal(TemporalType.DATE)
    @NotNull @Past
    private Date birthday;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
        if(manager.getEmployees() == null){
            manager.setEmployees(new ArrayList<Employee>());
        }
        manager.getEmployees().add(this);
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    
}
