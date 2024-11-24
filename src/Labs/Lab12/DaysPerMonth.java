package Labs.Lab12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *   DaysPerMonth
 *   VCU - Computer Science Department
 *   A program that prompts the user for a month and year (both as integers)
 *   then displays the number of days in that month.
 **/

public class DaysPerMonth {
    // I'M REALLY SORRY MY CODE IS SO UGLY... MY BRAIN ISN'T BRAINING RIGHT NOW
    // I AM STILL IN SHOCK ABOUT THE ELECTION :( 
    public static void main(String[] args) {
        String inputFileName = "";
        String outputFileName = "";
        if (args.length == 2) {
            inputFileName = args[0];
            outputFileName = args[1];
        } else if (args.length < 1) {
            System.out.println("File paths for both input and output files are required.");
        } else {
            System.out.println("Too many arguments. Only two file paths are required.");
        }
        try {
            processFile(new File(inputFileName), new File(outputFileName));
        } catch (FileNotFoundException e) {
            System.out.println("Bad file name.");
        }
    }
    
    // method to process the input file and write the output to the output file
    public static void processFile(File inputFile, File outputFile) throws FileNotFoundException {
        try (PrintWriter output = new PrintWriter(outputFile)) {
            Scanner input = new Scanner(inputFile);
            int month = 0;
            int year = 0;
            // reads the input file line by line, splitting each line into an array of strings
            while(input.hasNextLine()) {
                boolean validData = true;
                String[] line = input.nextLine().split(",");
                
                // checks if the month is between 1 and 12, and if the year is positive
                try {
                    month = Integer.parseInt(line[0]);
                    if (month < 1 || month > 12) {
                        validData = false;
                        output.println("Month must be between 1 and 12");
                    }
                } catch (NumberFormatException e) {
                    validData = false;
                    output.println("Not an integer");
                }
                try {
                    year = Integer.parseInt(line[1]);
                    if (year < 1) {
                        validData = false;
                        output.println("Year cannot be negative");
                    }
                } catch (NumberFormatException e) {
                    validData = false;
                    output.println("Year must be a positive integer");
                }
                
                // if the data is valid, it calls getDays() to determine the number of days in the month
                if (validData) {
                    int days = getDays(month, year);
                    output.println("There are " + days + " days in this month.");
                }
            }
        }
    }

    /**
     * method to determine the days for the given month and year
     **/
    private static int getDays(int mon, int yr) {
        int numDays = 0;

        switch(mon) {
            case 2: // February
                numDays = 28;
                if (yr % 4 == 0) {
                    numDays = 29;
                    if (yr % 100 == 0 && yr % 400 != 0) {
                        numDays = 28;
                    }
                }
                break;

            case 4:   //April
            case 6:   // June
            case 9:   // September
            case 11:  // November
                numDays = 30;
                break;

            default: numDays = 31;  // all the rest
        }
        return numDays;
    }

}
