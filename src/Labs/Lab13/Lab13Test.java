package Labs.Lab13;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;


import static org.junit.Assert.*;

@SuppressWarnings({"rawtypes","all"})
public class Lab13Test {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}
	
	//Test for Sellable interface
	@Test
	public void Sellable_getPriceTest(){
		Class c = Sellable.class;
		try {
			Method m = c.getMethod("getPrice");
			assertTrue("getPrice must be an abstract method.",Modifier.isAbstract(m.getModifiers()));
			assertTrue("getPrice must be a public method.",Modifier.isPublic(m.getModifiers()));
			assertEquals("getPrice must have a return type of double.", m.getReturnType(), double.class);
			
			
		} catch (NoSuchMethodException e) {
			fail("Could not find the " + e.getLocalizedMessage() + " method.\nIs your method named correctly? Does it have the correct parameters?");
		}
		assertTrue("Sellable must be an interface.",Modifier.isInterface(c.getModifiers()));
	}
	
	//Test for Lab13 class
	@Test
	public void Lab13_mainMethodTest(){
		Lab13.main(new String[] {""});
		String rawOutput = outContent.toString().replaceAll("\r?\n","\n");
		String expected = "Shopping Cart Contents:\n" +
		                   "\tCanned Sweet Corn,4.0,24,7.50,10,0.75\n" +
		                   "\tEddy's Ice Cream,4.8,276,19.95,5,3.99\n" +
		                   "\tGrape Tomatoes,3.7,458,2.85,3.20,0.89\n" +
		                   "\tBananas,4.1,167,1.05,1.40,0.75\n" +
		                   "\tLG Smart Washer,4.2,37,659.62,599.65,2\n" +
		                   "Sorted Cart Contents:\n" +
		                   "\tBananas,4.1,167,1.05,1.40,0.75\n" +
		                   "\tGrape Tomatoes,3.7,458,2.85,3.20,0.89\n" +
		                   "\tCanned Sweet Corn,4.0,24,7.50,10,0.75\n" +
		                   "\tEddy's Ice Cream,4.8,276,19.95,5,3.99\n" +
		                   "\tLG Smart Washer,4.2,37,659.62,599.65,2\n" +
		                   "Shopping Cart Total: $690.96\n";
		assertEquals("When calling Lab13's main method, we",expected,rawOutput);
		
	}
	
	//Tests for Product abstract class
	@Test
	public void Product_isAbstractTest(){
		Class c = Product.class;
		assertTrue("Product must be an abstract class.", Modifier.isAbstract(c.getModifiers()));
	}
	
	@Test
	public void Product_implementsSellableTest() {
		Product testProduct = new Product("TestProduct",4.2,255) {
			public double getPrice() {
				return 0.0;
			}
		};
		assertTrue("When testing if Product implements Sellable, we", (testProduct instanceof Sellable));
	}
	
	@Test
	public void Product_instanceCountTest(){
		Class c = Product.class;
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
	public void Product_instanceVariablesTest(){
		Class testProduct = Product.class;
		
		instanceVariablePrivate("name",testProduct);
		instanceVariablePrivate("rating",testProduct);
		instanceVariablePrivate("numRatings",testProduct);
		
		instanceVariableStatic("name",testProduct,false);
		instanceVariableStatic("rating",testProduct,false);
		instanceVariableStatic("numRatings",testProduct,false);
		
		instanceVariableCorrectType("name", String.class,testProduct);
		instanceVariableCorrectType("rating", double.class,testProduct);
		instanceVariableCorrectType("numRatings", int.class,testProduct);
	}
	
	@Test
	public void Product_parameterizedConstructorTest() {
		Product testProduct = new Product("TestProduct",4.2,255) {
			public double getPrice() {
				return 0.0;
			}
		};
		
		//DescendantLevel is 1 here, i *think* because testProduct *technically* stores a subclass of Product, an annoymous object we create which subclasses Product and implements it's abstract interface
		testVariable("name", testProduct, "TestProduct", "When checking the value of name we", 1);
		testVariable("rating", testProduct, 4.2, "When checking the value of rating we", 1);
		testVariable("numRatings", testProduct, 255, "When checking the value of rating we", 1);
		
		//Test that aNumRatings is set to 0 if constructor is given a negative value. Also test that rating becomes 0 if this happens
		
		testProduct = new Product("TestProduct",4.2,-5) {
			public double getPrice() {
				return 0.0;
			}
		};
		
		testVariable("name", testProduct, "TestProduct", "Calling the Product constructor with values: \"TestProduct\",4.2,-5 when checking the value of name we", 1);
		testVariable("rating", testProduct, 0.0, "Calling the Product constructor with values: \"TestProduct\",4.2,-5 when checking the value of rating we", 1);
		testVariable("numRatings", testProduct, 0, "Calling the Product constructor with values: \"TestProduct\",4.2,-5 when checking the value of rating we", 1);
	}
	
	@Test
	public void Product_toStringTest() {
		Product testProduct = createProduct("TestProduct",4.2,255);
		
		assertEquals(  "TestProduct,4.2,255,19.95", testProduct.toString());
		
		testProduct = createProduct("brat",5,200000);
		assertEquals(  "brat,5.0,200000,19.95", testProduct.toString());
	}
	
	@Test
	public void Product_isComparableTest(){
		Class c = Product.class;
		//Check if Product implements the Comparable interface
		if(Comparable.class.isAssignableFrom(c)){
			//Check if the compareTo method is implemented correctly
			//The access modifier and return type are kinda redundent but getMethod will fail if compareTo doesn't take in a Product object
			//This confirms Comparable is implemented with the correct type supplied to the generic type
			//lol also redundent cause this code won't compile at all if the comparable interface isn't implemented correctly
			try {
				Method m = c.getMethod("compareTo",c);
				assertTrue("compareTo must be a public method.",Modifier.isPublic(m.getModifiers()));
				assertEquals("compareTo must have a return type of int.", m.getReturnType(), int.class);
				
				//Implement bad getPrice methods for quick testing
				//ProductB will always return a larger double value than productA
				//ProductC is the same as ProductA
				
				Product productA = new Product("",0,0){
					@Override
					public double getPrice() {
						return 100.0;
					}
				};
				
				Product productB = new Product("",0,0){
					@Override
					public double getPrice() {
						return 1000.0;
					}
				};
				
				Product productC = new Product("",0,0){
					@Override
					public double getPrice() {
						return 100.0;
					}
				};
				
				// < 0
				productA.compareTo(productB);
				// > 0
				productB.compareTo(productA);
				// == 0
				productA.compareTo(productC);
				
				assertTrue("When *this* product's getPrice returns a double value smaller than the given product object's getPrice, compareTo should return a value less than zero, which your compareTo method did not do.",productA.compareTo(productB) < 0);
				
				assertTrue("When *this* product's getPrice returns a double value larger than the given product object's getPrice, compareTo should return a value greater than zero, which your compareTo method did not do.",productB.compareTo(productA) > 0);
				
				assertTrue("When *this* product's getPrice returns a double value equal to the given product object's getPrice, compareTo should return zero, which your compareTo method did not do.",productA.compareTo(productC) == 0);
				
				
			} catch (NoSuchMethodException e) {
				fail("Could not find the " + e.getLocalizedMessage() + " method.\nIs your method named correctly? Does it have the correct parameters?");
			}
			
		}
		else {
			fail("Product must implement the Comparable interface");
		}
	}
	
	@Test
	public void Product_getNameTest() {
		Product testProduct = createProduct("brat",5,200000);
		
		assertEquals("With an Product object who's name instance variable is brat, when calling getName we","brat",testProduct.getName());
	}
	
	@Test
	public void Product_setNameTest() {
		Product testProduct = createProduct("brat",5,200000);
		
		testProduct.setName("COWBOY CARTER");
		testVariable("name",testProduct,"COWBOY CARTER","After calling Product's setName method with an argument of COWBOY CARTER, for the value of name we",1);
	}
	
	@Test
	public void Product_getRatingTest() {
		Product testProduct = createProduct("brat",5,200000);
		
		assertEquals("With an Product object who's rating instance variable is 5, when calling getRating we",5,testProduct.getRating(),.01);
	}
	
	@Test
	public void Product_getNumRatingsTest() {
		Product testProduct = createProduct("brat",5,200000);
		
		assertEquals("With an Product object who's numRatings instance variable is 200000, when calling getNumRatings we",200000,testProduct.getNumRatings());
	}
	
	@Test
	public void Product_addRatingTest() {
		Product testProduct = createProduct("brat",0,0);
		
		//Add first 5 star review
		testProduct.addRating(5);
		testVariable("rating",testProduct,5.0,"With a product who's rating is 0 and numRatings is 0, after calling Product's addRating method with an argument of 5, for the value of rating we",1);
		
		testVariable("numRatings",testProduct,1,"With a product who's rating is 0 and numRatings is 0, after calling Product's addRating method with an argument of 5, for the value of numRatings we",1);
		
		//Add a 1 star review
		testProduct.addRating(1);
		testVariable("rating",testProduct,3.0,"With a product who's rating is 5.0 and numRatings is 1, after calling Product's addRating method with an argument of 1, for the value of rating we",1);
		
		testVariable("numRatings",testProduct,2,"With a product who's rating is 5.0 and numRatings is 1, after calling Product's addRating method with an argument of 1, for the value of numRatings we",1);
		
		//Add a 4.9 star review (pitchfork vibes lol)
		testProduct.addRating(4.9);
		testVariable("rating",testProduct,3.6333,"With a product who's rating is 3.0 and numRatings is 2, after calling Product's addRating method with an argument of 4.9, for the value of rating we",1);
		
		testVariable("numRatings",testProduct,3,"With a product who's rating is 3.0 and numRatings is 2, after calling Product's addRating method with an argument of 4.9, for the value of numRatings we",1);
		
		//Add a -1 star reivew (not allowed lol, should convert to a 0)
		testProduct.addRating(-1);
		testVariable("rating",testProduct,2.725,"With a product who's rating is 3.63 and numRatings is 3, after calling Product's addRating method with an argument of -1, for the value of rating we",1);
		
		testVariable("numRatings",testProduct,4,"With a product who's rating is 3.63 and numRatings is 3, after calling Product's addRating method with an argument of -1, for the value of numRatings we",1);
		
		//Add a 6 start review (also not allowed sry chari :`(  should convert to a 5)
		testProduct.addRating(6);
		testVariable("rating",testProduct,3.18,"With a product who's rating is 3.63 and numRatings is 2.725, after calling Product's addRating method with an argument of 6, for the value of rating we",1);
		
		testVariable("numRatings",testProduct,5,"With a product who's rating is 3.63 and numRatings is 4, after calling Product's addRating method with an argument of 6, for the value of numRatings we",1);
		
	}
	
	//Tests for Produce class
	@Test
	public void Produce_superClassTest() {
		Produce myProduce = new Produce("",0.0,0,0.0,0.0);
		assertTrue("When testing if Produce extends Product, we", (myProduce instanceof Product));
	}
	
	@Test
	public void Produce_instanceCountTest(){
		Class c = Produce.class;
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
	public void Produce_instanceVariablesTest(){
		Class testProduce = Produce.class;
		
		instanceVariablePrivate("weight",testProduce);
		instanceVariablePrivate("pricePerLb",testProduce);
		
		instanceVariableStatic("weight",testProduce,false);
		instanceVariableStatic("pricePerLb",testProduce,false);
		
		instanceVariableCorrectType("weight", double.class,testProduce);
		instanceVariableCorrectType("pricePerLb", double.class,testProduce);
	}
	
	@Test
	public void Produce_parameterizedConstructorTest() {
		Produce testProduce = new Produce("TestProduce",4.2,255,3.2,1.25);
		
		testVariable("name", testProduce, "TestProduce", "When checking the value of name we", 1);
		testVariable("rating", testProduce, 4.2, "When checking the value of rating we", 1);
		testVariable("numRatings", testProduce, 255, "When checking the value of rating we", 1);
		testVariable("weight", testProduce, 3.2, "When checking the value of weight we", 0);
		testVariable("pricePerLb", testProduce, 1.25, "When checking the value of pricePerLb we", 0);
		
	}
	
	@Test
	public void Produce_toStringTest() {
		Produce testProduce = createProduce("Apple",4.4,255,.8,1.25);
		assertEquals(  "Apple,4.4,255,1.00,0.80,1.25", testProduce.toString());
		
		testProduce = createProduce("Banana",4.1,67,.3,0.7);
		assertEquals(  "Banana,4.1,67,0.21,0.30,0.70", testProduce.toString());
	}
	
	@Test
	public void Produce_getPriceTest() {
		Produce testProduce = createProduce("Apple",4.4,255,.8,1.25);
		assertEquals("For a Produce object who's weight is .8 and pricePerLb is 1.25, when calling Produce's getPrice method we",  1.0, testProduce.getPrice(),.001);
		
		testProduce = createProduce("Banana",4.1,67,0.3,0.7);
		assertEquals("For a Produce object who's weight is 0.3 and pricePerLb is 0.7, when calling Produce's getPrice method we",  0.21, testProduce.getPrice(),.001);
	}
	
	@Test
	public void Produce_getWeightTest() {
		Produce testProduce = createProduce("Apple",4.4,255,.8,1.25);
		
		assertEquals("With an Produce object who's weight instance variable is 0.8, when calling getWeight we",0.8,testProduce.getWeight(),.001);
	}
	
	@Test
	public void Produce_setWeightTest() {
		Produce testProduce = createProduce("Apple",4.4,255,.8,1.25);
		
		testProduce.setWeight(0.6);
		testVariable("weight",testProduce,0.6,"After calling Produce's setWeight method with an argument of 0.6, for the value of weight we",0);
	}
	
	@Test
	public void Produce_getPricePerLbTest() {
		Produce testProduce = createProduce("Apple",4.4,255,.8,1.25);
		
		assertEquals("With an Produce object who's pricePerLb instance variable is 1.25, when calling getPricePerLb we",1.25,testProduce.getPricePerLb(),.001);
	}
	
	@Test
	public void Produce_setPricePerLbTest() {
		Produce testProduce = createProduce("Apple",4.4,255,.8,1.25);
		
		testProduce.setPricePerLb(1.0);
		testVariable("pricePerLb",testProduce,1.0,"After calling Produce's setPricePerLb method with an argument of 1.0, for the value of pricePerLb we",0);
	}
	
	
	//Tests for PackagedGood class
	@Test
	public void PackagedGood_superClassTest() {
		PackagedGood myPackagedGood = new PackagedGood("",0.0,0,0,0.0);
		assertTrue("When testing if PackagedGood extends Product, we", (myPackagedGood instanceof Product));
	}
	
	@Test
	public void PackagedGood_instanceCountTest(){
		Class c = PackagedGood.class;
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
	public void PackagedGood_instanceVariablesTest(){
		Class testPackagedGood = PackagedGood.class;
		
		instanceVariablePrivate("quantity",testPackagedGood);
		instanceVariablePrivate("pricePerUnit",testPackagedGood);
		
		instanceVariableStatic("quantity",testPackagedGood,false);
		instanceVariableStatic("pricePerUnit",testPackagedGood,false);
		
		instanceVariableCorrectType("quantity", int.class,testPackagedGood);
		instanceVariableCorrectType("pricePerUnit", double.class,testPackagedGood);
	}
	
	@Test
	public void PackagedGood_parameterizedConstructorTest() {
		PackagedGood testPackagedGood = new PackagedGood("TestPackagedGood",4.5,255,3,5.0);
		
		testVariable("name", testPackagedGood, "TestPackagedGood", "When checking the value of name we", 1);
		testVariable("rating", testPackagedGood, 4.5, "When checking the value of rating we", 1);
		testVariable("numRatings", testPackagedGood, 255, "When checking the value of rating we", 1);
		testVariable("quantity", testPackagedGood, 3, "When checking the value of quantity we", 0);
		testVariable("pricePerUnit", testPackagedGood, 5.0, "When checking the value of pricePerUnit we", 0);
		
	}
	
	@Test
	public void PackagedGood_toStringTest() {
		PackagedGood testPackagedGood = createPackagedGood("Ritz",4.6,255,4,5.35);
		assertEquals(  "Ritz,4.6,255,21.40,4,5.35", testPackagedGood.toString());
		
		testPackagedGood = createPackagedGood("Sun Chips",4.1,167,3,7.55);
		assertEquals(  "Sun Chips,4.1,167,22.65,3,7.55", testPackagedGood.toString());
	}
	
	@Test
	public void PackagedGood_getPriceTest() {
		PackagedGood testPackagedGood = createPackagedGood("Ritz",4.6,255,4,5.35);
		assertEquals("For a PackagedGood object who's quantity is 4 and pricePerUnit is 5.35, when calling PackagedGood's getPrice method we",  21.4, testPackagedGood.getPrice(),.001);
		
		testPackagedGood = createPackagedGood("Sun Chips",4.1,167,3,7.55);
		assertEquals("For a PackagedGood object who's quantity is 0.3 and pricePerUnit is 0.7, when calling PackagedGood's getPrice method we",  22.65, testPackagedGood.getPrice(),.001);
	}
	
	@Test
	public void PackagedGood_getQuantityTest() {
		PackagedGood testPackagedGood = createPackagedGood("Ritz",4.6,255,4,5.35);
		
		assertEquals("With an PackagedGood object who's quantity instance variable is 4, when calling getQuantity we",4,testPackagedGood.getQuantity());
	}
	
	@Test
	public void PackagedGood_setQuantityTest() {
		PackagedGood testPackagedGood = createPackagedGood("Ritz",4.6,255,4,5.35);
		
		testPackagedGood.setQuantity(10);
		testVariable("quantity",testPackagedGood,10,"After calling PackagedGood's setQuantity method with an argument of 10, for the value of quantity we",0);
	}
	
	@Test
	public void PackagedGood_getPricePerUnitTest() {
		PackagedGood testPackagedGood = createPackagedGood("Ritz",4.6,255,4,5.35);
		
		assertEquals("With an PackagedGood object who's pricePerUnit instance variable is 5.35, when calling getPricePerUnit we",5.35,testPackagedGood.getPricePerUnit(),.001);
	}
	
	@Test
	public void PackagedGood_setPricePerUnitTest() {
		PackagedGood testPackagedGood = createPackagedGood("Ritz",4.6,255,4,5.35);
		
		testPackagedGood.setPricePerUnit(4.99);
		testVariable("pricePerUnit",testPackagedGood,4.99,"After calling PackagedGood's setPricePerUnit method with an argument of 1.0, for the value of pricePerUnit we",0);
	}
	
	//Tests for Appliance class
	@Test
	public void Appliance_superClassTest() {
		Appliance myAppliance = new Appliance("",0.0,0,0.0,0);
		assertTrue("When testing if Appliance extends Product, we", (myAppliance instanceof Product));
	}
	
	@Test
	public void Appliance_instanceCountTest(){
		Class c = Appliance.class;
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
	public void Appliance_instanceVariablesTest(){
		Class testAppliance = Appliance.class;
		
		instanceVariablePrivate("warrantyLength",testAppliance);
		instanceVariablePrivate("basePrice",testAppliance);
		
		instanceVariableStatic("warrantyLength",testAppliance,false);
		instanceVariableStatic("basePrice",testAppliance,false);
		
		instanceVariableCorrectType("warrantyLength", int.class,testAppliance);
		instanceVariableCorrectType("basePrice", double.class,testAppliance);
	}
	
	@Test
	public void Appliance_parameterizedConstructorTest() {
		Appliance testAppliance = new Appliance("TestAppliance",4.2,255,3.2,5);
		
		testVariable("name", testAppliance, "TestAppliance", "When checking the value of name we", 1);
		testVariable("rating", testAppliance, 4.2, "When checking the value of rating we", 1);
		testVariable("numRatings", testAppliance, 255, "When checking the value of rating we", 1);
		testVariable("basePrice", testAppliance, 3.2, "When checking the value of basePrice we", 0);
		testVariable("warrantyLength", testAppliance, 5, "When checking the value of warrantyLength we", 0);
		
	}
	
	@Test
	public void Appliance_toStringTest() {
		Appliance testAppliance = createAppliance("iPod",4.6,1024,500,3);
		assertEquals(  "iPod,4.6,1024,575.00,500.00,3", testAppliance.toString());
		
		testAppliance = createAppliance("Tamagochi",4.9,1024,30,1);
		assertEquals(  "Tamagochi,4.9,1024,31.50,30.00,1", testAppliance.toString());
	}
	
	@Test
	public void Appliance_getPriceTest() {
		Appliance testAppliance = createAppliance("iPod",4.6,1024,500,3);
		assertEquals("For a Appliance object who's warrantyLength is 3 and basePrice is 500, when calling Appliance's getPrice method we",  575.0, testAppliance.getPrice(),.001);
		
		testAppliance = createAppliance("Tamagochi",4.9,1024,30,1);
		assertEquals("For a Appliance object who's warrantyLength is 1 and basePrice is 30, when calling Appliance's getPrice method we",  31.5, testAppliance.getPrice(),.001);
	}
	
	@Test
	public void Appliance_getWarrantyLengthTest() {
		Appliance testAppliance = createAppliance("iPod",4.6,1024,500,3);
		
		assertEquals("With an Appliance object who's warrantyLength instance variable is 3, when calling getWarrantyLength we",3,testAppliance.getWarrantyLength());
	}
	
	@Test
	public void Appliance_setWarrantyLengthTest() {
		Appliance testAppliance = createAppliance("iPod",4.6,1024,500,3);
		
		testAppliance.setWarrantyLength(6);
		testVariable("warrantyLength",testAppliance,6,"After calling Appliance's setWarrantyLength method with an argument of 6, for the value of warrantyLength we",0);
	}
	
	@Test
	public void Appliance_getBasePriceTest() {
		Appliance testAppliance = createAppliance("iPod",4.6,1024,500,3);
		
		assertEquals("With an Appliance object who's basePrice instance variable is 500.0, when calling getBasePrice we",500.0,testAppliance.getBasePrice(),.001);
	}
	
	@Test
	public void Appliance_setBasePriceTest() {
		Appliance testAppliance = createAppliance("iPod",4.6,1024,500,3);
		
		testAppliance.setBasePrice(499.99);
		testVariable("basePrice",testAppliance,499.99,"After calling Appliance's setBasePrice method with an argument of 499.99, for the value of basePrice we",0);
	}
	
	
	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}
	
	
	private Product createProduct(String aName, double aRating, int aNumRatings){
		Product testProduct = new Product("",0,0) {
			public double getPrice() {
				return 19.95;
			}
		};
		Class c = testProduct.getClass().getSuperclass();
		
		try {
			Field name = c.getDeclaredField("name");
			name.setAccessible(true);
			name.set(testProduct, aName);
			
			Field rating = c.getDeclaredField("rating");
			rating.setAccessible(true);
			rating.set(testProduct, aRating);
			
			Field numRatings = c.getDeclaredField("numRatings");
			numRatings.setAccessible(true);
			numRatings.set(testProduct, aNumRatings);
			
		} catch (Exception e) {
			fail(e.toString());
		}
		
		return testProduct;
	}
	
	private Produce createProduce(String aName, double aRating, int aNumRatings, double aWeight, double aPricePerLb){
		Produce testProduce = new Produce("",0,0,0,0);
		Class c = testProduce.getClass();
		Class sc = c.getSuperclass();
		
		try {
			Field name = sc.getDeclaredField("name");
			name.setAccessible(true);
			name.set(testProduce, aName);
			
			Field rating = sc.getDeclaredField("rating");
			rating.setAccessible(true);
			rating.set(testProduce, aRating);
			
			Field numRatings = sc.getDeclaredField("numRatings");
			numRatings.setAccessible(true);
			numRatings.set(testProduce, aNumRatings);
			
			Field weight = c.getDeclaredField("weight");
			weight.setAccessible(true);
			weight.set(testProduce, aWeight);
			
			Field pricePerLb = c.getDeclaredField("pricePerLb");
			pricePerLb.setAccessible(true);
			pricePerLb.set(testProduce, aPricePerLb);
			
		} catch (Exception e) {
			fail(e.toString());
		}
		
		return testProduce;
	}
	
	private PackagedGood createPackagedGood(String aName, double aRating, int aNumRatings, int aQuantity, double aPricePerUnit){
		PackagedGood testPackagedGood = new PackagedGood("",0,0,0,0);
		Class c = testPackagedGood.getClass();
		Class sc = c.getSuperclass();
		
		try {
			Field name = sc.getDeclaredField("name");
			name.setAccessible(true);
			name.set(testPackagedGood, aName);
			
			Field rating = sc.getDeclaredField("rating");
			rating.setAccessible(true);
			rating.set(testPackagedGood, aRating);
			
			Field numRatings = sc.getDeclaredField("numRatings");
			numRatings.setAccessible(true);
			numRatings.set(testPackagedGood, aNumRatings);
			
			Field quantity = c.getDeclaredField("quantity");
			quantity.setAccessible(true);
			quantity.set(testPackagedGood, aQuantity);
			
			Field pricePerUnit = c.getDeclaredField("pricePerUnit");
			pricePerUnit.setAccessible(true);
			pricePerUnit.set(testPackagedGood, aPricePerUnit);
			
		} catch (Exception e) {
			fail(e.toString());
		}
		
		return testPackagedGood;
	}
	
	private Appliance createAppliance(String aName, double aRating, int aNumRatings, double aBasePrice, int aWarrantyLength){
		Appliance testAppliance = new Appliance("",0,0,0,0);
		Class c = testAppliance.getClass();
		Class sc = c.getSuperclass();
		
		try {
			Field name = sc.getDeclaredField("name");
			name.setAccessible(true);
			name.set(testAppliance, aName);
			
			Field rating = sc.getDeclaredField("rating");
			rating.setAccessible(true);
			rating.set(testAppliance, aRating);
			
			Field numRatings = sc.getDeclaredField("numRatings");
			numRatings.setAccessible(true);
			numRatings.set(testAppliance, aNumRatings);
			
			Field basePrice = c.getDeclaredField("basePrice");
			basePrice.setAccessible(true);
			basePrice.set(testAppliance, aBasePrice);
			
			Field warrantyLength = c.getDeclaredField("warrantyLength");
			warrantyLength.setAccessible(true);
			warrantyLength.set(testAppliance, aWarrantyLength);
			
		} catch (Exception e) {
			fail(e.toString());
		}
		
		return testAppliance;
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
			Object fieldRating = field.get(testObject);
			
			if(expected == null){
				assertNull(message,fieldRating);
			}
			//If class is a double we have a special Junit assert to run
			else if(expected.getClass().equals(Double.class)){
				double doubleFieldRating = (double) fieldRating;
				double doubleExpected = (double) expected;
				assertEquals(message, doubleExpected, doubleFieldRating, .001);
			}
			//Array of some kind yay
			else if(expected.getClass().isArray()){
			
			}
			else if(expected.getClass().equals(ArrayList.class)){
			
			}
			else{
				assertEquals(message, expected, fieldRating);
			}
			
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
}
