
package PHRED2014;
import edu.wpi.first.wpilibj.*;

public class ObjM implements RobotMap{
    
    //Instance variables: Motors, Sensors, etc
    private OI COVOP;
    
    private Victor ForkMotor;
    private Victor BeltMotor;
    private Victor ArmMotor;
    private Encoder encoder;
    
    //Constructor(s)
    public ObjM(OI oi){
        COVOP = oi;
        
        ForkMotor = new Victor(Forkport);
        ArmMotor = new Victor(Armport);
        BeltMotor = new Victor(Beltport);

    }
    
    //Methods
    public void VerticalFork(){ // Forklift up and down
        moveForks(COVOP.getXBoxAxisValue(LStickY), NO_PRESET);
    }
        
    public void TankBelt(){ // Belt movement (It looks like a tank)
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


    //Prepare the robot for competition
    public boolean prepTheRobot(){
        deployArm(); Timer.delay(0.5);
        deployForks(); Timer.delay(0.5);
        moveForks(-1.0, 100); Timer.delay(0.5);//preset ???
        return true;
    }

    public void deployArm(){pl("Deploying the arm");}
    public void deployForks(){pl("Deploying the forks");}
    public void moveForks(double speed, int preset){
        pl("Moving the forks");
        
    //TODO:    if((topLimit.get() && speed < 0) || (botLimit.get() && speed > 0)){speed = 0.0;}
    
    //Check to see if we are at either the upper or lower limit (limit switches). Yes: set speed = 0 as appropriate
    //Check to see if we are moving under a preset command and if so if at that preset Yes: speed = 0 and clear the preset condition
    //If not at limits and not at preset then run the fork motor at speed "speed"
        
        ForkMotor.set(speed);
    }
    
    //I'm tired of typing System.out.println
    public void pl(String s){System.out.println(s);}
    public void pl(String s, int i){System.out.println(s + i);}
    public void pl(String s, double d){System.out.println(s + d);}
}
