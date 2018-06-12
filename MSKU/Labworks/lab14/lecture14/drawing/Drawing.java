import java.util.ArrayList;
import java.util.List;

public class Drawing {

	List<Shape> shapes = new ArrayList<>();
	
	public void add(Shape s){
		shapes.add(s);
	}
	
	public int calculateTotalArea(){
		int sum= 0;
		for(Shape s: shapes){
			sum+= s.calculateArea();
		}
		return sum;
	}
}
