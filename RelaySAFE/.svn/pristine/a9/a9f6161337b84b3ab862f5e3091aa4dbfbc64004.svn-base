package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import util.Fact;

import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class ClassPanelAnamnesi extends GenericPanel{
	
	private List<Fact> fatti = new LinkedList<Fact>();
	
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtGiorno;
	private JTextField txtMese;
	private JTextField txtAnno;
	private JComboBox cBoxSesso;
	private JPanel pnlDatiPersonali;
	
	//Combobox domande anamnesi odontoiatrica
	private JComboBox<String> cBoxSanguinamento;
	private JComboBox<String> cBoxAlito;
	private JComboBox<String> cBoxMobilita;
	private JComboBox<String> cBoxRumoriArticolari;
	private JComboBox<String> cBoxSensibilita;
	private JComboBox<String> cBoxTrisma;
	private JComboBox<String> cBoxDigrigna;
	private JComboBox<String> cBoxEmicrania;
	
	//Checkbox domande generali
	private JCheckBox chckbxBuonaSalute;
	private JCheckBox chckbxStupefacenti;
	private JCheckBox chckbxAlcolici;
	private JCheckBox chckbxFumatore;
	private JCheckBox chckbxGravidanza;
	private JCheckBox chckbxFarmaci;
	private JCheckBox chckbxInterventi;
	private JCheckBox chckbxMalattieSistemiche;
	private JCheckBox chckbxProblemiCardiaci;
	private JCheckBox chckbxIpertensione;
	private JCheckBox chckbxDiabete;
	private JCheckBox chckbxProblemiDiCoagulazione;
	private JCheckBox chckbxAllergieParticolari;
	
	public String[] sceltaBox = {"","Vero","Falso"};
	
	
	public String nome, cognome, sesso;
	/**Stringa adibita a contenere nome e cognome nel formato nome_cognome, utile a costruire i fatti
	 * in maniera più leggibile, utilizzando i dati personali del paziente*/
	private static String nome_cognome;
	int eta;
	private JPanel panelDomandeAnamnesi;
	
	private JButton btnInvia;
	private JTextField textFieldSigarette;
	private JTextField textFieldFarmaci;
	
	private JLabel lblConfermaDati;
	
	public ClassPanelAnamnesi() {
		super("Anamnesi");
		setBounds(33, 35, 1105, 580);
		setLayout(null);
		
		JPanel panelAnamnesi = new JPanel();
		panelAnamnesi.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelAnamnesi.setLayout(null);
		panelAnamnesi.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Scheda Anamnesi Dentale", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelAnamnesi.setBounds(10, 11, 1083, 557);
		add(panelAnamnesi);

		pnlDatiPersonali = new JPanel();
		pnlDatiPersonali.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Anagrafica", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDatiPersonali.setBounds(16, 23, 602, 99);
		panelAnamnesi.add(pnlDatiPersonali);
		pnlDatiPersonali.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(12, 29, 59, 14);
		pnlDatiPersonali.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(119, 29, 154, 26);
		pnlDatiPersonali.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblDataDiNascita = new JLabel("Data di Nascita");
		lblDataDiNascita.setBounds(326, 33, 115, 14);
		pnlDatiPersonali.add(lblDataDiNascita);

		txtGiorno = new JTextField();
		txtGiorno.setToolTipText("giorno");
		txtGiorno.setBounds(448, 29, 30, 26);
		pnlDatiPersonali.add(txtGiorno);
		txtGiorno.setColumns(10);

		txtMese = new JTextField();
		txtMese.setToolTipText("mese");
		txtMese.setBounds(478, 29, 30, 26);
		pnlDatiPersonali.add(txtMese);
		txtMese.setColumns(10);

		txtAnno = new JTextField();
		txtAnno.setToolTipText("anno");
		txtAnno.setBounds(508, 29, 45, 26);
		pnlDatiPersonali.add(txtAnno);
		txtAnno.setColumns(10);

		JLabel lblSesso = new JLabel("Sesso");
		lblSesso.setBounds(326, 70, 46, 14);
		pnlDatiPersonali.add(lblSesso);

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(12, 67, 89, 14);
		pnlDatiPersonali.add(lblCognome);

		txtCognome = new JTextField();
		txtCognome.setColumns(10);
		txtCognome.setBounds(119, 67, 154, 26);
		pnlDatiPersonali.add(txtCognome);

		txtAnno.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				abilitaInvio();
			}
		});
		
		cBoxSesso = new JComboBox();
		cBoxSesso.setModel(new DefaultComboBoxModel(new String[] {"", "Maschile", "Femminile"}));
		cBoxSesso.setBounds(448, 67, 105, 20);
		pnlDatiPersonali.add(cBoxSesso);
		
		panelDomandeAnamnesi = new JPanel();
		panelDomandeAnamnesi.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Anamnesi Odontoiatrica", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDomandeAnamnesi.setBounds(16, 134, 602, 345);
		panelAnamnesi.add(panelDomandeAnamnesi);
		panelDomandeAnamnesi.setLayout(null);
		
		JTextPane txtpnSanguinamento = new JTextPane();
		txtpnSanguinamento.setBounds(12, 23, 479, 34);
		panelDomandeAnamnesi.add(txtpnSanguinamento);
		txtpnSanguinamento.setBackground(SystemColor.control);
		txtpnSanguinamento.setDisabledTextColor(Color.BLACK);
		txtpnSanguinamento.setEditable(false);
		txtpnSanguinamento.setText("1. Accusa facile sanguinamento alle gengive?");

		cBoxSanguinamento = new JComboBox(sceltaBox);
		cBoxSanguinamento.setBounds(494, 28, 77, 20);
		panelDomandeAnamnesi.add(cBoxSanguinamento);
		cBoxSanguinamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		
		JTextPane txtpnAlito = new JTextPane();
		txtpnAlito.setBounds(12, 60, 479, 34);
		panelDomandeAnamnesi.add(txtpnAlito);
		txtpnAlito.setBackground(SystemColor.control);
		txtpnAlito.setDisabledTextColor(Color.BLACK);
		txtpnAlito.setEditable(false);
		txtpnAlito.setText("2. Soffre di alitosi o ha mai avuto un sapore cattivo in bocca?");

		cBoxAlito = new JComboBox(sceltaBox);
		cBoxAlito.setBounds(494, 65, 77, 20);
		panelDomandeAnamnesi.add(cBoxAlito);
		cBoxAlito.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		cBoxAlito.setSelectedIndex(0);
		
		JTextPane txtpnMobilita = new JTextPane();
		txtpnMobilita.setBounds(12, 97, 479, 34);
		panelDomandeAnamnesi.add(txtpnMobilita);
		txtpnMobilita.setBackground(SystemColor.control);
		txtpnMobilita.setDisabledTextColor(Color.BLACK);
		txtpnMobilita.setEditable(false);
		txtpnMobilita.setText("3. I suoi denti sono diventati mobili (non a causa di un trauma) o ha difficoltà a mordere una mela?");

		cBoxMobilita = new JComboBox(sceltaBox);
		cBoxMobilita.setBounds(494, 102, 77, 20);
		panelDomandeAnamnesi.add(cBoxMobilita);
		cBoxMobilita.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		cBoxMobilita.setSelectedIndex(0);
		
		JTextPane txtpnRumoriArticolari = new JTextPane();
		txtpnRumoriArticolari.setBounds(12, 134, 479, 34);
		panelDomandeAnamnesi.add(txtpnRumoriArticolari);
		txtpnRumoriArticolari.setBackground(SystemColor.control);
		txtpnRumoriArticolari.setDisabledTextColor(Color.BLACK);
		txtpnRumoriArticolari.setEditable(false);
		txtpnRumoriArticolari.setText("4. Avverte rumori articolari aprendo la bocca?");

		cBoxRumoriArticolari = new JComboBox(sceltaBox);
		cBoxRumoriArticolari.setBounds(494, 139, 77, 20);
		panelDomandeAnamnesi.add(cBoxRumoriArticolari);
		cBoxRumoriArticolari.setSelectedIndex(0);
				
		JTextPane txtpnSensibilita = new JTextPane();
		txtpnSensibilita.setText("5. Accusa sensibilità dentale?");
		txtpnSensibilita.setEditable(false);
		txtpnSensibilita.setDisabledTextColor(Color.BLACK);
		txtpnSensibilita.setBackground(SystemColor.window);
		txtpnSensibilita.setBounds(12, 171, 479, 34);
		panelDomandeAnamnesi.add(txtpnSensibilita);
		
		cBoxSensibilita = new JComboBox(sceltaBox);
		cBoxSensibilita.setSelectedIndex(0);
		cBoxSensibilita.setBounds(494, 176, 77, 20);
		panelDomandeAnamnesi.add(cBoxSensibilita);
		
		JTextPane txtpnTrisma = new JTextPane();
		txtpnTrisma.setText("6. Ha problemi con le sue articolazioni temporomandibolari? (es. accusa dolore nell'apertura della bocca)");
		txtpnTrisma.setEditable(false);
		txtpnTrisma.setDisabledTextColor(Color.BLACK);
		txtpnTrisma.setBackground(SystemColor.window);
		txtpnTrisma.setBounds(12, 208, 479, 34);
		panelDomandeAnamnesi.add(txtpnTrisma);
		
		cBoxTrisma = new JComboBox(sceltaBox);
		cBoxTrisma.setSelectedIndex(0);
		cBoxTrisma.setBounds(494, 213, 77, 20);
		panelDomandeAnamnesi.add(cBoxTrisma);
		
		JTextPane txtpnDigrigna = new JTextPane();
		txtpnDigrigna.setText("7. Digrigna o stringe i denti durante la notte o durante il giorno?");
		txtpnDigrigna.setEditable(false);
		txtpnDigrigna.setDisabledTextColor(Color.BLACK);
		txtpnDigrigna.setBackground(SystemColor.window);
		txtpnDigrigna.setBounds(12, 245, 479, 34);
		panelDomandeAnamnesi.add(txtpnDigrigna);
		
		cBoxDigrigna = new JComboBox(sceltaBox);
		cBoxDigrigna.setSelectedIndex(0);
		cBoxDigrigna.setBounds(494, 250, 77, 20);
		panelDomandeAnamnesi.add(cBoxDigrigna);
		
		JTextPane txtpnEmicrania = new JTextPane();
		txtpnEmicrania.setText("8. Soffre di mal di testa?");
		txtpnEmicrania.setEditable(false);
		txtpnEmicrania.setDisabledTextColor(Color.BLACK);
		txtpnEmicrania.setBackground(SystemColor.window);
		txtpnEmicrania.setBounds(12, 282, 479, 34);
		panelDomandeAnamnesi.add(txtpnEmicrania);
		
		cBoxEmicrania = new JComboBox(sceltaBox);
		cBoxEmicrania.setSelectedIndex(0);
		cBoxEmicrania.setBounds(494, 287, 77, 20);
		panelDomandeAnamnesi.add(cBoxEmicrania);
	
		
		JPanel pnlDatiGenerali = new JPanel();
		pnlDatiGenerali.setLayout(null);
		pnlDatiGenerali.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Dati Generali", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDatiGenerali.setBounds(630, 23, 441, 393);
		panelAnamnesi.add(pnlDatiGenerali);
		
		chckbxBuonaSalute = new JCheckBox("Gode di buona salute");
		chckbxBuonaSalute.setBounds(8, 18, 230, 23);
		pnlDatiGenerali.add(chckbxBuonaSalute);
		
		chckbxStupefacenti = new JCheckBox("Fa attualmente uso di sostanze stupefacenti");
		chckbxStupefacenti.setBounds(8, 41, 359, 23);
		pnlDatiGenerali.add(chckbxStupefacenti);
		
		chckbxAlcolici = new JCheckBox("Beve alcolici abitualmente");
		chckbxAlcolici.setBounds(8, 64, 245, 23);
		pnlDatiGenerali.add(chckbxAlcolici);
		
		chckbxFumatore = new JCheckBox("Fumatore");
		chckbxFumatore.setBounds(8, 86, 106, 23);
		pnlDatiGenerali.add(chckbxFumatore);
		chckbxFumatore.addItemListener(new ItemListener() {
		      public void itemStateChanged(ItemEvent e) {
		          if(chckbxFumatore.isSelected())
		        	  textFieldSigarette.setEnabled(true);
		          else 
		        	  textFieldSigarette.setEnabled(false);
		        }
		      });
		
		JLabel lblNumeroDiSigarette = new JLabel("Numero di sigarette al giorno:");
		lblNumeroDiSigarette.setBounds(122, 90, 245, 15);
		pnlDatiGenerali.add(lblNumeroDiSigarette);
		
		textFieldSigarette = new JTextField();
		textFieldSigarette.setBounds(349, 83, 60, 30);
		pnlDatiGenerali.add(textFieldSigarette);
		textFieldSigarette.setEnabled(false);
		textFieldSigarette.setColumns(10);
		
		chckbxGravidanza = new JCheckBox("E' in stato di gravidanza");
		chckbxGravidanza.setBounds(8, 109, 203, 23);
		pnlDatiGenerali.add(chckbxGravidanza);
		
		chckbxFarmaci = new JCheckBox("Prende regolarmente farmaci o ne ha assunti di recente");
		chckbxFarmaci.setBounds(8, 130, 425, 23);
		pnlDatiGenerali.add(chckbxFarmaci);
		chckbxFarmaci.addItemListener(new ItemListener() {
		      public void itemStateChanged(ItemEvent e) {
		          if(chckbxFarmaci.isSelected())
		        	  textFieldFarmaci.setEnabled(true);
		          else 
		        	  textFieldFarmaci.setEnabled(false);
		        }
		      });
		
		JLabel lblFarmaciAssunti = new JLabel("Farmaci assunti");
		lblFarmaciAssunti.setBounds(32, 157, 119, 15);
		pnlDatiGenerali.add(lblFarmaciAssunti);
		
		textFieldFarmaci = new JTextField();
		textFieldFarmaci.setBounds(159, 155, 270, 26);
		pnlDatiGenerali.add(textFieldFarmaci);
		textFieldFarmaci.setEnabled(false);
		textFieldFarmaci.setColumns(10);
		
		chckbxInterventi = new JCheckBox("Ha subito di recente interventi chirurgici");
		chckbxInterventi.setBounds(8, 188, 376, 23);
		pnlDatiGenerali.add(chckbxInterventi);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Ha sofferto o soffre di", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(8, 220, 421, 167);
		pnlDatiGenerali.add(panel);
		
		chckbxMalattieSistemiche = new JCheckBox("Malattie sistemiche");
		chckbxMalattieSistemiche.setBounds(8, 25, 170, 23);
		panel.add(chckbxMalattieSistemiche);
		
		chckbxProblemiCardiaci = new JCheckBox("Problemi cardiaci");
		chckbxProblemiCardiaci.setBounds(8, 48, 170, 23);
		panel.add(chckbxProblemiCardiaci);
		
		chckbxIpertensione = new JCheckBox("Ipertensione");
		chckbxIpertensione.setBounds(8, 72, 129, 23);
		panel.add(chckbxIpertensione);
		
		chckbxDiabete = new JCheckBox("Diabete");
		chckbxDiabete.setBounds(8, 94, 87, 23);
		panel.add(chckbxDiabete);
		
		chckbxProblemiDiCoagulazione = new JCheckBox("Problemi di coagulazione");
		chckbxProblemiDiCoagulazione.setBounds(8, 119, 187, 23);
		panel.add(chckbxProblemiDiCoagulazione);
		
		chckbxAllergieParticolari = new JCheckBox("Allergie particolari");
		chckbxAllergieParticolari.setBounds(120, 94, 225, 23);
		panel.add(chckbxAllergieParticolari);
		
		lblConfermaDati = new JLabel("Dati inseriti con successo");
		lblConfermaDati.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfermaDati.setBounds(765, 432, 288, 26);
		lblConfermaDati.setVisible(false);
		panelAnamnesi.add(lblConfermaDati);

		btnInvia= new JButton("Invia");
		btnInvia.setBounds(630, 434, 117, 25);
		panelAnamnesi.add(btnInvia);
		btnInvia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(assertaDatiPersonali()) {
					//Combobox
					addProblemFact(cBoxSanguinamento, "facile sanguinamento alle gengive");
					addProblemFact(cBoxAlito, "un alito poco fresco");
					addProblemFact(cBoxMobilita, "un aumento della mobilita' dei denti");
					addProblemFact(cBoxRumoriArticolari, "rumori articolari aprendo la bocca");
					addProblemFact(cBoxSensibilita, "sensibilita' dentale");
					addProblemFact(cBoxTrisma, "difficolta' ad aprire la bocca");
					addProblemFact(cBoxDigrigna, "digrigna i denti durante la notte o il giorno");
					addProblemFact(cBoxEmicrania, "mal di testa");
					//Checkbox
					addGeneralFactCheck(chckbxBuonaSalute, "uno stato di buona salute");
					addGeneralFactCheck(chckbxStupefacenti, "abitudine a sostanze stupefacenti");
					addGeneralFactCheck(chckbxAlcolici, "abitudine a sostanze alcoliche");
					addGeneralFactCheck(chckbxAllergieParticolari, "allergie particolari");
					addGeneralFactCheck(chckbxFumatore, "abitudine al fumo");
					addGeneralFactCheck(chckbxGravidanza, "stato di gravidanza");
					addGeneralFactCheck(chckbxFarmaci, "assunzione di farmaci");
					addGeneralFactCheck(chckbxInterventi, "interventi");
					addGeneralFactCheck(chckbxMalattieSistemiche, "malattie sistemiche");
					addGeneralFactCheck(chckbxProblemiCardiaci, "problemi cardiaci");
					addGeneralFactCheck(chckbxIpertensione, "ipertensione");
					addGeneralFactCheck(chckbxDiabete, "diabete");
					addGeneralFactCheck(chckbxProblemiDiCoagulazione, "problemi di coagulazione sanguigna");
					//TextFields
					addGeneralFactText(textFieldSigarette, "numero sigarette fumate al giorno");
					addGeneralFactText(textFieldFarmaci, "tipi di farmaci assunti");
	
					btnInvia.setEnabled(false); // per evitare che vengano modificati i valori dopo l'invio
					lblConfermaDati.setVisible(true);
					for(Fact f : fatti) {
						rw.writeFact(f.getFact(), (int) Math.round(f.getProb())); //ste le probabilita' devono essere float non int!!!
					}
				}
			}
		});
		btnInvia.setEnabled(false);
		btnInvia.setFont(new Font("Tahoma", Font.PLAIN, 10));

	}
	
	private void abilitaInvio() {
		if(!txtNome.getText().isEmpty() && !txtCognome.getText().isEmpty() && !(cBoxSesso.getSelectedIndex() == -1)) //ste && sesso && anno?
			btnInvia.setEnabled(true);
		else
			btnInvia.setEnabled(false);
	}
	
	private void addProblemFact(JComboBox<String> comboBox, String problem) {
		if(!comboBox.getSelectedItem().equals(""))
			fatti.add(new Fact("presenta(" + nome_cognome + "," + problem + ")", getProb((String)comboBox.getSelectedItem())));
	}
	
	private void addGeneralFactCheck(JCheckBox checkbox, String problem) {
		if(checkbox.isSelected())
			fatti.add(new Fact("presenta(" + nome_cognome + "," + problem + ")", 1 ));
		else
			fatti.add(new Fact("presenta(" + nome_cognome + "," + problem + ")", 0 ));
	}
	
	private void addGeneralFactText(JTextField textfield, String problem) {
		if(!textfield.getText().isEmpty())
			fatti.add(new Fact("presenta(" + nome_cognome + "," + problem + ")", 1 ));
		else
			fatti.add(new Fact("presenta(" + nome_cognome + "," + problem + ")", 0 ));
		
	}
	
	
	
	protected boolean assertaDatiPersonali() {
		boolean ok = true;
		try {
			nome = txtNome.getText();
			cognome = txtCognome.getText();
			nome_cognome = nome + "_" + cognome; //costruzione della stringa al fine di migliorare la leggibilità dei fatti a seguire
			sesso = (String)cBoxSesso.getSelectedItem();

			Calendar oggi = Calendar.getInstance();	     
			int anno = java.lang.Integer.parseInt(txtAnno.getText());
			int mese = java.lang.Integer.parseInt(txtMese.getText());
			int giorno = java.lang.Integer.parseInt(txtGiorno.getText());

			Calendar dataNascita = Calendar.getInstance();
			dataNascita.clear();
			dataNascita.set(anno, mese -1, giorno);
			int anni = -1;
			Calendar clona = (Calendar) dataNascita.clone();
			while (!clona.after(oggi)) {
				clona.add(Calendar.YEAR, 1);
				anni++;
			}
			eta = anni;

			assertaEta();
			assertaSesso();
		} catch (Exception e2) {
			ok = false;
		}
		return ok;
	}
	
	private void assertaEta(){
		String fatto_0_40 = "presenta(" + nome_cognome + ",eta' inferiore a 40 anni)";
		String fatto_40_65 = "presenta(" + nome_cognome + ",eta' compresa tra 40 e 65 anni)";
		String fatto_over_65 = "presenta(" + nome_cognome + ",eta' superiore ai 65 anni)";
		if (eta < 40) {
			fatti.add(new Fact(fatto_0_40, 1));
			fatti.add(new Fact(fatto_40_65, 0));
			fatti.add(new Fact(fatto_over_65, 0));
		} else if (eta>=40 && eta <= 65) {
			fatti.add(new Fact(fatto_0_40, 0));
			fatti.add(new Fact(fatto_40_65, 1));
			fatti.add(new Fact(fatto_over_65, 0));
		} else if (eta> 65 ) {
			fatti.add(new Fact(fatto_0_40, 0));
			fatti.add(new Fact(fatto_40_65, 0));
			fatti.add(new Fact(fatto_over_65, 1));
		}
	}
	
	private void assertaSesso() {
		String fatto_M = "presenta(" + nome_cognome + ",sesso maschile)";
		String fatto_F = "presenta(" + nome_cognome + ",sesso femminile)";
		if (sesso == "Maschile") {
			fatti.add(new Fact(fatto_M, 1));
			fatti.add(new Fact(fatto_F, 0));
		} else if (sesso == "Femminile") {
			fatti.add(new Fact(fatto_M, 0));
			fatti.add(new Fact(fatto_F, 1));
		}
	}
	
	//Permette di asserire le problematiche del paziente, controllando il valore della combobox in input;
	private void assertaProblematiche(JComboBox<String> comboBox, String fatto) {
		if (comboBox.getSelectedItem() == "Vero") {
			rw.writeFact(fatto, 1);
		} else if (comboBox.getSelectedItem() == "Falso") {
			rw.writeFact(fatto, 0);
		}
	}
	
	private double getProb(String risposta) {
		if(risposta.equals("Vero"))
			return 1.0;
		else if(risposta.equals("Falso"))
			return 0.0;
		else
			return -1;
	}
}
