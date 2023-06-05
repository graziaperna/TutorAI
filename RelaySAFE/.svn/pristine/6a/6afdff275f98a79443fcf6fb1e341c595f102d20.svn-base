package gui;

import java.awt.Color;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import kbManagement.filesWR.ReadFile;
import util.Fact;

import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.BevelBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Component;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class KBHistory extends GenericPanel{

	List<String> tree = new ArrayList<>();
	String fileContent = "";
	
public KBHistory() {
	super("KB History");
	JPanel KBHistory = new JPanel();
	JButton btnUpload = new JButton("Carica KB");
	DefaultListModel<String> myList = new DefaultListModel();
    JList<String> list = new JList<>(myList);
	
	add(KBHistory);
    add(btnUpload);
    //add(btnShowTree);
    
    btnUpload.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			JFileChooser fc = new JFileChooser();
		    JFrame frame = new JFrame();
		    
		    list.setBounds(100,100,200,100);
		    
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			        try {
			              StringBuilder sb = new StringBuilder();
			              InputStream in = new FileInputStream(file);
			              BufferedReader br = new BufferedReader(new InputStreamReader(in));
			              String line;
	
			              while ((line = br.readLine()) != null) {
			            	  myList.addElement(line);
			                  sb.append(line + System.lineSeparator());
			                  //System.out.println(line);
			              }
			              
			              fileContent = sb.toString();
			              
			              btnUpload.setVisible(false);
			              add(list);
			          }catch (Exception e) {
			          e.printStackTrace();
			        }
			      } else {
			        System.out.println("Operation is CANCELLED :(");
				      }
			
			}
		});
    
    	list.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			    int[] selectedIx = list.getSelectedIndices();
			    List<String> ruleHistory = null;
			    String ruleName = "";
			    
			    ruleName = list.getModel().getElementAt(selectedIx[0]).split(",(and)?(or)?\\(\\[")[0].substring(list.getModel().getElementAt(selectedIx[0]).split(",(and)?(or)?\\(\\[")[0].indexOf(",")+1, 
			    		list.getModel().getElementAt(selectedIx[0]).split(",(and)?(or)?\\(\\[")[0].length());
			    
			    ruleHistory = makeTree(fileContent, ruleName);
			    
			    RuleHistoryDialog d = new RuleHistoryDialog(ruleHistory);
			    d.setVisible(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
    	
	}

	public List<String> makeTree(String content, String selectedRule) {
		LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
		LinkedHashMap<String, Integer> treeList = new LinkedHashMap<>();
		List<String> graphList = new ArrayList<>();
		List<String> resList = new ArrayList<>();
		
		String[] rules = content.split("rule");
		
		for(int i=0;i<rules.length-3;i++) {
			
			String s = content.split("rule")[i+3].split(",(and)?(or)?\\(\\[")[0].substring(content.split("rule")[i+3].split(",(and)?(or)?\\(\\[")[0].indexOf(",")+1, 
					content.split("rule")[i+3].split(",(and)?(or)?\\(\\[")[0].length());
			
			graphList.add("");
			map.put(i, s);
		}
		
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			for(int j=0;j<rules.length-3;j++) {
				if(rules[j+3].split(",(and)?(or)?\\(\\[")[1].indexOf(entry.getValue())!=-1) {						
					graphList.set(j, graphList.get(j) + String.valueOf(entry.getKey()) + ",");
				}
			}
		}
		
		for(int i=0;i<map.size();i++) {
			getTree(map, graphList, i, "");
		}
		
		for(int i=0;i<tree.size();i++) {
			treeList.put(tree.get(i), i);
		}
		
		treeList.forEach((key, value) -> {
				String[] split = key.split("->");
				if(split.length>2 && split[split.length-1].indexOf(selectedRule)!=-1 && key.indexOf(map.get(0).split("\\(")[0])!=-1) {
					resList.add(key);
				}
			});
		
		return resList;
	}
	
	public void getTree(LinkedHashMap<Integer, String> map, List<String> graphList, int i, String path) {
			
			List<Integer> row = new ArrayList<>();
			
			String[] s = null;
			
			if(graphList.get(i).compareTo("\0")!=0)
				s = graphList.get(i).split(",");
			
			for(int j=0;j<s.length;j++) {
				if(s[j].compareTo("")!=0)
					row.add(Integer.valueOf(s[j]));
			}
			
			if(row.size()==0) {
				path = path + "->" + map.get(i);
				tree.add(path);
				return;
			} else {
				
				path = path + "->" + map.get(i);
				
				for(int k=0;k<row.size();k++) {
						getTree(map, graphList, row.get(k), path);
					}
			}
			
		}
}
