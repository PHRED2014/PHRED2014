
package PHRED2014;
import edu.wpi.first.wpilibj.*;

public class Autonomous implements RobotMap{
    
    //Instance variables
    private final TrainDrive trainDrive;
    private final ObjM ObjMan;
    private final OI COVOP;
    
    private Ultrasonic usFore = null;
    private Ultrasonic usAft = null;
//TODO:    private Ultrasonic usForward = null;

    private int rangeFore, rangeAft, rangeForward, rangeDiff, stopRange;

    //Contstructor(s)
    public Autonomous(TrainDrive td, ObjM om, OI oi){
        stopRange = round(14 * 25.4);//Fork length: 14" converted to mm
        trainDrive = td;
        ObjMan = om;
        COVOP = oi;
        
//TODO:        usForward = new Ultrasonic(FRONT_ULTRA_P, FRONT_ULTRA_E);
//        usForward.setAutomaticMode(false);
//        usForward.setEnabled(true);

        switch(COVOP.getAutoID()){
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
        rangeForward = 6100; //Init to ~20ft until the forward ultrasonic sensor is installed
        pl("Range  Forward: ", rangeForward);

        if(rangeForward > stopRange){driveForGoal(STRAIGHT);}
        else{driveForGoal(STOP);}
    }
    
    public void scrapeTheWall(int script){
        
//TODO:        while((rangeForward = round(usForward.getRangeMM())) == 0){}
        rangeForward = 3048; //Init to ~10ft until the forward ultrasonic sensor is installed
        pl("Range  Forward: ", rangeForward);

        if(rangeForward > stopRange){
            while((rangeFore = round(usFore.getRangeMM())) == 0){}
            pl("Range FORE: ", rangeFore);
            
            while((rangeAft = round(usAft.getRangeMM())) == 0){}
            pl("Range  AFT: ", rangeAft);
            
            rangeDiff = rangeFore - rangeAft;
            pl("Range DIFF: ", rangeDiff);
            
            if(COVOP.getAutoSpeedSettings(RANGE_TOLERANCE_IDX) <= Math.abs(rangeDiff)){
                if(rangeDiff > 0){
                    if(script == WALL_LEFT){driveForGoal(TURN_LEFT);}
                    else{driveForGoal(TURN_RIGHT);}
                }else if(script == WALL_LEFT){driveForGoal(TURN_RIGHT);}
                      else{driveForGoal(TURN_LEFT);}
            }else{driveForGoal(STRAIGHT);}
        }else{
            driveForGoal(STOP);
            strafeToScorePosition();
            scoreTheGoal();
        }
    }//End while

    private void driveForGoal(int direction){
        double lSpeed, rSpeed;
        lSpeed = rSpeed = COVOP.getAutoSpeedSettings(DRIVE_SPEED_IDX);
//        double autoSpeedSettings[] = COVOP.getAutoSpeedSettings();
//        lSpeed = rSpeed = autoSpeedSettings[DRIVE_SPEED_IDX];
        
        switch (direction){
            case TURN_LEFT: pl("Turn Left"); lSpeed *= COVOP.getAutoSpeedSettings(TURN_SPEED_IDX); break;
            case TURN_RIGHT: pl("Turn Right"); rSpeed *= COVOP.getAutoSpeedSettings(TURN_SPEED_IDX); break;
            case STRAIGHT: pl("Drive Straight"); break;
            case STOP:
            default: pl("Stop"); lSpeed = rSpeed = 0.0;
        }//End Switch

        trainDrive.driveLikeATank(lSpeed, rSpeed);
    }//End driveForGoal

    private void strafeToScorePosition(){
//TODO: Strafe left or right to center ball on goal        
    }
    
    private void scoreTheGoal(){
//TODO: Fork to position, and eject the ball
    }//End scoreTheGoal

    private int round(double n){
        if ((n % 1) >= 0.5) n++;
        return (int)(n - (n % 1));
    }//End round
    
    //I'm tired of typing System.out.println
    public void pl(String s){System.out.println(s);}
    public void pl(String s, int i){System.out.println(s + i);}
    public void pl(String s, double d){System.out.println(s + d);}
}