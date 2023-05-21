package gui;

import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Utility {	
	//aggiungi qui la tua interfaccia
	public static LinkedList<GenericPanel> GetAllPanel(MainFrame mainFrame) throws Exception{
		LinkedList<GenericPanel> result = new LinkedList<GenericPanel>();
		result.add(new MainPanel2(mainFrame,"ReLay")); // Interfaccia principale prendere come esempio
		result.add(new PanelManagerKB("Knowledge"));
		result.add(new ClassPanelAnamnesi());
		result.add(new ClassPanelPaziente("Paziente"));
		result.add(new ClassPanelEAT26());
		result.add(new ClassPanelBES());
		result.add(new ClassPanelSwap());
		result.add(new ClassPanelTutor());
		return result;
	}
	
	public static String transformString(String string){
		int i = 0;
		String result = "";
		String[] parts = string.split(",");
		for (String part: parts){
			part = part.trim();
			if (i == 0){
				result = part + ",";
			} else if (part.indexOf(" ") != -1){
				if (part.indexOf(")") != -1){
					part = part.substring(0, part.length()-1);
					part = "\'" + part + "\')";
					result = result + part;
				} else {
					result = result + "\'" + part + "\',";
				}
			} else {
				result = result + part;
			}
			i++;
		}
		return result;
	}
		
	public static void main(String[] args){
		Utility ut = new Utility();
		System.out.println(ut.transformString("manifesta(a, bulimia nervosa in forma lieve, ciao gatto giao)"));
	}
}
