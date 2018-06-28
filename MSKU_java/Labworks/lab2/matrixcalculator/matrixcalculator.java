public class matrixcalculator{

	
	
	public static void main(String[] args){
		
		int[][] matrixA = {{6, 8, 2}, {9, 5, 11}, {7, 2, 5}};
		
		int[][] matrixB = {{4, 6, 3}, {5, 8, 1}, {6, 6, 7}}; 

		
	
		//add matrixA and matrixB and put the result to r
		
		int[][] r = add(matrixA,matrixB);

		

		//print the result
		
		for (int i =0; i<3; i++){
			
			for(int j = 0; j<3; j++){
				
				System.out.print(r[i][j] + " ");
			
			}
			
			System.out.println();
		
		}

	
	}

	
	//adds given two matrix
	
	public static int[][] add(int[][] matrixA, int[][] matrixB){

		
		int[][] result = new int[3][3];
		
		for (int i =0; i<3; i++){
			
			for(int j = 0; j<3; j++){
				
				result[i][j] = 	matrixA[i][j] + matrixB[i][j];
			
			}

		}

		return result;

	}


}
