package backward;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Questa classe gestisce le sessioni dell'inferenza backward.
 * 
 * @author Davide Lofrese, Luca Musti
 */
public class BackwardSessionManager {	
	/**
	 * Dizionario che contiene le sessioni per ciascun progetto.
	 * <br><br> 
	 * Ogni	chiave corrisponde al percorso di un progetto di un utente, ed ogni valore
	 * corrisponde alla sessione dell'inferenza Backward associata al progetto.
	 */
	private static ConcurrentHashMap<String, BackwardSession> session = new ConcurrentHashMap<>();

	/**
	 * Restituisce la sessione dato il percorso del progetto.
	 *
	 * @param projectPath Percorso del progetto
	 * @return            oggetto con la sessione
	 */	
	public static BackwardSession getSession(String projectPath) {
		return session.get(projectPath);
	}
	
	/**
	 * Crea una nuova sessione (se non esiste) con il percorso del progetto come chiave.
	 *
	 * @param projectPath Percorso del progetto
	 */	
	public static void createSession(String projectPath) {
		if (!session.contains(projectPath)) {
			session.put(projectPath, new BackwardSession());
		}
	}
	
	/**
	 * Elimina una sessione (se esiste) con il percorso del progetto come chiave.
	 *
	 * @param projectPath Percorso del progetto
	 */
	public static void deleteSession(String projectPath) {
		if (session.contains(projectPath)) {
			session.remove(projectPath);
		}
	}
}
