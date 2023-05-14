package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class FileStreaming {
	
	private byte[] stream;
	private File file;
	private String fileName;
	private String extension;
	
	public FileStreaming(String path){
		try {
			this.file = new File(path);
			this.fileName = file.getName();
			this.extension = getFileExtension(file.getName());
			this.stream = loadFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
		
	public FileStreaming(String nameFile,byte[] stream){
		this.fileName = nameFile;
		try {
			this.stream = stream;
			this.extension = "";
			this.file = new File(fileName+extension);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getFileName(){
		if (this.fileName != null)
			return this.fileName;
		else 
			return null;			
	}
	
	public String getFileExtension(){
		return this.extension;
	}
	
	private byte[] loadFile() throws IOException {
		return FileUtils.readFileToByteArray(file);
	}
	
	public byte[] getStream() throws IOException{
		if (stream != null)
			return stream;
		else 
			throw new NullPointerException("Nessun stream caricato");
	}

	public String getStringStream(){
			return byteToString();
	}
	
	public void printStream(){
			System.out.println(byteToString());
	}
	
	public boolean saveFile(String path) throws IOException {
		if (!path.isEmpty()) {
			File filePath = new File(path);
			if (!filePath.exists())
				filePath.mkdirs();
			if (file != null){
				try {
					FileUtils.writeByteArrayToFile(new File(FilenameUtils.concat(path,fileName+extension)),stream);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;
			}
			else 
				return false;
		}
		else 
			return false;
	}
	
	private String byteToString() {
		if (stream != null) 
			return new String(stream, StandardCharsets.UTF_8);
		else {
			return "Stream non presente";
		}
	}
	
	private String getFileExtension(String nomeFile){
        String ex;
        if (nomeFile.contains("."))
        	ex = nomeFile.substring(nomeFile.lastIndexOf("."));
		else
			ex = ".txt";
		return ex;
    }
		
	
	public ArrayList<String> getStringListStream() throws IOException {
		ArrayList<String> array = new ArrayList<String>();
		if (stream != null) {
			FileUtils.writeByteArrayToFile(this.file,stream);
			BufferedReader bin = new BufferedReader(new FileReader(this.file.getAbsolutePath()));
			String entry;
	        boolean eof = false;
	        while(!eof) {
	          entry = bin.readLine();
	          if (entry==null)
	        	  eof = true;
	          else 
	        	  array.add(entry);
	        }
		}
		deleteFile();
		return array;
	}
	
	public boolean deleteFile() {
		boolean deleted = false;
		try {
			deleted = Files.deleteIfExists(this.file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return deleted;
	}
}
 
