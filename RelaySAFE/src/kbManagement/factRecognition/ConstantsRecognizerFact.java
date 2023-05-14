package kbManagement.factRecognition;

import java.util.ArrayList;

public class ConstantsRecognizerFact {
	public static ArrayList<String> constantsRecognizerFact(ArrayList<String> input) {
		ArrayList<String> constantList = new ArrayList<String>();

		for (int i = 0; i < input.size(); i++) {

			String[] partsPredicate = input.get(i).split("\\(");
			String[] partsArguments = partsPredicate[partsPredicate.length - 1].split("\\,");

			for (int k = 0; k < partsArguments.length; k++) {
				if (k == partsArguments.length - 1) {
					if (!Character.isUpperCase(partsArguments[k].charAt(0)))
						constantList.add(partsArguments[k].substring(0, partsArguments[k].length() - 1));
				} else {
					if (!Character.isUpperCase(partsArguments[k].charAt(0)))
						constantList.add(partsArguments[k]);
				}
			}
		}
		return constantList;

	}

	public static ArrayList<String> constantsRecognizerFact(String input) {
		ArrayList<String> constantList = new ArrayList<String>();

		String[] partsPredicate = input.split("\\(");
		String[] partsArguments = partsPredicate[partsPredicate.length - 1].split("\\,");

		for (int k = 0; k < partsArguments.length; k++) {
			if (k == partsArguments.length - 1) {
				if (!Character.isUpperCase(partsArguments[k].charAt(0)))
					constantList.add(partsArguments[k].substring(0, partsArguments[k].length() - 1));
			} else {
				if (!Character.isUpperCase(partsArguments[k].charAt(0)))
					constantList.add(partsArguments[k]);
			}
		}
		return constantList;

	}
}
