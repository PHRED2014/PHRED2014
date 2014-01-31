/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PHRED2014;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author PHRED
 * 
 * Edit for test
 */
public class Autonomous implements RobotMap{
    
    //Instance variables
    private TrainDrive trainDrive;
    private ObjM ObjMan;

    //Contstructor(s)
    public Autonomous(TrainDrive td, ObjM om){
        trainDrive = td;
        ObjMan = om;
        
        trainDrive.Safety(false);
    }
    //Methods
    
    public void RunAutonomous(int script){
        
        //Maybe use an array indexed by script to run the appropriate
        //autonomous routine?
    }
    
}
