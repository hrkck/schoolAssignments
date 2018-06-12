
public class Sphere {
	double radius;
	
	Sphere(double r){
		radius=r;
		
	}
	public double calculateArea(){
		return 4*3.14*radius*radius;
	}
	public double calculateVolume(){
		return (4/3)*3.14*radius*radius*radius;
	}

}
