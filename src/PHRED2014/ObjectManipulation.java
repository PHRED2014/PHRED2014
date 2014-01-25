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
 */
public class ObjectManipulation implements RobotMap{
    
    //Instance variables: Motors, Sensors, etc
    private Victor omMotors1;
    private Victor omMotors2;
    private Victor omMotors3;
    private Ultrasonic soundSensor;
    
    //Constructor(s)
    public ObjectManipulation(){
        omMotors1 = new Victor(1);
        omMotors2 = new Victor(2);
        omMotors3 = new Victor(3);
        soundSensor = new Ultrasonic(3,4);
        //etc
    }
    
    //Methods
    
}
