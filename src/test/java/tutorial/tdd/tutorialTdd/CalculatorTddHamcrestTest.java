package tutorial.tdd.tutorialTdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class CalculatorTddHamcrestTest {
	
	@Test
	@DisplayName("Test Sum")
	public void testSumWithPositiveIntegerNumbers() {
		Integer sumResult = null;
		CalculatorTdd myCalculator = null;
		
		myCalculator = new CalculatorTdd();
		sumResult = myCalculator.sum(2, 2);
		
		System.out.println("Execution test SUM!!!");
		
		assertNotNull(sumResult);
		assertTrue(sumResult.equals(4));
		
		assertEquals(4, sumResult);
		assertThat(sumResult, is(4));
		
		assertThat(sumResult, not(5));
		
		assertThat(sumResult, is(not(nullValue(Integer.class))));
		assertThat(sumResult, is(notNullValue(Integer.class)));
	}
	
	@Test
	public void testHamcrestWithList() {
		List<String> myList = new ArrayList<>();
		
		myList.add("Hello From The Beach :D");
		myList.add("Mojito");
		myList.add("espeto");
		
		assertTrue(!myList.isEmpty());
		assertThat(myList, not(empty()));
		
		assertThat(myList.get(0), endsWith(":D"));
		assertThat(myList.get(0), startsWith("Hello"));
		
		assertThat(myList, hasItem("Mojito"));
		assertThat(myList, hasItems("Mojito", "espeto"));
	}
	
	@Test
	void testHamcrestProperty() {
		PairNumber pairNumber = new PairNumber(3, 5);
		
		assertThat(pairNumber, hasProperty("number1"));
		assertThat(pairNumber, hasProperty("pako", is(3)));
	}
}












