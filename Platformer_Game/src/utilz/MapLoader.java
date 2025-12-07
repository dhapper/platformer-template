package utilz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {
	
	// reads from csv, returns int[][]

	public static int[][] LoadMapFromCSV(String filename) {
	    List<int[]> rows = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] tokens = line.split(",");
	            int[] row = new int[tokens.length];
	            for (int i = 0; i < tokens.length; i++) {
	                row[i] = Integer.parseInt(tokens[i].trim());
	            }
	            rows.add(row);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Convert List to 2D array
	    int[][] map = new int[rows.size()][];
	    for (int i = 0; i < rows.size(); i++) {
	        map[i] = rows.get(i);
	    }
	    return map;
	}

}