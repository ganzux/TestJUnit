package ais.equipoA;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class MyClassTest {

	///////////////////////////////////////////////////////////////
	//                       Public Methods                      //
	///////////////////////////////////////////////////////////////

	// Pruebas estructurales o de caja blanca: pasamos por todo el 
	// código

	/**
	 * Comprueba que los parámetros pueden ser pasados en cualquier orden.
	 * Concretamente está comprobando que pasa por if (n > m); para ello
	 * generamos valores aleatorios de m y n y llamamos al método permutando
	 * los valores m <-> n, teniendo que ser el resultado igual.
	 */
	@Test
	public void testMN_MN(){
		try {

			// Establecemos el límite en MyClass.LIMIT_M_TOP para que no salte la excepción de n
			// Para esta prueba hemos realizado un enfoque aleatorio
			int[] ms = getPositiveAleatNumbers(MyClass.LIMIT_M_BOT, MyClass.LIMIT_M_TOP, 100);
			int[] ns = getPositiveAleatNumbers(MyClass.LIMIT_M_BOT, MyClass.LIMIT_M_TOP, 100);
			
			for (int i=0;i<ms.length;i++){
				MyClass myClass1 = new MyClass(ms[i],ns[i]);
				myClass1.myMethod();
				int resultM1 = myClass1.getM();
				int resultN1 = myClass1.getN();
				
				MyClass myClass2 = new MyClass(ns[i],ms[i]);
				myClass2.myMethod();
				int resultM2 = myClass2.getM();
				int resultN2 = myClass2.getN();
				
				assertEquals(resultM1, resultM2);
				assertEquals(resultN1, resultN2);
			}
			
			for (int i = MyClass.LIMIT_M_BOT ; i < MyClass.LIMIT_M_TOP ; i++){
				for (int j = MyClass.LIMIT_M_BOT ; j < MyClass.LIMIT_M_TOP ; j++){
					MyClass myClass1 = new MyClass(i, j);
					myClass1.myMethod();
					int resultM1 = myClass1.getM();
					int resultN1 = myClass1.getN();
					
					MyClass myClass2 = new MyClass(j, i);
					myClass2.myMethod();
					int resultM2 = myClass2.getM();
					int resultN2 = myClass2.getN();
					
					assertEquals(resultM1, resultM2);
					assertEquals(resultN1, resultN2);
				}
			}

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Este test comprueba que pasa correctamente por el bucle
	 * while (r != 0), haciendo que la primera división que realiza
	 * el método tenga un resto mayor a cero. También comprobamos
	 * que no hay fallos al no pasar por el bucle, haciendo que la
	 * primera división tenga como resto 0.
	 */
	@Test
	public void testRest(){
		// El resto de dividir 20 entre 16 es 4
		try {
			(new MyClass(20, 16)).myMethod();
			assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		// El resto de dividir 10 entre 5 es 0
		try {
			(new MyClass(10, 5)).myMethod();
			assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}



	// Pruebas funcionales o de caja negra: vemos que las funcionalidades
	// son las correctas

	/**
	 * Comprueba que la longitud del parámetro M NO está comprendido entre
	 * los límites establecidos
	 */
	@Test
	public void testLengthM() {

		// m < min
		// m < min, n < min
		try {
			(new MyClass(MyClass.LIMIT_M_BOT - 1, MyClass.LIMIT_N_BOT - 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}

		// m < min, n = min
		try {
			(new MyClass(MyClass.LIMIT_M_BOT - 1, MyClass.LIMIT_N_BOT)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}

		// m < min, n = max
		try {
			(new MyClass(MyClass.LIMIT_M_BOT - 1, MyClass.LIMIT_N_TOP)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}

		// m < min, n > max
		try {
			(new MyClass(MyClass.LIMIT_M_BOT - 1, MyClass.LIMIT_N_TOP + 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}

		// m < min, n correcto
		try {
			(new MyClass(MyClass.LIMIT_M_BOT - 1, MyClass.LIMIT_N_TOP - 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}
		try {
			(new MyClass(MyClass.LIMIT_M_BOT - 1, MyClass.LIMIT_N_BOT + 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}
		try {
			(new MyClass(MyClass.LIMIT_M_BOT - 1, (MyClass.LIMIT_N_BOT + MyClass.LIMIT_N_TOP) / 2)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}



		// m > max
		// m > max, n < min
		try {
			(new MyClass(MyClass.LIMIT_M_TOP + 1, MyClass.LIMIT_N_BOT - 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}

		// m > max, n = min
		try {
			(new MyClass(MyClass.LIMIT_M_TOP + 1, MyClass.LIMIT_N_BOT)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}

		// m > max, n = max
		try {
			(new MyClass(MyClass.LIMIT_M_TOP + 1, MyClass.LIMIT_N_TOP)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}

		// m > max, n > max
		try {
			(new MyClass(MyClass.LIMIT_M_TOP + 1, MyClass.LIMIT_N_TOP + 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}

		// m > max, n correcto
		try {
			(new MyClass(MyClass.LIMIT_M_TOP + 1, MyClass.LIMIT_N_TOP - 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}
		try {
			(new MyClass(MyClass.LIMIT_M_TOP + 1, MyClass.LIMIT_N_BOT + 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}
		try {
			(new MyClass(MyClass.LIMIT_M_TOP + 1, (MyClass.LIMIT_N_BOT + MyClass.LIMIT_N_TOP) / 2)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_M));
		}

	}

	/**
	 * Comprueba que la longitud del parámetro N NO está comprendido entre
	 * los límites establecidos. En este caso el parámetro M debe ser siempre
	 * correcto porque si no saltaría antes esa excepción, que ha sido probada
	 * en el test testLengthM.
	 */
	@Test
	public void testLengthN() {

		// n < min, n correcto
		try {
			(new MyClass(MyClass.LIMIT_M_BOT, MyClass.LIMIT_N_BOT - 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_N));
		}
		try {
			(new MyClass(MyClass.LIMIT_M_BOT, MyClass.LIMIT_N_TOP + 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_N));
		}
		try {
			(new MyClass(MyClass.LIMIT_M_BOT + 1, MyClass.LIMIT_N_BOT - 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_N));
		}
		try {
			(new MyClass(MyClass.LIMIT_M_BOT + 1, MyClass.LIMIT_N_TOP + 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_N));
		}
		try {
			(new MyClass((MyClass.LIMIT_M_BOT + MyClass.LIMIT_M_TOP) / 2, MyClass.LIMIT_N_BOT - 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_N));
		}
		try {
			(new MyClass((MyClass.LIMIT_M_BOT + MyClass.LIMIT_M_TOP) / 2, MyClass.LIMIT_N_TOP + 1)).myMethod();
			fail("Aquí no debería llegar nunca, m debe ser correcto");
		} catch (Exception e) {
			assertTrue("OK", e.getMessage().equals(MyClass.ERROR_N));
		}

	}

	/**
	 * Comprueba que los valores de m y n están comprendidos entre los 
	 * límites establecidos. 5 posibilidades de M x 5 de N = 25 posibilidades.
	 */
	public void testMAndNAreOk(){

		try {
			// m = min
			(new MyClass(MyClass.LIMIT_M_BOT, MyClass.LIMIT_N_BOT)).myMethod();
			(new MyClass(MyClass.LIMIT_M_BOT, MyClass.LIMIT_N_BOT + 1)).myMethod();
			(new MyClass(MyClass.LIMIT_M_BOT, MyClass.LIMIT_N_TOP)).myMethod();
			(new MyClass(MyClass.LIMIT_M_BOT, MyClass.LIMIT_N_TOP - 1)).myMethod();
			(new MyClass(MyClass.LIMIT_M_BOT, (MyClass.LIMIT_N_BOT + MyClass.LIMIT_N_TOP ) / 2)).myMethod();

			// m = max
			(new MyClass(MyClass.LIMIT_M_TOP, MyClass.LIMIT_N_BOT)).myMethod();
			(new MyClass(MyClass.LIMIT_M_TOP, MyClass.LIMIT_N_BOT + 1)).myMethod();
			(new MyClass(MyClass.LIMIT_M_TOP, MyClass.LIMIT_N_TOP)).myMethod();
			(new MyClass(MyClass.LIMIT_M_TOP, MyClass.LIMIT_N_TOP - 1)).myMethod();
			(new MyClass(MyClass.LIMIT_M_TOP, (MyClass.LIMIT_N_BOT + MyClass.LIMIT_N_TOP ) / 2)).myMethod();

			// m = min +1
			(new MyClass(MyClass.LIMIT_M_BOT + 1, MyClass.LIMIT_N_BOT)).myMethod();
			(new MyClass(MyClass.LIMIT_M_BOT + 1, MyClass.LIMIT_N_BOT + 1)).myMethod();
			(new MyClass(MyClass.LIMIT_M_BOT + 1, MyClass.LIMIT_N_TOP)).myMethod();
			(new MyClass(MyClass.LIMIT_M_BOT + 1, MyClass.LIMIT_N_TOP - 1)).myMethod();
			(new MyClass(MyClass.LIMIT_M_BOT + 1, (MyClass.LIMIT_N_BOT + MyClass.LIMIT_N_TOP ) / 2)).myMethod();

			// m = max - 1
			(new MyClass(MyClass.LIMIT_M_TOP - 1, MyClass.LIMIT_N_BOT)).myMethod();
			(new MyClass(MyClass.LIMIT_M_TOP - 1, MyClass.LIMIT_N_BOT + 1)).myMethod();
			(new MyClass(MyClass.LIMIT_M_TOP - 1, MyClass.LIMIT_N_TOP)).myMethod();
			(new MyClass(MyClass.LIMIT_M_TOP - 1, MyClass.LIMIT_N_TOP - 1)).myMethod();
			(new MyClass(MyClass.LIMIT_M_TOP - 1, (MyClass.LIMIT_N_BOT + MyClass.LIMIT_N_TOP ) / 2)).myMethod();

			// m = (min + max) / 2
			(new MyClass((MyClass.LIMIT_M_BOT + MyClass.LIMIT_M_TOP ) / 2, MyClass.LIMIT_N_BOT)).myMethod();
			(new MyClass((MyClass.LIMIT_M_BOT + MyClass.LIMIT_M_TOP ) / 2, MyClass.LIMIT_N_BOT + 1)).myMethod();
			(new MyClass((MyClass.LIMIT_M_BOT + MyClass.LIMIT_M_TOP ) / 2, MyClass.LIMIT_N_TOP)).myMethod();
			(new MyClass((MyClass.LIMIT_M_BOT + MyClass.LIMIT_M_TOP ) / 2, MyClass.LIMIT_N_TOP - 1)).myMethod();
			(new MyClass((MyClass.LIMIT_M_BOT + MyClass.LIMIT_M_TOP ) / 2, (MyClass.LIMIT_N_BOT + MyClass.LIMIT_N_TOP ) / 2)).myMethod();
			
			assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/*
	 * El siguiente test comprueba algunos de los resultados que 
	 * debería dar el método a probar 
	 */
	@Test
	public void testResult(){
		try {
			// Cualquier número cuyo resto sea 0, al llamar a myMethod,
			// no debe cambiar los valores de M y N
			for (int i = 1 ; i <= MyClass.LIMIT_N_TOP ; i++){
				for (int j = 1 ; j <= MyClass.LIMIT_M_TOP ; j++){
					if (j % i == 0){
						MyClass myClass = new MyClass(i, j);
						myClass.myMethod();
						assertEquals(j, myClass.getM());
						assertEquals(i, myClass.getN());
					}
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	///////////////////////////////////////////////////////////////
	//                     End of Public Methods                 //
	///////////////////////////////////////////////////////////////




	///////////////////////////////////////////////////////////////
	//                       Private Methods                     //
	///////////////////////////////////////////////////////////////
	private int[] getPositiveAleatNumbers(int min, int limit, int size) throws InterruptedException{

		int[] numbres = new int[size];

		for (int i=0;i<size;i++){
			Random aRandom = new Random(System.currentTimeMillis());

			long range = (long)limit - (long)min + 1;
		    long fraction = (long)(range * aRandom.nextDouble());
		    int randomNumber =  (int)(fraction + min);

		    numbres[i] = randomNumber;
		}
		
		return numbres;
	}
	///////////////////////////////////////////////////////////////
	//                    End of Private Methods                 //
	///////////////////////////////////////////////////////////////
}
