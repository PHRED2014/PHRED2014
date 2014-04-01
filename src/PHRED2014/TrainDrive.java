
package PHRED2014;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TrainDrive implements RobotMap{
    
    //Instance variables: motors, sensors, etc.
    private RobotDrive driveMotors;
    private RobotDrive reverseDriveMotors;
    private double xPrevSpeed, XJoy = 0;
    private double yPrevSpeed, YJoy = 0;
    private double zPrevSpeed, ZJoy = 0;
    private OI COVOP;
    private double speedAdj = DRIVE_MOTOR_MOD;
    
    //Contructor(s)
    public TrainDrive(OI oi){
         driveMotors = new RobotDrive(LEFT_FRONT_MOTOR, LEFT_REAR_MOTOR, 
                                      RIGHT_FRONT_MOTOR, RIGHT_REAR_MOTOR);
         reverseDriveMotors = new RobotDrive(RIGHT_REAR_MOTOR, RIGHT_FRONT_MOTOR,
                                             LEFT_REAR_MOTOR, LEFT_FRONT_MOTOR);

         driveMotors.setSafetyEnabled(false);
         COVOP = oi;
  }
    
    //Methods(functions)
    public void MechaDrive(){
        
        xPrevSpeed = XJoy = setSpeed(COVOP.getJoyValue(XAxis), xPrevSpeed) * speedAdj;
        yPrevSpeed = YJoy = setSpeed(COVOP.getJoyValue(YAxis), yPrevSpeed) * speedAdj;
        zPrevSpeed = ZJoy = setSpeed(COVOP.getJoyValue(ZAxis), zPrevSpeed) * speedAdj;
        
        if(ZJoy < 0)
            ZJoy = Utils.power(ZJoy, 2) * -1;
        else
            ZJoy = Utils.power(ZJoy, 2);
        
//        driveMotors.mecanumDrive_Cartesian(-XJoy, -YJoy, -ZJoy, 0);
        
        if(COVOP.isTriggerPressed())
            reverseDriveMotors.mecanumDrive_Cartesian(-XJoy, -YJoy, -ZJoy, 0);
        else
            driveMotors.mecanumDrive_Cartesian(-XJoy, -YJoy, -ZJoy, 0);
        
        SmartDashboard.putNumber("ORCA Effeciency", speedAdj);
    }
    
    public void driveLikeATank(double leftSpeed, double rightSpeed){ //Used for atonomous
        driveMotors.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void InvertMecha(){
        driveMotors.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driveMotors.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    }
    
    private double setSpeed(double cs, double ps){
        double ms;
        int ss = 1;
        double maxSpeedIncrease = 0.20;
        
        if(cs < 0)
            ss = -1;

        //TODO: Handle a change from positive to negative or viceversa 

        cs = Math.abs(cs);
        ps = Math.abs(ps);

        if(cs == 0 || cs <= ps || ps + maxSpeedIncrease > cs)
            ms = cs;
        else
            ms = ps + maxSpeedIncrease;
         
        return ms * ss;
    }
}