package kbManagement.filesWR;

import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;

import java.io.*;

public class WriteFile {

	public static void writeFile(String s) {

		byte data[] = s.getBytes();
		Path p = Paths.get(ReadFile.strPathFileSelected);

		try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND))) {
			out.write(data, 0, data.length);
		} catch (IOException x) {
			System.err.println(x);
		}
	}

}
