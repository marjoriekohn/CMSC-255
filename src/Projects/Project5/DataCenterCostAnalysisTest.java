package Projects.Project5;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.*;

@SuppressWarnings("rawtypes")
public class DataCenterCostAnalysisTest {
    
    
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    @Test
    public void DataCenterAttributesTest() {
        try {
            DataCenterAttributes.valueOf("CONSTRUCTION_COST");
            DataCenterAttributes.valueOf("IT_LOAD");
            DataCenterAttributes.valueOf("OPERATING_COST");
        }
        catch(IllegalArgumentException e) {
            fail(e.getLocalizedMessage());
        }
        assertEquals("When looking at the number of values in the DataCenterAttributes enum, we",3,DataCenterAttributes.values().length);
    }
    
    @Test
    public void DataCenterCostAnalysis_GoodData_openFileTest() throws IOException {
        // Invoke method
        ArrayList<String> actual = DataCenterCostAnalysis.openFile(generateGoodInputFile());
        // Check results
        testStringArray("When checking the String array returned from the openFile method given a file with valid input data, we",generateGoodInputStringArray(),actual);
    }
    
    @Test
    public void DataCenterCostAnalysis_GoodData_createObjectsTest(){
        ArrayList<DataCenter> actual = DataCenterCostAnalysis.createObjects(generateGoodInputStringArray());
        testDataCenterArray("When checking the ArrayList returned from the createObjects method given data generated from dataCenterGoodData.txt, we",generateGoodInputArrayList(),actual);
    }
    
    @Test
    public void DataCenterCostAnalysis_GoodData_findMeanTest(){
        double actual = DataCenterCostAnalysis.findMean(generateGoodInputArrayList(),DataCenterAttributes.CONSTRUCTION_COST);
        assertEquals("When checking the return value from the findMean method given data generated from dataCenterGoodData.txt and the CONSTRUCTION_COST attribute, we",594.575,actual,0.01);
        
        actual = DataCenterCostAnalysis.findMean(generateGoodInputArrayList(),DataCenterAttributes.IT_LOAD);
        assertEquals("When checking the return value from the findMean method given data generated from dataCenterGoodData.txt and the IT_LOAD attribute, we",178.1625,actual,0.01);
        
        actual = DataCenterCostAnalysis.findMean(generateGoodInputArrayList(),DataCenterAttributes.OPERATING_COST);
        assertEquals("When checking the return value from the findMean method given data generated from dataCenterGoodData.txt and the OPERATING_COST attribute, we",19.5125,actual,0.01);
    }
    
    @Test
    public void DataCenterCostAnalysis_GoodData_findHighValueTest(){
        double actual = DataCenterCostAnalysis.findHighValue(generateGoodInputArrayList(),DataCenterAttributes.CONSTRUCTION_COST);
        assertEquals("When checking the return value from the findHighValue method given data generated from dataCenterGoodData.txt and the CONSTRUCTION_COST attribute, we",763.4,actual,0.01);
        
        actual = DataCenterCostAnalysis.findHighValue(generateGoodInputArrayList(),DataCenterAttributes.IT_LOAD);
        assertEquals("When checking the return value from the findHighValue method given data generated from dataCenterGoodData.txt and the IT_LOAD attribute, we",307.2,actual,0.01);
        
        actual = DataCenterCostAnalysis.findHighValue(generateGoodInputArrayList(),DataCenterAttributes.OPERATING_COST);
        assertEquals("When checking the return value from the findHighValue method given data generated from dataCenterGoodData.txt and the OPERATING_COST attribute, we",26.4,actual,0.01);
    }
    
    @Test
    public void DataCenterCostAnalysis_GoodData_findMeanDataCenterTest(){
        DataCenter actual = DataCenterCostAnalysis.findMeanDataCenter(generateGoodInputArrayList(),DataCenterAttributes.CONSTRUCTION_COST,594.575);
        DataCenter expected = generateGoodInputArrayList().get(1);
        
        assertTrue("When checking the return value from the findMeanDataCenter method given data generated from dataCenterGoodData.txt, the CONSTRUCTION_COST attribute, and an average constructionCost of 594.575, we expected " + expected + " but was " + actual,DataCenterIsEqual(expected,actual));
        
        actual = DataCenterCostAnalysis.findMeanDataCenter(generateGoodInputArrayList(),DataCenterAttributes.IT_LOAD,178.1625);
        expected = generateGoodInputArrayList().get(0);
        
        assertTrue("When checking the return value from the findMeanDataCenter method given data generated from dataCenterGoodData.txt, the IT_LOAD attribute, and an average ITLoad of 178.1625, we expected " + expected + " but was " + actual,DataCenterIsEqual(expected,actual));
        
        actual = DataCenterCostAnalysis.findMeanDataCenter(generateGoodInputArrayList(),DataCenterAttributes.OPERATING_COST,19.5125);
        expected = generateGoodInputArrayList().get(0);
        
        assertTrue("When checking the return value from the findMeanDataCenter method given data generated from dataCenterGoodData.txt, the OPERATING_COST attribute, and an average operatingCost of 19.5125, we expected " + expected + " but was " + actual,DataCenterIsEqual(expected,actual));
        
        
    }
    
    @Test
    public void DataCenterCostAnalysis_GoodData_findLowestDataCentersTest(){
        ArrayList<DataCenter> actual = DataCenterCostAnalysis.findLowestDataCenters(generateGoodInputArrayList(),DataCenterAttributes.CONSTRUCTION_COST,594.575);
        ArrayList<DataCenter> expected = generateGoodInputArrayList();
        expected.remove(6);expected.remove(5);expected.remove(3);
        
        testDataCenterArray("When checking the ArrayList returned from the findLowestDataCenters method given data generated from dataCenterGoodData.txt, the CONSTRUCTION_COST attribute, and the average CONSTRUCTION_COST, we",expected,actual);
        
        actual = DataCenterCostAnalysis.findLowestDataCenters(generateGoodInputArrayList(),DataCenterAttributes.IT_LOAD,178.1625);
        expected = generateGoodInputArrayList();
        expected.remove(7);expected.remove(6);expected.remove(3);expected.remove(1);
        
        testDataCenterArray("When checking the ArrayList returned from the findLowestDataCenters method given data generated from dataCenterGoodData.txt, the IT_LOAD attribute, and the average IT_LOAD, we",expected,actual);
        
        actual = DataCenterCostAnalysis.findLowestDataCenters(generateGoodInputArrayList(),DataCenterAttributes.OPERATING_COST,19.5125);
        expected = generateGoodInputArrayList();
        expected.remove(7);expected.remove(6);expected.remove(3);expected.remove(1);
        
        testDataCenterArray("When checking the ArrayList returned from the findLowestDataCenters method given data generated from dataCenterGoodData.txt, the OPERATING_COST attribute, and the average OPERATING_COST, we",expected,actual);
        
        
    }
    
    @Test
    public void DataCenterCostAnalysis_GoodData_writeOutDataCenterArrayListTest() throws IOException {
        File outputFile = folder.newFile("bar.txt");
        PrintWriter outWriter = new PrintWriter(outputFile);
        ArrayList<DataCenter> outputData = generateGoodInputArrayList();
        String expected = "Output Message: Turner 442.20 168.40 19.00 Holder 584.80 307.20 26.40 HITT 420.70 21.80 11.10 DPR 726.50 271.00 24.50 Fortis 574.10 50.50 12.70 Mortenson 717.00 117.70 16.20 JEDunn 763.40 199.60 20.70 Clune 527.90 289.10 25.50 ";
        String message = "Output Message: ";
        // Invoke method
        DataCenterCostAnalysis.outputToFile(message,outputData,outWriter);
        outWriter.flush();outWriter.close();
        // Check results
        assertTrue("Output file does not exist", outputFile.exists());
        Scanner outputScan = new Scanner(outputFile);
        
        int i = 0;
        
        assertEquals("When checking the output file produced by outputToFile given an ArrayList<DataCenter> of good data, at line " +(i+1)+ " we",expected,outputScan.nextLine());
        
        assertEquals("When checking the output file produced by outputToFile given an ArrayList<DataCenter> of good data, at line 2 we", "", outputScan.nextLine());

//        while (outputScan.hasNextLine()){
//            String expectedString = "";
//            if(i < expected.size()){
//                expectedString = expected.get(i).toString();
//            }
//            assertEquals("When checking the output file produced by outputToFile given an ArrayList<DataCenter> of good data, at line " +(i+2)+ " we",expectedString,outputScan.nextLine().trim());
//            i++;
//        }
        outputScan.close();
    }
    
    @Test
    public void DataCenterCostAnalysis_GoodData_writeOutDoubleDataTest() throws IOException {
        File outputFile = folder.newFile("bar.txt");
        PrintWriter outWriter = new PrintWriter(outputFile);
        String message = "Output Message: ";
        // Invoke method
        DataCenterCostAnalysis.outputToFile(message,100.756,outWriter);
        DataCenterCostAnalysis.outputToFile(message,256.754,outWriter);
        outWriter.flush();outWriter.close();
        // Check results
        assertTrue("Output file does not exist", outputFile.exists());
        Scanner outputScan = new Scanner(outputFile);
        int i = 1;
        try {
            assertEquals("When checking the output file produced by outputToFile given a String message and double value, at line 1 we", message + "100.76", outputScan.nextLine());i++;
            assertEquals("When checking the output file produced by outputToFile given a String message and double value, at line 2 (which should be a blank line) we", "", outputScan.nextLine());i++;
            
            assertEquals("When checking the output file produced by calling outputToFile a second time given a String message and double value, at line 3 we", message + "256.75", outputScan.nextLine());i++;
            assertEquals("When checking the output file produced by calling outputToFile a second time given a String message and double value, at line 4 (which should be a blank line) we", "", outputScan.nextLine());
        }
        catch (NoSuchElementException e){
            fail("Line " + i + " does not exist but it should. Do you have a blank line after your output?");
        }
        
        outputScan.close();
    }
    
    @Test
    public void DataCenterCostAnalysis_GoodData_writeOutDataCenterDataTest() throws IOException {
        File outputFile = folder.newFile("bar.txt");
        PrintWriter outWriter = new PrintWriter(outputFile);
        String message = "Output Message: ";
        // Invoke method
        DataCenterCostAnalysis.outputToFile(message,createDataCenter("DPR",726.5,271.0,24.5),outWriter);
        DataCenterCostAnalysis.outputToFile(message,createDataCenter("HITT",420.7,21.8,11.1),outWriter);
        outWriter.flush();outWriter.close();
        // Check results
        assertTrue("Output file does not exist", outputFile.exists());
        Scanner outputScan = new Scanner(outputFile);
        int i = 1;
        try {
            assertEquals("When checking the output file produced by outputToFile given a String message and double value, at line 1 we", message + "DPR 726.50 271.00 24.50", outputScan.nextLine());i++;
            assertEquals("When checking the output file produced by outputToFile given a String message and double value, at line 2 (which should be a blank line) we", "", outputScan.nextLine());i++;
            
            assertEquals("When checking the output file produced by calling outputToFile a second time given a String message and double value, at line 3 we", message + "HITT 420.70 21.80 11.10", outputScan.nextLine());i++;
            assertEquals("When checking the output file produced by calling outputToFile a second time given a String message and double value, at line 4 (which should be a blank line) we", "", outputScan.nextLine());
        }
        catch (NoSuchElementException e){
            fail("Line " + i + " does not exist but it should. Do you have a blank line after your output?");
        }
        
        outputScan.close();
    }
    
    @Test
    public void DataCenterCostAnalysis_GoodData_mainMethod(){
        try{
            File outputFile = folder.newFile("bar.txt");
            DataCenterCostAnalysis.main(new String[]{generateGoodInputFile().getAbsolutePath(),outputFile.getAbsolutePath()});
            
            // Check results
            assertTrue("Output file does not exist", outputFile.exists());
            Scanner outputScan = new Scanner(outputFile);
            int i = 1;
            try {
                assertEquals("When checking the output file produced by calling the main method given good data, at line 1 we", "The average construction cost of all data centers is: 594.57", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given good data, at line 2 we", "", outputScan.nextLine());i++;
                
                assertEquals("When checking the output file produced by calling the main method given good data, at line 3 we", "The highest IT load of all data centers is: 307.20", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given good data, at line 4 we", "", outputScan.nextLine());i++;
                
                assertEquals("When checking the output file produced by calling the main method given good data, at line 5 we", "The data center closest to the average construction cost is: Holder 584.80 307.20 26.40", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given good data, at line 6 we", "", outputScan.nextLine());i++;
                
                assertEquals("The data centers below the average value for construction cost are: Turner 442.20 168.40 19.00 Holder 584.80 307.20 26.40 HITT 420.70 21.80 11.10 Fortis 574.10 50.50 12.70 Clune 527.90 289.10 25.50 ", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given good data, at line 8 (which should be a blank line) we", "", outputScan.nextLine());i++;
                
            }
            catch (NoSuchElementException e){
                fail("Line " + i + " does not exist but it should");
            }
            
            outputScan.close();
            
        } catch (IOException e) { e.printStackTrace(); }
        
    }
    
    
    
    
    @Test
    public void DataCenterCostAnalysis_BadData_openFileTest() throws IOException {
        // Invoke method
        ArrayList<String> actual = DataCenterCostAnalysis.openFile(generateBadInputFile());
        // Check results
        testStringArray("When checking the String array returned from the openFile method given a file with invalid input data, we",generateBadInputStringArray(),actual);
    }
    
    @Test
    public void DataCenterCostAnalysis_BadData_buildObjectsTest(){
        ArrayList<DataCenter> actual = DataCenterCostAnalysis.createObjects(generateBadInputStringArray());
        testDataCenterArray("When checking the ArrayList returned from the createObjects method given data generated from dataCenterBadData.txt, we",generateBadInputArrayList(),actual);
    }
    
    @Test
    public void DataCenterCostAnalysis_BadData_findMeanTest(){
        double actual = DataCenterCostAnalysis.findMean(generateBadInputArrayList(),DataCenterAttributes.CONSTRUCTION_COST);
        assertEquals("When checking the return value from the findMean method given data generated from dataCenterBadData.txt and the CONSTRUCTION_COST attribute, we",522.8125,actual,0.01);
        
        actual = DataCenterCostAnalysis.findMean(generateBadInputArrayList(),DataCenterAttributes.IT_LOAD);
        assertEquals("When checking the return value from the findMean method given data generated from dataCenterBadData.txt and the IT_LOAD attribute, we",97.3125,actual,0.01);
        
        actual = DataCenterCostAnalysis.findMean(generateBadInputArrayList(),DataCenterAttributes.OPERATING_COST);
        assertEquals("When checking the return value from the findMean method given data generated from dataCenterBadData.txt and the OPERATING_COST attribute, we",16.325,actual,0.01);
    }
    
    @Test
    public void DataCenterCostAnalysis_BadData_findHighValueTest(){
        double actual = DataCenterCostAnalysis.findHighValue(generateBadInputArrayList(),DataCenterAttributes.CONSTRUCTION_COST);
        assertEquals("When checking the return value from the findHighValue method given data generated from dataCenterBadData.txt and the CONSTRUCTION_COST attribute, we",763.4,actual,0.01);
        
        actual = DataCenterCostAnalysis.findHighValue(generateBadInputArrayList(),DataCenterAttributes.IT_LOAD);
        assertEquals("When checking the return value from the findHighValue method given data generated from dataCenterBadData.txt and the IT_LOAD attribute, we",271.0,actual,0.01);
        
        actual = DataCenterCostAnalysis.findHighValue(generateBadInputArrayList(),DataCenterAttributes.OPERATING_COST);
        assertEquals("When checking the return value from the findHighValue method given data generated from dataCenterBadData.txt and the OPERATING_COST attribute, we",26.4,actual,0.01);
    }
    
    @Test
    public void DataCenterCostAnalysis_BadData_findMeanDataCenterTest(){
        DataCenter actual = DataCenterCostAnalysis.findMeanDataCenter(generateBadInputArrayList(),DataCenterAttributes.CONSTRUCTION_COST,522.8125);
        DataCenter expected = generateBadInputArrayList().get(7);
        
        assertTrue("When checking the return value from the findMeanDataCenter method given data generated from dataCenterBadData.txt, the CONSTRUCTION_COST attribute, and the average of CONSTRUCTION_COST, we expected " + expected + " but was " + actual,DataCenterIsEqual(expected,actual));
        
        actual = DataCenterCostAnalysis.findMeanDataCenter(generateBadInputArrayList(),DataCenterAttributes.IT_LOAD,97.3125);
        expected = generateBadInputArrayList().get(5);
        
        assertTrue("When checking the return value from the findMeanDataCenter method given data generated from dataCenterBadData.txt, the IT_LOAD attribute, and the average of IT_LOAD, we expected " + expected + " but was " + actual,DataCenterIsEqual(expected,actual));
        
        actual = DataCenterCostAnalysis.findMeanDataCenter(generateBadInputArrayList(),DataCenterAttributes.OPERATING_COST,16.325);
        expected = generateBadInputArrayList().get(5);
        
        assertTrue("When checking the return value from the findMeanDataCenter method given data generated from dataCenterBadData.txt, the OPERATING_COST attribute, and the average of OPERATING_COST, we expected " + expected + " but was " + actual,DataCenterIsEqual(expected,actual));
    }
    
    @Test
    public void DataCenterCostAnalysis_BadData_findLowestDataCentersTest(){
        ArrayList<DataCenter> actual = DataCenterCostAnalysis.findLowestDataCenters(generateBadInputArrayList(),DataCenterAttributes.CONSTRUCTION_COST,522.8125);
        ArrayList<DataCenter> expected = generateBadInputArrayList();
        expected.remove(7);expected.remove(6);expected.remove(5);expected.remove(3);expected.remove(1);
        
        testDataCenterArray("When checking the ArrayList returned from the findLowestDataCenters method given data generated from dataCenterBadData.txt, the CONSTRUCTION_COST attribute, and the average of CONSTRUCTION_COST, we",expected,actual);
        
        actual = DataCenterCostAnalysis.findLowestDataCenters(generateBadInputArrayList(),DataCenterAttributes.IT_LOAD,97.3125);
        expected = generateBadInputArrayList();
        expected.remove(6);expected.remove(5);expected.remove(3);expected.remove(0);
        
        testDataCenterArray("When checking the ArrayList returned from the findLowestDataCenters method given data generated from dataCenterBadData.txt, the IT_LOAD attribute, and the average of IT_LOAD, we",expected,actual);
        
        actual = DataCenterCostAnalysis.findLowestDataCenters(generateBadInputArrayList(),DataCenterAttributes.OPERATING_COST,16.325);
        expected = generateBadInputArrayList();
        expected.remove(6);expected.remove(3);expected.remove(1);expected.remove(0);
        
        testDataCenterArray("When checking the ArrayList returned from the findLowestDataCenters method given data generated from dataCenterBadData.txt, the OPERATING_COST attribute, and the average of OPERATING_COST, we",expected,actual);
        
    }
    
    @Test
    public void DataCenterCostAnalysis_BadData_writeOutDataCenterArrayListTest() throws IOException {
        File outputFile = folder.newFile("bar.txt");
        PrintWriter outWriter = new PrintWriter(outputFile);
        ArrayList<DataCenter> outputData = generateBadInputArrayList();
        String expected = "Output Message: Turner 442.20 168.40 19.00 Holder 584.80 0.00 26.40 HITT 420.70 21.80 11.10 DPR 726.50 271.00 24.50 Fortis 0.00 0.00 12.70 Mortenson 717.00 117.70 16.20 JEDunn 763.40 199.60 20.70 Clune 527.90 0.00 0.00 ";
        String message = "Output Message: ";
        // Invoke method
        DataCenterCostAnalysis.outputToFile(message,outputData,outWriter);
        outWriter.flush();outWriter.close();
        // Check results
        assertTrue("Output file does not exist", outputFile.exists());
        Scanner outputScan = new Scanner(outputFile);
        
        int i = 0;
        
        assertEquals("When checking the output file produced by outputToFile given an ArrayList<DataCenter> of bad data, at line " +(i+1)+ " we",expected,outputScan.nextLine());
        
        assertEquals("When checking the output file produced by outputToFile given an ArrayList<DataCenter> of bad data, at line 2 (which should be a blank line) we", "", outputScan.nextLine());

//        while (outputScan.hasNextLine()){
//            String expectedString = "";
//            if(i < expected.size()){
//                expectedString = expected.get(i).toString();
//            }
//            assertEquals("When checking the output file produced by outputToFile given an ArrayList<DataCenter> of good data, at line " +(i+2)+ " we",expectedString,outputScan.nextLine().trim());
//            i++;
//        }
        outputScan.close();
    }
    
    @Test
    public void DataCenterCostAnalysis_BadData_mainMethod(){
        try{
            File outputFile = folder.newFile("bar.txt");
            DataCenterCostAnalysis.main(new String[]{generateBadInputFile().getAbsolutePath(),outputFile.getAbsolutePath()});
            
            // Check results
            assertTrue("Output file does not exist", outputFile.exists());
            Scanner outputScan = new Scanner(outputFile);
            int i = 1;
            try {
                assertEquals("When checking the output file produced by calling the main method given good data, at line 1 we", "The average construction cost of all data centers is: 522.81", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given good data, at line 2 (which should be a blank line) we", "", outputScan.nextLine());i++;
                
                assertEquals("When checking the output file produced by calling the main method given good data, at line 3 we", "The highest IT load of all data centers is: 271.00", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given good data, at line 4 (which should be a blank line) we", "", outputScan.nextLine());i++;
                
                assertEquals("When checking the output file produced by calling the main method given good data, at line 5 we", "The data center closest to the average construction cost is: Clune 527.90 0.00 0.00", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given good data, at line 6 (which should be a blank line) we", "", outputScan.nextLine());i++;
                
                assertEquals("The data centers below the average value for construction cost are: Turner 442.20 168.40 19.00 HITT 420.70 21.80 11.10 Fortis 0.00 0.00 12.70 ", outputScan.nextLine());i++;
                assertEquals("When checking the output file produced by calling the main method given good data, at line 8 (which should be a blank line) we", "", outputScan.nextLine());i++;
                
            }
            catch (NoSuchElementException e){
                fail("Line " + i + " does not exist but it should");
            }
            
            outputScan.close();
            
        } catch (IOException e) { e.printStackTrace(); }
        
    }
    
    
    
    
    private File generateGoodInputFile() throws IOException {
        final String INPUT_FILENAME = "foo.txt";
        File inputFile = folder.newFile(INPUT_FILENAME);
        PrintWriter write = new PrintWriter(inputFile);
        write.print("Turner\t442.2\t168.4\t19.0\n" +
          "Holder\t584.8\t307.2\t26.4\n" +
          "HITT\t420.7\t21.8\t11.1\n" +
          "DPR\t726.5\t271.0\t24.5\n" +
          "Fortis\t574.1\t50.5\t12.7\n" +
          "Mortenson\t717.0\t117.7\t16.2\n" +
          "JEDunn\t763.4\t199.6\t20.7\n" +
          "Clune\t527.9\t289.1\t25.5");
        write.close();
        return inputFile;
    }
    
    private ArrayList<String> generateGoodInputStringArray(){
        String[] array = {"Turner\t442.2\t168.4\t19.0",
          "Holder\t584.8\t307.2\t26.4",
          "HITT\t420.7\t21.8\t11.1",
          "DPR\t726.5\t271.0\t24.5",
          "Fortis\t574.1\t50.5\t12.7",
          "Mortenson\t717.0\t117.7\t16.2",
          "JEDunn\t763.4\t199.6\t20.7",
          "Clune\t527.9\t289.1\t25.5"};
        ArrayList<String> toReturn = new ArrayList<>();
        Collections.addAll(toReturn, array);
        return toReturn;
    }
    
    private ArrayList<DataCenter> generateGoodInputArrayList(){
        DataCenter[] temp = {
          createDataCenter("Turner",442.2,168.4,19.0),
          createDataCenter("Holder",584.8,307.2,26.4),
          createDataCenter("HITT",420.7,21.8,11.1),
          createDataCenter("DPR",726.5,271.0,24.5),
          createDataCenter("Fortis",574.1,50.5,12.7),
          createDataCenter("Mortenson",717.0,117.7,16.2),
          createDataCenter("JEDunn",763.4,199.6,20.7),
          createDataCenter("Clune",527.9,289.1,25.5)
        };
        
        return new ArrayList<>(Arrays.asList(temp));
    }
    
    
    private File generateBadInputFile() throws IOException {
        final String INPUT_FILENAME = "foo.txt";
        File inputFile = folder.newFile(INPUT_FILENAME);
        PrintWriter write = new PrintWriter(inputFile);
        write.print("Turner\t442.2\t168.4\t19.0\n" +
          "Holder\t584.8\t3o7.2\t26.4\n" +
          "HITT\t420.7\t21.8\t11.1\n" +
          "DPR\t726.5\t271.0\t24.5\n" +
          "Fortis\t-574.1\t5O.5\t12.7\n" +
          "Mortenson\t717.0\t117.7\t16.2\n" +
          "JEDunn\t763.4\t199.6\t20.7\n" +
          "Clune\t527.9\t-289.1\t2t.5");
        write.close();
        return inputFile;
    }
    
    private ArrayList<String> generateBadInputStringArray(){
        String[] array = {"Turner\t442.2\t168.4\t19.0",
          "Holder\t584.8\t3o7.2\t26.4",
          "HITT\t420.7\t21.8\t11.1",
          "DPR\t726.5\t271.0\t24.5",
          "Fortis\t-574.1\t5O.5\t12.7",
          "Mortenson\t717.0\t117.7\t16.2",
          "JEDunn\t763.4\t199.6\t20.7",
          "Clune\t527.9\t-289.1\t2t.5"};
        ArrayList<String> toReturn = new ArrayList<>();
        Collections.addAll(toReturn, array);
        return toReturn;
    }
    
    private ArrayList<DataCenter> generateBadInputArrayList(){
        DataCenter[] temp = {
          createDataCenter("Turner",442.2,168.4,19.0),
          createDataCenter("Holder",584.8,0,26.4),
          createDataCenter("HITT",420.7,21.8,11.1),
          createDataCenter("DPR",726.5,271.0,24.5),
          createDataCenter("Fortis",0,0,12.7),
          createDataCenter("Mortenson",717.0,117.7,16.2),
          createDataCenter("JEDunn",763.4,199.6,20.7),
          createDataCenter("Clune",527.9,0,0)
        };
        
        return new ArrayList<>(Arrays.asList(temp));
    }
    
    
    private void testDataCenterArray(String message, ArrayList expected, ArrayList actual){
        assertEquals(message + " looked at the size and ",expected.size(),actual.size());
        
        for(int i = 0; i < expected.size(); i++) {
            if (!DataCenterIsEqual(expected.get(i), actual.get(i))) {
                assertEquals(message, expected, actual);
            }
        }
    }
    
    private void testStringArray(String message, ArrayList expected, ArrayList actual){
        assertEquals(message + " looked at the size and ",expected.size(),actual.size());
        
        for(int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) {
                assertEquals(message, expected, actual);
            }
        }
    }
    
    private boolean DataCenterIsEqual(Object o1, Object o2){
        Class c = o1.getClass();
        try {
            Field o1FieldConstructionFirm = c.getDeclaredField("constructionFirm");
            o1FieldConstructionFirm.setAccessible(true);
            Object o1ConstructionFirm = o1FieldConstructionFirm.get(o1);
            
            Field o2FieldConstructionFirm = c.getDeclaredField("constructionFirm");
            o2FieldConstructionFirm.setAccessible(true);
            Object o2ConstructionFirm = o2FieldConstructionFirm.get(o2);
            
            Field o1FieldConstructionCost = c.getDeclaredField("constructionCost");
            o1FieldConstructionCost.setAccessible(true);
            Object o1ConstructionCost = o1FieldConstructionCost.get(o1);
            
            Field o2FieldConstructionCost = c.getDeclaredField("constructionCost");
            o2FieldConstructionCost.setAccessible(true);
            Object o2ConstructionCost = o2FieldConstructionCost.get(o2);
            
            Field o1FieldITLoad = c.getDeclaredField("ITLoad");
            o1FieldITLoad.setAccessible(true);
            Object o1ITLoad = o1FieldITLoad.get(o1);
            
            Field o2FieldITLoad = c.getDeclaredField("ITLoad");
            o2FieldITLoad.setAccessible(true);
            Object o2ITLoad = o2FieldITLoad.get(o2);
            
            Field o1FieldOperatingCost = c.getDeclaredField("operatingCost");
            o1FieldOperatingCost.setAccessible(true);
            Object o1OperatingCost = o1FieldOperatingCost.get(o1);
            
            Field o2FieldOperatingCost = c.getDeclaredField("operatingCost");
            o2FieldOperatingCost.setAccessible(true);
            Object o2OperatingCost = o2FieldOperatingCost.get(o2);
            
            return  o1ConstructionFirm.equals(o2ConstructionFirm) &&
              o1ConstructionCost.equals(o2ConstructionCost) &&
              o1ITLoad.equals(o2ITLoad) &&
              o1OperatingCost.equals(o2OperatingCost);
            
            
        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            //TODO report null input with clarity
            
            fail("Something weird went wrong");
        }
        
        return false;
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
    
    
}
