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
public class Utils{
    
    private double elapsedTime = 0.0;
    private double startTime = 0.0;
    private boolean STOP = false;
    
    public Utils(){}
        
    public void timeReset(){startTime = elapsedTime = 0.0; STOP = false;}
    public void timeStart(){startTime = System.currentTimeMillis(); STOP = false;}
    public void timeStop(){STOP = true;}
    public double timeElapsed(){
        if(STOP) return elapsedTime;
        else return elapsedTime = System.currentTimeMillis() - startTime;
    }
}
