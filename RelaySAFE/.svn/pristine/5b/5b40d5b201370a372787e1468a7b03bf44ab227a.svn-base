package kbManagement.structuredInput;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Predicate {

	public static ArrayList<String> predicates = new ArrayList<String>();

	public static void addPredicates(ArrayList<String> predicatesList) {
		predicates.addAll(predicatesList);
		removeDuplicatePredicates(predicates);
	}

	public static void removeDuplicatePredicates(ArrayList<String> predicatesList) {
		predicates = (ArrayList<String>) predicatesList.stream().distinct().collect(Collectors.toList());
	}

	public static String getNumArguments(String predicate) {
		String parts[] = predicate.split("/");
		String numAgs = parts[1];

		return numAgs;
	}

	public static String getPredicate(String predicate) {
		String parts[] = predicate.split("/");
		String numAgs = parts[0];

		return numAgs;
	}

	public static String reconstructPredicates(String predicate, String numArgs) {
		String predicateComplete = predicate + "/" + numArgs;
		return predicateComplete;

	}

}
