
package by.vlados.carrentalsystem.logic;

import by.vlados.carrentalsystem.util.PasswordHashing;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vlados
 */
public class PasswordHashingTest {
    
    public PasswordHashingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getHashValue method, of class PasswordHashing.
     */
    @Test
    public void testGetHashValue() {
        System.out.println("getHashValue");
        String password = "1234";
        String expResult = "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4";
        String result = PasswordHashing.getHashValue(password);
        assertEquals(expResult, result);
    }
    
}
