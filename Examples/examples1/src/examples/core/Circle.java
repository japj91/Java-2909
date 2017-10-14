package examples.core;

public class Circle extends Shape {
	private double radius;

	public Circle() {
	}

	public Circle(double radius) {
		this.radius = radius;
	}

	/** Return radius */
	public double getRadius() {
		return radius;
	}

	/** Set a new radius */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/** Return area */
	public double getArea() {
		return radius * radius * Math.PI;
	}

	/** Return diameter */
	public double getDiameter() {
		return 2 * radius;
	}

	/** Return perimeter */
	public double getPerimeter() {
		return 2 * Math.PI * radius;
	}

	/* Print the circle info */
	public void printCircle() {
		String date = getDateCreated();
		double radius = getRadius();
		double perimeter = getPerimeter();
		double area = getArea();
		// to fix this, change %c to %s
		System.out.format("The circle created on %c has a radius of %.2f cm, a circumference of %.2f cm and an area of %.2f square cm%n", date,
				radius, perimeter, area);
	}

}
