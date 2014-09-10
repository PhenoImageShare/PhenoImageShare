/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.hw.macs.bisel.phis.iqs;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kcm
 */
public class GetHostTest {
    
    public GetHostTest() {
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
     * Test of getURI method, of class GetHost.
     */
    @Test
    public void testGetURI() {
        System.out.println("getURI");
        
        String result = GetHost.getURI();
        if(result.equalsIgnoreCase("http://lxbisel.macs.hw.ac.uk:8080/IQS/")) {
            assertEquals(result, "http://lxbisel.macs.hw.ac.uk:8080/IQS/");
        } else if(result.equalsIgnoreCase("http://beta.phenoimageshare.org/data/iqs/")) {
            assertEquals(result, "http://beta.phenoimageshare.org/data/iqs/");
        } else {
            fail("You forgot the mvn -P switch!");
        }                
    }

    
}
