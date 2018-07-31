

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class importData {

	private String src;
	private String dst;
	private List<double[]> raw = new ArrayList<>();
	
	public importData() {
		
	}
	
    public void read(String filename) throws FileNotFoundException {
    	src = filename;
    	
    	List<String> list = new ArrayList<>();

        Scanner scanner = new Scanner(new File(src));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
        	String ele = scanner.next();
        	
        	if (ele.contains("\n")) {
        		String[] eles = ele.split("\n");
        		list.add(eles[0]);
        		int add_line = 0;
        		double[] line = new double[list.size()];
        		for (int j = 0; j < list.size(); j++)
        			try {
        				line[j] = Double.parseDouble(list.get(j));
        			}catch (Exception e) {
        				add_line++;
        				continue;
        			}
        		if (add_line == 0)
        			raw.add(line);
        		
        		list.clear();
        		try {
        			list.add(eles[1]);
        		}catch (ArrayIndexOutOfBoundsException e) {
        			System.out.println("finish reading");
        		}
        		
        	}else {
        		list.add(ele);
        	}

        }
        scanner.close();

    }

    public double[][] list2array(){

    	int m = raw.size();
    	int n = raw.get(0).length;
    	double[][] data = new double[m][n];
    	for (int i = 0; i < m; i++)
    		for (int j = 0; j < n; j++)
    			try {
    				data[i][j] = raw.get(i)[j];
    			}catch (Exception e) {
    				continue;
    			}
    	
    	return data;
    }

    public void write(double[] w, String filename) throws FileNotFoundException, UnsupportedEncodingException {
    	dst = filename;
    	String s = "";
    	for (double w_i: w)
    		s += w_i + "\t";
    	PrintWriter writer = new PrintWriter(dst, "UTF-8");
    	writer.println(s);
    	writer.close();
    }
}
