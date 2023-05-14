package kbManagement.structuredInput;

import java.util.ArrayList;

public class Comments {

	public static ArrayList<ArrayList<String>> commentFacts = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<String>> commentRules = new ArrayList<ArrayList<String>>();

	public static void addCommentFacts(ArrayList<String> commentFactsList) {

		commentFacts.add(commentFactsList);
	}

	public static void addCommentRules(ArrayList<String> commentRulesList) {

		commentRules.add(commentRulesList);
	}
}
