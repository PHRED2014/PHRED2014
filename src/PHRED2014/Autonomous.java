
package PHRED2014;
import edu.wpi.first.wpilibj.*;

public class Autonomous implements RobotMap{
    
    //Instance variables
    private TrainDrive trainDrive;
    private ObjM ObjMan;
  //  private Servo ServoI;
 //   private Servo ServoII;

    //Contstructor(s)
    public Autonomous(TrainDrive td, ObjM om){
        trainDrive = td;
        ObjMan = om;
  //      ServoI = new Servo(1);
  //      ServoII = new Servo(2);
        
    }
    //Methods
    public void RunAutonomous(int script){
        
        //Maybe use an array indexed by script to run the appropriate
        //autonomous routine?
    }
}
