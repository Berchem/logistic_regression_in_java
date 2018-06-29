

public class core {
	
	public static double exp(double x) {
		return Math.exp(x);
	}
	
	public static double sigmoid(double x) {
		return 1 / (1 + exp(-x));
	}
	
	public static double[] sigmoid(double[] x){
		int len = x.length;
		double[] y = new double[len];
		for (int i = 0; i < len; i++)
			y[i] = sigmoid(x[i]);
		return y;
	}
	
	public static double[] sigmoid_prime(double[] x){
		int len = x.length;
		double[] y = new double[len];
		for (int i = 0; i < len; i++)
			y[i] = sigmoid(x[i]) * (1 - sigmoid(x[i]));
		return y;
	}
	
	public static double log_likelihood_i(double[] x_i, double y_i, double[] w) {
		double l_i = Math.log(sigmoid(y_i * Matrix.dot(x_i, w)));
		return l_i;
	}
	
	public static double log_likelihood(double[][] x, double[] y, double[] w) {
		double l = 0;
		for (int i = 0; i < x.length; i++)
			l += log_likelihood_i(x[i], y[i], w);
		return l;
	}
	
	public static double[][] hessian(double[][] x, double[][] A, double[][] lambda){
		double[][] H = Matrix.plus(Matrix.dot(Matrix.dot(Matrix.transpose(x), A), x), lambda);
		return H;
	}
	
	public static double[] linspace(double start, double end, int n) {
		double[] list = new double[n];
		for (int i = 0; i < n; i++)
			list[i] = start + (double)(end - start) / (n - 1) * i;
		return list;
	}
	
	public static double[] linspace(double start, double end) {
		return linspace(start, end, 50);
	}
	
	public static double[] ones(int n) {
		double[] onearray = new double[n];
		for (int i = 0; i < n; i++)
			onearray[i] = 1.0;
		return onearray;
	}
	
	public static double[] ones(int n, double x) {
		double[] onearray = new double[n];
		for (int i = 0; i < n; i++)
			onearray[i] = x;
		return onearray;
	}
	
	class LogisticRegression{
		double tol = 1e-8;
		int lim = 100;
		double lambda = 1.0;
		double[][] x;
		double[] y;
		boolean fit_intercept = true;
		
		public LogisticRegression() {
			
		}
		
		public LogisticRegression(double[][] x, double[] y) {
			this.x = _reshape_x(x);
			this.y = _rescale_y(y);
		}
		
		public LogisticRegression(double[][] x, double[] y, boolean fit_intercept) {
			if (fit_intercept)
				this.x = _reshape_x(x);
			else
				this.x = x;
			this.y = _rescale_y(y);
		}
		
		private double[][] _reshape_x(double[][] X){
			int m = X.length;
			int n = X[0].length;
			double[][] Xnew = new double[m][n + 1];
			for (int i = 0; i < m; i++) {
				Xnew[i][0] = 1.0;
				for (int j = 0; j < n; j++)
					Xnew[i][j + 1] = X[i][j]; 
			}
			return Xnew;
		}
		
		private double[] _rescale_y(double[] Y){
			int m = Y.length;
			double[] Ynew = new double[m];
			for (int i = 0; i < m; i++) 
				Ynew[i] = (Y[i] - 0.5) * 2; 
			return Ynew;
		}
	}

}
