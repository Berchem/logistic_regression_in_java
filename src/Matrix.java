

class Matrix{
	
	static double[] plus(double[] A, double[] B) {
		int ma = A.length;
		int mb = B.length;
		if (ma != mb) throw new RuntimeException("Index Error");
		double[] C = new double[ma];
		for (int i = 0; i < ma; i++)
			C[i] = A[i] + B[i];
		return C;
	}
	
	static double[][] plus(double[][] A, double[][] B) {
		int ma = A.length;
		int na = A[0].length;
		int mb = B.length;
		int nb = B[0].length;
		if (ma != mb || na != nb) throw new RuntimeException("Index Error");
		double[][] C = new double[ma][na];
		for (int i = 0; i < ma; i++)
			for (int j = 0; j < na; j++)
				C[i][j] = A[i][j] + B[i][j];
		return C;
	}
	
	static double[] sub(double[] A, double[] B) {
		int ma = A.length;
		int mb = B.length;
		if (ma != mb) throw new RuntimeException("Index Error");
		double[] C = new double[ma];
		for (int i = 0; i < ma; i++)
			C[i] = A[i] - B[i];
		return C;
	}
	
	static double dot(double[] A, double[] B) {
		int ma = A.length;
		int mb = B.length;
		if (ma != mb) throw new RuntimeException("Index Error");
		double C = 0;
		for (int i = 0; i < ma; i++)
			C += A[i] * B[i]; 
		return C;
	}
	
	static double[] dot(double[][] A, double[] B) {
		int ma = A.length;
		int na = A[0].length;
		int mb = B.length;
		if (na != mb) throw new RuntimeException("Index Error");
		double[] C = new double[ma];
		for (int i = 0; i < ma; i++)
			for (int j = 0; j < na; j++)
					C[i] += A[i][j] * B[j]; 
		return C;
	}

	static double[][] dot(double[][] A, double[][] B) {
		int ma = A.length;
		int na = A[0].length;
		int mb = B.length;
		int nb = B[0].length;
		if (na != mb) throw new RuntimeException("Index Error");
		double[][] C = new double[ma][nb];
		for (int i = 0; i < ma; i++)
			for (int j = 0; j < nb; j++)
				for (int k = 0; k < na; k++)
					C[i][j] += A[i][k] * B[k][j]; 
		return C;
	}
	
	static double[][] transpose(double[][] A){
		int m = A.length;
		int n = A[0].length;
		double[][] AT = new double[n][m];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				AT[j][i] = A[i][j];
		return AT;
	}
	
	static double[][] diagflat(double[] A){
		int m = A.length;
		double[][] DiagA = new double[m][m];
		for (int i = 0; i < m; i++)
			DiagA[i][i] = A[i];
		return DiagA;
	}
	
	static double[] element_dot(double[] A, double[] B) {
		int na = A.length, nb = B.length;
		if (na != nb) throw new RuntimeException("Index Error");
		double[] C = new double[na];
		for (int i = 0; i < na; i++)
			C[i] = A[i] * B[i];
		return C;
	}
	
	static double[] element_divide(double[] A, double[] B) {
		int na = A.length, nb = B.length;
		if (na != nb) throw new RuntimeException("Index Error");
		double[] C = new double[na];
		for (int i = 0; i < na; i++)
			C[i] = A[i] / B[i];
		return C;
	}
	
	static double[][] inv(double[][] A) {
		int m = A.length, n = A[0].length;
		double[][] inverse = new double[m][n];
		//minors and cofactors
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (m > 2) {
					inverse[i][j] = Math.pow(-1, i + j) * determinant(minor(A, i, j));
				}else {
					if (i == j) {
						inverse[i][i] = Math.pow(-1, i + j) * A[1-i][1-i];
					}else {
						inverse[i][j] = Math.pow(-1, i + j) * A[j][i];
					}
				}
		//adjugate and determinant
		double det = 1.0 / determinant(A);
		for (int i = 0; i < inverse.length; i++) {
			for (int j = 0; j <= i; j++) {
				double temp = inverse[i][j];
				inverse[i][j] = inverse[j][i] * det;
				inverse[j][i] = temp * det;
			}
		}
		return inverse;
	}
	
	static double determinant(double[][] A) {
		if (A.length != A[0].length)
			throw new IllegalStateException("invalid dimensions");

		if (A.length == 2)
			return A[0][0] * A[1][1] - A[0][1] * A[1][0];

		double det = 0;
		for (int i = 0; i < A[0].length; i++)
			det += Math.pow(-1, i) * A[0][i] * determinant(minor(A, 0, i));
		return det;
	}
	
	static double[][] minor(double[][] A, int row, int column) {
		double[][] minor = new double[A.length - 1][A.length - 1];

		for (int i = 0; i < A.length; i++)
			for (int j = 0; i != row && j < A[i].length; j++)
				if (j != column)
					minor[i < row ? i : i - 1][j < column ? j : j - 1] = A[i][j];
		return minor;
	}
	
	static double abs(double[] A) {
		double len = 0;
		for (double ele: A)
			len += Math.pow(ele, 2);
		return Math.sqrt(len);
	}

} 
