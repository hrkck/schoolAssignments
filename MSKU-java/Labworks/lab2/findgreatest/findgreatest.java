public class findgreatest {
	public static void main (String[] args){
		int max=0;
		for (int i=0; i<args.length; i++){
			int current= Integer.parseInt(args[i]);
			if(current>max){
				max=current;
			}
		}

	System.out.println(max);
	}

}