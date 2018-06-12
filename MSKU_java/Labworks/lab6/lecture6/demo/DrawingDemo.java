package demo;

import shapes2d.Circle;
import shapes2d.Drawing;
import shapes2d.DrawingV2;
import shapes2d.DrawingV3;
import shapes2d.Rectangle;
import shapes2d.Square;

public class DrawingDemo {

	public static void main(String[] args) {
		DrawingV3 drawing = new DrawingV3();
		System.out.println("Total area: " + drawing.calculateTotalArea());
		
		drawing.addShape(new Circle(5));
		System.out.println("Total area: " + drawing.calculateTotalArea());
		
		drawing.addShape(new Rectangle(5,6));
		System.out.println("Total area: " + drawing.calculateTotalArea());

		drawing.addShape(new Square(5));
		System.out.println("Total area: " + drawing.calculateTotalArea());

	}

}
