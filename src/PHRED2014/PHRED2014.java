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
    
    // This method is run when the robot is first started    
    public void robotInit() {
        //Instantiate the hardware objects
        COVOP = new OI();
        trainDrive = new TrainDrive(COVOP);
        ObjMan = new ObjM(COVOP);
    }

    // This method is called once prior to autonomous
    public void autonomousInit(){
        //Instantiate the autonomous object
        auto = new Autonomous(trainDrive, ObjMan, COVOP.getAutoID());
    }

    // This method is called periodically during autonomous
    public void autonomousPeriodic() {
        switch(COVOP.getAutoID()){
            case WALL_LEFT: auto.scrapeTheWall(WALL_LEFT); break;
            case WALL_RIGHT: auto.scrapeTheWall(WALL_RIGHT);break;
            default: auto.driveForward();break;
        }
    }

    //This method is called once prior to teleop
    public void teleopInit(){

    }

    // This method is called periodically during operator control
    public void teleopPeriodic() {
        trainDrive.MechaDrive();
        ObjMan.TankBelt();
        ObjMan.AerialArm();
        ObjMan.VerticalFork();
    }
    
    // This function is called periodically during test mode
    public void testPeriodic() {
    
    }
}