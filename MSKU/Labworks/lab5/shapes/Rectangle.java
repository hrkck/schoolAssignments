package shapes;

public class Rectangle {
	protected double length;
	protected double width;

	public Rectangle() {
	}

	public Rectangle(double w, double l) {
		length = l;
		width = w;
	}

	protected double area() {
		return length * width;
	}

	public double perimeter() {
		return (2 * length) + (2 * width);
	}

	public double getLength() {
		return length;
	}

	public double getWidth() {
		return width;
	}

	public void setDimension(double length, double width) {
		this.length = length;
		this.width = width;

	}

	public static void methodA() {
	}

	public String toString() {
		return "width= " + width + " length= " + length;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle rect = (Rectangle) obj;
			if ((width == rect.width) && (length == rect.length)) {
				return true;
			}
		}
		return false;
	}
}
