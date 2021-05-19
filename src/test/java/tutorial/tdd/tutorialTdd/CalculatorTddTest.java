package tutorial.tdd.tutorialTdd;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@DisplayName("Testing class calculator")
@Execution(ExecutionMode.CONCURRENT)
public class CalculatorTddTest {
	
	@BeforeAll
	static void beforeAllThis() {
		System.out.println("beforeAllThis");
	}
	
	@BeforeEach
	void beforeEachDoThis() {
		System.out.println("beforeEachDoThis");
	}
	
	/** Premisa de entrada: Números enteros (2, 2)
	 *  Premisa de salida: se devuelve la suma = 4
	 * */
	@Test
	@DisplayName("Test Sum")
	@EnabledOnOs(OS.WINDOWS)
	public void testSumWithPositiveIntegerNumbers() {
		Integer sumResult = null;
		CalculatorTdd myCalculator = null;
		
		myCalculator = new CalculatorTdd();
		sumResult = myCalculator.sum(2, 2);
		
		System.out.println("Execution test SUM!!!");
		
		assertNotNull(sumResult);
		assertTrue(sumResult.equals(4));
	}
	
	@Test
	@DisplayName("Test Sum")
	@DisabledOnOs(OS.LINUX)
	public void testDivideWithPositiveIntegerNumbers() {
		//Integer sumResult = null;
		CalculatorTdd myCalculator = null;
		
		myCalculator = new CalculatorTdd();
		Integer sumResult = myCalculator.div(2, 2);
		
		System.out.println("Execution test DIVIDE!!!");
		
		assertAll(() -> {
			assertNotNull(sumResult);
		}, () -> {
			assertTrue(sumResult.equals(1));
		});
	}
	
	@Test
	public void testDivideByZero() {
		CalculatorTdd myCalculator = new CalculatorTdd();

		assertThrows(ArithmeticException.class, () -> {
			myCalculator.div(1, 0);
		});
		
		System.out.println("DIVIDE BY ZERO!!!");
		
		/*
		ArithmeticException myEx = null;
		
		try {
			myCalculator = new CalculatorTdd();
			myCalculator.div(1, 0);
		} catch(ArithmeticException ex) {
			myEx = ex;
		}
		
		assertNotNull(myEx);*/
	}
	
	@Test
	void testSumWithAssertTimeout() {
		CalculatorTdd myCalculator = new CalculatorTdd();
		
		assertTimeout(Duration.ofSeconds(5), () -> {
			myCalculator.sum(4, 5);
			Thread.sleep(10000);
		});
	}
	
	@Test
	void testSumWithAssertTimeoutPreemptively() {
		CalculatorTdd myCalculator = new CalculatorTdd();
		
		assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
			myCalculator.sum(4,  5);
			Thread.sleep(4000);
		});
	}
	
	@AfterEach
	void afterEachDoThis() {
		System.out.println("afterEachDoThis");
	}
	
	@AfterAll
	static void afterAllThis() {
		System.out.println("afterAllThis");
	}
}



