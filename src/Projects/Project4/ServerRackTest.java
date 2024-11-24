package Projects.Project4;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ServerRackTest {
	
	@Test
	public void ServerRack_instanceCountTest(){
		ServerRack testServerRack = new ServerRack();
		@SuppressWarnings("rawtypes")
		Class c = testServerRack.getClass();
		try {
			assertEquals(
				"You must only have the instance variables specified. When looking at the number of instance variables we",
				6, c.getDeclaredFields().length);
		}
		catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	@Test
	public void ServerRack_instanceVariablesTest(){
		ServerRack testServerRack = new ServerRack();
		instanceVariablePrivate("brand",testServerRack);
		instanceVariablePrivate("rackID",testServerRack);
		instanceVariablePrivate("operatingCost",testServerRack);
		instanceVariablePrivate("operatingSystem",testServerRack);
		instanceVariablePrivate("cooling",testServerRack);
		instanceVariablePrivate("ownership",testServerRack);
		
		instanceVariableStatic("brand",testServerRack);
		instanceVariableStatic("rackID",testServerRack);
		instanceVariableStatic("operatingCost",testServerRack);
		instanceVariableStatic("operatingSystem",testServerRack);
		instanceVariableStatic("cooling",testServerRack);
		instanceVariableStatic("ownership",testServerRack);
		
		instanceVariableCorrectType("brand", String.class,testServerRack);
		instanceVariableCorrectType("rackID", int.class,testServerRack);
		instanceVariableCorrectType("operatingCost", double.class,testServerRack);
		instanceVariableCorrectType("operatingSystem", OS.class,testServerRack);
		instanceVariableCorrectType("cooling", Cooling.class,testServerRack);
		instanceVariableCorrectType("ownership", Ownership.class,testServerRack);
	}
	
	@Test
	public void ServerRack_defaultConstructorTest() {
		ServerRack testServerRack = new ServerRack();
		
		testVariable("brand",testServerRack,"","When checking the value of brand we");
		testVariable("rackID",testServerRack,0,"When checking the value of rackID we");
		testVariable("operatingCost",testServerRack,0.0,"When checking the value of operatingCost we");
		testVariable("operatingSystem",testServerRack,OS.WINDOWS,"When checking the value of operatingSystem we");
		testVariable("cooling",testServerRack,Cooling.AIR,"When checking the value of operatingSystem we");
		testVariable("ownership",testServerRack,Ownership.RENTAL,"When checking the value of ownership we");
	}
	
	@Test
	public void ServerRack_parameterizedConstructorTest() {
		ServerRack testServerRack = new ServerRack("Compaq",454,20.9,OS.WINDOWS,Cooling.HEAT_SINK,Ownership.RENTAL);
		
		testVariable("brand",testServerRack,"Compaq","When checking the value of brand we");
		testVariable("rackID",testServerRack,454,"When checking the value of rackID we");
		testVariable("operatingCost",testServerRack,20.9,"When checking the value of operatingCost we");
		testVariable("operatingSystem",testServerRack,OS.WINDOWS,"When checking the value of operatingSystem we");
		testVariable("cooling",testServerRack,Cooling.HEAT_SINK,"When checking the value of cooling we");
		testVariable("ownership",testServerRack,Ownership.RENTAL,"When checking the value of ownership we");
	}
	
	@Test
	public void ServerRack_getOwnershipTest() {
		ServerRack testServerRack = createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED);
		assertEquals("With a ServerRack object who's ownership instance variable is OWNED, when calling getOwnership we",Ownership.OWNED,testServerRack.getOwnership());
	}
	
	@Test
	public void ServerRack_setOwnershipTest() {
		ServerRack testServerRack = createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED);
		testServerRack.setOwnership(Ownership.FINANCED);
		testVariable("ownership",testServerRack,Ownership.FINANCED,"After calling ServerRack's setOwnership method with an argument of Ownership.FINANCED, for the value of ownership we");
	}
	
	@Test
	public void ServerRack_getBrandTest() {
		ServerRack testServerRack = createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED);
		assertEquals("With a ServerRack object who's brand instance variable is Gateway, when calling getBrand we","Gateway",testServerRack.getBrand());
	}
	
	@Test
	public void ServerRack_setBrandTest() {
		ServerRack testServerRack = createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED);
		testServerRack.setBrand("Nortel");
		testVariable("brand",testServerRack,"Nortel","After calling ServerRack's setBrand method with an argument of Nortel, for the value of brand we");
	}
	
	@Test
	public void ServerRack_getOperatingCost() {
		ServerRack testServerRack = createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED);
		assertEquals("With a ServerRack object who's operatingCost instance variable is 13.0, when calling getOperatingCost() we",13.0,testServerRack.getOperatingCost(),.001);
	}
	
	@Test
	public void ServerRack_setOperatingCostTest() {
		ServerRack testServerRack = createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED);
		testServerRack.setOperatingCost(17.32);
		testVariable("operatingCost",testServerRack,17.32,"After calling ServerRack's setOperatingCost method with an argument of 17.32, for the value of operatingCost we");
	}
	
	@Test
	public void ServerRack_getRackIDTest() {
		ServerRack testServerRack = createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED);
		assertEquals("With a ServerRack object who's rackID instance variable is 14, when calling getRackID we",14,testServerRack.getRackID());
	}
	
	@Test
	public void ServerRack_setRackIDTest() {
		ServerRack testServerRack = createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED);
		testServerRack.setRackID(32);
		testVariable("rackID",testServerRack,32,"After calling ServerRack's setRackID method with an argument of 32, for the value of rackID we");
	}
	
	@Test
	public void ServerRack_getOSTest() {
		ServerRack testServerRack = createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED);
		assertEquals("With a ServerRack object who's operatingSystem instance variable is OS.LINUX, when calling getOperatingSystem we",OS.LINUX,testServerRack.getOperatingSystem());
	}
	
	@Test
	public void ServerRack_setOSTest() {
		ServerRack testServerRack = createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED);
		testServerRack.setOperatingSystem(OS.MAC);
		testVariable("operatingSystem",testServerRack,OS.MAC,"After calling ServerRack's setOperatingSystem method with an argument of OS.MAC, for the value of operatingSystem we");
	}
	
	@Test
	public void ServerRack_getCoolingTest() {
		ServerRack testServerRack = createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED);
		assertEquals("With a ServerRack object who's cooling instance variable is Cooling.LIQUID, when calling getCooling we",Cooling.LIQUID,testServerRack.getCooling());
	}
	
	@Test
	public void ServerRack_setCoolingTest() {
		ServerRack testServerRack = createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED);
		testServerRack.setCooling(Cooling.AIR);
		testVariable("cooling",testServerRack,Cooling.AIR,"After calling ServerRack's setCooling method with an argument of Cooling.AIR, for the value of cooling we");
	}
	
	
	@Test
	public void ServerRack_toStringTest() {
		ServerRack testServerRack = createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED);
		String normalized = normalizer(testServerRack.toString());
		assertEquals(  "\n\tGateway\n" +
				"\t14\n" +
				"\t13.00\n" +
				"\tLINUX\n" +
				"\tLIQUID\n" +
				"\tOWNED\n"
			, normalized);
		
		
		testServerRack = createServerRack("Compaq",454,20.9,OS.WINDOWS,Cooling.HEAT_SINK,Ownership.RENTAL);
		normalized = normalizer(testServerRack.toString());
		assertEquals(  "\n\tCompaq\n" +
				"\t454\n" +
				"\t20.90\n" +
				"\tWINDOWS\n" +
				"\tHEAT_SINK\n" +
				"\tRENTAL\n"
			, normalized);
	}
	
	private String normalizer(String string) {
		String normalized = string;
		normalized = normalized.replaceAll("\r?\n", "\n");
		return normalized;
	}
	
	private ServerRack createServerRack(String aBrand, int aRackID, double anOperatingCost, OS aOS, Cooling aCooling, Ownership aOwnership){
		ServerRack testServerRack = new ServerRack();
		@SuppressWarnings("rawtypes")
		Class c = testServerRack.getClass();
		
		try {
			Field ownership = c.getDeclaredField("ownership");
			ownership.setAccessible(true);
			ownership.set(testServerRack, aOwnership);
			
			Field brand = c.getDeclaredField("brand");
			brand.setAccessible(true);
			brand.set(testServerRack, aBrand);
			
			Field rackID = c.getDeclaredField("rackID");
			rackID.setAccessible(true);
			rackID.set(testServerRack, aRackID);
			
			Field operatingCost = c.getDeclaredField("operatingCost");
			operatingCost.setAccessible(true);
			operatingCost.set(testServerRack, anOperatingCost);
			
			Field operatingSystem = c.getDeclaredField("operatingSystem");
			operatingSystem.setAccessible(true);
			operatingSystem.set(testServerRack, aOS);
			
			Field cooling = c.getDeclaredField("cooling");
			cooling.setAccessible(true);
			cooling.set(testServerRack, aCooling);
			
		} catch (Exception e) {
			fail(e.toString());
		}
		
		return testServerRack;
	}
	
	private void instanceVariablePrivate(String aField, Object testObject) {
		@SuppressWarnings("rawtypes")
		Class c = testObject.getClass();
		try {
			c.getDeclaredField(aField);
			
			assertTrue("You must make your instance variables private.", Modifier.isPrivate(c.getDeclaredField(aField).getModifiers()));
			
		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	private void instanceVariableStatic(String aField, Object testObject) {
		@SuppressWarnings("rawtypes")
		Class c = testObject.getClass();
		try {
			c.getDeclaredField(aField);
			
			assertEquals("Your instance variables must NOT be static.", false,
				Modifier.isStatic(c.getDeclaredField(aField).getModifiers()));
			
		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	private void instanceVariableCorrectType(String aField, Class<?> aClass,  Object testObject) {
		@SuppressWarnings("rawtypes")
		Class c = testObject.getClass();
		try {
			c.getDeclaredField(aField);
			
			assertEquals("You must make the speed instance variable of type"+ aClass.toString() +".", aClass, c.getDeclaredField(aField).getType());
			
		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	private void testVariable(String aField, Object testObject, Object expected, String message){
		@SuppressWarnings("rawtypes")
		Class c = testObject.getClass();
		try {
			Field field = c.getDeclaredField(aField);
			field.setAccessible(true);
			Object fieldValue = field.get(testObject);
			
			if(expected == null){
				assertNull(message,fieldValue);
			}
			//If class is a double we have a special Junit assert to run
			else if(expected.getClass().equals(Double.class)){
				double doubleFieldValue = (double) fieldValue;
				double doubleExpected = (double) expected;
				assertEquals(message, doubleExpected, doubleFieldValue, .01);
			}
			//Array of some kind yay
			else if(expected.getClass().isArray()){
			
			}
			else if(expected.getClass().equals(ArrayList.class)){
			
			}
			else{
				assertEquals(message, expected, fieldValue);
			}
			
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
}
