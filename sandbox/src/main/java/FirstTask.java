import jft.task1.Point;

public class FirstTask{

	public static void main(String[] args) {
		Point a = new Point(1, 0);
		Point b = new Point(1, 3);
		System.out.println("Расстояние между точкой а("+ a.x + "," + a.y +") и точкой б("+ b.x + "," + b.y +") = " + Point.distance(a, b));
	}

	
}