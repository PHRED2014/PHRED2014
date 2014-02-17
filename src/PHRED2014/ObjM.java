
package PHRED2014;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ObjM implements RobotMap{
    
    //Instance variables: Motors, Sensors, etc
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
        COVOP = oi;
        
        ForkMotor = new Victor(FORK_MOTOR);
        BeltMotor = new Relay(BELT_SPIKE); //IT IS NOT PWMIII FIND OUT WHAT IT IS ASAP
        ArmDeploy = new Relay(ARM_SPIKE);//it controls the one-time deployment solenoid of the Belt. Dunno the port
        ForkDeploy = new Relay(FORK_SPIKE);//it controls the one-time deployment solenoid of the Forks. Dunno the port
        topLimit = new DigitalInput(TOP_LIMIT); //top limit
        botLimit = new DigitalInput(BOT_LIMIT); //bottom limit
 
        
        encoder = new Encoder(CODERI,CODERII);
        encoder.reset();
        encoder.start();
    }
    
    //Methods
    public void VerticalFork(){ // Forklift up and down
        moveForks(COVOP.getXBoxAxisValue(LStickY), NO_PRESET);
    }
        
    public void TankBelt(int axis){ // Belt movement (It looks like a tank)
        if(COVOP.getXBoxAxisValue(axis) > DeadZone){
            BeltMotor.setDirection(Relay.Direction.kForward);    
        }
        if(COVOP.getXBoxAxisValue(axis) < -DeadZone){
            BeltMotor.setDirection(Relay.Direction.kReverse);
        }
        if(COVOP.getXBoxAxisValue(axis) < DeadZone && COVOP.getXBoxAxisValue(axis) > -DeadZone){
            BeltMotor.set(Relay.Value.kOff);
        }
    }

   /* public boolean getXBoxButton(int button){
        return COVOP.XStick.getButton(button);
    }       */

    //Prepare the robot for competition
    public int GetEncoder(){
        int encodercount = encoder.get();
        return encodercount;
    }
    
    public boolean prepTheRobot(){
        deployArm(); Timer.delay(0.5);
        deployForks(); Timer.delay(0.5);
//TODO:        moveForks(-1.0, 100); Timer.delay(0.5);//preset ???
        return true;
    }

    public void deployArm(){SmartDashboard.putString("Arm Status", "Deploying the arm");
        ArmDeploy.set(Relay.Value.kOn);
        Timer.delay(.05);
        ArmDeploy.set(Relay.Value.kOff);
    }
    public void deployForks(){SmartDashboard.putString("Fork Status: ","Deploying the forks");
        ForkDeploy.set(Relay.Value.kOn);
        Timer.delay(.05);
        ForkDeploy.set(Relay.Value.kOff);
    }
    public void moveForks(double speed, int preset){
       
        if(botLimit.get() && speed < 0){ // ARBITRARY NUMBER
            speed = 0;
        }
        
        if(topLimit.get() && speed > 0){
            speed = 0;
        }     
        
        if(preset == NO_PRESET){ //This stuff should make it move to the preset. 
            speed = speed;
        } else if(encoder.get() < preset){
            speed *= 1;
        } else if(encoder.get() > preset){
            speed *= -1;
        } else if(encoder.get() == preset){ //stop plz
            preset = NO_PRESET;
            speed = 0;
        }
        
        if(botLimit.get() && speed < 0){ // Redundant system just in case. We is sorry if the makes the robot too heavy.
            speed = 0;
        }
        
        if(topLimit.get() && speed > 0){
            speed = 0;
        }
        
        ForkMotor.set(speed);
        pl("Moving the forks");
        SmartDashboard.putString("Fork Status: ", "Moving the Forks");
    }
    
    public void XFork(int up, int down){ //This is for the test function.
        if(COVOP.getXBoxButton(up) && !COVOP.getXBoxButton(down)){
            moveForks(1, NO_PRESET);
        }
        
        if(COVOP.getXBoxButton(down) && !COVOP.getXBoxButton(up)){
            moveForks(-1, NO_PRESET);
        }
        
        if((!COVOP.getXBoxButton(down) && !COVOP.getXBoxButton(up)) || (COVOP.getXBoxButton(down) && COVOP.getXBoxButton(up))){
            moveForks(0, NO_PRESET);
        }
    }
    
    public void Move_to_the_preset_values_that_we_determined_at_a_previous_time_(int button, int preset, int speed){
        if(COVOP.getXBoxButton(button)){
            moveForks(speed, preset);
        }
    }
    
    //I'm tired of typing System.out.println You could just use smartDashboard :|
    public void pl(String s){System.out.println(s); SmartDashboard.putString(s, s);}
    public void pl(String s, int i){System.out.println(s + i); SmartDashboard.putInt(s, i);}
    public void pl(String s, double d){System.out.println(s + d); SmartDashboard.putNumber(s, d);}
}
