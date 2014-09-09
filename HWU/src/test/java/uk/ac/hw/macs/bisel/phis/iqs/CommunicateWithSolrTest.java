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
import static org.junit.Assert.*;

/**
 *
 * @author kcm
 */
public class CommunicateWithSolrTest {
    
    public CommunicateWithSolrTest() {
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
     * Test of talk method, of class CommunicateWithSolr.
     */
    @org.junit.Test
    public void testTalk() {
        System.out.println("testing CommunicateWithSolr.talk()... /getAutosuggest");
        String queryURL = "http://beta.phenoimageshare.org/data/rest/getAutosuggest?term=ey";
        CommunicateWithSolr instance = new CommunicateWithSolr();
        String expResult = "{\"response\":{\"suggestions\":[\"<b>eyelids</b> fail to open\",\"<b>eye</b>\",\"narrow <b>eye</b> opening\",\"<b>eyes</b> fail to open\",\"abnormal <b>eyelid</b> morphology\",\"TS20 <b>eye</b>\",\"bulging <b>eyes</b>\",\"left <b>eye</b>\",\"TS21 <b>eye</b>\",\"abnormal <b>eye</b> development\"]}}";
        String result = instance.talk(queryURL);
        assertEquals(expResult, result);
        
        System.out.println("testing CommunicateWithSolr.talk()... /getAutosuggest with resultNo");
        queryURL = "http://beta.phenoimageshare.org/data/rest/getAutosuggest?term=ey&resultNo=1";        
        expResult = "{\"response\":{\"suggestions\":[\"narrow <b>eye</b> opening\"]}}";
        result = instance.talk(queryURL);
        assertEquals(expResult, result);        
    }
    
    
    
}
