package shapes2D;

public class Circle {
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
	
	public String toString(){
		return "radius= " + radius;
	}
	
	public boolean equals(Object obj){
		if (obj instanceof Circle){
			Circle circle = (Circle)obj;
			if (circle.radius == radius){
				return true;
			}
		}
		return false;
	}	
}
