package api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class RelayLayer {
	private Object relay;
	
	public RelayLayer() throws Exception {
		boolean useService = true;
		String serviceURL = "";
		String importMode = "";
		
		try (InputStream input = new FileInputStream("config/configuration.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            System.out.println("useService: " + properties.getProperty("useService"));
            useService = properties.getProperty("useService").equals("true");
            
            System.out.println("serviceURL: " + properties.getProperty("serviceURL"));
            serviceURL = properties.getProperty("serviceURL");
            
            System.out.println("importMode: " + properties.getProperty("importMode"));
            importMode = properties.getProperty("importMode");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		if (useService) { // use XMLRPC client library and instantiate it
			Class c = Class.forName("client.ReasoningServiceClient");
            Constructor ct = c.getConstructor(new Class[] {URL.class, String.class, String.class});
            relay = ct.newInstance(new Object[] {new URL(serviceURL), "demoorganization", "demopassword"});
		} else { // use RelayCORE library and instantiate it
			String workspacePath = System.getProperty("user.dir") + "/projects";
			
			if (importMode.equals("jar")) {
				// Nel caso in cui RelayCORE viene importato come jar (e non come progetto).	
				Class c = Class.forName("core.APICoreFactory");
	            Constructor ct = c.getConstructor(new Class[] {});		
				Object apiCoreFactory = ct.newInstance();
				Method m = apiCoreFactory.getClass().getMethod("createAPICore", new Class[] {String.class});
				relay = m.invoke(apiCoreFactory, new Object[] {workspacePath});
			} else {
				// Nel caso in cui RelayCORE viene importato come progetto (e non come jar).
				Class c = Class.forName("core.APICoreFactory");
				URL location = c.getProtectionDomain().getCodeSource().getLocation();
		        String cwd = location.getFile().replace("bin/", "");	        
	            Constructor ct = c.getConstructor(new Class[] {String.class});		
				Object apiCoreFactory = ct.newInstance(new Object[] {cwd});
				Method m = apiCoreFactory.getClass().getMethod("createAPICore", new Class[] {String.class});
				relay = m.invoke(apiCoreFactory, new Object[] {workspacePath});				
			}
		}
	}
		
	public boolean existsProject(String projectName) throws Exception {
		return (boolean) invokeMethod("existsProject", new Object[] {projectName});
	}
	
	public void createProject(String projectName) throws Exception {
		invokeMethod("createProject", new Object[] {projectName});
	}
	
	public void removeProject(String projectName) throws Exception {
		invokeMethod("removeProject", new Object[] {projectName});
	}	

	public boolean sendFactFileToProject(String projectName, String factFilePath) throws Exception {
		return (boolean) invokeMethod("sendFactFileToProject", new Object[] {projectName, factFilePath});
	}

	public boolean sendKnowledgeFileToProject(String projectName, String knowledgeFilePath) throws Exception {
		return (boolean) invokeMethod("sendKnowledgeFileToProject", new Object[] {projectName, knowledgeFilePath});
	}

	public HashMap<String, ArrayList<String>> startInferenceForward(String projectName) throws Exception {
		return (HashMap<String, ArrayList<String>>) invokeMethod("startInferenceForward", new Object[] {projectName});
	}
	
	public HashMap<String, ArrayList<String>> startInferenceRete(String projectName) throws Exception {
		return (HashMap<String, ArrayList<String>>) invokeMethod("startInferenceRete", new Object[] {projectName});
	}

	public boolean startInferenceBackward(String projectName, String goal) throws Exception {
		return (boolean) invokeMethod("startInferenceBackward", new Object[] {projectName, goal});
	}
		
	public HashMap<String, Object> getSession(String projectName, int lastVersionRead) throws Exception {
		return (HashMap<String, Object>) invokeMethod("getSession", new Object[] {projectName, lastVersionRead});
	}
	
	public boolean sendAnswer(String projectName, String answer) throws Exception {
		return (boolean) invokeMethod("sendAnswer", new Object[] {projectName, answer});
	}
	
	public boolean sendOtherDeductions(String projectName, boolean otherDeductions) throws Exception {
		return (boolean) invokeMethod("sendOtherDeductions", new Object[] {projectName, otherDeductions});
	}
	
	public boolean sendFinish(String projectName) throws Exception {
		return (boolean) invokeMethod("sendFinish", new Object[] {projectName});
	}
	
	private Object invokeMethod(String methodName, Object[] params) throws Exception {
		ArrayList<Class> paramsTypes = new ArrayList<>();
		
		for (Object param : params) {
			paramsTypes.add(param.getClass());
		}
		
		Method m = relay.getClass().getMethod(methodName, paramsTypes.toArray(new Class[] {}));
		return m.invoke(relay, params);
	}
}
