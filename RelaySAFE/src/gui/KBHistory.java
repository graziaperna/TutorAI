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
import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class KBHistory extends GenericPanel{

public KBHistory() {
	super("KB History");
	JPanel KBHistory = new JPanel();
	add(KBHistory);
JButton btnUpload = new JButton("Carica KB");
    add(btnUpload);
    btnUpload.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent event) {
		JFileChooser fc = new JFileChooser();
	    JFrame frame = new JFrame();
	    DefaultListModel<String> myList = new DefaultListModel();
	    JList<String> list = new JList<>(myList);
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
		                  System.out.println(line);
		              }
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
    	
	}
}
