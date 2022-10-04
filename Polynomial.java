import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial {
	double[] coeff;
	int[] expo;
	public Polynomial() {
		coeff = new double[1];
		expo = new int[1];
	}
	public Polynomial(double[] coeff, int[] expo) {
		this.coeff = new double[coeff.length];
		for(int i = 0; i < coeff.length; i++) {
			this.coeff[i] = coeff[i];
		}
		this.expo = new int[expo.length];
		for(int i = 0; i < expo.length; i++) {
			this.expo[i] = expo[i];
		}
	}
	public Polynomial(File text) {
		Scanner file;
		String whole = "";
		try {
			file = new Scanner(text);
			whole = file.nextLine();
		} catch (FileNotFoundException e) {}

		int size = 1;
		for(int i = 0; i < whole.length(); i++) {
			if(whole.charAt(i) == '-' || whole.charAt(i) == '+') {
				size++;
			}
		}
		
		double[] coeff = new double[size];
		int[] expo = new int[size];
		int pos = 0;
		
		boolean atExpo = false;
		String number = "";
		for(int i = 0; i < whole.length(); i++) {
			if(whole.charAt(i) == 'x') {
				coeff[pos] = Double.parseDouble(number);
				number = "";
				atExpo = true;
			}
			else if(whole.charAt(i) == '+' || whole.charAt(i) == '-') {
				if(!number.equals("")) {
					if(atExpo) {
						expo[pos] = Integer.parseInt(number);
						atExpo = false;
					}
					else {
						coeff[pos] = Double.parseDouble(number);
						expo[pos] = 0;
					}
					
					pos++;
				}

				if(whole.charAt(i) == '-') {number = "-";}
				else {number = "";}
			}
			else {
				number += whole.charAt(i);
			}
		}
		if(atExpo) {
			expo[pos] = Integer.parseInt(number);
		}
		else {
			coeff[pos] = Double.parseDouble(number);
			expo[pos] = 0;
		}
		this.coeff = coeff.clone();
		this.expo = expo.clone();
	}
	
	public Polynomial add(Polynomial p2) {
		double[] coeff1 = coeff.clone();
		double[] coeff2 = p2.coeff.clone();
		int[] expo2 = p2.expo.clone();
		
		int slots = expo.length + p2.expo.length;
		for(int i = 0; i < expo.length; i++) {
			for(int j = 0; j < p2.expo.length; j++) {
				if(expo[i] == p2.expo[j]) {
					slots--;
					expo2[j]=-1;
					coeff1[i] += coeff2[j];
					if(coeff1[i] == 0) {
						slots--;
					}
				}
			}
		}
		
		if(slots == 0) {return new Polynomial();}
		
		double[] totalCoeff = new double[slots];
		int[] totalExpo = new int[slots];
		int totalPos = 0;
		
		for(int pos1 = 0; pos1 < expo.length; pos1++) {
			if(coeff1[pos1] != 0) {
				totalCoeff[totalPos] = coeff1[pos1];
				totalExpo[totalPos] = expo[pos1];
				totalPos++;
			}
		}
		for(int pos2 = 0; pos2 < p2.expo.length; pos2++) {
			if(expo2[pos2] != -1) {
				totalCoeff[totalPos] = p2.coeff[pos2];
				totalExpo[totalPos] = p2.expo[pos2];
				totalPos++;
			}
		}

		return new Polynomial(totalCoeff, totalExpo);
	}

	public double evaluate(double x) {
		double total = 0;
		for(int i = 0; i < expo.length; i++) {
			total += coeff[i]*Math.pow(x, expo[i]);
		}
		return total;
	}

	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
	}
	
	public Polynomial multiply(Polynomial p2) {
		double[] coeff1 = new double[p2.coeff.length];
		int[] expo1 = new int[p2.expo.length];
		double[] coeff2 = new double[p2.coeff.length];
		int[] expo2 = new int[p2.expo.length];
		
		for(int j = 0; j < p2.expo.length; j++) {
			coeff1[j] = coeff[0] * p2.coeff[j];
			expo1[j] = expo[0] + p2.expo[j];
		}
		Polynomial np1 = new Polynomial(coeff1, expo1);
		
		for(int i = 1; i < expo.length; i++) {
			for(int j = 0; j < p2.expo.length; j++) {
				coeff2[j] = coeff[i] * p2.coeff[j];
				expo2[j] = expo[i] + p2.expo[j];
			}
			Polynomial np2 = new Polynomial(coeff2, expo2);
			np1 = np1.add(np2);
		}
		
		return np1;
	}
	public void saveToFile(String fileName) {
	
		String text = "";
		boolean first = false;
		
		for(int i = 0; i < coeff.length; i++) {
			if(coeff[i]!=0) {
				if(first && coeff[i] >= 0) {
					text += "+";
				}
				first = true;
				text += Double.toString(coeff[i]);
				if(expo[i] != 0) {text += "x" + Integer.toString(expo[i]);}	
			}
		}
		
		File output = new File(fileName);
		try {
			output.createNewFile();
		} catch (IOException e) {
			System.out.println("Create File Failed");
		}
		
		FileWriter filewrite;
		try {
			filewrite = new FileWriter(fileName);
			if(text.equals("")) {text = "0";}
			filewrite.write(text);
			filewrite.close();
		} catch (IOException e) {
			System.out.println("Write File Failed");
		}
	}
}
