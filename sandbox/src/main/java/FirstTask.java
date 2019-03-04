import jft.task1.Point;

public class FirstTask{

	public static void main(String[] args) {
		Point a = new Point(3, 1);
		Point b = new Point(5, 9);
		System.out.println("Расстояние между точкой а("+ a.x + "," + a.y +") и точкой б("+ b.x + "," + b.y +") = " + Point.distance(a, b));
	}

	
}