
package PHRED2014;

public class Autonomous implements RobotMap{
    
    //Instance variables
    private final TrainDrive trainDrive;
    //private Utils utils;
    
    private PHREDSonic usFore = null;
    private PHREDSonic usAft = null;
    private PHREDSonic usForward = null;

    private int rangeFore, rangeAft, rangeForward, 
                rangeDiff, stopRange, rangeTolerance;
    private double turnSpeed, driveSpeed;

    //Contstructor(s)
    public Autonomous(TrainDrive td, int autoID, double[] autoSpeedSettings){
        trainDrive = td;
        //utils = new Utils();
        
        stopRange = (int)autoSpeedSettings[SCORE_RANGE_IDX];
        rangeTolerance = (int)autoSpeedSettings[RANGE_TOLERANCE_IDX];
        turnSpeed = autoSpeedSettings[TURN_SPEED_IDX];
        driveSpeed = autoSpeedSettings[DRIVE_SPEED_IDX];
        
        if(usForward == null)usForward = new PHREDSonic(FRONT_ULTRA_P, FRONT_ULTRA_E);
        switch(autoID){
            case WALL_LEFT:{
                if(usFore == null)usFore = new PHREDSonic(LEFT_FRONT_ULTRA_P,LEFT_FRONT_ULTRA_E);
                if(usAft == null)usAft = new PHREDSonic(LEFT_REAR_ULTRA_P, LEFT_REAR_ULTRA_E);
                break;
            }
            case WALL_RIGHT:{
                if(usFore == null)usFore = new PHREDSonic(RIGHT_FRONT_ULTRA_P,RIGHT_FRONT_ULTRA_E);
                if(usAft == null)usAft = new PHREDSonic(RIGHT_REAR_ULTRA_P, RIGHT_REAR_ULTRA_E);
                break;
            }
            case CENTER:
            default:
                stopRange = 3000;// ~10 feet
                break;
        }//End switch
        
        Utils.timeReset();
        Utils.timeStart();
        
    }//End Constructor
    
    //Methods
    public void driveForward(){
        usForward.ping();
        while((rangeForward = Utils.round(usForward.getRangeMM())) == 0){}
        pl("Range  Forward: ", rangeForward);

        if(rangeForward > stopRange){driveForGoal(STRAIGHT);}
        else{driveForGoal(STOP);}
    }
    
    public void scrapeTheWall(int script){
        pl("Time: " + Utils.timeElapsed());
        
        usForward.ping();
        while((rangeForward = Utils.round(usForward.getRangeMM())) == 0){}
        pl("Range  Forward: ", rangeForward);

        if(rangeForward > stopRange){
            usFore.ping();
            while((rangeFore = Utils.round(usFore.getRangeMM())) == 0){}
            
            usFore.ping();
            while((rangeAft = Utils.round(usAft.getRangeMM())) == 0){}
            
            rangeDiff = rangeFore - rangeAft;
            System.out.println("Range F,A,D: "+rangeFore+" "+rangeAft+" "+rangeDiff);
            
            if(rangeTolerance <= Math.abs(rangeDiff)){
                if(rangeDiff > 0){
                    if(script == WALL_LEFT){driveForGoal(TURN_LEFT);}
                    else{driveForGoal(TURN_RIGHT);}
                }else if(script == WALL_LEFT){driveForGoal(TURN_RIGHT);}
                      else{driveForGoal(TURN_LEFT);}
            }else{driveForGoal(STRAIGHT);}
        }else{
            Utils.timeStop();
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

    //I'm tired of typing System.out.println
    public void pl(String s){System.out.println(s);}
    public void pl(String s, int i){System.out.println(s + i);}
    public void pl(String s, double d){System.out.println(s + d);}
}