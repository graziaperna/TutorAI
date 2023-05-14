package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.declarativa.interprolog.util.IPException;

import backward.BackwardSessionManager;
import backward.BackwardSession;
import enginePool.IObjectPool;
import reasoningEngine.ReasoningEngine;

/**
 * Questa classe contiene i metodi utilizzati per l'esecuzione dei tre tipi di inferenza.
 * 
 * @author Davide Lofrese, Luca Musti
 */
public class RelayImpl {
	/**
	 * Intero con il timeout dell'esecuzione dell'inferenza Backward espresso in millisecondi
	 * <br>
	 * Valore di default: 30 minuti
	 */
	private static final int BACKWARD_TIMEOUT = 1800 * 1000;
	
	/**
	 * Oggetto contenente il pool dei motori inferenziali.
	 */
	private IObjectPool<ReasoningEngine> pool;
	
	/**
	 * Stringa contenente il percorso della working directory dei motori inferenziali
	 */
	private String cwd = System.getProperty("user.dir");

	/**
	 * Istanzia un oggetto RelayImpl.
	 *
	 * @param pool Oggetto contenente il pool dei motori inferenziali.
	 */	
	public RelayImpl(IObjectPool<ReasoningEngine> pool) {
		this.pool = pool;
	}
	
	/**
	 * Istanzia un oggetto RelayImpl.
	 *
	 * @param pool Oggetto contenente il pool dei motori inferenziali.
	 * @param cwd Stringa contenente il percorso della working directory dei motori inferenziali.
	 */	
	public RelayImpl(IObjectPool<ReasoningEngine> pool, String cwd) {
		this.pool = pool;
		this.cwd = cwd;
	}
	
	/**
	 * Avvia l'esecuzione dell'inferenza Forward.
	 *
	 * @param factPath 	Path del file dei fatti
	 * @param kb			Path del file della conoscenza
	 * @return Dizionario con due coppie chiave-valore:
	 * <ul>
	 * 	<li><b>facts</b>: lista di stringhe contenenti i fatti dedotti
	 * 	<li><b>explanations</b>: lista di stringhe con la spiegazione dei fatti dedotti
	 * </ul>
	 */
	public HashMap<String, ArrayList<String>> startInferenceForward(String factPath, String kb) throws Exception {
		String[] kbs = { kb };
		return startInferenceForward(factPath, kbs);		
	}

	/**
	 * Avvia l'esecuzione dell'inferenza Forward.
	 *
	 * @param factPath 	Path del file dei fatti
	 * @param kb			Array di path dei file della conoscenza
	 * @return Dizionario con due coppie chiave-valore:
	 * <ul>
	 * 	<li><b>facts</b>: lista di stringhe contenenti i fatti dedotti
	 * 	<li><b>explanations</b>: lista di stringhe con la spiegazione dei fatti dedotti
	 * </ul>
	 */
	public HashMap<String, ArrayList<String>> startInferenceForward(String factPath, String[] kb) throws Exception {
		String kbs = buildKbsString(kb);
		
		ReasoningEngine engine = pool.requestObject();				
		HashMap<String, ArrayList<String>> results = new HashMap<>();
		
		try {			
			MIinterprolog mi = new MIinterprolog(engine.getEngine());
			mi.loadMi(cwd + "ES/IE/seva_start_online.pl");
			mi.runMIonServer_multikb(kbs, factPath);			
			ArrayList<String> facts = readNewFacts(factPath);
			
			ArrayList<String> explanations = new ArrayList<>();
			try {
				mi.startExplainer();
				explanations = mi.getexplaindedfacts();
			} catch (NullPointerException e) {
				explanations.add("Spiegazione non disponibile."); 
			}
			
			mi.refresh();
			
			results.put("facts", facts);
			results.put("explanations", explanations);
		} finally {
			pool.returnObject(engine);
		}
		
		return results;
	}
	
	/**
	 * Avvia l'esecuzione dell'inferenza Rete.
	 *
	 * @param factPath 	Path del file dei fatti
	 * @param kb			Path del file della conoscenza
	 * @return Dizionario con una coppia chiave-valore:
	 * <ul>
	 * 	<li><b>facts</b>: lista di stringhe contenenti i fatti dedotti
	 * </ul>
	 */
	public HashMap<String, ArrayList<String>> startInferenceRete(String factPath, String kb) throws Exception {
		String[] kbs = { kb };
		return startInferenceRete(factPath, kbs);
	}

	/**
	 * Avvia l'esecuzione dell'inferenza Rete.
	 *
	 * @param factPath 	Path del file dei fatti
	 * @param kb			Array di path dei file della conoscenza
	 * @return Dizionario con una coppia chiave-valore:
	 * <ul>
	 * 	<li><b>facts</b>: lista di stringhe contenenti i fatti dedotti
	 * </ul>
	 */
	public HashMap<String, ArrayList<String>> startInferenceRete(String factPath, String[] kb) throws Exception {		
		String kbs = buildKbsString(kb);
		
		ReasoningEngine engine = pool.requestObject();				
		HashMap<String, ArrayList<String>> results = new HashMap<>();
		
		try {			
			MIinterprolog mi = new MIinterprolog(engine.getEngine());

			mi.loadMi(cwd + "ES/IE/rete/retecompile.pl");
			mi.loadMi(cwd + "ES/IE/rete/retego.pl");
			
			mi.startReteWithPaths(factPath, kbs);
			ArrayList<String> facts = mi.getdedfacts();
			
			results.put("facts", facts);
		} finally {
			pool.returnObject(engine);
		}
		
		return results;
	}
	
	/**
	 * Avvia l'esecuzione dell'inferenza Backward.
	 * 
	 * @param projectPath  	Path del percorso del progetto
	 * @param factPath 		Path del file dei fatti
	 * @param kb			Path del file della conoscenza
	 * @param goal			Obiettivo da provare
	 * @return              true se l'inferenza è stata avviata correttamente
	 */
	public boolean startInferenceBackward(String projectPath, String factPath, String kb, String goal) throws Exception {
		String[] kbs = { kb };
		return startInferenceBackward(projectPath, factPath, kbs, goal);		
	}

	/**
	 * Avvia l'esecuzione dell'inferenza Backward in un thread con tempo di esecuzione massimo uguale a BACKWARD_TIMEOUT.
	 * 
	 * @param projectPath  	Path del percorso del progetto
	 * @param factPath 		Path del file dei fatti
	 * @param kb			Array di path dei file della conoscenza
	 * @param goal			Obiettivo da provare
	 * @return              true se l'inferenza è stata avviata correttamente
	 */
	public boolean startInferenceBackward(String projectPath, String factPath, String[] kb, String goal) throws Exception {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				ReasoningEngine engine = null;
				
				try {
					BackwardSessionManager.createSession(projectPath);
					
					engine = pool.requestObject();
					MIinterprolog mi = new MIinterprolog(engine.getEngine());
					
					// load backward module and knowledge base
					mi.loadMi(cwd + "ES/IE/seva_backward_server/seva_backward_online.pl");
					mi.loadMi(factPath);			
					for (String k : kb) {
						mi.loadMi(k);
					}
					
					mi.asserisci("current_project_path('" + projectPath + "')");
					
					try {
						mi.startBackward(goal);
						BackwardSession status = BackwardSessionManager.getSession(projectPath);		
						status.setStatus("FINISH");
						status.incrementVersion();
					} catch (IPException e) {						
						BackwardSession status = BackwardSessionManager.getSession(projectPath);		
						status.setStatus("TIMEOUT");
						status.incrementVersion();
					}					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						pool.returnObject(engine);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                if (t.isAlive()) {                	
                    t.interrupt();
                }
            }
        }, BACKWARD_TIMEOUT);
        
		t.setName("Backward-Thread");		
        t.start();
		System.out.println("Backward inference task started");
		
		// Attesa della creazione della sessione prima di terminare il metodo
		while (BackwardSessionManager.getSession(projectPath) == null) {
			TimeUnit.MILLISECONDS.sleep(500);
		}
		
		return true;
	}
	
	/**
	 * Restituisce la sessione aggiornata dato il percorso del progetto.
	 *
	 * @param projectPath 		Percorso del progetto
	 * @param lastVersionRead	Ultima versione della sessione letta
	 * @return              	oggetto con la sessione aggiornata
	 */
	public HashMap<String, Object> getSession(String projectPath, Integer lastVersionRead) throws Exception {
		BackwardSession status = BackwardSessionManager.getSession(projectPath);
		
		// Attesa di un aggiornamento della sessione
		while (status.getVersion() <= lastVersionRead) {
			TimeUnit.MILLISECONDS.sleep(500);
		}
				
		return status.toHashMap();
	}
	
	/**
	 * Riceve la risposta ad una domanda e aggiorna la sessione.
	 *
	 * @param projectPath 	Percorso del progetto
	 * @param answer		Risposta dell'utente 
	 * @return              true se la risposta è stata ricevuta correttamente
	 */
	public boolean sendAnswer(String projectPath, String answer) throws Exception {
		BackwardSession status = BackwardSessionManager.getSession(projectPath);		
		status.setQuestionAnswer(answer);	
		return true;
	}
	
	/**
	 * Riceve la risposta alla domanda relativa alle altre deduzioni e aggiorna la sessione.
	 *
	 * @param projectPath 		Percorso del progetto
	 * @param otherDeductions	Risposta dell'utente 
	 * @return              	true se la risposta è stata ricevuta correttamente
	 */
	public boolean sendOtherDeductions(String projectPath, Boolean otherDeductions) throws Exception {
		BackwardSession status = BackwardSessionManager.getSession(projectPath);		
		status.setOtherDeductions(otherDeductions);
		status.setDedGoal(null);
		status.setDedProb(0F);
		status.setDedTrace(null);
		return true;
	}
	
	/**
	 * Riceve la conferma della terminazione dell'inferenza Backward ed elimina la sessione relativa al progetto.
	 *
	 * @param projectPath 	Percorso del progetto
	 * @return              true se la conferma è stata ricevuta correttamente
	 */
	public boolean sendFinish(String projectPath) throws Exception {
		BackwardSessionManager.deleteSession(projectPath);
		return true;
	}
	
	/**
	 * Crea la stringa contenente la lista Prolog con i path dei file della conoscenza.
	 *
	 * @param kb Array di path dei file della conoscenza
	 * @return Stringa contenente la lista Prolog con i path dei file della conoscenza
	 */	
	private String buildKbsString(String[] kb) {
		//creo la stringa che rispetta il formalismo prolog per indicare la lista dei percorsi delle basi di conoscenza
		String kbs = "[";
		for (int i = 0; i < kb.length; i++) {
			String s = kb[i];
			kbs += "'" + s + "'";

			if (i < kb.length - 1)
				kbs = kbs + ",";
		}
		kbs += "]";
		
		return kbs;
	}

	/**
	 * Legge dal file dei fatti i nuovi fatti dedotti a seguito dell'inferenza Forward.
	 *
	 * @param factPath Path del file dei fatti
	 * @return Lista contenente i nuovi fatti dedotti
	 */	
	private static ArrayList<String> readNewFacts(String factPath) {
		ArrayList<String> listaFatti = new ArrayList<String>();
		String rigaFile;
		try {
			BufferedReader bufferFile = new BufferedReader(new FileReader(factPath));

			rigaFile = bufferFile.readLine();
			while (rigaFile != null) {
				if (rigaFile.contains("%")) {
					rigaFile = bufferFile.readLine();
					while (rigaFile != null) {
						listaFatti.add(rigaFile.replace(" ", ""));
						rigaFile = bufferFile.readLine();
					}
				} else {
					rigaFile = bufferFile.readLine();
				}
			}
			bufferFile.close();

			File f = new File(factPath);
			f.delete();
			f.createNewFile();

			return listaFatti;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
