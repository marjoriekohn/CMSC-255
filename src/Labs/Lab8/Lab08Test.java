package Labs.Lab8;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Lab08Test {
	
	private Class Test_Fan;
	
	@Before
	public void setUpStreams() {
		Test_Fan = Fan.class;
	}
	
	
	@Test
	public void InstanceConstantsCountTest() {
		try {
			assertEquals(
				"You must only have the instance variables specified. When looking at the number of instance variables we",
				7, Test_Fan.getDeclaredFields().length);
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	@Test
	public void ConstantsTest() {
		Class c = Test_Fan;
		try {
			c.getDeclaredField("SLOW");
			c.getDeclaredField("MEDIUM");
			c.getDeclaredField("FAST");
			
			assertTrue("You must make your constant variables public.", Modifier.isPublic(c.getDeclaredField("SLOW").getModifiers()));
			assertTrue("You must make your constant variables public.", Modifier.isPublic(c.getDeclaredField("MEDIUM").getModifiers()));
			assertTrue("You must make your constant variables public.", Modifier.isPublic(c.getDeclaredField("FAST").getModifiers()));
			
			assertTrue("Your constant variables must be static.", Modifier.isStatic(c.getDeclaredField("SLOW").getModifiers()));
			assertTrue("Your constant variables must be static.", Modifier.isStatic(c.getDeclaredField("MEDIUM").getModifiers()));
			assertTrue("Your constant variables must be static.", Modifier.isStatic(c.getDeclaredField("FAST").getModifiers()));
			
			assertTrue("Your constant variables must be final.", Modifier.isFinal(c.getDeclaredField("SLOW").getModifiers()));
			assertTrue("Your constant variables must be final.", Modifier.isFinal(c.getDeclaredField("MEDIUM").getModifiers()));
			assertTrue("Your constant variables must be final.", Modifier.isFinal(c.getDeclaredField("FAST").getModifiers()));
			
			assertEquals("You must make the SLOW constant variable of type int.", (int.class),
				c.getDeclaredField("SLOW").getType());
			assertEquals("You must make the MEDIUM constant variable of type int.", (int.class),
				c.getDeclaredField("MEDIUM").getType());
			assertEquals("You must make the FAST constant variable of type int.", (int.class),
				c.getDeclaredField("FAST").getType());
			
		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage().toString() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	@Test
	public void InstanceVariablesTest() {
		Object testFan = createDefaultFanByPass();
		
		instanceVariablePrivate("speed", testFan);
		instanceVariablePrivate("on", testFan);
		instanceVariablePrivate("radius", testFan);
		instanceVariablePrivate("color", testFan);
		
		instanceVariableStatic("speed", testFan);
		instanceVariableStatic("on", testFan);
		instanceVariableStatic("radius", testFan);
		instanceVariableStatic("color", testFan);
		
		instanceVariableCorrectType("speed", int.class, testFan);
		instanceVariableCorrectType("on", boolean.class, testFan);
		instanceVariableCorrectType("radius", double.class, testFan);
		instanceVariableCorrectType("color", String.class, testFan);
	}
	
	@Test
	public void fanDefaultConstructorTest() {
		Object testFan = createDefaultObject(true);
		
		testVariable("speed", testFan, 1, "When checking the value of speed we", 0);
		testVariable("on", testFan, false, "When checking the value of on we", 0);
		testVariable("radius", testFan, 5.0, "When checking the value of radius we", 0);
		testVariable("color", testFan, "blue", "When checking the value of color we", 0);
	}
	
	@Test
	public void parameterizedFanConstructor() {
		Object testFan;
		try {
			Constructor c = getConstructor(false, int.class, boolean.class, double.class, String.class);
			testFan = c.newInstance(3, false, 25.0, "Yellow");
		} catch (NoSuchMethodException e) {
			throw new AssertionError("Could not find a paramterized constructor of " +
				"Fan(int, boolean, double, String).");
		} catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
			throw new AssertionError("Unexpected error when calling your parameterized constructor. " +
				"Please contact an instructor.");
		}
		testVariable("speed", testFan, 3, "When checking the value of speed we", 0);
		testVariable("on", testFan, false, "When checking the value of on we", 0);
		testVariable("radius", testFan, 25.0, "When checking the value of radius we", 0);
		testVariable("color", testFan, "Yellow", "When checking the value of color we", 0);
	}
	
	@Test
	public void getSpeedTest() {
		Object testFan = createFan(3, false, 25.0, "Yellow");
		int actual = invokeAcc("getSpeed", testFan);
		assertEquals("With a Fan object who's speed instance variable is 3, when calling getSpeed we",
			3, actual);
	}
	
	@Test
	public void setSpeedTest() {
		Object testFan = createFan(3, false, 25.0, "Yellow");
		invokeMut(getMutator("setSpeed", int.class), testFan, 2);
		testVariable("speed", testFan, 2, "After calling Fan's setSpeed method with an argument of 2, for the value of speed we", 0);
	}
	
	@Test
	public void isOnTest() {
		Object testFan = createFan(3, false, 25.0, "Yellow");
		boolean expected = invokeAcc("isOn", testFan);
		assertFalse("With a Fan object who's on instance variable is false, when calling isOn we", expected);
	}
	
	@Test
	public void setOnTest() {
		Object testFan = createFan(3, false, 25.0, "Yellow");
		invokeMut(getMutator("setOn", boolean.class), testFan, true);
		testVariable("on", testFan, true, "After calling Fan's setOn method with an argument of true, for the value of on we", 0);
	}
	
	@Test
	public void getRadiusTest() {
		Object testFan = createFan(3, false, 25.0, "Yellow");
		double expected = invokeAcc("getRadius", testFan);
		assertEquals("With a Fan object who's radius instance variable is 25.0, when calling getRadius we", 25.0,
			expected, .01);
	}
	
	@Test
	public void setRadiusTest() {
		Object testFan = createFan(3, false, 25.0, "Yellow");
		invokeMut(getMutator("setRadius", double.class), testFan, 15.55);
		testVariable("radius", testFan, 15.55, "After calling Fan's setRadius method with an argument of 15.55, for the value of radius we", 0);
	}
	
	@Test
	public void getColorTest() {
		Object testFan = createFan(3, false, 25.0, "Yellow");
		String expected = invokeAcc("getColor", testFan);
		assertEquals("With a Fan object who's color instance variable is Yellow, when calling getColor we", "Yellow", expected);
	}
	
	@Test
	public void setColorTest() {
		Object testFan = createFan(3, false, 25.0, "Yellow");
		invokeMut(getMutator("setColor", String.class), testFan, "Red");
		testVariable("color", testFan, "Red", "After calling Fan's setColor method with an argument of Red, for the value of color we", 0);
	}
	
	@Test
	public void toStringTest() {
		Object testFan = createFan(3, false, 25.0, "Yellow");
		assertEquals("fan is off", testFan.toString());
		
		testFan = createFan(3, true, 25.0, "Yellow");
		assertEquals("fan is 3, Yellow, and size 25.0", testFan.toString());
	}
	
	@Test
	public void fanTestTest() {
		Class fanTestJava = classCheck("FanTest");
		String className = fanTestJava.getName();
		className = className.substring(className.lastIndexOf(".") + 1).trim();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		ByteArrayOutputStream errContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		
		try {
			fanTestJava.getDeclaredMethod("main", String[].class).invoke(null, (Object) null);
		} catch (InvocationTargetException e) {
			throw new AssertionError(className + " did not compile correctly. Please double check your code.");
		} catch (Exception other) {
			//catch (NoSuchMethodException e)
			//catch (IllegalAccessException e)
			throw new AssertionError("Unanticipated error in your code. Please contact instructor before submitting again");
		}
		String rawOutput = outContent.toString();
		int flags =  Pattern.MULTILINE + Pattern.CASE_INSENSITIVE;
		Pattern first = Pattern.compile(" *fan *is *3 *, * Yellow *, *and *size * 10.0 *", flags);
		Pattern second = Pattern.compile(" *fan *is *off *", flags);
		assertTrue("Could not find the first fan object" +
			"(\"fan is 3, Yellow, and size 10.0\") in your output", first.matcher(rawOutput).find());
		assertTrue("Could not find the second fan object " +
			"(\"fan is off\"))", second.matcher(rawOutput).find());
	}
	
	private Class classCheck(String newClass) {
		Class success;
		String canonicalName = this.getClass().getCanonicalName();
		String currentPackage = canonicalName.substring(0,canonicalName.length() - 1 - (new StringBuilder(canonicalName).reverse().toString().indexOf('.')));
		currentPackage = currentPackage.replaceFirst(".tests","");
		String trimedClass = newClass.substring(newClass.lastIndexOf(".") + 1).trim();
		try {
			success = Class.forName(currentPackage + "." + trimedClass);
			
		} catch (ClassNotFoundException noClass) {
			throw new AssertionError(trimedClass + ".java not found. Did you name your file correctly?");
		}
		return success;
	}
	
	private Constructor getConstructor(boolean failOnScope, Class... args) throws NoSuchMethodException {
		Constructor constructor;
		try {
			constructor = Test_Fan.getDeclaredConstructor(args);
			constructor.setAccessible(true);
			assertTrue("Your constructor has the wrong access modifier. " +
					"Please make sure your constructor is accessible from anywhere.",
				Modifier.isPublic(constructor.getModifiers()) || !failOnScope);
		} catch (NoSuchMethodException e) {
			throw (e);
		} catch (Exception e) {
			throw new AssertionError("An unexpected error occurred in your code. " +
				"Please contact an instructor for help");
		}
		return constructor;
	}
	
	private Object createDefaultObject(boolean failOnPrivate) {
		Object fan_object;
		try {
			Constructor constructor = getConstructor(false);
			constructor.setAccessible(true);
			fan_object = constructor.newInstance();
			assertTrue("Your default constructor has the wrong access modifier. " +
					"Please make sure your default constructor is accessible from anywhere.",
				Modifier.isPublic(constructor.getModifiers()) || !failOnPrivate);
		} catch (InvocationTargetException | NoSuchMethodException e) {
			throw new AssertionError("We could not find a default constructor. " +
				"Please make sure you followed the lab specification");
		} catch (Exception e) {
			throw new AssertionError("An unexpected error occurred in your code. " +
				"Please contact an instructor for help");
		}
		return fan_object;
	}
	
	private Method getMutator(String methodName, Class... paramTypes) {
		Method studentMethod;
		String message0 = "Could not find method " + methodName + " ";
		try {
			studentMethod = Test_Fan.getDeclaredMethod(methodName, paramTypes);
			studentMethod.setAccessible(true);
			if (!Modifier.isPublic(studentMethod.getModifiers())) {
				throw new AssertionError(message0 + "because the method was not public");
			}
		} catch (NoSuchMethodException e) {
			throw new AssertionError(message0 + "with appropriate method signature. " +
				"Make sure the method name meets specification");
		}
		return studentMethod;
	}
	
	private Method getAccessor(String methodName) {
		Method studentMethod;
		String message0 = "Could not find method " + methodName + " ";
		try {
			studentMethod = Test_Fan.getDeclaredMethod(methodName);
			studentMethod.setAccessible(true);
			if (!Modifier.isPublic(studentMethod.getModifiers())) {
				throw new AssertionError(message0 + "because the method was not public");
			}
		} catch (NoSuchMethodException e) {
			throw new AssertionError(message0 + "with appropriate method signature. " +
				"Make sure your method names meet specification");
		}
		return studentMethod;
	}
	
	private void invokeMut(Method mutator, Object ref, Object... args) {
		try {
			mutator.invoke(ref, args);
		} catch (Exception e) {
			throw new AssertionError("An unexpected error has occurred. Please contact an instructor.");
		}
	}
	
	private <T> T invokeAcc(String methodName, Object ref) {
		try {
			return (T) getAccessor(methodName).invoke(ref);
		} catch (Exception e) {
			throw new AssertionError("An unexpected error has occurred. Please contact an instructor.");
		}
	}
	
	private Object createDefaultFanByPass() {
		Object testFan;
		try {
			testFan = createDefaultObject(false);
		} catch (AssertionError | Exception e) {
			try {
				Constructor c = getConstructor(false, int.class, boolean.class, double.class, String.class);
				testFan = c.newInstance(3, false, 25.0, "Yellow");
			} catch (Exception e2) {
				throw new AssertionError("Could not find of the constructors specified in the Lab specification. " +
					"Make sure you have implemented at least one of the constructors as specified");
			}
		}
		return testFan;
	}
	
	private Object createFan(int aSpeed, boolean isOn, double aRadius, String aColor) {
		Object testFan = createDefaultFanByPass();
		Class c = testFan.getClass();
		
		try {
			Field speed = c.getDeclaredField("speed");
			speed.setAccessible(true);
			speed.set(testFan, aSpeed);
			
			Field on = c.getDeclaredField("on");
			on.setAccessible(true);
			on.set(testFan, isOn);
			
			Field radius = c.getDeclaredField("radius");
			radius.setAccessible(true);
			radius.set(testFan, aRadius);
			
			Field color = c.getDeclaredField("color");
			color.setAccessible(true);
			color.set(testFan, aColor);
			
		} catch (Exception e) {
			fail(e.toString());
		}
		
		return testFan;
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
			
			assertFalse("Your instance variables must NOT be static.",
				Modifier.isStatic(c.getDeclaredField(aField).getModifiers()));
			
		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	private void instanceVariableCorrectType(String aField, Class<?> aClass, Object testObject) {
		Class c = testObject.getClass();
		try {
			c.getDeclaredField(aField);
			
			assertEquals("You must make the speed instance variable of type" + aClass.toString() + ".", aClass, c.getDeclaredField(aField).getType());
			
		} catch (NoSuchFieldException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
		} catch (Exception e) {
			fail("Something weird went wrong");
		}
	}
	
	private void testVariable(String aField, Object testObject, Object expected, String message, int descendantLevel) {
		Class c = testObject.getClass();
		
		for (int i = 0; i < descendantLevel; i++) {
			c = c.getSuperclass();
		}
		
		try {
			Field field = c.getDeclaredField(aField);
			field.setAccessible(true);
			Object fieldValue = field.get(testObject);
			
			if (expected == null) {
				assertNull(message, fieldValue);
			}
			//If class is a double we have a special Junit assert to run
			else if (expected.getClass().equals(Double.class)) {
				double doubleFieldValue = (double) fieldValue;
				double doubleExpected = (double) expected;
				assertEquals(message, doubleExpected, doubleFieldValue, .01);
			}
			//Array of some kind yay
			else if (expected.getClass().isArray()) {
				System.out.println("Array of some kind...yay...");
				//TODO: ???
			} else {
				assertEquals(message, expected, fieldValue);
			}
			
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
}
