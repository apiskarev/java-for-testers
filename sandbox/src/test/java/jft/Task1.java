package jft;

import jft.task1.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task1 {

    private Point a = new Point(1, 0);
    private Point b = new Point(1, 3);

    @Test
    public void test1(){
        Assert.assertEquals(Point.distance(a,b), 3.0);
    }

    @Test
    public void test2(){
        Assert.assertNotEquals(Point.distance(a,b), 3);
    }
}
