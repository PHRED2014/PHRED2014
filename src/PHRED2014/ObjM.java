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
    //private Relay omMotors1; //Could be a Victor, or a spike. Same with the other victors below.
    //private Relay omMotors2;
    //private Relay omMotors3;
    //private Relay omMotors4;
    private OI COVOP;
    
    private Victor ForkMotor;
    private Victor ArmMotor;
    private Victor BeltMotor;
    
    private Encoder encoder;
    
    //Constructor(s)
    public ObjM(OI oi){
        //omMotors1 = new Relay(SpikeI);
        //omMotors2 = new Relay(SpikeII);
        //omMotors3 = new Relay(SpikeIII);
        //omMotors4 = new Relay(SpikeIV);
        COVOP = oi;
        
        ForkMotor = new Victor(PWMI);
        ArmMotor = new Victor(PWMII);
        BeltMotor = new Victor(PWMIII);
        
        encoder = new Encoder(CoderI,CoderII);
        
        encoder.start();
    }
    
    //Methods
    public void VerticalFork(){ // Forklift up and down
        double Xval = COVOP.getXBoxAxisValue(LStickY); // Xval is a method specfic variable that is where we put the axis values
        ForkMotor.set(Xval);
    }
    
    public void AerialArm(){ // Arm movement
        double Xval = COVOP.getXBoxAxisValue(RStickY); // Again, we reinitialize Xval because it's specific to this method only :|
        ArmMotor.set(Xval);    
    }
    
    public void TankBelt(){ // Belt movement (It looks like a tank)
        double Xval = COVOP.getXBoxTrigger();
        
        if(Xval > 0){
            BeltMotor.set(0.5);
        }
        else if(Xval < 0){
            BeltMotor.set(-0.5);
        }else{
            BeltMotor.set(0.0);
        }
    }
    
    public int Carriage(){
        int encodercount = encoder.get();
        return encodercount;
    }
}