package Labs.Lab12;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.Timeout;

import java.io.*;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.util.Scanner;


import static org.junit.Assert.*;



public class Lab12Test {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(3); // 3 seconds max per method tested

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void goodDataTest() throws FileNotFoundException {
        File outputFile = generateOutputFile();
        DaysPerMonth.processFile(generateGoodInputFile(),outputFile);

        assertTrue("Output file does not exist", outputFile.exists());

        Scanner outputScan;
        int i = 1;
        try {
            outputScan = new Scanner(outputFile);

            assertEquals("When looking at the first line of the output file, we","There are 30 days in this month.",outputScan.nextLine()); i++;
            assertEquals("When looking at the second line of the output file, we","There are 30 days in this month.",outputScan.nextLine()); i++;
            assertEquals("When looking at the third line of the output file, we","There are 29 days in this month.",outputScan.nextLine());
            outputScan.close();
        } catch (FileNotFoundException e) {
            fail("outputFile not found");
        } catch (NoSuchElementException e){
            fail("Output file line " + i + " and beyond are missing ");
        }
    }

    @Test
    public void goodDataTestLineCount() throws FileNotFoundException {
        File outputFile = generateOutputFile();
        DaysPerMonth.processFile(generateGoodInputFile(),outputFile);

        assertTrue("Output file does not exist", outputFile.exists());

        Scanner outputScan;
        int i = 0;
        try {
            outputScan = new Scanner(outputFile);
            while(outputScan.hasNextLine()) {
                outputScan.nextLine();
                i++;
            }
            outputScan.close();
        } catch (FileNotFoundException e) {
            fail("outputFile not found");
        }
        if(i > 3) {
            fail("It seems good data input is producing too many lines");
        }
        if(i < 3) {
            fail("It seems like good data input is not producing enough lines");
        }
    }

    @Test
    public void badDataTest() throws FileNotFoundException {
        File outputFile = generateOutputFile();
        DaysPerMonth.processFile(generateBadInputFile(),outputFile);

        assertTrue("Output file does not exist", outputFile.exists());

        Scanner outputScan;
        int i = 1;
        try {
            outputScan = new Scanner(outputFile);

            assertEquals("When looking at the first line of the output file, we","Month must be between 1 and 12",outputScan.nextLine()); i++;
            assertEquals("When looking at the second line of the output file, we","Not an integer",outputScan.nextLine()); i++;
            assertEquals("When looking at the third line of the output file, we","Year cannot be negative",outputScan.nextLine()); i++;
            assertEquals("When looking at the fourth line of the output file, we","There are 29 days in this month.",outputScan.nextLine());
            outputScan.close();
        } catch (FileNotFoundException e) {
            fail("outputFile not found");
        } catch (NoSuchElementException e){
            fail("Output file line " + i + " and beyond are missing ");
        }
    }

    @Test
    public void badDataTestLineCount() throws FileNotFoundException {
        File outputFile = generateOutputFile();
        DaysPerMonth.processFile(generateBadInputFile(),outputFile);

        assertTrue("Output file does not exist", outputFile.exists());
        StringBuilder studentOutput = new StringBuilder();
        Scanner outputScan;
        int i = 0;
        try {
            outputScan = new Scanner(outputFile);
            while(outputScan.hasNextLine()) {
                studentOutput.append(outputScan.nextLine());
                studentOutput.append("\n");
                i++;
            }
            outputScan.close();
        } catch (FileNotFoundException e) {
            fail("outputFile not found");
        }
        String message = "";
        if(i > 4) {
            message = "It seems bad data input is producing too many lines. Here's the output we found:\n";
        }
        if(i < 3) {
            message = "It seems like bad data input is not producing enough lines. Here's the output we found::\n";
        }
        message += studentOutput;
        assertEquals(message, 4, i);
    }

    @Test
    public void commandLineArgumentTest(){
        File outputFile = generateOutputFile();
        String[] args = {generateGoodInputFile().getAbsolutePath(),outputFile.getAbsolutePath()};
        Method studentMain;
        try {
            studentMain = DaysPerMonth.class.getMethod("main", String[].class);
            assertEquals("Your main method should not be throwing any exceptions.",
                    0, studentMain.getExceptionTypes().length);
            studentMain.invoke(null, (Object) args);
        } catch(Exception e) {
            throw new AssertionError("Your main method is sus. Contact an instructor for assistance");
        }
        assertTrue("Output file does not exist", outputFile.exists());

        Scanner outputScan;
        int i = 1;
        try {
            outputScan = new Scanner(outputFile);

            assertEquals("When looking at the first line of the output file, we","There are 30 days in this month.",outputScan.nextLine()); i++;
            assertEquals("When looking at the second line of the output file, we","There are 30 days in this month.",outputScan.nextLine()); i++;
            assertEquals("When looking at the third line of the output file, we","There are 29 days in this month.",outputScan.nextLine());
            outputScan.close();
        } catch (FileNotFoundException e) {
            fail("outputFile not found");
        } catch (NoSuchElementException e){
            fail("Output file line " + i + " and beyond are missing ");
        }
    }

    private File generateGoodInputFile(){
        final String INPUT_FILENAME = "foo.txt";
        File inputFile = null;
        try {
            inputFile = folder.newFile(INPUT_FILENAME);


        PrintWriter write = new PrintWriter(inputFile);
        write.print("6,2000\n" +
                    "4,2001\n" +
                    "2,2008");
        write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputFile;

    }

    private File generateBadInputFile(){
        final String INPUT_FILENAME = "foo.txt";
        File inputFile = null;
        try {
            inputFile = folder.newFile(INPUT_FILENAME);

            PrintWriter write = new PrintWriter(inputFile);
            write.print("66,2000\n" +
                        "re,2010\n" +
                        "4,-6754\n" +
                        "2,2008\n");
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputFile;

    }

    private File generateOutputFile(){
        final String OUTPUT_FILENAME = "bar.txt";
        File outputFile = null;
        try {
            outputFile = folder.newFile(OUTPUT_FILENAME);
            PrintWriter write = new PrintWriter(outputFile);
            write.print("");
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;

    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
}
