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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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
	 * in maniera pi� leggibile, utilizzando i dati personali del paziente*/
	private static String nome_cognome;
	int eta;
	private JPanel panelDomandeTutor;
	private JButton btnInvia;
	private JLabel lblConfermaDati;
	private JScrollPane scrollPane;

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
		
				lblConfermaDati = new JLabel("Dati inseriti con successo");
				lblConfermaDati.setForeground(new Color(255, 0, 0));
				lblConfermaDati.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblConfermaDati.setHorizontalAlignment(SwingConstants.LEFT);
				lblConfermaDati.setBounds(642, 480, 288, 26);
				lblConfermaDati.setVisible(false);
						
						scrollPane = new JScrollPane();
						scrollPane.setViewportBorder(null);
						scrollPane.setBounds(343, 74, 703, 378);
						panelTutor.add(scrollPane);
						
								panelDomandeTutor = new JPanel();
								panelDomandeTutor.setAlignmentX(Component.LEFT_ALIGNMENT);
								scrollPane.setViewportView(panelDomandeTutor);
								panelDomandeTutor.setBackground(new Color(255, 255, 255));
								panelDomandeTutor.setBorder(null);
																																																																																														GridBagLayout gbl_panelDomandeTutor = new GridBagLayout();
																																																																																														gbl_panelDomandeTutor.columnWidths = new int[] {1, 2};
																																																																																														gbl_panelDomandeTutor.rowHeights = new int[] {20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 0};
																																																																																														gbl_panelDomandeTutor.columnWeights = new double[]{0.0, 0.0};
																																																																																														gbl_panelDomandeTutor.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
																																																																																														panelDomandeTutor.setLayout(gbl_panelDomandeTutor);
																																																																																																				
																																																																																																						JTextPane txtUnderline = new JTextPane();
																																																																																																						txtUnderline.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																						GridBagConstraints gbc_txtUnderline = new GridBagConstraints();
																																																																																																						gbc_txtUnderline.fill = GridBagConstraints.BOTH;
																																																																																																						gbc_txtUnderline.weighty = 0.5;
																																																																																																						gbc_txtUnderline.weightx = 0.5;
																																																																																																						gbc_txtUnderline.insets = new Insets(0, 0, 5, 0);
																																																																																																						gbc_txtUnderline.gridx = 0;
																																																																																																						gbc_txtUnderline.gridy = 1;
																																																																																																						panelDomandeTutor.add(txtUnderline, gbc_txtUnderline);
																																																																																																						txtUnderline.setBackground(new Color(255, 255, 255));
																																																																																																						txtUnderline.setDisabledTextColor(Color.BLACK);
																																																																																																						txtUnderline.setEditable(false);
																																																																																																						txtUnderline.setText("1. Quando studi tendi a sottolineare i concetti fondamentali con vari colori?");
																																																																																																		
																																																																																																				cBox1 = new JComboBox(sceltaBox);
																																																																																																				cBox1.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																				cBox1.setModel(new DefaultComboBoxModel(new String[] {"", "Si", "No"}));
																																																																																																				GridBagConstraints gbc_cBox1 = new GridBagConstraints();
																																																																																																				gbc_cBox1.weighty = 0.5;
																																																																																																				gbc_cBox1.gridwidth = 0;
																																																																																																				gbc_cBox1.fill = GridBagConstraints.HORIZONTAL;
																																																																																																				gbc_cBox1.weightx = 0.5;
																																																																																																				gbc_cBox1.insets = new Insets(0, 0, 5, 0);
																																																																																																				gbc_cBox1.gridx = 1;
																																																																																																				gbc_cBox1.gridy = 1;
																																																																																																				panelDomandeTutor.add(cBox1, gbc_cBox1);
																																																																																																				cBox1.addItemListener(new ItemListener() {
																																																																																																					public void itemStateChanged(ItemEvent e) {
																																																																																																					}
																																																																																																				});
																																																																																																				
																																																																																																						JTextPane txtDeepening = new JTextPane();
																																																																																																						txtDeepening.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																						txtDeepening.setText("7. Quando studi un argomento di solito sei interessato ad approfondire tale argomento?");
																																																																																																						txtDeepening.setEditable(false);
																																																																																																						txtDeepening.setDisabledTextColor(Color.BLACK);
																																																																																																						txtDeepening.setBackground(SystemColor.window);
																																																																																																						GridBagConstraints gbc_txtDeepening = new GridBagConstraints();
																																																																																																						gbc_txtDeepening.fill = GridBagConstraints.BOTH;
																																																																																																						gbc_txtDeepening.weighty = 0.5;
																																																																																																						gbc_txtDeepening.weightx = 0.5;
																																																																																																						gbc_txtDeepening.insets = new Insets(0, 0, 5, 0);
																																																																																																						gbc_txtDeepening.gridx = 0;
																																																																																																						gbc_txtDeepening.gridy = 7;
																																																																																																						panelDomandeTutor.add(txtDeepening, gbc_txtDeepening);
																																																																																																				
																																																																																																						cBox7 = new JComboBox(sceltaBox);
																																																																																																						cBox7.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																						cBox7.setModel(new DefaultComboBoxModel(new String[] {"", "Si", "No", "A volte"}));
																																																																																																						cBox7.setSelectedIndex(0);
																																																																																																						GridBagConstraints gbc_cBox7 = new GridBagConstraints();
																																																																																																						gbc_cBox7.fill = GridBagConstraints.HORIZONTAL;
																																																																																																						gbc_cBox7.insets = new Insets(0, 0, 5, 0);
																																																																																																						gbc_cBox7.gridx = 1;
																																																																																																						gbc_cBox7.gridy = 7;
																																																																																																						panelDomandeTutor.add(cBox7, gbc_cBox7);
																																																																																																		
																																																																																																				JTextPane txtAskForDoubt = new JTextPane();
																																																																																																				txtAskForDoubt.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																				GridBagConstraints gbc_txtAskForDoubt = new GridBagConstraints();
																																																																																																				gbc_txtAskForDoubt.fill = GridBagConstraints.BOTH;
																																																																																																				gbc_txtAskForDoubt.weighty = 0.5;
																																																																																																				gbc_txtAskForDoubt.weightx = 0.5;
																																																																																																				gbc_txtAskForDoubt.insets = new Insets(0, 0, 5, 0);
																																																																																																				gbc_txtAskForDoubt.gridx = 0;
																																																																																																				gbc_txtAskForDoubt.gridy = 2;
																																																																																																				panelDomandeTutor.add(txtAskForDoubt, gbc_txtAskForDoubt);
																																																																																																				txtAskForDoubt.setBackground(new Color(255, 255, 255));
																																																																																																				txtAskForDoubt.setDisabledTextColor(Color.BLACK);
																																																																																																				txtAskForDoubt.setEditable(false);
																																																																																																				txtAskForDoubt.setText("2. Al termine dello studio chiedi ad altre persone di porti delle domande sull'argomento studiato?");
																																																																																																		
																																																																																																				cBox2 = new JComboBox(sceltaBox);
																																																																																																				cBox2.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																				GridBagConstraints gbc_cBox2 = new GridBagConstraints();
																																																																																																				gbc_cBox2.fill = GridBagConstraints.HORIZONTAL;
																																																																																																				gbc_cBox2.insets = new Insets(0, 0, 5, 0);
																																																																																																				gbc_cBox2.gridx = 1;
																																																																																																				gbc_cBox2.gridy = 2;
																																																																																																				panelDomandeTutor.add(cBox2, gbc_cBox2);
																																																																																																				cBox2.addItemListener(new ItemListener() {
																																																																																																					public void itemStateChanged(ItemEvent e) {
																																																																																																					}
																																																																																																				});
																																																																																																				cBox2.setModel(new DefaultComboBoxModel(new String[] {"", "Si", "No", "A volte"}));
																																																																																																				cBox2.setSelectedIndex(0);
																																																																																																
																																																																																																		JTextPane txtQuestionToTeacher = new JTextPane();
																																																																																																		txtQuestionToTeacher.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																		txtQuestionToTeacher.setText("8. Durante la spiegazione del docente sei propenso a interagire con lui e a fare domande?");
																																																																																																		txtQuestionToTeacher.setEditable(false);
																																																																																																		txtQuestionToTeacher.setDisabledTextColor(Color.BLACK);
																																																																																																		txtQuestionToTeacher.setBackground(new Color(255, 255, 255));
																																																																																																		GridBagConstraints gbc_txtQuestionToTeacher = new GridBagConstraints();
																																																																																																		gbc_txtQuestionToTeacher.fill = GridBagConstraints.BOTH;
																																																																																																		gbc_txtQuestionToTeacher.weighty = 0.5;
																																																																																																		gbc_txtQuestionToTeacher.weightx = 0.5;
																																																																																																		gbc_txtQuestionToTeacher.insets = new Insets(0, 0, 5, 0);
																																																																																																		gbc_txtQuestionToTeacher.gridx = 0;
																																																																																																		gbc_txtQuestionToTeacher.gridy = 8;
																																																																																																		panelDomandeTutor.add(txtQuestionToTeacher, gbc_txtQuestionToTeacher);
																																																																																																		
																																																																																																		cBox8= new JComboBox(sceltaBox);
																																																																																																		cBox8.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																		cBox8.setModel(new DefaultComboBoxModel(new String[] {"", "Si No"}));
																																																																																																		cBox8.setSelectedIndex(0);
																																																																																																		GridBagConstraints gbc_cBox8 = new GridBagConstraints();
																																																																																																		gbc_cBox8.fill = GridBagConstraints.HORIZONTAL;
																																																																																																		gbc_cBox8.insets = new Insets(0, 0, 5, 0);
																																																																																																		gbc_cBox8.gridx = 1;
																																																																																																		gbc_cBox8.gridy = 8;
																																																																																																		panelDomandeTutor.add(cBox8, gbc_cBox8);
																																																																																																		
																																																																																																				JTextPane txtEnigma = new JTextPane();
																																																																																																				txtEnigma.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																				GridBagConstraints gbc_txtEnigma = new GridBagConstraints();
																																																																																																				gbc_txtEnigma.fill = GridBagConstraints.BOTH;
																																																																																																				gbc_txtEnigma.weighty = 0.5;
																																																																																																				gbc_txtEnigma.weightx = 0.5;
																																																																																																				gbc_txtEnigma.insets = new Insets(0, 0, 5, 0);
																																																																																																				gbc_txtEnigma.gridx = 0;
																																																																																																				gbc_txtEnigma.gridy = 3;
																																																																																																				panelDomandeTutor.add(txtEnigma, gbc_txtEnigma);
																																																																																																				txtEnigma.setBackground(new Color(255, 255, 255));
																																																																																																				txtEnigma.setDisabledTextColor(Color.BLACK);
																																																																																																				txtEnigma.setEditable(false);
																																																																																																				txtEnigma.setText("3. Il cadavere di una donna e' ancora riverso sul marciapiede. \r\nCi sono quattro uomini indiziati per l'omicidio. \r\nAlle prime domande della polizia rispondono: \r\nANTONIO: \"Ho visto Carlo e Dario sul luogo del delitto, quindi uno di loro e' l'assassino.\" \r\nBERNARDO: \"Non sono stato io.\" \r\nCARLO: \"E' stato Dario. L'ho visto sparare!\" \r\nDARIO: \"E' stato Bernardo. L'ho visto mentre fuggiva.\"\r\nSapendo che esattamente due tra le asserzioni dei quattro uomini sono false, chi e' l'assassino?");
																																																																																																		
																																																																																																				cBox3 = new JComboBox(sceltaBox);
																																																																																																				cBox3.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																				GridBagConstraints gbc_cBox3 = new GridBagConstraints();
																																																																																																				gbc_cBox3.fill = GridBagConstraints.HORIZONTAL;
																																																																																																				gbc_cBox3.insets = new Insets(0, 0, 5, 0);
																																																																																																				gbc_cBox3.gridx = 1;
																																																																																																				gbc_cBox3.gridy = 3;
																																																																																																				panelDomandeTutor.add(cBox3, gbc_cBox3);
																																																																																																				cBox3.addItemListener(new ItemListener() {
																																																																																																					public void itemStateChanged(ItemEvent e) {
																																																																																																					}
																																																																																																				});
																																																																																																				cBox3.setModel(new DefaultComboBoxModel(new String[] {"", "Antonio", "Bernardo", "Carlo", "Dario"}));
																																																																																																				cBox3.setSelectedIndex(0);
																																																																																																
																																																																																																		JTextPane txtTest = new JTextPane();
																																																																																																		txtTest.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																		txtTest.setText("9. Preferisci le verifiche settimanali o semestrali?");
																																																																																																		txtTest.setEditable(false);
																																																																																																		txtTest.setDisabledTextColor(Color.BLACK);
																																																																																																		txtTest.setBackground(SystemColor.window);
																																																																																																		GridBagConstraints gbc_txtTest = new GridBagConstraints();
																																																																																																		gbc_txtTest.fill = GridBagConstraints.BOTH;
																																																																																																		gbc_txtTest.weighty = 0.5;
																																																																																																		gbc_txtTest.weightx = 0.5;
																																																																																																		gbc_txtTest.insets = new Insets(0, 0, 5, 0);
																																																																																																		gbc_txtTest.gridx = 0;
																																																																																																		gbc_txtTest.gridy = 9;
																																																																																																		panelDomandeTutor.add(txtTest, gbc_txtTest);
																																																																																																								
																																																																																																										cBox9= new JComboBox(sceltaBox);
																																																																																																										cBox9.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																										cBox9.setModel(new DefaultComboBoxModel(new String[] {"", "Settimanali", "Semestrali"}));
																																																																																																										cBox9.setSelectedIndex(0);
																																																																																																										GridBagConstraints gbc_cBox9 = new GridBagConstraints();
																																																																																																										gbc_cBox9.fill = GridBagConstraints.HORIZONTAL;
																																																																																																										gbc_cBox9.insets = new Insets(0, 0, 5, 0);
																																																																																																										gbc_cBox9.gridx = 1;
																																																																																																										gbc_cBox9.gridy = 9;
																																																																																																										panelDomandeTutor.add(cBox9, gbc_cBox9);
																																																																																																						
																																																																																																								JTextPane txtBookEbook = new JTextPane();
																																																																																																								txtBookEbook.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																								txtBookEbook.setText("10. Preferisci i libri cartacei o gli ebook?");
																																																																																																								txtBookEbook.setEditable(false);
																																																																																																								txtBookEbook.setDisabledTextColor(Color.BLACK);
																																																																																																								txtBookEbook.setBackground(SystemColor.window);
																																																																																																								GridBagConstraints gbc_txtBookEbook = new GridBagConstraints();
																																																																																																								gbc_txtBookEbook.fill = GridBagConstraints.BOTH;
																																																																																																								gbc_txtBookEbook.weighty = 0.5;
																																																																																																								gbc_txtBookEbook.weightx = 0.5;
																																																																																																								gbc_txtBookEbook.insets = new Insets(0, 0, 5, 0);
																																																																																																								gbc_txtBookEbook.gridx = 0;
																																																																																																								gbc_txtBookEbook.gridy = 10;
																																																																																																								panelDomandeTutor.add(txtBookEbook, gbc_txtBookEbook);
																																																																																																				
																																																																																																						cBox10 = new JComboBox(sceltaBox);
																																																																																																						cBox10.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																						cBox10.setModel(new DefaultComboBoxModel(new String[] {"", "Cartacei", "Ebook"}));
																																																																																																						cBox10.setSelectedIndex(0);
																																																																																																						GridBagConstraints gbc_cBox10 = new GridBagConstraints();
																																																																																																						gbc_cBox10.fill = GridBagConstraints.HORIZONTAL;
																																																																																																						gbc_cBox10.insets = new Insets(0, 0, 5, 0);
																																																																																																						gbc_cBox10.gridx = 1;
																																																																																																						gbc_cBox10.gridy = 10;
																																																																																																						panelDomandeTutor.add(cBox10, gbc_cBox10);
																																																																																																						
																																																																																																								JTextPane txtBookAudioBook= new JTextPane();
																																																																																																								txtBookAudioBook.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																								txtBookAudioBook.setText("11. Preferisci libri cartacei/documenti o audiolibri?");
																																																																																																								txtBookAudioBook.setEditable(false);
																																																																																																								txtBookAudioBook.setDisabledTextColor(Color.BLACK);
																																																																																																								txtBookAudioBook.setBackground(SystemColor.window);
																																																																																																								GridBagConstraints gbc_txtBookAudioBook = new GridBagConstraints();
																																																																																																								gbc_txtBookAudioBook.fill = GridBagConstraints.BOTH;
																																																																																																								gbc_txtBookAudioBook.weighty = 0.5;
																																																																																																								gbc_txtBookAudioBook.weightx = 0.5;
																																																																																																								gbc_txtBookAudioBook.insets = new Insets(0, 0, 5, 0);
																																																																																																								gbc_txtBookAudioBook.gridx = 0;
																																																																																																								gbc_txtBookAudioBook.gridy = 11;
																																																																																																								panelDomandeTutor.add(txtBookAudioBook, gbc_txtBookAudioBook);
																																																																																																				
																																																																																																						cBox11 = new JComboBox(sceltaBox);
																																																																																																						cBox11.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																						cBox11.setModel(new DefaultComboBoxModel(new String[] {"", "Documenti", "Audiolibri"}));
																																																																																																						cBox11.setSelectedIndex(0);
																																																																																																						GridBagConstraints gbc_cBox11 = new GridBagConstraints();
																																																																																																						gbc_cBox11.fill = GridBagConstraints.HORIZONTAL;
																																																																																																						gbc_cBox11.insets = new Insets(0, 0, 5, 0);
																																																																																																						gbc_cBox11.gridx = 1;
																																																																																																						gbc_cBox11.gridy = 11;
																																																																																																						panelDomandeTutor.add(cBox11, gbc_cBox11);
																																																																																																				
																																																																																																						JTextPane txtComputer = new JTextPane();
																																																																																																						txtComputer.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																						txtComputer.setText("12. Quanto spesso usi il computer?");
																																																																																																						txtComputer.setEditable(false);
																																																																																																						txtComputer.setDisabledTextColor(Color.BLACK);
																																																																																																						txtComputer.setBackground(SystemColor.window);
																																																																																																						GridBagConstraints gbc_txtComputer = new GridBagConstraints();
																																																																																																						gbc_txtComputer.fill = GridBagConstraints.BOTH;
																																																																																																						gbc_txtComputer.weighty = 0.5;
																																																																																																						gbc_txtComputer.weightx = 0.5;
																																																																																																						gbc_txtComputer.insets = new Insets(0, 0, 5, 0);
																																																																																																						gbc_txtComputer.gridx = 0;
																																																																																																						gbc_txtComputer.gridy = 12;
																																																																																																						panelDomandeTutor.add(txtComputer, gbc_txtComputer);
																																																																																																		
																																																																																																				cBox12 = new JComboBox(sceltaBox);
																																																																																																				cBox12.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																				cBox12.setModel(new DefaultComboBoxModel(new String[] {"", "Ogni giorno", "Ogni 3 giorni", "Ogni 5 giorni", "Nessuna di queste"}));
																																																																																																				cBox12.setSelectedIndex(0);
																																																																																																				GridBagConstraints gbc_cBox12 = new GridBagConstraints();
																																																																																																				gbc_cBox12.fill = GridBagConstraints.HORIZONTAL;
																																																																																																				gbc_cBox12.insets = new Insets(0, 0, 5, 0);
																																																																																																				gbc_cBox12.gridx = 1;
																																																																																																				gbc_cBox12.gridy = 12;
																																																																																																				panelDomandeTutor.add(cBox12, gbc_cBox12);
																																																																																																		
																																																																																																				JTextPane txtTypeSubject = new JTextPane();
																																																																																																				txtTypeSubject.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																				GridBagConstraints gbc_txtTypeSubject = new GridBagConstraints();
																																																																																																				gbc_txtTypeSubject.fill = GridBagConstraints.BOTH;
																																																																																																				gbc_txtTypeSubject.weighty = 0.5;
																																																																																																				gbc_txtTypeSubject.weightx = 0.5;
																																																																																																				gbc_txtTypeSubject.insets = new Insets(0, 0, 5, 0);
																																																																																																				gbc_txtTypeSubject.gridx = 0;
																																																																																																				gbc_txtTypeSubject.gridy = 4;
																																																																																																				panelDomandeTutor.add(txtTypeSubject, gbc_txtTypeSubject);
																																																																																																				txtTypeSubject.setBackground(new Color(255, 255, 255));
																																																																																																				txtTypeSubject.setDisabledTextColor(Color.BLACK);
																																																																																																				txtTypeSubject.setEditable(false);
																																																																																																				txtTypeSubject.setText("4. Preferisci materie umanistiche o scientifiche?");
																																																																																																				
																																																																																																						cBox4 = new JComboBox(sceltaBox);
																																																																																																						cBox4.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																						GridBagConstraints gbc_cBox4 = new GridBagConstraints();
																																																																																																						gbc_cBox4.fill = GridBagConstraints.HORIZONTAL;
																																																																																																						gbc_cBox4.insets = new Insets(0, 0, 5, 0);
																																																																																																						gbc_cBox4.gridx = 1;
																																																																																																						gbc_cBox4.gridy = 4;
																																																																																																						panelDomandeTutor.add(cBox4, gbc_cBox4);
																																																																																																						cBox4.setModel(new DefaultComboBoxModel(new String[] {"", "Umanistiche", "Scientifiche"}));
																																																																																																						cBox4.setSelectedIndex(0);
																																																																																																		
																																																																																																				JTextPane txtExerciseTeacherStudent = new JTextPane();
																																																																																																				txtExerciseTeacherStudent.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																				txtExerciseTeacherStudent.setText("13. Durante lo svolgimento degli esercizi in classe preferisci che li svolga il docente o gli studenti?");
																																																																																																				txtExerciseTeacherStudent.setEditable(false);
																																																																																																				txtExerciseTeacherStudent.setDisabledTextColor(Color.BLACK);
																																																																																																				txtExerciseTeacherStudent.setBackground(SystemColor.window);
																																																																																																				GridBagConstraints gbc_txtExerciseTeacherStudent = new GridBagConstraints();
																																																																																																				gbc_txtExerciseTeacherStudent.fill = GridBagConstraints.BOTH;
																																																																																																				gbc_txtExerciseTeacherStudent.weighty = 0.5;
																																																																																																				gbc_txtExerciseTeacherStudent.weightx = 0.5;
																																																																																																				gbc_txtExerciseTeacherStudent.insets = new Insets(0, 0, 5, 0);
																																																																																																				gbc_txtExerciseTeacherStudent.gridx = 0;
																																																																																																				gbc_txtExerciseTeacherStudent.gridy = 13;
																																																																																																				panelDomandeTutor.add(txtExerciseTeacherStudent, gbc_txtExerciseTeacherStudent);
																																																																																																		
																																																																																																		cBox13 = new JComboBox(sceltaBox);
																																																																																																		cBox13.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																		cBox13.setModel(new DefaultComboBoxModel(new String[] {"", "Docente", "Studente"}));
																																																																																																		cBox13.setSelectedIndex(0);
																																																																																																		GridBagConstraints gbc_cBox13 = new GridBagConstraints();
																																																																																																		gbc_cBox13.fill = GridBagConstraints.HORIZONTAL;
																																																																																																		gbc_cBox13.insets = new Insets(0, 0, 5, 0);
																																																																																																		gbc_cBox13.gridx = 1;
																																																																																																		gbc_cBox13.gridy = 13;
																																																																																																		panelDomandeTutor.add(cBox13, gbc_cBox13);
																																																																																																		
																																																																																																				JTextPane txtMusicPodcast = new JTextPane();
																																																																																																				txtMusicPodcast.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																				txtMusicPodcast.setText("5. Ascolti podcast e musica?");
																																																																																																				txtMusicPodcast.setEditable(false);
																																																																																																				txtMusicPodcast.setDisabledTextColor(Color.BLACK);
																																																																																																				txtMusicPodcast.setBackground(new Color(255, 255, 255));
																																																																																																				GridBagConstraints gbc_txtMusicPodcast = new GridBagConstraints();
																																																																																																				gbc_txtMusicPodcast.fill = GridBagConstraints.BOTH;
																																																																																																				gbc_txtMusicPodcast.weighty = 0.5;
																																																																																																				gbc_txtMusicPodcast.weightx = 0.5;
																																																																																																				gbc_txtMusicPodcast.insets = new Insets(0, 0, 5, 0);
																																																																																																				gbc_txtMusicPodcast.gridx = 0;
																																																																																																				gbc_txtMusicPodcast.gridy = 5;
																																																																																																				panelDomandeTutor.add(txtMusicPodcast, gbc_txtMusicPodcast);
																																																																																																
																																																																																																		cBox5 = new JComboBox(sceltaBox);
																																																																																																		cBox5.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																		cBox5.setModel(new DefaultComboBoxModel(new String[] {"", "Si", "No"}));
																																																																																																		cBox5.setSelectedIndex(0);
																																																																																																		GridBagConstraints gbc_cBox5 = new GridBagConstraints();
																																																																																																		gbc_cBox5.fill = GridBagConstraints.HORIZONTAL;
																																																																																																		gbc_cBox5.insets = new Insets(0, 0, 5, 0);
																																																																																																		gbc_cBox5.gridx = 1;
																																																																																																		gbc_cBox5.gridy = 5;
																																																																																																		panelDomandeTutor.add(cBox5, gbc_cBox5);
																																																																																														
																																																																																																JTextPane txtExperts = new JTextPane();
																																																																																																txtExperts.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																txtExperts.setText("6. Credi che siano utili interventi di specialisti?");
																																																																																																txtExperts.setEditable(false);
																																																																																																txtExperts.setDisabledTextColor(Color.BLACK);
																																																																																																txtExperts.setBackground(SystemColor.window);
																																																																																																GridBagConstraints gbc_txtExperts = new GridBagConstraints();
																																																																																																gbc_txtExperts.fill = GridBagConstraints.BOTH;
																																																																																																gbc_txtExperts.weighty = 0.5;
																																																																																																gbc_txtExperts.weightx = 0.5;
																																																																																																gbc_txtExperts.insets = new Insets(0, 0, 5, 0);
																																																																																																gbc_txtExperts.gridx = 0;
																																																																																																gbc_txtExperts.gridy = 6;
																																																																																																panelDomandeTutor.add(txtExperts, gbc_txtExperts);
																																																																																														
																																																																																																cBox6 = new JComboBox(sceltaBox);
																																																																																																cBox6.setFont(new Font("Tahoma", Font.PLAIN, 12));
																																																																																																cBox6.setModel(new DefaultComboBoxModel(new String[] {"", "Si", "No"}));
																																																																																																cBox6.setSelectedIndex(0);
																																																																																																GridBagConstraints gbc_cBox6 = new GridBagConstraints();
																																																																																																gbc_cBox6.fill = GridBagConstraints.HORIZONTAL;
																																																																																																gbc_cBox6.gridx = 1;
																																																																																																gbc_cBox6.gridy = 6;
																																																																																																panelDomandeTutor.add(cBox6, gbc_cBox6);
				
						btnInvia= new JButton("Invia");
						btnInvia.setEnabled(false);
						btnInvia.setBounds(515, 482, 117, 25);
						panelTutor.add(btnInvia);
						btnInvia.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(assertaDatiPersonali()) {
									//Combobox
									addProblemFact(cBox1, "", "colors");
									addProblemFact(cBox2, "", "questions_topic");
									addProblemFact(cBox3, "", "subject_type");
									addProblemFact(cBox4, "", "audio");
									addProblemFact(cBox5, "", "experts");
									addProblemFact(cBox6, "", "deepening");
									addProblemFact(cBox7, "", "interaction");
									addProblemFact(cBox8, "", "period_test");
									addProblemFact(cBox9, "", "book");
									addProblemFact(cBox10, "", "book_type");
									addProblemFact(cBox11, "", "exercise");
									addProblemFact(cBox12, "", "computer");
									addProblemFact(cBox13, "", "enigma");

									btnInvia.setEnabled(false); // per evitare che vengano modificati i valori dopo l'invio
									lblConfermaDati.setVisible(true);
									for(Fact f : fatti) {
										rw.writeFact(f.getFact(), (int) Math.round(f.getProb())); //ste le probabilita' devono essere float non int!!!
									}
								}
							}
						});
						btnInvia.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panelTutor.add(lblConfermaDati);

		pnlDatiPersonali = new JPanel();
		pnlDatiPersonali.setForeground(new Color(0, 0, 0));
		pnlDatiPersonali.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Dati studente", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDatiPersonali.setBounds(22, 67, 288, 389);
		panelTutor.add(pnlDatiPersonali);
		pnlDatiPersonali.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(27, 126, 59, 14);
		pnlDatiPersonali.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(121, 124, 125, 20);
		pnlDatiPersonali.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblDataDiNascita = new JLabel("Data di Nascita");
		lblDataDiNascita.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataDiNascita.setBounds(27, 201, 89, 14);
		pnlDatiPersonali.add(lblDataDiNascita);

		txtGiorno = new JTextField();
		txtGiorno.setToolTipText("giorno");
		txtGiorno.setBounds(121, 195, 30, 20);
		pnlDatiPersonali.add(txtGiorno);
		txtGiorno.setColumns(10);

		txtMese = new JTextField();
		txtMese.setToolTipText("mese");
		txtMese.setBounds(161, 195, 30, 20);
		pnlDatiPersonali.add(txtMese);
		txtMese.setColumns(10);

		txtAnno = new JTextField();
		txtAnno.setToolTipText("anno");
		txtAnno.setBounds(201, 195, 45, 20);
		pnlDatiPersonali.add(txtAnno);
		txtAnno.setColumns(10);

		JLabel lblSesso = new JLabel("Sesso");
		lblSesso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSesso.setBounds(27, 235, 46, 14);
		pnlDatiPersonali.add(lblSesso);

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCognome.setBounds(27, 167, 89, 14);
		pnlDatiPersonali.add(lblCognome);

		txtCognome = new JTextField();
		txtCognome.setColumns(10);
		txtCognome.setBounds(121, 164, 125, 20);
		pnlDatiPersonali.add(txtCognome);

		txtAnno.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				abilitaInvio();
			}
		});

		cBoxSesso = new JComboBox();
		cBoxSesso.setModel(new DefaultComboBoxModel(new String[] {"", "Maschile", "Femminile"}));
		cBoxSesso.setBounds(121, 232, 125, 20);
		pnlDatiPersonali.add(cBoxSesso);

	}

	private void abilitaInvio() {
		if(!txtNome.getText().isEmpty() && !txtCognome.getText().isEmpty() && !(cBoxSesso.getSelectedIndex() == -1)) //ste && sesso && anno?
			btnInvia.setEnabled(true);
		else
			btnInvia.setEnabled(false);
	}

	private void addProblemFact(JComboBox<String> comboBox, String problem, String fact) {
		if(!comboBox.getSelectedItem().equals(""))
			fatti.add(new Fact( fact + "(" + nome_cognome + "," + problem + ")", getProb((String)comboBox.getSelectedItem())));
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
			nome_cognome = nome + "_" + cognome; //costruzione della stringa al fine di migliorare la leggibilit� dei fatti a seguire
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
