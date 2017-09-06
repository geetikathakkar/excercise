package com.toyrobot.Utils;


import java.util.HashMap;
import java.util.Map;

public enum CompassDirection {

    NORTH(0), EAST(1), SOUTH(2), WEST(3);
    private static Map<Integer, CompassDirection> map = new HashMap<Integer, CompassDirection>();

    static {
        for (CompassDirection directionEnum : CompassDirection.values()) {
            map.put(directionEnum.directionIndex, directionEnum);
        }
    }

    private int directionIndex;

    private CompassDirection(int direction) {
        this.directionIndex = direction;
    }

    public static CompassDirection valueOf(int directionNum) {
        return map.get(directionNum);
    }

    /**
     * Returns the direction on the left of the current one
     */
    public CompassDirection leftDirection() {
        return rotate(-1);
    }

    /**
     * Returns the direction on the right of the current one
     */
    public CompassDirection rightDirection() {
        return rotate(1);
    }

    private CompassDirection rotate(int step) {

        int newIndex = (this.directionIndex + step) < 0 ?
                map.size() - 1 :
                (this.directionIndex + step) % map.size();

        return CompassDirection.valueOf(newIndex);
    }

}
