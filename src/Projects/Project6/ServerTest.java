package Projects.Project6;


import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

@SuppressWarnings({"rawtypes","all"})
public class ServerTest {
	
	@Test
	public void Server_isAbstractTest(){
		Class c = Server.class;
		assertTrue("Server must be an abstract class.",Modifier.isAbstract(c.getModifiers()));
	}
	
	@Test
	public void Server_instanceCountTest(){
		Class c = Server.class;
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
	public void Server_instanceVariablesTest(){
		Class testServer = Server.class;
		
		instanceVariablePrivate("brand",testServer);
		instanceVariablePrivate("compareNumYears",testServer);
		
		instanceVariableStatic("brand",testServer,false);
		instanceVariableStatic("compareNumYears",testServer,true);
		
		instanceVariableCorrectType("brand", String.class,testServer);
		instanceVariableCorrectType("compareNumYears", int.class,testServer);
	}
	
	@Test
	public void Server_parameterizedConstructorTest() {
		Server testServer = new Server("Compaq") {
			public double getOperatingCost(int years) {
				return 0.0;
			}
		};
		
		testVariable("brand", testServer, "Compaq", "When checking the value of brand we", 1);
		testVariable("compareNumYears", testServer, 5, "When checking the value of compareNumYears we", 1);
	}
	
	@Test
	public void Server_toStringTest() {
		Server testServer = createServer("Compaq");
		
		assertEquals(  "Compaq", testServer.toString());
		
		testServer = createServer("Gateway");
		assertEquals(  "Gateway", testServer.toString());
	}
	
	@Test
	public void Server_getOperatingCostTest(){
		Class c = Server.class;
		try {
			Method m = c.getMethod("getOperatingCost",int.class);
			assertTrue("getOperatingCost must be an abstract method.",Modifier.isAbstract(m.getModifiers()));
			assertTrue("getOperatingCost must be a public method.",Modifier.isPublic(m.getModifiers()));
			assertEquals("getOperatingCost must have a return type of double.", m.getReturnType(), double.class);
			
			
		} catch (NoSuchMethodException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " method.\nIs your method named correctly? Does it have the correct parameters?");
		}
		assertTrue("Server must be an abstract class.",Modifier.isAbstract(c.getModifiers()));
	}
	
	@Test
	public void Server_isComparableTest(){
		Class c = Server.class;
		//Check if Server implements the Comparable interface
		if(Comparable.class.isAssignableFrom(c)){
			//Check if the compareTo method is implemented correctly
			//The access modifier and return type are kinda redundent but getMethod will fail if compareTo doesn't take in a Server object
			//This confirms Comparable is implemented with the correct type supplied to the generic type
			//lol also redundent cause this code won't compile at all if the comparable interface isn't implemented correctly
			try {
				Method m = c.getMethod("compareTo",c);
				assertTrue("compareTo must be a public method.",Modifier.isPublic(m.getModifiers()));
				assertEquals("compareTo must have a return type of int.", m.getReturnType(), int.class);
				
				//Implement bad getOperatingCost methods for quick testing
				//ServerB will always return a larger double value than serverA
				//ServerC is the same as ServerA
				
				Server serverA = new Server("Apple"){
					@Override
					public double getOperatingCost(int years) {
						return getCompareNumYears();
					}
				};
				
				Server serverB = new Server("Apple"){
					@Override
					public double getOperatingCost(int years) {
						return getCompareNumYears() + 10;
					}
				};
				
				Server serverC = new Server("Apple"){
					@Override
					public double getOperatingCost(int years) {
						return getCompareNumYears();
					}
				};
				
				// < 0
				serverA.compareTo(serverB);
				// > 0
				serverB.compareTo(serverA);
				// == 0
				serverA.compareTo(serverC);
				
				assertTrue("When *this* server's getOperatingCost returns a double value smaller than the given server object's getOperatingCost, compareTo should return a value less than zero, which your compareTo method did not do.",serverA.compareTo(serverB) < 0);
				
				assertTrue("When *this* server's getOperatingCost returns a double value larger than the given server object's getOperatingCost, compareTo should return a value greater than zero, which your compareTo method did not do.",serverB.compareTo(serverA) > 0);
				
				assertTrue("When *this* server's getOperatingCost returns a double value equal to the given server object's getOperatingCost, compareTo should return zero, which your compareTo method did not do.",serverA.compareTo(serverC) == 0);
				
				
			} catch (NoSuchMethodException e) {
				fail("Could not find the " + e.getLocalizedMessage() + " method.\nIs your method named correctly? Does it have the correct parameters?");
			}
			
		}
		else {
			fail("Server must implement the Comparable interface");
		}
	}

    /*
    @Test
    public void Server_equalsTest() {
        //new String() is not redundant, it is used to get around String interning.
        Server testServer = createServer(new String("Campbell’s Soup Cans"),new String("Andy Warhol"),1204214.0,new String("1962 W 53rd St"));
        Server testServerClone = createServer(new String("Campbell’s Soup Cans"),new String("Andy Warhol"),1204214.0,new String("1962 W 53rd St"));
        Server testServerOtherClone = createServer(new String("Campbell’s Soup Cans"),new String("Andy Warhol"),1204214.0,new String("1962 W 53rd St"));
        Server otherServer = createServer(new String("Autumn Rhythm"),new String("Jackson Pollock"),2343454.0,new String("1950 W 53rd St"));
        Server thirdServer = createServer(new String("Infinity Mirror Room"),new String("Yayoi Kusama"),1204214.0,new String("1965 W 53rd St"));


        Class c = testServer.getClass();
        //Test if equals takes a parameter of type City and fail them if it does
        try {
            c.getMethod("equals",Server.class);
            fail("Server's equals method should have a single parameter of type Object");
        } catch (NoSuchMethodException ignored) {}

        try {
            Method f = c.getMethod("equals",Object.class);

            //Test given null
            try {
                Object nullReference = null;
                assertFalse("When calling Server's equals method with an argument of null, we", (boolean) f.invoke(testServer, nullReference));
            }
            catch (Exception e){
                fail("When calling Server's equals method with an argument of null, we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test given a non state object
            try{
                assertFalse("When calling Server's equals method with a non Server argument, we", (boolean) f.invoke(testServer,"NotAnServer") );
            }
            catch (Exception e){
                fail("When calling Server's equals method with a non Server argument, we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test given itself //reflexive
            try {
                assertTrue("When calling Server's equals method with an argument of itself, we", (boolean) f.invoke(testServer, testServer));
            }
            catch (Exception e){
                fail("When calling Server's equals method with an argument of itself, we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test symmetric (x.equals(y) == y.equals(x)
            try{
                assertTrue("Symmetric test. Expected x.equals(y) == y.equals(x), was",(boolean) f.invoke(testServer,testServerClone) && (boolean) f.invoke(testServerClone,testServer));
            }
            catch (Exception e){
                fail("When calling Server's equals method with a symmetric test. We expected x.equals(y) == y.equals(x) but we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test transitive (x.equals(y) == y.equals(z) == x.equals(z))
            try{
                assertTrue("Transitive test. Expected x.equals(y) == y.equals(z) == x.equals(z), was",
                        (boolean) f.invoke(testServer,testServerClone) ==
                                (boolean) f.invoke(testServerClone,testServerOtherClone) ==
                                (boolean) f.invoke(testServer,testServerOtherClone)
                );
            }
            catch (Exception e){
                fail("When calling Server's equals method with a transitive test. We expected x.equals(y) == y.equals(z) == x.equals(z) but we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

            //Test non equivalent objects
            try{
                assertFalse("When calling Server's equals method with an argument of a logically different Server object, we",(boolean) f.invoke(testServer,otherServer) && (boolean) f.invoke(testServer,thirdServer));
            }
            catch (Exception e){
                fail("When calling Server's equals method with an argument of a logically different Server object, we got an exception. Try using the Debugger to figure out the issue! Place a debug point at the start of your equals method and debug this test case!");
            }

        } catch (NoSuchMethodException e) {
            fail("Something weird went wrong. " + e.toString());
        }
    }
    */
	
	@Test
	public void Server_getBrandTest() {
		Server testServer = createServer("Compaq");
		
		assertEquals("With an Server object who's brand instance variable is Compaq, when calling getBrand we","Compaq",testServer.getBrand());
	}
	
	@Test
	public void Server_setBrandTest() {
		Server testServer = createServer("Compaq");
		
		testServer.setBrand("Gateway");
		testVariable("brand",testServer,"Gateway","After calling Server's setBrand method with an argument of Gateway, for the value of brand we",1);
	}
	
	@Test
	public void Server_getCompareNumYearsTest() {
		assertEquals("With the Server class' compareNumYears instance variable being 5, when calling getCompareNumYears we",5,Server.getCompareNumYears());
	}
	
	@Test
	public void Server_setCompareNumYearsTest() {
		
		Server.setCompareNumYears(20);
		testVariable("compareNumYears",createServer("Compaq"),20,"After calling Server's setCompareNumYears method with an argument of 20, for the value of compareNumYears we",1);
		
		//Because this is a static variable, need to set it's value back to the default value or other tests will get confused
		//And we can guarentee execution order of tests
		try {
			Field numYears = Server.class.getDeclaredField("compareNumYears");
			numYears.setAccessible(true);
			numYears.set(createServer("Compaq"),5);
		} catch (NoSuchFieldException e) {
			fail("compareNumYears doesn't exist. This error shouldn't happen. Please contact your instructor.");
		} catch (IllegalAccessException e) {
			fail("compareNumYears couldn't be accessed?? This error *realy* shouldn't happen. Please contact your instructor.");
		}
	}
	
	@Test
	public void Server_CompareNumYearsGetterSetterStaticTest() {
		
		Class c = Server.class;
		try {
			Method m = c.getMethod("getCompareNumYears");
			assertTrue("getCompareNumYears must be a static method.",Modifier.isStatic(m.getModifiers()));
			assertTrue("getCompareNumYears must be a public method.",Modifier.isPublic(m.getModifiers()));
			assertEquals("getCompareNumYears must have a return type of int.", m.getReturnType(), int.class);
			
		} catch (NoSuchMethodException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " method.\nIs your method named correctly? Does it have the correct parameters?");
		}
		
		try {
			Method m = c.getMethod("setCompareNumYears",int.class);
			assertTrue("setCompareNumYears must be a static method.",Modifier.isStatic(m.getModifiers()));
			assertTrue("setCompareNumYears must be a public method.",Modifier.isPublic(m.getModifiers()));
			assertEquals("setCompareNumYears must have a return type of void.", m.getReturnType(), void.class);
			
		} catch (NoSuchMethodException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " method.\nIs your method named correctly? Does it have the correct parameters?");
		}
		
	}
	
	
	
	
	private Server createServer(String aBrand){
		Server testServer = new Server("Compaq") {
			public double getOperatingCost(int years) {
				return 0.0;
			}
		};
		Class c = testServer.getClass().getSuperclass();
		
		try {
			Field brand = c.getDeclaredField("brand");
			brand.setAccessible(true);
			brand.set(testServer, aBrand);
			
		} catch (Exception e) {
			fail(e.toString());
		}
		
		return testServer;
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
			Object fieldCompareNumYears = field.get(testObject);
			
			if(expected == null){
				assertNull(message,fieldCompareNumYears);
			}
			//If class is a double we have a special Junit assert to run
			else if(expected.getClass().equals(Double.class)){
				double doubleFieldCompareNumYears = (double) fieldCompareNumYears;
				double doubleExpected = (double) expected;
				assertEquals(message, doubleExpected, doubleFieldCompareNumYears, .001);
			}
			//Array of some kind yay
			else if(expected.getClass().isArray()){
			
			}
			else if(expected.getClass().equals(ArrayList.class)){
			
			}
			else{
				assertEquals(message, expected, fieldCompareNumYears);
			}
			
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
}
