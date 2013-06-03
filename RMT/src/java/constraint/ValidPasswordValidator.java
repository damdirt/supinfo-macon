/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package constraint;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author patrice
 */
public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String>{

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        // NOPE - We does not need any initialization.
    }

    /**
     *  Contains at least:
     * <ul>
     *  <li>One number</li>
     *  <li>One letter in upper case</li>
     *  <li>One letter in lower case</li>
     * </ul>
     * 
     * @param value To valid
     * @param context Context of validation.
     * 
     * @return true if valid, false otherwise.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        /* 
         ======================================================================
                                Pattern explanation
         ======================================================================
         Source from:
            http://stackoverflow.com/questions/1559751/regex-to-make-sure-that-the-string-contains-at-least-one-lower-case-char-upper
         
         ***************************************************************************************
         ^            - the start of the string
         (?=.*[a-z])  - use positive look ahead to see if at least one lower case letter exists
         (?=.*[A-Z])  - use positive look ahead to see if at least one upper case letter exists
         (?=.*\d)     - use positive look ahead to see if at least one digit exists
         .+           - gobble up the entire string
         $            - the end of the string
         ***************************************************************************************
         */
        final String regex = "^((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)).+$";
        return Pattern.matches(regex, value);
    }
    
}
