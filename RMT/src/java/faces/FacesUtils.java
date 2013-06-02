/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package faces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author patrice
 */
public class FacesUtils {
    
    public static void addFacesErrorMessage(final String msgSum, final String msgDetail) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgSum, msgDetail);
        context.addMessage(null, msg);
    }
    
}
