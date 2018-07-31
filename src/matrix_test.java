
public class matrix_test {

	static void show2darray(double[][] A) {
		for (int i = 0; i < A.length; i++)
			for (int j = 0; j < A[i].length; j++)
				System.out.printf("%.2f%s", A[i][j], (j == A[i].length - 1) ? "\n" : ", ");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] A = {{1, 2, 3}, {-6, 7, 8}, {3, 4, 5}};
		show2darray(A);
		show2darray(Matrix.inv(A));

	}

}
