package Projects.Project6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AnalyzeOperatingCosts {
	public static void main(String[] args) throws FileNotFoundException {
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		int numberOfYears = Integer.parseInt(args[2]);
		ArrayList<Server> servers = readFile(inputFile);
		ArrayList<Server> cheapestServers = cheapestServers(servers);
		printOutput(cheapestServers, outputFile);
	}
	
	public static ArrayList<Server> readFile(File inputFile) throws FileNotFoundException {
		ArrayList<Server> servers = new ArrayList<>();
		try {
			Scanner input = new Scanner(inputFile);
			while (input.hasNextLine()) {
				String[] serverInfo = input.nextLine().split(",");
				if (serverInfo.length == 4) {
					servers.add(new OwnedServer(serverInfo[0], Double.parseDouble(serverInfo[1]), Double.parseDouble(serverInfo[2]), Double.parseDouble(serverInfo[3])));
				} else if (serverInfo.length == 5) {
					servers.add(new RentalServer(serverInfo[0], Double.parseDouble(serverInfo[1])));
				} else if (serverInfo.length == 6) {
					servers.add(new FinancedServer(serverInfo[0], Double.parseDouble(serverInfo[1]), Double.parseDouble(serverInfo[2]), Double.parseDouble(serverInfo[3]), Integer.parseInt(serverInfo[4]), Double.parseDouble(serverInfo[5])));
				}
			}
		} catch (FileNotFoundException readFileException) {
			System.out.println("readFile(): File not found.");
		}
		return servers;
	}
	
	public static ArrayList<Server> cheapestServers(ArrayList<Server> servers) {
		ArrayList<Server> cheapestServers = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			int cheapestIndex = 0;
			for (int j = 0; j < servers.size(); j++) {
				if (servers.get(j).compareTo(servers.get(cheapestIndex)) < 0) {
					cheapestIndex = j;
				}
			}
			cheapestServers.add(servers.get(cheapestIndex));
			servers.remove(cheapestIndex);
		}
		return cheapestServers;
	}

	public static void printOutput(ArrayList<Server> cheapestServers, File outputFile) throws FileNotFoundException {
		try (PrintWriter output = new PrintWriter(outputFile)) {
			for (Server server : cheapestServers) {
				output.print(server.toString());
			}
		}
	}
}
