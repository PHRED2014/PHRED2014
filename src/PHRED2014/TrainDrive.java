
package PHRED2014;
import edu.wpi.first.wpilibj.*;

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
         driveMotors.setSafetyEnabled(false);
         COVOP = oi;
  }
    
    //Methods(functions)
    public void MechaDrive(){
//TODO: Add smartdashboard slider tied to the speed factor
        XJoy = COVOP.getJoyValue(1)*0.75;
        YJoy = COVOP.getJoyValue(2)*0.75;
        ZJoy = COVOP.getJoyValue(3)*0.75;
        
        driveMotors.mecanumDrive_Cartesian(-XJoy, -YJoy, -ZJoy, 0);
    }
    
    public void driveLikeATank(double leftSpeed, double rightSpeed){ //Used for atonomous
        driveMotors.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void InvertMecha(){
        driveMotors.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driveMotors.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    }
}

