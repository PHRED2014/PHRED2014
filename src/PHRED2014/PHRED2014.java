/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package PHRED2014;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Random; // --! THIS IS FOR THE TEST FUNCTION. NOT ACTUAL ROBOT. IF NEEDED IS OKAY TO DELETE !--

public class PHRED2014 extends IterativeRobot implements RobotMap{    
    //Create Object References
    TrainDrive trainDrive;
    ObjM ObjMan;
    OI COVOP;
    Autonomous auto;
    
    Random r = new Random(); // !-- THIS IS FOR TEST FUNCTION --! 
    int graph = 5; // !-- SO IS THIS --!
    boolean invert = false; // !-- AND THIS --!

    int autoID;
    double[] autoSpeedSettings;
    boolean robotPrepped = false;

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
        autoID = COVOP.getAutoID();
        autoSpeedSettings = COVOP.getAutoSpeedSettings();
        auto = new Autonomous(trainDrive, autoID, autoSpeedSettings);
    }

    // This method is called periodically during autonomous
    public void autonomousPeriodic() {
        if(!robotPrepped){robotPrepped = ObjMan.prepTheRobot();}
//TODO:        else{ObjMan.moveForks(-1.0, NO_PRESET);}
        switch(autoID){
            case WALL_LEFT: auto.scrapeTheWall(WALL_LEFT); break;
            case WALL_RIGHT: auto.scrapeTheWall(WALL_RIGHT);break;
            case CENTER: auto.driveForward();break;
            default: break;
        }
    }

    //This method is called once prior to teleop
    public void teleopInit(){
        trainDrive.InvertMecha();  
       // ObjMan.deployForks();
    }

    // This method is called periodically during operator control
    public void teleopPeriodic() {
        if(!robotPrepped){robotPrepped = ObjMan.prepTheRobot();}
        trainDrive.MechaDrive();
        ObjMan.TankBelt(RStickY);
        ObjMan.VerticalFork(LStickY);
        SmartDashboard.putNumber("RStrickY: ", COVOP.getXBoxAxisValue(RStickY));
        SmartDashboard.putNumber("RStrickX: ", COVOP.getXBoxAxisValue(RStickX));
        SmartDashboard.putNumber("Incoder", ObjMan.GetEncoder());
        ObjMan.Move_to_the_preset_values_that_we_determined_at_a_previous_time_(XA, CF_BOTTOM, 1);
        ObjMan.Move_to_the_preset_values_that_we_determined_at_a_previous_time_(XY, CF_TOP, 1);
        ObjMan.Move_to_the_preset_values_that_we_determined_at_a_previous_time_(XB, CF_MID, 1);
        ObjMan.Move_to_the_preset_values_that_we_determined_at_a_previous_time_(XX, CF_SCORE, 1);
        
//        if (COVOP.getXBoxButton(XA))
//        {
//            SmartDashboard.putString("flagella: ", "seventy seven");
//        }
    }
    
    // This function is called periodically during test mode
    public void testPeriodic() {
        if(!invert){
            trainDrive.InvertMecha();
            invert = true;
        }
        
         if(COVOP.getXBoxButton(XA)){
             ObjMan.deployForks();
         }
         
         if(COVOP.getXBoxButton(XY)){
             ObjMan.deployArm();
         }
         
         ObjMan.TankBelt(Trigger);
         
         trainDrive.MechaDrive();
         trainDrive.BoxDrive();
         
         ObjMan.XFork(BumperL, BumperR);
         
         SmartDashboard.putString("TEST MODE", "ACTIVATED");
         
         
         graph += (r.nextInt(3) - 1);
         SmartDashboard.putInt("TEST OUTPUT", graph); // Hopefully this will be a cool graph
         
         
    }
}
