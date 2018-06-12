package othershapes;

import shapes.Rectangle;

public class Box extends Rectangle{
	
	private double height;
	private String width;
	
	public Box(double width, double length, double height){
		super(width,length);
		this.height = height;
		
	}
	
	public void setDimension(double width, double length, double height){
		super.setDimension(width,length);
		this.height = height;	
		
	}
	
	public double volume(){
		return super.width * height * length;
	}

	public double area() {
		return super.area();
		//return 2 * (length * width + length * height + width*height);
	}
	
	public static void methodB(){
		methodA();
	}
	
	public String toString(){
		return super.toString() + " height= " + height;
	}	
	
	public boolean equals(Object obj) {
		if (obj instanceof Box) {
			Box box = (Box) obj;
			if (super.equals(box)&& this.height == box.height) {
				return true;
			}
		}
		return false;
	}	
}
