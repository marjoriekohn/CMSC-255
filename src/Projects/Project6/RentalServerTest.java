package Projects.Project6;


import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

@SuppressWarnings({"rawtypes","all"})
public class RentalServerTest {
	
	@Test
	public void RentalRentalServer_superClassTest() {
		RentalServer myRentalServer = new RentalServer("",0.0);
	}
	
	@Test
	public void RentalServer_instanceCountTest(){
		Class c = RentalServer.class;
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
			 1, count);
		}
		catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	@Test
	public void RentalServer_instanceVariablesTest(){
		Class testRentalServer = RentalServer.class;
		
		instanceVariablePrivate("annualRent",testRentalServer);
		
		instanceVariableStatic("annualRent",testRentalServer,false);
		
		instanceVariableCorrectType("annualRent", double.class,testRentalServer);
		
	}
	
	@Test
	public void RentalServer_parameterizedConstructorTest() {
		RentalServer testRentalServer = new RentalServer("Commodore",25.3);
		
		testVariable("brand", testRentalServer, "Commodore", "When checking the value of brand we", 1);
		testVariable("annualRent", testRentalServer, 25.3, "When checking the value of annualRent we", 0);
	}
	
	@Test
	public void RentalServer_toStringTest() {
		RentalServer testRentalServer = createRentalServer("Compaq",10.1);
		
		assertEquals(  "Compaq,10.10,N/A,N/A,N/A,N/A,N/A", testRentalServer.toString());
		
		testRentalServer = createRentalServer("Gateway",12.2);
		assertEquals(  "Gateway,12.20,N/A,N/A,N/A,N/A,N/A", testRentalServer.toString());
	}
	
	@Test
	public void RentalServer_getOperatingCostTest(){
		RentalServer testRentalServer = createRentalServer("Gateway",2351.35);
		
		
		
		
		testRentalServer = createRentalServer("Gateway",1337);
		
		
	}
	
	@Test
	public void RentalServer_getAnnualRentTest() {
		RentalServer testRentalServer = createRentalServer("Compaq",1995.01);
		
	}
	
	@Test
	public void RentalServer_setAnnualRentTest() {
		RentalServer testRentalServer = createRentalServer("Compaq",1995.01);
		
		testRentalServer.setAnnualRent(3656.78);
	}
	
	
	
	
	private RentalServer createRentalServer(String aBrand, double anAnnualRent){
		RentalServer testRentalServer = new RentalServer("Amiga",19.84);
		Class c = testRentalServer.getClass();
		
		try {
			Field annualRent = c.getDeclaredField("annualRent");
			annualRent.setAccessible(true);
			annualRent.set(testRentalServer, anAnnualRent);
			
			Field brand = c.getSuperclass().getDeclaredField("brand");
			brand.setAccessible(true);
			brand.set(testRentalServer, aBrand);
			
		} catch (Exception e) {
			fail(e.toString());
		}
		
		return testRentalServer;
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
