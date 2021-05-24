package tutorial.tdd.tutorialTdd;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class BussinessTest {
	
	@Test
	@Tag("development")
	@Tag("production")
	public void testSumWithPositiveIntegerNumbers(TestInfo testInfo) {
		Integer sumResult = null;
		CalculatorTdd myCalculator = null;
		
		myCalculator = new CalculatorTdd();
		sumResult = myCalculator.sum(2, 2);
		
		System.out.println("Execution test SUM!!!");
		
		assertNotNull(sumResult);
		assertTrue(sumResult.equals(4));
	}
	
	@Test
	@Tag("production")
	public void testDivideByZero() {
		CalculatorTdd myCalculator = new CalculatorTdd();

		assertThrows(ArithmeticException.class, () -> {
			myCalculator.div(1, 0);
		});
		
		System.out.println("DIVIDE BY ZERO!!!");
	}
}
