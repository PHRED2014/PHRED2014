/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PHRED2014;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author PHRED
 * 
 * Added comment to test commit from netbeans
 */
public class Autonomous implements RobotMap{
    
    //Instance variables
    private Ultrasonic lfSonicSensor;

    //Contstructor(s)
    public Autonomous(){
        lfSonicSensor = new Ultrasonic(lfSonicSensorPort1,lfSonicSensorPort2);
    }
    //Methods
    
}
