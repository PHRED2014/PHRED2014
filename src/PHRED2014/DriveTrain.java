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
public class DriveTrain  implements RobotMap{
    
    //Instance variables: motors, sensors, etc.
    private RobotDrive driveMotors;
    
    //Contructor(s)
    public DriveTrain(){
         driveMotors = new RobotDrive(1,2,3,4);
         //etc
    }
    
    //Methods(functions)
    
}

