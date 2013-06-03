/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author patrice
 */
@NotBlank
@Size(min=6)
@Constraint(validatedBy = ValidPasswordValidator.class)
@Target(value = {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@Documented
public @interface ValidPassword {
    String message() default "{constraint.validPasswordValidator}";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
