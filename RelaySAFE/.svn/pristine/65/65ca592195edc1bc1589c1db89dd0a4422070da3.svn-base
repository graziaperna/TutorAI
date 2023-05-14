package gui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Main {	
	public static void main(String[] args) throws Exception, InterruptedException, ParserConfigurationException, SAXException {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}		

		MainFrame mf = new MainFrame();		
	}
}
