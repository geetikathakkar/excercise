package test;
import org.junit.Assert;
import org.junit.Test;

import com.toy.robot.RobotException;
import com.toy.robot.toyRobotHandler;
import com.toyrobot.Utils.CompassDirection;
import com.toyrobot.Utils.Position;
import com.toyrobot.Utils.RobotMovementHandler;
import com.toyrobot.Utils.TableTop;

public class ToyRobotHandlerTest {

    @Test
    public void testPlacing() throws RobotException {

        TableTop board = new TableTop(5, 5);
        RobotMovementHandler toyRobot = new RobotMovementHandler();
        toyRobotHandler robotHandler = new toyRobotHandler(board, toyRobot);

        Assert.assertTrue(robotHandler.placeToyRobot(new Position(0, 1, CompassDirection.NORTH)));
        Assert.assertTrue(robotHandler.placeToyRobot(new Position(2, 2, CompassDirection.SOUTH)));
        Assert.assertFalse(robotHandler.placeToyRobot(new Position(6, 6, CompassDirection.WEST)));
        Assert.assertFalse(robotHandler.placeToyRobot(new Position(-1, 5, CompassDirection.EAST)));
    }

    @Test
    public void testEval() throws RobotException {

    	TableTop board = new TableTop(5, 5);
        RobotMovementHandler toyRobot = new RobotMovementHandler();
        toyRobotHandler robotHandler = new toyRobotHandler(board, toyRobot);

        robotHandler.move("PLACE 0,0,NORTH");
        Assert.assertEquals("0,0,NORTH", robotHandler.move("REPORT"));

        robotHandler.move("MOVE");
        robotHandler.move("LEFT");
        Assert.assertEquals("0,1,WEST", robotHandler.move("REPORT"));

         //rotate on itself
        for (int i = 0; i < 4; i++)
        	robotHandler.move("LEFT");
        Assert.assertEquals("0,1,WEST", robotHandler.move("REPORT"));
        
       //Any move that causes the robot to fall is ignored. 
        robotHandler.move("LEFT");
        robotHandler.move("MOVE");
        robotHandler.move("MOVE");
        Assert.assertEquals("0,0,SOUTH", robotHandler.move("REPORT"));
      
    }
}
