/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package faces.converter;

import business.ClientService;
import domain.Client;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author patrice
 */
@ManagedBean
public class ManagedClientConverter implements Converter{
    
    @EJB
    private ClientService clientService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return clientService.getById(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = null;
        
        final Long id = ((Client)value).getId();
        if(id != null){
            result = String.valueOf(id);
        }
            
        return result;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
    
    
}
