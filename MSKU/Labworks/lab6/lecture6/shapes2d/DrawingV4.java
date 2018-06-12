package shapes2d;

import java.util.ArrayList;

public class DrawingV4 {
	
	ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	
	public void addShape(Drawable drawable){
		drawables.add(drawable);
	}
		
	public double calculateTotalArea(){
		double totalArea = 0;
		
		for (Drawable drawable : drawables){
			if (drawable instanceof Shape){
				totalArea += ((Shape)drawable).area();
			}
		}
					
		return totalArea;
	}
	
	public void draw(){
		for(Drawable d: drawables){
			d.draw();
		}
	}
}
