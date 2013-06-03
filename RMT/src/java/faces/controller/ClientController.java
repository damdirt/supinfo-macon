/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package faces.controller;

import business.ClientService;
import domain.Client;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author patrice
 */
@ManagedBean
@ViewScoped
public class ClientController {
    
    private Client client = new Client();
    
    @EJB
    private ClientService clientService;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
    
    public String addClient(){
        String outcome = "manager_home";
        clientService.addClient(client);
        return outcome;
    }
    
}
