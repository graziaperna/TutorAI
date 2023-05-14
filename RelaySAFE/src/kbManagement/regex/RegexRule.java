package kbManagement.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexRule {

	public static boolean rule(String textToRegex) {
		String re1 = "(r)"; // Any Single Character 1
		String re2 = "(u)"; // Any Single Character 2
		String re3 = "(l)"; // Any Single Character 3
		String re4 = "(e)"; // Any Single Character 4
		String re5 = "(\\(.*\\))"; // Round Braces 1
		String re6 = "(\\.)"; // Any Single Character 5

		Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(textToRegex);

		if (m.find()) {
			//System.out.println("regola riconosciuta");
			return true;
		} else
			//System.out.println("regola non riconosciuta");
		return false;
	}
}
