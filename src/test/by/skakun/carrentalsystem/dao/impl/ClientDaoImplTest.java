
package by.vlados.carrentalsystem.dao.impl;

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
public class ClientDaoImplTest {
    
    public ClientDaoImplTest() {
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
     * Test of checkLogin method, of class ClientDaoImpl.
     */
    @Test
    public void testCheckLogin() throws Exception {
        System.out.println("checkLogin");
        String login = "";
        ClientDaoImpl instance = new ClientDaoImpl();
        boolean expResult = false;
        boolean result = instance.checkLogin(login);
        assertEquals(expResult, result);
        
       
    }

    

    /**
     * Test of deleteUser method, of class ClientDaoImpl.
     */
    @Test
    public void testDeleteUser() throws Exception {
        System.out.println("deleteUser");
        int id = 0;
        ClientDaoImpl instance = new ClientDaoImpl();
        boolean expResult = false;
        boolean result = instance.deleteUser(id);
        assertEquals(expResult, result);
       
    }
    
}
