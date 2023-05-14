package api;

import java.util.ArrayList;
import java.util.HashMap;

public class Forward {
	private ArrayList<String> facts;
	private ArrayList<String> explanations;
	
	public void forward(String factsPath, String knowledgePath) throws Exception {	
		RelayLayer client = ClientSingleton.getInstance().getClient();
		String projName = ClientSingleton.getInstance().getProjName();
		
		client.sendFactFileToProject(projName, factsPath);			
		client.sendKnowledgeFileToProject(projName, knowledgePath);
		
		HashMap<String, ArrayList<String>> result = client.startInferenceForward(projName);		
		facts = result.get("facts");
		explanations = result.get("explanations");
	}
	
	public ArrayList<String> getFacts() {
		return facts;
	}
	
	public ArrayList<String> getExplanations() {
		return explanations;
	}
}
