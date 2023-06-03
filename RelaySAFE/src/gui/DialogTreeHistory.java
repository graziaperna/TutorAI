package gui;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

public class DialogTreeHistory extends GenericPanel{
	
	private List<String> listRuleHistory = null;
	
	public DialogTreeHistory(List<String> listRuleHistory) {
		super("Rule History");
		this.listRuleHistory = listRuleHistory;
		
		JPanel KBHistory = new JPanel();
		DefaultListModel<String> myList = new DefaultListModel();
	    JList<String> list = new JList<>(myList);
	    
	    add(KBHistory);
	    
	    list.setBounds(100,100,200,100);
	    
	    myList.addAll(listRuleHistory);
	    
	    add(list);
	}

}
