public class sentencereverser{
	
	public static void main(String[] args){
		reverser(args.length-1,args);
	
	}

	///For the sake of God, I could finally write these six lines of code ( in python, we trust ):

	public static int reverser(int start,String[] args){
		System.out.println(args[start]);
		if (start==0)
			return start;		
		else	
			return reverser(start-1,args);
				
	}	

}

