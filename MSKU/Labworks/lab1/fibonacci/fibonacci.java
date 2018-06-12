public class fibonacci{
	public static void main(String[] args){
		int a=1;
		int b=0;
		for(int count=1; count<100; count=a+b){
			System.out.println(count);
			b=a;
			a=count;
			
		}
	}
}