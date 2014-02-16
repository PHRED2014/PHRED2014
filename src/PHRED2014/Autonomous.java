
package PHRED2014;
import edu.wpi.first.wpilibj.*;

public class Autonomous implements RobotMap{
    
    //Instance variables
    private final TrainDrive trainDrive;
    private final OI COVOP;
    private Utils utils;
    
    private PHREDSonic usFore = null;
    private PHREDSonic usAft = null;
    private PHREDSonic usForward = null;

    private int rangeFore, rangeAft, rangeForward, 
                rangeDiff, stopRange, rangeTolerance;
    private double turnSpeed, driveSpeed;

    //Contstructor(s)
    public Autonomous(TrainDrive td, OI oi){
        trainDrive = td;
        COVOP = oi;

        utils = new Utils();
        utils.timeReset();
        utils.timeStart();
        
        stopRange = round(COVOP.getAutoSpeedSettings(SCORE_RANGE_IDX));
        rangeTolerance = round(COVOP.getAutoSpeedSettings(RANGE_TOLERANCE_IDX));
        turnSpeed = round(COVOP.getAutoSpeedSettings(TURN_SPEED_IDX));
        driveSpeed = COVOP.getAutoSpeedSettings(DRIVE_SPEED_IDX);
        
        usForward = new PHREDSonic(FRONT_ULTRA_P, FRONT_ULTRA_E);
        switch(COVOP.getAutoID()){
            case WALL_LEFT:{
                usFore = new PHREDSonic(LEFT_FRONT_ULTRA_P,LEFT_FRONT_ULTRA_E);
                usAft = new PHREDSonic(LEFT_REAR_ULTRA_P, LEFT_REAR_ULTRA_E);
                break;
            }
            case WALL_RIGHT:{
                usFore = new PHREDSonic(RIGHT_FRONT_ULTRA_P,RIGHT_FRONT_ULTRA_E);
                usAft = new PHREDSonic(RIGHT_REAR_ULTRA_P, RIGHT_REAR_ULTRA_E);
                break;
            }
            case CENTER:
            default:
                stopRange = 3000;// ~10 feet
                break;
        }//End switch
    }//End Constructor
    
    //Methods
    public void driveForward(){
        usForward.ping();
        while((rangeForward = round(usForward.getRangeMM())) == 0){}
        pl("Range  Forward: ", rangeForward);

        if(rangeForward > stopRange){driveForGoal(STRAIGHT);}
        else{driveForGoal(STOP);}
    }
    
    public void scrapeTheWall(int script){
        pl("Time: " + utils.timeElapsed());
        
        usForward.ping();
        while((rangeForward = round(usForward.getRangeMM())) == 0){}
        pl("Range  Forward: ", rangeForward);

        if(rangeForward > stopRange){
            usFore.ping();
            while((rangeFore = round(usFore.getRangeMM())) == 0){}
            pl("Range FORE: ", rangeFore);
            
            usFore.ping();
            while((rangeAft = round(usAft.getRangeMM())) == 0){}
            pl("Range  AFT: ", rangeAft);
            
            rangeDiff = rangeFore - rangeAft;
            pl("Range DIFF: ", rangeDiff);
            
            if(rangeTolerance <= Math.abs(rangeDiff)){
                if(rangeDiff > 0){
                    if(script == WALL_LEFT){driveForGoal(TURN_LEFT);}
                    else{driveForGoal(TURN_RIGHT);}
                }else if(script == WALL_LEFT){driveForGoal(TURN_RIGHT);}
                      else{driveForGoal(TURN_LEFT);}
            }else{driveForGoal(STRAIGHT);}
        }else{
            utils.timeStop();
            driveForGoal(STOP);
            strafeToScorePosition();
            scoreTheGoal();
        }
    }//End while

    private void driveForGoal(int direction){
        double lSpeed, rSpeed;
        lSpeed = rSpeed = -driveSpeed;
        
        switch (direction){
            case TURN_LEFT: pl("Turn Left"); lSpeed *= turnSpeed; break;
            case TURN_RIGHT: pl("Turn Right"); rSpeed *= turnSpeed; break;
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