package kbManagement.regex;

import java.util.regex.*;

public class RegexFact {

	public static boolean fact(String textToRegex) {
		String re1 = "(f)"; // Any Single Character 1
		String re2 = "(a)"; // Any Single Character 2
		String re3 = "(c)"; // Any Single Character 3
		String re4 = "(t)"; // Any Single Character 4
		String re5 = "(\\(.*\\))"; // Round Braces 1
		String re6 = "(\\.)"; // Any Single Character 5

		Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(textToRegex);

		if (m.find()) {
			return true;
			//System.out.println("fatto riconosciuto: " + textToRegex);
		} else
			return false;
			//System.out.println("fatto non riconosciuto");
	}
}