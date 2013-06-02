/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package faces.converter;

import business.ClientService;
import domain.Client;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBContext;
import javax.ejb.embeddable.EJBContainer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author patrice
 */
@FacesConverter("com.supinfo.macon.faces.ContextClientConverter")
public class ContextClientConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            final Context ctx = new InitialContext();
            final ClientService clientService = (ClientService) ctx.lookup("java:comp/env/business/clientService");
            return clientService.getById(Long.valueOf(value));
        } catch (NamingException ex) {
            Logger.getLogger(ContextClientConverter.class.getName()).log(Level.SEVERE, null, ex);
            throw new ConverterException("Could not convert client value");
        }
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
    
}
