package jft;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Task3 {

    private Point a = new Point(1, 0);
    private Point b = new Point(1, 3);

    @Test
    public void test1(){
        Assert.assertEquals(a.distance(b), 3.0);
    }

    @Test
    public void test2(){
        Assert.assertNotEquals(a.distance(b), 3);
    }

    @Test
    public void incorrectTest(){
        Assert.assertNotEquals(a.distance(b), 4);
    }
}
