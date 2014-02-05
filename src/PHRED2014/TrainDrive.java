
package PHRED2014;
import edu.wpi.first.wpilibj.*;

public class TrainDrive implements RobotMap{
    
    //Instance variables: motors, sensors, etc.
    private RobotDrive driveMotors;
    private double XJoy = 0;
    private double YJoy = 0;
    private double ZJoy = 0;
    private OI COVOP;
    private Servo ServoI;
    private Servo ServoII;
    
    //Contructor(s)
    public TrainDrive(OI oi){
        COVOP = oi;
        driveMotors = new RobotDrive(1,2,3,4);
        driveMotors.setSafetyEnabled(false);
        ServoI = new Servo(1);
        ServoII = new Servo(2);
    }
    
    //Methods(functions)
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
  
  public void setServo(){
      double servoIangle = ServoI.getAngle();
      double servoIIangle = ServoII.getAngle();
      
      ServoI.setAngle(0.0);
      ServoII.setAngle(0.0);
  }
  
}

