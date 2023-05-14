package kbManagement.structuredInput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Constants {

	public static ArrayList<String> constants = new ArrayList<String>();

	public static void addConstants(ArrayList<String> constantsList) {

		constants.addAll(constantsList);
		removeDuplicateConstants(constants);
	}

	public static void removeDuplicateConstants(ArrayList<String> constantsList) {

		constants = (ArrayList<String>) constantsList.stream().distinct().collect(Collectors.toList());
	}

}
