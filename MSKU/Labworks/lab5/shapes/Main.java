package shapes;

import othershapes.Box;

public class Main {

	public static void main(String[] args){
		Box box = new Box(5,6,10);
		System.out.println(box.volume());
		System.out.println(box.area());
		System.out.println(box.hashCode());
		System.out.println(box);
		
		Rectangle boxR = box;
		System.out.println(((Box)boxR).volume());
		
		Object obj = box;
		if (obj instanceof String){
			String str =((String)obj);
		}
		
		Rectangle rect = new Rectangle(5,6);
		System.out.println(rect);
		
		Rectangle rect1 = new Rectangle(5,6);
		
		if (rect.equals(rect1)){
			System.out.println("rect and rect1 have equal sizes");
		}
		
		Box box2 = new Box(5,6,10);

		if (box2.equals(box)){
			System.out.println("box2 and box have equal sizes");
		}
		
	}
	
	
}
