package kbManagement.ruleRecognition;

import java.util.ArrayList;

public class HeadRecognizer {
	public static ArrayList<String> recognitionPremise(String head) {

		ArrayList<String> premiseRecognized = new ArrayList<String>();

		String[] parts = head.split("\\),");
		for (int i = 0; i < parts.length; i++) {
			if (i != parts.length - 1) {
				parts[i] = parts[i] + ")";
			}
			premiseRecognized.add(parts[i]);
		}

		return premiseRecognized;

	}
}
