package kbManagement.structuredInput;

import java.util.ArrayList;

public class Facts {

	public static ArrayList<String> facts = new ArrayList<String>();

	public static void addFact(String fact) {
		facts.add(fact);
	}

	public static String generateId() {
		ArrayList<String> totalFacts = new ArrayList<String>();
		totalFacts.addAll(facts);
		ArrayList<Integer> totalFactsId = new ArrayList<Integer>();
		for(int i=0;i<totalFacts.size();i++) {
			String[] parts = totalFacts.get(i).split(",");
			String parts1 = parts[0].substring(5);
			totalFactsId.add(Integer.parseInt(parts1));
		}
		int idMax=0;
		for(int j=0;j<totalFactsId.size();j++) {
			if(totalFactsId.get(j)>idMax) {
				idMax=totalFactsId.get(j);
			}
		}
		int idNew = idMax +1;
		String id = String.valueOf(idNew);
		return id;
	}
}
