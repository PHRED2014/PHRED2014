
package PHRED2014;
import edu.wpi.first.wpilibj.*;

public class ObjM implements RobotMap{
    
    //Instance variables: Motors, Sensors, etc
    //TODO: private Relay omMotors1; //Could be a Victor, or a spike. Same with the other victors below.
    //private Relay omMotors2;
    //private Relay omMotors3;
    //private Relay omMotors4;
    private OI COVOP;
    
    private Victor ForkMotor;
    private Relay BeltMotor;
    private Relay ForkDeploy;
    private Relay ArmDeploy;
    private Encoder encoder;
    private DigitalInput topLimit;
    private DigitalInput botLimit;
    
    
    //Constructor(s)
    public ObjM(OI oi){
        //TODO: omMotors1 = new Relay(SpikeI);
        //omMotors2 = new Relay(SpikeII);
        //omMotors3 = new Relay(SpikeIII);
        //omMotors4 = new Relay(SpikeIV);
        COVOP = oi;
        
        ForkMotor = new Victor(FORK_PORT);
        BeltMotor = new Relay(PWMIII); //IT IS NOT PWMIII FIND OUT WHAT IT IS ASAP
        ArmDeploy = new Relay(SpikeI);//it controls the one-time deployment solenoid of the Belt. Dunno the port
        ForkDeploy = new Relay(SpikeII);//it controls the one-time deployment solenoid of the Forks. Dunno the port
        topLimit = new DigitalInput(TOP_LIMIT); //top limit
        botLimit = new DigitalInput(BOT_LIMIT); //bottom limit
 
        
        encoder = new Encoder(CoderI,CoderII);
        encoder.reset();
        encoder.start();
    }
    
    //Methods
    public void VerticalFork(){ // Forklift up and down
        moveForks(COVOP.getXBoxAxisValue(LStickY), NO_PRESET);
    }
        
    public void TankBelt(){ // Belt movement (It looks like a tank)
        if(COVOP.getXBoxAxisValue(RStickY) > 0.05){
            BeltMotor.setDirection(Relay.Direction.kForward);    
        }
        if(COVOP.getXBoxAxisValue(RStickY) < -0.05){
            BeltMotor.setDirection(Relay.Direction.kReverse);
        }
        if(COVOP.getXBoxAxisValue(RStickY) < 0.05 && COVOP.getXBoxAxisValue(RStickY) > -0.05){
            BeltMotor.set(Relay.Value.kOff);
        }
    }

   /* public boolean getXBoxButton(int button){
        return COVOP.XStick.getButton(button);
    }       */
/*TODO: Uncomment when encoders are added to the robot. Needs work: Probably belongs in VerticleFork().
    public int Carriage(){
        int encodercount = encoder.get();
        return encodercount;
    }
 */

    //Prepare the robot for competition
    public int GetEncoder(){
        int encodercount = encoder.get();
        return encodercount;
    }
    
    public boolean prepTheRobot(){
        deployArm(); Timer.delay(0.5);
        deployForks(); Timer.delay(0.5);
        moveForks(-1.0, 100); Timer.delay(0.5);//preset ???
        return true;
    }

    public void deployArm(){pl("Deploying the arm");
    ArmDeploy.set(Relay.Value.kOn);
    Timer.delay(.05);
    ArmDeploy.set(Relay.Value.kOff);
    }
    public void deployForks(){pl("Deploying the forks");
    ForkDeploy.set(Relay.Value.kOn);
    Timer.delay(.05);
    ForkDeploy.set(Relay.Value.kOff);
    }
    public void moveForks(double speed, int preset){
       
        if(botLimit.get() && speed < 0){ // ARBITRARY NUMBER
            ForkMotor.set(0);
        }
        
        if(topLimit.get() && speed > 0){
            ForkMotor.set(0);
        }     
        
    //TODO:    
    //Check to see if we are moving under a preset command and if so if at that preset Yes: speed = 0 and clear the preset condition
    //If not at limits and not at preset then run the fork motor at speed "speed"
        
        ForkMotor.set(speed);
        pl("Moving the forks");
    }
    
    //I'm tired of typing System.out.println
    public void pl(String s){System.out.println(s);}
    public void pl(String s, int i){System.out.println(s + i);}
    public void pl(String s, double d){System.out.println(s + d);}
}
