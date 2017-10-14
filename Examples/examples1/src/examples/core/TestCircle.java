package examples.core;

public class TestCircle {

	public static void main(String[] args) {
		System.out.println("TestCircle start");
		Circle circle = new Circle();
		circle.setRadius(7);
		circle.printCircle();
		circle.setRadius(-1);
		circle.printCircle();
	}
}
