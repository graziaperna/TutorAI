package backward;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe per l'interazione tra Prolog e Java.
 * <br>
 * Nota: i metodi di questa classe vengono richiamati direttamente dall'interprete Prolog.
 * 
 * @author Davide Lofrese, Luca Musti
 */
public class PrologInteraction {
	/**
	 * Riceve una domanda binaria a cui rispondere, aggiorna la sessione con la nuova domanda e attende una risposta dell'utente.
	 * <br>
	 * A seguito della risposta, questa viene scritta nel file dei fatti e inviata a Prolog.
	 * 
	 * @param projectPath Path della directory del progetto
	 * @param question Testo della domanda 
	 * @param predicate Predicato dell'obiettivo della domanda
	 * @param questionWhy Testo della spiegazione della domanda 
	 * @return Risposta data dall'utente o halt in caso di timeout dell'inferenza
	 */
	public static String prologQuestion(String projectPath, String question, String predicate, String questionWhy) {
		System.out.println("Question: " + question + "\nPredicate: " + predicate);
		
		BackwardSession backwardSession = BackwardSessionManager.getSession(projectPath);
		backwardSession.setStatus("QUESTION");
		backwardSession.setQuestion(question);
		backwardSession.setQuestionWhy(questionWhy);
		backwardSession.setBinaryQuestion(true);
		backwardSession.setQuestionAnswer(null);
		backwardSession.incrementVersion();
		
		while (backwardSession.getQuestionAnswer() == null) {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {		
				return "halt";
			}
		}
			
		System.out.println("Received answer: " + backwardSession.getQuestionAnswer());
				
		String prob = backwardSession.getQuestionAnswer().equals("y") ? "1.0" : "0.0";
		Object[] args = new Object[] { backwardSession.getCurrentFactID().toString(), predicate, prob };
		String fact = MessageFormat.format("fact({0}, {1}, {2}).", args);
		fact = escapeFact(fact);
		
		retractFact(projectPath + "/facts", predicate);
		saveFact(projectPath + "/facts", fact);
		backwardSession.incrementCurrentFactID();
		
		return backwardSession.getQuestionAnswer();		
	}

	/**
	 * Riceve una domanda a risposta multipla a cui rispondere, aggiorna la sessione con la nuova domanda e attende una risposta dell'utente.
	 * <br>
	 * A seguito della risposta, questa viene scritta nel file dei fatti e inviata a Prolog.
	 * 
	 * @param projectPath Path della directory del progetto
	 * @param question Testo della domanda 
	 * @param predicate Predicato dell'obiettivo della domanda
	 * @param answers Possibili risposte alla domanda
	 * @param patient Nome del paziente
	 * @param questionWhy Testo della spiegazione della domanda 
	 * @return Risposta data dall'utente o halt in caso di timeout dell'inferenza
	 */
	public static String prologQuestionMultiple(String projectPath, String question, String predicate, String answers, String patient, String questionWhy) {
		System.out.println("Question: " + question + "\nPredicate: " + predicate);
		
		String[] menu = answers.replaceAll("[\\[\\](){}]","").split(",");
		
		BackwardSession backwardSession = BackwardSessionManager.getSession(projectPath);
		backwardSession.setStatus("QUESTION");
		backwardSession.setQuestion(question);
		backwardSession.setQuestionWhy(questionWhy);
		backwardSession.setBinaryQuestion(false);
		backwardSession.setAvailableAnswers(menu);
		backwardSession.setQuestionAnswer(null);
		backwardSession.incrementVersion();
		
		while (backwardSession.getQuestionAnswer() == null) {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				return "halt";
			}
		}		
	
		System.out.println("Received answer: " + backwardSession.getQuestionAnswer());
		
		if (!predicate.contains(backwardSession.getQuestionAnswer())) {
			Object[] args = new Object[] { backwardSession.getCurrentFactID().toString(), predicate, "0.0" };
			String fact = MessageFormat.format("fact({0}, {1}, {2}).", args);			
			fact = escapeFact(fact);
			
			retractFact(projectPath + "/facts", predicate);
			saveFact(projectPath + "/facts", fact);
			backwardSession.incrementCurrentFactID();
		}
		
		predicate = predicate.replaceFirst("\\(.+?\\)", "");
		String predicatoFinale = predicate + "(" + patient + "," + backwardSession.getQuestionAnswer() + ")";
		
		Object[] args = new Object[] { backwardSession.getCurrentFactID().toString(), predicatoFinale, "1.0" };
		String fact = MessageFormat.format("fact({0}, {1}, {2}).", args);		
		fact = escapeFact(fact);
		
		retractFact(projectPath + "/facts", predicatoFinale);
		saveFact(projectPath + "/facts", fact);
		backwardSession.incrementCurrentFactID();		
		
		return backwardSession.getQuestionAnswer();		
	}
	
	/**
	 * Riceve l'obiettivo provato e aggiorna la sessione con il nuovo obiettivo.
	 * 
	 * @param projectPath Path della directory del progetto
	 * @param result Obiettivo provato
	 * @param probability Probabilità dell'obiettivo provato
	 * @param trace Testo della spiegazione dell'obiettivo provato
	 * @return Stringa "y" come risposta affermativa a Prolog
	 */
	public static String appendResult(String projectPath, String result, String probability, String trace) {
		System.out.println("Result: " + result + " - Probability: " + probability);
		
		BackwardSession backwardSession = BackwardSessionManager.getSession(projectPath);
		backwardSession.setStatus("DED_GOAL");
		backwardSession.setDedGoal(result);
		backwardSession.setDedProb(Float.parseFloat(probability));
		backwardSession.setDedTrace(trace);
		backwardSession.setOtherDeductions(null);
		backwardSession.incrementVersion();
		
		Object[] args = new Object[] { backwardSession.getCurrentFactID().toString(), result, probability };
		String fact = MessageFormat.format("fact({0}, {1}, {2}).", args);
		saveFact(projectPath + "/facts", fact);
		backwardSession.incrementCurrentFactID();
		
		return "y";
	}

	/**
	 * Riceve la richiesta di altre deduzioni e attende una risposta dell'utente.
	 * 
	 * @param projectPath Path della directory del progetto
	 * @return Risposta data dall'utente o halt in caso di timeout dell'inferenza
	 */
	public static String otherDeductions(String projectPath) {
		BackwardSession backwardSession = BackwardSessionManager.getSession(projectPath);		
		
		while (backwardSession.getOtherDeductions() == null) {
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				return "halt";
			}
		}
			
		System.out.println("Received answer for other deductions: " + backwardSession.getOtherDeductions());
			
		return backwardSession.getOtherDeductions() ? "y" : "n";
	}
	
	private static String escapeFact(String fatto) {
		if (fatto.contains("(")) {
			// formato fatto: ha(nome_cognome,fatto)
			String regex = "(.+)\\(([a-zA-Z_]+),(.*)\\), (.+)\\)";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(fatto);
	        
	        if (matcher.find()) {	
	        	fatto = matcher.group(1) + "(" + matcher.group(2) + "," + prologEscapeString(matcher.group(3)) + "), " + matcher.group(4) + ")";
	        }
		} else {
			fatto = prologEscapeString(fatto);
		}
		
		return fatto;
	}
	
	private static String prologEscapeString(String stringa) {
		// sostituisci apici con doppi apici per fare l'esame
		stringa = stringa.replace("'", "''");
		
		// controlla se contiene spazi
		if(stringa.contains(" ")) {
			stringa = "'" + stringa +"'";
        }
		
		return stringa;
	}
	
	private static boolean saveFact(String factsPath, String fact) {
		try {
			File inFile = new File(factsPath);
			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return false;
			}
			
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(factsPath, true)));
			pw.println(fact);			
			pw.flush();
			pw.close();
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
			
	private static boolean retractFact(String factsPath, String predicate) {
		try {
			File inFile = new File(factsPath);
			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return false;
			}
			
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
			BufferedReader br = new BufferedReader(new FileReader(factsPath));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			
			String line = null;
			while ((line = br.readLine()) != null) {
				if (!line.contains(predicate)) {
					pw.println(line);
				} else {
					System.out.println("Retracted from facts: " + predicate);
				}
			}
			
			pw.flush();
			pw.close();
			br.close();			
			
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return false;
			}
			if (!tempFile.renameTo(inFile)) {
				System.out.println("Could not rename file");
				return false;
			}
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}	
}
