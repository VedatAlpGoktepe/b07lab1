public class Polynomial {
	double[] coeff;
	public Polynomial() {
		coeff = new double[1];
	}
	public Polynomial(double[] coeff) {
		this.coeff = new double[coeff.length];
		for(int i = 0; i < coeff.length; i++) {
			this.coeff[i] = coeff[i];
		}
	}

	public Polynomial add(Polynomial p2) {
		double[] totalCoeff;
		if(coeff.length <= p2.coeff.length) {
			totalCoeff = new double[p2.coeff.length];
			for(int i = 0; i < p2.coeff.length; i++) {
				totalCoeff[i] = p2.coeff[i];
			}
			for(int i = 0; i < coeff.length; i++) {
				totalCoeff[i] += coeff[i];
			}
		}
		else {
			totalCoeff = new double[coeff.length];
			for(int i = 0; i < coeff.length; i++) {
				totalCoeff[i] = coeff[i];
			}
			for(int i = 0; i < p2.coeff.length; i++) {
				totalCoeff[i] += p2.coeff[i];
			}
		}

		return new Polynomial(totalCoeff);
	}

	public double evaluate(double x) {
		double total = 0;
		for(int i = 0; i < coeff.length; i++) {
			double var = 1;
			for(int j = 0; j < i; j++) {
				var *= x;
			}
			total += coeff[i]*var;
		}

		return total;
	}

	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
	}
}