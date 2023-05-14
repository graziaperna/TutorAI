package gui;
 
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import java.awt.Dimension;

public class ClassPanelEAT26 extends GenericPanel {
	
	public String[] sceltaBox = {"","Sempre","Molto spesso","Spesso","Qualche volta","Raramente","Mai"};
	public int domanda = 0;
	public String punteggioStrEAT26 = new String();
	public Integer punteggioEAT26;
	private Map<Integer, String> risposteEAT26 = new HashMap<>();
	private Map<Integer, Integer> punteggiEAT26 = new HashMap<>();
	HashSet<String> setFattiEAT26 = new HashSet<String>();
	private String[] testEAT26 = {
		"1. Ho una terribile paura di ingrassare",
		"2. Quando ho fame evito di mangiare",
		"3. Penso al cibo con preoccupazione ",
		"4. Mi è capitato di mangiare con enorme voracità sentendomi \r\nincapace di smettere",
		"5. Ho l'abitudine di sminuzzare il cibo",
		"6. Faccio molta attenzione al potere calorico dei cibi \r\nche mangio",
		"7. Evito in particolare i cibi con elevato contenuto di \r\ncarboidrati (pane, pasta, dolci)",
		"8. Sento che gli altri vorrebbero che io mangiassi di più",
		"9. Mi capita di vomitare dopo aver mangiato",
		"10. Mi sento molto in colpa dopo mangiato",
		"11. Mi tormenta il desiderio di essere più sottile",
		"12. Mi sottopongo a esercizi fisici intensi per bruciare calorie",
		"13. Gli altri pensano che sono troppo magra/o",
		"14. Mi preoccupa l'idea di avere del grasso sul corpo",
		"15. Impiego più tempo degli altri per mangiare",
		"16. Evito cibi dolci",
		"17. Mangio cibi dietetici",
		"18. Sento che il cibo domina la mia vita",
		"19. Mi piace mostrare un grande autocontrollo verso il cibo \r\ne dominare la fame ",
		"20. Sento che gli altri fanno pressioni su di me perchè io mangi",
		"21. Dedico al cibo troppo tempo e troppi pensieri",
		"22. Mi dispero se mangio dei dolci ",
		"23. Mi impegno in programmi di dieta",
		"24. Mi piace che il mio stomaco sia vuoto",
		"25. Ho l'impulso di vomitare dopo mangiato",
		"26. Mi piace provare nuovi cibi elaborati"
	};
	private JTextField textFieldCalcolaEAT26;
	private JPanel panelItemEAT26;

	public ClassPanelEAT26() {
		super("EAT-26");
		setBounds(33, 35, 735, 431);
		setLayout(null);
		
		JPanel panelEAT26 = new JPanel();
		panelEAT26.setBorder(new TitledBorder(null, "Test EAT26", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEAT26.setBounds(10, 11, 706, 410);
		add(panelEAT26);
		panelEAT26.setLayout(null);
		
		JScrollPane scrollPaneEAT26 = new JScrollPane();
		scrollPaneEAT26.setBounds(171, 41, 525, 265);
		panelEAT26.add(scrollPaneEAT26);
		
		final JTextArea txtrEAT2601 = new JTextArea();
		txtrEAT2601.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtrEAT2601.setEditable(false);
		txtrEAT2601.setText(testEAT26[domanda]);
		txtrEAT2601.setCaretPosition(0);
		scrollPaneEAT26.setViewportView(txtrEAT2601);
		
		panelItemEAT26 = new JPanel();
		panelItemEAT26.setBounds(7, 33, 160, 275);
		panelEAT26.add(panelItemEAT26);
		panelItemEAT26.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Item - Valore", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelItemEAT26.setLayout(null);
		
		JScrollPane scrollPaneAreaItemEAT26 = new JScrollPane();
		scrollPaneAreaItemEAT26.setBounds(5, 15, 150, 255);

		panelItemEAT26.add(scrollPaneAreaItemEAT26);
		
		final JTextArea textAreaItemEAT26 = new JTextArea();
		textAreaItemEAT26.setAutoscrolls(false);
		scrollPaneAreaItemEAT26.setViewportView(textAreaItemEAT26);
		textAreaItemEAT26.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textAreaItemEAT26.setEditable(false);
		
		final JLabel labelSetDomande = new JLabel(domanda+1+"° Set di domande. SELEZIONA ->");
		labelSetDomande.setBounds(280, 335, 201, 14);
		panelEAT26.add(labelSetDomande);
		
		final JLabel lblTestoDomanda = new JLabel("Testo della domanda "+domanda+1);
		lblTestoDomanda.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTestoDomanda.setBounds(340, 23, 147, 14);
		panelEAT26.add(lblTestoDomanda);
		
		JButton btnAvanti = new JButton("Avanti");
		btnAvanti.setBounds(607, 331, 89, 23);
		panelEAT26.add(btnAvanti);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(171, 331, 89, 23);
		panelEAT26.add(btnIndietro);		

		JLabel lblSelezionaUnaDelle = new JLabel("Seleziona l'affermazione più congeniale nel descrivere la tua condizione emotiva.");
		lblSelezionaUnaDelle.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSelezionaUnaDelle.setBounds(171, 311, 508, 21);
		panelEAT26.add(lblSelezionaUnaDelle);
		
		textFieldCalcolaEAT26 = new JTextField();
		textFieldCalcolaEAT26.setEditable(false);
		textFieldCalcolaEAT26.setColumns(10);
		textFieldCalcolaEAT26.setBounds(40, 368, 102, 31);
		panelEAT26.add(textFieldCalcolaEAT26);
		
		final JLabel labelPunteggio = new JLabel("");
		labelPunteggio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		labelPunteggio.setBounds(164, 368, 532, 31);
		panelEAT26.add(labelPunteggio);
		
		final JComboBox comboBoxRisposta = new JComboBox(sceltaBox);
		comboBoxRisposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTextAreaItems(textAreaItemEAT26);
				recuperaRisposta(comboBoxRisposta, domanda);
			}
		});
		
		comboBoxRisposta.setBounds(480, 332, 120, 20);
		panelEAT26.add(comboBoxRisposta);

		btnAvanti.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				int i = domanda;
				i++;
				setTextAreaItems(textAreaItemEAT26);
				if (i>=0 && i<26){
					txtrEAT2601.setText(testEAT26[i]);
					domanda = i;
					labelSetDomande.setText(domanda+1+"° Set di domande. SELEZIONA ->");
					lblTestoDomanda.setText("Testo della domanda "+(domanda+1));
					comboBoxRisposta.setSelectedIndex(0);
				}
			}			
		});	

		btnIndietro.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				int i = domanda;
				i--;
				if (i>=0 && i<26){
					txtrEAT2601.setText(testEAT26[i]);
					domanda = i;				
					labelSetDomande.setText(domanda+1+"° Set di domande. SELEZIONA ->");
					lblTestoDomanda.setText("Testo della domanda "+(domanda+1));
					comboBoxRisposta.setSelectedIndex(0);
				}
			}
		});
		


		
		JButton btnCalcolaEAT26 = new JButton("Calcola");
		btnCalcolaEAT26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPunteggioEAT26 ();
				trovaFattiEAT26();
				setTextAreaItems(textAreaItemEAT26);
				setLabelPunteggio(labelPunteggio);
			}
		});
		btnCalcolaEAT26.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnCalcolaEAT26.setBounds(40, 326, 102, 31);
		panelEAT26.add(btnCalcolaEAT26);		

	}
	 
	 public void setPunteggioEAT26 (){		 
		 for (Integer key : risposteEAT26.keySet()) {
			 if (key < 25) {
				 switch(risposteEAT26.get(key)){
				 case "Sempre":{
					 punteggiEAT26.put(key, 3);
					 break;
				 	}
				 case "Molto spesso":{
					 punteggiEAT26.put(key, 2);
					 break;
				 	}
				 case "Spesso":{
					 punteggiEAT26.put(key, 1);
					 break;
				 	}
				 case "Qualche volta":{
					 punteggiEAT26.put(key, 0);
					 break;
				 	}
				 case "Raramente":{
					 punteggiEAT26.put(key, 0);
					 break;
				 	}
				 case "Mai":{
					 punteggiEAT26.put(key, 0);
					 break;
				 	}
				 }				 
			 }
			 else if (key == 25){
				 switch(risposteEAT26.get(key)){
				 case "Sempre":{
					 punteggiEAT26.put(key, 0);
					 break;
				 	}
				 case "Molto spesso":{
					 punteggiEAT26.put(key, 0);
					 break;
				 	}
				 case "Spesso":{
					 punteggiEAT26.put(key, 0);
					 break;
				 	}
				 case "Qualche volta":{
					 punteggiEAT26.put(key, 1);
					 break;
				 	}
				 case "Raramente":{
					 punteggiEAT26.put(key, 2);
					 break;
				 	}
				 case "Mai":{
					 punteggiEAT26.put(key, 3);
					 break;
				 	}
				 }			 	
			 }
			 punteggioStrEAT26 = punteggioStrEAT26 + "Domanda " + key.toString() + ": "	+ risposteEAT26.get(key)+ " = "+ punteggiEAT26.get(key) +"\n";	 
		}
		 punteggioEAT26 = 0;
		 for (Object chiave : punteggiEAT26.keySet()) {
			 //System.out.print("i = "+chiave.toString()+" valore: " + punteggiEAT26.get(chiave)+"\n");
			 punteggioEAT26 = punteggioEAT26 + punteggiEAT26.get(chiave);
		 }
		 textFieldCalcolaEAT26.setText("Punteggio: "+ getPunteggioEAT26());
		 //System.out.println(getPunteggioStrEAT26());
		 //System.out.println("Punteggio: "+ punteggioEAT26);
	 }
	 
	 public void trovaFattiEAT26(){
		int bassoPeso = 0;
		int pauraAumentarePeso = 0;
		int anomaliaPercepireCorpo = 0;
		int mancanzaControlloMangiare = 0;
		int comportamentiPrevenireAumentoPeso = 0;
		int disagioAbbuffate = 0;
		int abusoIntegratori = 0;
		int esercizioFisicoCompulsivo = 0;
		 
		 for (Object chiave : punteggiEAT26.keySet()) {
			Integer i = (Integer) chiave;
			switch(i){
				case 7: case 12: case 19:{
					//System.out.println("1)Basso peso - Minimo punteggio 3");
					//System.out.print("i = "+chiave.toString()+" Asserisci - valore: " + punteggiEAT26.get(chiave)+"\n");
					bassoPeso = bassoPeso + punteggiEAT26.get(chiave);
				}
				break;
				case 0: {
					//System.out.println("2)Paura di aumentare di peso - Minimo punteggio 1");
					//System.out.print("i = "+chiave.toString()+" Asserisci - valore: " + punteggiEAT26.get(chiave)+"\n");
					pauraAumentarePeso = pauraAumentarePeso  + punteggiEAT26.get(chiave);
				}
				break;
				case 10: case 13: case 21: case 23:{
					//System.out.println("3)Anomalia nel percepire peso e forma del corpo - Minimo punteggio 4");
					//System.out.print("i = "+chiave.toString()+" Asserisci - valore: " + punteggiEAT26.get(chiave)+"\n");
					anomaliaPercepireCorpo = anomaliaPercepireCorpo + punteggiEAT26.get(chiave);
				}
				break;
				case 2: case 3: case 17: case 20:{
					//System.out.println("4)Mancanza di controllo nell�atto di mangiare - Minimo punteggio 4");
					//System.out.print("i = "+chiave.toString()+" Asserisci - valore: " + punteggiEAT26.get(chiave)+"\n");
					mancanzaControlloMangiare = mancanzaControlloMangiare + punteggiEAT26.get(chiave);
				}
				break;
				case 1: case 4: case 8: case 14: case 15: case 16: case 18: case 22: case 24: case 25: {
					//System.out.println("5)Comportamenti volti a prevenire l�aumento di peso - Minimo punteggio 10");
					//System.out.print("i = "+chiave.toString()+" Asserisci - valore: " + punteggiEAT26.get(chiave)+"\n");
					comportamentiPrevenireAumentoPeso = comportamentiPrevenireAumentoPeso + punteggiEAT26.get(chiave);
				}
				break;
				case 9: {
					//System.out.println("6)Disagio e sofferenza suscitate dalle abbuffate convulsive - Minimo punteggio 1");
					//System.out.print("i = "+chiave.toString()+" Asserisci - valore: " + punteggiEAT26.get(chiave)+"\n");
					disagioAbbuffate = disagioAbbuffate + punteggiEAT26.get(chiave);
				}
				break;
				case 5:  case 6: {
					//System.out.println("7)Abuso di integratori, diete iperproteiche - Minimo punteggio 2");
					//System.out.print("i = "+chiave.toString()+" Asserisci - valore: " + punteggiEAT26.get(chiave)+"\n");
					abusoIntegratori = abusoIntegratori + punteggiEAT26.get(chiave);
				}
				break;
				case 11: {
					//System.out.println("8)Esercizio fisico convulsivo - Minimo punteggio 1");
					//System.out.print("i = "+chiave.toString()+" Asserisci - valore: " + punteggiEAT26.get(chiave)+"\n");
					esercizioFisicoCompulsivo = esercizioFisicoCompulsivo + punteggiEAT26.get(chiave);
				}
				break;
			}			 
		}
		System.out.println("1)Tot EAT26= "+bassoPeso+" Basso peso - Minimo punteggio 3");
		System.out.println("2)Tot EAT26= "+pauraAumentarePeso+" Paura di aumentare di peso - Minimo punteggio 1");
		System.out.println("3)Tot EAT26= "+anomaliaPercepireCorpo+" Anomalia nel percepire peso e forma del corpo - Minimo punteggio 4");
		System.out.println("4)Tot EAT26= "+mancanzaControlloMangiare+" Mancanza di controllo nell'atto di mangiare - Minimo punteggio 4");
		System.out.println("5)Tot EAT26= "+comportamentiPrevenireAumentoPeso+" Comportamenti volti a prevenire l'aumento di peso - Minimo punteggio 10");
		System.out.println("6)Tot EAT26= "+disagioAbbuffate+" Disagio e sofferenza suscitate dalle abbuffate convulsive - Minimo punteggio 1");
		System.out.println("7)Tot EAT26= "+abusoIntegratori+" Abuso di integratori, diete iperproteiche - Minimo punteggio 2");
		System.out.println("8)Tot EAT26= "+esercizioFisicoCompulsivo+" Esercizio fisico convulsivo - Minimo punteggio 1");
		
		elaboraFattiEAT26(bassoPeso,pauraAumentarePeso,anomaliaPercepireCorpo,mancanzaControlloMangiare,comportamentiPrevenireAumentoPeso,
				disagioAbbuffate,abusoIntegratori,esercizioFisicoCompulsivo);

	 }
	 
	 private void elaboraFattiEAT26(int bassoPeso, int pauraAumentarePeso, int anomaliaPercepireCorpo, int mancanzaControlloMangiare,
			int comportamentiPrevenireAumentoPeso, int disagioAbbuffate, int abusoIntegratori, int esercizioFisicoCompulsivo) {
		String fattoBassoPesoSi = "presenta("+ ClassPanelPaziente.getnome_cognome() +",basso peso)";
		String fattoPauraAumentarePesoSi = "accusa("+ ClassPanelPaziente.getnome_cognome() +",intensa paura di aumentare di peso o dingrassare)";
		String fattoAnomaliaPercepireCorpoSi = "presenta("+ ClassPanelPaziente.getnome_cognome() +",valutazione di se inappropriata influenzata dalla forma e dal peso del corpo)";
		String fattoMancanzaControlloMangiareSi = "presenta("+ ClassPanelPaziente.getnome_cognome() +",mancanza di controllo nell atto di mangiare)";
		String fattoComportamentiPrevenireAumentoPesoSi = "accusa("+ ClassPanelPaziente.getnome_cognome() +",comportamento persistente che interferisce con laumento di peso nonostante il peso sia significativamente basso)";
		String fattoDisagioAbbuffateSi = "ha("+ ClassPanelPaziente.getnome_cognome() +",abbuffate compulsive che suscitano sofferenza e disagio)";
		String fattoAbusoIntegratoriSi = "presenta("+ ClassPanelPaziente.getnome_cognome() +",abuso di integratori anabolizzanti e diete iperproteiche)";
		String fattoEsercizioFisicoCompulsivoSi = "presenta("+ ClassPanelPaziente.getnome_cognome() +",esercizio fisico compulsivo)";
								
		//trascurati al momento i casi negativi
		if (bassoPeso >=3) {
			rw.writeFact(fattoBassoPesoSi,1);
		}
		 if (pauraAumentarePeso >= 1) {
			 rw.writeFact(fattoPauraAumentarePesoSi,1);
		}
		 if (anomaliaPercepireCorpo >= 4) {
			 rw.writeFact(fattoAnomaliaPercepireCorpoSi,1);
		}
		 if (mancanzaControlloMangiare >= 4) {
			 rw.writeFact(fattoMancanzaControlloMangiareSi,1);
		}
		 if (comportamentiPrevenireAumentoPeso >= 10) {
			 rw.writeFact(fattoComportamentiPrevenireAumentoPesoSi,1);
		}
		 if (disagioAbbuffate >= 1) {
			 rw.writeFact(fattoDisagioAbbuffateSi,1);
		}
		 if (abusoIntegratori >= 2) {
			 rw.writeFact(fattoAbusoIntegratoriSi,1);
		}
		 if (esercizioFisicoCompulsivo >= 1) {
			 rw.writeFact(fattoEsercizioFisicoCompulsivoSi,1);
		}
		
	}

	private void recuperaRisposta(JComboBox comboBox, int i){
         String selezionato = (String) comboBox.getSelectedItem();
         if (selezionato == "Sempre" || selezionato == "Molto spesso" || selezionato == "Spesso" || selezionato == "Qualche volta" || selezionato == "Raramente" || selezionato == "Mai") {
        	 risposteEAT26.put(i,selezionato);	
		}
                     	 
	 }
	 
	 public Integer getPunteggioEAT26(){
		 return punteggioEAT26;
	 }
	 
	 public String getPunteggioStrEAT26(){
		 return punteggioStrEAT26;
	 }
	 
	public void clearTextArea(JTextArea textArea){
			textArea.setText("");
		}

	public void setTextAreaItems(JTextArea textArea){
		String itemEAT26 = new String();
		
		clearTextArea(textArea);
		for (int i = 0; i < 26; i++) {
			//System.out.print((i+1)+") "	+ risposteEAT26.get(i)+ " - Punti: "+ punteggiEAT26.get(i) +"\n");
			if (risposteEAT26.containsKey(i)) {
				itemEAT26 = itemEAT26 + (i+1)+") "	+ risposteEAT26.get(i)+ " - Punti: "+ punteggiEAT26.get(i) +"\n";
			} else {
				itemEAT26 = itemEAT26 + (i+1)+ ") \n";
			}
			
		}
		textArea.setText(itemEAT26);
	}
	
	private void setLabelPunteggio(JLabel label){
		if (punteggioEAT26<20) {
			label.setText("Il punteggio è inferiore a 20, è IMPROBABILE la presenza di un disturbo dell'alimentazione");
		}
		else if (punteggioEAT26>= 20) {
			label.setText("Il punteggio è superiore o uguale a 20, è POSSIBILE la presenza di un disturbo dell'alimentazione");
		}
	}
}
