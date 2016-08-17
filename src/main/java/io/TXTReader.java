package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import main.Main;

public class TXTReader {
	
	/**
	 * This function reads a text file and returns it's lines in a LinkedList<String>.
	 * @param path - The path of the file.
	 * @return LinkedList<String> lines of the file.
	 */
	public static LinkedList<String> read(String path){
		
		Main.log.logMessage("Start Reading The Urls File..");
		
		//Better than ArrayList which may need to copy all the elements when the array size is smaller than the number of urls.
		LinkedList<String> lines = new LinkedList<String>();
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(path));
		    String line = br.readLine();
		    while (line != null) {
		    	lines.add(line);
		        line = br.readLine();
		    }
		}catch (IOException e) {
			Main.log.logMessage("An Error Encountered While Reading The Urls File, Got IO Exception");
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				Main.log.logMessage("An Error Encountered While Trying To Close The File.");
			}
		}
		
		Main.log.logMessage("Finished Reading The Urls File..");
        return lines;
	}
	
}
