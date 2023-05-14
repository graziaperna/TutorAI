package kbManagement.ruleRecognition;

import java.util.ArrayList;

public class PremiseRecognizer {

	public static ArrayList<String> recognitionPremise(String tail) {

		ArrayList<String> premiseRecognized = new ArrayList<String>();

		String[] parts = tail.split("\\),");
		parts[0] = parts[0].substring(2, parts[0].length());
		parts[parts.length - 2] = parts[parts.length - 2].substring(0, parts[parts.length - 2].length() - 2);
		for (int i = 0; i < parts.length - 1; i++) {
			parts[i] = parts[i] + ")";
			premiseRecognized.add(parts[i]);
		}

		return premiseRecognized;

	}

	public static ArrayList<ArrayList<String>> recognitionPremise2(ArrayList<String> premise) {

		ArrayList<ArrayList<String>> premiseRecognized2 = new ArrayList<ArrayList<String>>();
		String operatore = "";

		for (int i = 0; i < premise.size(); i++) {
			if (premise.get(i).contains("or(")) {
				operatore = "or";
				String[] parts = premise.get(i).split("\\+");
				int numParts = parts.length - 1;
				parts[0] = parts[0].substring(4, parts[0].length());
				parts[numParts] = parts[numParts].substring(0, parts[numParts].length() - 2);

				ArrayList<String> partsList = new ArrayList<String>();
				partsList.add(operatore);
				for (int w = 0; w < parts.length; w++) {
					partsList.add(parts[w]);

				}
				premiseRecognized2.add(partsList);
				/*
				 * for(int l = 0; l< premiseRecognized2.size(); l++) {
				 * System.out.println(premiseRecognized2.get(l).toString()); }
				 */
			} else if (premise.get(i).contains("and(")) {
				operatore = "and";
				String[] parts = premise.get(i).split("\\-");
				int numParts = parts.length - 1;
				parts[0] = parts[0].substring(5, parts[0].length());
				parts[numParts] = parts[numParts].substring(0, parts[numParts].length() - 2);

				ArrayList<String> partsList = new ArrayList<String>();
				partsList.add(operatore);
				for (int w = 0; w < parts.length; w++) {
					partsList.add(parts[w]);

				}
				premiseRecognized2.add(partsList);
				/*
				 * for(int l = 0; l< premiseRecognized2.size(); l++) {
				 * System.out.println(premiseRecognized2.get(l).toString()); }
				 */
			} else if (premise.get(i).contains("not(")) {
				operatore = "not";
				String parts = premise.get(i);
				parts = parts.substring(4, premise.get(i).length() - 1);

				ArrayList<String> partsList = new ArrayList<String>();
				partsList.add(operatore);
				partsList.add(parts);

				premiseRecognized2.add(partsList);
				/*
				 * for(int l = 0; l< premiseRecognized2.size(); l++) {
				 * System.out.println(premiseRecognized2.get(l).toString()); }
				 */
			} else {
				ArrayList<String> partsList = new ArrayList<String>();
				partsList.add(premise.get(i));

				premiseRecognized2.add(partsList);
			}
		}

		// predicateRecognizer(premiseRecognized2);
		return premiseRecognized2;
	}

}
