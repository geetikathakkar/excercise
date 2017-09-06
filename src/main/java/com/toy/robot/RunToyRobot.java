package com.toy.robot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.toyrobot.Utils.RobotMovementHandler;
import com.toyrobot.Utils.TableTop;

public class RunToyRobot {

    public static void main(String[] args) {
    	String line;
    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));   
	    BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out)); 
      TableTop tableTop = new TableTop(5, 5);
        RobotMovementHandler robot = new RobotMovementHandler();
        toyRobotHandler toyRobotHandler = new toyRobotHandler(tableTop, robot);
        System.out.println("=======================Move Toy Robot you would like to=========================");
        System.out.println("Command should be like :" + "\\n" + "\'PLACE X,Y,NORTH/EAST/WEST/SOUTH', MOVE/LEFT/RIGHT/REPORT/EXIT");
        System.out.println("Enter command :: ");
        

        boolean keepRunning = true;
        String inputString = "";
        while (keepRunning) {
        	 try {
				inputString = input.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            if (inputString.equalsIgnoreCase("EXIT")) {
                keepRunning = false;
            } else {
                try {
                    String outputVal = toyRobotHandler.move(inputString);
                    if(outputVal.equalsIgnoreCase("true"))
                    	System.out.println("Success ! Give REPORT command to see toy current position.");
                    else if(outputVal.equalsIgnoreCase("false"))
                    	System.out.println("This movement will make toy fall from table. Please enter right co-ordinates.");
                    else
                    System.out.println(outputVal);
                } catch (RobotException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}