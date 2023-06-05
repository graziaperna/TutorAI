package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.KnowledgeLoaderFrame.KnowledgeLoaderPanel;
import service.RW;


public class GenericPanel extends JPanel {
	public String title = "";
	protected static JTextField txtInfo;
	//public static JTextArea txtrFattiTrue = new JTextArea();
	//public static JTextArea txtrFattiFalse = new JTextArea();
	
	protected static RW rw;
	protected static String workingDirectory = new File(".").getAbsolutePath();
	protected static String currentRunKnowledge = workingDirectory + "/knowledge";
	protected static String currentRunFacts = workingDirectory + "/new_fact";
	protected static String currentRunExamples = workingDirectory + "/examples";
	
	static {
		rw = new RW(currentRunKnowledge, currentRunFacts, currentRunExamples);
		rw.resetKnowledge(currentRunFacts);
		rw.resetKnowledge(currentRunKnowledge);
		rw.resetExamples();
	}
	
	public GenericPanel() {
	}

	public GenericPanel(String t) {
		title = t;
	}	

	public File SceltaFile() {			
		final JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			return file;
		} else {
			txtInfo.setText("Operazione Annullata - Nessun file caricato");
		}
		return null;
	}

	protected static int setMaxIdFact() throws IOException { //ste mai chiamato? COMUNQUE QUELLO CHE FA POTREBBE/DOVREBBE FARLO PROLOG
		InputStream nameFile = new FileInputStream(currentRunFacts); //(pathFactsFile);
		InputStreamReader stream = new InputStreamReader(nameFile, Charset.forName("UTF-8"));
		@SuppressWarnings("resource")
		BufferedReader countLines = new BufferedReader(stream);
		int temp[] = new int[10000];
		String fact = countLines.readLine();
		int lines = 0;
		while (fact != null) {
			String lastFact = "";
			int startId = 0;
			int finishId = 0;
			for(int i = 0; i < fact.length()-1; i++) {
				if(fact.substring(i, i+1).compareToIgnoreCase("(") == 0) {
					startId = i + 1;
				}
				if(fact.substring(i, i+1).compareToIgnoreCase(",") == 0) {
					finishId = i;
					break;
				}
			}
			lastFact = fact;
			if(lastFact.compareToIgnoreCase("") != 0) {
				temp[lines] = Integer.parseInt(lastFact.substring(startId,finishId));
				lines++;
			}
			fact = countLines.readLine();
		}
		int factId[] = new int[lines];
		for(int i = 0; i < factId.length; i++) {
			factId[i] = temp[i];
		}
		if(factId.length != 0) {
			int maxId = factId[0];
			for(int i = 1; i < factId.length; i++) {
				int currId = factId[i];
				if(currId > maxId) {
					maxId = currId;
				}
			}
			return maxId;
		} else
			return 0;
	}

	public String getTitle() {
		return title;
	}

	public void onStart() {
		// TODO Auto-generated method stub
	}
	
}
