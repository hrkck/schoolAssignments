package shapes2d;

import java.util.ArrayList;

public class DrawingV2 {
	
	ArrayList<Object> shapes = new ArrayList<Object>();
	
	public void addShape(Object shape){
		shapes.add(shape);
	}
		
	public double calculateTotalArea(){
		double totalArea = 0;
		
		for (Object shape : shapes){
			
			if (shape instanceof Circle){
				totalArea += ((Circle)shape).area();
			}
			else if (shape instanceof Rectangle){
				totalArea += ((Rectangle)shape).area();
			}
			else if (shape instanceof Square){
				totalArea += ((Square)shape).area();
			}
		}
		
					
		return totalArea;
	}
}
