package Labs.Lab6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ArrayTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private String rgx = System.getProperty("line.separator");
	
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}
	
	@Test
	public void mainFirstPrintedArrayTest(){
		String[] args = {"Beth,Rick,Jerry,Rick,Summer,Morty"};
		Array.main(args);
		String[] rawOutput = outContent.toString().split(rgx);
		
		assertEquals("When checking the first element of the first array (the 1st line of standard output, we","Beth",rawOutput[0]);
		assertEquals("When checking the second element of the first array (the 2nd line of standard output, we","Rick",rawOutput[1]);
		assertEquals("When checking the third element of the first array (the 3rd line of standard output, we","Jerry",rawOutput[2]);
		assertEquals("When checking the fourth element of the first array (the 4th line of standard output, we","Rick",rawOutput[3]);
		assertEquals("When checking the fifth element of the first array (the 5th line of standard output, we","Summer",rawOutput[4]);
		assertEquals("When checking the sixth element of the first array (the 6th line of standard output, we","Morty",rawOutput[5]);
		assertEquals("When checking the seventh element of the first array (the 7th line of standard output, we","null",rawOutput[6]);
		assertEquals("When checking the eighth element of the first array (the 8th line of standard output, we","null",rawOutput[7]);
	}
	
	@Test
	public void mainSecondPrintedArrayTest() {
		String[] args = {"Beth,Rick,Jerry,Rick,Summer,Morty"};
		Array.main(args);
		String[] rawOutput = outContent.toString().split(rgx);
		
		assertEquals("When checking the first element of the second array (the 9th line of standard output, we","Beth",rawOutput[9]);
		assertEquals("When checking the second element of the second array (the 10th line of standard output, we","Rick",rawOutput[10]);
		assertEquals("When checking the third element of the second array (the 11th line of standard output, we","Jerry",rawOutput[11]);
		assertEquals("When checking the fourth element of the second array (the 12th line of standard output, we","Rick",rawOutput[12]);
		assertEquals("When checking the fifth element of the second array (the 13th line of standard output, we","Rick",rawOutput[13]);
		assertEquals("When checking the sixth element of the second array (the 14th line of standard output, we","Jessica",rawOutput[14]);
		assertEquals("When checking the seventh element of the second array (the 15th line of standard output, we","Summer",rawOutput[15]);
		assertEquals("When checking the eighth element of the second array (the 16th line of standard output, we","Morty",rawOutput[16]);
		
	}
	
	@Test
	public void mainThirdPrintedArrayTest() {
		String[] args = {"Beth,Rick,Jerry,Rick,Summer,Morty"};
		Array.main(args);
		String[] rawOutput = outContent.toString().split(rgx);
		
		assertEquals("When checking the first element of the third array (the 17th line of standard output, we","Morty",rawOutput[18]);
		assertEquals("When checking the second element of the third array (the 18th line of standard output, we","Summer",rawOutput[19]);
		assertEquals("When checking the third element of the third array (the 19th line of standard output, we","Jessica",rawOutput[20]);
		assertEquals("When checking the fourth element of the third array (the 20th line of standard output, we","Rick",rawOutput[21]);
		assertEquals("When checking the fifth element of the third array (the 21st line of standard output, we","Rick",rawOutput[22]);
		assertEquals("When checking the sixth element of the third array (the 22nd line of standard output, we","Jerry",rawOutput[23]);
		assertEquals("When checking the seventh element of the third array (the 23rd line of standard output, we","Rick",rawOutput[24]);
		assertEquals("When checking the eighth element of the third array (the 24th line of standard output, we","Beth",rawOutput[25]);
	}
	
	@Test
	public void mainFourthPrintedArrayTest() {
		String[] args = {"Beth,Rick,Jerry,Rick,Summer,Morty"};
		Array.main(args);
		String[] rawOutput = outContent.toString().split(rgx);
		
		assertEquals("When checking the first element of the fourth array (the 25th line of standard output, we","Morty",rawOutput[27]);
		assertEquals("When checking the second element of the fourth array (the 26th line of standard output, we","Summer",rawOutput[28]);
		assertEquals("When checking the third element of the fourth array (the 27th line of standard output, we","Jessica",rawOutput[29]);
		assertEquals("When checking the fourth element of the fourth array (the 28th line of standard output, we","Jerry",rawOutput[30]);
		assertEquals("When checking the fifth element of the fourth array (the 29th line of standard output, we","Beth",rawOutput[31]);
		assertEquals("When checking the sixth element of the fourth array (the 30th line of standard output, we","null",rawOutput[32]);
		assertEquals("When checking the seventh element of the fourth array (the 31st line of standard output, we","null",rawOutput[33]);
		assertEquals("When checking the eighth element of the fourth array (the 32nd line of standard output, we","null",rawOutput[34]);
		
	}
	
	@Test
	public void displayNamesTest(){
		String[] args = {"Beth","Rick","Jerry","Rick","Summer","Morty"};
		Array.displayNames(args);
		String[] rawOutput = outContent.toString().split(rgx);
		
		assertEquals("After calling displayNames with an Array of elements \"Beth,Rick,Jerry,Rick,Summer,Morty\" when checking the first value printed, we","Beth",rawOutput[0]);
		assertEquals("After calling displayNames with an Array of elements \"Beth,Rick,Jerry,Rick,Summer,Morty\" when checking the second value printed, we","Rick",rawOutput[1]);
		assertEquals("After calling displayNames with an Array of elements \"Beth,Rick,Jerry,Rick,Summer,Morty\" when checking the third value printed, we","Jerry",rawOutput[2]);
		assertEquals("After calling displayNames with an Array of elements \"Beth,Rick,Jerry,Rick,Summer,Morty\" when checking the fourth value printed, we","Rick",rawOutput[3]);
		assertEquals("After calling displayNames with an Array of elements \"Beth,Rick,Jerry,Rick,Summer,Morty\" when checking the fifth value printed, we","Summer",rawOutput[4]);
		assertEquals("After calling displayNames with an Array of elements \"Beth,Rick,Jerry,Rick,Summer,Morty\" when checking the sixth value printed, we","Morty",rawOutput[5]);
		assertEquals("DisplayNames must end with a blank line",rgx+rgx,outContent.toString().split("Morty")[1]);
		
	}
	
	@Test
	public void reverseNamesEvenTest(){
		String[] args = {"Beth","Rick","Jerry","Rick","Summer","Morty"};
		String[] rawOutput = Array.reverseNames(args);
		
		assertEquals("After calling displayNames with an Array of elements \"Beth,Rick,Jerry,Rick,Summer,Morty\" when checking the first element of the returned array, we","Morty",rawOutput[0]);
		assertEquals("After calling displayNames with an Array of elements \"Beth,Rick,Jerry,Rick,Summer,Morty\" when checking the first element of the returned array, we","Summer",rawOutput[1]);
		assertEquals("After calling displayNames with an Array of elements \"Beth,Rick,Jerry,Rick,Summer,Morty\" when checking the first element of the returned array, we","Rick",rawOutput[2]);
		assertEquals("After calling displayNames with an Array of elements \"Beth,Rick,Jerry,Rick,Summer,Morty\" when checking the first element of the returned array, we","Jerry",rawOutput[3]);
		assertEquals("After calling displayNames with an Array of elements \"Beth,Rick,Jerry,Rick,Summer,Morty\" when checking the first element of the returned array, we","Rick",rawOutput[4]);
		assertEquals("After calling displayNames with an Array of elements \"Beth,Rick,Jerry,Rick,Summer,Morty\" when checking the first element of the returned array, we","Beth",rawOutput[5]);
	}
	
	@Test
	public void reverseNamesOddTest(){
		String[] args = {"Rick","Morty","Summer","Beth","Jerry",};
		String[] rawOutput = Array.reverseNames(args);
		
		assertEquals("After calling displayNames with an Array of elements \"Rick,Morty,Summer,Beth,Jerry\" when checking the first element of the returned array, we","Jerry",rawOutput[0]);
		assertEquals("After calling displayNames with an Array of elements \"Rick,Morty,Summer,Beth,Jerry\" when checking the first element of the returned array, we","Beth",rawOutput[1]);
		assertEquals("After calling displayNames with an Array of elements \"Rick,Morty,Summer,Beth,Jerry\" when checking the first element of the returned array, we","Summer",rawOutput[2]);
		assertEquals("After calling displayNames with an Array of elements \"Rick,Morty,Summer,Beth,Jerry\" when checking the first element of the returned array, we","Morty",rawOutput[3]);
		assertEquals("After calling displayNames with an Array of elements \"Rick,Morty,Summer,Beth,Jerry\" when checking the first element of the returned array, we","Rick",rawOutput[4]);
		
	}
	
	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}
	
}
