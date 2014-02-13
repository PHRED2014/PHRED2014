
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
        double Xval = COVOP.getXBoxAxisValue(LStickY); // Xval is a method specfic variable that is where we put the axis values
        ForkMotor.set(Xval);
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


    
    public void deployArm(){pl("Deploying the arm");}
    public void deployForks(){pl("Deploying the forks");}

    //I'm tired of typing System.out.println
    public void pl(String s){System.out.println(s);}
    public void pl(String s, int i){System.out.println(s + i);}
    public void pl(String s, double d){System.out.println(s + d);}
}
