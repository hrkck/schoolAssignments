package shapes2d;

import java.util.ArrayList;

public class DrawingV3 {
	
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public void addShape(Shape shape){
		shapes.add(shape);
	}
		
	public double calculateTotalArea(){
		double totalArea = 0;
		
		for (Shape shape : shapes){
			totalArea += shape.area();
		}
		
					
		return totalArea;
	}
}
