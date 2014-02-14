
package PHRED2014;
import edu.wpi.first.wpilibj.*;

public class OI implements RobotMap{
    
    private DriverStation driverStation;
    private Joystick MechStick;
    private Joystick XStick;
    
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
                if(Math.abs(XStick.getRawAxis(axis)) < DeadZone){
                    return 0;
                }else{
                    return XStick.getRawAxis(axis);
                }
            case 2:
                if(Math.abs(XStick.getRawAxis(axis)) < DeadZone){
                    return 0;
                }else{
                    return XStick.getRawAxis(axis);
                }
            case 4:
                if(Math.abs(XStick.getRawAxis(axis)) < DeadZone){
                    return 0;
                }else{
                    return XStick.getRawAxis(axis);
                }
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
            default:
        }
        return (int)driverStation.getAnalogIn(i);
    }

}
