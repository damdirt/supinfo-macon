/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Client;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author patrice
 */
@Singleton(name = "clientService", mappedName = "business/clientService")
@TransactionAttribute
public class ClientServiceImpl implements ClientService{
    @PersistenceContext(unitName = "RMTPU")
    private EntityManager em;
    
    @Override
    public Client addClient(Client client) {
        em.persist(client);
        return client;
    }

    @Override
    public List<Client> getAll() {
        return em.createNamedQuery("Client.findAll").getResultList();
    }

    @Override
    public Client getById(Long id) {
        return em.getReference(Client.class, id);
    }
    
    
}
