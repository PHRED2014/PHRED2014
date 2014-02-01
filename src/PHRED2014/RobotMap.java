/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PHRED2014;

/**
 *
 * @author PHRED
 */
public interface RobotMap {
    
    //Global Constants
    
//Analog
    
    public final static int GyroI = 1; //PRobably unndeeded
    
//Digital
    public final static int SanicI = 1; //probably undeedeed
    public final static int SanicII = 2;
    public final static int SanicIII = 3;
    public final static int SanicIV = 4;
    public final static int SanicV = 5;
    
    public final static int OpticsI = 6; //Optical sensors. Put it on screen, Data
    
    public final static int LSwitchI = 7; //Limit switches
    public final static int LSwitchII = 8;
    
    public final static int CoderI = 9; //Encoder
    public final static int CoderII = 10;
    
    public final static int ServoI = 11; //Servo Thingy for the optics. They came from behind!
    public final static int ServoII = 12;
    
//Relays
    
    public final static int SpikeI = 1; //Spikes for Victors and Relays?
    public final static int SpikeII = 2;
    public final static int SpikeIII = 3;
    public final static int SpikeIV = 4;
    
    public final static int SolI = 1; //Soleniods
    public final static int SolII = 2;
    public final static int SolIII = 3;
    
//PWM
    
    public final static int PWMI = 10;
    public final static int PWMII = 8;
    public final static int PWMIII = 6;
    
    // -- OI VARIABLES -- //
    
    // Joystick axis and buttons 
    public final static int XAxis = 1;
    public final static int YAxis = 2;
    public final static int ZAxis = 3;
    
    // Gamepad axis and buttons
    public final static int LStickX = 1;
    public final static int LStickY = 2;
    public final static int Trigger = 3;
    public final static int RStickX = 4;
    public final static int RStickY = 5;
    public final static int DPadX = 6;
    
    
    

    
}
