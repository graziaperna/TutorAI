package core;

import java.util.ArrayList;
import java.util.HashMap;

import backward.BackwardSession;

/**
 * Questa interfaccia contiene i metodi principali per l'esecuzione dei tre tipi di inferenza.
 * 
 * @author Davide Lofrese, Luca Musti
 */
public interface IAPICore {
	/**
	 * Verifica l'esistenza di un progetto dato il nome.
	 *
	 * @param projectName Nome del progetto
	 * @return            true se il progetto esiste, false altrimenti
	 */	
	public boolean existsProject(String projectName) throws Exception;
	
	/**
	 * Crea un nuovo progetto dato il nome.
	 *
	 * @param projectName Nome del progetto
	 */	
	public void createProject(String projectName) throws Exception;
	
	/**
	 * Rimuove un progetto dato il nome.
	 *
	 * @param projectName Nome del progetto
	 */	
	public void removeProject(String projectName) throws Exception;
	
	/**
	 * Invia al progetto il file dei fatti da utilizzare per l'inferenza.
	 *
	 * @param projectName 		Nome del progetto
	 * @param factFilePath		Path del file dei fatti
	 */	
	public boolean sendFactFileToProject(String projectName, String factFilePath) throws Exception;
	
	/**
	 * Invia al progetto il file della conoscenza da utilizzare per l'inferenza.
	 *
	 * @param projectName 			Nome del progetto
	 * @param knowledgeFilePath		Path del file della conoscenza
	 */	
	public boolean sendKnowledgeFileToProject(String projectName, String knowledgeFilePath) throws Exception;
	
	/**
	 * Avvia l'esecuzione dell'inferenza Forward sul progetto.
	 *
	 * @param projectName 	Nome del progetto
	 * @return Dizionario con due coppie chiave-valore:
	 * <ul>
	 * 	<li><b>facts</b>: lista di stringhe contenenti i fatti dedotti
	 * 	<li><b>explanations</b>: lista di stringhe con la spiegazione dei fatti dedotti
	 * </ul>
	 */
	public HashMap<String, ArrayList<String>> startInferenceForward(String projectName) throws Exception;
	
	/**
	 * Avvia l'esecuzione dell'inferenza Rete sul progetto.
	 *
	 * @param projectName 	Nome del progetto
	 * @return Dizionario con una coppia chiave-valore:
	 * <ul>
	 * 	<li><b>facts</b>: lista di stringhe contenenti i fatti dedotti
	 * </ul>
	 */
	public HashMap<String, ArrayList<String>> startInferenceRete(String projectName) throws Exception;
	
	/**
	 * Avvia l'esecuzione dell'inferenza Backward sul progetto dato l'obiettivo.
	 *
	 * @param projectName 	Nome del progetto
	 * @param goal			Obiettivo da provare
	 * @return              true se l'inferenza è stata avviata correttamente
	 */
	public boolean startInferenceBackward(String projectName, String goal) throws Exception;
	
	/**
	 * Restituisce la sessione dato il nome del progetto.
	 *
	 * @param projectName 		Nome del progetto
	 * @param lastVersionRead	Ultima versione della sessione letta
	 * @return              	dizionario con la sessione
	 */
	public HashMap<String, Object> getSession(String projectName, Integer lastVersionRead) throws Exception;
	
	/**
	 * Invia una risposta ad una domanda.
	 *
	 * @param projectName	Nome del progetto
	 * @param answer		Risposta dell'utente 
	 * @return              true se la risposta è stata ricevuta correttamente
	 */
	public boolean sendAnswer(String projectName, String answer) throws Exception;
	
	/**
	 * Invia una risposta alla domanda relativa alle altre deduzioni.
	 *
	 * @param projectName		Nome del progetto
	 * @param otherDeductions	Risposta dell'utente 
	 * @return              	true se la risposta è stata ricevuta correttamente
	 */
	public boolean sendOtherDeductions(String projectName, Boolean otherDeductions) throws Exception;
	
	/**
	 * Invia la conferma della terminazione dell'inferenza Backward.
	 *
	 * @param projectName	Nome del progetto
	 * @return              true se la conferma è stata ricevuta correttamente
	 */
	public boolean sendFinish(String projectName) throws Exception;
}
