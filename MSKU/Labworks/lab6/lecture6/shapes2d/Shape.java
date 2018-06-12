package shapes2d;

public abstract class  Shape implements Drawable{
	
	public abstract double area();

	public double perimeter(){
		return 0;	//default implementation
	}
	
}
