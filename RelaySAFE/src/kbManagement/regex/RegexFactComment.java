package kbManagement.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFactComment {

	public static boolean isCommentFact(String regex) {

		String re1 = "(c)"; // Any Single Character 1
		String re2 = "(o)"; // Any Single Character 2
		String re3 = "(m)"; // Any Single Character 3
		String re4 = "(m)"; // Any Single Character 4
		String re5 = "(e)"; // Any Single Character 5
		String re6 = "(n)"; // Any Single Character 6
		String re7 = "(t)"; // Any Single Character 7
		String re8 = "(f)"; // Any Single Character 8
		String re9 = "(\\(.*\\))"; // Round Braces 1
		String re10 = "(\\.)"; // Any Single Character 12

		Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6 + re7 + re8 + re9 + re10,
				Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(regex);
		if (m.find()) {
			//System.out.println("commento al fatto riconosciuto: " + regex);
			return true;
		} else
			//System.out.println("commento al fatto non riconosciuto");
		return false;
	}
}
