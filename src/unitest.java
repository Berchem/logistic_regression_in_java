/*
 * @author: Berchem Lin
 * @version: v1.0, 29 Jun 2018
 * 
 * These class were a side project during my Java course 
 * 
*/

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

class content_variables{
	// declare variables
	static double[][] data = null;
	static int m;
	static int n;
	static double[] y;
	static double[][] x;
	static double lambda;
	static int lim;
	static boolean fit_intercept;
	static double tol;
	static importData load = new importData();
	static algorithm.newton lr = null;
}

public class unitest extends content_variables {
	
	static void show1darray(double[] A) {
		for (int i = 0; i < A.length; i++)
			System.out.printf("%.4f%s", A[i], (i == A.length - 1) ? "\n" : ", ");
	}
	
	static void show2darray(double[][] A) {
		for (int i = 0; i < A.length; i++)
			for (int j = 0; j < A[i].length; j++)
				System.out.printf("%.2f%s", A[i][j], (j == A[i].length - 1) ? "\n" : ", ");
	}

	static algorithm.newton fit(double[][] x, double[] y){
		algorithm alg = new algorithm();
		algorithm.newton lr = alg.new newton(x, y);
		return lr;
	}
	
	static algorithm.newton fit(double[][] x, double[] y, double lambda){
		algorithm alg = new algorithm();
		algorithm.newton lr = alg.new newton(x, y, lambda);
		return lr;
	}
	
	static algorithm.newton fit(double[][] x, double[] y, double lambda, int lim){
		algorithm alg = new algorithm();
		algorithm.newton lr = alg.new newton(x, y, lambda, lim);
		return lr;
	}
	
	static algorithm.newton fit(double[][] x, double[] y, double lambda, int lim, boolean fit_intercept){
		algorithm alg = new algorithm();
		algorithm.newton lr = alg.new newton(x, y, lambda, lim, fit_intercept);
		return lr;
	}

	static algorithm.newton fit(double[][] x, double[] y, double lambda,  int lim, boolean fit_intercept, double tol){
		algorithm alg = new algorithm();
		algorithm.newton lr = alg.new newton(x, y, lambda, lim, fit_intercept, tol);
		return lr;
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException  {
		//define source and distribution		
		String src = args[0];
		String dst = args[1];
		
		// importdata
		load.read(src);
		data = load.list2array();

		m = data.length;
		n = data[0].length;
		y = new double[m];
		x = new double[m][n - 1];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) 
				if (j == 0) 
					y[i] = data[i][j];
				else 
					x[i][j - 1] = data[i][j];
			
		// calculate
		switch (args.length) {			
		
		case 3:
			lambda = Double.parseDouble(args[2]);
			lr = fit(x, y, lambda);
			break;
			
		case 4:
			lambda = Double.parseDouble(args[2]);
			lim = Integer.parseInt(args[3]);
			lr = fit(x, y, lambda, lim);
			break;
			
		case 5:
			lambda = Double.parseDouble(args[2]);
			lim = Integer.parseInt(args[3]);
			fit_intercept = Boolean.parseBoolean(args[4]);
			lr = fit(x, y, lambda, lim, fit_intercept);
			break;
			
		case 6:
			lambda = Double.parseDouble(args[2]);
			lim = Integer.parseInt(args[3]);
			fit_intercept = Boolean.parseBoolean(args[4]);
			tol = Double.parseDouble(args[5]);
			lr = fit(x, y, lambda, lim, fit_intercept, tol);
			break;
		
		default:
			lr = fit(x, y);
			
		}
		
		System.out.print("\nweight : ");
		
		show1darray(lr.get_weight());
		load.write(lr.get_weight(),	dst);
		

	}

}
