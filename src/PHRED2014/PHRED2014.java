/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//package edu.wpi.first.wpilibj.templates;
package PHRED2014;
import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class PHRED2014 extends IterativeRobot implements RobotMap{
    
    //Comment added to test github.
    
    // This method is run when the robot is first started    
    public void robotInit() {
        DriveTrain driveRobot = new DriveTrain();
        ObjectManipulation obMan = new ObjectManipulation();
    }

    // This method is called once prior to autonomous
    public void autonomousInit(){
        Autonomous auto = new Autonomous();
    }

    // This method is called periodically during autonomous
    public void autonomousPeriodic() {

    }

    //This method is called once prior to teleop
    public void teleopInit(){
        
    }

    // This method is called periodically during operator control
    public void teleopPeriodic() {
        
    }
    
    // This function is called periodically during test mode
    public void testPeriodic() {
    
    }
}
