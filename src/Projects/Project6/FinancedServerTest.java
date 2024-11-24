package Projects.Project6;


import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

@SuppressWarnings({"rawtypes","all"})
public class FinancedServerTest {
	
	@Test
	public void RentalFinancedServer_superClassTest() {
		FinancedServer myFinancedServer = new FinancedServer("Gateway",2001.01, .08,3043.9,7,0.04);
		assertTrue("When testing if FinancedServer extends OwnedServer, we", (myFinancedServer instanceof OwnedServer));
	}
	
	@Test
	public void FinancedServer_instanceCountTest(){
		Class c = FinancedServer.class;
		try {
			int count = 0;
			for(Field field : c.getDeclaredFields()){
				//Added this because switch statements were breaking this test. like i get it but i don't *get* it.
				if(!field.getName().contains("SWITCH")){
					count++;
				}
			}
			
			assertEquals(
			 "You must only have the instance variables specified. When looking at the number of instance variables we",
			 2, count);
		}
		catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	@Test
	public void FinancedServer_instanceVariablesTest(){
		Class testFinancedServer = FinancedServer.class;
		
		instanceVariablePrivate("loanTerm",testFinancedServer);
		instanceVariablePrivate("apr",testFinancedServer);
		
		instanceVariableStatic("loanTerm",testFinancedServer,false);
		instanceVariableStatic("apr",testFinancedServer,false);
		
		instanceVariableCorrectType("loanTerm", int.class,testFinancedServer);
		instanceVariableCorrectType("apr", double.class,testFinancedServer);
		
	}
	
	@Test
	public void FinancedServer_parameterizedConstructorTest() {
		FinancedServer testFinancedServer = new FinancedServer("Gateway",2024.01, .07,4443.9,7,0.04);
		
		testVariable("brand", testFinancedServer, "Gateway", "When checking the value of brand we", 2);
		testVariable("maintenanceCost", testFinancedServer, 2024.01, "When checking the value of maintenanceCost we", 1);
		testVariable("failureRate", testFinancedServer, .07, "When checking the value of failureRate we", 1);
		testVariable("baseCost", testFinancedServer, 4443.9, "When checking the value of baseCost we", 1);
		testVariable("loanTerm", testFinancedServer, 7, "When checking the value of loanTerm we", 0);
		testVariable("apr", testFinancedServer, 0.04, "When checking the value of apr we", 0);
	}
	
	@Test
	public void FinancedServer_toStringTest() {
		FinancedServer testFinancedServer = createFinancedServer("Gateway",2024.01, .07,4443.9,7,0.04);
		
		assertEquals(  "Gateway,N/A,2024.01,0.07,4443.90,7,0.04", testFinancedServer.toString());
		
		testFinancedServer = createFinancedServer("Xerox",1776.06, .07,3122.3,5,0.09);
		assertEquals(  "Xerox,N/A,1776.06,0.07,3122.30,5,0.09", testFinancedServer.toString());
	}
	
	@Test
	public void FinancedServer_getOperatingCostTest(){
		FinancedServer testFinancedServer = createFinancedServer("Gateway",2024.01, .07,4443.9,7,0.04);
		
		assertEquals("With a FinancedServer object who's maintenanceCost, failureRate, baseCost, loanTerm, and apr is 2024.01, .07, 4443.9, 7, and 0.04 respectivally, when calling getOperatingCost given 10 for the years parameter, we",27345.099,testFinancedServer.getOperatingCost(10),.001);
		
		assertEquals("With a FinancedServer object who's maintenanceCost, failureRate, baseCost, loanTerm, and apr is 2024.01, .07, 4443.9, 7, and 0.04 respectivally, when calling getOperatingCost given 5 for the years parameter, we",14891.4477,testFinancedServer.getOperatingCost(5),.001);
		
		assertEquals("With a FinancedServer object who's maintenanceCost, failureRate, baseCost, loanTerm, and apr is 2024.01, .07, 4443.9, 7, and 0.04 respectivally, when calling getOperatingCost given 256 for the years parameter, we",560105.0112,testFinancedServer.getOperatingCost(256),.001);
		
		assertEquals("With a FinancedServer object who's maintenanceCost, failureRate, baseCost, loanTerm, and apr is 2024.01, .07, 4443.9, 7, and 0.04 respectivally, when calling getOperatingCost given -10 for the years parameter, we",-29782.8955,testFinancedServer.getOperatingCost(-10),.001);
		
		testFinancedServer = createFinancedServer("Xerox",1776.06, .07,3122.3,5,0.09);
		
		assertEquals("With a FinancedServer object who's maintenanceCost, failureRate, baseCost, loanTerm, and apr is 1776.06, .07, 3122.3, 5, and 0.09 respectivally, when calling getOperatingCost given 10 for the years parameter, we",23531.177,testFinancedServer.getOperatingCost(10),.001);
		
		assertEquals("With a FinancedServer object who's maintenanceCost, failureRate, baseCost, loanTerm, and apr is 1776.06, .07, 3122.3, 5, and 0.09 respectivally, when calling getOperatingCost given 5 for the years parameter, we",14029.256,testFinancedServer.getOperatingCost(5),.001);
		
	}
	
	@Test
	public void FinancedServer_getLoanTermTest() {
		FinancedServer testFinancedServer = createFinancedServer("Gateway",2024.01, .07,4443.9,7,0.04);
		
		assertEquals("With a FinancedServer object who's loanTerm instance variable is 7, when calling getLoanTerm we",7,testFinancedServer.getLoanTerm());
	}
	
	@Test
	public void FinancedServer_setLoanTermTest() {
		FinancedServer testFinancedServer = createFinancedServer("Gateway",2024.01, .07,4443.9,7,0.04);
		
		testFinancedServer.setLoanTerm(10);
		testVariable("loanTerm",testFinancedServer,10,"After calling FinancedServer's setLoanTerm method with an argument of 10, for the value of loanTerm we",0);
	}
	
	@Test
	public void FinancedServer_getAprTest() {
		FinancedServer testFinancedServer = createFinancedServer("Gateway",2024.01, .07,4443.9,7,0.04);
		
		assertEquals("With a FinancedServer object who's apr instance variable is 0.04, when calling getApr we",0.04,testFinancedServer.getApr(),.001);
	}
	
	@Test
	public void FinancedServer_setAprTest() {
		FinancedServer testFinancedServer = createFinancedServer("Gateway",2024.01, .07,4443.9,7,0.04);
		
		testFinancedServer.setApr(0.09);
		testVariable("apr",testFinancedServer,0.09,"After calling FinancedServer's setApr method with an argument of 0.09, for the value of apr we",0);
	}
	
	
	
	
	
	
	
	
	private FinancedServer createFinancedServer(String aBrand, double aMaintenanceCost, double aFailureRate, double aBaseCost, int aLoanTerm, double anAPR){
		FinancedServer testFinancedServer = new FinancedServer("Gateway",2001.01, .08,3043.9,7,0.04);
		Class c = testFinancedServer.getClass();
		
		try {
			Field loanTerm = c.getDeclaredField("loanTerm");
			loanTerm.setAccessible(true);
			loanTerm.set(testFinancedServer, aLoanTerm);
			
			Field apr = c.getDeclaredField("apr");
			apr.setAccessible(true);
			apr.set(testFinancedServer, anAPR);
			
			Field maintenanceCost = c.getSuperclass().getDeclaredField("maintenanceCost");
			maintenanceCost.setAccessible(true);
			maintenanceCost.set(testFinancedServer, aMaintenanceCost);
			
			Field failureRate = c.getSuperclass().getDeclaredField("failureRate");
			failureRate.setAccessible(true);
			failureRate.set(testFinancedServer, aFailureRate);
			
			Field baseCost = c.getSuperclass().getDeclaredField("baseCost");
			baseCost.setAccessible(true);
			baseCost.set(testFinancedServer, aBaseCost);
			
			Field brand = c.getSuperclass().getSuperclass().getDeclaredField("brand");
			brand.setAccessible(true);
			brand.set(testFinancedServer, aBrand);
			
		} catch (Exception e) {
			fail(e.toString());
		}
		
		return testFinancedServer;
	}
	
	private void instanceVariablePrivate(String aField, Class testClass) {
		try {
			testClass.getDeclaredField(aField);
			
			assertTrue("You must make your instance variables private.", Modifier.isPrivate(testClass.getDeclaredField(aField).getModifiers()));
			
		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	private void instanceVariableStatic(String aField, Class testClass, boolean isStatic) {
		try {
			testClass.getDeclaredField(aField);
			if(isStatic){
				assertTrue("The "+aField+" instance variable must be static.", Modifier.isStatic(testClass.getDeclaredField(aField).getModifiers()));
			}
			else{
				assertFalse("Your instance variables must NOT be static.", Modifier.isStatic(testClass.getDeclaredField(aField).getModifiers()));
			}
			
			
		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	private void instanceVariableCorrectType(String aField, Class<?> aClass,  Class testClass) {
		try {
			testClass.getDeclaredField(aField);
			
			assertEquals("You must make the speed instance variable of type"+ aClass.toString() +".", aClass, testClass.getDeclaredField(aField).getType());
			
		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	private void testVariable(String aField, Object testObject, Object expected, String message, int descendantLevel){
		Class c = testObject.getClass();
		
		for(int i = 0; i < descendantLevel; i++){
			c = c.getSuperclass();
		}
		
		try {
			Field field = c.getDeclaredField(aField);
			field.setAccessible(true);
			Object fieldCompareTime = field.get(testObject);
			
			if(expected == null){
				assertNull(message,fieldCompareTime);
			}
			//If class is a double we have a special Junit assert to run
			else if(expected.getClass().equals(Double.class)){
				double doubleFieldCompareTime = (double) fieldCompareTime;
				double doubleExpected = (double) expected;
				assertEquals(message, doubleExpected, doubleFieldCompareTime, .001);
			}
			//Array of some kind yay
			else if(expected.getClass().isArray()){
			
			}
			else if(expected.getClass().equals(ArrayList.class)){
			
			}
			else{
				assertEquals(message, expected, fieldCompareTime);
			}
			
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
}
