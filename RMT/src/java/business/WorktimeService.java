/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Worktime;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author patrice
 */
@Remote
public interface WorktimeService {
    
    Worktime addWorktime(Worktime worktime);
    
    List<Worktime> findAll();
    
    boolean delete(Long id);
    
}
