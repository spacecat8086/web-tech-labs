
package by.vlados.carrentalsystem.entity;

import java.sql.Date;
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
public class OrderTest {
    
    public OrderTest() {
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
     * Test of getDate method, of class Order.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Order instance = new Order();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setDate method, of class Order.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date date = null;
        Order instance = new Order();
        instance.setDate(date);
    }

    /**
     * Test of getPeriod method, of class Order.
     */
    @Test
    public void testGetPeriod() {
        System.out.println("getPeriod");
        Order instance = new Order();
        int expResult = 0;
        int result = instance.getPeriod();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setPeriod method, of class Order.
     */
    @Test
    public void testSetPeriod() throws Exception {
        System.out.println("setPeriod");
        int period = 1;
        Order instance = new Order();
        instance.setPeriod(period);
        
    }

    /**
     * Test of getPassNum method, of class Order.
     */
    @Test
    public void testGetPassNum() {
        System.out.println("getPassNum");
        Order instance = new Order();
        String expResult = null;
        String result = instance.getPassNum();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setPassNum method, of class Order.
     */
    @Test
    public void testSetPassNum() throws Exception {
        System.out.println("setPassNum");
        String passNum = "passNum";
        Order instance = new Order();
        instance.setPassNum(passNum);
      
    }

    /**
     * Test of getClientSurname method, of class Order.
     */
    @Test
    public void testGetClientSurname() {
        System.out.println("getClientSurname");
        Order instance = new Order();
        String expResult = null;
        String result = instance.getClientSurname();
        assertEquals(expResult, result);
     
    }

    /**
     * Test of setClientSurname method, of class Order.
     */
    @Test
    public void testSetClientSurname() throws Exception {
        System.out.println("setClientSurname");
        String clientSurname = "surname";
        Order instance = new Order();
        instance.setClientSurname(clientSurname);
       
    }

    /**
     * Test of getCarName method, of class Order.
     */
    @Test
    public void testGetCarName() {
        System.out.println("getCarName");
        Order instance = new Order();
        String expResult = null;
        String result = instance.getCarName();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCarName method, of class Order.
     */
    @Test
    public void testSetCarName() throws Exception {
        System.out.println("setCarName");
        String carName = "carname";
        Order instance = new Order();
        instance.setCarName(carName);
        
    }

    /**
     * Test of getPrice method, of class Order.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Order instance = new Order();
        int expResult = 0;
        int result = instance.getPrice();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setPrice method, of class Order.
     */
    @Test
    public void testSetPrice() throws Exception {
        System.out.println("setPrice");
        int price = 1;
        Order instance = new Order();
        instance.setPrice(price);
      
    }

    /**
     * Test of getUserId method, of class Order.
     */
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        Order instance = new Order();
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of setUserId method, of class Order.
     */
    @Test
    public void testSetUserId() {
        System.out.println("setUserId");
        int userId = 1;
        Order instance = new Order();
        instance.setUserId(userId);
        
    }

    /**
     * Test of getCarId method, of class Order.
     */
    @Test
    public void testGetCarId() {
        System.out.println("getCarId");
        Order instance = new Order();
        int expResult = 0;
        int result = instance.getCarId();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setCarId method, of class Order.
     */
    @Test
    public void testSetCarId() {
        System.out.println("setCarId");
        int carId = 0;
        Order instance = new Order();
        instance.setCarId(carId);
        
    }

    /**
     * Test of getSumToPay method, of class Order.
     */
    @Test
    public void testGetSumToPay() {
        System.out.println("getSumToPay");
        Order instance = new Order();
        int expResult = 0;
        int result = instance.getSumToPay();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setSumToPay method, of class Order.
     */
    @Test
    public void testSetSumToPay() throws Exception {
        System.out.println("setSumToPay");
        int sumToPay = 1;
        Order instance = new Order();
        instance.setSumToPay(sumToPay);
        
    }

    /**
     * Test of isPaid method, of class Order.
     */
    @Test
    public void testIsPaid() {
        System.out.println("isPaid");
        Order instance = new Order();
        boolean expResult = false;
        boolean result = instance.isPaid();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setPaid method, of class Order.
     */
    @Test
    public void testSetPaid() {
        System.out.println("setPaid");
        boolean paid = false;
        Order instance = new Order();
        instance.setPaid(paid);
       
    }

    /**
     * Test of getDamaged method, of class Order.
     */
    @Test
    public void testGetDamaged() {
        System.out.println("getDamaged");
        Order instance = new Order();
        Damage expResult = null;
        Damage result = instance.getDamaged();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setDamaged method, of class Order.
     */
    @Test
    public void testSetDamaged() {
        System.out.println("setDamaged");
        Damage damaged = null;
        Order instance = new Order();
        instance.setDamaged(damaged);
       
    }

    /**
     * Test of getReturned method, of class Order.
     */
    @Test
    public void testGetReturned() {
        System.out.println("getReturned");
        Order instance = new Order();
        int expResult = 0;
        int result = instance.getReturned();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setReturned method, of class Order.
     */
    @Test
    public void testSetReturned() {
        System.out.println("setReturned");
        int returned = 0;
        Order instance = new Order();
        instance.setReturned(returned);
        
    }

    /**
     * Test of getConfirmed method, of class Order.
     */
    @Test
    public void testGetConfirmed() {
        System.out.println("getConfirmed");
        Order instance = new Order();
        int expResult = 0;
        int result = instance.getConfirmed();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setConfirmed method, of class Order.
     */
    @Test
    public void testSetConfirmed() {
        System.out.println("setConfirmed");
        int confirmed = 0;
        Order instance = new Order();
        instance.setConfirmed(confirmed);
        
    }

    /**
     * Test of getRefusalReason method, of class Order.
     */
    @Test
    public void testGetRefusalReason() {
        System.out.println("getRefusalReason");
        Order instance = new Order();
        String expResult = null;
        String result = instance.getRefusalReason();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setRefusalReason method, of class Order.
     */
    @Test
    public void testSetRefusalReason() {
        System.out.println("setRefusalReason");
        String refusalReason = "";
        Order instance = new Order();
        instance.setRefusalReason(refusalReason);
        
    }

   
    /**
     * Test of equals method, of class Order.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Order instance = new Order();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
    }

    
    
}
