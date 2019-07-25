/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pennyMac.soccer;



/**
 *
 * @author qing
 */
public class DriverCode {

    /**
     * The code takes in a soccer.dat  file and print out the team(s) with smallest spread(s).
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String filePath = "/soccer.dat";
        new SoccerData().findSmallestDifferenceFor(filePath);
    }
    
    
    
}
