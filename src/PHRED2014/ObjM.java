
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
    private AnalogChannel forkLimit;
    private AnalogChannel armLimit;
    
    private boolean exit;
    
    //Constructor(s)
    public ObjM(OI oi){
        COVOP = oi;
        
        ForkMotor = new Victor(FORK_MOTOR);
        BeltMotor = new Relay(BELT_SPIKE); //IT IS NOT PWMIII FIND OUT WHAT IT IS ASAP
        ArmDeploy = new Relay(ARM_SPIKE);//it controls the one-time deployment solenoid of the Belt. Dunno the port
        ForkDeploy = new Relay(FORK_SPIKE);//it controls the one-time deployment solenoid of the Forks. Dunno the port
        topLimit = new DigitalInput(TOP_LIMIT); //top limit
        botLimit = new DigitalInput(BOT_LIMIT); //bottom limit
        forkLimit = new AnalogChannel(FORK_LIMIT);
        armLimit = new AnalogChannel(ARM_LIMIT);
        encoder = new Encoder(CODERI,CODERII);
        encoder.reset();
        encoder.start();
        
        exit = false;
    }
    
    //Methods
    public void VerticalFork(int axis){ // Forklift up and down
        moveForks(COVOP.getXBoxAxisValue(axis), NO_PRESET);
    }
        
    public void TankBelt(int axis){ // Belt movement (It looks like a tank)
             
        if(COVOP.getXBoxAxisValue(axis) > DeadZone){
            BeltMotor.set(Relay.Value.kForward);
        }
        if(COVOP.getXBoxAxisValue(axis) < -DeadZone){
            BeltMotor.set(Relay.Value.kReverse);
        }
        if(COVOP.getXBoxAxisValue(axis) < DeadZone && COVOP.getXBoxAxisValue(axis) > -DeadZone){
            BeltMotor.set(Relay.Value.kOff);
        }
    }

    public int GetEncoder(){
        int encodercount = encoder.get();
        return encodercount;
    }

    //Prepare the robot for competition
    public boolean prepTheRobot(){
        deployArm();
        deployForks();
        moveForks();
        Timer.delay(1.0);
        return true;
    }

    public void deployArm(){pl("Arm Status", "Deploying the arm");
        ArmDeploy.set(Relay.Value.kForward);
    }
    
    public void deployForks(){pl("Fork Status: ","Deploying the forks");
        ForkDeploy.set(Relay.Value.kForward);
    }
    
    public void moveForks(double speed, int preset){
        String bob = "EXCEPTION";
        if(!botLimit.get() && speed > 0){ //Bot Limit switch tripped and pushing down on JS
            speed = 0;
            encoder.reset();
            bob = "Bottom Limit Tripped";
        }else if(!topLimit.get() && speed < 0){ //Top Limit switch tripped and pushing up on JS
            speed = 0;
            bob = "Top Limit Tripped";
        }else if(preset == NO_PRESET){ //This stuff should make it move to the preset. 
            speed = -speed;
            bob = "Moving the Forks";
        }else if(checkPreset(preset) < preset){
            speed *= 1;
            bob = "Moving the Forks Up";
        }else if(checkPreset(preset) > preset){
            speed *= -1;
            bob = "Moving the Forks Down";
        }else if(checkPreset(preset) == preset){ //stop plz
            speed = 0;
            bob = "Not Moving the forks";
        }else {
            bob = "Not Moving the Forks";
        }
        
        if(CarrageSafety(Select, Start) == true){
            speed = 0;
        }
        
        ForkMotor.set(speed);
        pl("Fork Status: ", bob);
    }
    
    private int checkPreset(int p){
        int loc = encoder.get();
        
        //Create a deadzone around the preset of +/- 1/16"
        if((loc > p - 5) && (loc < p + 5))
            loc = p;
        return loc;
    }
    
    public void Move_to_the_preset_values_that_we_determined_at_a_previous_time_(int button, int preset, int speed){
        if(COVOP.getXBoxButton(button)){
            moveForks(speed, preset);
        }
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
    
//*** Methods used for autonomous.
    //Override of TankBelt. Used for autonomous
    public void TankBelt(){
        BeltMotor.set(Relay.Value.kForward);
    }
    
    //Override of moveForks. Used for autonomous.
    public boolean moveForks(){ 
        double speed = 0.0;
        String bob = "EXCEPTION";
        
        if(checkPreset(CF_SCORE) == CF_SCORE){
            bob = "At the preset";
            speed = 0.0;
            return true;
        }else if(checkPreset(CF_SCORE) < CF_SCORE){
            bob = "Moving the Forks Up";
            speed = 1.0;
        }else if(checkPreset(CF_SCORE) > CF_SCORE){
            bob = "Moving the Forks Down";
            speed = -1.0;
        }
        
        if(CarrageSafety(Select, Start) == true){
            speed = 0;
        }

        ForkMotor.set(speed);
        pl("Fork Status: ", bob);
        return false;
    }
    
     public boolean CarrageSafety(int button1, int button2){
        boolean bool = false; // false = MOVE. true = NO MOVE.
        
        if(exit){
            SmartDashboard.putBoolean("Carrage Safety", exit);
            return false;
        }
        
        if(COVOP.getXBoxButton(button1) && COVOP.getXBoxButton(button2)){
            bool = false;
            exit = true;
        }
        else if(forkLimit.getVoltage() > 1.000 || armLimit.getVoltage() > 1.000) {
            bool = true;
        }
        
        SmartDashboard.putBoolean("Carrage Safety", exit);
        SmartDashboard.putBoolean("Carrage Limit Switches", bool);
        SmartDashboard.putNumber("Fork Limit Voltages", forkLimit.getVoltage());
        SmartDashboard.putNumber("Arm Limit Voltages", armLimit.getVoltage());
        return bool;
    }

    //I'm tired of typing System.out.println You could just use smartDashboard :|
    public void pl(String s){System.out.println(s); SmartDashboard.putString(s, s);}
    public void pl(String s1, String s2){System.out.println(s1 + s2); SmartDashboard.putString(s1, s2);}
    public void pl(String s, int i){System.out.println(s + i); SmartDashboard.putNumber(s, i);}
    public void pl(String s, double d){System.out.println(s + d); SmartDashboard.putNumber(s, d);}


}