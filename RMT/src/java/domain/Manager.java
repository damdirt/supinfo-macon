/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "M")
public class Manager extends User {
    
}
