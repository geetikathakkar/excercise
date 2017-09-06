package com.toy.robot;

import com.toyrobot.Utils.Command;
import com.toyrobot.Utils.CompassDirection;
import com.toyrobot.Utils.Position;
import com.toyrobot.Utils.TableTop;
import com.toyrobot.Utils.RobotMovementHandler;

public class toyRobotHandler {

	TableTop tableTop;
	RobotMovementHandler robot;

	public toyRobotHandler(TableTop tableTop, RobotMovementHandler robot) {
		this.tableTop = tableTop;
		this.robot = robot;
	}

	/**
	 * Places a toy robot moving on a square tabletop, of dimensions 5 units x 5
	 * units. in position X,Y and can face NORTH, EAST, WEST and SOUTH *
	 */
	public boolean placeToyRobot(Position position) throws RobotException {

		if (tableTop == null)
			throw new RobotException("Please check dimension of table top ! ");

		if (position == null)
			throw new RobotException("Position entered is invalid.");

		if (position.getDirection() == null)
			throw new RobotException("Direction entered is invalid.");

		// validate the position
		if (!tableTop.isValidPosition(position))
			return false;

		// if position is valid then assign values to fields
		robot.setPosition(position);
		return true;
	}

	/**
	 * Evals and executes a string command.
	 *
	 * @param inputString
	 *            command string
	 * @return string value of the executed command
	 * @throws com.toyrobot.RobotException.ToyRobotExceptionHandler
	 *
	 */
	public String move(String inputString) throws RobotException {
		String[] args = inputString.toUpperCase().split(" ");

		// validate command
		Command command;
		try {
			command = Command.valueOf(args[0]);
		} catch (IllegalArgumentException e) {
			throw new RobotException("Command is invalid, Valid are PLACE, MOVE, LEFT, RIGHT, REPORT.");
		}
		if (command == Command.PLACE && args.length < 2) {
			throw new RobotException(
					"Command is invalid or less number of arguments are passed.Right format : PLACE X,Y,FACING");
		}

		// validate PLACE params
		String[] params;
		int x = 0;
		int y = 0;
		CompassDirection commandDirection = null;
		if (command == Command.PLACE) {
			params = args[1].split(",");
			try {
				x = Integer.parseInt(params[0]);
				y = Integer.parseInt(params[1]);
				commandDirection = CompassDirection.valueOf(params[2]);
			} catch (Exception e) {
				throw new RobotException("Invalid command");
			}
		}

		String output;

		switch (command) {
		case PLACE:
			output = String.valueOf(placeToyRobot(new Position(x, y, commandDirection)));
			break;
		case MOVE:
			Position newPosition = robot.getPosition().getNextPosition();
			if (!tableTop.isValidPosition(newPosition))
				output = String.valueOf(false);
			else
				output = String.valueOf(robot.move(newPosition));
			break;
		case LEFT:
			output = String.valueOf(robot.rotateLeft());
			break;
		case RIGHT:
			output = String.valueOf(robot.rotateRight());
			break;
		case REPORT:
			if (report() != null)
				output = report();
			else {
				output = "";
				System.out.println("There are no initial co-ordinates set for toy. ");
			}
			break;
		default:
			throw new RobotException("Invalid command");
		}

		return output;
	}

	/**
	 * Returns the X,Y and Direction of the robot
	 */
	public String report() {
		if (robot.getPosition() == null)
			return null;

		return robot.getPosition().getX() + "," + robot.getPosition().getY() + ","
				+ robot.getPosition().getDirection().toString();
	}
}
