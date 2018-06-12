public class FindGreatestRec{
	
	public static void main(String[] args){
		System.out.println("Greatest of given numbers is " + findMax(0,args));
	}
	
	public static int findMax(int startIndex, String[] args){
		
		int current = Integer.parseInt(args[startIndex]);
		
		if (startIndex == args.length-1)
			return current;
		else{
			int maxRest = findMax(startIndex+1,args);
			if(current > maxRest)
				return current;
			else
				return maxRest;
		}
	}
}