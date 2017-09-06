package test;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import com.toyrobot.Utils.Position;
import com.toyrobot.Utils.TableTop;

public class TableTopTest {

    @Test
    public void testIsValidPosition() throws Exception {
        Position mockPosition = mock(Position.class);
        TableTop tableTop = new TableTop(4, 5);
        
        when(mockPosition.getX()).thenReturn(7);
        when(mockPosition.getY()).thenReturn(8);    
        Assert.assertFalse(tableTop.isValidPosition(mockPosition));


        when(mockPosition.getX()).thenReturn(2);
        when(mockPosition.getY()).thenReturn(3);
        Assert.assertTrue(tableTop.isValidPosition(mockPosition));


        when(mockPosition.getX()).thenReturn(-1);
        when(mockPosition.getY()).thenReturn(-1);
        Assert.assertFalse(tableTop.isValidPosition(mockPosition));
    }

}
