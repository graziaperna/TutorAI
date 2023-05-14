package kbManagement.factRecognition;

import java.util.ArrayList;

public class CommentFactRecognizer {

	public static ArrayList<String> commentRecognizerFact(String input) {
		ArrayList<String> singleComment = new ArrayList<String>();

		String[] splitParenthesis = input.split("\\(");
		String[] idAndCommentSplit = splitParenthesis[1].split("\\,");
		String id = idAndCommentSplit[0];
		String commentText = idAndCommentSplit[1].substring(0, idAndCommentSplit[1].length() - 2);

		singleComment.add(id);
		singleComment.add(commentText);

		return singleComment;

	}
}
