package kbManagement.ruleRecognition;

import java.util.ArrayList;

public class PredicateRecognizer {

	public static ArrayList<String> predicateRecognizerTails(ArrayList<ArrayList<String>> input) {
		ArrayList<String> predicateList = new ArrayList<String>();

		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).get(0).equalsIgnoreCase("OR") || input.get(i).get(0).equalsIgnoreCase("AND")
					|| input.get(i).get(0).equalsIgnoreCase("NOT")) {
				for (int j = 1; j < input.get(i).size(); j++) {

					String[] partsPredicate = input.get(i).get(j).split("\\(");
					String[] partsArguments = partsPredicate[1].split("\\,");

					String predicateAriety = partsPredicate[0] + "/" + partsArguments.length;
					predicateList.add(predicateAriety);

				}
			} else {
				for (int j = 0; j < input.get(i).size(); j++) {
					String[] partsPredicate = input.get(i).get(j).split("\\(");
					if(!input.get(i).get(j).contains("\\,")){
						
					} else {
						String[] partsArguments = partsPredicate[1].split("\\,");

						String predicateAriety = partsPredicate[0] + "/" + partsArguments.length;
						predicateList.add(predicateAriety);
					}				

				}
			}

		}

		return predicateList;

	}
	
	
	/**
	 * Metodo che verifica se la premessa di una data regola e' speciale, cioe' contiene delle funzioni
	 * @param input
	 * @return
	 */
	public static boolean isSpecialTails(ArrayList<ArrayList<String>> input) {
	
		for (int i = 0; i < input.size(); i++) {
			for (int j = 0; j < input.get(i).size(); j++) {
				int contaAperte=0;
				int contaChiuse=0;
				for(int k=0; k<input.get(i).get(j).length();k++) {
					if(input.get(i).get(j).charAt(k)=='(') {
						contaAperte++;
					}else if(input.get(i).get(j).charAt(k)==')') {
						contaChiuse++;
					}
				}
				if(contaAperte!=contaChiuse) {
					return true;
				}	
			}
		}
		return false;
	}
	

	public static ArrayList<String> predicateRecognizerHead(ArrayList<String> input) {
		ArrayList<String> predicateList = new ArrayList<String>();

		for (int i = 0; i < input.size(); i++) {
			String[] partsPredicate = input.get(i).split("\\(");
			String[] partsArguments = partsPredicate[1].split("\\,");

			String predicateAriety = partsPredicate[0] + "/" + partsArguments.length;
			predicateList.add(predicateAriety);
		}

		return predicateList;

	}

	public static String predicateRecognizerTreeView(String input) {
		String predicate = null;

		for (int i = 0; i < input.length(); i++) {
			String[] partsPredicate = input.split("\\(");
			String[] partsArguments = partsPredicate[1].split("\\,");

			String predicateAriety = partsPredicate[0] + "/" + partsArguments.length;
			predicate = predicateAriety;
		}

		return predicate;

	}
}
