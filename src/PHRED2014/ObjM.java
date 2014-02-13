
package PHRED2014;
import edu.wpi.first.wpilibj.*;

public class ObjM implements RobotMap{
    
    //Instance variables: Motors, Sensors, etc
    //private Relay omMotors1; //Could be a Victor, or a spike. Same with the other victors below.
    //private Relay omMotors2;
    //private Relay omMotors3;
    //private Relay omMotors4;
    private OI COVOP;
    
    private Victor ForkMotor;
    private Victor BeltMotor;
    private Encoder encoder;
    private DigitalInput topLimit;
    private DigitalInput botLimit;
    
    //Constructor(s)
    public ObjM(OI oi){
        //omMotors1 = new Relay(SpikeI);
        //omMotors2 = new Relay(SpikeII);
        //omMotors3 = new Relay(SpikeIII);
        //omMotors4 = new Relay(SpikeIV);
        COVOP = oi;
        
        ForkMotor = new Victor(FORK_PORT);
        BeltMotor = new Victor(PWMIII); //Is this a victor or a relay?
        topLimit = new DigitalInput(TOP_LIMIT); //top limit
        botLimit = new DigitalInput(BOT_LIMIT); //bottom limit
 
/*Uncomment when encoders are added to Robot
        encoder = new Encoder(CoderI,CoderII);
        encoder.reset();
        encoder.start();
*/    }
    
    //Methods
    public void VerticalFork(){ // Forklift up and down
        double Xval = COVOP.getXBoxAxisValue(LStickY); // Xval is a method specfic variable that is where we put the axis values
        
        if((topLimit.get() && Xval < 0) || (botLimit.get() && Xval > 0)){Xval = 0.0;}
        
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

/*TODO: Uncomment when encoders are added to the robot. Needs work: Probably belongs in VerticleFork().
    public int Carriage(){
        int encodercount = encoder.get();
        return encodercount;
    }
 */
    
    public void deployArm(){pl("Deploying the arm");}
    public void deployForks(){pl("Deploying the forks");}
    public void moveForks(double speed, int preset){pl("Raising the forks");}

    //I'm tired of typing System.out.println
    public void pl(String s){System.out.println(s);}
    public void pl(String s, int i){System.out.println(s + i);}
    public void pl(String s, double d){System.out.println(s + d);}
}
