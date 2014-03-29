/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package PHRED2014;
import edu.wpi.first.wpilibj.*;

public class PHRED2014 extends IterativeRobot implements RobotMap{    
    //Create Object References
    TrainDrive trainDrive;
    ObjM ObjMan;
    OI COVOP;
    Autonomous auto;

    int autoID;
    double[] autoSpeedSettings;

    // This method is run when the robot is first started    
    public void robotInit() {
        //Instantiate the hardware objects
        COVOP = new OI();
        trainDrive = new TrainDrive(COVOP);
        ObjMan = new ObjM(COVOP);
    }

    // This method is called once prior to autonomous
    public void autonomousInit(){
        autoID = COVOP.getAutoID();
        //autoSpeedSettings = COVOP.getAutoSpeedSettings();
        auto = new Autonomous(trainDrive);
        auto.initialize();
    }

    // This method is called periodically during autonomous
    public void autonomousPeriodic() {
//        if(!robotPrepped){
//            robotPrepped = ObjMan.prepTheRobot();
//        }else{
//            ObjMan.moveForks();
        switch(autoID){
            //case WALL_LEFT: auto.scoreAGoal(WALL_LEFT); break;
            //case WALL_RIGHT: auto.scoreAGoal(WALL_RIGHT);break;
            case CENTER: auto.driveForward();break;
            case DO_NOTHING:
            default: break;
        }
//        }
    }

    //This method is called once prior to teleop
    public void teleopInit(){
        trainDrive.InvertMecha();
    }

    // This method is called periodically during operator control
    public void teleopPeriodic() {
        trainDrive.MechaDrive();
        //ObjMan.moveBelt();
        //ObjMan.moveForks();
        //ObjMan.moveArm();
    }
    
    // This function is called periodically during test mode
    public void testPeriodic() {
    
    }
}
