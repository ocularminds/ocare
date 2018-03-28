/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatitentRouteTest {

    @LocalServerPort
    private int port;
    
    @Test
    public void testOk(){
        createURLWithPort("/api/patients");
    }

    private String createURLWithPort(String uri) {
        return String.format("http://localhost:%s%s", port, uri);
    }
    
    @Test
    public void testRegisterPatient(){
        
    }
    
    @Test
    public void testRegisterPatientWithInvalidParameters(){
        
    }
    
     @Test
    public void testFindPatient(){
        
    }
    
     @Test
    public void testDeletePatient(){
        
    }
    
     @Test
    public void testAddMedicalRecordForAGivenPatient(){
    }
    
     @Test
    public void testAddMedicalRecordToInvalidPatiendId(){
    }
    
     @Test
    public void testFetchMedicalHistoryForAGivenPatient(){
        
    }
    
     @Test
    public void addSomeTreatmentsToMedicalRecord(){
        
    }
    
     @Test
    public void addSomeTreatmentsToMedicalRecordThatDoesNotExist(){
        
    }
    
     @Test
    public void addVitalsToMedicalRecord(){
        
    }
    
    @Test
    public void addVitalsToMedicalRecordThatDoesNotExist(){
        
    }
    
}
