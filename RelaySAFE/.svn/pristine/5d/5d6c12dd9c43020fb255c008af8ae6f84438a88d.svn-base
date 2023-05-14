package api;

public class ClientSingleton {
	private RelayLayer client;
	private String projName;
	
	private static ClientSingleton instance;
	
	static {
		instance = new ClientSingleton();
	}	
	
	private ClientSingleton() {		
		try {
			client = new RelayLayer();

			projName = "proj" + 1;
			if(!client.existsProject(projName)) {
				client.createProject(projName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static ClientSingleton getInstance() {
		return instance;
	}	

	public RelayLayer getClient() {
		return client;
	}

	public String getProjName() {
		return projName;
	}	
}
