package shapes2d;

import java.util.ArrayList;

public class Drawing {
	
	ArrayList<Circle> circles = new ArrayList<Circle>();
	ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	ArrayList<Square> squares = new ArrayList<Square>();
	
	public void addCircle(Circle c){
		circles.add(c);
	}
	
	public void addRectangle(Rectangle r){
		rectangles.add(r);
	}
	
	public void addSquare(Square s){
		squares.add(s);
	}	
	
	public double calculateTotalArea(){
		double totalArea = 0;
		
		for (Circle circle : circles){
			totalArea += circle.area();
		}
		
		for (Rectangle rect : rectangles){
			totalArea += rect.area();
		}
		
		for (Square sq : squares){
			totalArea += sq.area();
		}					
		return totalArea;
	}
}
