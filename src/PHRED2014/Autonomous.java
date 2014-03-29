
package PHRED2014;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous implements RobotMap{
    
    //Instance variables
    private TrainDrive trainDrive;
    private ObjM ObjMan;
    
    private PHREDSonic usFore = null;
    private PHREDSonic usAft = null;
    private PHREDSonic usForward = null;

    private double rangeFore, rangeAft, rangeDiff, endOfFirstLeg, endOfSecondLeg,
            rangeTolerance, turnSpeed, flDriveSpeed, slDriveSpeed, rangeForward,
            timeOut = 0.0;
    
    private boolean firstLeg;
    
    //Contstructor(s)
    public Autonomous(TrainDrive td, ObjM om){
        trainDrive = td;
        ObjMan = om;
    }//End Constructor
    
    //Methods
    public void initialize(int autoID, double[] autoSpeedSettings){
        flDriveSpeed = autoSpeedSettings[FL_DRIVE_SPEED_IDX - 1] * -1;//Initialize to first leg drive speed
        slDriveSpeed = autoSpeedSettings[SL_DRIVE_SPEED_IDX - 1] * -1;//Initialize to second leg drive speed

        endOfFirstLeg = autoSpeedSettings[FL_RANGE_TO_GOAL_IDX - 1]; //1800 - Distance in millimeters from the goal
        endOfSecondLeg = autoSpeedSettings[SL_RANGE_TO_GOAL_IDX - 1]; //450

        rangeTolerance = 12.7; //The allowable difference in range between the two side ultrasonic sensors
        turnSpeed = 0.5; //The 
//
        firstLeg = true;
        
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
                Utils.timeReset();
                Utils.timeStart();
                flDriveSpeed = -0.5;//Initialize to first leg drive speed
                timeOut = 3.0; //Seconds
//                endOfFirstLeg = 3000;// ~10 feet
                break;
        }//End switch

    }//End autoInit
    
    public void driveForward(){
        if(Utils.timeElapsed() < timeOut)
            driveForGoal(STRAIGHT);
        else
            driveForGoal(STOP);
        
        pl("Elapsed Time ", Utils.timeElapsed());
    }
    
    public void scoreAGoal(int script){
        
        if(firstLeg){ //The first of two legs: Tankdrive parallel to the wall until 5-6ft from goal
            //Get the range forward
            rangeForward = getTheRange(usForward);
            pl("Range Forward: ", rangeForward);

            if(rangeForward <= endOfFirstLeg){
                driveForGoal(STOP);
                firstLeg = false;
            }else{
                //Get the range to the wall at the front of the robot
                rangeFore = getTheRange(usFore);
            
                //Get the range to the wall at the rear of the robot
                rangeAft = getTheRange(usAft);
            
                //Calculate the difference between the front and rear ranges
                rangeDiff = rangeFore - rangeAft;
                System.out.println("Range F,A,D: "+rangeFore+" "+rangeAft+" "+rangeDiff);
            
                //Check for the need to correct course
                if(rangeTolerance <= Math.abs(rangeDiff)){
                    if(rangeDiff > 0){
                    
                        //Front of robot farthest from wall: course correction needed
                        if(script == WALL_LEFT){driveForGoal(TURN_LEFT);}
                        else{driveForGoal(TURN_RIGHT);}
                    
                    //Rear of robot farthest from wall: course correction needed
                    }else if(script == WALL_LEFT){driveForGoal(TURN_RIGHT);}
                          else{driveForGoal(TURN_LEFT);}
                
                //Within tolerance so no course correction
                }else{driveForGoal(STRAIGHT);}
            }
        }else{ //The second Leg: Mechanum drive at a 45 deg angle towards the wall/goal
            //Get the range forward
            rangeForward = getTheRange(usForward);
            pl("Range Forward: ", rangeForward);

            //Check to see if the robot is in scoring position
            if(rangeForward <= endOfSecondLeg){
                driveForGoal(STOP);
                scoreTheGoal();
            }else{
                if(script == WALL_LEFT)
                    driveForGoal(MECANUM_LEFT);
                else
                    driveForGoal(MECANUM_RIGHT);
            }
        }
    }

    private void driveForGoal(int direction){
        switch (direction){
            case TURN_LEFT:    trainDrive.driveLikeATank(flDriveSpeed * turnSpeed, flDriveSpeed);
                               pl("Turn Left"); break;
            case TURN_RIGHT:   trainDrive.driveLikeATank(flDriveSpeed, flDriveSpeed * turnSpeed);
                               pl("Turn Right"); break;
            case STRAIGHT:     trainDrive.driveLikeATank(flDriveSpeed, flDriveSpeed);
                               pl("Drive Straight"); break;
   /*       case MECANUM_LEFT: trainDrive.MechaDrive(slDriveSpeed, slDriveSpeed, 0.0);
                               pl("Mecanum Left"); break;
            case MECANUM_RIGHT:trainDrive.MechaDrive(-slDriveSpeed, slDriveSpeed, 0.0);
                               pl("Mecanum Right"); break;
   */
            case STOP:
            default:           trainDrive.driveLikeATank(0.0, 0.0);
                               pl("Stop"); break;
        }//End Switch
    }//End driveForGoal

    private void scoreTheGoal(){
        if(ObjMan.moveForks())//Returns true when forks in scoring position
            ObjMan.TankBelt();
    }//End scoreTheGoal
    
    private double getTheRange(PHREDSonic us){
        double range = 0.0;
        double maxRange = 4500; //~15 Feet
        timeOut = 0.010;
        
        us.ping();
        Utils.timeReset();
        Utils.timeStart();
        
        while(Utils.timeElapsed() < timeOut){
            if((range = us.getRangeMM())!= 0.0)break;
        }
        if(range == 0.0 || range > maxRange)
            range = maxRange;
        return range;
    }

    //I'm tired of typing System.out.println
    public void pl(String s){System.out.println(s); SmartDashboard.putString(s, s);}
    public void pl(String s1, String s2){System.out.println(s1 + s2); SmartDashboard.putString(s1, s2);}
    public void pl(String s, int i){System.out.println(s + i); SmartDashboard.putNumber(s, i);}
    public void pl(String s, double d){System.out.println(s + d); SmartDashboard.putNumber(s, d);}
}