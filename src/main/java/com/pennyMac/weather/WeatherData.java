/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 *
 */
package com.pennyMac.weather;

/**
 * The code takes in a w_data file and output the day number (column one) with the smallest temperature spread(s) 
 * @author qing
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WeatherData {

    
    /**
     * The code takes in a w_data file and output the day number(s) (column one) with the smallest temperature spread(s)  
     * @param dataFile 
     */
	public void findMinimumTemperatureSpreadFor(String dataFile) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(dataFile)));

			//The list of smallest spread kept here.
                        List<Weather> smallestSpreadList = new ArrayList<Weather>();
			String currentLine;
			boolean inData = false;
                        int temperatureSpread = 0;
                        int smallest = 10000;
			while ((currentLine = br.readLine()) != null) {
				if (inData == true && currentLine.startsWith("  mo")) {
					inData = false;
				} else if (inData == false && currentLine.startsWith("   ")) {
					inData = true;
				}
				if (inData) {
					Pattern p = Pattern.compile("\\d");
					Matcher m = p.matcher(currentLine);
					m.find();
					int dayStartIndex = m.start();
					p = Pattern.compile("\\d ");
					m = p.matcher(currentLine);
					m.find();
					int dayEndIndex = m.start() + 1;
					int day = Integer.valueOf(currentLine.substring(dayStartIndex, dayEndIndex));

					p = Pattern.compile("\\d  ");
					m = p.matcher(currentLine);
					m.find();
					int maxStartIndex = m.end();
					p = Pattern.compile("\\d  \\d+");
					m = p.matcher(currentLine);
					m.find();
					int maxEndIndex = m.end();
					int maxTemp = Integer.valueOf(currentLine.substring(maxStartIndex, maxEndIndex));

					p = Pattern.compile("    \\d");
					m = p.matcher(currentLine);
					m.find();
					int minStartIndex = m.end() - 1;
					p = Pattern.compile("    [\\d]+");
					m = p.matcher(currentLine);
					m.find();
					int minEndIndex = m.end();
					int minTemp = Integer.valueOf(currentLine.substring(minStartIndex, minEndIndex));
                
                                        temperatureSpread = maxTemp - minTemp;
                                        if(temperatureSpread < smallest){
                                            smallestSpreadList.clear();
                                            smallestSpreadList.add(new Weather(day, maxTemp, minTemp));
                                            smallest = temperatureSpread;
                                        }else if(temperatureSpread == smallest){
                                            smallestSpreadList.add(new Weather(day, maxTemp, minTemp));
                                        }

				}
			}

			for (Weather weather : smallestSpreadList) {
				System.out.println("Smallest day(s): " + weather.toString());

			}

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

	private static class Weather {
		private final int day;
		private final int maximumTemp;
		private final int minimumTemp;

		public Weather(int day, int maximumTemp, int minimumTemp) {
			this.day = day;
			this.minimumTemp = minimumTemp;
			this.maximumTemp = maximumTemp;
		}

		public int getDay() {
			return day;
		}

		public int getTemperatureSpread() {
			return maximumTemp - minimumTemp;
		}

		@Override
		public String toString() {
			return "Weather [day=" + day + ", maximumTemp=" + maximumTemp + ", minimumTemp=" + minimumTemp + "]";
		}

	}

}