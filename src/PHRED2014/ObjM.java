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
public class ObjM implements RobotMap{
    
    //Instance variables: Motors, Sensors, etc
    private Relay omMotors1; //Could be a Victor, or a spike. Same with the other victors below.
    private Relay omMotors2;
    private Relay omMotors3;
    private Relay omMotors4;
    private Ultrasonic soundSensor;
    private Gyro GG; //This is the Guidance Gyro
    
    
    //Constructor(s)
    public ObjM(){
        omMotors1 = new Relay(SpikeI);
        omMotors2 = new Relay(SpikeII);
        omMotors3 = new Relay(SpikeIII);
        omMotors4 = new Relay(SpikeIV);
        soundSensor = new Ultrasonic(3,4);
        GG = new Gyro(1);
        //etc
    }
    
    public double getGyroAngle() {
        return GG.getAngle();
    }
    
    //Methods
    //test whether GIT works
    
}
