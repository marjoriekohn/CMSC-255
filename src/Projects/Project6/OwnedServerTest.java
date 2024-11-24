package Projects.Project6;


import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

@SuppressWarnings({"rawtypes","all"})
public class OwnedServerTest {
	
	@Test
	public void OwnedServer_superClassTest() {
		OwnedServer myOwnedServer = new OwnedServer("Xerox",1001.01, .08,3043.9);
	}
	
	@Test
	public void OwnedServer_instanceCountTest(){
		Class c = OwnedServer.class;
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
			 3, count);
		}
		catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	@Test
	public void OwnedServer_instanceVariablesTest(){
		Class testOwnedServer = OwnedServer.class;
		
		instanceVariablePrivate("maintenanceCost",testOwnedServer);
		instanceVariablePrivate("failureRate",testOwnedServer);
		instanceVariablePrivate("baseCost",testOwnedServer);
		
		instanceVariableStatic("maintenanceCost",testOwnedServer,false);
		instanceVariableStatic("failureRate",testOwnedServer,false);
		instanceVariableStatic("baseCost",testOwnedServer,false);
		
		instanceVariableCorrectType("maintenanceCost", double.class,testOwnedServer);
		instanceVariableCorrectType("failureRate", double.class,testOwnedServer);
		instanceVariableCorrectType("baseCost", double.class,testOwnedServer);
		
	}
	
	@Test
	public void OwnedServer_parameterizedConstructorTest() {
		OwnedServer testOwnedServer = new OwnedServer("Xerox",1001.01, .08,3043.9);
		
		testVariable("brand", testOwnedServer, "Xerox", "When checking the value of brand we", 1);
		testVariable("maintenanceCost", testOwnedServer, 1001.01, "When checking the value of maintenanceCost we", 0);
		testVariable("failureRate", testOwnedServer, .08, "When checking the value of failureRate we", 0);
		testVariable("baseCost", testOwnedServer, 3043.9, "When checking the value of baseCost we", 0);
	}
	
	@Test
	public void OwnedServer_toStringTest() {
		OwnedServer testOwnedServer = createOwnedServer("Xerox",2567.12, .07,4122.3);
		
		assertEquals(  "Xerox,N/A,2567.12,0.07,4122.30,N/A,N/A", testOwnedServer.toString());
		
		testOwnedServer = createOwnedServer("Gateway",1367.12, .05,3131.7);
		assertEquals(  "Gateway,N/A,1367.12,0.05,3131.70,N/A,N/A", testOwnedServer.toString());
	}
	
	@Test
	public void OwnedServer_getOperatingCostTest(){
		OwnedServer testOwnedServer = createOwnedServer("Xerox",2567.12, .07,4122.3);
		
		assertEquals("With a OwnedServer object who's maintenanceCost, failureRate, and baseCost is 2567.12, .07, and 4122.3 respectivally, when calling getOperatingCost given 10 for the years parameter, we",31590.484,testOwnedServer.getOperatingCost(10),.001);
		
		assertEquals("With a OwnedServer object who's maintenanceCost, failureRate, and baseCost is 2567.12, .07, and 4122.3 respectivally, when calling getOperatingCost given 256 for the years parameter, we",707307.8104,testOwnedServer.getOperatingCost(256),.001);
		
		assertEquals("With a OwnedServer object who's maintenanceCost, failureRate, and baseCost is 2567.12, .07, and 4122.3 respectivally, when calling getOperatingCost given -10 for the years parameter, we",-23345.884,testOwnedServer.getOperatingCost(-10),.001);
		
		testOwnedServer = createOwnedServer("Xerox",1534.54, .04,5666.3);
		
		assertEquals("With a OwnedServer object who's maintenanceCost, failureRate, and baseCost is 1534.54, .04, and 5666.3 respectivally, when calling getOperatingCost given 10 for the years parameter, we",21625.516,testOwnedServer.getOperatingCost(10),.001);
		
	}
	
	
	@Test
	public void OwnedServer_getMaintenanceCostTest() {
		OwnedServer testOwnedServer = createOwnedServer("Xerox",2567.12, .07,4122.3);
		
		assertEquals("With a OwnedServer object who's maintenanceCost instance variable is 2567.12, when calling getMaintenanceCost we",2567.12,testOwnedServer.getMaintenanceCost(),.001);
	}
	
	@Test
	public void OwnedServer_setMaintenanceCostTest() {
		OwnedServer testOwnedServer = createOwnedServer("Xerox",2567.12, .07,4122.3);
		
		testOwnedServer.setMaintenanceCost(3656.78);
		testVariable("maintenanceCost",testOwnedServer,3656.78,"After calling OwnedServer's setMaintenanceCost method with an argument of 3656.78, for the value of maintenanceCost we",0);
	}
	
	@Test
	public void OwnedServer_getFailureRateTest() {
		OwnedServer testOwnedServer = createOwnedServer("Xerox",2567.12, .07,4122.3);
		
		assertEquals("With a OwnedServer object who's failureRate instance variable is .07, when calling getFailureRate we",.07,testOwnedServer.getFailureRate(),.001);
	}
	
	@Test
	public void OwnedServer_setFailureRateTest() {
		OwnedServer testOwnedServer = createOwnedServer("Xerox",2567.12, .07,4122.3);
		
		testOwnedServer.setFailureRate(.1);
		testVariable("failureRate",testOwnedServer,.1,"After calling OwnedServer's setFailureRate method with an argument of .1, for the value of failureRate we",0);
	}
	
	@Test
	public void OwnedServer_getBaseCostTest() {
		OwnedServer testOwnedServer = createOwnedServer("Xerox",2567.12, .07,4122.3);
		
		assertEquals("With a OwnedServer object who's baseCost instance variable is 4122.3, when calling getBaseCost we",4122.3,testOwnedServer.getBaseCost(),.001);
	}
	
	@Test
	public void OwnedServer_setBaseCostTest() {
		OwnedServer testOwnedServer = createOwnedServer("Xerox",2567.12, .07,4122.3);
		
		testOwnedServer.setBaseCost(3235.25);
		testVariable("baseCost",testOwnedServer,3235.25,"After calling OwnedServer's setBaseCost method with an argument of 3235.25, for the value of baseCost we",0);
	}
	
	
	
	
	
	
	
	private OwnedServer createOwnedServer(String aBrand, double aMaintenanceCost, double aFailureRate, double aBaseCost){
		OwnedServer testOwnedServer = new OwnedServer("Xerox",1001.01, .08,3043.9);
		Class c = testOwnedServer.getClass();
		
		try {
			Field maintenanceCost = c.getDeclaredField("maintenanceCost");
			maintenanceCost.setAccessible(true);
			maintenanceCost.set(testOwnedServer, aMaintenanceCost);
			
			Field failureRate = c.getDeclaredField("failureRate");
			failureRate.setAccessible(true);
			failureRate.set(testOwnedServer, aFailureRate);
			
			Field baseCost = c.getDeclaredField("baseCost");
			baseCost.setAccessible(true);
			baseCost.set(testOwnedServer, aBaseCost);
			
			Field brand = c.getSuperclass().getDeclaredField("brand");
			brand.setAccessible(true);
			brand.set(testOwnedServer, aBrand);
			
		} catch (Exception e) {
			fail(e.toString());
		}
		
		return testOwnedServer;
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
