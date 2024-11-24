package Labs.Lab14;


import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("rawtypes")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Lab14Test {
	private static final int NUM_LINES = 5;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		
	}
	
	private void testPatternOutput(String pattern, int expected, ArrayList<Integer> answer){
		ArrayList<Integer> values = new ArrayList<>();
		
		try {
			Method studentPattern = Lab14.class.getMethod(pattern, int.class, answer.getClass());
			int finalValue = (Integer) studentPattern.invoke(null, answer.size(), values);
			assertEquals(answer, values);
			assertEquals(expected, finalValue);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			fail("For some reason we aren't able to run Lab14." + pattern + ". Make sure you have the appropriate method");
		}
	}
	
	@Test
	public void pattern1Test() {
		//1, 2, 5, 10, 17, 26, 37, 50, 65, 82
		// f(n) = f(n-1) + 2(n-1) -1
		ArrayList<Integer> answer = new ArrayList<>(Arrays.asList(1, 2, 5, 10, 17, 26, 37, 50, 65, 82));
		testPatternOutput("pattern1", 82, answer);
	}
	
	@Test
	public void pattern2Test() {
		//4, 12, 10, 30, 28, 84, 82, Pattern 2C
		//f(n) = 3f(n-1) if n is even, f(n-1)-2 if n is odd
		ArrayList<Integer> answer = new ArrayList<>(Arrays.asList(4, 12, 10, 30, 28, 84, 82, 246, 244, 732));
		testPatternOutput("pattern2", 732, answer);
	}
	
	@Test
	public void pattern3Test() {
		//3, 6, 5, 10, 9, 18, 17, Pattern 1F
		//f(n) = 2 f(n-1) if n is even, f(n-1)-1 if n is odd
		ArrayList<Integer> answer = new ArrayList<>(Arrays.asList(3, 6, 5, 10, 9, 18, 17, 34, 33, 66, 65));
		testPatternOutput("pattern3", 65, answer);
	}
	
	@Test
	public void pattern4Test() {
		//1, 5, 13, 26, 45, 71, pattern 2F
		//f(n) = 2f(n-1) - f(n - 2) + n + 1
		ArrayList<Integer> answer = new ArrayList<>(Arrays.asList(1, 5, 13, 26, 45, 71, 105, 148, 201, 265));
		testPatternOutput("pattern4", 265, answer);
	}
	
	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}
}
