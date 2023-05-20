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

public class ClassPanelTutor extends GenericPanel{

	private List<Fact> fatti = new LinkedList<Fact>();

	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtGiorno;
	private JTextField txtMese;
	private JTextField txtAnno;
	private JComboBox cBoxSesso;
	private JPanel pnlDatiPersonali;

	//Combobox domande anamnesi odontoiatrica
	private JComboBox<String> cBox1;
	private JComboBox<String> cBox2;
	private JComboBox<String> cBox3;
	private JComboBox<String> cBox4;
	private JComboBox<String> cBox5;
	private JComboBox<String> cBox6;
	private JComboBox<String> cBox7;
	private JComboBox<String> cBox8;
	private JComboBox<String> cBox9;
	private JComboBox<String> cBox10;
	private JComboBox<String> cBox11;
	private JComboBox<String> cBox12;
	private JComboBox<String> cBox13;
	public String[] sceltaBox = {"","Vero","Falso"};


	public String nome, cognome, sesso;
	/**Stringa adibita a contenere nome e cognome nel formato nome_cognome, utile a costruire i fatti
	 * in maniera più leggibile, utilizzando i dati personali del paziente*/
	private static String nome_cognome;
	int eta;
	private JPanel panelDomandeTutor;
	private JButton btnInvia;
	private JLabel lblConfermaDati;

	public ClassPanelTutor() {
		super("Tutor");
		setBounds(33, 35, 1105, 580);
		setLayout(null);

		JPanel panelTutor = new JPanel();
		panelTutor.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelTutor.setLayout(null);
		panelTutor.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Tutor Intelligente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelTutor.setBounds(10, 11, 1150, 557);
		add(panelTutor);

		pnlDatiPersonali = new JPanel();
		pnlDatiPersonali.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dati studente", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDatiPersonali.setBounds(16, 23, 602, 99);
		panelTutor.add(pnlDatiPersonali);
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

		panelDomandeTutor = new JPanel();
		panelDomandeTutor.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Questionario", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDomandeTutor.setBounds(16, 134, 1130, 345);
		panelTutor.add(panelDomandeTutor);
		panelDomandeTutor.setLayout(null);

		JTextPane txtUnderline = new JTextPane();
		txtUnderline.setBounds(12, 23, 479, 34);
		panelDomandeTutor.add(txtUnderline);
		txtUnderline.setBackground(SystemColor.control);
		txtUnderline.setDisabledTextColor(Color.BLACK);
		txtUnderline.setEditable(false);
		txtUnderline.setText("1. Quando studi tendi a sottolineare i concetti fondamentali con vari colori? ");

		cBox1 = new JComboBox(sceltaBox);
		cBox1.setBounds(494, 28, 77, 20);
		panelDomandeTutor.add(cBox1);
		cBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});

		JTextPane txtAskForDoubt = new JTextPane();
		txtAskForDoubt.setBounds(12, 60, 479, 34);
		panelDomandeTutor.add(txtAskForDoubt);
		txtAskForDoubt.setBackground(SystemColor.control);
		txtAskForDoubt.setDisabledTextColor(Color.BLACK);
		txtAskForDoubt.setEditable(false);
		txtAskForDoubt.setText("2. Al termine dello studio chiedi ad altre persone di porti delle domande sull’argomento studiato? ");

		cBox2 = new JComboBox(sceltaBox);
		cBox2.setBounds(494, 65, 77, 20);
		panelDomandeTutor.add(cBox2);
		cBox2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		cBox2.setSelectedIndex(0);

		JTextPane txtEnigma = new JTextPane();
		txtEnigma.setBounds(12, 97, 479, 115);
		panelDomandeTutor.add(txtEnigma);
		txtEnigma.setBackground(SystemColor.control);
		txtEnigma.setDisabledTextColor(Color.BLACK);
		txtEnigma.setEditable(false);
		txtEnigma.setText("3. Il cadavere di una donna è ancora riverso sul marciapiede. Ci sono quattro uomini indiziati per l’omicidio. "
				+ "Alle prime domande della polizia rispondono: ANTONIO: “Ho visto Carlo e Dario sul luogo del delitto, quindi uno di loro è l’assassino."
				+ "” BERNARDO: “Non sono stato io.” CARLO: “E’ stato Dario. L’ho visto sparare!” DARIO: “E’ stato Bernardo. "
				+ "L’ho visto mentre fuggiva.”\n"
				+ "Sapendo che esattamente due tra le asserzioni dei quattro uomini sono false, chi è l'assassino?");

		cBox3 = new JComboBox(sceltaBox);
		cBox3.setBounds(494, 102, 77, 20);
		panelDomandeTutor.add(cBox3);
		cBox3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		cBox3.setSelectedIndex(0);

		JTextPane txtTypeSubject = new JTextPane();
		txtTypeSubject.setBounds(12, 218, 479, 34);
		panelDomandeTutor.add(txtTypeSubject);
		txtTypeSubject.setBackground(SystemColor.control);
		txtTypeSubject.setDisabledTextColor(Color.BLACK);
		txtTypeSubject.setEditable(false);
		txtTypeSubject.setText("4. Preferisci materie umanistiche o scientifiche?");

		cBox4 = new JComboBox(sceltaBox);
		cBox4.setBounds(494, 220, 77, 20);
		panelDomandeTutor.add(cBox4);
		cBox4.setSelectedIndex(0);

		JTextPane txtMusicPodcast = new JTextPane();
		txtMusicPodcast.setText("5. Ascolti podcast e musica?");
		txtMusicPodcast.setEditable(false);
		txtMusicPodcast.setDisabledTextColor(Color.BLACK);
		txtMusicPodcast.setBackground(SystemColor.window);
		txtMusicPodcast.setBounds(12, 258, 479, 34);
		panelDomandeTutor.add(txtMusicPodcast);

		cBox5 = new JComboBox(sceltaBox);
		cBox5.setSelectedIndex(0);
		cBox5.setBounds(494, 260, 77, 20);
		panelDomandeTutor.add(cBox5);

		JTextPane txtExperts = new JTextPane();
		txtExperts.setText("6. Credi che siano utili interventi di specialisti?");
		txtExperts.setEditable(false);
		txtExperts.setDisabledTextColor(Color.BLACK);
		txtExperts.setBackground(SystemColor.window);
		txtExperts.setBounds(575, 23, 420, 30);
		panelDomandeTutor.add(txtExperts);

//		cBox6 = new JComboBox(sceltaBox);
//		cBox6.setSelectedIndex(0);
//		cBox6.setBounds(494, 213, 77, 20);
//		panelDomandeTutor.add(cBox6);

		JTextPane txtDeepening = new JTextPane();
		txtDeepening.setText("7. Quando studi un argomento di solito sei interessato ad approfondire tale argomento?");
		txtDeepening.setEditable(false);
		txtDeepening.setDisabledTextColor(Color.BLACK);
		txtDeepening.setBackground(SystemColor.window);
		txtDeepening.setBounds(575, 60, 420, 34);
		panelDomandeTutor.add(txtDeepening);

//		cBox7 = new JComboBox(sceltaBox);
//		cBox7.setSelectedIndex(0);
//		cBox7.setBounds(494, 250, 77, 20);
//		panelDomandeTutor.add(cBox7);

		JTextPane txtQuestionToTeacher = new JTextPane();
		txtQuestionToTeacher.setText("8. Durante la spiegazione del docente sei propenso a interagire con lui e a fare domande?");
		txtQuestionToTeacher.setEditable(false);
		txtQuestionToTeacher.setDisabledTextColor(Color.BLACK);
		txtQuestionToTeacher.setBackground(SystemColor.window);
		txtQuestionToTeacher.setBounds(575, 100, 420, 34);
		panelDomandeTutor.add(txtQuestionToTeacher);
//
//		cBox8 = new JComboBox(sceltaBox);
//		cBox8.setSelectedIndex(0);
//		cBox8.setBounds(494, 287, 77, 20);
//		panelDomandeTutor.add(cBox8);

		JTextPane txtTest = new JTextPane();
		txtTest.setText("9. Preferisci le verifiche settimanali o semestrale?");
		txtTest.setEditable(false);
		txtTest.setDisabledTextColor(Color.BLACK);
		txtTest.setBackground(SystemColor.window);
		txtTest.setBounds(575, 140, 420, 34);
		panelDomandeTutor.add(txtTest);
//
//		cBox9= new JComboBox(sceltaBox);
//		cBox9.setSelectedIndex(0);
//		cBox9.setBounds(494, 287, 77, 20);
//		panelDomandeTutor.add(cBox9);

		JTextPane txtBookEbook = new JTextPane();
		txtBookEbook.setText("10. Preferisci i libri cartacei o gli ebook?");
		txtBookEbook.setEditable(false);
		txtBookEbook.setDisabledTextColor(Color.BLACK);
		txtBookEbook.setBackground(SystemColor.window);
		txtBookEbook.setBounds(575, 180, 420, 34);
		panelDomandeTutor.add(txtBookEbook);

//		cBox10 = new JComboBox(sceltaBox);
//		cBox10.setSelectedIndex(0);
//		cBox10.setBounds(494, 287, 77, 20);
//		panelDomandeTutor.add(cBox10);

		JTextPane txtBookAudioBook= new JTextPane();
		txtBookAudioBook.setText("11. Preferisci libri cartacei/documenti o audiolibri?");
		txtBookAudioBook.setEditable(false);
		txtBookAudioBook.setDisabledTextColor(Color.BLACK);
		txtBookAudioBook.setBackground(SystemColor.window);
		txtBookAudioBook.setBounds(575, 220, 420, 34);
		panelDomandeTutor.add(txtBookAudioBook);
//
//		cBox11 = new JComboBox(sceltaBox);
//		cBox11.setSelectedIndex(0);
//		cBox11.setBounds(494, 287, 77, 20);
//		panelDomandeTutor.add(cBox11);

		JTextPane txtComputer = new JTextPane();
		txtComputer.setText("12. Quanto spesso usi il computer?");
		txtComputer.setEditable(false);
		txtComputer.setDisabledTextColor(Color.BLACK);
		txtComputer.setBackground(SystemColor.window);
		txtComputer.setBounds(575, 260, 420, 34);
		panelDomandeTutor.add(txtComputer);

//		cBox12 = new JComboBox(sceltaBox);
//		cBox12.setSelectedIndex(0);
//		cBox12.setBounds(494, 287, 77, 20);
//		panelDomandeTutor.add(cBox12);

		JTextPane txtExerciseTeacherStudent = new JTextPane();
		txtExerciseTeacherStudent.setText("13. Durante lo svolgimento degli esercizi in classe preferisci che li svolga il docente o gli studenti?");
		txtExerciseTeacherStudent.setEditable(false);
		txtExerciseTeacherStudent.setDisabledTextColor(Color.BLACK);
		txtExerciseTeacherStudent.setBackground(SystemColor.window);
		txtExerciseTeacherStudent.setBounds(575, 300, 420, 34);
		panelDomandeTutor.add(txtExerciseTeacherStudent);
//		
//		cBox13 = new JComboBox(sceltaBox);
//		cBox13.setSelectedIndex(0);
//		cBox13.setBounds(494, 287, 77, 20);
//		panelDomandeTutor.add(cBox13);

		lblConfermaDati = new JLabel("Dati inseriti con successo");
		lblConfermaDati.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfermaDati.setBounds(765, 432, 288, 26);
		lblConfermaDati.setVisible(false);
		panelTutor.add(lblConfermaDati);

		btnInvia= new JButton("Invia");
		btnInvia.setBounds(630, 434, 117, 25);
		panelTutor.add(btnInvia);
		btnInvia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(assertaDatiPersonali()) {
					//Combobox
					addProblemFact(cBox1, "");
					addProblemFact(cBox2, "");
					addProblemFact(cBox3, "");
					addProblemFact(cBox4, "");
					addProblemFact(cBox5, "");
					addProblemFact(cBox6, "");
					addProblemFact(cBox7, "");
					addProblemFact(cBox8, "");
					addProblemFact(cBox9, "");
					addProblemFact(cBox10, "");
					addProblemFact(cBox11, "");
					addProblemFact(cBox12, "");
					addProblemFact(cBox13, "");

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
