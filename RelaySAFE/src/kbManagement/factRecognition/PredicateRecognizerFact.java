package kbManagement.factRecognition;

import java.util.ArrayList;

public class PredicateRecognizerFact {
	public static ArrayList<String> predicateRecognizer(String input) {
		ArrayList<String> predicateList = new ArrayList<String>();
		String fact = "";
		String[] partsPredicate = input.split("\\,");
		for (int j = 1; j < partsPredicate.length - 1; j++) {
			if (j == 1) {
				fact = fact + partsPredicate[j];
			} else {
				fact = fact + "," + partsPredicate[j];
			}

		}

		// System.out.println(fact);

		String[] partsPredicateFact = partsPredicate[1].split("\\(");
		String onlyPredicate = partsPredicateFact[0];
		String[] partsArgumentsPredicate = fact.split("\\,");

		String predicateAriety = onlyPredicate + "/" + partsArgumentsPredicate.length;
		predicateList.add(predicateAriety);

		return predicateList;

	}

	/*
	 * Testo del fatto Es. genitore(antonio,marco)
	 */
	public static ArrayList<String> factRecognizer(String input) {
		ArrayList<String> predicateList = new ArrayList<String>();
		String fact = "";
		String[] partsPredicate = input.split("\\,");
		for (int j = 1; j < partsPredicate.length - 1; j++) {
			if (j == 1) {
				fact = fact + partsPredicate[j];
			} else {
				fact = fact + "," + partsPredicate[j];
			}

		}
		predicateList.add(fact);
		return predicateList;

	}
}
