class primefinder{
	public static void main(String[] args){
		String decleration = " is a prime number.";
		for(int number=1; number<100; number ++){
			int x=0;
			for(int divider=1; divider<=number; divider ++){
				if (number%divider==0){
					x++;
				}
			}
			if(x==2){
				System.out.println(number+decleration);
			}

		}

	}
}
