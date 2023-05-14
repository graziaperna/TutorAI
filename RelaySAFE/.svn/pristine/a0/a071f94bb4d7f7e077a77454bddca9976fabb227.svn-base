package kbManagement.structuredInput;

import java.util.ArrayList;

import kbManagement.ruleRecognition.ConstantsRecognizer;
import kbManagement.ruleRecognition.HeadRecognizer;
import kbManagement.ruleRecognition.PredicateRecognizer;
import kbManagement.ruleRecognition.PremiseRecognizer;
import kbManagement.ruleRecognition.RuleRecognizer;
import kbManagement.ruleRecognition.VariableRecognizer;

public class Variables {

	public static ArrayList<String> variablesRule = new ArrayList<String>();

	public static void addVariables(ArrayList<String> variables) {

		variablesRule.addAll(variables);
	}

	public static void initializeVariables() {

		variablesRule.clear();
	}

	public static ArrayList<String> getVariables(String rule) {

		ArrayList<String> headAndTail = new ArrayList<String>();
		headAndTail.addAll(RuleRecognizer.recognitionHeadOpTail(rule));

		ArrayList<String> head = new ArrayList<String>();

		head.addAll(HeadRecognizer.recognitionPremise(headAndTail.get(0)));

		ArrayList<ArrayList<String>> tails = new ArrayList<ArrayList<String>>(); // Arraylist contenete tutte le code
		tails.addAll(PremiseRecognizer.recognitionPremise2(PremiseRecognizer.recognitionPremise(headAndTail.get(3))));

		ArrayList<String> predicateListTails = new ArrayList<String>();
		predicateListTails.addAll(PredicateRecognizer.predicateRecognizerTails(tails));

		ArrayList<String> predicateListHead = new ArrayList<String>();
		predicateListHead.addAll(PredicateRecognizer.predicateRecognizerHead(head));

		ArrayList<String> constantsListTails = new ArrayList<String>();
		constantsListTails.addAll(ConstantsRecognizer.constantsRecognizerTails(tails));

		ArrayList<String> constantsListHead = new ArrayList<String>();
		constantsListHead.addAll(ConstantsRecognizer.constantsRecognizerHead(head));

		ArrayList<String> variablesListTails = new ArrayList<String>();
		variablesListTails.addAll(VariableRecognizer.variablesRecognizerTails(tails));

		ArrayList<String> variablesListHead = new ArrayList<String>();
		variablesListHead.addAll(VariableRecognizer.variablesRecognizerHead(head));

		ArrayList<String> allVariables = new ArrayList<String>();
		allVariables.addAll(variablesListHead);
		allVariables.addAll(variablesListTails);

		return allVariables;

	}

}
