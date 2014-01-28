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
    private OI COVOP;
    
    //Constructor(s)
    public ObjM(OI oi){
        omMotors1 = new Relay(SpikeI);
        omMotors2 = new Relay(SpikeII);
        omMotors3 = new Relay(SpikeIII);
        omMotors4 = new Relay(SpikeIV);
        COVOP = oi;
    }
    
    //Methods
    
}
