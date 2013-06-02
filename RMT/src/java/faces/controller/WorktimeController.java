/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package faces.controller;

import business.ClientService;
import business.WorktimeService;
import domain.Client;
import domain.Worktime;
import faces.FacesUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author patrice
 */
@ManagedBean
@ViewScoped
public class WorktimeController implements Serializable{
    
    private Worktime worktime = new Worktime();
    
    private List<Client> clients = new ArrayList<Client>();
    
    @EJB
    private WorktimeService worktimeService;
    
    @EJB
    private ClientService clientService;
   
    @PostConstruct
    public void init(){
        final List<Client> listeClient = clientService.getAll();
        if(listeClient != null && !listeClient.isEmpty()){
            clients.addAll(listeClient);
        }
    }
    
    public List<Worktime> getWorktimes(){
        List<Worktime> worktimes = new ArrayList<Worktime>();
        
        worktimes.addAll(worktimeService.findAll());
        
        return worktimes;
    }
    
    public String addWorktime(){
        String outcome = null;
        
        if(worktime.getBeginDate() != null
           && worktime.getClient() != null){
            final Worktime result = worktimeService.addWorktime(worktime);
            if(result != null && result.getId() != null){
                outcome = "employee_home";
            }
        }else{
            FacesUtils.addFacesErrorMessage("Validation error", "Please fill out at least beginDate and client fields.");
        }
        
        
        return outcome;
    }
    
    public void deleteWorktime(final ActionEvent e){
        final Long id  = (Long) e.getComponent().getAttributes().get("worktimeToDeleteId");
        worktimeService.delete(id);
    }

    public void setWorktime(Worktime worktime) {
        this.worktime = worktime;
    }

    public Worktime getWorktime() {
        return worktime;
    }

    public void setWorktimeService(WorktimeService worktimeService) {
        this.worktimeService = worktimeService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public List<Client> getClients() {
        return clients;
    }
    
}
