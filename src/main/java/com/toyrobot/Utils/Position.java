package com.toyrobot.Utils;

import com.toy.robot.RobotException;

public class Position {
    int x;
    int y;
    CompassDirection direction;

    public Position(Position position) {
        this.x = position.getX();
        this.y = position.getY();
        this.direction = position.getDirection();
    }

    public Position(int x, int y, CompassDirection direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public CompassDirection getDirection() {
        return this.direction;
    }

    public void setDirection(CompassDirection direction) {
        this.direction = direction;
    }

    public void change(int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }

    public Position getNextPosition() throws RobotException {
        if (this.direction == null)
            throw new RobotException("Invalid robot direction");

        Position newPosition = new Position(this);
        switch (this.direction) {
            case NORTH:
                newPosition.change(0, 1);  //move to y-axis, where x will be zero  (0,1)
                break;
            case EAST:
                newPosition.change(1, 0); //move to x-axis, where y will be zero (1,0)
                break;
            case SOUTH:
                newPosition.change(0, -1); 
                break;
            case WEST:
                newPosition.change(-1, 0);
                break;
        }
        return newPosition;
    }
}
