/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package constraint;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author patrice
 */
public class ValidPasswordValidatorTest {
    
    private ValidPasswordValidator validator = new ValidPasswordValidator();
    
        
    @Test
    public void testStringWithACharOfEachConstraint() {
        final boolean result = validator.isValid("mP4", null);
        Assert.assertTrue(result);
    }
    
    @Test
    public void testWithoutNumber() {
        final boolean result = validator.isValid("mP", null);
        Assert.assertFalse(result);
    }
    
    @Test
    public void testWithoutLowerCaseChar() {
        final boolean result = validator.isValid("P4", null);
        Assert.assertFalse(result);
    }
    
    @Test
    public void testWithoutUpperCaseChar() {
        final boolean result = validator.isValid("m4", null);
        Assert.assertFalse(result);
    }
    
    @Test
    public void testRightStringWithMulpleRightChar() {
        final boolean result = validator.isValid("mP4_2PPxxsSf23", null);
        Assert.assertTrue(result);
    }
}