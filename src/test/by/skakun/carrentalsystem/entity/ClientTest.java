
package by.vlados.carrentalsystem.entity;

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
public class ClientTest {
    
    public ClientTest() {
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
     * Test of getCredit method, of class Client.
     */
    @Test
    public void testGetCredit() {
        System.out.println("getCredit");
        Client instance = new Client();
        int expResult = 0;
        int result = instance.getCredit();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCredit method, of class Client.
     */
    @Test
    public void testSetCredit() {
        System.out.println("setCredit");
        int credit = 0;
        Client instance = new Client();
        instance.setCredit(credit);
    }

    /**
     * Test of getActive method, of class Client.
     */
    @Test
    public void testGetActive() {
        System.out.println("getActive");
        Client instance = new Client();
        int expResult = 0;
        int result = instance.getActive();
        assertEquals(expResult, result);
    }

    /**
     * Test of setActive method, of class Client.
     */
    @Test
    public void testSetActive() {
        System.out.println("setActive");
        int active = 0;
        Client instance = new Client();
        instance.setActive(active);
    }

    /**
     * Test of getEmail method, of class Client.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Client instance = new Client();
        String expResult = null;
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Client.
     */
    @Test
    public void testSetEmail() throws Exception {
        System.out.println("setEmail");
        String email = "e-mail";
        Client instance = new Client();
        instance.setEmail(email);
    }

    /**
     * Test of getName method, of class Client.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Client instance = new Client();
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Client.
     */
    @Test
    public void testSetName() throws Exception {
        System.out.println("setName");
        String name = "name";
        Client instance = new Client();
        instance.setName(name);
    }

    /**
     * Test of getSurname method, of class Client.
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        Client instance = new Client();
        String expResult = null;
        String result = instance.getSurname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSurname method, of class Client.
     */
    @Test
    public void testSetSurname() throws Exception {
        System.out.println("setSurname");
        String surname = "surname";
        Client instance = new Client();
        instance.setSurname(surname);
    }

    /**
     * Test of getPassNum method, of class Client.
     */
    @Test
    public void testGetPassNum() {
        System.out.println("getPassNum");
        Client instance = new Client();
        String expResult = null;
        String result = instance.getPassNum();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassNum method, of class Client.
     */
    @Test
    public void testSetPassNum() throws Exception {
        System.out.println("setPassNum");
        String passNum = "passNum";
        Client instance = new Client();
        instance.setPassNum(passNum);
    }

    /**
     * Test of getType method, of class Client.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Client instance = new Client();
        ClientType expResult = null;
        ClientType result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Client.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String s = "";
        Client instance = new Client();
        instance.setType(s);
    }

    /**
     * Test of getLogin method, of class Client.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        Client instance = new Client();
        String expResult = null;
        String result = instance.getLogin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLogin method, of class Client.
     */
    @Test
    public void testSetLogin() throws Exception {
        System.out.println("setLogin");
        String login = "login";
        Client instance = new Client();
        instance.setLogin(login);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPassword method, of class Client.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Client instance = new Client();
        String expResult = null;
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class Client.
     */
    @Test
    public void testSetPassword() throws Exception {
        System.out.println("setPassword");
        String password = "passNum";
        Client instance = new Client();
        instance.setPassword(password);
    }

    /**
     * Test of getId method, of class Client.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Client instance = new Client();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Client.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Client instance = new Client();
        instance.setId(id);
    }

    

    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Client instance = new Client();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    
    
}
