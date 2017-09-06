package com.toyrobot.Utils;

import com.toy.robot.RobotException;

public class RobotMovementHandler {

    private Position position;

    public RobotMovementHandler() {
    }

    public RobotMovementHandler(Position position) {
        this.position = position;
    }

    public boolean setPosition(Position position) {
        if (position == null)
            return false;

        this.position = position;
        return true;
    }

    public boolean move() throws RobotException {
        return move(position.getNextPosition());
    }

    /**
     * Moves the robot one step forward in currently facing direction
     *
    */
    public boolean move(Position newPosition) {
        if (newPosition == null)
            return false;
        this.position = newPosition;
        return true;
    }

    public Position getPosition() {
        return this.position;
    }

    /**
     * Rotates the robot 90 degrees LEFT
     */
    public boolean rotateLeft() {
        if (this.position.direction == null)
            return false;

        this.position.direction = this.position.direction.leftDirection();
        return true;
    }

    /**
     * Rotates the robot 90 degrees RIGHT
     *
     */
    public boolean rotateRight() {
        if (this.position.direction == null)
            return false;

        this.position.direction = this.position.direction.rightDirection();
        return true;
    }

}
