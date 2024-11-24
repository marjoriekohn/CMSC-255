package Projects.Project2;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CompareDataCentersTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}
	
	
	String[] corporateNames = {"Turner","Holder","HITT","DPR","Fortis","Mortenson","JE Dunn","Clune"};
	
	double[] constructionCost = {442.2,584.8,420.7,726.5,574.1,717.0,763.4,527.9};
	double[] ITLoad = {168.4,307.2,21.8,271.0,50.5,117.7,199.6,289.1};
	double[] operatingCost = {19.0,26.4,11.1,24.5,12.7,16.2,20.7,25.5};
	
	
	String[] newCorporateNames = {"Bechtel-Kaiser","Utah Construction Company","Morrison-Knudsen","Pacific Bridge Company","J.F. Shea Co","Washington Group International","URS","Burns & McDonnell"};
	double[] newConstructionCost = {626.7,460.2,419.7,588.6,671.4,786.0,436.9,742.8};
	double[] newITLoad = {171.6,30.7,184.0,91.3,145.5,282.2,281.4,280.9};
	double[] newOperatingCost = {19.2,11.6,19.9,14.9,17.8,25.1,25.0,24.8};
	
	
	@Test
	public void calcAvgGivenConstructionCostDataTest(){
		double actual = CompareDataCenters.calcAvg(constructionCost);
		double expected = 594.575;
		assertEquals("When checking the result of calcAvg we",expected,actual,.001);
	}
	
	@Test
	public void calcAvgUnknownDataTest(){
		double actual = CompareDataCenters.calcAvg(newConstructionCost);
		double expected = 591.538;
		assertEquals("When checking the result of calcAvg we",expected,actual,.001);
	}
	
	@Test
	public void findHighValueGivenITLoadDataTest(){
		double actual = CompareDataCenters.findHighValue(ITLoad);
		double expected = 307.2;
		assertEquals("When checking the result of findHighValue we",expected,actual,.001);
	}
	
	@Test
	public void findHighValueUnknownDataTest(){
		double actual = CompareDataCenters.findHighValue(newITLoad);
		double expected = 282.2;
		assertEquals("When checking the result of findHighValue we",expected,actual,.001);
	}
	
	@Test
	public void findLeastValueGivenOperatingCostDataTest(){
		double actual = CompareDataCenters.findLeastValue(operatingCost);
		double expected = 11.1;
		assertEquals("When checking the result of findLeastValue we",expected,actual,.001);
	}
	
	@Test
	public void findLeastValueUnknownDataTest(){
		double actual = CompareDataCenters.findLeastValue(newOperatingCost);
		double expected = 11.6;
		assertEquals("When checking the result of findLeastValue we",expected,actual,.001);
	}
	
	@Test
	public void findHighestTwoValuesGivenConstructionCostDataTest(){
		String[] actual = CompareDataCenters.findHighestTwo(corporateNames,constructionCost);
		assertEquals("When checking the result of findHighestTwo we expected the first element of the resulting array to be","JE Dunn",actual[0]);
		assertEquals("When checking the result of findHighestTwo we expected the second element of the array to be", "DPR",actual[1]);
	}
	
	@Test
	public void findHighestTwoValuesUnknownDataTest(){
		String[] actual = CompareDataCenters.findHighestTwo(newCorporateNames,newConstructionCost);
		assertEquals("When checking the result of findHighestTwo we expected the first element of the resulting array to be","Washington Group International",actual[0]);
		assertEquals("When checking the result of findHighestTwo we expected the second element of the array to be", "Burns & McDonnell",actual[1]);
	}
	
	@Test
	public void findLeastTwoValuesGivenITLoadDataTest(){
		String[] actual = CompareDataCenters.findLeastTwo(corporateNames,ITLoad);
		assertEquals("When checking the result of findLeastTwo we expected the first element of the resulting array to be","HITT",actual[0]);
		assertEquals("When checking the result of findLeastTwo we expected the second element of the array to be", "Fortis",actual[1]);
	}
	
	@Test
	public void findLeastTwoValuesUnknownDataTest(){
		String[] actual = CompareDataCenters.findLeastTwo(newCorporateNames,newITLoad);
		assertEquals("When checking the result of findLeastTwo we expected the first element of the resulting array to be","Utah Construction Company",actual[0]);
		assertEquals("When checking the result of findLeastTwo we expected the second element of the array to be", "Pacific Bridge Company",actual[1]);
	}
	
	@Test
	public void findConstructionFirmGivenDataTest(){
		boolean actual = CompareDataCenters.findConstructionFirm(corporateNames,"HITT");
		assertTrue("When checking the result of findConstructionFirm the given array contained \"HITT\" and should return true",actual);
		
		actual = CompareDataCenters.findConstructionFirm(corporateNames,"PWA");
		assertFalse("When checking the result of findConstructionFirm the given array does not contain \"PWA\" and should return false",actual);
	}
	
	@Test
	public void findConstructionFirmUnknownDataTest(){
		boolean actual = CompareDataCenters.findConstructionFirm(newCorporateNames,"Morrison-Knudsen");
		assertTrue("When checking the result of findConstructionFirm the given array contained \"Morrison-Knudsen\" and should return true",actual);
		
		actual = CompareDataCenters.findConstructionFirm(newCorporateNames,"Beauvine");
		assertFalse("When checking the result of findConstructionFirm the given array does not contain \"Beauvine\" and should return false",actual);
	}
	
	@Test
	public void mainMethodValidCompany(){
		String input = "Clune";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		CompareDataCenters.main(null);
		String[] rawOutput = outContent.toString().split("\r?\n");
		assertEquals("When checking the first line of output we","The average construction cost in dollars: $594.57M",rawOutput[0].trim());
		assertEquals("When checking the second line of output we","The average IT Load in megawatts is: 178.16MW",rawOutput[1].trim());
		assertEquals("When checking the third line of output we","The highest construction cost in dollars: $763.40M",rawOutput[2].trim());
		assertEquals("When checking the fourth line of output we","The least operating cost in dollars is: $11.10M",rawOutput[3].trim());
		assertEquals("When checking the fifth line of output we","The two construction firms with the highest construction cost are:",rawOutput[4].trim());
		assertEquals("When checking the sixth line of output we","JE Dunn",rawOutput[5].trim());
		assertEquals("When checking the seventh line of output we","DPR",rawOutput[6].trim());
		assertEquals("When checking the eighth line of output we","The two construction firms with the lowest IT Load are:",rawOutput[7].trim());
		assertEquals("When checking the ninth line of output we","HITT",rawOutput[8].trim());
		assertEquals("When checking the tenth line of output we","Fortis",rawOutput[9].trim());
		assertEquals("When checking the tenth line of output we","Enter a construction firm:",rawOutput[10].trim());
		assertEquals("When checking the tenth line of output we","Clune is a construction firm in the array.",rawOutput[11].trim());
		assertTrue("You should not have more than 12 lines of output!",rawOutput.length < 13);
	}
	
	@Test
	public void mainMethodInvalidCompany(){
		String input = "PWA";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		CompareDataCenters.main(null);
		String[] rawOutput = outContent.toString().split("\r?\n");
		assertEquals("When checking the first line of output we","The average construction cost in dollars: $594.57M",rawOutput[0].trim());
		assertEquals("When checking the second line of output we","The average IT Load in megawatts is: 178.16MW",rawOutput[1].trim());
		assertEquals("When checking the third line of output we","The highest construction cost in dollars: $763.40M",rawOutput[2].trim());
		assertEquals("When checking the fourth line of output we","The least operating cost in dollars is: $11.10M",rawOutput[3].trim());
		assertEquals("When checking the fifth line of output we","The two construction firms with the highest construction cost are:",rawOutput[4].trim());
		assertEquals("When checking the sixth line of output we","JE Dunn",rawOutput[5].trim());
		assertEquals("When checking the seventh line of output we","DPR",rawOutput[6].trim());
		assertEquals("When checking the eighth line of output we","The two construction firms with the lowest IT Load are:",rawOutput[7].trim());
		assertEquals("When checking the ninth line of output we","HITT",rawOutput[8].trim());
		assertEquals("When checking the tenth line of output we","Fortis",rawOutput[9].trim());
		assertEquals("When checking the tenth line of output we","Enter a construction firm:",rawOutput[10].trim());
		assertEquals("When checking the tenth line of output we","PWA is not a construction firm in the array.",rawOutput[11].trim());
		assertTrue("You should not have more than 12 lines of output!",rawOutput.length < 13);
	}
	
	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}
}