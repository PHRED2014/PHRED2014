
package PHRED2014;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;

public class OI implements RobotMap{
    
    private DriverStation driverStation;
    private Joystick MechStick;
    private Joystick XStick;
    private Button SpeedI = new JoystickButton(MechStick, ButtonVII),
            SpeedII = new JoystickButton(MechStick, ButtonVIII),
            SpeedIII = new JoystickButton(MechStick, ButtonIX),
            SpeedIV = new JoystickButton(MechStick, ButtonX),
            SpeedV = new JoystickButton(MechStick, ButtonXI),
            SpeedVI = new JoystickButton(MechStick, ButtonXII);        
            
    
    public OI(){
        driverStation = DriverStation.getInstance();
        MechStick = new Joystick(Xstreme3D);
        XStick = new Joystick(GamePad);
    }
    
    public double getJoyValue(int axis){
        switch(axis){
            case XAxis:
                if(Math.abs(MechStick.getX()) < DeadZone){
                    return 0;
                }else{
                return MechStick.getX();
                }
            case YAxis:
                if(Math.abs(MechStick.getY()) < DeadZone){
                    return 0;
                }else{
                return MechStick.getY();
                }
            case ZAxis:
                if(Math.abs(MechStick.getZ()) < DeadZone){
                    return 0;
                }else{
                return MechStick.getZ();
                }
            default:
                return 0;
        }
    }
    
    public double getXBoxAxisValue(int axis){
        switch(axis){
            case 1:
                
            case 2:
               
            case 4:
                
            case 5:
                if(Math.abs(XStick.getRawAxis(axis)) < DeadZone){
                    return 0;
                }else{
                    return XStick.getRawAxis(axis);
                }
            default:
                return 0;
        }
    }
    
    public double getXBoxTrigger(){
        return XStick.getRawAxis(Trigger); // Triggers return numbers between 1 and -1. Right trigger is negative.
    }
    
    public int getAutoID(){
        for(int i=1; i<9; i++)
            if(driverStation.getDigitalIn(i)){return i;}
        return 0;
    }
    
    public double getAutoSpeedSettings(int i){
        switch (i){
            case DRIVE_SPEED_IDX: 
                return driverStation.getAnalogIn(DRIVE_SPEED_IDX)/5;
            case TURN_SPEED_IDX:
                return driverStation.getAnalogIn(TURN_SPEED_IDX)/5;
            case RANGE_TOLERANCE_IDX:
                return driverStation.getAnalogIn(RANGE_TOLERANCE_IDX)*10;
            case SCORE_RANGE_IDX:
                return driverStation.getAnalogIn(SCORE_RANGE_IDX)*600;
            default:
        }
        return driverStation.getAnalogIn(i);
    }
    
    public void SetSpeedJar(double a){
        
    }
    
    public double SpeedJar(double num){
        boolean a = MechStick.getRawButton(ButtonVII);
        boolean b = MechStick.getRawButton(ButtonVIII);
        boolean c = MechStick.getRawButton(ButtonIX);
        boolean d = MechStick.getRawButton(ButtonX);
        boolean e = MechStick.getRawButton(ButtonXI);
        boolean f = MechStick.getRawButton(ButtonXII);
        
        if(a){
            return 0.5;
        }
        if(b){
            return 0.6;
        }
        if(c){
            return 0.7;
        }
        if(d){
            return 0.8;
        }
        if(e){
            return 0.9;
        }
        if(f){
            return 1.0;
        }
        
        return num;
    }
}
