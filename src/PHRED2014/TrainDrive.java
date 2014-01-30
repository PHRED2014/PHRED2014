/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package edu.wpi.first.wpilibj.templates;
package PHRED2014;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author PHRED
 */
public class TrainDrive implements RobotMap{
    
    //Instance variables: motors, sensors, etc.
    private RobotDrive driveMotors;
    private double XJoy = 0;
    private double YJoy = 0;
    private double ZJoy = 0;
    private OI COVOP;
    
    //Contructor(s)
    public TrainDrive(OI oi){
         driveMotors = new RobotDrive(1,2,3,4);
         COVOP = oi;
    }
    
    //Methods(functions)
    public void Safety(boolean onf){
        driveMotors.setSafetyEnabled(onf);
    }
    
    public void MechaDrive(){
        XJoy = COVOP.getJoyValue(1)*0.75;
        YJoy = COVOP.getJoyValue(2)*0.75;
        ZJoy = COVOP.getJoyValue(3)*0.75;
        
        driveMotors.mecanumDrive_Cartesian(-XJoy, -YJoy, -ZJoy, 0);
    }
    
    public void InvertMecha(){
        driveMotors.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driveMotors.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    }    
    
}

