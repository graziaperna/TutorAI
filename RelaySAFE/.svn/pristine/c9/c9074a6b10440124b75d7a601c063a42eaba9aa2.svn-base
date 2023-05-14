package kbManagement.ruleRecognition;

import java.util.ArrayList;

public class VariableRecognizer {

	public static ArrayList<String> variablesRecognizerTails(ArrayList<ArrayList<String>> input) {
		ArrayList<String> variablesList = new ArrayList<String>();

		for (int i = 0; i < input.size(); i++) {

			for (int j = 0; j < input.get(i).size(); j++) {

				String[] partsPredicate = input.get(i).get(j).split("\\(");
				String[] partsArguments = partsPredicate[partsPredicate.length - 1].split("\\,");

				for (int k = 0; k < partsArguments.length; k++) {
					if (!partsArguments[k].equalsIgnoreCase("OR") && !partsArguments[k].equalsIgnoreCase("AND")
							&& !partsArguments[k].equalsIgnoreCase("NOT")) {
						if (k == partsArguments.length - 1) {
							if (Character.isUpperCase(partsArguments[k].charAt(0)))
								variablesList.add(partsArguments[k].substring(0, partsArguments[k].length() - 1));
						} else {
							if (Character.isUpperCase(partsArguments[k].charAt(0)))
								variablesList.add(partsArguments[k]);
						}
					}

				}
			}
		}
		return variablesList;

	}

	public static ArrayList<String> variablesRecognizerHead(ArrayList<String> input) {
		ArrayList<String> variablesList = new ArrayList<String>();

		for (int i = 0; i < input.size(); i++) {

			String[] partsPredicate = input.get(i).split("\\(");
			String[] partsArguments = partsPredicate[partsPredicate.length - 1].split("\\,");

			for (int k = 0; k < partsArguments.length; k++) {
				if (k == partsArguments.length - 1) {
					if (Character.isUpperCase(partsArguments[k].charAt(0)))
						variablesList.add(partsArguments[k].substring(0, partsArguments[k].length() - 1));
				} else {
					if (Character.isUpperCase(partsArguments[k].charAt(0)))
						variablesList.add(partsArguments[k]);
				}
			}
		}
		return variablesList;

	}

	public static ArrayList<String> variablesRecognizerTreeView(String input) {
		ArrayList<String> constantList = new ArrayList<String>();

		String[] partsPredicate = input.split("\\(");
		String[] partsArguments = partsPredicate[partsPredicate.length - 1].split("\\,");

		for (int k = 0; k < partsArguments.length; k++) {
			if (k == partsArguments.length - 1) {
				if (Character.isUpperCase(partsArguments[k].charAt(0)))
					constantList.add(partsArguments[k].substring(0, partsArguments[k].length() - 1));
			} else {
				if (Character.isUpperCase(partsArguments[k].charAt(0)))
					constantList.add(partsArguments[k]);
			}
		}
		return constantList;

	}
}
