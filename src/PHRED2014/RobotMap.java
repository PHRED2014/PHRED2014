
package PHRED2014;

public interface RobotMap {
    
//Global Constants
//TODO: Verify port mappings. Determine what to do about 1 too many Digital
    
/* -- SIDECAR PORT MAPPINGS -- */
    
//Analog
    public final static int FORK_LIMIT = 1;
    public final static int ARM_LIMIT = 2;
    
//Digital
    public final static int LEFT_FRONT_ULTRA_P = 1; //Ultrasonic Sensors
    public final static int LEFT_FRONT_ULTRA_E = 2;

    public final static int LEFT_REAR_ULTRA_P = 3;
    public final static int LEFT_REAR_ULTRA_E = 4;
    
    public final static int RIGHT_FRONT_ULTRA_P = 5;
    public final static int RIGHT_FRONT_ULTRA_E = 6;

    public final static int RIGHT_REAR_ULTRA_P = 7;
    public final static int RIGHT_REAR_ULTRA_E = 8;

    public final static int FRONT_ULTRA_P = 9;
    public final static int FRONT_ULTRA_E = 10;
    
    public final static int TOP_LIMIT = 11;//Need to verify these settings
    public final static int BOT_LIMIT = 12;
    
    public final static int CODERI = 13;//Need to verify these settings
    public final static int CODERII = 14;

//Relays
    public final static int ARM_SPIKE = 1; //Need to verify these settings
    public final static int FORK_SPIKE = 2;
    public final static int BELT_SPIKE = 3;
    
//PWM
    public final static int LEFT_FRONT_MOTOR = 1; //Train drive motors
    public final static int LEFT_REAR_MOTOR = 2;
    public final static int RIGHT_FRONT_MOTOR = 3;
    public final static int RIGHT_REAR_MOTOR = 4;
    public final static int BELT_MOTOR = 5;
    public final static int FORK_MOTOR = 8; //Need to verify this setting
    
    public final static double BELT_SPEED = 1.0;
    public final static double DRIVE_MOTOR_MOD = 1.0;


/* -- OI VARIABLES/CONSTANTS -- */
// Joystick DS Ports
    public final static int Xstreme3D = 3;
    public final static int GamePad = 4;
    
// Joystick axis and buttons 
    public final static int XAxis = 1;
    public final static int YAxis = 2;
    public final static int ZAxis = 3;
    public final static int ButtonVII = 7;
    public final static int ButtonVIII = 8;
    public final static int ButtonIX = 9;
    public final static int ButtonX = 10;
    public final static int ButtonXI = 11;
    public final static int ButtonXII = 12;
    
// Gamepad axis and buttons
    public final static int LStickX = 1;
    public final static int LStickY = 2;
    public final static int Trigger = 3;
    public final static int RStickX = 4;
    public final static int RStickY = 5;
    public final static int DPadX = 6;
    public final static int XA = 1;
    public final static int XB = 2;
    public final static int XX = 3;
    public final static int XY = 4;
    public final static int BumperL = 5;
    public final static int BumperR = 6;
    public final static int Select = 7;
    public final static int Start = 8;
    public final static int JoyPressL = 9;
    public final static int JoyPressR = 10;
    
    public final static double DeadZone = 0.10;
    
/* -- AUTONOMOUS CONTSTANTS -- */
    
    public final static int CENTER = 1;
    public final static int WALL_LEFT = 2;
    public final static int WALL_RIGHT = 3;
    
    public final static int STOP = 0;
    public final static int STRAIGHT = 1;
    public final static int TURN_LEFT = 2;
    public final static int TURN_RIGHT = 3;
    public final static int MECANUM_LEFT = 4;
    public final static int MECANUM_RIGHT = 5;
    
    public final static double RANGE_DIFF_LIMIT = 25.4/2; // ~1/2 inch
    public final static int FL_DRIVE_SPEED_IDX = 1; //First Leg Drive Speed
    public final static int FL_RANGE_TO_GOAL_IDX = 2;
    public final static int SL_DRIVE_SPEED_IDX = 3;
    public final static int SL_RANGE_TO_GOAL_IDX = 4;
    
    public final static int NO_PRESET = -1;
     
   /* -- TELEOP PRESETS -- */
    
    //Carrage Fork presets
    public final static int CF_BOTTOM = 0; //LOL RANDOM NUMBER
    public final static int CF_TOP = 2444;
    public final static int CF_MID = 777;
    public final static int CF_SCORE = 594;
}
