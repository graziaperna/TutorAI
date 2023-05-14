package kbManagement.filesWR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import kbManagement.regex.RegexLinesFile;

import java.nio.file.Files;

public class ReadFile {
	
	public static ArrayList<String> linesFile = new ArrayList<String>();
	protected static String strPathFileSelected;

	public static void readFile(String pathFileSelected) {
		strPathFileSelected = pathFileSelected;
		
		Path file = Paths.get(pathFileSelected);

		try (InputStream in = Files.newInputStream(file);
		     BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				linesFile.add(line);
			}
		} catch (IOException x) {
			System.err.println(x);
		}
		//System.out.println("linee lett: "+linesFile.toString());
		RegexLinesFile.linesDispatcher(linesFile);
		linesFile.clear();
	}

	public static void readFileFromPath(String uri) {
		Path file = Paths.get(uri);

		try (InputStream in = Files.newInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
				String line = null;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
		} catch (IOException x) {
			System.err.println(x);
		}

	}

}
