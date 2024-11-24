package Projects.Project4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

@SuppressWarnings("rawtypes")
public class DataCenterTest {
	
	@Rule
	public Timeout globalTimeout = Timeout.seconds(3); // 3 seconds max per method tested
	
	@Test
	public void DataCenter_instanceCountTest(){
		DataCenter testDataCenter = new DataCenter();
		Class c = testDataCenter.getClass();
		try {
			assertEquals(
				"You must only have the instance variables specified. When looking at the number of instance variables we",
				4, c.getDeclaredFields().length);
		}
		catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	@Test
	public void DataCenter_instanceVariablesTest(){
		DataCenter testDataCenter = new DataCenter();
		instanceVariablePrivate("centerName",testDataCenter);
		instanceVariablePrivate("capacity",testDataCenter);
		instanceVariablePrivate("budget",testDataCenter);
		instanceVariablePrivate("rackList",testDataCenter);
		
		instanceVariableStatic("centerName",testDataCenter);
		instanceVariableStatic("capacity",testDataCenter);
		instanceVariableStatic("budget",testDataCenter);
		instanceVariableStatic("rackList",testDataCenter);
		
		instanceVariableCorrectType("centerName",String.class,testDataCenter);
		instanceVariableCorrectType("capacity",int.class,testDataCenter);
		instanceVariableCorrectType("budget",double.class,testDataCenter);
		instanceVariableCorrectType("rackList",ArrayList.class,testDataCenter);
	}
	
	@Test
	public void DataCenter_defaultConstructorTest() {
		DataCenter testDataCenter = new DataCenter();
		
		testVariable("centerName",testDataCenter,"","When checking the value of centerName we");
		testVariable("capacity",testDataCenter,0,"When checking the value of capacity we");
		testVariable("budget",testDataCenter,0.0,"When checking the value of budget we");
		testVariable("rackList",testDataCenter,new ArrayList<>(),"When checking the value of rackList we");
		
	}
	
	@Test
	public void DataCenter_parameterizedConstructorTest() {
		DataCenter testDataCenter = new DataCenter("Rackcon",128,20.2);
		
		testVariable("centerName",testDataCenter,"Rackcon","When checking the value of centerName we");
		testVariable("capacity",testDataCenter,128,"When checking the value of capacity we");
		testVariable("budget",testDataCenter,20.2,"When checking the value of budget we");
		testVariable("rackList",testDataCenter,new ArrayList<>(),"When checking the value of rackList we");
		
	}
	
	@Test
	public void DataCenter_getCenterNameTest() {
		ArrayList<ServerRack> someServerRacks = new ArrayList<>();
		DataCenter testDataCenter = createDataCenter("Rackcon",128,20.2,someServerRacks);
		assertEquals("With a DataCenter object who's centerName instance variable is Rackcon, when calling getCenterName we","Rackcon",testDataCenter.getCenterName());
	}
	
	@Test
	public void DataCenter_setCenterNameTest() {
		ArrayList<ServerRack> someServerRacks = new ArrayList<>();
		DataCenter testDataCenter = createDataCenter("Rackcon",128,20.2,someServerRacks);
		
		testDataCenter.setCenterName("EvilCorp");
		testVariable("centerName",testDataCenter,"EvilCorp","After calling DataCenter's setCenterName method with an argument of EvilCorp, for the value of centerName we");
	}
	
	@Test
	public void DataCenter_getCapacityTest() {
		ArrayList<ServerRack> someServerRacks = new ArrayList<>();
		DataCenter testDataCenter = createDataCenter("Rackcon",128,20.2,someServerRacks);
		assertEquals("With a DataCenter object who's capacity instance variable is 128, when calling getCapacity we",128,testDataCenter.getCapacity());
	}
	
	@Test
	public void DataCenter_setCapacityTest() {
		ArrayList<ServerRack> someServerRacks = new ArrayList<>();
		DataCenter testDataCenter = createDataCenter("Rackcon",128,20.2,someServerRacks);
		testDataCenter.setCapacity(512);
		testVariable("capacity",testDataCenter,512,"After calling DataCenter's setCapacity method with an argument of 512, for the value of capacity we");
	}
	
	@Test
	public void DataCenter_getBudgetTest() {
		ArrayList<ServerRack> someServerRacks = new ArrayList<>();
		DataCenter testDataCenter = createDataCenter("Rackcon",128,20.2,someServerRacks);
		assertEquals("With a DataCenter object who's budget instance variable is 20.2, when calling getBudget we",20.2,testDataCenter.getBudget(),.001);
	}
	
	@Test
	public void DataCenter_setBudgetTest() {
		ArrayList<ServerRack> someServerRacks = new ArrayList<>();
		DataCenter testDataCenter = createDataCenter("Rackcon",128,20.2,someServerRacks);
		testDataCenter.setBudget(31.3);
		testVariable("budget",testDataCenter,31.3,"After calling DataCenter's setBudget method with an argument of 31.3, for the value of budget we");
	}
	
	@Test
	public void DataCenter_isInBudgetTrueTest() {
		ArrayList<ServerRack> someServerRacks = new ArrayList<>();
		
		someServerRacks.add(createServerRack("Zenith Data Systems",256,9.2,OS.LINUX,Cooling.HEAT_SINK,Ownership.RENTAL));
		someServerRacks.add(createServerRack("Sun Microsystems",128,7.4,OS.MAC,Cooling.LIQUID,Ownership.FINANCED));
		someServerRacks.add(createServerRack("NeXT",1337,17.2,OS.MAC,Cooling.HYBRID,Ownership.OWNED));
		someServerRacks.add(createServerRack("North Star Computers",64,19.4,OS.WINDOWS,Cooling.AIR,Ownership.RENTAL));
		
		
		DataCenter testDataCenter = createDataCenter("Rackcon",128,60.0,someServerRacks);
		
		assertEquals("With a DataCenter object who's SeverRack objects are in budget when calling isInBudget we",true,testDataCenter.isInBudget());
		
	}
	
	@Test
	public void DataCenter_isInBudgetFalseTest() {
		ArrayList<ServerRack> someServerRacks = new ArrayList<>();
		
		someServerRacks.add(createServerRack("Zenith Data Systems",256,9.2,OS.LINUX,Cooling.HEAT_SINK,Ownership.RENTAL));
		someServerRacks.add(createServerRack("Sun Microsystems",128,7.4,OS.MAC,Cooling.LIQUID,Ownership.FINANCED));
		someServerRacks.add(createServerRack("NeXT",1337,17.2,OS.MAC,Cooling.HYBRID,Ownership.OWNED));
		someServerRacks.add(createServerRack("North Star Computers",64,19.4,OS.WINDOWS,Cooling.AIR,Ownership.RENTAL));
		
		
		DataCenter testDataCenter = createDataCenter("Rackcon",128,20.0,someServerRacks);
		
		assertEquals("With a DataCenter object who's SeverRack objects are *not* in budget when calling isInBudget we",false,testDataCenter.isInBudget());
	}
	
	
	
	@Test
	public void DataCenter_getServerRacksTest() {
		ArrayList<ServerRack> someServerRacks = new ArrayList<>();
		someServerRacks.add(createServerRack("Zenith Data Systems",256,9.2,OS.LINUX,Cooling.HEAT_SINK,Ownership.RENTAL));
		someServerRacks.add(createServerRack("Sun Microsystems",128,7.4,OS.MAC,Cooling.LIQUID,Ownership.FINANCED));
		
		ArrayList<ServerRack> expectedServerRacks = new ArrayList<>();
		
		expectedServerRacks.add(createServerRack("Zenith Data Systems",256,9.2,OS.LINUX,Cooling.HEAT_SINK,Ownership.RENTAL));
		expectedServerRacks.add(createServerRack("Sun Microsystems",128,7.4,OS.MAC,Cooling.LIQUID,Ownership.FINANCED));
		
		DataCenter testDataCenter = createDataCenter("Rackcon",128,20.2,someServerRacks);
		testServerRackArray("With a DataCenter object who's rackList instance variable has two elements, when calling getServerRacks we",expectedServerRacks,testDataCenter.getServerRacks());
	}
	
	@Test
	public void DataCenter_getNumServerRacksTest() {
		ArrayList<ServerRack> someServerRacks = new ArrayList<>();
		someServerRacks.add(createServerRack("Zenith Data Systems",256,9.2,OS.LINUX,Cooling.HEAT_SINK,Ownership.RENTAL));
		someServerRacks.add(createServerRack("Sun Microsystems",128,7.4,OS.MAC,Cooling.LIQUID,Ownership.FINANCED));
		DataCenter testDataCenter = createDataCenter("Rackcon",128,20.2,someServerRacks);
		assertEquals("With a DataCenter object who's rackList instance variable has two elements, when calling getNumServerRacks we",2,testDataCenter.getNumServerRacks());
	}
	
	@Test
	public void DataCenter_addServerRackOneServerRackTest() {
		ArrayList<ServerRack> someServerRacks = new ArrayList<>();
		ArrayList<ServerRack> expectedServerRack = new ArrayList<>();
		expectedServerRack.add(createServerRack("Zenith Data Systems",256,9.2,OS.LINUX,Cooling.HEAT_SINK,Ownership.RENTAL));
		DataCenter testDataCenter = createDataCenter("Rackcon",128,20.2,someServerRacks);
		testDataCenter.addServerRack(createServerRack("Zenith Data Systems",256,9.2,OS.LINUX,Cooling.HEAT_SINK,Ownership.RENTAL));
		
		testVariable("rackList",testDataCenter,expectedServerRack,"With a DataCenter object who's rackList instance variable had zero elements, then calling addServerRack once, when checking the rackList instance variable we");
	}
	
	@Test
	public void DataCenter_addServerRackFourServerRacksTest() {
		ArrayList<ServerRack> someServerRacks = new ArrayList<>();
		ArrayList<ServerRack> expectedServerRack = new ArrayList<>();
		expectedServerRack.add(createServerRack("Zenith Data Systems",256,9.2,OS.LINUX,Cooling.HEAT_SINK,Ownership.RENTAL));
		expectedServerRack.add(createServerRack("Sun Microsystems",128,7.4,OS.MAC,Cooling.LIQUID,Ownership.FINANCED));
		expectedServerRack.add(createServerRack("NeXT",1337,17.2,OS.MAC,Cooling.HYBRID,Ownership.OWNED));
		expectedServerRack.add(createServerRack("North Star Computers",64,19.4,OS.WINDOWS,Cooling.AIR,Ownership.RENTAL));
		
		
		DataCenter testDataCenter = createDataCenter("Rackcon",128,20.2,someServerRacks);
		
		testDataCenter.addServerRack(createServerRack("Zenith Data Systems",256,9.2,OS.LINUX,Cooling.HEAT_SINK,Ownership.RENTAL));
		testDataCenter.addServerRack(createServerRack("Sun Microsystems",128,7.4,OS.MAC,Cooling.LIQUID,Ownership.FINANCED));
		testDataCenter.addServerRack(createServerRack("NeXT",1337,17.2,OS.MAC,Cooling.HYBRID,Ownership.OWNED));
		testDataCenter.addServerRack(createServerRack("North Star Computers",64,19.4,OS.WINDOWS,Cooling.AIR,Ownership.RENTAL));
		
		testVariable("rackList",testDataCenter,expectedServerRack,"With a DataCenter object who's rackList instance variable had zero elements, then calling addServerRack four times, when checking the rackList instance variable we");
	}
	
	@Test
	public void Customer_toStringTest() {
		ArrayList<ServerRack> someServerRack = new ArrayList<>();
		someServerRack.add(createServerRack("Gateway",14,13.0,OS.LINUX,Cooling.LIQUID,Ownership.OWNED));
		DataCenter testDataCenter = createDataCenter("Rackcon",128,20.2,someServerRack);
		String normalized = normalizer(testDataCenter.toString());
		
		assertEquals(  "Rackcon\n" +
			"128\n" +
			"20.20\n" +
			"ServerRacks:\n" +
			"\n\tGateway\n" +
			"\t14\n" +
			"\t13.00\n" +
			"\tLINUX\n" +
			"\tLIQUID\n" +
			"\tOWNED\n", normalized);
		
		
		someServerRack = new ArrayList<>();
		
		someServerRack.add(createServerRack("Zenith Data Systems",256,9.2,OS.LINUX,Cooling.HEAT_SINK,Ownership.RENTAL));
		someServerRack.add(createServerRack("Sun Microsystems",128,7.4,OS.MAC,Cooling.LIQUID,Ownership.FINANCED));
		someServerRack.add(createServerRack("NeXT",1337,17.2,OS.MAC,Cooling.HYBRID,Ownership.OWNED));
		someServerRack.add(createServerRack("North Star Computers",64,19.4,OS.WINDOWS,Cooling.AIR,Ownership.RENTAL));
		
		testDataCenter = createDataCenter("StackChanger",19,4.2,someServerRack);
		
		assertEquals(  "StackChanger\n" +
			"19\n" +
			"4.20\n" +
			"ServerRacks:\n" +
			"\n\tZenith Data Systems\n" +
			"\t256\n" +
			"\t9.20\n" +
			"\tLINUX\n" +
			"\tHEAT_SINK\n" +
			"\tRENTAL\n"+
			"\n\tSun Microsystems\n" +
			"\t128\n" +
			"\t7.40\n" +
			"\tMAC\n" +
			"\tLIQUID\n" +
			"\tFINANCED\n"+
			"\n\tNeXT\n" +
			"\t1337\n" +
			"\t17.20\n" +
			"\tMAC\n" +
			"\tHYBRID\n" +
			"\tOWNED\n"+
			"\n\tNorth Star Computers\n" +
			"\t64\n" +
			"\t19.40\n" +
			"\tWINDOWS\n" +
			"\tAIR\n" +
			"\tRENTAL\n", testDataCenter.toString());
	}
	
	private String normalizer(String string) {
		String normalized = string;
		normalized = normalized.replaceAll("\r?\n", "\n");
		return normalized;
	}
	
	private DataCenter createDataCenter(String aCenterName, int aCapacity, double aBudget, ArrayList aServerRacks){
		DataCenter testDataCenter = new DataCenter();
		Class c = testDataCenter.getClass();
		
		try {
			Field centerName = c.getDeclaredField("centerName");
			centerName.setAccessible(true);
			centerName.set(testDataCenter, aCenterName);
			
			Field capacity = c.getDeclaredField("capacity");
			capacity.setAccessible(true);
			capacity.set(testDataCenter, aCapacity);
			
			Field budget = c.getDeclaredField("budget");
			budget.setAccessible(true);
			budget.set(testDataCenter, aBudget);
			
			Field rackList = c.getDeclaredField("rackList");
			rackList.setAccessible(true);
			rackList.set(testDataCenter, aServerRacks);
			
		} catch (Exception e) {
			fail(e.toString());
		}
		
		return testDataCenter;
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
		Class c = testObject.getClass();
		try {
			c.getDeclaredField(aField);
			
			assertFalse("Your instance variables must NOT be static.", Modifier.isStatic(c.getDeclaredField(aField).getModifiers()));
			
		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	private void instanceVariableCorrectType(String aField, Class<?> aClass,  Object testObject) {
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
				//CUSTOM FOR PROJECT6TESTS!!!
				testServerRackArray(message,(ArrayList) expected, (ArrayList) fieldValue);
			}
			else{
				assertEquals(message, expected, fieldValue);
			}
			
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	private void testServerRackArray(String message, ArrayList expected, ArrayList actual){
		assertEquals(message + " looked at the size and ",expected.size(),actual.size());
		
		for(int i = 0; i < expected.size(); i++) {
			if (!ServerRackIsEqual(expected.get(i), actual.get(i))) {
				assertEquals(message + " looked at index "+i+" and ", expected, actual);
			}
		}
	}
	
	private boolean ServerRackIsEqual(Object o1, Object o2){
		Class c = o1.getClass();
		try {
			Field o1FieldOwner = c.getDeclaredField("brand");
			o1FieldOwner.setAccessible(true);
			Object o1Owner = o1FieldOwner.get(o1);
			
			Field o2FieldOwner = c.getDeclaredField("brand");
			o2FieldOwner.setAccessible(true);
			Object o2Owner = o2FieldOwner.get(o2);
			
			Field o1FieldSquareFootage = c.getDeclaredField("rackID");
			o1FieldSquareFootage.setAccessible(true);
			Object o1squareFootage = o1FieldSquareFootage.get(o1);
			
			Field o2FieldSquareFootage = c.getDeclaredField("rackID");
			o2FieldSquareFootage.setAccessible(true);
			Object o2squareFootage = o2FieldSquareFootage.get(o2);
			
			Field o1FieldLotNumber = c.getDeclaredField("operatingCost");
			o1FieldLotNumber.setAccessible(true);
			Object o1lotNumber = o1FieldLotNumber.get(o1);
			
			Field o2FieldLotNumber = c.getDeclaredField("operatingCost");
			o2FieldLotNumber.setAccessible(true);
			Object o2lotNumber = o2FieldLotNumber.get(o2);
			
			Field o1FieldBedrooms = c.getDeclaredField("operatingSystem");
			o1FieldBedrooms.setAccessible(true);
			Object o1Bedrooms = o1FieldBedrooms.get(o1);
			
			Field o2FieldBedrooms = c.getDeclaredField("operatingSystem");
			o2FieldBedrooms.setAccessible(true);
			Object o2Bedrooms = o2FieldBedrooms.get(o2);
			
			Field o1FieldBaths = c.getDeclaredField("cooling");
			o1FieldBaths.setAccessible(true);
			Object o1Baths = o1FieldBaths.get(o1);
			
			Field o2FieldBaths = c.getDeclaredField("cooling");
			o2FieldBaths.setAccessible(true);
			Object o2Baths = o2FieldBaths.get(o2);
			
			Field o1FieldBathsII = c.getDeclaredField("ownership");
			o1FieldBathsII.setAccessible(true);
			Object o1BathsII = o1FieldBaths.get(o1);
			
			Field o2FieldBathsII = c.getDeclaredField("ownership");
			o2FieldBathsII.setAccessible(true);
			Object o2BathsII = o2FieldBaths.get(o2);
			
			return  o1Owner.equals(o2Owner) &&
				o1squareFootage.equals(o2squareFootage) &&
				o1lotNumber.equals(o2lotNumber) &&
				o1Bedrooms.equals(o2Bedrooms) &&
				o1Baths.equals(o2Baths) &&
				o1BathsII.equals(o2BathsII);
			
			
		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
		
		return false;
	}
	
}
