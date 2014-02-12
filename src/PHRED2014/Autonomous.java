
package PHRED2014;
import edu.wpi.first.wpilibj.*;

public class Autonomous implements RobotMap{
    
    //Instance variables
    private final TrainDrive trainDrive;
    private final ObjM ObjMan;
    
    private Ultrasonic usFore = null;
    private Ultrasonic usAft = null;
//TODO:    private Ultrasonic usForward = null;

    private int rangeFore, rangeAft, rangeForward, rangeDiff, stopRange;
    private double portSpeed, starboardSpeed;

    //Contstructor(s)
    public Autonomous(TrainDrive td, ObjM om, int script){
//TODO: Add smartdashboard slider tied to these speed variables
        portSpeed = -0.5;
        starboardSpeed = -0.5;
        stopRange = round(14 * 25.4);//Fork length: 14" converted to mm
        trainDrive = td;
        ObjMan = om;
        
//TODO:        usForward = new Ultrasonic(FRONT_ULTRA_P, FRONT_ULTRA_E);
//        usForward.setAutomaticMode(false);
//        usForward.setEnabled(true);

        switch(script){
            case WALL_LEFT:{
                usFore = new Ultrasonic(LEFT_FRONT_ULTRA_P,LEFT_FRONT_ULTRA_E);
                usFore.setAutomaticMode(false);
                usFore.setEnabled(true);
 
                usAft = new Ultrasonic(LEFT_REAR_ULTRA_P, LEFT_REAR_ULTRA_E);
                usAft.setAutomaticMode(true);
                usAft.setEnabled(true);
                break;
            }
            case WALL_RIGHT:{
                usFore = new Ultrasonic(RIGHT_FRONT_ULTRA_P,RIGHT_FRONT_ULTRA_E);
                usFore.setAutomaticMode(false);
                usFore.setEnabled(true);
 
                usAft = new Ultrasonic(RIGHT_REAR_ULTRA_P, RIGHT_REAR_ULTRA_E);
                usAft.setAutomaticMode(true);
                usAft.setEnabled(true);
                break;
            }
            case CENTER:
            default:
                stopRange = 3048;// ~10 feet
                break;
        }
    }
    
    //Methods
    public void driveForward(){
//TODO:        while((rangeForward = round(usForward.getRangeMM())) == 0){}
        rangeForward = 6000; //Init to ~20ft until the forward ultrasonic sensor is installed
        pl("Range  Forward: ", rangeForward);

        if(rangeForward > stopRange)
            trainDrive.driveLikeATank(portSpeed, starboardSpeed);
        else trainDrive.driveLikeATank(0.0, 0.0);
    }
    
    public void scrapeTheWall(int script){
//TODO: Add hot goal sensing
        
        //ObjMan.deployArm(); Timer.delay(0.5);
        //ObjMan.deployForks(); Timer.delay(0.5);
        //ObjMan.VerticalFork(); Timer.delay(0.5);
        
        while((rangeFore = round(usFore.getRangeMM())) == 0){}
        pl("Range FORE: ", rangeFore);
            
        while((rangeAft = round(usAft.getRangeMM())) == 0){}
        pl("Range  AFT: ", rangeAft);

//TODO:        while((rangeForward = round(usForward.getRangeMM())) == 0){}
        rangeForward = 3048; //Init to ~10ft until the forward ultrasonic sensor is installed
        pl("Range  Forward: ", rangeForward);
           
        rangeDiff = rangeFore - rangeAft;
        pl("Range DIFF: ", rangeDiff);
            
        if(rangeForward > stopRange){
            if(Math.abs(rangeDiff) > 25.4/2){
                if(rangeDiff > 0){
                    if(script == WALL_LEFT) driveForGoal(TURN_LEFT);
                    else driveForGoal(TURN_RIGHT);

                }else if(script == WALL_LEFT) driveForGoal(TURN_RIGHT);
                      else driveForGoal(TURN_LEFT);
            
            }else driveForGoal(STRAIGHT);
        }else scoreTheGoal();
    }//End while

    private void driveForGoal(int direction){
        
        switch (direction){
            case TURN_LEFT: pl("Turn Left"); portSpeed *= 0.5; break;
            case TURN_RIGHT: pl("Turn Right"); starboardSpeed *= 0.5; break;
            default: pl("Drive Straight"); break;
        }//End Switch

        trainDrive.driveLikeATank(portSpeed, starboardSpeed);
        //ObjMan.VerticalFork();
    }//End driveForGoal

    private void scoreTheGoal(){
//TODO:Stop the drive motors, Fork to the top, and eject the ball
    }//End scoreTheGoal

    private int round(double n){
        if ((n % 1) >= 0.5) n++;
        return (int)(n - (n % 1));
    }//End round
    
    //I'm tired of typing System.out.println
    public void pl(String s){System.out.println(s);}
    public void pl(String s, int i){System.out.println(s + i);}
}