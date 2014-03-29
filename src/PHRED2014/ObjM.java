
package PHRED2014;
import edu.wpi.first.wpilibj.*;

public class ObjM implements RobotMap{
    
    //Instance variables: Motors, Sensors, etc
    private OI COVOP;
    
    private Victor ForkMotor;
    private Victor BeltMotor;
    private Victor ArmMotor;
    
    //Constructor(s)
    public ObjM(OI oi){
        COVOP = oi;
        //ForkMotor = new Victor(FORK_PORT);
        //ArmMotor = new Victor(ARM_PORT);
        //BeltMotor = new Victor(BELT_PORT);
    }
    
    //Methods
    public void moveForks(){ // Forklift up and down
        double speed = COVOP.getXBoxAxisValue(LStickY);
        if(Math.abs(speed) <= DeadZone) speed = 0.0;
        ForkMotor.set(speed);
    }
        
    public void moveBelt(){ // Belt movement (It looks like a tank)
        double Xval = COVOP.getXBoxTrigger();
        
        if(Xval > 0){
            BeltMotor.set(BELT_SPEED);
        }
        else if(Xval < 0){
            BeltMotor.set(-BELT_SPEED);
        }else{
            BeltMotor.set(0.0);
        }
    }

    public void moveArm(){
        double speed = COVOP.getXBoxAxisValue(RStickY);
        if(Math.abs(speed) <= DeadZone) speed = 0.0;
        ArmMotor.set(speed);
    }
}
