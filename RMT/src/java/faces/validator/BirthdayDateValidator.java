/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package faces.validator;

import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author patrice
 */
@FacesValidator("com.supinfo.macon.faces.BirthdayDateValidator")
public class BirthdayDateValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        final Date date = (Date) value;
        
        if(date != null){
            final Calendar today = Calendar.getInstance();
            today.clear(Calendar.MILLISECOND);
            today.clear(Calendar.SECOND);
            today.clear(Calendar.MINUTE);
            today.clear(Calendar.HOUR);
            
            if(today.getTime().compareTo(date) <= 0){
                final ResourceBundle rb = context.getApplication().getResourceBundle(context, "validationBundle");
                final String summary = rb.getString("birthdayDateValidator.summary");
                final String details = rb.getString("birthdayDateValidator.details");
                FacesMessage msg = new FacesMessage(summary, details);
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }
        
    }
    
}
