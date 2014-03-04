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
    
    private static double elapsedTime = 0.0;
    private static double startTime = 0.0;
    private static boolean running = false;
    
    //public Utils(){}
        
    public static void timeReset(){startTime = elapsedTime = 0.0;}
    public static void timeStart(){startTime = System.currentTimeMillis(); running = true;}
    public static void timeStop(){running = false;}
    public static double timeElapsed(){
        if(running) elapsedTime = (System.currentTimeMillis() - startTime)/1000;
        return elapsedTime;
    }
    
    public static int round(double n){
        if ((n % 1) >= 0.5) n++;
        return (int)(n - (n % 1));
    }//End round
    
    //
    public static double power(double d, int p){
        double dd = 1;
        
        if(p != 0)
            for(int i = 1; i <= p; i++){dd *= d;}
        return dd;
    }//End power
}
