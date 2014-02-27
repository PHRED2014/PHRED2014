
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
    
    public double[] getAutoSpeedSettings(){
        double as[] = new double[4];
        as[0] = driverStation.getAnalogIn(FL_DRIVE_SPEED_IDX)/5;//0 to 1
        as[1] = driverStation.getAnalogIn(FL_RANGE_TO_GOAL_IDX)*600;//0 to 3000
        as[2] = driverStation.getAnalogIn(SL_DRIVE_SPEED_IDX)/5;
        as[3] = driverStation.getAnalogIn(SL_RANGE_TO_GOAL_IDX)*600;
        return as;
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
    
    public boolean getXBoxButton(int button){
        return XStick.getRawButton(button);
    }
}
