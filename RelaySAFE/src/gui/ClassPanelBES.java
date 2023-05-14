package gui;
 
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ClassPanelBES extends GenericPanel {
	public String[] sceltaBox = {"","1","2","3","4"};
	public int domanda = 0;
	public String punteggioStrBES = new String();
	public Integer punteggioBES;
	private Map<Integer, String> risposteBES = new HashMap<>();
	private Map<Integer, Integer> punteggiBES = new HashMap<>();
	HashSet<String> setFattiBES = new HashSet<String>();
	private String[] testBES = {
		"1. Non penso al mio peso e alle dimensioni del mio corpo \r\nquando sono con altre persone \u2028\r\n\r\n2. Mi preoccupo del mio aspetto ma questo non mi rende \r\ninsoddisfatta/o di me \u2028\r\n\r\n3. Penso al mio aspetto e al mio peso e mi sento delusa/o \r\nda me stessa/o \r\n\r\n4. Penso molto al mio peso e provo spesso forte vergogna \r\ne disgusto per me stessa/o. Perci\u00F2 evito per quando possibile \r\ndi incontrare altre persone \r\n",
		"1. Non ho difficolta' a mangiare lentamente, seduta/o \r\nin maniera corretta \r\n\r\n2. Mi sembra di trangugiare il cibo ma alla fine non mi sento \r\ntroppo piena/o per aver mangiato eccessivamente\r\n\r\n3. A volte mangio velocemente e dopo mi sento troppo piena/o\r\n\r\n4. Di solito ingoio il cibo quasi senza masticarlo e poi \r\nmi sento scoppiare perch\u00E9 ho mangiato troppo ",
		"1. Quando voglio, sono capace di controllare i miei impulsi \r\nverso il cibo\r\n\r\n2. Penso di avere minor controllo sul cibo rispetto alla \r\nmaggior parte delle persone\r\n\r\n3. Mi sento totalmente incapace di controllare i miei impulsi \r\nverso il cibo\r\n\r\n4. Mi sento totalmente incapace di controllare il mio rapporto \r\ncon l\u2019alimentazione e cerco disperatamente di combattere \r\ni miei impulsi verso il cibo ",
		"1. Non ho l'abitudine di mangiare quando sono annoiata/o\r\n\r\n2. Qualche volta mangio quando sono annoiata/o, ma spesso \r\nriesco a distrarmi e a non pensare al cibo\r\n\r\n3. Spesso mangio quando sono annoiata/o, ma talvolta riesco \r\na distrarmi e a non pensare al cibo\r\n\r\n4. Ho l\u2019abitudine di mangiare quando sono annoiata/o \r\ne niente riesce a farmi smettere ",
		"1. Di solito, quando mangio qualcosa \u00E8 perch\u00E9 ho fame\r\n\r\n2. Talvolta mangio d'impulso, senza avere veramente fame\r\n\r\n3. Mi capita spesso di mangiare per soddisfare una sensazione \r\ndi fame anche se fisicamente non ho bisogno di cibo; in queste \r\noccasioni non riesco nemmeno a gustare quello che mangio\r\n\r\n4. Anche se non ho fisicamente fame, avverto il bisogno \r\ndi mettere qualcosa in bocca e mi sento soddisfatta/o solo quando\r\nriesco na riempirmi la bocca (per esempio con un pezzo di pane). \r\nQualche volta, quando questo succede, risputo il cibo \r\nper non ingrassare",
		"1. Non mi sento per nulla in colpa, n\u00E9 provo odio per me \r\nstessa/o dopo aver mangiato troppo \r\n\r\n2. A volte mi sento in colpa o provo odio per me stessa/o \r\ndopo aver mangiato troppo\r\n\r\n3. Quasi sempre provo un forte senso di colpa o odio per me \r\nstessa/o se ho mangiato troppo \r\n",
		"1. Quando sono a dieta non perdo mai del tutto il controllo \r\nsul cibo, anche in momenti in cui mangio troppo\r\n\r\n2. Quando sono a dieta e mangio un cibo proibito, sento che \r\normai ho trasgredito e mangio ancora di pi\u00F9\r\n\r\n3. Quando sono a dieta e mangio pi\u00F9 del dovuto mi dico spesso: \r\n\u201COrmai hai trasgredito, perch\u00E9 non vai fino in fondo?\u201D \r\nQuando questo succede, mangio ancora di pi\u00F9\r\n\r\n4. Mi metto regolarmente a dieta stretta, ma poi interrompo \r\nla dieta con un\u2019abbuffata. La mia vita \u00E8 fatta di abbuffate \r\ne digiuni",
		"1. E' raro che io mangi così tanto da sentirmi \r\nsgradevolmente piena/o\r\n\r\n2. Circa una volta al mese mangio cos\u00EC tanto da sentirmi \r\nsgradevolmente piena/o\r\n\r\n3. Ci sono periodi regolari durante il mese in cui mangio \r\ngrandi quantit\u00E0 di cibo, ai pasti o fuori dai pasti\r\n\r\n4. Mangio cos\u00EC tanto che di solito, dopo aver mangiato, \r\nmi sento piuttosto male e ho nausea",
		"1. La quantità di calorie che assumo \u00E8 abbastanza costante \r\nnel tempo\r\n\r\n2. Qualche volta, dopo aver mangiato troppo, cerco di mangiare \r\npochissime calorie per compensare l' eccesso \r\ndel pasto precedente.\r\n\r\n3. Ho l\u2019abitudine di mangiare troppo di sera. \r\nDi solito non ho fame la mattina e mangio troppo la sera\r\n\r\n4. Da adulto ho avuto periodi di circa una settimana\r\nin cui mi sono imposto diete da fame, a seguito di periodi \r\nin cui avevo mangiato troppo. \r\nLa mia vita \u00E8 fatta di abbuffate e digiuni",
		"1. Di solito riesco a smettere di mangiare quando lo decido \r\nSo quando \u00E8 ora di dire basta\r\n\r\n2. A volte avverto un impulso a mangiare che non riesco \r\na controllare\r\n\r\n3. Spesso avverto impulsi a mangiare cos\u00EC forti che \r\nnon riesco a vincerli, mentre altre volte riesco a \r\ncontrollarmi\r\n\r\n4. Mi sento del tutto incapace di controllare i miei \r\nimpulsi a mangiare. \r\nHo paura di non farcela a smettere di mangiare con un atto \r\ndi volont\u00E0",
		"1. Non ho problemi a smettere di mangiare quando mi sento \r\npiena/o\r\n\r\n2. Di solito riesco a smettere di mangiare appena mi sento \r\npiena/o, ma talvolta mangio cos\u00EC tanto da sentirmi piena/o\r\nin modo sgradevole\r\n\r\n3. Per me \u00E8 un vero problema smettere di mangiare una volta \r\nche ho iniziato e di solito, alla fine, mi sento piena/o \r\nin modo sgradevole\r\n\r\n4. Per me \u00E8 un vero problema smettere di mangiare e qualche \r\nvolta devo provocarmi il vomito per avere sollievo",
		"1. Quando sono con gli altri (incontri familiari, occasioni \r\nsociali) mi sembra di mangiare come quando sono da sola/o\r\n\r\n2. Quando sono con gli altri a volte non mangio quanto \r\nvorrei, perch\u00E9 sono consapevole dei miei problemi con il \r\ncibo\r\n\r\n3. Quando sono con gli altri spesso mangio poco, perch\u00E9 \r\nmangiare di fronte ad altri mi imbarazza molto\r\n\r\n4. Mi vergogno cos\u00EC tanto di mangiare troppo, che per farlo \r\nscelgo i momenti in cui nessuno mi vede. \r\nIn effetti, mangio di nascosto",
		"1. Faccio tre pasti al giorno e occasionalmente uno spuntino\r\n\r\n2. Faccio tre pasti al giorno e di solito anche degli spuntini\r\n\r\n3. Quando faccio molti spuntini salto i pasti regolari\r\n\r\n4. Ci sono periodi in cui mi sembra di mangiare continuamente, \r\nsenza pasti regolari",
		"1. Non penso molto a controllare gli impulsi a mangiare \r\nche non vorrei avere\r\n\r\n2. A volte la mia mente \u00E8 occupata dal pensiero di come \r\ncontrollare l\u2019impulso a mangiare\r\n\r\n3. Spesso passo molto tempo pensando a quanto ho mangiato \r\no a come fare per non mangiare\r\n\r\n4. La mia mente \u00E8 occupata per la maggior parte del tempo \r\nda pensieri sul mangiare. \r\nMi sembra di essere continuamente in lotta per non mangiare",
		"1. Non penso molto al cibo\r\n\r\n2. Mi capita di avere un forte desiderio di cibo, \r\nma solo per brevi periodi di tempo\r\n\r\n3. Ci sono giorni in cui non penso ad altro che al cibo\r\n\r\n4. La maggior parte delle mie giornate \u00E8 occupata da \r\npensieri sul cibo. Mi sembra di vivere per mangiare",
		"1. Di solito so se sono affamata/o oppure no. \r\nPrendo la porzione giusta per saziarmi.\r\n\r\n2. A volte non so bene se ho fisicamente fame oppure no. \r\nIn questi momenti, mi \u00E8 difficile capire quanto cibo ci \r\nvorrebbe per saziarmi.\r\n\r\n3. Anche se sapessi quante calorie dovrei mangiare, non avrei \r\nun\u2019idea chiara di quale sarebbe, per me, una normale \r\nquantit\u00E0 di cibo"
	};
	private JTextField textFieldCalcolaBES;
	
	public ClassPanelBES() {
		super("BES");
		setBounds(33, 35, 735, 431);
		setLayout(null);
		
		JPanel panelBES = new JPanel();
		panelBES.setBorder(new TitledBorder(null, "Test BES", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBES.setBounds(10, 11, 705, 412);
		add(panelBES);
		panelBES.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(171, 41, 525, 265);
		panelBES.add(scrollPane);
		
		final JTextArea txtrBes01 = new JTextArea();
		txtrBes01.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtrBes01.setEditable(false);
		
		txtrBes01.setText(testBES[domanda].replaceAll("\\\\n", System.getProperty("line.separator")));
		scrollPane.setViewportView(txtrBes01);
		
		final JLabel labelSetDomande = new JLabel(domanda+1+"Set di domande. SELEZIONA ->");
		labelSetDomande.setBounds(280, 335, 201, 14);
		panelBES.add(labelSetDomande);
		
		final JLabel lblTestoDomanda = new JLabel("Testo della domanda "+domanda+1);
		lblTestoDomanda.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTestoDomanda.setBounds(340, 23, 147, 14);
		panelBES.add(lblTestoDomanda);
		
		JPanel panelItemBES = new JPanel();
		panelItemBES.setBounds(7, 33, 160, 275);
		panelBES.add(panelItemBES);
		panelItemBES.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Item - Valore", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelItemBES.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setFocusTraversalKeysEnabled(false);
		scrollPane_1.setBounds(5, 15, 150, 255);
		panelItemBES.add(scrollPane_1);
		
		final JTextArea textAreaItemBES = new JTextArea();
		textAreaItemBES.setAutoscrolls(false);
		scrollPane_1.setViewportView(textAreaItemBES);
		textAreaItemBES.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textAreaItemBES.setEditable(false);
		
		JButton btnAvanti = new JButton("Avanti");
		btnAvanti.setBounds(607, 331, 89, 23);
		panelBES.add(btnAvanti);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(171, 331, 89, 23);
		panelBES.add(btnIndietro);		

		JLabel lblSelezionaUnaDelle = new JLabel("Seleziona l'affermazione piu' congeniale nel descrivere la tua condizione emotiva.");
		lblSelezionaUnaDelle.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSelezionaUnaDelle.setBounds(171, 311, 508, 21);
		panelBES.add(lblSelezionaUnaDelle);
		
		textFieldCalcolaBES = new JTextField();
		textFieldCalcolaBES.setEditable(false);
		textFieldCalcolaBES.setColumns(10);
		textFieldCalcolaBES.setBounds(40, 368, 102, 31);
		panelBES.add(textFieldCalcolaBES);
		
		final JLabel labelPunteggio = new JLabel("");
		labelPunteggio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		labelPunteggio.setBounds(164, 368, 532, 31);
		panelBES.add(labelPunteggio);
		
		final JComboBox comboBoxRisposta = new JComboBox(sceltaBox);
		comboBoxRisposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTextAreaItems(textAreaItemBES);
				recuperaRisposta(comboBoxRisposta, domanda);
			}
		});
		comboBoxRisposta.setBounds(480, 332, 63, 20);
		panelBES.add(comboBoxRisposta);

		btnAvanti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtrBes01.repaint();
				setTextAreaItems(textAreaItemBES);
				if (domanda < 15) {
					domanda++;
					txtrBes01.setText(testBES[domanda].replaceAll("\\\\n", System.getProperty("line.separator")));
					labelSetDomande.setText(domanda+1+" Domanda. SELEZIONA ->");
					lblTestoDomanda.setText("Testo della domanda "+(domanda+1));
					comboBoxRisposta.setSelectedIndex(0);
				}

			}			
		});	

		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (domanda > 0) {
					domanda--;
					txtrBes01.setText(testBES[domanda]);
					labelSetDomande.setText(domanda+1+" Domanda. SELEZIONA ->");
					lblTestoDomanda.setText("Testo della domanda "+(domanda+1));
					comboBoxRisposta.setSelectedIndex(0);
				}
			}
		});
		
		JButton btnCalcolaBES = new JButton("Calcola");
		btnCalcolaBES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPunteggioBES ();
				trovaFattiBES();
				setTextAreaItems(textAreaItemBES);
				setLabelPunteggio(labelPunteggio);
			}
		});
		btnCalcolaBES.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnCalcolaBES.setBounds(40, 326, 102, 31);
		panelBES.add(btnCalcolaBES);

	}
	 
	 public void setPunteggioBES () {		 
		 for (Integer key : risposteBES.keySet()) {
			 if (key == 0) {
				 switch(risposteBES.get(key)){
				 case "1":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 case "2":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 case "3":{
					 punteggiBES.put(key, 1);
					 break;
				 	}
				 case "4":{
					 punteggiBES.put(key, 3);
					 break;
				 	}
				 }				 
			 }
			 else if (key == 1 || key == 4 || key == 7 || key == 8 || key == 9 || key == 10 || key == 11 || key == 13 || key == 14  ){
				 switch(risposteBES.get(key)){
				 case "1":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 case "2":{
					 punteggiBES.put(key, 1);
					 break;
				 	}
				 case "3":{
					 punteggiBES.put(key, 2);
					 break;
				 	}
				 case "4":{
					 punteggiBES.put(key, 3);
					 break;
				 	}
				 }			 	
			 }
			 else if (key == 2 ){
				 switch(risposteBES.get(key)){
				 case "1":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 case "2":{
					 punteggiBES.put(key, 1);
					 break;
				 	}
				 case "3":{
					 punteggiBES.put(key, 3);
					 break;
				 	}
				 case "4":{
					 punteggiBES.put(key, 3);
					 break;
				 	}
				 }			 	
			 }
			 else if (key == 3 ){
				 switch(risposteBES.get(key)){
				 case "1":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 case "2":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 case "3":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 case "4":{
					 punteggiBES.put(key, 3);
					 break;
				 	}
				 }			 	
			 }
			 else if (key == 5 ){
				 switch(risposteBES.get(key)){
				 case "1":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 case "2":{
					 punteggiBES.put(key, 1);
					 break;
				 	}
				 case "3":{
					 punteggiBES.put(key, 3);
					 break;
				 	}
				 case "4":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 }			 	
			 }
			 else if (key == 6 ){
				 switch(risposteBES.get(key)){
				 case "1":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 case "2":{
					 punteggiBES.put(key, 2);
					 break;
				 	}
				 case "3":{
					 punteggiBES.put(key, 3);
					 break;
				 	}
				 case "4":{
					 punteggiBES.put(key, 3);
					 break;
				 	}
				 }			 	
			 }
			 else if (key == 12 ){
				 switch(risposteBES.get(key)){
				 case "1":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 case "2":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 case "3":{
					 punteggiBES.put(key, 2);
					 break;
				 	}
				 case "4":{
					 punteggiBES.put(key, 3);
					 break;
				 	}
				 }			 	
			 }
			 else if (key == 15 ){
				 switch(risposteBES.get(key)){
				 case "1":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 case "2":{
					 punteggiBES.put(key, 1);
					 break;
				 	}
				 case "3":{
					 punteggiBES.put(key, 2);
					 break;
				 	}
				 case "4":{
					 punteggiBES.put(key, 0);
					 break;
				 	}
				 }			 	
			 }
			 punteggioStrBES = punteggioStrBES + "Domanda " + key.toString() + ": "	+ risposteBES.get(key)+ " = "+ punteggiBES.get(key) +"\n";	 
		}
		 punteggioBES = 0;
		 for (Object chiave : punteggiBES.keySet()) {
			 //System.out.print("i = "+chiave.toString()+" valore: " + punteggiBES.get(chiave)+"\n");
			 punteggioBES = punteggioBES + punteggiBES.get(chiave);
		 }
		 textFieldCalcolaBES.setText("Punteggio: "+ punteggioBES);
		 //System.out.println(punteggioStrBES);
		 //System.out.println("Punteggio: "+ punteggioBES);
	 }
	 
	 private void recuperaRisposta(JComboBox comboBox, int i){
         String selezionato = (String) comboBox.getSelectedItem();
         if (selezionato == "1" || selezionato == "2" || selezionato == "3" || selezionato == "4" ) {
        	 risposteBES.put(i,selezionato);	
		}
	 }
	 
	 public Integer getPunteggioBES(){
		 return punteggioBES;
	 }
	 
	 public String getPunteggioStrBES(){
		 return punteggioStrBES;
	 }
	 
	public void clearTextArea(JTextArea textArea){
			textArea.setText("");
		}

	public void setTextAreaItems(JTextArea textArea){
		String itemBES = new String();
		clearTextArea(textArea);
		for (int i = 0; i < 16; i++) {
			if (risposteBES.containsKey(i)) {
				itemBES = itemBES + (i+1)+") Valore: "	+ risposteBES.get(i)+ " - Punti: "+ punteggiBES.get(i) +"\n";
			} else {
				itemBES = itemBES + (i+1)+ ") \n";
			}
		}
		textArea.setText(itemBES);
	}
	
	private void setLabelPunteggio(JLabel label){
		if (punteggioBES<17) {
			label.setText("Il punteggio è inferiore a 17, la presenza di sintomi di binge eating è IMPROBABILE");
		}
		else if (punteggioBES>=17 && punteggioBES<=27) {
			label.setText("Il punteggio è compreso tra 17 e 27, la presenza di sintomi di binge eating è POSSIBILE");
		}
		else if(punteggioBES>27) {
			label.setText("Il punteggio è superiore a 27, la presenza di sintomi di binge eating è PROBABILE");
		}
	}
	
 public void trovaFattiBES(){
		int abbuffateIncontrollate = 0;
		int mancanzaControlloMangiare = 0;
		int disagioAbbuffate = 0;
		 
		 for (Object chiave : punteggiBES.keySet()) {
			Integer i = (Integer) chiave;
			switch(i){
				case 3: case 4: case 6: case 8: case 12:{
					//System.out.println("1)Abbuffate incontrollate - Minimo punteggio 3");
					//System.out.print("i = "+chiave.toString()+" Asserisci - valore: " + punteggiBES.get(chiave)+"\n");
					abbuffateIncontrollate = abbuffateIncontrollate + punteggiBES.get(chiave);
				}
				break;
				case 1: case 2: case 7: case 9: case 10: case 13: case 15:{
					//System.out.println("2)Mancanza di controllo nell'atto di mangiare - Minimo punteggio 4");
					//System.out.print("i = "+chiave.toString()+" Asserisci - valore: " + punteggiBES.get(chiave)+"\n");
					mancanzaControlloMangiare = mancanzaControlloMangiare + punteggiBES.get(chiave);
				}
				case 0: case 5: case 11: case 14:{
					//System.out.println("3)Disagio e sofferenza suscitate dalle abbuffate convulsive - Minimo punteggio 1");
					//System.out.print("i = "+chiave.toString()+" Asserisci - valore: " + punteggiBES.get(chiave)+"\n");
					disagioAbbuffate = disagioAbbuffate + punteggiBES.get(chiave);
				}
				break;
			}			 
		}
		System.out.println("1)Tot BES= "+abbuffateIncontrollate+" Abbuffate incontrollate - Minimo punteggio 5");
		System.out.println("2)Tot BES= "+mancanzaControlloMangiare+" Mancanza di controllo nell'atto di mangiare - Minimo punteggio 7");
		System.out.println("3)Tot BES= "+disagioAbbuffate+" Disagio e sofferenza suscitate dalle abbuffate convulsive - Minimo punteggio 4");
		
		elaboraFattiBES(abbuffateIncontrollate,mancanzaControlloMangiare,disagioAbbuffate);
	 }

	private void elaboraFattiBES(int abbuffateIncontrollate, int mancanzaControlloMangiare, int disagioAbbuffate) {
		String fattoAbbuffateIncontrollateSi = "presenta("+ ClassPanelPaziente.getnome_cognome() +",episodi di abbuffate)";
		String fattoMancanzaControlloMangiareSi = "presenta("+ ClassPanelPaziente.getnome_cognome() +",mancanza di controllo nell atto di mangiare)";
		String fattoDisagioAbbuffateSi = "presenta("+ ClassPanelPaziente.getnome_cognome() +",abbuffate compulsive che suscitano sofferenza e disagio)";

		if (abbuffateIncontrollate >=5) {
			rw.writeFact(fattoAbbuffateIncontrollateSi, 1);
		}
		 if (mancanzaControlloMangiare >= 7) {
			 rw.writeFact(fattoMancanzaControlloMangiareSi, 1);
		}
		 if (disagioAbbuffate >= 4) {
			 rw.writeFact(fattoDisagioAbbuffateSi, 1);
		}		
	}

}
