package kbManagement.structuredInput;

import java.util.ArrayList;

import kbManagement.factRecognition.CommentFactRecognizer;
import kbManagement.factRecognition.ConstantsRecognizerFact;
import kbManagement.factRecognition.FactRecognizer;
import kbManagement.factRecognition.PredicateRecognizerFact;
import kbManagement.ruleRecognition.CommentRuleRecognizer;
import kbManagement.ruleRecognition.ConstantsRecognizer;
import kbManagement.ruleRecognition.HeadRecognizer;
import kbManagement.ruleRecognition.PredicateRecognizer;
import kbManagement.ruleRecognition.PremiseRecognizer;
import kbManagement.ruleRecognition.RuleRecognizer;
import kbManagement.ruleRecognition.VariableRecognizer;

public class Structurizer {

	public static void structuredRule(String rules) {
		int idRules = RuleRecognizer.getRuleId(rules);
		//System.out.println("id della regola:\n" + idRules + "\n------------------------");

		float priorityRules = RuleRecognizer.getRulePriority(rules);
		//System.out.println("priorit� della regola:\n" + priorityRules + "\n------------------------");

		float certezzaRules = RuleRecognizer.getRuleCertezza(rules);
		//System.out.println("certezza della regola:\n" + certezzaRules + "\n------------------------");
		
		String operatore = Rules.getOperator(rules);
		//System.out.println("operatore della regola:\n" + operatore + "\n------------------------");

		ArrayList<String> headAndTail = new ArrayList<String>(); // Arraylist contenente le teste le code e l'operatore
																	// generale
		headAndTail.addAll(RuleRecognizer.recognitionHeadOpTail(rules));
		//System.out.println(
		//		"lista delle teste code e op:\n" + headAndTail.toString() + "\n--------------------------------");

		ArrayList<String> head = new ArrayList<String>(); // Arraylist contenente le teste le code e l'operatore
															// generale
		head.addAll(HeadRecognizer.recognitionPremise(headAndTail.get(0)));
		//System.out.println("lista delle teste:\n" + head.toString() + "\n--------------------------------");

		ArrayList<ArrayList<String>> tails = new ArrayList<ArrayList<String>>(); // Arraylist contenete tutte le code
		tails.addAll(PremiseRecognizer.recognitionPremise2(PremiseRecognizer.recognitionPremise(headAndTail.get(3))));
		//System.out.println("lista delle code:\n" + tails.toString() + "\n--------------------------------");

		ArrayList<String> predicateListTails = new ArrayList<String>();
		predicateListTails.addAll(PredicateRecognizer.predicateRecognizerTails(tails));
		//System.out.println("lista dei predicati delle code:\n" + predicateListTails.toString()
		//		+ "\n--------------------------------");
		/*
		String specialRule = "";
		if(PredicateRecognizer.isSpecialTails(tails)) {
			specialRule = Integer.toString(idRules);
			System.out.println(idRules + " e' speciale");
		}*/
		

		ArrayList<String> predicateListHead = new ArrayList<String>();
		predicateListHead.addAll(PredicateRecognizer.predicateRecognizerHead(head));
		//System.out.println("lista dei predicati delle teste:\n" + predicateListHead.toString()
		//		+ "\n--------------------------------");

		ArrayList<String> constantsListTails = new ArrayList<String>();
		constantsListTails.addAll(ConstantsRecognizer.constantsRecognizerTails(tails));
		//System.out.println("lista delle costanti nella coda:\n" + constantsListTails.toString()
		//		+ "\n--------------------------------");

		ArrayList<String> constantsListHead = new ArrayList<String>();
		constantsListHead.addAll(ConstantsRecognizer.constantsRecognizerHead(head));
		//System.out.println("lista delle costanti nella testa:\n" + constantsListHead.toString()
		//		+ "\n--------------------------------");

		ArrayList<String> variablesListTails = new ArrayList<String>();
		variablesListTails.addAll(VariableRecognizer.variablesRecognizerTails(tails));
		//System.out.println("lista delle variabili nella coda:\n" + variablesListTails.toString()
		//		+ "\n--------------------------------");
		
		ArrayList<String> variablesListHead = new ArrayList<String>();
		variablesListHead.addAll(VariableRecognizer.variablesRecognizerHead(head));
		//System.out.println("lista delle variabili nella testa:\n" + variablesListHead.toString()
		//		+ "\n--------------------------------");

		if(!PredicateRecognizer.isSpecialTails(tails)) {
			Constants.addConstants(constantsListHead);
			Constants.addConstants(constantsListTails);

			Variables.initializeVariables();
			Variables.addVariables(variablesListTails);
			Variables.addVariables(variablesListHead);

			Predicate.addPredicates(predicateListHead);
			Predicate.addPredicates(predicateListTails);
			
			Rules.addRule(rules);
		}else {
			SpecialRules.addRule(rules);
		}
	}

	public static void structuredRuleComment(String comment) {
		ArrayList<String> ruleComment = new ArrayList<String>();
		ruleComment.addAll(CommentRuleRecognizer.commentRecognizerRule(comment));
		//System.out.println("id del commento alla regola: " + ruleComment.get(0) + "\ntesto commento regola: "
		//		+ ruleComment.get(1) + "\n--------------------------------");
		Comments.addCommentRules(ruleComment);
	}

	public static void structuredFact(String fact) {
		int idFact = FactRecognizer.getFactId(fact);
		//System.out.println("id del fatto:\n" + idFact + "\n------------------------");

		float priorityFact = FactRecognizer.getFactCertezza(fact);
		//System.out.println("priorit� della regola:\n" + priorityFact + "\n------------------------");

		ArrayList<String> predicateFact = new ArrayList<String>();
		predicateFact.addAll(PredicateRecognizerFact.predicateRecognizer(fact));
		//System.out.println("Predicato del fatto:\n" + predicateFact.toString() + "\n--------------------------------");

		ArrayList<String> factList = new ArrayList<String>();
		factList.addAll(PredicateRecognizerFact.factRecognizer(fact));
		//System.out.println("Testo del fatto:\n" + factList.toString() + "\n--------------------------------");

		ArrayList<String> constantsFactList = new ArrayList<String>();
		constantsFactList.addAll(ConstantsRecognizerFact.constantsRecognizerFact(factList));
		//System.out
		//		.println("Costanti del fatto:\n" + constantsFactList.toString() + "\n--------------------------------");

		Constants.addConstants(constantsFactList);

		Predicate.addPredicates(predicateFact);

		Facts.addFact(fact);

	}

	public static void structuredFactComment(String comment) {
		ArrayList<String> factComment = new ArrayList<String>();
		factComment.addAll(CommentFactRecognizer.commentRecognizerFact(comment));
		//System.out.println("id del commento al fatto: " + factComment.get(0) + "\ntesto commento fatto: "
		//		+ factComment.get(1) + "\n--------------------------------");
		Comments.addCommentFacts(factComment);
	}
}
