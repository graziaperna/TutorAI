package api;

import java.util.HashMap;

public class Backward {	
	private int lastVersionRead = -1;
	
	public boolean backward(String factsPath, String knowledgePath, String obiettivo) throws Exception {	
		RelayLayer client = ClientSingleton.getInstance().getClient();
		String projName = ClientSingleton.getInstance().getProjName();

		client.sendFactFileToProject(projName, factsPath);		
		client.sendKnowledgeFileToProject(projName, knowledgePath);
		
		return client.startInferenceBackward(projName, obiettivo);	
	}

	public HashMap<String, Object> getSession() throws Exception {
		RelayLayer client = ClientSingleton.getInstance().getClient();
		String projName = ClientSingleton.getInstance().getProjName();
		
		System.out.println("Getting status...");
		
		HashMap<String, Object> status = client.getSession(projName, lastVersionRead);
		System.out.println(status);
		
		this.lastVersionRead = (int) status.get("version");
		
		return status;
	}

	public boolean sendAnswer(String answer) throws Exception {
		RelayLayer client = ClientSingleton.getInstance().getClient();
		String projName = ClientSingleton.getInstance().getProjName();
		
		System.out.println("Sending answer...: " + answer);
		
		return client.sendAnswer(projName, answer);
	}

	public boolean sendOtherDeductions(boolean otherDeductions) throws Exception {
		RelayLayer client = ClientSingleton.getInstance().getClient();
		String projName = ClientSingleton.getInstance().getProjName();
		
		System.out.println("Sending other deductions...: " + otherDeductions);
		
		return client.sendOtherDeductions(projName, otherDeductions);
	}
	
	public boolean sendFinish() throws Exception {
		RelayLayer client = ClientSingleton.getInstance().getClient();
		String projName = ClientSingleton.getInstance().getProjName();
		
		System.out.println("Sending finish...");
		
		return client.sendFinish(projName);
	}	
}
