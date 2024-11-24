/**
 * Lab11.java is a program that reads a file of team stats and writes a summary to an output file.
 *
 * @author Maggie Kohn
 * @version 2024.10.30
 * CMSC255.002
 */

package Labs.Lab11;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads a file of team stats and writes a summary to an output file.
 */
public class Lab11 {
	
	/**
	 * Reads the input and output file names from the command line and calls processFile().
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		try {
			processFile(inputFile, outputFile);
		}
		catch (FileNotFoundException e) {
			System.out.println("main(): File(s) not found");
		}
	}
	
	/**
	 * Reads the input file and creates a Team object for each line, then calls writeSummaryToFile().
	 * @param inputFile the input file
	 * @param outputFile the output file
	 */
	public static void processFile(File inputFile, File outputFile) throws FileNotFoundException {
		ArrayList<Team> teams = new ArrayList<>();
		try (Scanner input = new Scanner(inputFile)) {
			while (input.hasNextLine()) {
				String[] teamStats = input.nextLine().split(",");
				Team team = new Team(teamStats[0], Integer.parseInt(teamStats[1]), Double.parseDouble(teamStats[2]));
				teams.add(team);
			}
		} catch (FileNotFoundException e) {
			System.out.println("getTeamsFromFile(): Input file not found");
		}
		writeSummaryToFile(outputFile, teams);
	}
	
	/**
	 * Writes the summary of the team stats to the output file after calling calculateStats().
	 * @param outputFile the output file
	 * @param teams the list of teams
	 */
	public static void writeSummaryToFile(File outputFile, ArrayList<Team> teams) {
		String summary = calculateStats(teams);
		try (PrintWriter output = new PrintWriter(outputFile)) {
			output.println(summary);
		} catch (FileNotFoundException e) {
			System.out.println("writeSummaryToFile(): Output file not found");
		}
	}
	
	/**
	 * Calculates the maximum and minimum goals scored by teams and the average shots per game.
	 * @param teams the list of teams
	 * @return the summary of the team stats
	 */
	public static String calculateStats(ArrayList<Team> teams) {
		Team maxTeam = teams.get(0);
		Team minTeam = teams.get(0);
		double totalShots = 0;
		
		for (Team team : teams) {
			totalShots += team.getNumShots();
			if (team.getNumGoals() > maxTeam.getNumGoals()) {
				maxTeam = team;
			}
			if (team.getNumGoals() < minTeam.getNumGoals()) {
				minTeam = team;
			}
		}
		double avgShots = totalShots / teams.size();
		return
			"Maximum goals Scored: " + maxTeam.getName() + " " + maxTeam.getNumGoals() + "\n" +
			"Minimum goals Scored: " + minTeam.getName() + " " + minTeam.getNumGoals() + "\n" +
			"Average shots per game: " + String.format("%.3f", avgShots);
	}
}
