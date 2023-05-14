package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

//import ReLay.MainPanel.MyListCellThing;
import service.RW;

public class ApprendimentoFrame extends JFrame {

	private JPanel contentPane;
	ButtonGroup bg;
	ArrayList<JCheckBox> arrayCheckbox; 
	JTextArea textArea;
	JPanel panel_3;
	JRadioButton rdbtnPositivo;
	JRadioButton rdbtnNegativo;

	public ApprendimentoFrame() {
		setTitle("Apprendimento");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
	
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleziona fatti non dedotti", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 8;
		gbc_panel_1.gridwidth = 6;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		 panel_3 = new JPanel();
		scrollPane_1.setViewportView(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		bg=new ButtonGroup();
		
	//	Hashtable<String,String>  table=XML.ottieniargomentiXMLtable();
	
		ArrayList<String> argomenticonv=new ArrayList<String>();
		ArrayList<String> argomentisg=new ArrayList<String>();
		//Iterator<Entry<String, String>> it = table.entrySet().iterator();
		ArrayList<String> TPL = new ArrayList<>(); // MIinterprolog.getTPL();
		DefaultListModel listModel = new DefaultListModel();
			for(int i=0;i<TPL.size();i++){
			 listModel.addElement(TPL.get(i));
		  
		}
			
		         JPanel panel_4 = new JPanel();
		         panel_4.setBorder(new TitledBorder(null, "Inserimento assistito", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		         GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		         gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		         gbc_panel_4.gridwidth = 6;
		         gbc_panel_4.fill = GridBagConstraints.BOTH;
		         gbc_panel_4.gridx = 0;
		         gbc_panel_4.gridy = 8;
		         contentPane.add(panel_4, gbc_panel_4);
		         GridBagLayout gbl_panel_4 = new GridBagLayout();
		         gbl_panel_4.columnWidths = new int[]{55, 28, 258, 0, 0, 0, 0};
		         gbl_panel_4.rowHeights = new int[]{130, 0, 0};
		         gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		         gbl_panel_4.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		         panel_4.setLayout(gbl_panel_4);
		         
		         final JComboBox comboBox = new JComboBox();
		         GridBagConstraints gbc_comboBox = new GridBagConstraints();
		         gbc_comboBox.anchor = GridBagConstraints.WEST;
		         gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		         gbc_comboBox.gridx = 0;
		         gbc_comboBox.gridy = 0;
		         panel_4.add(comboBox, gbc_comboBox);
		         
		         JScrollPane scrollPane = new JScrollPane();
		         GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		         gbc_scrollPane.fill = GridBagConstraints.BOTH;
		         gbc_scrollPane.gridwidth = 2;
		         gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		         gbc_scrollPane.gridx = 2;
		         gbc_scrollPane.gridy = 0;
		         panel_4.add(scrollPane, gbc_scrollPane);
		         
		         JList<String> list_1 = new JList();
		         scrollPane.setViewportView(list_1);
		         list_1.setModel(listModel);
		         
		         JButton btnNewButton = new JButton("Completa");
		         btnNewButton.addActionListener(new ActionListener() {
		         	public void actionPerformed(ActionEvent arg0) {
		         		textArea.setText(textArea.getText().substring(0,textArea.getText().length()-1));
		         		textArea.append(")");
		         	}
		         });
		         GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		         gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		         gbc_btnNewButton.gridx = 5;
		         gbc_btnNewButton.gridy = 0;
		         panel_4.add(btnNewButton, gbc_btnNewButton);
		         list_1.addMouseListener(new MouseAdapter() {
					    public void mouseClicked(MouseEvent evt) {
					        JList<?> list = (JList<?>)evt.getSource();
					        if (evt.getClickCount() == 2) {
					            final int indexcell = list.locationToIndex(evt.getPoint());
					            if(indexcell>-1){
					            	
					            	textArea.setText(textArea.getText()+list.getModel().getElementAt(indexcell)+",");
					            }}}}
					);
		         textArea = new JTextArea();
		         
		      
		         
		         JPanel panel = new JPanel();
		         panel.setBorder(new TitledBorder(null, "Inserimento manuale", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		         GridBagConstraints gbc_panel = new GridBagConstraints();
		         gbc_panel.insets = new Insets(0, 0, 5, 0);
		         gbc_panel.gridwidth = 6;
		         gbc_panel.fill = GridBagConstraints.BOTH;
		         gbc_panel.gridx = 0;
		         gbc_panel.gridy = 9;
		         contentPane.add(panel, gbc_panel);
		         panel.setLayout(new BorderLayout(0, 0));
		         
		         panel.add(textArea);
		           
		           JPanel panel_2 = new JPanel();
		           panel_2.setBorder(new TitledBorder(null, "Opzioni", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		           GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		           gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		           gbc_panel_2.anchor = GridBagConstraints.SOUTH;
		           gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		           gbc_panel_2.gridwidth = 6;
		           gbc_panel_2.gridx = 0;
		           gbc_panel_2.gridy = 10;
		           contentPane.add(panel_2, gbc_panel_2);
		           GridBagLayout gbl_panel_2 = new GridBagLayout();
		           gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		           gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		           gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		           gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		           panel_2.setLayout(gbl_panel_2);
		           
		            rdbtnPositivo = new JRadioButton("P");
		            GridBagConstraints gbc_rdbtnPositivo = new GridBagConstraints();
		            gbc_rdbtnPositivo.insets = new Insets(0, 0, 5, 5);
		            gbc_rdbtnPositivo.gridx = 0;
		            gbc_rdbtnPositivo.gridy = 0;
		            panel_2.add(rdbtnPositivo, gbc_rdbtnPositivo);
		            
		            bg.add(rdbtnPositivo);
		            
		             rdbtnNegativo = new JRadioButton("N");
		             GridBagConstraints gbc_rdbtnNegativo = new GridBagConstraints();
		             gbc_rdbtnNegativo.insets = new Insets(0, 0, 5, 5);
		             gbc_rdbtnNegativo.gridx = 1;
		             gbc_rdbtnNegativo.gridy = 0;
		             panel_2.add(rdbtnNegativo, gbc_rdbtnNegativo);
		             bg.add(rdbtnNegativo);
		             JButton btnAggiungi = new JButton("Aggiungi");
		             btnAggiungi.addActionListener(new ActionListener() {
		             	public void actionPerformed(ActionEvent arg0) {
		             		String fatto=textArea.getText();
		             		ArrayList<String> listfatti=new ArrayList<String>();
		             		//if(!fatto.equals(""))
		             		//	listfatti.add(fatto);
		             		Component[] components=panel_3.getComponents();
		             		if(rdbtnNegativo.isSelected()) {
		             			RW.writeExample("headFact("+fatto+",neg).");
		             		} else if(rdbtnPositivo.isSelected()) {
		             			RW.writeExample("headFact("+fatto+",pos).");
		             		}
		             					
		             		/*for(int i=0;i<components.length;i++){
		             			JCheckBox c=(JCheckBox) components[i];
		             			/*if(c.isSelected()){
		             				listfatti.add(c.getText());
		             			}*/
		             		/*}
		             		for(int i=0;i<listfatti.size();i++){
		             			Fatto f=new Fatto(listfatti.get(i));
		             			if(rdbtnNegativo.isSelected())					
		             			//	XML.addXMLnodeEtichetta(f.getPred(listfatti.get(i)),f.dividiArgomenti(listfatti.get(i)),"not");
		             			else
		             				if(rdbtnPositivo.isSelected())
		             			//	XML.addXMLnodeEtichetta(f.getPred(listfatti.get(i)),f.dividiArgomenti(listfatti.get(i)),"");

		             		}*/
		             	}
		             });
		             GridBagConstraints gbc_btnAggiungi = new GridBagConstraints();
		             gbc_btnAggiungi.insets = new Insets(0, 0, 5, 0);
		             gbc_btnAggiungi.gridx = 19;
		             gbc_btnAggiungi.gridy = 0;
		             panel_2.add(btnAggiungi, gbc_btnAggiungi);
		ArrayList<String> files=new ArrayList<String>();
		ArrayList<String> predicati=new ArrayList<String>();
		for(int i=0;i<files.size();i++)	{
			for(int j=0;j<argomenticonv.size();j++){
				predicati =RW.readDed(argomenticonv.get(j), files.get(i),argomentisg);
				
				if(predicati.size()>0){
					panel_3.setLayout(new GridLayout(panel_3.getComponents().length+predicati.size(), 0, 0, 0));

					for(int k=0;k<predicati.size();k++){
						JCheckBox ck=new JCheckBox(predicati.get(k));
						panel_3.add(ck);
						panel_3.validate();
					}
				}			
			}
		}
		
		for(int i=0;i<RW.filtro.size();i++){
			if(!RW.filtro.get(i).contains("%")&& !RW.filtro.get(i).equals("")){
				comboBox.addItem(RW.filtro.get(i).substring(RW.filtro.get(i).indexOf("(")+1,RW.filtro.get(i).indexOf(",")));
			}
			
			comboBox.addItemListener(new ItemListener() {
			    public void itemStateChanged(ItemEvent  evt) {
			       // if (evt.getClickCount() == 1) {			        	
			            	textArea.setText(evt.getItem()+"(");
			            }}
			);
		}
		/*
		 JCheckBox ck=new JCheckBox(entry.getValue()+"---"+entry.getKey());
		 // bgargomenti.add(ck);
		  panel_3.add(ck);
		  panel_3.validate();*/
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

}
