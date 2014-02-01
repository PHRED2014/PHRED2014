/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PHRED2014;
//import edu.wpi.first.wpilibj.*;

/**
 *
 * @author PHRED
 */
public class Teleoperated implements RobotMap{
    
    //Instance Variables
    private TrainDrive trainDrive;
    private ObjM ObjMan;
    
    //Constructor(s)
    public Teleoperated(TrainDrive td, ObjM om){
        trainDrive = td;
        ObjMan = om;
        
        trainDrive.InvertMecha();
    }
    
    //Methods
    public void PassScoreBlock(){
        trainDrive.MechaDrive();
        ObjMan.TankBelt();
        ObjMan.AerialArm();
        ObjMan.VerticalFork();
    }
    
}
