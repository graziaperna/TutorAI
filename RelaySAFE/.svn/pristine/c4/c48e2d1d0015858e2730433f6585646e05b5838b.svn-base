package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.xmlrpc.XmlRpcException;

import api.Backward;
import api.Forward;
import api.Rete;
import service.RW;
import util.Fatto;
import util.MyDialogEsempi;

//import Intellicare.intellicare.IntellicareGUI;
//import jpl.JPL;
//import jpl.Query;

public class MainPanel2 extends GenericPanel {
	public static ArrayList<String> files = new ArrayList<String>(); //ste aggiungera' sempre se non si consente di rimuovere

	// private JTextArea txtrSpiegazione;

	private Forward f;
	private Rete r;
	private Backward b;
	
	//Tutti i JBUTTON
	private JButton btnLoad; // ex btnNewButton_1
	//private JButton btnCaricaFatti;  tasto Carica Fatti
	private JButton btnDeduci; // tasto Deduci
	public  JButton btnNewButton; // tasto spiega
	private JButton btnNewButton_2; // tasto Refresh
	private JButton btnReinizializza; // tasto Reinizializza
	private JButton btnNewButton_4; // tasto Cancella fact-rfact
	private JButton btnNewButton_5; // tasto Esegui tuning
	private JButton btnNewButton_6; // tasto Esempio non dedotto
	private JButton btnAsserisci;  // tasto asserisci
	private JButton btnNewButton_7;
	private JButton btnWoman;

	//Tutti i Jpanel
	private JPanel panelSpiegazione;//pannello spiegazioni 
	private JPanel panel_1;//pannello fatti da asserire
	private JPanel panel_3; //pannelo asserisci fatti
	private JPanel panelDeduzioni;//pannello fatti dell'inferenza
	private JPanel panel_5; //pannello regolazioni finali
	private JPanel panel_6; //pannello vuoto ci metto i fatti
	private JPanel panel_7; //pannello che contitene i due pulsanti
	private JPanel panel_8;//Contiene i pulsanti inferiori
	private JPanel panel_9;//Contiene textInfo

	//Tutti i jScrollPane
	private JScrollPane scrollPane_1;//dentro fatti da asserire
	private JScrollPane scrollPaneDeduzioni; //dentro fatti dell'inferenza
	private JScrollPane scrollPane_3;//dentro regolazioni finali
	public JScrollPane scrollPane_4;//dentro spiegazioni
	//public JScrollPane scrollPane_5; //dentro pannelo vuoto

	//Tutti i jTextrea
	private JTextArea txtrFattiDaAsserire; //in scrollPane 1
	private JTextArea textAreafinal_adjustment;// in scrollPane 3
	private static JTextArea txtRelay = new JTextArea();

	//Tutti i JQualcosa
	public static JList<String> listDeduzioni; //in scrollPane 3
	private JTree tree; //dentro spiegazioni
	private DefaultMutableTreeNode root;//root di tree
	private JLabel lblTime;//in panel_7 stampa la durata quando si preme deduci

	//Tutte Le Altre Variabili
	public static String body;//si inserisce in esegui tuning ma non viene mai istanziata
	//era gia commentata e istanziata quando si preme Carica
	public static ArrayList<String> persone; //viene usato in deduci settato in caricati

	private ArrayList<Integer> positivi;
	private ArrayList<Integer> negativi;
	private ArrayList<Integer> noninteressanti;

	//Aggiunte grafiche prese da l'altra interfaccia "EDES"

	//Classe MainFrame con il menu
	MainFrame mainframe;    

	//Classe Mi che usa per interfacciarsi col prolog da modificare con la nostra
	//MI mi; Sostituito con Minterprolog

	// In questa lista inseriremo gli output provenienti da prolog.
	static DefaultListModel<String> lista_prolog = new DefaultListModel<String>();


	public MainPanel2(final MainFrame mainframe, String title) {
		super(title);

		//SET THE OBJECT MAINF
		this.mainframe=mainframe;
		txtInfo = new JTextField();

		RW.filtro=RW.readfile("apprendimento/filtro");
		positivi=new ArrayList<Integer>();
		negativi=new ArrayList<Integer>();
		noninteressanti=new ArrayList<Integer>();
		// setResizable(false);

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 528, 342);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 399, 0, 16, 0, 0, 0, 0, 152, 0,
				250, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 34, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0,
				1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE, 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				1.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		panelSpiegazione = new JPanel();
		panelSpiegazione.setBorder(new TitledBorder(null, "Spiegazione", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridheight = 8;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		add(panelSpiegazione, gbc_panel_2);
		panelSpiegazione.setLayout(new BoxLayout(panelSpiegazione, BoxLayout.X_AXIS));

		scrollPane_4 = new JScrollPane();
		panelSpiegazione.add(scrollPane_4);

		inittree();
		scrollPane_4.setViewportView(tree);

		/*
		 * txtrSpiegazione = new JTextArea();
		 * txtrSpiegazione.setEditable(false);
		 * txtrSpiegazione.setText("Spiegazione...\r\n\r\n");
		 * scrollPane.setViewportView(txtrSpiegazione);
		 */
		//MI.idnewfact ++;

		panelDeduzioni = new JPanel();
		panelDeduzioni.setBorder(new TitledBorder(null, "Fatti ottenuti dall'inferenza", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridheight = 4;
		gbc_panel_4.gridwidth = 7;
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 1;
		gbc_panel_4.gridy = 0;
		add(panelDeduzioni, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{73, 0, 0};
		gbl_panel_4.rowHeights = new int[]{187, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDeduzioni.setLayout(gbl_panel_4);

		scrollPaneDeduzioni = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 2;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 0;
		panelDeduzioni.add(scrollPaneDeduzioni, gbc_scrollPane_2);

		listDeduzioni = new JList<String>();
		listDeduzioni.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList<?> list = (JList<?>)evt.getSource();
				if (evt.getClickCount() == 2) {
					final int indexcell = list.locationToIndex(evt.getPoint());
					if(indexcell>-1){
						MyDialogEsempi dlg = new MyDialogEsempi(null);
						final String insieme=dlg.getInsieme();
						Integer i=indexcell;
						String fatto=(String) list.getModel().getElementAt(i);
						Fatto f=new Fatto(fatto);
						// String fconv=f.convertFact(f.getPredicatointero());
						String fconv=fatto.substring(fatto.indexOf(",")+1,fatto.lastIndexOf(","));
						f=new Fatto(fconv);
						if(positivi.contains(i)) {
							positivi.remove(i);
							RW.retractExampleFromFile("headFact("+fconv+",pos).");
							//XML.deleteXMLnode(f.getPred(fconv));
						}
						if(negativi.contains(i)) {
							negativi.remove(i);
							RW.retractExampleFromFile("headFact("+fconv+",neg).");
							//XML.deleteXMLnode("not("+f.getPred(fconv)+")");
						}            
						if(noninteressanti.contains(i)) {
							noninteressanti.remove(i);
							//	XML.deleteXMLnode(f.getPred(fconv));
							//	XML.deleteXMLnode("not("+f.getPred(fconv)+")");
						}
						if(insieme.equals("pos")) {
							positivi.add(i);
							RW.writeExample("headFact("+fconv+",pos).");
							//XML.addXMLnodeEtichetta(f.getPred(fconv),f.dividiArgomenti(fconv),"");
						}
						if(insieme.equals("neg")) {
							negativi.add(i);
							RW.writeExample("headFact("+fconv+",neg).");
							//	XML.addXMLnodeEtichetta(f.getPred(fconv),f.dividiArgomenti(fconv),"not");
						}
						if(insieme.equals("null"))
							noninteressanti.add(i);
						list.setCellRenderer(new MyListCellThing());
					}
				} 
			}
		});
		scrollPaneDeduzioni.setViewportView(listDeduzioni);

		panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 0, 5);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 2;
		panelDeduzioni.add(panel_7, gbc_panel_7);

		btnNewButton_6 = new JButton("Esempio non dedotto");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApprendimentoFrame frame = new ApprendimentoFrame();
				frame.setVisible(true); 
			}
		});
		panel_7.add(btnNewButton_6);

		btnNewButton_5 = new JButton("Esegui tuning");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
//				To implement later.
//								
//				String head=MIinterprolog.translateToInthelexTune();
//				try {
//					ClientInthelex.tuning(false, head+":-"+body, "projSingle1", "complessivoRelay_intellicare_forteRelay_bathroomRelay_butlerRelay_kitchenRelay_living_roomRelay_ekmanRelay_gardenRelay");
//				} catch (XmlRpcException | IOException e) {
//					e.printStackTrace();
//				}
			}
		});

		panel_7.add(btnNewButton_5);
		lblTime = new JLabel("");
		panel_7.add(lblTime);

		panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Regolazioni finali",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.gridheight = 4;
		gbc_panel_5.gridwidth = 5;
		gbc_panel_5.insets = new Insets(0, 0, 5, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 8;
		gbc_panel_5.gridy = 0;
		add(panel_5, gbc_panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));

		scrollPane_3 = new JScrollPane();
		panel_5.add(scrollPane_3);

		textAreafinal_adjustment = new JTextArea();
		textAreafinal_adjustment.setText("Regolazioni finali...\n");
		textAreafinal_adjustment.setEditable(false);
		scrollPane_3.setViewportView(textAreafinal_adjustment);

		panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null,
				"Fatti caricati", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.gridheight = 4;
		gbc_panel_6.gridwidth = 7;
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 0;
		add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{73, 0, 0};
		gbl_panel_6.rowHeights = new int[]{187, 0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		//scrollPane_5= new JScrollPane();
		//GridBagConstraints gbc_scrollPane_5 = new GridBagConstraints();
		//gbc_scrollPane_5.gridwidth = 2;
		//gbc_scrollPane_5.insets = new Insets(0, 0, 5, 5);
		//gbc_scrollPane_5.fill = GridBagConstraints.BOTH;
		//gbc_scrollPane_5.gridx = 0;
		//gbc_scrollPane_5.gridy = 0;
		//panel_6.add(scrollPane_5,gbc_scrollPane_5);
		//scrollPane_5.setViewportView(txtrFattiTrue);
		//add(panel_6, gbc_panel_6);


		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Fatti da asserire",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 4;
		gbc_panel_1.gridwidth = 11;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 4;
		add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);

		txtrFattiDaAsserire = new JTextArea();
		txtrFattiDaAsserire.setText("Asserire fatti da id=" + (RW.idnewfact + 1) + "...");
		scrollPane_1.setViewportView(txtrFattiDaAsserire);

		panel_8 = new JPanel();
		panel_8.setBounds(0, 0, 212, 46);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 8;
		add(panel_8, gbc_panel);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		panel_9 = new JPanel();
		panel_9.setBounds(0, 0, 212, 46);
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.insets = new Insets(0, 0, 0, 5);
		gbc_panel_9.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 9;
		add(panel_9, gbc_panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		//Sezione Inferiore con i pulsanti

		btnLoad = new JButton("Carica Conoscenza");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KnowledgeLoaderFrame knowledgeFrame = new KnowledgeLoaderFrame();
				btnDeduci.setEnabled(true);
				//   PrologEngine pe = new YAPSubprocessEngine("/usr/bin/yap");
				//	MIinterprolog mIinterprolog = new MIinterprolog(pe);	
			}
		});
		panel_8.add(btnLoad);
		
		//Pulsante deduci
		btnDeduci = new JButton("Deduci");
		btnDeduci.setEnabled(false);
		panel_8.add(btnDeduci);
		btnDeduci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnNewButton.setEnabled(true);
					btnReinizializza.setEnabled(true);
					btnNewButton_2.setEnabled(true);
					files.add(currentRunFacts); //(pathFactsFile);
					switch (mainframe.metodo) {
					case "Forward": 
						clickForward();
						break;
					case "Backward": 
						lista_prolog.clear();
						if(!files.isEmpty()) {
							JFrame frame = new JFrame("Diagnosi");
							String name = JOptionPane.showInputDialog(frame, "Cosa vuoi sapere?", "Inserisci obiettivo");
							clickBackward(name);
						} else {
							txtRelay.append("FILE CONOSCENZA NON CARICATI\n");
						}
						break;
					case "Rete":    
						clickRete();
						break;
					default :	
						txtInfo.setText("Conoscenza caricata con successo");
						break;
					}
				} catch(Exception e1) {
					if(e1.getMessage().contains("fact_ded")) {
						DefaultListModel<String> listModel = new DefaultListModel<String>();
						listModel.addElement("Nessun fatto dedotto...");
						listDeduzioni.setModel(listModel);
					}
					if(e1.getMessage().contains("final_adj"))	
						textAreafinal_adjustment.setText("Non ci sono regolazioni finali...\n");		
				} finally {
					//lblTime.setText(mIinterprolog.getDurata());
				}
			}
		});
		//Fine sezione pulsante deduci

		btnNewButton = new JButton("Spiega");
		btnNewButton.setEnabled(false);
		panel_8.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> explanations = f.getExplanations();
				reInitTree();
				DefaultMutableTreeNode fatto = new DefaultMutableTreeNode();
				ArrayList<String> spiegazioni= new ArrayList<String>();
				String nomenodo=new String();
				for (int i = 0; i < explanations.size(); i++) {
					// txtrSpiegazione.append(list.get(i)); 
					//PICCOLA MODIFICA PER INTERPROLOG ARRAYLIST PER SPIEGAZIONI DUPLICATE
					if (!explanations.get(i).startsWith(" - ")) {
						fatto = new DefaultMutableTreeNode(explanations.get(i));
						root.add(fatto);
						nomenodo=explanations.get(i);
					} else {
						if(!spiegazioni.contains(nomenodo+"_"+explanations.get(i))){
							DefaultMutableTreeNode spiega = new DefaultMutableTreeNode(explanations.get(i));
							fatto.add(spiega);
							spiegazioni.add(nomenodo+"_"+explanations.get(i));}
					}
				}
				tree.expandPath(new TreePath(root.getPath()));
			}
		});

		btnReinizializza = new JButton("Reinizializza");
		btnReinizializza.setEnabled(false);
		panel_8.add(btnReinizializza);
		btnReinizializza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reInitTree();
				btnDeduci.setEnabled(false);
				btnNewButton.setEnabled(false);
				btnReinizializza.setEnabled(false);
				btnNewButton_2.setEnabled(false);
				textAreafinal_adjustment.setText("Regolazioni finali...");
				DefaultListModel listModel = (DefaultListModel) listDeduzioni.getModel();
				listModel.removeAllElements();
				rw.resetKnowledge(currentRunFacts); //ste
				rw.resetKnowledge(currentRunKnowledge);
				rw.resetExamples();
			}
		});
		
		btnNewButton_2 = new JButton("Refresh");
		btnNewButton_2.setEnabled(false);
		panel_8.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RW.retractFromFile("rfact", 1); 
			}
		});
		btnNewButton_4 = new JButton("Cancella fact-rfact");
		panel_8.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RW.retractFromFile("fact(", 1);
			}
		});

		txtInfo.setEditable(false);
		panel_9.add(txtInfo, "cell 0 5 3 1,growx,aligny top");
		txtInfo.setColumns(50);

		panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.gridwidth = 11;
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 2;
		gbc_panel_3.gridy = 8;
		add(panel_3, gbc_panel_3);

		btnAsserisci = new JButton("Asserisci");
		panel_3.add(btnAsserisci);
		btnAsserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = txtrFattiDaAsserire.getText();
				InputStream is = new ByteArrayInputStream(s.getBytes());
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String strLine;
				// Read File Line By Line
				try {
					int flag = 0;
					while ((strLine = br.readLine()) != null) {
						// Print the content on the console
						if (strLine.contains("rfact"))
							flag = 1;
						RW.writeInKnowledge("ReLay",strLine);
						RW.idnewfact++;
					}
					
					// if (flag == 1) 					
						// startloaderFuzzi calls loaderfuzzifier predicate which does not exist in any prolog file.
						// should it be fuzzyfier instead of loaderfuzzifier?? 
						// MIinterprolog.startloaderFuzzi("new_fact"); //modifica Morisco Petrera
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	//fine graifca Funzioni utili per il sistema
	

	private void inittree() {
		root = new DefaultMutableTreeNode("Spiegazione");
		tree = new JTree(root);
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
		Icon closedIcon = new ImageIcon("images/closed.png");
		Icon openIcon = new ImageIcon("images/open.png");
		Icon leafIcon = new ImageIcon("images/leaf.png");
		renderer.setClosedIcon(closedIcon);
		renderer.setOpenIcon(openIcon);
		renderer.setLeafIcon(leafIcon);
	}
	private void reInitTree() {
		root.removeAllChildren();
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		model.reload(root);
	}

	//colora le celle in label uno in caso si parli di fatti positivi negativi e non interesanti
	class MyListCellThing extends JLabel implements ListCellRenderer {

		public MyListCellThing() {
			setOpaque(true);
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			// Assumes the stuff in the list has a pretty toString
			setText(value.toString());

			// based on the index you set the color.  This produces the every other effect
			if(positivi.contains(new Integer(index)))
				setBackground(Color.green);
			if(negativi.contains(new Integer(index)))
				setBackground(Color.red);
			if(noninteressanti.contains(new Integer(index)))
				setBackground(Color.white);
			return this;
		}
	}

	public void visualizzaFeedbackRegole() {
		txtInfo.setText("Aggiunto modulo di conoscenza");
	}

	//Funzioni Prese dall'altra interfccia

	private void clickForward(){
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try{
			f = new Forward();
			f.forward(currentRunFacts, currentRunKnowledge);
			ArrayList<String> facts = f.getFacts();

			if(facts != null){
				listModel.addElement("****** Nuovi fatti dedotti ******\n");
				for(int i = 0; i < facts.size(); i++){
					listModel.addElement(facts.get(i)+"\n");
					RW.writeFact(facts.get(i));
				}
				listModel.addElement("****** Fine nuovi fatti dedotti ******\n");
				RW.writeFact("%End deduct facts from inference");
				txtInfo.setText("Fatti Dedotti con successo"); 				
			}
		} catch(NullPointerException e) {
			stampaTextArea("ERRORE: FATTI NON CARICATI\n");
		} catch (XmlRpcException e) {
			stampaTextArea("ERRORE DI CONNESSIONE AL SERVER\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			listModel.addElement("-----------------------------------------------------------\n");
			listDeduzioni.setModel(listModel);
		}
	}	

	private void clickBackward(String goal) {		
		MainPanel2.listDeduzioni.setModel(MainPanel2.lista_prolog);
		
		if(goal != null && goal.compareToIgnoreCase("") != 0) {			
			try {
				b = new Backward();
				b.backward(currentRunFacts, currentRunKnowledge, goal);

				boolean repeatBackward = true;				
				while (repeatBackward) {
					HashMap<String, Object> session = b.getSession();
					
					String status = (String) session.get("status");
					switch(status) {
						case "QUESTION":
							boolean binaryQuestion = (boolean) session.get("binaryQuestion");
							String answer = binaryQuestion ? BackwardInteraction.askQuestion(session) : BackwardInteraction.askQuestionMultiple(session);
							b.sendAnswer(answer);					
							
							break;						
						case "DED_GOAL":
							BackwardInteraction.appendDeductions(session);
							BackwardInteraction.askHowDeduction(session);
							
							boolean otherDeductions = BackwardInteraction.askAdditionalDeductions();
							b.sendOtherDeductions(otherDeductions);
							
							break;
						case "FINISH":
							if (lista_prolog.isEmpty()) {
								lista_prolog.addElement(goal + " è falso \n");
							}
							
							lista_prolog.addElement("-----------------------------------------------------------\n");
							lista_prolog.add(0, "Obiettivo: " + goal + "\n");													
							
							repeatBackward = false;
							break;
						case "TIMEOUT":							
							JOptionPane.showMessageDialog(new JFrame(), "Backward timeout reached, the inference has been stopped.", "Backward timeout", JOptionPane.WARNING_MESSAGE);
							
							repeatBackward = false;
							break;
					}
				}
				
				b.sendFinish();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void clickRete() {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try {
			r = new Rete();
			r.rete(currentRunFacts, currentRunKnowledge);			
			ArrayList<String> facts = r.getFacts();

			if(facts != null) {
				listModel.addElement("****** Nuovi fatti dedotti ******\n");
				for(int i = 0; i < facts.size(); i++) {
					listModel.addElement(facts.get(i)+"\n");
					RW.writeFact(facts.get(i));
				}
				listModel.addElement("****** Fine nuovi fatti dedotti ******\n");
				RW.writeFact("%End deduct facts from inference");
				txtInfo.setText("Fatti Dedotti con successo"); 				
			}
		} catch(NullPointerException e) {
			stampaTextArea("ERRORE: FATTI NON CARICATI\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			listModel.addElement("-----------------------------------------------------------\n");
			listDeduzioni.setModel(listModel);
		}
	}
	
	//ste PRESE DA GenericPanel MA INUTILIZZATE
	
	public static void visualizzaFatti(String path) {
		try {
			BufferedReader bufferReader = new BufferedReader(new FileReader(path));
			String strLine;
			DefaultListModel<String> listModel = new DefaultListModel<String>();
			listModel.addElement("");
			//txtrFattiFalse.setText("");
			while ((strLine = bufferReader.readLine()) != null) {
				//if (strLine.contains(", 1).") || strLine.contains(",1).")) {
				listModel.addElement(strLine);
				listModel.addElement("\n");	     
				//} else if (strLine.contains(", 0).") || strLine.contains(",0).")) {
				//txtrFattiFalse.append(strLine);
				//txtrFattiFalse.append("\n");
				//}
			}
			listDeduzioni.setModel(listModel);
			bufferReader.close();
			txtInfo.setText("Fatti caricati con successo"); 
		} catch(Exception e2) {
			txtInfo.setText("ERRORE - Caricamento file "+e2); 
		}
	}

	public static void stampaCome(String obiettivo) {
		int n = JOptionPane.showConfirmDialog(null, obiettivo + " è vero. Vuoi sapere come?" , "Domanda", JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION){
			stampaTextArea("Spiegazione " + obiettivo + ":\n");
			BufferedReader br = null;
			try {
				String sCurrentLine;
				br = new BufferedReader(new FileReader("traccia.txt"));
				while ((sCurrentLine = br.readLine()) != null) {
					stampaTextArea("\t" + sCurrentLine + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public static void stampaTextArea(String stringa) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addElement(stringa);
		listDeduzioni.setModel(listModel);
	}


}
