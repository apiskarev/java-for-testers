import jft.task1.Point;

public class FirstTask{

	public static void main(String[] args) {
		Point a = new Point(2, 6);
		Point b = new Point(7, 5);
		System.out.println("Расстояние между точкой а("+ a.x + "," + a.y +") и точкой б("+ b.x + "," + b.y +") = " + Point.distance(a, b));
	}

	
}