/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PHRED2014;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author PHRED
 */
public class PHREDSonic {

    private static final double pingTime = 10 * 1e-6;	// Time in seconds for the ping/trigger pulse.
    private static final double sosMMPerSec = 1130.0 * 12.0 * 25.4; // Speed of sound in millimeters/second
    private DigitalInput echoChannel = null;
    private DigitalOutput pingChannel = null;
    private Counter counter = null; // Used to count the high/low changes on the echo channel

    public PHREDSonic(final int pc, final int ec) {
        pingChannel = new DigitalOutput(pc);
        echoChannel = new DigitalInput(ec);

        counter = new Counter(echoChannel); // Set up the counter on the echo channel
        counter.setMaxPeriod(1.0);
        counter.setSemiPeriodMode(true);

        counter.reset();
        counter.start();
    }

    public void ping() {
        counter.reset();             // Reset the counter to zero
        pingChannel.pulse(pingTime); // Send the ping
        Timer.delay(0.010);          // Wait a little for the pulse
    }

    public double getRangeMM() {
        if (counter.get() > 1){      // Need a count of 2 to know an echo was received
            //System.out.println("period: " + counter.getPeriod());
            return counter.getPeriod() * sosMMPerSec / 2.0; //Only one leg needed so half the period
        }
        return 0;
    }
    
    //Frees the objects used in this class
    public void free(){
        counter.free();
        counter = null;

        echoChannel.free();
        echoChannel = null;
        
        pingChannel.free();
        pingChannel = null;
    }
}