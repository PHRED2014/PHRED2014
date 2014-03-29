
package PHRED2014;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous implements RobotMap{
    
    //Instance variables
    private TrainDrive trainDrive;
    
    private PHREDSonic usFore = null;
    private PHREDSonic usAft = null;
    private PHREDSonic usForward = null;

    String status;
    private double rangeFore, rangeAft, rangeDiff, stopRange, rangeTolerance = 0.0;
    private double turnSpeed, driveSpeed, rangeForward, plSpeed, prSpeed = 0.0;

    //Contstructor(s)
    public Autonomous(TrainDrive td, int autoID, double[] autoSpeedSettings){
        trainDrive = td;
        
        stopRange = autoSpeedSettings[SCORE_RANGE_IDX - 1];
        rangeTolerance = autoSpeedSettings[RANGE_TOLERANCE_IDX - 1];
        turnSpeed = autoSpeedSettings[TURN_SPEED_IDX - 1];
        driveSpeed = autoSpeedSettings[DRIVE_SPEED_IDX - 1];
        
        //if(usForward == null)usForward = new PHREDSonic(FRONT_ULTRA_P, FRONT_ULTRA_E);
        usForward = new PHREDSonic(9,10);

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
                stopRange = 1000;// ~10 feet
                break;
        }//End switch
        
        Utils.timeReset();
        Utils.timeStart();
    }//End Constructor
    
    //Methods
    public void driveForward(){
        rangeForward = getTheRange(usForward);
        SmartDashboard.putNumber("E Time: ", Utils.timeElapsed());        
        SmartDashboard.putNumber("Forward: ", rangeForward);

        if(rangeForward > stopRange){driveForGoal(STRAIGHT);}
        else{driveForGoal(STOP);}
    }
    
    public void scrapeTheWall(int script){
        rangeForward = getTheRange(usForward);

        if(rangeForward > stopRange){
            rangeFore = getTheRange(usFore);
            rangeAft = getTheRange(usAft);
            rangeDiff = rangeFore - rangeAft;
            
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
        sdPost();
    }

    private void driveForGoal(int direction){
        double lSpeed, rSpeed;
        lSpeed = rSpeed = -driveSpeed;
        
        switch (direction){
            case TURN_LEFT: lSpeed *= turnSpeed; status = "Turn Left"; break;
            case TURN_RIGHT: rSpeed *= turnSpeed; status = "Turn Right"; break;
            case STRAIGHT: status = "Drive Straight"; break;
            case STOP:
            default: lSpeed = rSpeed = 0.0; status = "Stop"; break;
        }//End Switch

        if(plSpeed != lSpeed || prSpeed != rSpeed){
            plSpeed = lSpeed; prSpeed = rSpeed;
            trainDrive.driveLikeATank(lSpeed, rSpeed);
        }
    }//End driveForGoal

    private void strafeToScorePosition(){
//TODO: Strafe left or right to center ball on goal        
    }
    
    private void scoreTheGoal(){
//TODO: Fork to position, and eject the ball
    }//End scoreTheGoal
    
    private double getTheRange(PHREDSonic us){
        double range;
        while(true){
            us.ping();
            if((range = us.getRangeMM())!= 0.0)break;
        }
        return range;
    }

    private void sdPost(){
        SmartDashboard.putNumber("E Time: ", Utils.timeElapsed());        
        SmartDashboard.putNumber("Front: ", rangeForward);
        SmartDashboard.putNumber("LeftFront: ", rangeFore);
        SmartDashboard.putNumber("LeftRear: ", rangeAft);
        SmartDashboard.putNumber("F/R Diff: ", rangeDiff);
        SmartDashboard.putString("Robot Status: ", status);
    }
}