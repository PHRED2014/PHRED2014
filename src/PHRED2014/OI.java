/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package PHRED2014;
import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.*;

/**
 *
 * @author PHRED
 */
public class OI implements RobotMap{
    
    private Joystick MechStick;
    private int XJoy = 1;
    private int YJoy = 2;
    private int ZJoy = 3;
    
    
    public OI(){
        MechStick = new Joystick(3);
    }
    
    public double getJoyValue(int axis){
        switch(axis){
            case 1:
                if(Math.abs(MechStick.getX()) < 0.05){
                    return 0;
                }else{
                return MechStick.getX();
                }
            case 2:
                if(Math.abs(MechStick.getY()) < 0.05){
                    return 0;
                }else{
                return MechStick.getY();
                }
            case 3:
                if(Math.abs(MechStick.getZ()) < 0.05){
                    return 0;
                }else{
                return MechStick.getZ();
                }
            default:
                return 0;
        }
    }
}
