package kbManagement.structuredInput;

import java.util.ArrayList;

import kbManagement.ruleRecognition.HeadRecognizer;
import kbManagement.ruleRecognition.PremiseRecognizer;
import kbManagement.ruleRecognition.RuleRecognizer;

public class Rules {

	public static ArrayList<String> rules = new ArrayList<String>();

	public static void addRule(String rule) {
		rules.add(rule);
	}

	public static String generateId() {
		ArrayList<String> totalRules = new ArrayList<String>();
		totalRules.addAll(rules);
		totalRules.addAll(SpecialRules.Specialrules);
		ArrayList<Integer> totalRulesId = new ArrayList<Integer>();
		for(int i=0;i<totalRules.size();i++) {
			String[] parts = totalRules.get(i).split(",");
			String parts1 = parts[0].substring(5);
			totalRulesId.add(Integer.parseInt(parts1));
		}
		int idMax=0;
		for(int j=0;j<totalRulesId.size();j++) {
			if(totalRulesId.get(j)>idMax) {
				idMax=totalRulesId.get(j);
			}
		}
		int idNew = idMax +1;
		String id = String.valueOf(idNew);
		return id;
	}

	public static ArrayList<String> getHeadsRule(String rule) {
		ArrayList<String> headAndTail = new ArrayList<String>(); // Arraylist contenente le teste le code e l'operatore
																	// generale
		headAndTail.addAll(RuleRecognizer.recognitionHeadOpTail(rule));

		ArrayList<String> heads = new ArrayList<String>(); // Arraylist contenente le teste
		heads.addAll(HeadRecognizer.recognitionPremise(headAndTail.get(0)));

		return heads;
	}

	public static ArrayList<ArrayList<String>> getTailsRule(String rule) {
		ArrayList<String> headAndTail = new ArrayList<String>(); // Arraylist contenente le teste le code e l'operatore
																	// generale
		headAndTail.addAll(RuleRecognizer.recognitionHeadOpTail(rule));

		ArrayList<ArrayList<String>> tails = new ArrayList<ArrayList<String>>(); // Arraylist contenete tutte le code
		tails.addAll(PremiseRecognizer.recognitionPremise2(PremiseRecognizer.recognitionPremise(headAndTail.get(3))));

		return tails;
	}

	/**
	 * Metodo che ritorna una stringa contenente tutte le teste della regola
	 * selezionata
	 * 
	 * @param rule
	 * @return
	 */
	public static String getTotalHead(String rule) {
		ArrayList<String> headAndTail = new ArrayList<String>(); // Arraylist contenente le teste le code e l'operatore
																	// generale
		headAndTail.addAll(RuleRecognizer.recognitionHeadOpTail(rule));

		ArrayList<String> heads = new ArrayList<String>(); // Arraylist contenente le teste
		heads.addAll(HeadRecognizer.recognitionPremise(headAndTail.get(0)));
		String totalHead = "";
		for (int i = 0; i < heads.size(); i++) {
			totalHead += heads.get(i);
		}
		return totalHead;
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
			totalTail += tails.get(i);
		}
		return totalTail;
	}
	
	
	public static String getOperator(String rule) {
		String operatore="";
		String rule1 = rule.replace("[", "(").replace("]", ")");
		
		String[] parts = rule1.split("\\(\\(");
		String[] parts1 = parts[0].split("\\,");
		operatore = parts1[parts1.length-1];
		
		return operatore;
	}
}
