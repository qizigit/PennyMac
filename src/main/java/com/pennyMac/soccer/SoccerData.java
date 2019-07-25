/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 */
package com.pennyMac.soccer;

/**
 *.
 * @author qing
 */
import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class SoccerData {

       /**
        * * The code takes in a soccer.dat file and print out the team(s) with smallest spread(s).
        * @param dataFile 
        */
	public void findSmallestDifferenceFor(String dataFile) {
		BufferedReader br = null;
                HashMap<String, Integer> teams = new HashMap<>();
		try {
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(dataFile)));
			String currentLine;
                        String teamName = null;
                        int smallest = 10000;
			while ((currentLine = br.readLine()) != null) {

                                     String[] name = split(filter(currentLine));
                                     
                             if(name != null){
                                 
                                 String team = name[1];
                                 int a = Integer.valueOf(name[6]);
				 int b = Integer.valueOf(name[8]);
                                 int diff = a - b;
                                 if(diff >=0 && diff < smallest){
                                    smallest =  diff ;
                                    teamName = team ;
                                    teams.clear();
                                    
                                    teams.put(team, smallest); // save the smallest team
                                    
                                 }else if(diff >=0 && diff == smallest){  // multiple teams
                                    teams.put(team, smallest);
                                 }

                             }
			}
                                                          
                        //print out the result
                                 
                        teams.entrySet().forEach((entry) -> {
                            System.out.println(entry.getKey()+" : "+entry.getValue());
                           });


		} catch (IOException e) {
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}

	}
        
        public String[] split(String inputRow) {
		return (inputRow != null ? inputRow.split("\\s+") : null);
	}

	public String filter(String inputRow) {
		if (inputRow != null) {
			inputRow = inputRow.trim();

			if (inputRow != null && inputRow.length() > 0) {
				Pattern pat = Pattern.compile("\\s\\_?\\d+\\.?");

				Matcher match = pat.matcher(inputRow);
				if (match.find()) {
					return inputRow;

				}
			}
		}
		return null;
	}



}