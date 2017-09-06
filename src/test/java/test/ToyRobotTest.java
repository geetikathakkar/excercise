package test;
import org.junit.Assert;
import org.junit.Test;

import com.toy.robot.RobotException;
import com.toyrobot.Utils.CompassDirection;
import com.toyrobot.Utils.Position;
import com.toyrobot.Utils.RobotMovementHandler;

public class ToyRobotTest {

    @Test
    public void testRobotMovement() throws RobotException {

    	RobotMovementHandler robot = new RobotMovementHandler(new Position(0, 0, CompassDirection.SOUTH));

        Assert.assertTrue(robot.move());
        Assert.assertTrue(robot.move());
        Assert.assertEquals(0, robot.getPosition().getX());
        Assert.assertEquals(-2, robot.getPosition().getY());
        Assert.assertEquals(CompassDirection.SOUTH, robot.getPosition().getDirection());


        robot.setPosition(new Position(1, 2, CompassDirection.EAST));
        robot.move();
        robot.move();
        robot.rotateLeft();
        robot.move();

        Assert.assertEquals(3, robot.getPosition().getX());
        Assert.assertEquals(3, robot.getPosition().getY());
        Assert.assertEquals(CompassDirection.NORTH, robot.getPosition().getDirection());

        robot.setPosition(new Position(0, 0, CompassDirection.NORTH));
        robot.rotateRight();
        Assert.assertEquals(CompassDirection.EAST, robot.getPosition().getDirection());
        robot.rotateRight();
        Assert.assertEquals(CompassDirection.SOUTH, robot.getPosition().getDirection());
        robot.rotateRight();
        Assert.assertEquals(CompassDirection.WEST, robot.getPosition().getDirection());
        robot.rotateRight();
        Assert.assertEquals(CompassDirection.NORTH, robot.getPosition().getDirection());
        robot.rotateRight();
        Assert.assertEquals(CompassDirection.EAST, robot.getPosition().getDirection());
    }

}