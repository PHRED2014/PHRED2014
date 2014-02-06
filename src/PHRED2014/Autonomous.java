
package PHRED2014;
import edu.wpi.first.wpilibj.*;

public class Autonomous implements RobotMap{
    
    //Instance variables
    private TrainDrive trainDrive;
    private ObjM ObjMan;

    //Contstructor(s)
    public Autonomous(TrainDrive td, ObjM om){
        trainDrive = td;
        ObjMan = om;
    }
    //Methods
    public void RunAutonomous(int script){
        
        //Maybe use an array indexed by script to run the appropriate
        //autonomous routine?
    }
}
