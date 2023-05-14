package gui;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Classe contenente i metodi per l'interazione con l'utente durante l'esecuzione dell'inferenza Backward.
 * 
 * @author Davide Lofrese, Luca Musti
 */
public class BackwardInteraction {
	/**
	 * Visualizza un popup con la domanda binaria ricevuta.
	 * 
	 * @param session Oggetto della sessione
	 * @return Risposta data dall'utente
	 */
	public static String askQuestion(HashMap<String, Object> session) throws Exception {
		String question = (String) session.get("question");
		String questionWhy = (String) session.get("questionWhy");
		
		System.out.println("Showing question: " + question);
		
		String risposta = "";
		
		try {
			JPanel panel_1 = new JPanel();
			
			Object[] opzioni = { "Si", "No", "Perchè?" };
			panel_1.add(new JLabel(question));
			
			do {			
				int dialogResult = JOptionPane.showOptionDialog(null,panel_1, "Domanda", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opzioni, null);
				
				System.out.println(dialogResult);
				switch (dialogResult) {
					//caso in cui rispondo 'si'
					case JOptionPane.YES_OPTION:
						System.out.println("Hai scelto si");
						risposta = "y";
						break;
					//caso in cui rispondo 'no'
					case JOptionPane.NO_OPTION:
						System.out.println("Hai scelto no");
						risposta = "n";
						break;
					//caso in cui chiedo il perchè della domanda
					case JOptionPane.CANCEL_OPTION:
						System.out.println("Hai chiesto il perchè.");
						risposta = "w";
						showQuestionWhy(questionWhy);
						break;
				}
			} while (risposta.equals("w"));			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return risposta;
	}

	/**
	 * Visualizza un popup con la domanda a risposta multipla ricevuta.
	 * 
	 * @param session Oggetto della sessione
	 * @return Risposta data dall'utente
	 */
	public static String askQuestionMultiple(HashMap<String, Object> session) throws Exception {
		String question = (String) session.get("question");
		Object[] availableAnswers = (Object[]) session.get("availableAnswers");
		String questionWhy = (String) session.get("questionWhy");
		
		System.out.println("Showing question: " + question);
		
		String risposta = "";
		
		try {
			JPanel panel_1 = new JPanel();
			
			JComboBox cb = new JComboBox(availableAnswers);
			
			Object[] opzioni = { "Conferma", "Perchè?" };
	
			panel_1.add(new JLabel(question));
			panel_1.add(cb);
			
			do {			
				int dialogResult = JOptionPane.showOptionDialog(null,panel_1, "Domanda",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opzioni, null);
				
				String sceltaBox;
				System.out.println(dialogResult);
				
				switch(dialogResult) {
					//caso in cui scelgo dalla combobox
					case JOptionPane.YES_OPTION:
						sceltaBox=cb.getSelectedItem().toString();
						risposta = sceltaBox;
						System.out.println("Hai scelto " + sceltaBox);
						break;
					//caso in cui chiedo il perchè della domanda
					case JOptionPane.NO_OPTION:					
						System.out.println("Hai chiesto il perchè.");
						risposta = "w";
						showQuestionWhy(questionWhy);
						break;
				}
			} while (risposta.equals("w"));			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return risposta;
	}

	/**
	 * Visualizza un popup con il testo della spiegazione della domanda.
	 * 
	 * @param questionWhy Testo con la spiegazione della domanda
	 */
	public static void showQuestionWhy(String questionWhy) {
		System.out.println("Question explanation: " + questionWhy);
		
		JPanel panel_why = new JPanel();
		panel_why.add(new JLabel(questionWhy));

		JTextArea txtArea_motivazione = new JTextArea(questionWhy);
		txtArea_motivazione.setEditable(false);
		
		JOptionPane.showConfirmDialog(panel_why, txtArea_motivazione, "Motivazione del quesito", JOptionPane.DEFAULT_OPTION);
	}
	
	/**
	 * Aggiunge l'obiettivo dedotto alla lista mostrata nell'interfaccia.
	 * 
	 * @param session Oggetto con la sessione 
	 */
	public static void appendDeductions(HashMap<String, Object> session) throws Exception {
		String dedGoal = (String) session.get("dedGoal");
		float dedProb = (float) session.get("dedProb");
		
		String deduction = dedGoal + " con certezza: " + dedProb;		
		MainPanel2.lista_prolog.addElement(deduction);
		
		System.out.println("Deduction added to list: " + deduction);		
	}
	
	/**
	 * Visualizza un popup chiedendo all'utente se mostrare la spiegazione della deduzione.
	 * 
	 * @param session Oggetto della sessione
	 */
	public static void askHowDeduction(HashMap<String, Object> session) throws Exception {
		String dedTrace = (String) session.get("dedTrace");
		
		String title = "Domanda";
		String text = "Vuoi sapere come sia stata ottenuta la deduzione?";
		
		if (JOptionPane.showConfirmDialog(null, text, title, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			System.out.println("Deduction trace: " + dedTrace);
			
			JPanel panel_come = new JPanel();
			panel_come.add(new JLabel(dedTrace));
			
			JTextArea txtArea_come = new JTextArea(dedTrace);
			txtArea_come.setWrapStyleWord(true);
			txtArea_come.setLineWrap(true);
			txtArea_come.setColumns(60);
			txtArea_come.setRows(10);
			txtArea_come.setEditable(false);

			JScrollPane panelScroll_come = new JScrollPane(txtArea_come) {
				@Override
			    public Dimension getPreferredSize() {
			        return new Dimension(400, 250);
			    }
			};
			
			JOptionPane.showConfirmDialog(panel_come, panelScroll_come, "Motivazione del quesito", JOptionPane.DEFAULT_OPTION);
		}
	}

	/**
	 * Visualizza un popup chiedendo all'utente se voglia ricevere altre deduzioni.
	 * 
	 * @return Risposta data dall'utente
	 */
	public static boolean askAdditionalDeductions() {
		String domanda = "Visualizzare ulteriori deduzioni?";
		
		JPanel panel_1 = new JPanel();		
		Object[] opzioni = { "Si", "No" };
		panel_1.add(new JLabel(domanda));
		
		int dialogResult = JOptionPane.showOptionDialog(null,panel_1, "Domanda", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opzioni, null);
		return dialogResult == JOptionPane.YES_OPTION;
	}
}