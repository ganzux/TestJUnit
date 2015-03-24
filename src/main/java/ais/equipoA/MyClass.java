package ais.equipoA;

public class MyClass {

	///////////////////////////////////////////////////////////////
	//                         Attributes                        //
	///////////////////////////////////////////////////////////////

	private int m;
	private int n;
	
	public static final String ERROR_M = "m debe ser un campo numérico positivo de 3 dígitos (1 - 999)";
	public static final String ERROR_N = "n debe ser un campo numérico positivo de 5 dígitos (1 - 99999)";
	
	// m: es un campo numérico obligatorio de 3 dígitos
	// n: es un campo numérico obligatorio de 5 dígitos
	public static final int LIMIT_M_TOP = 999;
	public static final int LIMIT_N_TOP = 99999;
	public static final int LIMIT_M_BOT = 1;
	public static final int LIMIT_N_BOT = 1;

	///////////////////////////////////////////////////////////////
	//                     End of the Attributes                 //
	///////////////////////////////////////////////////////////////




	///////////////////////////////////////////////////////////////
	//                           Methods                         //
	///////////////////////////////////////////////////////////////
	public void myMethod() throws Exception{

		if (m > LIMIT_M_TOP || m < LIMIT_M_BOT)
			throw new Exception(ERROR_M);
		if (n > LIMIT_N_TOP || n < LIMIT_N_BOT)
			throw new Exception(ERROR_N);

		int r;

		//  n > m => Intercambia sus valores
		if (n > m){
			r = m;
			m = n;
			n = r;
		}

		// r = resto de m / n
		r = m % n;

		// Mientras el resto no sea 0, sigue calculando el mismo
		// como el módulo del divisor entre el resto r = n % r
		while (r != 0){
			m = n;
			n = r;
			r = m % n;
		}

	}
	
	///////////////////////////////////////////////////////////////
	//                      End of Methods                       //
	///////////////////////////////////////////////////////////////




	///////////////////////////////////////////////////////////////
	//               Constructors, getters, setters...           //
	///////////////////////////////////////////////////////////////
	
	public MyClass(int m, int n){
		this.m = m;
		this.n = n;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}

	///////////////////////////////////////////////////////////////
	//           End of Constructors, getters, setters...        //
	///////////////////////////////////////////////////////////////

}
