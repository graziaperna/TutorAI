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
import java.util.HashSet;
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


public class ClassPanelPaziente extends GenericPanel {
	private List<Fact> fatti = new LinkedList<Fact>();
	//HashSet<String> setFattiPazienteTrue = (HashSet<String>) MainEDES.setFattiTrue;
	//HashSet<String> setFattiPazienteFalse = (HashSet<String>) MainEDES.setFattiFalse;

	private JTextField txtNome;
	private JTextField txtGiorno;
	private JTextField txtMese;
	private JTextField txtAnno;
	private JTextField txtAltezza;
	private JTextField txtPeso;
	private JTextField lblLogPaziente;
	private JLabel lblPunteggioBMI;
	private JComboBox<String> cBoxAbbuffate;
	private JComboBox<String> cBoxVomito;
	private JComboBox<String> cBoxLassativi;
	private JComboBox<String> cBoxDCA;
	private JComboBox<String> cBoxSuicidio;
	private JTextField lblValoreBmi;
	private JTextField txtCognome;
	private JPanel pnlDatiPersonali;

	public String[] sceltaBox = {"","Vero","Falso"};

	public String nome,cognome, altezza, peso, stringBMI;
	/**Stringa adibita a contenere nome e cognome nel formato nome_congome, utile a costruire i fatti
	 * in maniera più leggibile, utilizzando i dati personali del paziente*/
	private static String nome_cognome;
	String sesso;
	double bmi = 0;
	int eta;
	private JLabel lblNomeInserito;
	private JLabel lblCognomeInserito;
	private JLabel lblEta;
	private JLabel lblSessoInserito;
	private JPanel panelDatiPaziente;
	private JComboBox<String> cBoxAbbConvulsive;
	private JComboBox cBoxSesso;
	private JPanel panelDomandeComportamentali;

	private JButton btnInvia;

	/**
	 * Create the panel.
	 */
	public ClassPanelPaziente(String title) {
		super(title);
		setBounds(33, 35, 735, 431);
		setLayout(null);

		JPanel panelPaziente = new JPanel();
		panelPaziente.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelPaziente.setLayout(null);
		panelPaziente.setBorder(new TitledBorder(null, "Paziente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPaziente.setBounds(10, 11, 706, 490);
		add(panelPaziente);

		pnlDatiPersonali = new JPanel();
		pnlDatiPersonali.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Anagrafica", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDatiPersonali.setBounds(16, 13, 245, 131);
		panelPaziente.add(pnlDatiPersonali);
		pnlDatiPersonali.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(8, 19, 59, 14);
		pnlDatiPersonali.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(72, 16, 154, 20);
		pnlDatiPersonali.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblDataDiNascita = new JLabel("Data di Nascita");
		lblDataDiNascita.setBounds(8, 72, 89, 14);
		pnlDatiPersonali.add(lblDataDiNascita);

		txtGiorno = new JTextField();
		txtGiorno.setToolTipText("giorno");
		txtGiorno.setBounds(121, 66, 30, 20);
		pnlDatiPersonali.add(txtGiorno);
		txtGiorno.setColumns(10);

		txtMese = new JTextField();
		txtMese.setToolTipText("mese");
		txtMese.setBounds(151, 66, 30, 20);
		pnlDatiPersonali.add(txtMese);
		txtMese.setColumns(10);

		txtAnno = new JTextField();
		txtAnno.setToolTipText("anno");
		txtAnno.setBounds(181, 66, 45, 20);
		pnlDatiPersonali.add(txtAnno);
		txtAnno.setColumns(10);

		JLabel lblSesso = new JLabel("Sesso");
		lblSesso.setBounds(8, 93, 46, 14);
		pnlDatiPersonali.add(lblSesso);

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(8, 44, 59, 14);
		pnlDatiPersonali.add(lblCognome);

		txtCognome = new JTextField();
		txtCognome.setColumns(10);
		txtCognome.setBounds(72, 41, 154, 20);
		pnlDatiPersonali.add(txtCognome);

		txtAnno.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				abilitaInvio();
			}
		});

		cBoxSesso = new JComboBox();
		cBoxSesso.setModel(new DefaultComboBoxModel(new String[] {"", "Maschile", "Femminile"}));
		cBoxSesso.setBounds(121, 93, 105, 20);
		pnlDatiPersonali.add(cBoxSesso);

		JPanel panelBMI = new JPanel();
		panelBMI.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "BMI (body mass index)", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelBMI.setBounds(298, 13, 187, 131);
		panelPaziente.add(panelBMI);
		panelBMI.setLayout(null);

		JLabel lblAltezza = new JLabel("Altezza (cm)");
		lblAltezza.setBounds(6, 20, 77, 14);
		panelBMI.add(lblAltezza);

		JLabel lblPesoAttuale = new JLabel("Peso Attuale (Kg)");
		lblPesoAttuale.setBounds(6, 48, 105, 14);
		panelBMI.add(lblPesoAttuale);

		JLabel lblBMI = new JLabel("Indice BMI");
		lblBMI.setBounds(6, 75, 77, 14);
		panelBMI.add(lblBMI);

		txtAltezza = new JTextField();
		txtAltezza.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
			}
		});
		txtAltezza.setBounds(121, 16, 55, 23);
		panelBMI.add(txtAltezza);
		txtAltezza.setColumns(10);

		txtPeso = new JTextField();
		txtPeso.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
			}
		});
		txtPeso.setBounds(121, 44, 55, 23);
		panelBMI.add(txtPeso);
		txtPeso.setColumns(10);

		lblValoreBmi = new JTextField("");
		lblValoreBmi.setBounds(121, 71, 55, 23);
		panelBMI.add(lblValoreBmi);
		lblValoreBmi.setOpaque(false);
		lblValoreBmi.setEditable(false);

		panelDomandeComportamentali = new JPanel();
		panelDomandeComportamentali.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Domande Comportamentali", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDomandeComportamentali.setBounds(16, 145, 676, 295);
		panelPaziente.add(panelDomandeComportamentali);
		panelDomandeComportamentali.setLayout(null);

		JLabel lblNewLabel = new JLabel("Negli ultimi tre mesi ti \u00E8 capitato di avere uno dei seguenti comportamenti?");
		lblNewLabel.setBounds(6, 20, 558, 14);
		panelDomandeComportamentali.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		JTextPane txtpnAvereDelle = new JTextPane();
		txtpnAvereDelle.setBounds(6, 38, 479, 34);
		panelDomandeComportamentali.add(txtpnAvereDelle);
		txtpnAvereDelle.setBackground(SystemColor.control);
		txtpnAvereDelle.setDisabledTextColor(Color.BLACK);
		txtpnAvereDelle.setEditable(false);
		txtpnAvereDelle.setText("1. Avere delle abbuffate, cio\u00E8 mangiare molto pi\u00F9 di quello che la maggior parte delle persone mangerebbe in circostanze simili?");

		cBoxAbbuffate = new JComboBox(sceltaBox);
		cBoxAbbuffate.setBounds(488, 43, 77, 20);
		panelDomandeComportamentali.add(cBoxAbbuffate);
		cBoxAbbuffate.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		cBoxAbbuffate.setSelectedIndex(0);

		JTextPane txtpnNelleAbbuffate = new JTextPane();
		txtpnNelleAbbuffate.setBounds(6, 75, 479, 34);
		panelDomandeComportamentali.add(txtpnNelleAbbuffate);
		txtpnNelleAbbuffate.setText("2. In pi\u00F9, hai anche la sensazione di non riuscire a fermarti una volta cominciato?");
		txtpnNelleAbbuffate.setEditable(false);
		txtpnNelleAbbuffate.setDisabledTextColor(Color.BLACK);
		txtpnNelleAbbuffate.setBackground(SystemColor.menu);

		cBoxAbbConvulsive = new JComboBox(sceltaBox);
		cBoxAbbConvulsive.setBounds(488, 80, 77, 20);
		panelDomandeComportamentali.add(cBoxAbbConvulsive);
		cBoxAbbConvulsive.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		cBoxAbbConvulsive.setSelectedIndex(0);


		JTextPane txtpnIndurtiIl = new JTextPane();
		txtpnIndurtiIl.setBounds(6, 112, 479, 34);
		panelDomandeComportamentali.add(txtpnIndurtiIl);
		txtpnIndurtiIl.setText("3. Indurti il vomito allo scopo di controllare il tuo peso o le tue forme corporee?");
		txtpnIndurtiIl.setEditable(false);
		txtpnIndurtiIl.setDisabledTextColor(Color.BLACK);
		txtpnIndurtiIl.setBackground(SystemColor.menu);

		cBoxVomito = new JComboBox(sceltaBox);
		cBoxVomito.setBounds(488, 117, 77, 20);
		panelDomandeComportamentali.add(cBoxVomito);
		cBoxVomito.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		cBoxVomito.setSelectedIndex(0);


		JTextPane txtpnFareUso = new JTextPane();
		txtpnFareUso.setBounds(6, 149, 479, 34);
		panelDomandeComportamentali.add(txtpnFareUso);
		txtpnFareUso.setText("4. Fare uso di lassativi per controllare il tuo peso o le tue forme corporee?");
		txtpnFareUso.setEditable(false);
		txtpnFareUso.setDisabledTextColor(Color.BLACK);
		txtpnFareUso.setBackground(SystemColor.menu);

		cBoxLassativi = new JComboBox(sceltaBox);
		cBoxLassativi.setBounds(488, 154, 77, 20);
		panelDomandeComportamentali.add(cBoxLassativi);
		cBoxLassativi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		cBoxLassativi.setSelectedIndex(0);


		JTextPane txtpnEssereIn = new JTextPane();
		txtpnEssereIn.setBounds(6, 186, 479, 34);
		panelDomandeComportamentali.add(txtpnEssereIn);
		txtpnEssereIn.setText("5. Essere in cura a causa di un disturbo dell\u2019alimentazione?");
		txtpnEssereIn.setEditable(false);
		txtpnEssereIn.setDisabledTextColor(Color.BLACK);
		txtpnEssereIn.setBackground(SystemColor.menu);

		cBoxDCA = new JComboBox(sceltaBox);
		cBoxDCA.setBounds(488, 191, 77, 20);
		panelDomandeComportamentali.add(cBoxDCA);
		cBoxDCA.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		cBoxDCA.setSelectedIndex(0);


		JTextPane txtpnPensareO = new JTextPane();
		txtpnPensareO.setBounds(6, 223, 479, 34);
		panelDomandeComportamentali.add(txtpnPensareO);
		txtpnPensareO.setText("6. Pensare o tentare di suicidarti?");
		txtpnPensareO.setEditable(false);
		txtpnPensareO.setDisabledTextColor(Color.BLACK);
		txtpnPensareO.setBackground(SystemColor.menu);

		cBoxSuicidio = new JComboBox(sceltaBox);
		cBoxSuicidio.setBounds(488, 228, 77, 20);
		panelDomandeComportamentali.add(cBoxSuicidio);
		cBoxSuicidio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		cBoxSuicidio.setSelectedIndex(0);


		panelDatiPaziente = new JPanel();
		panelDatiPaziente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dati Paziente", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDatiPaziente.setBounds(518, 13, 172, 131);
		panelPaziente.add(panelDatiPaziente);
		panelDatiPaziente.setLayout(null);

		lblPunteggioBMI = new JLabel("");
		lblPunteggioBMI.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblPunteggioBMI.setBounds(6, 100, 160, 14);
		panelDatiPaziente.add(lblPunteggioBMI);

		lblNomeInserito = new JLabel("Nome: ");
		lblNomeInserito.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNomeInserito.setBounds(6, 20, 154, 14);
		panelDatiPaziente.add(lblNomeInserito);
		lblNomeInserito.setEnabled(false);

		lblCognomeInserito = new JLabel("Cognome: ");
		lblCognomeInserito.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblCognomeInserito.setBounds(6, 40, 154, 14);
		panelDatiPaziente.add(lblCognomeInserito);
		lblCognomeInserito.setEnabled(false);

		lblEta = new JLabel("Eta': ");
		lblEta.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblEta.setBounds(6, 60, 154, 14);
		panelDatiPaziente.add(lblEta);
		lblEta.setEnabled(false);

		lblSessoInserito = new JLabel("Sesso:");
		lblSessoInserito.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblSessoInserito.setBounds(6, 80, 154, 14);
		panelDatiPaziente.add(lblSessoInserito);
		lblSessoInserito.setEnabled(false);

		lblLogPaziente = new JTextField("");
		lblLogPaziente.setEditable(false);
		lblLogPaziente.setForeground(Color.RED);
		lblLogPaziente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLogPaziente.setBounds(17, 440, 671, 37);
		panelPaziente.add(lblLogPaziente);
		
		btnInvia = new JButton("Invia");
		btnInvia.setBounds(280, 261, 87, 23);
		panelDomandeComportamentali.add(btnInvia);
		btnInvia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(assertaDatiPersonali()) {
					setBMI();
					addProblemFact(cBoxAbbuffate, "episodi di abbuffate");
					addProblemFact(cBoxAbbConvulsive, "mancanza di controllo nell atto di mangiare");
					addProblemFact(cBoxVomito, "episodi di vomito autoindotto");
					addProblemFact(cBoxLassativi, "uso di lassativi");
					addProblemFact(cBoxLassativi, "una vecchia situazione di cura per dca");
					addProblemFact(cBoxSuicidio, "pensieri o tentativi di suicidio");
					btnInvia.setEnabled(false); // per evitare che vengano modificati i valori dopo l'invio
					for(Fact f : fatti) {
						rw.writeFact(f.getFact(), (int)Math.round(f.getProb())); //ste le probabilita' devono essere float non int!!!
					}
				}
			}
		});
		btnInvia.setEnabled(false);
		btnInvia.setFont(new Font("Tahoma", Font.PLAIN, 10));
	}
	
	private void abilitaInvio() {
		if(!txtNome.getText().isEmpty() && !txtCognome.getText().isEmpty()) //ste && sesso && anno?
			btnInvia.setEnabled(true);
		else
			btnInvia.setEnabled(false);
	}
	
	private void addProblemFact(JComboBox<String> comboBox, String problem) {
		if(!comboBox.getSelectedItem().equals(""))
			fatti.add(new Fact("presenta(" + nome_cognome + "," + problem + ")", getProb((String)comboBox.getSelectedItem())));
	}

	protected boolean assertaDatiPersonali() {
		boolean ok = true;
		lblLogPaziente.setText(null);		
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

			lblNomeInserito.setText("Nome: "+nome);
			lblCognomeInserito.setText("Cognome: "+cognome);
			lblEta.setText("Eta': "+ eta);
			lblSessoInserito.setText("Sesso: "+sesso);

			lblNomeInserito.setEnabled(true);
			lblCognomeInserito.setEnabled(true);
			lblEta.setEnabled(true);
			lblSessoInserito.setEnabled(true);

			assertaEta();
			assertaSesso();
		} catch (Exception e2) {
			lblLogPaziente.setText("Errore - Valori non validi");
			ok = false;
		}
		return ok;
	}

	/**
	 * A seconda dell'età inserita dall'utente, invcoca il metodo adibito all'asserzione di tali informazioni
	 * nella base di conoscenza; le asserzioni prevedono l'utilizzo di nome e cognome del paziente nel formato 'nome_cognome' 
	 */
	private void assertaEta(){
		String fatto_0_2 = "eta(" + nome_cognome + ",tra_0_2_anni)";
		String fatto_3_11 = "eta(" + nome_cognome + ",tra_3_11_anni)";
		String fatto_12_25 = "eta(" + nome_cognome + ",tra_12_25_anni)";
		String fatto_over_25 = "eta(" + nome_cognome + ",maggiore_25_anni)";
		if (eta<=2) {
			fatti.add(new Fact(fatto_0_2, 1));
			fatti.add(new Fact(fatto_3_11, 0));
			fatti.add(new Fact(fatto_12_25, 0));
			fatti.add(new Fact(fatto_over_25, 0));
		} else if (eta>2 && eta < 12) {
			fatti.add(new Fact(fatto_0_2, 0));
			fatti.add(new Fact(fatto_3_11, 1));
			fatti.add(new Fact(fatto_12_25, 0));
			fatti.add(new Fact(fatto_over_25, 0));
		} else if (eta>=12 && eta <= 25) {
			fatti.add(new Fact(fatto_0_2, 0));
			fatti.add(new Fact(fatto_3_11, 0));
			fatti.add(new Fact(fatto_12_25, 1));
			fatti.add(new Fact(fatto_over_25, 0));
		} else if (eta> 25 ) {
			fatti.add(new Fact(fatto_0_2, 0));
			fatti.add(new Fact(fatto_3_11, 0));
			fatti.add(new Fact(fatto_12_25, 0));
			fatti.add(new Fact(fatto_over_25, 1));
		}
	}

	/**
	 * Controlla il genere del paziente e lo asserisce tramite il metodo assertaDettagliPaziente
	 */
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

	protected void setBMI() {
		lblLogPaziente.setText(null);
		lblPunteggioBMI.setText(null);
		try {
			int a = java.lang.Integer.parseInt(txtAltezza.getText());
			int p = java.lang.Integer.parseInt(txtPeso.getText());
			lblValoreBmi.setText(calcolaBMI(a,p));
			setLabelPunteggioBMI(lblPunteggioBMI);
			assertaBMIGravita();
		} catch (Exception e2) {
			lblLogPaziente.setText("Errore - Valori non validi");
		}		
	}

	public String calcolaBMI(double altezza, double peso){
		bmi = peso/((altezza/100) * (altezza/100));
		String bmiString = String.format("%.2f", bmi);
		return bmiString;
	}

	/**
	 * In base ai dati inseriti nella sezione dedicato al BMI, restituisce informazioni all'utente ed effettua le rispettive asserzioni
	 * @param label
	 */
	private void setLabelPunteggioBMI(JLabel label) {
		//settiamo delle stringhe che mantengano le informazioni necessarie all'asserzione
		String regolare = "peso_bmi("+ nome_cognome +",regolare)";
		String sovrappeso = "peso_bmi("+ nome_cognome +",sovrappeso)";
		String sottopeso = "peso_bmi("+ nome_cognome +",sottopeso)";
		//tutti casi sottopeso, quindi mettiamo l'asserzione alla fine ma differenziamo le visualizzazioni
		//a video nella label
		if (bmi<15.50 || (bmi>=15.50 && bmi<=18.49)) {
			if (bmi<15.50) {
				label.setText("Grave magrezza");
			} else if (bmi>=15.50 && bmi<=17.49) {
				label.setText("Visibilmente sottopeso");
			} else if (bmi>=17.5 && bmi<=18.49) {
				label.setText("Leggermente sottopeso");
			}
			fatti.add(new Fact(regolare, 0));
			fatti.add(new Fact(sovrappeso, 0));
			fatti.add(new Fact(sottopeso, 1));
		} else if (bmi>=18.5 && bmi<=24.9) {
			label.setText("Peso Regolare");
			fatti.add(new Fact(regolare, 1));
			fatti.add(new Fact(sovrappeso, 0));
			fatti.add(new Fact(sottopeso, 0));
		}
		//tutti casi sovrappeso, quindi mettiamo l'asserzione alla fine ma differenziamo le visualizzazioni
		//a video nella label
		else if (bmi>=25.0) {
			if (bmi>=25.0 && bmi<=29.9) {
				label.setText("Sovrappeso");
			} else if (bmi>=30.0 && bmi<=34.9) {
				label.setText("Obesità moderata");
			} else if (bmi>=35.00 && bmi<=39.9) {
				label.setText("Obesità grave");
			} else if(bmi>=40.00) {
				label.setText("Obesità gravissima");
			}
			fatti.add(new Fact(regolare, 0));
			fatti.add(new Fact(sovrappeso, 1));
			fatti.add(new Fact(sottopeso, 0));
		}
	}

	private void abilitaButton (JComboBox<String> comboBox, JButton button){
		if (comboBox.getSelectedItem() == "Vero") {
			button.setEnabled(true);
		} else if (comboBox.getSelectedItem() == "Falso") {
			button.setEnabled(true);
		} else {
			button.setEnabled(false);
		}	 
	}

	/**
	 * Permette di asserire le problematiche del paziente, controllando il valore della combobox in input;
	 * in base ad essa, l'asserzione sarà con probabilità pari a 0 o 1
	 * @param comboBox JComboBox di cui controllare il valore, per verificare se sia settato su vero o falso
	 * @param fatto
	 */
	private void assertaProblematiche(JComboBox<String> comboBox, String fatto) {
		if (comboBox.getSelectedItem() == "Vero") {
			rw.writeFact(fatto, 1);
		} else if (comboBox.getSelectedItem() == "Falso") {
			rw.writeFact(fatto, 0);
		}
	}

	private void assertaBMIGravita() {
		//predisposizione stringhe per le asserzioni
		String minore15 = "indice_bmi(" + nome_cognome +",minore_di_15)";
		String tra15e15_99 = "indice_bmi(" + nome_cognome +",compreso_tra_15_e_15_99)";
		String tra16e16_99 = "indice_bmi(" + nome_cognome +",compreso_tra_16_e_16_99)";
		String magg_ug_17 = "indice_bmi(" + nome_cognome +",maggiore_o_uguale_a_17)";
		if (bmi<15) {
			fatti.add(new Fact(minore15, 1));
			fatti.add(new Fact(tra15e15_99, 0));
			fatti.add(new Fact(tra16e16_99, 0));
			fatti.add(new Fact(magg_ug_17, 0));
		}
		else if (bmi>=15 && bmi<16) {
			fatti.add(new Fact(minore15, 0));
			fatti.add(new Fact(tra15e15_99, 1));
			fatti.add(new Fact(tra16e16_99, 0));
			fatti.add(new Fact(magg_ug_17, 0));
		}
		else if (bmi>=16 && bmi<17) {
			fatti.add(new Fact(minore15, 0));
			fatti.add(new Fact(tra15e15_99, 0));
			fatti.add(new Fact(tra16e16_99, 1));
			fatti.add(new Fact(magg_ug_17, 0));
		}
		else if (bmi>=17) {
			fatti.add(new Fact(minore15, 0));
			fatti.add(new Fact(tra15e15_99, 0));
			fatti.add(new Fact(tra16e16_99, 0));
			fatti.add(new Fact(magg_ug_17, 1));
		}
	}

	/**
	 * Restituisce il valore dell'attributo nome_cognome al fine di utilizzare tale informazione in altri panel
	 * @return valore dell'attributo nome_cognome 
	 */
	public static String getnome_cognome() {
		return nome_cognome;
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
