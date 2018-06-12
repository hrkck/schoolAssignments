
public class Prism {
	double lenght;
	double widht;
	double height;
	Prism(double l, double w, double h){
		lenght=l;
		widht=w;
		height=h;
		
	}
	public double calculateArea(){
		return 2*lenght+2*widht+2*height;
	}
	public double calculateVolume(){
		return lenght*widht*height;
	}
}
