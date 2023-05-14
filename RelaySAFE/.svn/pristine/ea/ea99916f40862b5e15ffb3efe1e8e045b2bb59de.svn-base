package kbManagement.ruleRecognition;

public class TailOpTrasform {

	public static String trasformOpTail(String tail) {
	
		String tailTrasformRounded = parenthesisRounded(tail);
		for (int i = 0; i < tailTrasformRounded.length(); i++) {

			if (tailTrasformRounded.charAt(i) == 'o' && tailTrasformRounded.charAt(i + 1) == 'r'
					&& tailTrasformRounded.charAt(i + 2) == '(') {
				for (int k = i + 2; k < tailTrasformRounded.length() - 1; k++) {
					if (tailTrasformRounded.charAt(k) == ')' && tailTrasformRounded.charAt(k + 1) == ',') {
						String str1 = tailTrasformRounded.substring(0, k + 1);
						String str2 = tailTrasformRounded.substring(k + 2, tailTrasformRounded.length());
						String concatenata = "";
						concatenata = str1 + "+" + str2;
						tailTrasformRounded = concatenata;
					}
					if (tailTrasformRounded.charAt(k) == ')' && tailTrasformRounded.charAt(k + 1) == ')') {
						k = tailTrasformRounded.length();
					}
				}
			}

			if (tailTrasformRounded.charAt(i) == 'n' && tailTrasformRounded.charAt(i + 1) == 'd'
					&& tailTrasformRounded.charAt(i + 2) == '(') {
				for (int k = i + 2; k < tailTrasformRounded.length() - 1; k++) {
					if (tailTrasformRounded.charAt(k) == ')' && tailTrasformRounded.charAt(k + 1) == ',') {
						String str1 = tailTrasformRounded.substring(0, k + 1);
						String str2 = tailTrasformRounded.substring(k + 2, tailTrasformRounded.length());
						String concatenata = "";
						concatenata = str1 + "-" + str2;
						tailTrasformRounded = concatenata;
					}
					if (tailTrasformRounded.charAt(k) == ')' && tailTrasformRounded.charAt(k + 1) == ')') {
						k = tailTrasformRounded.length();
					}
				}
			}
			
			if (tailTrasformRounded.charAt(i) == 'o' && tailTrasformRounded.charAt(i + 1) == 't'
					&& tailTrasformRounded.charAt(i + 2) == '(') {
				for (int k = i + 2; k < tailTrasformRounded.length() - 1; k++) {
					if (tailTrasformRounded.charAt(k) == ')' && tailTrasformRounded.charAt(k + 1) == ',') {
						String str1 = tailTrasformRounded.substring(0, k + 1);
						String str2 = tailTrasformRounded.substring(k + 2, tailTrasformRounded.length());
						String concatenata = "";
						concatenata = str1 + "-" + str2;
						tailTrasformRounded = concatenata;
					}
					if (tailTrasformRounded.charAt(k) == ')' && tailTrasformRounded.charAt(k + 1) == ')') {
						k = tailTrasformRounded.length();
					}
				}
			}
		}
		return tailTrasformRounded;
	}

	public static String parenthesisRounded(String stringa) {
		String stringaRounded = stringa.replace("[", "(").replace("]", ")");
		//System.out.println(stringaRounded);

		return stringaRounded;
	}

}
