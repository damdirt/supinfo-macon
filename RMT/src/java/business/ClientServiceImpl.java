/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Client;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author patrice
 */
@Singleton(name = "clientService")
@TransactionAttribute
public class ClientServiceImpl implements ClientService{
    @PersistenceContext(unitName = "RMTPU")
    private EntityManager em;
    
    @Override
    public Client addClient(Client client) {
        em.persist(client);
        return client;
    }
    
}
