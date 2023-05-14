package kbManagement.structuredInput;

import java.util.ArrayList;

import kbManagement.ruleRecognition.PremiseRecognizer;
import kbManagement.ruleRecognition.RuleRecognizer;

public class SpecialRules {
	
	public static ArrayList<String> Specialrules = new ArrayList<String>();

	public static void addRule(String rule) {
		Specialrules.add(rule);
	}
	
	/**
	 * Metodo che ritorna una stringa contenente tutte le premesse della regola
	 * selezionata
	 * 
	 * @param rule
	 * @return
	 */
	public static String getTotalTail(String rule) {
		ArrayList<String> headAndTail = new ArrayList<String>(); // Arraylist contenente le teste le code e l'operatore
																	// generale
		headAndTail.addAll(RuleRecognizer.recognitionHeadOpTail(rule));

		ArrayList<ArrayList<String>> tails = new ArrayList<ArrayList<String>>(); // Arraylist contenete tutte le code
		tails.addAll(PremiseRecognizer.recognitionPremise2(PremiseRecognizer.recognitionPremise(headAndTail.get(3))));
		String totalTail = "";
		for (int i = 0; i < tails.size(); i++) {
			totalTail += tails.get(i).toString();
		}
		return totalTail;
	}
}
