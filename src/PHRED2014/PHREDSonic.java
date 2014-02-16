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

    private static final double pingTime = 10 * 1e-6;	///< Time (sec) for the ping trigger pulse.
    private static final double sosMMPerSec = 1130.0 * 12.0 * 25.4;
    private DigitalInput echoChannel = null;
    private DigitalOutput pingChannel = null;
    private Counter counter = null;

    public PHREDSonic(final int pc, final int ec) {
        pingChannel = new DigitalOutput(pc);
        echoChannel = new DigitalInput(ec);

        counter = new Counter(echoChannel); // set up counter for this sensor
        counter.setMaxPeriod(1.0);
        counter.setSemiPeriodMode(true);

        counter.reset();
        counter.start();
    }

    public void ping() {
        counter.reset(); // reset the counter to zero (invalid data now)
        pingChannel.pulse(pingTime); // do the ping to start getting a single range
        Timer.delay(0.001);
    }

    public double getRangeMM() {
        if (counter.get() > 1) {
            return counter.getPeriod() * sosMMPerSec / 2.0;
        } else {
            return 0;
        }
    }
    
    public void free(){
        pingChannel.free();
        pingChannel = null;
        
        echoChannel.free();
        echoChannel = null;
        
        counter.free();
        counter = null;
    }
}