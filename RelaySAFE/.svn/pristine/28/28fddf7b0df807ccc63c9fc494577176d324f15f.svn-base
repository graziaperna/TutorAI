package kbManagement.regex;

import java.util.ArrayList;

import kbManagement.structuredInput.Structurizer;

public class RegexLinesFile {
	
	static ArrayList<String> linesFacts = new ArrayList<String>();
	static ArrayList<String> linesRules = new ArrayList<String>();
	static ArrayList<String> linesCommentsFacts = new ArrayList<String>();
	static ArrayList<String> linesCommentsRules = new ArrayList<String>();
	
	public static void linesDispatcher(ArrayList<String> linesFile) {
		addFact(linesFile);
		addRule(linesFile);
		addCommentFact(linesFile);
		addCommentRule(linesFile);
	}
	
	public static void addFact(ArrayList<String> linesFile) {
		for(int i=0;i<linesFile.size();i++) {
			if(RegexFact.fact(linesFile.get(i))) {
				linesFacts.add(linesFile.get(i));
				Structurizer.structuredFact(linesFile.get(i));
			}			
		}
		//System.out.println("fatti: " + linesFacts.toString());
	}
	
	public static void addRule(ArrayList<String> linesFile) {
		for(int i=0;i<linesFile.size();i++) {
			if(RegexRule.rule(linesFile.get(i))) {
				linesRules.add(linesFile.get(i));
				Structurizer.structuredRule(linesFile.get(i));
			}			
		}
		//System.out.println("regole: " + linesRules.toString());
	}

	public static void addCommentFact(ArrayList<String> linesFile) {
		for(int i=0;i<linesFile.size();i++) {
			if(RegexFactComment.isCommentFact(linesFile.get(i))) {
				linesCommentsFacts.add(linesFile.get(i));
				Structurizer.structuredFactComment(linesFile.get(i));
			}			
		}
		//System.out.println("comm fatti: " + linesCommentsFacts.toString());
	}

	public static void addCommentRule(ArrayList<String> linesFile) {
		for(int i=0;i<linesFile.size();i++) {
			if(RegexRuleComment.isCommentRule(linesFile.get(i))) {
				linesCommentsRules.add(linesFile.get(i));
				Structurizer.structuredRuleComment(linesFile.get(i));
			}			
		}
		//System.out.println("comm rule: " + linesCommentsRules.toString());
	}
}
