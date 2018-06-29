

public class algorithm extends core{
	
	class newton extends LogisticRegression{
		private double[] w1;
		private double[][] H;
			
		public newton() {
			
		}

		public newton(double[][] X, double[] Y) {
			super(X, Y);
			calculate(x, y);
		}
		
		public newton(double[][] X, double[] Y, double lambda) {
			super(X, Y);
			this.lambda = lambda;
			calculate(x, y);
		}
		
		public newton(double[][] X, double[] Y, double lambda, int lim) {
			super(X, Y);
			this.lambda = lambda;
			this.lim = lim;
			calculate(x, y);
		}

		public newton(double[][] X, double[] Y, double lambda, int lim, boolean fit_intercept) {
			super(X, Y, fit_intercept);
			this.lambda = lambda;
			this.lim = lim;
			calculate(x, y);
		}
		
		public newton(double[][] X, double[] Y, double lambda, int lim, boolean fit_intercept, double tol) {
			super(X, Y, fit_intercept);
			this.lambda = lambda;
			this.tol = tol;
			this.lim = lim;
			super.fit_intercept = fit_intercept;
			calculate(x, y);
		}
		
		private void calculate(double[][] x, double[] y) {
			int m = this.x.length;
			int n = this.x[0].length;
			if (m != y.length) throw new IllegalStateException("invalid dimensions");
			
			double[] w0 = new double[n];
			w1 = new double[n];
			H = new double[n][n];
			
			int times = 0;

			double[] a = new double[n];
			double[][] lambda = Matrix.diagflat(core.ones(n, this.lambda));
			double[][] A = new double[m][m];
			double[] z = new double[n];
			double[] XAz = new double[n];

			while (times < lim) {
				// update parameter
				w0 = w1;
				
				// calculate diagonal matrix, that a is the A_ii
				a = core.sigmoid_prime(Matrix.dot(x, w0));
				A = Matrix.diagflat(a);
				
				// Hessian matrix
				H = core.hessian(x,  A, lambda);
				
				// a part of gradient objective and combine previous iterated parameter
				z = Matrix.plus(Matrix.dot(x, w0), Matrix.element_divide(Matrix.element_dot(
								Matrix.sub(core.ones(m), core.sigmoid(Matrix.element_dot(y, Matrix.dot(x, w0)))), y), a));
				XAz = Matrix.dot(Matrix.dot(Matrix.transpose(x), A), z);
				
				// calculate current parameter
				w1 = Matrix.dot(Matrix.inv(H), XAz);
				for (int i = 0; i < w1.length; i++)
					System.out.printf("%.4f%s", w1[i], (i == w1.length-1) ? "\n" : "  ");
				
				if (Matrix.abs(Matrix.sub(w1, w0)) < tol) {
					break;
					
				}else {
					times++;
					
				}
			}
		}
		
		public double[] get_weight() {
			return this.w1;
		}
	}
}
