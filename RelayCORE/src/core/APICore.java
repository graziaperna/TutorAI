package core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import backward.BackwardSession;
import util.FileStreaming;

/**
 * Questa classe implementa i metodi principali per l'esecuzione dei tre tipi di inferenza.
 * @see IAPICore
 * 
 * @author Davide Lofrese, Luca Musti
 */
public class APICore implements IAPICore {
	private RelayImpl relayImpl;
	private String workspacePath;
	
	public APICore(RelayImpl relayImpl, String workspacePath) {
		this.relayImpl = relayImpl;
		this.workspacePath = workspacePath;
	}

	@Override
	public boolean existsProject(String projectName) throws Exception {
		System.out.println("existsProject");
		
		if (!Pattern.compile("^\\w+$").matcher(projectName).find()) {
			throw new Exception("Invalid project name: " + projectName);
		}
		
		File projectPath = new File(FilenameUtils.concat(workspacePath, projectName));		
		return projectPath.exists() && projectPath.isDirectory();
	}

	@Override
	public void createProject(String projectName) throws Exception {
		System.out.println("createProject");

		if (existsProject(projectName)) {
			throw new Exception("Project " + projectName + " already exists");
		}
		
		File projectPath = new File(FilenameUtils.concat(workspacePath, projectName));
		FileUtils.forceMkdir(projectPath.getCanonicalFile());
	}

	@Override
	public void removeProject(String projectName) throws Exception {
		System.out.println("removeProject");

		if (!existsProject(projectName)) {
			throw new Exception("Project " + projectName + " does not exist");
		}
		
		File projectPath = new File(FilenameUtils.concat(workspacePath, projectName));
		FileUtils.deleteDirectory(projectPath);
	}

	@Override
	public boolean sendFactFileToProject(String projectName, String factFilePath) throws Exception {
		System.out.println("sendFactFileToProject");
		
		byte[] factFileStream = new FileStreaming(factFilePath).getStream();
		return sendFileToProject(projectName, "facts", factFileStream);
	}

	@Override
	public boolean sendKnowledgeFileToProject(String projectName, String knowledgeFilePath) throws Exception {
		System.out.println("sendKnowledgeFileToProject");
		
		byte[] knowledgeFileStream = new FileStreaming(knowledgeFilePath).getStream();
		return sendFileToProject(projectName, "knowledge", knowledgeFileStream);
	}

	@Override
	public HashMap<String, ArrayList<String>> startInferenceForward(String projectName) throws Exception {
		System.out.println("startInferenceForward");
		String projectPath = FilenameUtils.concat(workspacePath, projectName);		
		return relayImpl.startInferenceForward(projectPath + "/facts", projectPath + "/knowledge");
	}

	@Override
	public HashMap<String, ArrayList<String>> startInferenceRete(String projectName) throws Exception {
		System.out.println("startInferenceRete");
		String projectPath = FilenameUtils.concat(workspacePath, projectName);
		return relayImpl.startInferenceRete(projectPath + "/facts", projectPath + "/knowledge");
	}

	@Override
	public boolean startInferenceBackward(String projectName, String goal) throws Exception {
		System.out.println("startInferenceBackward");
		String projectPath = FilenameUtils.concat(workspacePath, projectName);
		return relayImpl.startInferenceBackward(projectPath, projectPath + "/facts", projectPath + "/knowledge", goal);
	}
	
	@Override
	public HashMap<String, Object> getSession(String projectName, Integer lastVersionRead) throws Exception {
		System.out.println("getSession");
		String projectPath = FilenameUtils.concat(workspacePath, projectName);
		return relayImpl.getSession(projectPath, lastVersionRead);
	}
	
	@Override
	public boolean sendAnswer(String projectName, String answer) throws Exception {
		System.out.println("sendAnswer");
		String projectPath = FilenameUtils.concat(workspacePath, projectName);
		return relayImpl.sendAnswer(projectPath, answer);
	}
	
	@Override
	public boolean sendOtherDeductions(String projectName, Boolean otherDeductions) throws Exception {
		System.out.println("sendOtherDeductions");
		String projectPath = FilenameUtils.concat(workspacePath, projectName);
		return relayImpl.sendOtherDeductions(projectPath, otherDeductions);
	}
	
	@Override
	public boolean sendFinish(String projectName) throws Exception {
		System.out.println("sendFinish");
		String projectPath = FilenameUtils.concat(workspacePath, projectName);
		return relayImpl.sendFinish(projectPath);
	}
	
	private boolean sendFileToProject(String projectName, String outputFileName, byte[] inputFileStream) throws Exception {	
		FileStreaming file = new FileStreaming(outputFileName, inputFileStream);
		return file.saveFile(FilenameUtils.concat(workspacePath, projectName));
	}
	
	public static void main(String[] args) {}
}
