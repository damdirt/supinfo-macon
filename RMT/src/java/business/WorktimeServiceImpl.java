/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Worktime;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author patrice
 */
@Singleton(name = "worktimeService")
@TransactionAttribute
public class WorktimeServiceImpl implements WorktimeService{
    
    @PersistenceContext(unitName = "RMTPU")
    private EntityManager em;
    
    @Override
    public Worktime addWorktime(Worktime worktime) {
        em.persist(worktime);
        return worktime;
    }

    @Override
    public List<Worktime> findAll() {
        return em.createNamedQuery("Worktime.findAll", Worktime.class).getResultList();
    }

    @Override
    public boolean delete(Long id) {
        try{
            final Worktime w = em.getReference(Worktime.class, id);
            em.remove(w);
            return true;
        }catch (EntityNotFoundException e){
            return false;
        }
        
    }
    
    
}
