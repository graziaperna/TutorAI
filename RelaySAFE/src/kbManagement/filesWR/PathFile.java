package kbManagement.filesWR;

public class PathFile {
	public String pathFileSelected = "";
	
	public String getPath() {
		return pathFileSelected;
	}
	
	public String setPath(String path) {
		//System.out.println("hai passatoo"+path);
		pathFileSelected = path;
		return pathFileSelected;
	}
}
