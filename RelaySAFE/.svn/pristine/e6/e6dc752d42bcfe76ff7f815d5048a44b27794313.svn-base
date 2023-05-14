package api;

import java.util.ArrayList;
import java.util.HashMap;

public class Rete {
	private ArrayList<String> facts;
	
	public void rete(String factsPath, String knowledgePath) throws Exception {	
		RelayLayer client = ClientSingleton.getInstance().getClient();
		String projName = ClientSingleton.getInstance().getProjName();
		
		client.sendFactFileToProject(projName, factsPath);			
		client.sendKnowledgeFileToProject(projName, knowledgePath);
		
		HashMap<String, ArrayList<String>> result = client.startInferenceRete(projName);		
		facts = result.get("facts");
	}
	
	public ArrayList<String> getFacts() {
		return facts;
	}
}