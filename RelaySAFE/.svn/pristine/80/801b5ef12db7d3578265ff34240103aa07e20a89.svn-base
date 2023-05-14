package kbManagement.filesWR;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ReplaceInFile {

	public static void replaceInFile(String toReplace, String replace) throws IOException {

		try {
			Path path = Paths.get(ReadFile.strPathFileSelected);
			Stream<String> lines = Files.lines(path);
			List<String> replaced = lines.map(line -> line.replaceAll(toReplace, replace)).collect(Collectors.toList());
			Files.write(path, replaced);
			lines.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
