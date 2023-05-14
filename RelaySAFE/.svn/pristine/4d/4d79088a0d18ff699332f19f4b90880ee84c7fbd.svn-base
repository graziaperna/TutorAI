/**
 * @author Nicola Stigliano and Pierpaolo Masella
 */
package gui;

import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import service.RW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
//@SuppressWarnings("unused")
public class ClassPanelSwap<E> extends GenericPanel {
	public int domanda = 1; //indice che tiene conto della domanda corrente
	//public String[] testSWAP = spostato alla fine per leggibilita'
	public String[] sceltaBox = {"","0","1","2","3","4", "5", "6", "7"}; // punteggi degli item (la teoria di swap parla di valutazioni da 0 a 7)
	private Map<Integer, String> risposteSWAP = new HashMap<>(); //ospita le risposte numeriche date dall'utente
	private Map<Integer, Integer> punteggiSWAP = new HashMap<>(); //ospita i punteggi calcolati in base alle risposte date
	public Integer punteggioSWAP; //ospitera' il punteggio finale del test swap
	public String punteggioStrSWAP = new String(); 
	HashSet<String> setFattiSWAP = new HashSet<String>(); // permette di inviare i fatti ottenuti mediante il test a prolog
	private JTextField textFieldCalcolaSWAP;
	private JScrollPane scrollPane_2;
	// Attraverso queste variabili controllo se i container rispettano i limiti massimi.
	public int valore1=0;
	public int valore2=0;
	public int valore3=0;
	public int valore4=0;
	public int valore5=0;
	public int valore6=0;
	public int valore7=0;
	public int valore8=0;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_4;
	public JTextField textField_5;
	public JTextField textField_6;
	public JTextField textField_7;
	public JTextField textField;
	public String valueofTextArea1;
	public String valueofTextArea2;
	public String valueofTextArea3;
	public String valueofTextArea4;
	public String valueofTextArea5;
	public String valueofTextArea6;
	public String valueofTextArea7;
	public String valueofTextArea8;
	// Creazione delle 8 textArea che ospiteranno i vari container.
	JTextArea textArea = new JTextArea();
	JTextArea textArea_1 = new JTextArea();
	JTextArea textArea_2 = new JTextArea();
	JTextArea textArea_3 = new JTextArea();
	JTextArea textArea_4 = new JTextArea();
	JTextArea textArea_5 = new JTextArea();
	JTextArea textArea_6 = new JTextArea();
	JTextArea textArea_7 = new JTextArea();
	// Creazione di 8 variabili che conterranno il numero massimo di elementi che possono essere inseriti in un container.
	public String max1= "100";
	public String max5= "14";
	public String max4= "16";
	public String max8= "8";
	public String max6= "12";
	public String max7= "10";
	public String max2= "22";
	public String max3= "18";
	//Creazione di 8 hashmap che conterranno i valori inseriti nei container.
	public Map<Integer,String> cuno = new HashMap<>();
	public Map<Integer,String> cdue = new HashMap<>();
	public Map<Integer,String> ctre = new HashMap<>();
	public Map<Integer,String> cquattro = new HashMap<>();
	public Map<Integer,String> ccinque = new HashMap<>();
	public Map<Integer,String> csei = new HashMap<>();
	public Map<Integer,String> csette = new HashMap<>();
	public Map<Integer,String> cotto = new HashMap<>();
	
	@SuppressWarnings("rawtypes")
	 /**
	  * Classe che si occupa di gestire il funzionamento del test SWAP
	  * @param title
	  * @throws Exception
	  */
	public ClassPanelSwap() throws Exception {
		super("SWAP");

		setBounds(33, 35, 1105, 580);
		setLayout(null);
		JPanel panelBES = new JPanel();
		panelBES.setBounds(0, 0, 1105, 580);
		panelBES.setBorder(new TitledBorder(null, "Test SWAP", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelBES);
		panelBES.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 1070, 116);
		panelBES.add(scrollPane);

		final JTextArea txtrBes01 = new JTextArea();
		txtrBes01.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtrBes01.setEditable(false);
		txtrBes01.setText(testSWAP[domanda-1]);
		scrollPane.setViewportView(txtrBes01);

		final JLabel labelSetDomande = new JLabel("Possibili risposte ---->");
		labelSetDomande.setBounds(318, 485, 124, 14);
		panelBES.add(labelSetDomande);

		final JLabel lblTestoDomanda = new JLabel("Testo dell'affermazione:");
		lblTestoDomanda.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTestoDomanda.setBounds(382, 22, 147, 14);
		panelBES.add(lblTestoDomanda);

		JPanel panelItemBES = new JPanel();
		panelItemBES.setBounds(1, 152, 160, 271);
		panelBES.add(panelItemBES);
		panelItemBES.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Item Mancanti", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelItemBES.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setFocusTraversalKeysEnabled(false);
		scrollPane_1.setBounds(5, 15, 150, 255);
		panelItemBES.add(scrollPane_1);

		JTextArea textAreaItemSWAP = new JTextArea();
		textAreaItemSWAP.setAutoscrolls(false);
		scrollPane_1.setViewportView(textAreaItemSWAP);
		textAreaItemSWAP.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textAreaItemSWAP.setEditable(false);
		setTextAreaItems(textAreaItemSWAP);

		textAreaItemSWAP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (textAreaItemSWAP.getSelectedText() != null) { 
					String s = textAreaItemSWAP.getSelectedText();
					System.out.println(s);
					try {
						txtrBes01.setText(testSWAP[Integer.parseInt(s)-1]);
						domanda=Integer.parseInt(s);
					} catch(Exception e) {
						System.out.println("Seleziona un numero.");
					}
				}
			}
		});

		JButton btnAvanti = new JButton("Avanti");
		btnAvanti.setBounds(564, 481, 89, 23);
		panelBES.add(btnAvanti);

		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(181, 481, 89, 23);
		panelBES.add(btnIndietro);  

		JLabel lblSelezionaUnaDelle = new JLabel("Seleziona il valore piu' congeniale da attribuire all'affermazione.");
		lblSelezionaUnaDelle.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSelezionaUnaDelle.setBounds(171, 444, 508, 21);
		panelBES.add(lblSelezionaUnaDelle);

		textFieldCalcolaSWAP = new JTextField();
		textFieldCalcolaSWAP.setEditable(false);
		textFieldCalcolaSWAP.setColumns(10);
		textFieldCalcolaSWAP.setBounds(27, 477, 102, 31);
		panelBES.add(textFieldCalcolaSWAP);

		final JLabel labelPunteggio = new JLabel("");
		labelPunteggio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		labelPunteggio.setBounds(234, 516, 669, 31);
		panelBES.add(labelPunteggio);

		@SuppressWarnings("unchecked")
		final JComboBox comboBoxRisposta = new JComboBox(sceltaBox);
		comboBoxRisposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recuperaRisposta(comboBoxRisposta, domanda);
			}
		});
		comboBoxRisposta.setBounds(465, 482, 89, 20);
		panelBES.add(comboBoxRisposta);

		btnAvanti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = domanda;
				i++;
				if (i>=0 && i<200){
					txtrBes01.setText(testSWAP[i-1]);
					domanda = i;
					labelSetDomande.setText( "Possibili risposte ---->");
					lblTestoDomanda.setText("Testo dell'affermazione: ");
					comboBoxRisposta.setSelectedIndex(0);
					textAreaItemSWAP.setText(textAreaItemSWAP.getText().replace(testSWAP[domanda-2],""));
				}
			}   
		}); 

		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = domanda;
				i--;
				if (i>=0 && i<200){
					txtrBes01.setText(testSWAP[i-1]);
					domanda = i;    
					labelSetDomande.setText("Possibili risposte ---->");
					lblTestoDomanda.setText("Testo dell'affermazione: ");
					comboBoxRisposta.setSelectedIndex(0);
					textAreaItemSWAP.setText(textAreaItemSWAP.getText().replace(testSWAP[domanda-2],""));
				}
			}
		});
		/**
		 * Una volta cliccato, permettera' l'asserzione dei fatti ricavati dallo SWAP.
		 */
		JButton btnCalcolaBES = new JButton("Calcola");
		btnCalcolaBES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//				setpunteggioSWAP ();
				//				trovaFattiBES();
				//				setTextAreaItems(textAreaItemSWAP);
				//				setLabelPunteggio(labelPunteggio);
				ArrayList<String> fatti= creaFatti();
				for(String fatto : fatti) {
					asserisciFattoSWAP(fatto);
				}
			}
		});
		btnCalcolaBES.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnCalcolaBES.setBounds(27, 434, 102, 31);
		panelBES.add(btnCalcolaBES);

		JPanel panel = new JPanel();
		panel.setBounds(171, 162, 909, 271);
		panelBES.add(panel);
		panel.setLayout(null);

		JTextPane txtpnContainer = new JTextPane();
		txtpnContainer.setEditable(false);
		txtpnContainer.setText("Container 0");
		txtpnContainer.setBounds(15, 0, 90, 20);
		panel.add(txtpnContainer);

		JTextPane txtpnContainer_1 = new JTextPane();
		txtpnContainer_1.setEditable(false);
		txtpnContainer_1.setText("Container 1");
		txtpnContainer_1.setBounds(134, 0, 90, 20);
		panel.add(txtpnContainer_1);

		JTextPane txtpnContainer_2 = new JTextPane();
		txtpnContainer_2.setEditable(false);
		txtpnContainer_2.setText("Container 2");
		txtpnContainer_2.setBounds(255, 0, 90, 20);
		panel.add(txtpnContainer_2);

		JTextPane txtpnContainer_3 = new JTextPane();
		txtpnContainer_3.setEditable(false);
		txtpnContainer_3.setText("Container 3");
		txtpnContainer_3.setBounds(369, 0, 90, 20);
		panel.add(txtpnContainer_3);

		JTextPane txtpnContainer_4 = new JTextPane();
		txtpnContainer_4.setEditable(false);
		txtpnContainer_4.setText("Container 4");
		txtpnContainer_4.setBounds(486, 0, 94, 20);
		panel.add(txtpnContainer_4);

		JTextPane txtpnContainer_5 = new JTextPane();
		txtpnContainer_5.setEditable(false);
		txtpnContainer_5.setText("Container 5");
		txtpnContainer_5.setBounds(605, 0, 90, 20);
		panel.add(txtpnContainer_5);

		JTextPane txtpnContainer_6 = new JTextPane();
		txtpnContainer_6.setEditable(false);
		txtpnContainer_6.setText("Container 6");
		txtpnContainer_6.setBounds(719, 0, 90, 20);
		panel.add(txtpnContainer_6);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(15, 21, 90, 195);
		panel.add(scrollPane_3);
		//Qui sono gestite tutte  le TextArea che rappresentano i container del test
		
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (textArea.getSelectedText() != null) { 
					String s = textArea.getSelectedText();
					if(s.contains("Item")) {
						String tempWord = "Item" ; 
						s = s.replaceAll(tempWord, "");
						System.out.println("sei all'item"+s);
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					} else {
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					}
				}
			}
		});

		scrollPane_3.setViewportView(textArea);
		textArea.setEditable(false);
		setVisible(true);

		comboBoxRisposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recuperaRisposta(comboBoxRisposta, domanda);
				String numero = (String) comboBoxRisposta.getSelectedItem();
				// Temp e valueofTextArea sono necessari per verificare se un item e' gia presente nel container
				String temp="Item"+domanda+ "\n";
				valueofTextArea1= textArea.getText().toString();
				if(numero=="0" ) {
				if(valore1 <100) {	
					// Attraverso il .contains verifico in contenuto della TextArea e verifico se al suo interno sia presente temp. Se si,non me lo fa reiserire altrimenti lo inserir� nel container.
					if(cuno.containsValue(temp)) {
						System.out.println("item gia' presente");

					} else {
						valore1++;
						cuno.put(valore1, temp);
						setTextAreaContainer(textArea);
						textArea.append(temp);
						modificaItem(temp,textArea);
						onStart(textField,valore1,max1);

					}
				} else {
					String errore= "Hai raggiunto il numero massimo di item inseribili per il Container 0";
					String container = "0";
					Errori.popuperrore(errore,container);
				}
				}
			}
		});

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(134, 21, 90, 195);
		panel.add(scrollPane_4);
		textArea_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (textArea_1.getSelectedText() != null) { 
					String s = textArea_1.getSelectedText();
					if(s.contains("Item")) {
						String tempWord = "Item" ; 
						s = s.replaceAll(tempWord, "");
						System.out.println(s);
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					} else {
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					}
				}
			}
		});

		scrollPane_4.setViewportView(textArea_1);
		textArea_1.setEditable(false);
		comboBoxRisposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTextAreaContainer(textArea_1);
				recuperaRisposta(comboBoxRisposta, domanda);
				String numero = (String) comboBoxRisposta.getSelectedItem();
				// Temp e valueofTextArea sono necessari per verificare la presenza ( o assenza) di un item in un container.
				String temp="Item"+domanda+ "\n";
				valueofTextArea2= textArea_1.getText().toString();
				if(numero=="1") {
					if(valore2 <22) {
						if(cdue.containsValue(temp)) {
							System.out.println("item gia' presente");

						} else {
							valore2++;
							cdue.put(valore2,temp);
							System.out.println(cdue);
							setTextAreaContainer( textArea_1);
							textArea_1.append(temp);
							modificaItem(temp,textArea_1);
							onStart(textField_1,valore2,max2);
						}
					} else { //Gestiamo il caso in cui l'utente cerca di inserire un nuovo item ma ha raggiunto il massimo
						String errore= "Hai raggiunto il numero massimo di item inseribili per il Container 1";
						String container = "1";
						Errori.popuperrore(errore,container);
					}
				}
			}
		});
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(255, 21, 90, 195);
		panel.add(scrollPane_5);
		textArea_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (textArea_2.getSelectedText() != null) { 
					String s = textArea_2.getSelectedText();
					if(s.contains("Item")) {
						String tempWord = "Item" ; 
						s = s.replaceAll(tempWord, "");
						System.out.println(s);
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					} else {
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					}
				}
			}
		});

		scrollPane_5.setViewportView(textArea_2);
		textArea_2.setEditable(false);
		comboBoxRisposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTextAreaContainer(textArea_2);
				recuperaRisposta(comboBoxRisposta, domanda);
				String numero = (String) comboBoxRisposta.getSelectedItem();
				String temp="Item"+domanda+ "\n";
				valueofTextArea3= textArea_2.getText().toString();
				if(numero=="2") {
					if( valore3 <18) {
						if(ctre.containsValue(temp)) {
							System.out.println("item gia' presente");
						} else {
							valore3++;
							ctre.put(valore3,temp);
							System.out.println(ctre);
							setTextAreaContainer( textArea_2);
							textArea_2.append(temp);
							modificaItem(temp,textArea_2);
							onStart(textField_2,valore3,max3);
						}
					} else {
						String errore= "Hai raggiunto il numero massimo di item inseribili per il Container 2";
						String container = "2";
						Errori.popuperrore(errore,container);
					}
				}
			}
		});
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(369, 21, 90, 195);
		panel.add(scrollPane_6);
		textArea_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (textArea_3.getSelectedText() != null) { 
					String s = textArea_3.getSelectedText();
					if(s.contains("Item")) {
						String tempWord = "Item" ; 
						s = s.replaceAll(tempWord, "");
						System.out.println(s);
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					} else {
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					}
				}
			}
		});

		scrollPane_6.setViewportView(textArea_3);
		textArea_3.setEditable(false);
		comboBoxRisposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTextAreaContainer(textArea_3);
				recuperaRisposta(comboBoxRisposta, domanda);
				String numero = (String) comboBoxRisposta.getSelectedItem();
				String temp="Item"+domanda+ "\n";
				valueofTextArea4= textArea_3.getText().toString();
				if(numero=="3") {
					if( valore4 <16) {
						if(cquattro.containsValue(temp)) {
							System.out.println("item gia' presente");
						} else {
							valore4++;
							cquattro.put(valore4,temp);
							System.out.println(cquattro);
							setTextAreaContainer( textArea_3);
							textArea_3.append(temp);
							modificaItem(temp,textArea_3);
							onStart(textField_3,valore4,max4);
						}
					} else {
						String errore= "Hai raggiunto il numero massimo di item inseribili per il Container 3";
						String container = "3";
						Errori.popuperrore(errore,container);
					}
				}
			}
		});
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(486, 21, 94, 195);
		panel.add(scrollPane_7);
		textArea_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (textArea_4.getSelectedText() != null) { 
					String s = textArea_4.getSelectedText();
					if(s.contains("Item")) {
						String tempWord = "Item" ; 
						s = s.replaceAll(tempWord, "");
						System.out.println(s);
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					} else {
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					}
				}
			}
		});

		scrollPane_7.setViewportView(textArea_4);
		textArea_4.setEditable(false);
		comboBoxRisposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTextAreaContainer(textArea_4);
				recuperaRisposta(comboBoxRisposta, domanda);
				String numero = (String) comboBoxRisposta.getSelectedItem();
				String temp="Item"+domanda+ "\n";
				valueofTextArea5= textArea_4.getText().toString();
				if(numero=="4") {
					if( valore5 <14) {
						if(ccinque.containsValue(temp)) {
							System.out.println("item gia' presente");

						} else {
							valore5++;
							ccinque.put(valore5,temp);
							System.out.println(ccinque);
							setTextAreaContainer( textArea_4);
							textArea_4.append(temp);
							modificaItem(temp,textArea_4);
							onStart(textField_4,valore5,max5);
						}
					} else {
						String errore= "Hai raggiunto il numero massimo di item inseribili per il Container 4";
						String container = "4";
						Errori.popuperrore(errore,container);
					}
				}
			}
		});
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(605, 21, 90, 195);
		panel.add(scrollPane_8);
		textArea_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (textArea_5.getSelectedText() != null) { 
					String s = textArea_5.getSelectedText();
					if(s.contains("Item")) {
						String tempWord = "Item" ; 
						s = s.replaceAll(tempWord, "");
						System.out.println(s);
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					} else {
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					}
				}
			}
		});

		scrollPane_8.setViewportView(textArea_5);
		textArea_5.setEditable(false);
		comboBoxRisposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTextAreaContainer(textArea_5);
				recuperaRisposta(comboBoxRisposta, domanda);
				String numero = (String) comboBoxRisposta.getSelectedItem();
				String temp="Item"+domanda+ "\n";
				valueofTextArea6= textArea_5.getText().toString();
				if(numero=="5") {
					if( valore6 <12) {
						if(csei.containsValue(temp)) {
							System.out.println("item gia' presente");

						} else {
							valore6++;
							csei.put(valore6,temp);
							System.out.println(csei);
							setTextAreaContainer( textArea_5);
							textArea_5.append(temp);
							modificaItem(temp,textArea_5);
							onStart(textField_5,valore6,max6);
						}
					} else {
						String errore= "Hai raggiunto il numero massimo di item inseribili per il Container 5";
						String container = "5";
						Errori.popuperrore(errore,container);
					}
				}
			}
		});
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(719, 21, 90, 195);
		panel.add(scrollPane_9);
		textArea_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (textArea_6.getSelectedText() != null) { 
					String s = textArea_6.getSelectedText();
					if(s.contains("Item")) {
						String tempWord = "Item" ; 
						s = s.replaceAll(tempWord, "");
						System.out.println(s);
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					} else {
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					}
				}
			}
		});

		scrollPane_9.setViewportView(textArea_6);
		textArea_6.setEditable(false);
		comboBoxRisposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTextAreaContainer(textArea_6);
				recuperaRisposta(comboBoxRisposta, domanda);
				String numero = (String) comboBoxRisposta.getSelectedItem();
				String temp="Item"+domanda+ "\n";
				valueofTextArea7= textArea_6.getText().toString();
				if(numero=="6")
				{
					if( valore7 <10)
					{
						if(csette.containsValue(temp))
						{
							System.out.println("item gi� presente");

						}
						else
						{
							valore7++;
							csette.put(valore7,temp);
							System.out.println(csette);
							setTextAreaContainer( textArea_6);
							textArea_6.append(temp);
							onStart(textField_6,valore7,max7);
							modificaItem(temp,textArea_6);
							onStart(textField_6,valore7,max7);
						}
					} else {
						String errore= "Hai raggiunto il numero massimo di item inseribili per il Container 6";
						String container = "6";
						Errori.popuperrore(errore,container);
					}
				}
			}
		});

		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(821, 22, 88, 193);
		panel.add(scrollPane_10);
		textArea_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (textArea_7.getSelectedText() != null) { 
					String s = textArea_7.getSelectedText();
					if(s.contains("Item")) {
						String tempWord = "Item" ; 
						s = s.replaceAll(tempWord, "");
						System.out.println(s);
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					} else {
						txtrBes01.setText(testSWAP[Integer.parseInt(s.trim())-1]);
						domanda=Integer.parseInt(s.trim());
					}
				}
			}
		});

		scrollPane_10.setViewportView(textArea_7);
		textArea_7.setEditable(false);
		comboBoxRisposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTextAreaContainer(textArea_7);
				recuperaRisposta(comboBoxRisposta, domanda);
				String numero = (String) comboBoxRisposta.getSelectedItem();
				String temp="Item"+domanda+ "\n";
				valueofTextArea8= textArea_7.getText().toString();
				if(numero=="7") {
					if( valore8 <8) {
						if(cotto.containsValue(temp)) {
							System.out.println("item gia' presente");
						} else {
							valore8++;
							cotto.put(valore8,temp);
							System.out.println(cotto);
							setTextAreaContainer( textArea_7);	    		
							textArea_7.append(temp);
							modificaItem(temp,textArea_7);
							onStart(textField_7,valore8,max8);
						}
					} else {
						String errore= "Hai raggiunto il numero massimo di item inseribili per il Container 7";
						String container = "7";
						Errori.popuperrore(errore,container);
					}
				}
			}
		});

		JTextPane txtpnContainer_7 = new JTextPane();
		txtpnContainer_7.setText("Container 7");
		txtpnContainer_7.setEditable(false);
		txtpnContainer_7.setBounds(819, 0, 90, 20);
		panel.add(txtpnContainer_7);
		repaint();

		textField_1 = new JTextField();
		textField_1.setText("0/22");
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setColumns(10);
		textField_1.setBounds(134, 219, 90, 28);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setText("0/18");
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(255, 219, 90, 28);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setText("0/16");
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(369, 219, 90, 28);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setText("0/14");
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(486, 219, 94, 28);
		panel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setText("0/12");
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(605, 219, 90, 28);
		panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setText("0/10");
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(719, 219, 90, 28);
		panel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setText("0/8");
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(819, 219, 90, 28);
		panel.add(textField_7);

		textField = new JTextField();
		textField.setText("0/100");
		textField.setEditable(false);
		textField.setBounds(15, 219, 90, 28);
		panel.add(textField);
		textField.setColumns(10);
		// Carico il logo del test SWAP
		BufferedImage myPicture = ImageIO.read(new File("resource/testSWAP200flogo.png"));
		JLabel lblNewLabel = new JLabel(new ImageIcon(myPicture));
		lblNewLabel.setBounds(659, 444, 421, 59);
		panelBES.add(lblNewLabel);
	}

	/**
 * Metodo che si occupa di aggiornare il contenuto dei vari container ( e quindi gli item ) nel caso in cui l'utente decida di ritrattare la sua scelta. 
 * @param temp
 * @param area
 */
	public void modificaItem(String temp, JTextArea area) {
		if(textArea !=area && cuno.containsValue(temp)) {
			cuno.keySet().removeIf(key -> cuno.containsValue(temp));;
			cuno.values().removeIf(value-> cuno.containsValue(temp));
			textArea.setText(textArea.getText().replaceAll(temp,""));;
			valore1--;
			onStart(textField,valore1,max1);
		}
		if(textArea_1 !=area && cdue.containsValue(temp)) {
			cdue.keySet().removeIf(key -> cdue.containsValue(temp));
			cdue.values().removeIf(value-> cdue.containsValue(temp));
			textArea_1.setText(textArea_1.getText().replaceAll(temp,""));;
			valore2--;
			onStart(textField_1,valore2,max2);
		}
		if(textArea_2 !=area && ctre.containsValue(temp)) {
			ctre.keySet().removeIf(key -> ctre.containsValue(temp));;
			ctre.values().removeIf(value-> ctre.containsValue(temp));
			textArea_2.setText(textArea_2.getText().replaceAll(temp,""));;
			valore3--;
			onStart(textField_2,valore3,max3);
		}
		if(textArea_3 !=area && cquattro.containsValue(temp)) {
			cquattro.keySet().removeIf(key -> cquattro.containsValue(temp));;
			cquattro.values().removeIf(value-> cquattro.containsValue(temp));
			textArea_3.setText(textArea_3.getText().replaceAll(temp,""));;
			valore4--;
			onStart(textField_3,valore4,max4);
		}
		if(textArea_4 !=area && ccinque.containsValue(temp)) {
			ccinque.keySet().removeIf(key -> ccinque.containsValue(temp));;
			ccinque.values().removeIf(value-> ccinque.containsValue(temp));
			textArea_4.setText(textArea_4.getText().replaceAll(temp,""));;
			valore5--;
			onStart(textField_4,valore5,max5);
		}
		if(textArea_5 !=area && csei.containsValue(temp)) {
			csei.keySet().removeIf(key -> csei.containsValue(temp));;
			csei.values().removeIf(value-> csei.containsValue(temp));
			textArea_5.setText(textArea_5.getText().replaceAll(temp,""));;
			valore6--;
			onStart(textField_5,valore6,max6);
		}
		if(textArea_6 !=area && csette.containsValue(temp)) {
			csette.keySet().removeIf(key -> csette.containsValue(temp));;
			csette.values().removeIf(value-> csette.containsValue(temp));
			textArea_6.setText(textArea_6.getText().replaceAll(temp,""));;
			valore7--;
			onStart(textField_6,valore7,max7);
		}
		if(textArea_7 !=area && cotto.containsValue(temp)) {
			cotto.keySet().removeIf(key -> cotto.containsValue(temp));;
			cotto.values().removeIf(value-> cotto.containsValue(temp));
			textArea_7.setText(textArea_7.getText().replaceAll(temp,""));;
			valore8--;
			onStart(textField_7,valore8,max8);
		}
	}
/**
 * Metodo che si occupa di avviare diversi Thread necessari per aggiornare il numero di item presenti in un container.
 * @param field
 * @param valore
 * @param max
 */
	public void onStart(JTextField field,int valore,String max) {
		super.onStart();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					field.revalidate();
					field.setText(valore + "/"+max);
					Thread.interrupted();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}
/**
 * Metodo che si occupa di creare dei fatti basandosi sul test SWAP
 * @return
 */
	@SuppressWarnings({ "rawtypes" })
	public ArrayList<String> creaFatti() {
		LinkedList<HashMap> containers = new LinkedList<HashMap>(); 
		containers.add((HashMap) cuno);
		containers.add((HashMap) cdue);
		containers.add((HashMap) ctre);
		containers.add((HashMap) cquattro);
		containers.add((HashMap) ccinque);
		containers.add((HashMap) csei);
		containers.add((HashMap) csette);
		containers.add((HashMap) cotto);
		ArrayList<String> arlist = new ArrayList<String>( );
		int indicecontainer=0;
		for (HashMap mycontainer :containers) {
			for (Object j : mycontainer.values()) {
				RW.idnewfact++;
				String tempWord = "Item" ; 
				String indiceitem=((String) j).replaceAll(tempWord, "");
				// aggiunto il -1 perch� parte da zero mentre replace all toglie i numeri dalle stringhe del test swap BISOGNA INSERIRE L'ID UTENTE
				arlist.add("fact("+RW.idnewfact+",item('"+testSWAP[Integer.parseInt(indiceitem.trim())-1].replaceAll("^([0-9]+)", "")+"',risposta(container"+indicecontainer+ "),"+ClassPanelPaziente.getnome_cognome() + "),"+1+")");
			}
			indicecontainer++;
		}
		return arlist;
	}

	public void mousePressed (MouseEvent event) {
		Object o = event.getSource();
		JButton b = null;
		String buttonText = "";
		if(o instanceof JButton)
			b = (JButton)o;
		if(b != null)
			buttonText = b.getText();
	}

	@SuppressWarnings("rawtypes")
	private void recuperaRisposta(JComboBox comboBox, int i) {
		String selezionato = (String) comboBox.getSelectedItem();
		if (selezionato == "1" || selezionato == "2" || selezionato == "3" || selezionato == "4"|| selezionato == "5"|| selezionato == "6"|| selezionato == "7" ) {
			risposteSWAP.put(i,selezionato); 
		}
	}

	public Integer getpunteggioSWAP(){
		return punteggioSWAP;
	}

	public String getpunteggioStrSWAP() {
		return punteggioStrSWAP;
	}

	public void clearTextArea(JTextArea textArea) {
		textArea.setText("");
	}

	public void setTextAreaItems(JTextArea textArea) {
		textArea.setText(String.join("\r\n ", testSWAP));
	}

	public int setTextAreaContainer(JTextArea textArea) {
		Integer count=textArea.getLineCount();
		return count;
	}

	private void asserisciFattoSWAP(String fattoSWAPString) {
		if(RW.retractFromFile(fattoSWAPString, 1) ) {
			RW.writeFact(fattoSWAPString);
			System.out.println("FattoSWAP - "+fattoSWAPString);
		} else
			System.out.println("FattoSWAP gia' asserito - "+fattoSWAPString);
	}

	public String getScrollPane_2ToolTipText() {
		return scrollPane_2.getToolTipText();
	}
	public void setScrollPane_2ToolTipText(String toolTipText) {
		scrollPane_2.setToolTipText(toolTipText);
	}
	public String getTextArea(String s) {
		return s;
	}

	private String[] testSWAP = {
		"1 Tende a incolparsi o a sentirsi responsabile delle cose negative che accadono.",
		"2 Sa usare i suoi talenti, capacita' ed energie in modo efficace e produttivo.",
		"3 Sfrutta gli altri; cerca di essere il/la numero uno; da' pochissima importanza ai valori morali.",
		"4 Si sente esageratamente importante.",
		"5 Tende a essere emotivamente intrusivo/a; Tende a non rispettare i bisogni di autonomia degli altri, la loro privacy ecc..",
		"6 E' tormentato/a da pensieri ossessivi ricorrenti che vive come intrusivi e privi di significato.",
		"7 Sembra in conflitto circa la propria identita' etnica o razziale (Per esempio, sottovaluta e rifiuta oppure sopravvaluta ed e' troppo concentrato sulle sue tradizioni culturali).",
		"8 Tende a partecipare a scontri di potere.",
		"9 Tende a pensare che gli altri siano invidiosi di lui/lei.",
		"10 Sente che una o alcune delle persone per lui/lei importanti hanno una capacita' speciale e quasi magica di capire i suoi pensieri e i suoi sentimenti piu' intimi (Per es., puo' immaginare che l'intesa fra lui/lei e questa persona sia cosi' perfetta da rendere superflui i normali sforzi di comunicazione).",
		"11 Tende ad attaccarsi agli altri in modo intenso o veloce; Sviluppa sentimenti, aspettative ecc.. che non sono giustificati dal contesto o dalla storia della relazione.",
		"12 Le sue emozioni tendono a crescere vertiginosamente sino a sfuggire al suo controllo, sfociando cosi' in sentimenti estremi di angoscia, tristezza, rabbia, eccitazione ecc.",
		"13 Tende a usare i propri problemi medici o psicologici per non lavorare o per non assumersi responsabilita'.",
		"14 Tende a incolpare gli altri dei propri fallimenti o difetti; tende a credere che i suoi problemi siano causati da fattori esterni.",
		"15 Non ha un'immagine stabile di chi e' o di chi vorrebbe diventare (per esempio, gli atteggiamenti, i valori, gli obiettivi o i sentimenti relativi a se stesso/a possono essere instabili e mutevoli).",
		"16 Tende a essere arrabbiato/a o ostile (sia consciamente sia inconsciamente).",
		"17 Tende a ingraziarsi gli altri o a farsi sottomettere (per es., puo' acconsentire a cose che non condivide o fare cose che non vuole perche' spera, in questo modo, di guadagnare il sostegno o l'approvazione altrui).",
		"18 Quando si innamora romanticamente o e' sessualmente attratto/a da una persona, tende a perdere interesse se questa contraccambia.",
		"19 Ama mettersi in gioco; prova piacere nel realizzare le cose.",
		"20 Tende a essere disonesto/ a; tende a mentire o ingannare le altre persone.",
		"21 Tende a essere ostile verso le persone dell'altro sesso, sia consciamente sia inconsciamente (per es., puo' screditarle o mettersi in competizione con loro ecc.).",
		"22 Tende a sviluppare sintomi somatici in risposta a stress o conflitti (per es., mal di testa, mal di schiena, dolori addominali, asma ecc.).",
		"23 Tende a coinvolgersi in situazioni romantiche o sessuali a 'tre' (Per es., E' piu' interessato/a a partner che hanno gia' una relazione, che sono corteggiati da qualcun altro ecc.)",
		"24 Tende a essere inaffidabile e irresponsabile (per esempio, non soddisfa i propri obblighi professionali o non onora i propri impegni finanziari).",
		"25 Ha difficolta' a riconoscere o esprimere la propria rabbia.",
		"26 Tende a essere coinvolto/a o a rimanere in relazioni in cui subisce abusi emotivi o fisici.",
		"27 Ha attacchi di panico che durano da pochi minuti ad alcune ore, accompagnati da forti risposte fisiologiche (per es., accelerazione del battito cardiaco, respiro affannoso, senso di soffocamento, nausea e vertigine).",
		"28 Tende a sviluppare preoccupazioni relative alla sporcizia, alla pulizia, alla contaminazione, ecc. (Per esempio, bere dal bicchiere di un'altra persona, usare i bagni pubblici, ecc.).",
		"29 Ha difficolta' nel comprendere il senso del comportamento altrui; spesso lo fraintende, lo interpreta in modo scorretto o e' confuso/a dalle azioni e dalle reazioni degli altri.",
		"30 Tende a sentirsi apatico/a, affaticato/a e privo/a di energia.",
		"31 Tende a dimostrare una sconsiderata indifferenza verso i diritti, la proprieta' o la sicurezza degli altri.",
		"32 Sa mantenere una relazione amorosa caratterizzata da un'intimita' autentica e dalla capacita' di prendersi cura dell'altra persona.",
		"33 E' inibito/a rispetto al raggiungimento di obiettivi o in generale del successo; le sue aspirazioni o le realizzazioni tendono a essere al di sotto delle sue potenzialita'.",
		"34 Tende a essere eccessivamente seduttivo/a o provocante dal punto di vista sessuale, sia consciamente sia inconsciamente (per es., puo' flirtare in modo inopportuno, essere completamente assorbito/a dalle conquiste sessuali, ed essere incline a 'tenere sulla corda').",
		"35 E' tendenzialmente ansioso/a.",
		"36 Tende a sentirsi impotente, debole o alla merce' di forze al di fuori del suo controllo.",
		"37 Trova significato nella sua appartenenza e nel suo contributo a una comunita' piu' ampia (Per esempio, organizzazioni di vario tipo, chiesa, vicinato ecc.)",
		"38 Quando e' con gli altri non riesce a sentirsi veramente se stesso; tende a sentirsi 'finto' o poco autentico.",
		"39 Sembra trarre piacere o soddisfazione comportandosi in modo sadico o aggressivo con gli altri.",
		"40 Tende a comportarsi in modo illegale o criminale.",
		"41 Sembra incapace di descrivere le persone che per lui/lei sono importanti riuscendo a trasmettere l'idea di che tipo di persone siano; le descrizioni di queste persone sono bidimensionali e piuttosto misere.",
		"42 Tende a provare invidia.",
		"43 Cerca di avere potere o di esercitare la sua influenza sugli altri (Sia in modi benefici sia in modi distruttivi).",
		"44 La sua percezione della realta' puo' deteriorarsi gravemente sotto stress (per es., puo' diventare delirante).",
		"45 Tende a idealizzare alcune persone in modi irrealistici; le vede come 'totalmente buone', fino a escludere la presenza anche dei difetti umani piu' comuni.",
		"46 Tende a essere suggestionabile o facilmente influenzabile.",
		"47 Non sa se e' eterosessuale, omosessuale o bisessuale.",
		"48 Vuole essere al centro dell'attenzione.",
		"49 Ha fantasie di potere, bellezza, talento, ingegno, ecc., senza limiti.",
		"50 Tende a sentire che la sua vita non ha significato.",
		"51 Tende a suscitare simpatia negli altri.",
		"52 Ha poca empatia; sembra incapace di capire o rispondere ai bisogni e ai sentimenti degli altri a meno che non coincidano con i propri.",
		"53 Tende a trattare gli altri come un pubblico che deve testimoniare la sua importanza, il suo ingegno, la sua bellezza ecc.",
		"54 Tende a sentirsi inadeguato/a, inferiore o fallito/a.",
		"55 E' capace di trovare significato e fonte di soddisfazione nel guidare, educare o crescere altre persone.",
		"56 Sembra provare poco piacere, soddisfazione o godimento nelle attivita' quotidiane, o non ne prova affatto.",
		"57 Tende a sentirsi in colpa.",
		"58 Ha poco o nessun interesse nell'avere esperienze sessuali con altre persone.",
		"59 E' empatico/a, sensibile e responsivo/a verso i bisogni e i sentimenti degli altri.",
		"60 Tende a essere timido/a o riservato/a in situazioni sociali.",
		"61 Tende a disprezzare le qualita' tradizionalmente associate al proprio sesso mentre apprezza qualita' tradizionalmente associate al sesso opposto (Per es., una donna che svaluta la capacita' di prendersi cura degli altri e la sensibilita' emotiva mentre attribuisce molto valore all'autoaffermazione e all'indipendenza).",
		"62 Tende a essere preoccupato/a del cibo, della dieta e in generale del mangiare.",
		"63 Quando e' necessario, sa essere assertivo/a in modo efficace e appropriato.",
		"64 Il suo umore tende a mutare ciclicamente con intervalli di settimane o mesi tra gli stati eccitati e quelli depressivi (Posizionare molto in alto questo item implica un disturbo bipolare dell'umore).",
		"65 Cerca di dominare con la violenza o l'intimidazione una persona per lui/lei importante (Per es., il coniuge, l'amante, un membro della famiglia).",
		"66 Si dedica al lavoro e alla produttivita' in maniera eccessiva a scapito del tempo libero e delle relazioni.",
		"67 Tende a essere avaro/a e poco generoso/a (con il denaro, le idee, le emozioni, ecc.).",
		"68 Apprezza e sa rispondere all'umorismo.",
		"69 E' incapace di buttar via gli oggetti anche quando ormai sono consumati o privi di valore; Tende ad accumulare e/o raccogliere e/o tenersi strette le sue cose.",
		"70 Fa ricorrenti abbuffate seguite da â€œpurgheâ€� (Per es., si provoca vomito, abusa di lassativi, digiuna ecc.); Sono presenti episodi bulimici.",
		"71 Tende a cercare il brivido, la novita', l'avventura ecc.",
		"72 Le sue percezioni sembrano superficiali, generiche, impressionistiche; fatica a mettere a fuoco dettagli specifici.",
		"73 Tende a essere 'catastrofico/a'; E' portato/a a vedere i problemi come disastrosi, impossibili da risolvere ecc.",
		"74 Esprime le proprie emozioni in modi esagerati e teatrali.",
		"75 Tende a pensare in termini concreti e a interpretare le cose in modo troppo letterale; non E' molto capace di apprezzare le metafore, le analogie o le sfumature.",
		"76 Si comporta in modo da suscitare negli altri sentimenti simili a quelli che lui/lei stesso/a sta provando (Per es., quando E' arrabbiato/a, agisce in un modo che provoca rabbia negli altri; Quando E' angosciato/a, agisce in modo che induce angoscia negli altri).",
		"77 Tende a essere eccessivamente bisognoso/ a o dipendente; richiede rassicurazioni o approvazioni eccessive.",
		"78 Tende a esprimere la propria aggressivita' in modi passivi e indiretti (per es., puo' fare errori, procrastinare, dimenticare, tenere il muso ecc.).",
		"79 Tende a vedere alcune persone come 'totalmente cattive' e perde la capacita' di percepire le loro qualita' positive.",
		"80 Tende a essere geloso/a e sessualmente possessivo/a; E' spesso assorbito/a da pensieri riguardanti infedelta' reali o immaginate.",
		"81 Ri-esperisce o ri-vive piu' volte un evento traumatico del passato (per es., ha ricordi intrusivi o sogni ricorrenti dell'evento; E' scioccato/a o terrorizzato/a da eventi presenti che assomigliano o simbolizzano il trauma passato).",
		"82 Riesce ad ascoltare una notizia minacciosa sul piano emotivo (cioe' un'informazione che mette in discussione le credenze e percezioni di se' e degli altri che per lui/lei sono fondamentali) e sa usarla e trarne beneficio.",
		"83 Le sue credenze e aspettative sembrano cliche' o stereotipi, come fossero uscite da un libro di fiabe o da un film.",
		"84 Tende a essere competitivo/a (sia consciamente sia inconsciamente).",
		"85 E' consapevole dei suoi interessi omosessuali.	",
		"86 Tende a provare vergogna o a sentirsi imbarazzato/a.",
		"87 E' subito portato/a a pensare che gli altri vogliano danneggiarlo/a o approfittarsi di lui/lei; tende a cogliere intenzioni malevole nelle parole e nelle azioni degli altri.",
		"88 Sembra non preoccuparsi abbastanza della soddisfazione dei propri bisogni; sembra non sentirsi in diritto di ottenere o chiedere cio' che si merita.",
		"89 Sembra essere riuscito/a a scendere a patti con esperienze dolorose del passato, avervi trovato un significato ed essere cresciuto/a grazie anche a queste esperienze.",
		"90 Tende a sentirsi vuoto/a o annoiato/a.",
		"91 Tende a essere autocritico/a; si pone standard irrealisticamente elevati ed E' intollerante anche verso i propri umani difetti.",
		"92 Sa esprimersi in modo articolato; sa raccontarsi.",
		"93 Sembra che di come vanno le cose nel mondo ne sappia meno di quanto ci si aspetterebbe da una persona con la sua intelligenza, il suo background ecc.; appare naif o 'innocente'.",
		"94 Ha una vita sessuale attiva e soddisfacente.",
		"95 Si sente a proprio agio in situazioni sociali.",
		"96 Tende a suscitare negli altri antipatia e animosita'.",
		"97 Tende a usare troppo il proprio aspetto fisico per attirare l'attenzione altrui ed essere notato/a.",
		"98 Tende ad aver paura di essere rifiutato/a o abbandonato/a dalle persone che per lui/lei sono emotivamente significative.",
		"99 Sembra che associ l'attivita' sessuale al pericolo (Per esempio, a ferite, punizioni, contaminazione, ecc.).",
		"100 Tende a pensare in termini astratti e intellettualizzati, anche su argomenti di rilievo personale.",
		"101 Di solito trova soddisfazione e motivo di felicita' in quel che fa.",
		"102 Ha una fobia specifica (Per es., serpenti, ragni, cani, aeroplani, ascensori ecc.).",
		"103 Tende a reagire alle critiche con sentimenti di rabbia e umiliazione.",
		"104 Sembra non aver bisogno della compagnia e del contatto umano; E' proprio indifferente alla presenza degli altri.",
		"105 Tende a evitare di confidarsi con gli altri per paura di essere tradito/a; Si aspetta che le cose che dice e fa siano poi usate contro di lui/lei.",
		"106 Tende a esprimere affetti appropriati per qualita' e intensita' alla situazione che sta vivendo.",
		"107 Tende a esprimere in modo esagerato caratteristiche o modi di fare tradizionalmente associati al proprio sesso (Per es., una donna iperfemminile o un uomo ipervirile e 'macho').",
		"108 Tende a limitare l'assunzione di cibo fino al punto da diventare sottopeso e malnutrito/a.",
		"109 Tende ad attuare comportamenti automutilanti (per es. tagliarsi, bruciarsi ecc.).",
		"110 Tende ad attaccarsi o a coinvolgersi sentimentalmente con persone che non sono emotivamente disponibili.",
		"111 E' capace di riconoscere punti di vista alternativi anche quando si tratta di argomenti che suscitano emozioni forti.",
		"112 Tende a non preoccuparsi delle conseguenze delle sue azioni; sembra che si senta immune o invulnerabile.",
		"113 Sembra non provare alcun rimorso per i danni o le ferite che ha arrecato ad altre persone.",
		"114 Tende a essere critico/a con le altre persone.",
		"115 Quando e' arrabbiato/a tende a rompere le cose o a diventare fisicamente aggressivo/a.",
		"116 Tende a vedere negli altri sentimenti e impulsi che non accetta in se stesso/a.",
		"117 E' incapace di calmarsi o tranquillizzarsi da solo/a quando e' stressato/a; ha bisogno di un'altra persona che lo/la aiuti a regolare gli affetti.",
		"118 Tende a considerare le esperienze sessuali come qualcosa di rivoltante o disgustoso. ",
		"119 Tende a essere inibito/a o coartato/a; non riesce a concedersi di riconoscere o esprimere desideri e impulsi.",
		"120 Ha standard morali ed etici e si sforza di vivere alla loro altezza.",
		"121 E' creativo/a; sa vedere le cose o affrontare i problemi in modi originali.",
		"122 La sua vita E' organizzata in modo tendenzialmente caotico o instabile (Per es., vive in condizioni precarie, transitorie, mal definite; Puo' non avere un telefono o un indirizzo stabile).",
		"123 Tende ad aderire rigidamente alla routine giornaliera e si angoscia o si sente a disagio quando subisce alterazioni.",
		"124 Tende a evitare le situazioni sociali perche' ha paura di trovarsi in imbarazzo o sentirsi umiliato/a.",
		"125 Il suo aspetto e i suoi modi di fare sembrano strani o particolari (per esempio. il look, l'igiene, la postura, il contatto visivo, l'andamento dell'eloquio, ecc. sembrano in qualche modo strani o 'fuori contatto ').",
		"126 Sembra avere una gamma di emozioni limitata o ristretta.",
		"127 Tende a sentirsi incompreso/a, maltrattato/a o vittimizzato/a.",
		"128 Fantastica di trovare l'amore ideale e perfetto.",
		"129 Tende ad avere conflitti relativi all'autorita' (per es., puo' sentire di doverla sottomettere, vincere, sconfiggere, di doversi ribellare ecc.).",
		"130 I suoi processi di ragionamento o le esperienze percettive sembrano strani e peculiari (per es. puo' fare inferenze arbitrarie; puo' vedere messaggi nascosti o significati speciali in eventi ordinari).",
		"131 Difficilmente si concede la possibilita' di provare forti emozioni piacevoli (per es., eccitazione, gioia, orgoglio).",
		"132 Tende ad avere molte avventure sessuali; E' promiscuo/a. ",
		"133 Tende a essere arrogante, superbo/a e sprezzante.",
		"134 Tende ad agire in modo impulsivo, senza considerare le conseguenze delle sue azioni.",
		"135 Ha paure infondate di contrarre patologie mediche; Tende a interpretare dei banali dolori come se fossero sintomi di malattie particolari; E' ipocondriaco/a.",
		"136 Tende a essere superstizioso/a o a credere in fenomeni magici e soprannaturali (Per es., astrologia, tarocchi, cristalli, percezioni extra-sensoriali, 'auree' ecc.).",
		"137 Mostra desideri o interessi omosessuali inconsapevoli (Per es., e' eccessivamente omofobo oppure mostra segni di attrazione verso una persona dello stesso sesso ma non ne riconosce il significato).",
		"138 Quando e' sotto stress tende a entrare in stati di coscienza alterati o dissociati (Per es., si sente strano/a o il mondo gli sembra strano, estraneo o irreale.",
		"139 Tende a tenere il broncio; puo' legarsi al dito insulti e offese per molto tempo.",
		"140 Ha perversioni sessuali o E' feticista; Prima di provare gratificazioni sessuali deve costruire situazioni dal copione rigido o molto strane e particolari.",
		"141 Si identifica in misura estrema con una 'causa' sociale o politica, al punto che tale identificazione puo' sembrare eccessiva o fanatica.",
		"142 Tende a minacciare o tentare ripetutamente il suicidio, sia in forma di 'grido d'aiuto' sia come tentativo di manipolare gli altri.",
		"143 Tende a credere che una persona come lui/lei possa essere apprezzato/a solo da, o possa frequentare solo persone che hanno uno status elevato, sono superiori o in qualche modo 'speciali'.",
		"144 Tende a vedersi come una persona logica e razionale, non influenzata dalle emozioni; preferisce comportarsi come se le emozioni fossero irrilevanti o prive di conseguenze.",
		"145 L'eloquio tende a essere circostanziato, vago, sconnesso, pieno di digressioni ecc.",
		"146 Tende a suscitare noia nelle altre persone (per es., puo' parlare incessantemente, senza partecipazione, o di argomenti del tutto irrilevanti).",
		"147 Tende ad abusare di alcolici.",
		"148 Ha poco insight psicologico riguardo alle proprie motivazioni, ai comportamenti, ecc.; non riesce a prendere in considerazione interpretazioni alternative della propria esperienza.",
		"149 Tende a considerarsi un emarginato/a o un outsider, si sente privo/a di qualunque appartenenza.",
		"150 Tende ad identificarsi in modo eccessivo con altre persone che ammira, delle quali tende a diventare un/una ammiratore/trice o un/una 'discepolo' (Per es., puo' assumere gli atteggiamenti, le credenze, i modi di fare ecc.).",
		"151 Fa esperienza del passato come di una serie di eventi privi di rapporti e connessioni reciproche; Ha difficolta' a fornire un racconto coerente della storia della propria vita.",
		"152 Tende a rimuovere o a 'dimenticare' gli eventi stressanti, o a distorcere il ricordo di eventi stressanti senza rendersene conto.",
		"153 Le sue relazioni interpersonali tendono a essere instabili  e caotiche e cambiano rapidamente.",
		"154 Tende a suscitare negli altri relazioni estreme e sentimenti forti.",
		"155 Tende a descrivere le esperienze in termini generali; non vuole o non sa offrire dettagli specifici.",
		"156 Ha un'immagine del proprio corpo disturbata o distorta, si trova poco attraente, grottesco/a, disgustoso/a ecc.",
		"157 Le emozioni forti tendono a farlo/a diventare irrazionale; puo' mostrare un notevole declino del proprio livello di funzionamento abituale.",
		"158 Ha paura di coinvolgersi in relazioni dâ€™amore a lungo termine.",
		"159 Tende a negare o disconoscere i propri bisogni di cure, conforto, intimita' ecc. o a considerare tali bisogni inaccettabili.",
		"160 Non ha relazioni ne' amici stretti.",
		"161 Tende a fare abuso di sostanze illegali.",
		"162 Esprime sentimenti o credenze contraddittorie senza essere disturbato/a dalla loro incoerenza; Sente relativamente poco il bisogno di coinciliare o risolvere idee contraddittorie.",
		"163 Sembra volersi 'punire'; crea situazioni che procurano infelicita', o evita attivamente occasioni di piacere e gratificazione.",
		"164 Tende a credersi piu' virtuoso/a degli altri e a fare il/la moralista.",
		"165 Tende a distorcere desideri o sentimenti inaccettabili trasformandoli nel loro opposto (Per es., puo' esprimere preoccupazione o affetto eccessivi mostrando al contempo segni di ostilita' che non riconosce; E' disgustato/a dal tema della sessualita', ma al tempo stesso mostra segni di interesse o eccitazione, ecc.).",
		"166 Tende a oscillare tra un controllo troppo scarso e un controllo eccessivo dei propri bisogni e dei propri impulsi (cioe', bisogni e desideri sono espressi in modo impulsivo e con una scarsa considerazione delle loro conseguenze, oppure sono negati senza che sia permesso loro alcun tipo di espressione).",
		"167 Ha bisogno degli altri e allo stesso tempo li respinge (Per es., chiede insistentemente che qualcuno si occupi di lui/lei e stabilisca con lui/lei un rapporto di intimita' ma poi tende a rifiutarlo quando gli/le viene offerto).",
		"168 Lotta contro veri e propri desideri suicidi.",
		"169 Ha paura di diventare come uno dei suoi genitori (O una delle sue figure genitoriali) per il quale nutre forti sentimenti negativi; Puo' fare di tutto per evitare o rifiutare atteggiamenti o comportamenti associati a quella persona.",
		"170 Tende a essere oppositivo/a, caparbio/a, 'bastian contrario'.",
		"171 Ha paura della solitudine; fa di tutto per non restare solo/a.",
		"172 Ha una disfunzione sessuale specifica durante i rapporti o mentre cerca di avere rapporti sessuali (Per es., inibizioni dell'orgasmo o vaginismo nelle donne, impotenza o eiaculazione precoce degli uomini).",
		"173 Tende a interessarsi eccessivamente ai dettagli sino a perdere di vista cio' che E' davvero significativo in una data situazione.",
		"174 Si aspetta di essere 'perfetto/a' (per es. nell'aspetto, in cio' che vuole realizzare, nelle performance ecc.).",
		"175 E' tendenzialmente coscienzioso/a e responsabile.",
		"176 Tende a confondere i propri pensieri, sentimenti o tratti della personalita' con quelli di altre persone (Per es., puo' usare le stesse parole per descriversi e per descrivere un'altra persona, credere di condividere con essa gli stessi pensieri e sentimenti, trattare l'altra persona come un'estensione di se'ecc.).",
		"177 Cerca di convincere piu' volte gli altri del proprio impegno a cambiare ma poi torna a comportarsi in modo disadattivo; vuole convincere gli altri che 'questa volta E' davvero diverso'.",
		"178 E' angosciato/a dalla sensazione che qualcuno o qualcosa sia stato irrimediabilmente perso (Per es., l'amore, la gioventu', la possibilita' di essere felice ecc.).",
		"179 E' tendenzialmente energico/a o espansivo/a.",
		"180 Ha problemi nel prendere decisioni; tende a essere indeciso/a o a tentennare di fronte alle scelte.",
		"181 Tende a scegliere partner sessuali o amorosi inappropriati per eta', status (per es. sociale, economico, intellettuale) ecc.",
		"182 Tende a essere controllante.",
		"183 Ha intuito psicologico; riesce a capire in modo piuttosto sofisticato se stesso/a e gli altri.",
		"184 Le sue affermazioni verbali sembrano incongruenti con gli affetti o con i messaggi non verbali ad esse associati.",
		"185 Tende a esprimere una rabbia intensa e inappropriata, sproporzionata rispetto alla situazione.",
		"186 Fa fatica a rivolgere contemporaneamente sentimenti di tenerezza e sentimenti sessuali verso la stessa persona (Per es., vede gli altri come rispettabili e virtuosi oppure sensuali ed eccitanti, ma non riesce ad attribuire queste caratteristiche a una stessa persona).",
		"187 Tende a sentirsi in colpa o a provare vergogna per i propri interessi o attivita' sessuali.",
		"188 La sua vita lavorativa tende a essere caotica o instabile (Per es., la sua organizzazione lavorativa sembra sempre temporanea, transitoria o mal definita).",
		"189 Tende a sentirsi infelice. depresso/a o abbattuto/a.",
		"190 Sembra che si senta privilegiato/a e di avere tutti i diritti; si aspetta un trattamento preferenziale.",
		"191 Le sue emozioni tendono a cambiare rapidamente e in modo imprevedibile.",
		"192 Tende a essere eccessivamente preoccupato/a per le regole, le procedure, l'ordine, l'organizzazione, le programmazioni ecc.",
		"193 Ha scarse capacita' sociali; nelle situazioni sociali tende a comportarsi in modo goffo e inappropriato.",
		"194 Tende a manipolare le emozioni degli altri per ottenere cio' che vuole.",
		"195 Tende a essere preoccupato/a dalla morte e dal morire.",
		"196 Riesce a trovare senso e soddisfazione nel perseguire obiettivi e ambizioni a lungo termine.",
		"197 Tende a cercare o a creare relazioni interpersonali nelle quali ha il ruolo della persona che si prende cura degli altri, li salva o li protegge.",
		"198 E' verbalmente poco articolato/a; Non riesce a esprimersi bene con le parole.",
		"199 Tende a essere passivo/a e poco assertivo/a.",
		"200 Riesce a stringere amicizie intime e di lunga durata caratterizzate da sostegno reciproco e condivisione delle esperienze."
	};

}
