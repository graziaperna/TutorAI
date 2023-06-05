package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class RuleHistoryDialog extends JDialog {

	private JPanel contentPane = new JPanel();
	private DefaultListModel<String> myList = new DefaultListModel();
	private JTabbedPane tabbedPane;
	private List<String> ruleHistory = null;
	
	public RuleHistoryDialog(List<String> ruleHistory) {
		this.ruleHistory = ruleHistory;
		
		JPanel KBHistory = new JPanel();
	    JList<String> list = new JList<>(myList);
		
		setTitle("ReLay");
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	    
	    list.setBounds(100,100,500,500);
	    
	    myList.addAll(ruleHistory);
	    
	    KBHistory.add(list);
	    tabbedPane.add("Rule History", new JScrollPane(KBHistory));
		
	}
	
}
