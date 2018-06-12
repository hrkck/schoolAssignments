package shapes2d;



public class Circle extends Shape {
	private double radius;
	
	public Circle(){
		radius = 1;
	}
	
	public Circle(double radius){
		this.radius = radius;
	}

	public double area(){
		return Math.PI * Math.pow(radius, 2);
	}

	public double perimeter(){
		return 2 * Math.PI * radius;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}
