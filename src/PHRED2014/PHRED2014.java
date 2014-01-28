/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//package edu.wpi.first.wpilibj.templates;
package PHRED2014;
import edu.wpi.first.wpilibj.*;
import java.lang.Math.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project update the manifest file in the resource
 * directory.
 */
public class PHRED2014 extends IterativeRobot implements RobotMap{
    
    //Create Object References
    TrainDrive trainDrive;
    ObjM ObjMan;
    OI COVOP;
    
    Autonomous auto;
    Teleoperated teleOp;
    
    // This method is run when the robot is first started    
    public void robotInit() {
        //Instantiate the hardware objects
        trainDrive = new TrainDrive(COVOP);
        ObjMan = new ObjM(COVOP);
    }

    // This method is called once prior to autonomous
    public void autonomousInit(){
        //Instantiate the autonomous object
        auto = new Autonomous(trainDrive, ObjMan);
    }

    // This method is called periodically during autonomous
    public void autonomousPeriodic() {
        //Example for selecting the autonomous script
        auto.RunAutonomous(1);
    }

    //This method is called once prior to teleop
    public void teleopInit(){
        //Instantiate the teleop object
        teleOp = new Teleoperated(trainDrive, ObjMan);
    }

    // This method is called periodically during operator control
    public void teleopPeriodic() {
        teleOp.PassScoreBlock();
    }
    
    // This function is called periodically during test mode
    public void testPeriodic() {
    
    }
}
