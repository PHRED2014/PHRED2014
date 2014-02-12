
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
         driveMotors = new RobotDrive(LEFT_FRONT_MOTOR, LEFT_REAR_MOTOR, RIGHT_FRONT_MOTOR, RIGHT_REAR_MOTOR);
         driveMotors.setSafetyEnabled(false);
         COVOP = oi;
  }
    
    //Methods(functions)
    public void MechaDrive(){
//TODO: Add smartdashboard slider tied to the speed factor
        XJoy = COVOP.getJoyValue(XAxis)*DRIVE_MOTOR_MOD;
        YJoy = COVOP.getJoyValue(YAxis)*DRIVE_MOTOR_MOD;
        ZJoy = COVOP.getJoyValue(ZAxis)*DRIVE_MOTOR_MOD;
        
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

