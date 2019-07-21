package jft;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class Task3 {

    private Point a = new Point(1, 0);
    private Point b = new Point(1, 3);

    @Test
    public void test1(){
        assertEquals(a.distance(b), 3.0);
    }

    @Test
    public void test2(){
        assertNotEquals(a.distance(b), 3);
    }

    @Test
    public void incorrectTest(){
        assertEquals(a.distance(b), 4);
    }
}
