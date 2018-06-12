public class Circle{
	
	double radius;
	Circle(double r){
		radius=r;
	}
	public double calculatePerimeter(){
		return 2*3.14*radius;
		
	}
	public double calculateArea(){
		return 3.14*radius*radius;
	}
}