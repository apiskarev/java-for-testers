package jft.task1;

public class Point {

    public int x;
    public int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static double distance(Point a, Point b){
        return Math.sqrt((b.x - a.x) * 2 + (b.y - a.y) * 2);
    }

}
