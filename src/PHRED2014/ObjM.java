
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
    private DigitalInput LSwitchI;
    private DigitalInput LSwitchII;
    
    //Constructor(s)
    public ObjM(OI oi){
        //omMotors1 = new Relay(SpikeI);
        //omMotors2 = new Relay(SpikeII);
        //omMotors3 = new Relay(SpikeIII);
        //omMotors4 = new Relay(SpikeIV);
        COVOP = oi;
        
        ForkMotor = new Victor(PWMI);
        BeltMotor = new Victor(PWMIII);
        LSwitchI = new DigitalInput(7); //top limit
        LSwitchII = new DigitalInput(8); //bottom limit
 
/*Uncomment when encoders are added to Robot
        encoder = new Encoder(CoderI,CoderII);
        encoder.reset();
        encoder.start();
*/    }
    
    //Methods
    public void VerticalFork(){ // Forklift up and down
        double Xval = COVOP.getXBoxAxisValue(LStickY); // Xval is a method specfic variable that is where we put the axis values
        if (LSwitchI.get() && Xval < 0)
            Xval = 0.0;
        if (LSwitchII.get() && Xval > 0)
            Xval = 0.0;
        ForkMotor.set(Xval);
    }
        
    public void TankBelt(){ // Belt movement (It looks like a tank)
        double Xval = COVOP.getXBoxTrigger();
        
        if(Xval > 0){
            BeltMotor.set(1.0);
        }
        else if(Xval < 0){
            BeltMotor.set(-1.0);
        }else{
            BeltMotor.set(0.0);
        }
    }
    
    public void deployArm(){pl("Deploying the arm");}
    public void deployForks(){pl("Deploying the forks");}

/*Uncomment when encoders are added to the robot. Needs work: Probably belongs in VerticleFork().
    public int Carriage(){
        int encodercount = encoder.get();
        return encodercount;
    }
 */
    //I'm tired of typing System.out.println
    public void pl(String s){System.out.println(s);}
    public void pl(String s, int i){System.out.println(s + i);}
    
    boolean lSwitchLimitI = LSwitchI.get();
    boolean lSwitchLimitII = LSwitchII.get();
    
}