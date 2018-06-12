package shapes2d;

public class Rectangle extends Shape{
	
	double width;
	double height;

	public Rectangle(double w, double h){
		width = w;
		height = h;
	}
	
	public double perimeter(){
		return 2 * (height + width);
	}
	
	public double area(){
		return height * width;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}
