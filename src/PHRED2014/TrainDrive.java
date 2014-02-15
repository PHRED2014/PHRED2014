
package PHRED2014;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TrainDrive implements RobotMap{
    
    //Instance variables: motors, sensors, etc.
    private RobotDrive driveMotors;
    private double XJoy = 0;
    private double YJoy = 0;
    private double ZJoy = 0;
    private OI COVOP;
    private double Speed = DRIVE_MOTOR_MOD;
    
    //Contructor(s)
    public TrainDrive(OI oi){
         driveMotors = new RobotDrive(LEFT_FRONT_MOTOR, LEFT_REAR_MOTOR, 
                                      RIGHT_FRONT_MOTOR, RIGHT_REAR_MOTOR);
         driveMotors.setSafetyEnabled(false);
         COVOP = oi;
  }
    
    //Methods(functions)
    public void MechaDrive(){
        Speed = COVOP.SpeedJar(Speed);
        XJoy = COVOP.getJoyValue(XAxis)*Speed;
        YJoy = COVOP.getJoyValue(YAxis)*Speed;
        ZJoy = COVOP.getJoyValue(ZAxis)*Speed;
        
        driveMotors.mecanumDrive_Cartesian(-XJoy, -YJoy, -ZJoy, 0);
        
        SmartDashboard.putNumber("ORCA Effeciency", Speed);
    }
    
    public void driveLikeATank(double leftSpeed, double rightSpeed){ //Used for atonomous
        driveMotors.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void InvertMecha(){
        driveMotors.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driveMotors.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    }
    
    public void BoxDrive(){
        Speed = COVOP.SpeedJar(Speed);
        XJoy = COVOP.getXBoxAxisValue(LStickX)*Speed;
        YJoy = COVOP.getXBoxAxisValue(LStickY)*Speed;
        ZJoy = COVOP.getXBoxAxisValue(RStickX)*Speed;
        
        driveMotors.mecanumDrive_Cartesian(-XJoy, -YJoy, -ZJoy, 0);
    }
}

