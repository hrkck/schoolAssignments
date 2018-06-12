
public class Rectangle {
	
	double width;
	double height;
	Rectangle(double w, double h){
		width=w;
		height=h;
	}
	public double calculatePerimeter(){
		return 2*width+2*height;
		
	}
	public double calculateArea(){
		return height*width;
		
	}

}
