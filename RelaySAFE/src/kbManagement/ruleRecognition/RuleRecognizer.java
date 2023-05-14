package kbManagement.ruleRecognition;

import java.util.ArrayList;

public class RuleRecognizer {

	public static ArrayList<String> recognitionHeadOpTail(String rule) {

		ArrayList<String> ruleRecognized = new ArrayList<String>();
		String str1 = null;
		String str2 = null;
		String str3 = null;

		int indexComma = indexComma(rule);

		for (int i = 0; i < rule.length(); i++) {
			if (rule.charAt(i) == 'o' && rule.charAt(i + 1) == 'r' && rule.charAt(i + 2) == '(') {
				str1 = rule.substring(indexComma, i - 1);
				str2 = rule.substring(i, i + 2);
				str3 = rule.substring(i + 2, rule.length());
				i = rule.length();
			} else if (rule.charAt(i) == 'n' && rule.charAt(i + 1) == 'd' && rule.charAt(i + 2) == '(') {
				str1 = rule.substring(indexComma, i - 2);
				str2 = rule.substring(i - 1, i + 2);
				str3 = rule.substring(i + 2, rule.length());
				i = rule.length();
			} else if (rule.charAt(i) == 'o' && rule.charAt(i + 1) == 't' && rule.charAt(i + 2) == '(') {
				str1 = rule.substring(indexComma, i - 2);
				str2 = rule.substring(i - 1, i + 2);
				str3 = rule.substring(i + 2, rule.length());
				i = rule.length();
			}

		}
		ruleRecognized.add(str1);
		ruleRecognized.add(str2);
		ruleRecognized.add(str3);
		ruleRecognized.add(TailOpTrasform.trasformOpTail(str3));

		// System.out.println(ruleRecognized.get(0).toString());
		// System.out.println(ruleRecognized.get(1).toString());
		// System.out.println(ruleRecognized.get(2).toString());
		// System.out.println(ruleRecognized.get(3).toString());

		// PremiseRecognizer.recognitionPremise(ruleRecognized.get(3));

		return ruleRecognized;
	}

	/*
	 * Ritorna l'indice della prima virgola presente nella regola
	 */
	public static int indexComma(String rule) {
		int c = 0, indexComma = 0;
		boolean tr = false;
		while (!tr) {
			if (rule.charAt(c) == ',') {
				indexComma = c + 1;
				tr = true;
			}
			c++;
		}
		return indexComma;
	}

	public static int getRuleId(String rule) {
		int id;
		String idSplitted = "";
		String[] idSplit = rule.split("\\,");
		idSplitted = idSplit[0];
		int idSplittedLength = idSplitted.length();
		id = Integer.parseInt(idSplitted.substring(5, idSplittedLength));
		return id;
	}

	public static float getRulePriority(String rule) {
		float priority;
		String prioritySplitted = "";
		String[] idSplit = rule.split("\\,");
		prioritySplitted = idSplit[idSplit.length - 2];
		priority = Float.parseFloat(prioritySplitted);
		return priority;
	}

	public static float getRuleCertezza(String rule) {
		float certezza;
		String certezzaSplitted = "";
		String[] idSplit = rule.split("\\,");
		certezzaSplitted = idSplit[idSplit.length - 1];

		certezza = Float.parseFloat(certezzaSplitted.substring(0, certezzaSplitted.length() - 2));
		return certezza;
	}
}
