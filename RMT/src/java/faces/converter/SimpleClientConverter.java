/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package faces.converter;

import domain.Client;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author patrice
 */
@FacesConverter("com.supinfo.macon.faces.SimpleClientConverter")
public class SimpleClientConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        final Client client = new Client();
        client.setId(Long.valueOf(value));
        return client;
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
