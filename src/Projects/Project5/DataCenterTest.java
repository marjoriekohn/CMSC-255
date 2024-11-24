package Projects.Project5;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

@SuppressWarnings({"rawtypes"})
public class DataCenterTest {
	
	@Test
	public void DataCenter_instanceCountTest(){
		DataCenter testDataCenter = new DataCenter();
		Class c = testDataCenter.getClass();
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
				4, count);
		}
		catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	@Test
	public void DataCenter_instanceVariablesTest(){
		DataCenter testDataCenter = new DataCenter();
		instanceVariablePrivate("constructionFirm",testDataCenter);
		instanceVariablePrivate("constructionCost",testDataCenter);
		instanceVariablePrivate("ITLoad",testDataCenter);
		instanceVariablePrivate("operatingCost",testDataCenter);
		
		instanceVariableStatic("constructionFirm",testDataCenter);
		instanceVariableStatic("constructionCost",testDataCenter);
		instanceVariableStatic("ITLoad",testDataCenter);
		instanceVariableStatic("operatingCost",testDataCenter);
		
		instanceVariableCorrectType("constructionFirm", String.class,testDataCenter);
		instanceVariableCorrectType("constructionCost", double.class,testDataCenter);
		instanceVariableCorrectType("ITLoad", double.class,testDataCenter);
		instanceVariableCorrectType("operatingCost", double.class,testDataCenter);
	}
	
	@Test
	public void DataCenter_defaultConstructorTest() {
		DataCenter testDataCenter = new DataCenter();
		
		testVariable("constructionFirm",testDataCenter,"","When checking the value of constructionFirm we",0);
		testVariable("constructionCost",testDataCenter,0.0,"When checking the value of constructionCost we",0);
		testVariable("ITLoad",testDataCenter,0.0,"When checking the value of ITLoad we",0);
		testVariable("operatingCost",testDataCenter,0.0,"When checking the value of operatingCost we",0);
	}
	
	@Test
	public void DataCenter_parameterizedConstructorTest() {
		DataCenter testDataCenter = new DataCenter("Pacific Bridge Company", 42.8, 276.4, 1738.55);
		
		testVariable("constructionFirm",testDataCenter,"Pacific Bridge Company","When checking the value of constructionFirm we",0);
		testVariable("constructionCost",testDataCenter,42.8,"When checking the value of constructionCost we",0);
		testVariable("ITLoad",testDataCenter,276.4,"When checking the value of ITLoad we",0);
		testVariable("operatingCost",testDataCenter,1738.55,"When checking the value of operatingCost we",0);
		
	}
	
	@Test
	public void DataCenter_toStringTest() {
		DataCenter testDataCenter = new DataCenter("Pacific Bridge Company", 42.8, 276.4, 1738.55);
		assertEquals(  "Pacific Bridge Company 42.80 276.40 1738.55", testDataCenter.toString());
		
		testDataCenter = createDataCenter("Utah Construction Company", 92.2, 950.4, 6259.38);
		assertEquals(  "Utah Construction Company 92.20 950.40 6259.38", testDataCenter.toString());
	}

    /*
    @Test
    public void DataCenter_equalsTest() {
        //new String() is not redundant, it is used to get around String interning.
        DataCenter testDataCenter = createDataCenter(new String("Campbell’s Soup Cans"),new String("Andy Warhol"),1204214.0,new String("1962 W 53rd St"));
        DataCenter testDataCenterClone = createDataCenter(new String("Campbell’s Soup Cans"),new String("Andy Warhol"),1204214.0,new String("1962 W 53rd St"));
        DataCenter testDataCenterOtherClone = createDataCenter(new String("Campbell’s Soup Cans"),new String("Andy Warhol"),1204214.0,new String("1962 W 53rd St"));
        DataCenter otherDataCenter = createDataCenter(new String("Autumn Rhythm"),new String("Jackson Pollock"),2343454.0,new String("1950 W 53rd St"));
        DataCenter thirdDataCenter = createDataCenter(new String("Infinity Mirror Room"),new String("Yayoi Kusama"),1204214.0,new String("1965 W 53rd St"));


        Class c = testDataCenter.getClass();
        //Test if equals takes a parameter of type City and fail them if it does
        try {
            c.getMethod("equals",DataCenter.class);
            fail("DataCenter's equals method should have a single parameter of type Object");
        } catch (NoSuchMethodException ignored) {}

        try {
            Method f = c.getMethod("equals",Object.class);

            //Test given null
            try {
                Object nullReference = null;
                assertFalse("When calling DataCenter's equals method with an argument of null, we", (boolean) f.invoke(testDataCenter, nullReference));
            }
            catch (Exception e){
                fail("When calling DataCenter's equals method with an argument of null, we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test given a non state object
            try{
                assertFalse("When calling DataCenter's equals method with a non DataCenter argument, we", (boolean) f.invoke(testDataCenter,"NotAnDataCenter") );
            }
            catch (Exception e){
                fail("When calling DataCenter's equals method with a non DataCenter argument, we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test given itself //reflexive
            try {
                assertTrue("When calling DataCenter's equals method with an argument of itself, we", (boolean) f.invoke(testDataCenter, testDataCenter));
            }
            catch (Exception e){
                fail("When calling DataCenter's equals method with an argument of itself, we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test symmetric (x.equals(y) == y.equals(x)
            try{
                assertTrue("Symmetric test. Expected x.equals(y) == y.equals(x), was",(boolean) f.invoke(testDataCenter,testDataCenterClone) && (boolean) f.invoke(testDataCenterClone,testDataCenter));
            }
            catch (Exception e){
                fail("When calling DataCenter's equals method with a symmetric test. We expected x.equals(y) == y.equals(x) but we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test transitive (x.equals(y) == y.equals(z) == x.equals(z))
            try{
                assertTrue("Transitive test. Expected x.equals(y) == y.equals(z) == x.equals(z), was",
                        (boolean) f.invoke(testDataCenter,testDataCenterClone) ==
                                (boolean) f.invoke(testDataCenterClone,testDataCenterOtherClone) ==
                                (boolean) f.invoke(testDataCenter,testDataCenterOtherClone)
                );
            }
            catch (Exception e){
                fail("When calling DataCenter's equals method with a transitive test. We expected x.equals(y) == y.equals(z) == x.equals(z) but we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test non equivalent objects
            try{
                assertFalse("When calling DataCenter's equals method with an argument of a logically different DataCenter object, we",(boolean) f.invoke(testDataCenter,otherDataCenter) && (boolean) f.invoke(testDataCenter,thirdDataCenter));
            }
            catch (Exception e){
                fail("When calling DataCenter's equals method with an argument of a logically different DataCenter object, we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

        } catch (NoSuchMethodException e) {
            fail("Something weird went wrong. " + e.toString());
        }
    }
    */
	
	@Test
	public void DataCenter_getConstructionFirmTest() {
		DataCenter testDataCenter = new DataCenter("Pacific Bridge Company", 42.8, 276.4, 1738.55);
		assertEquals("With an DataCenter object who's constructionFirm instance variable is Pacific Bridge Company, when calling getConstructionFirm we","Pacific Bridge Company",testDataCenter.getConstructionFirm());
	}
	
	@Test
	public void DataCenter_setConstructionFirmTest() {
		DataCenter testDataCenter = new DataCenter("Pacific Bridge Company", 42.8, 276.4, 1738.55);
		testDataCenter.setConstructionFirm("Bechtel-Kaiser");
		testVariable("constructionFirm",testDataCenter,"Bechtel-Kaiser","After calling DataCenter's setConstructionFirm method with an argument of Bechtel-Kaiser, for the value of constructionFirm we",0);
	}
	
	@Test
	public void DataCenter_getConstructionCostTest() {
		DataCenter testDataCenter = new DataCenter("Pacific Bridge Company", 42.8, 276.4, 1738.55);
		assertEquals("With an DataCenter object who's constructionCost instance variable is 42.8, when calling getConstructionCost we",42.8,testDataCenter.getConstructionCost(),.001);
	}
	
	@Test
	public void DataCenter_setConstructionCostTest() {
		DataCenter testDataCenter = new DataCenter("Pacific Bridge Company", 42.8, 276.4, 1738.55);
		testDataCenter.setConstructionCost(9.85);
		testVariable("constructionCost",testDataCenter,9.85,"After calling DataCenter's setConstructionCost method with an argument of 9.85, for the value of constructionCost we",0);
	}
	
	@Test
	public void DataCenter_getITLoadTest() {
		DataCenter testDataCenter = new DataCenter("Pacific Bridge Company", 42.8, 276.4, 1738.55);
		assertEquals("With an DataCenter object who's ITLoad instance variable is 276.4, when calling getITLoad we",276.4,testDataCenter.getITLoad(),.001);
	}
	
	@Test
	public void DataCenter_setITLoadTest() {
		DataCenter testDataCenter = new DataCenter("Pacific Bridge Company", 42.8, 276.4, 1738.55);
		testDataCenter.setITLoad(401.1);
		testVariable("ITLoad",testDataCenter,401.1,"After calling DataCenter's setITLoad method with an argument of 401.1, for the value of ITLoad we",0);
	}
	
	@Test
	public void DataCenter_getOperatingCostTest() {
		DataCenter testDataCenter = new DataCenter("Pacific Bridge Company", 42.8, 276.4, 1738.55);
		assertEquals("With an DataCenter object who's operatingCost instance variable is 1738.55, when calling getOperatingCost we",1738.55,testDataCenter.getOperatingCost(),.001);
	}
	
	@Test
	public void DataCenter_setOperatingCostTest() {
		DataCenter testDataCenter = new DataCenter("Pacific Bridge Company", 42.8, 276.4, 1738.55);
		testDataCenter.setOperatingCost(4096.3);
		testVariable("operatingCost",testDataCenter,4096.3,"After calling DataCenter's setOperatingCost method with an argument of 4096.3, for the value of operatingCost we",0);
	}
	
	
	
	private DataCenter createDataCenter(String aConstructionFirm, double aConstructionCost, double aITLoad, double aOperatingCost){
		DataCenter testDataCenter = new DataCenter();
		Class c = testDataCenter.getClass();
		
		try {
			Field constructionFirm = c.getDeclaredField("constructionFirm");
			constructionFirm.setAccessible(true);
			constructionFirm.set(testDataCenter, aConstructionFirm);
			
			Field constructionCost = c.getDeclaredField("constructionCost");
			constructionCost.setAccessible(true);
			constructionCost.set(testDataCenter, aConstructionCost);
			
			Field value = c.getDeclaredField("ITLoad");
			value.setAccessible(true);
			value.set(testDataCenter, aITLoad);
			
			Field operatingCost = c.getDeclaredField("operatingCost");
			operatingCost.setAccessible(true);
			operatingCost.set(testDataCenter, aOperatingCost);
			
			
		} catch (Exception e) {
			fail(e.toString());
		}
		
		return testDataCenter;
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
			
			assertEquals("Your instance variables must NOT be static.", false,
				Modifier.isStatic(c.getDeclaredField(aField).getModifiers()));
			
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
	
	private void testVariable(String aField, Object testObject, Object expected, String message, int descendantLevel){
		Class c = testObject.getClass();
		
		for(int i = 0; i < descendantLevel; i++){
			c = c.getSuperclass();
		}
		
		try {
			Field field = c.getDeclaredField(aField);
			field.setAccessible(true);
			Object fieldITLoad = field.get(testObject);
			
			if(expected == null){
				assertNull(message,fieldITLoad);
			}
			//If class is a double we have a special Junit assert to run
			else if(expected.getClass().equals(Double.class)){
				double doubleFieldITLoad = (double) fieldITLoad;
				double doubleExpected = (double) expected;
				assertEquals(message, doubleExpected, doubleFieldITLoad, .001);
			}
			//Array of some kind yay
			else if(expected.getClass().isArray()){
			
			}
			else if(expected.getClass().equals(ArrayList.class)){
			
			}
			else{
				assertEquals(message, expected, fieldITLoad);
			}
			
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
}
