
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
        ForkMotor = new Victor(Forkport);
        ArmMotor = new Victor(Armport);
        BeltMotor = new Victor(Beltport);
    }
    
    //Methods
    public void moveForks(){ // Forklift up and down
        ForkMotor.set(COVOP.getXBoxAxisValue(LStickY));
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
        ArmMotor.set(COVOP.getXBoxAxisValue(RStickY));
    }
}
