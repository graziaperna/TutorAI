package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gui.MainFrame;
import util.Fatto;
import util.XML;

public class RW {
	private static String knowledge_path;
	private static String facts_path;
	private static String examples_path;
	public static ArrayList<String> filtro;
	private int currentFactId = 0;
	public static int idnewfact=100000;

	public RW() { }

	public RW(String kP, String fP) { //ste la web application non la chiama, per questo realizzato il metodo setPaths
		knowledge_path=kP;
		facts_path=fP;
	}
	
	public RW(String kP, String fP, String eP) {
		knowledge_path=kP;
		facts_path=fP;
		examples_path=eP;
	}	
	
	public void resetKnowledge(String filename) {
		try { //ste prima li scriveva in knowledge, non in facts - VERIFICARE CHE FUNZIONI LO STESSO
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			out.println(":- dynamic (fact/3).");
			out.println(":- dynamic (rule/5).");
			out.close();
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
	}
	
	public void resetExamples() {
		try { 
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(examples_path)));
			out.close();
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
	}

	// SCRIVO NEL FILE KNOWLEDGE RIGA PER RIGA [non piu' CAMBIANDO L'ID DELLA REGOLA]
	/*ste ma legge riga riga, se un elemento di conoscenza e' suddiviso su piu' righe
	 * sballa tutto!!! Creare una procedura load_module nel motore inferenziale, che
	 * leggendo l'intero elemento di conoscenza modifichi l'id aggiungendo "nomeFile-"
	 * davanti all'id */
	public void writerules(ArrayList<String> files) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(knowledge_path)));
			out.println(":- dynamic (fact/3).");
			out.println(":- dynamic (rule/5).");
			for (int nfiles = 0; nfiles < files.size(); nfiles++) {
				ArrayList<String> file = readfile(files.get(nfiles).toString());
				for (int i = 0; i < file.size(); i++) 
					out.println(file.get(i));
			}
			out.close();
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
	}	
		
	public static void writeFact(String Fact) { //ste per compatibilita', da rimuovere?
		writeFact(facts_path,Fact);
	}
	
	public static void writeExample(String Example) {
		writeFact(examples_path, Example);
	}
	
	public static void writeFact(String file, String Fact) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			String stringa = Fact.toString();
			out.println(stringa);
			out.close();
			System.out.println("scritto fatto " + Fact);
		} catch (IOException e) {
			System.err.println("Errore durante la scrittura sul file");
		}
	}
		
	public void writeFact(String fatto, int prob) {
		currentFactId++;
		if(RW.retractFromFile(fatto, 1)){
			// controlla se la stringa contiene una parentesi
			if (fatto.contains("(")) {
				// formato fatto: ha(nome_cognome,fatto)
				String regex = "(.+)\\(([a-zA-Z_]+),(.*)\\)";
		        Pattern pattern = Pattern.compile(regex);
		        Matcher matcher = pattern.matcher(fatto);
		        
		        if (matcher.find()) {	
		        	fatto = matcher.group(1) + "(" + matcher.group(2) + "," + this.prologEscapeString(matcher.group(3)) + ")";
		        } else {
		        	// formato fatto: ha(fatto)
		        	regex = "(.+)\\((.*)\\)";
			        pattern = Pattern.compile(regex);
			        matcher = pattern.matcher(fatto);
			        
			        if (matcher.find()) {
			        	fatto = matcher.group(1) + "(" + this.prologEscapeString(matcher.group(2)) + ")";
			        }
		        }
			} else {
				fatto = this.prologEscapeString(fatto);
			}
			
			RW.writeFact(facts_path,"fact(" + currentFactId + "," + fatto + "," + (float) prob + ").");
		}				
	}

	// SCRIVO NELLA BASE DI CONOSCENZA
	//26-11-2014 modifica di Morisco_Petrera:
	//su richiesta del professore cambiamo la destinazione dei nuovi fatti cosi da non scrivere 
	//nello stesso file fatti e regole.
	//le regole andranno inserite in knowledge
	//i fatti verranno scritti in new_fact
	public static boolean writeInKnowledge(String istanza, String s) {
		try {
			if (!(s.startsWith("fact") && search(facts_path, s.toLowerCase().substring((s.toLowerCase().indexOf(",")) + 1)))) {
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(facts_path, true)));
				out.println(s.toLowerCase() + ".");
				out.close();
			}
			return true;
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
			return false;
		}
	}

	// CANCELLO NELLA BASE DI CONOSCENZA, //ste NO, NEL FILE!
	// option=1 cerco un'intera riga(in genere fatti), option=0 cerco una riga
	// che contiene la stringa, 
	public static boolean retractFromFile(String s, int option) { //ste per compatibilita', da rimuovere?
		return retractFromFile(facts_path, s, option);
	}
	
	public static boolean retractExampleFromFile(String example) {
		return retractFromFile(examples_path, example, 1);
	}
	
	public static boolean retractFromFile(String factFile, String s, int option) {
		try {
			File inFile = new File(factFile);
			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return false;
			}
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
			BufferedReader br = new BufferedReader(new FileReader(factFile));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (option == 0 && !line.equals(s + ".")) {
					pw.println(line);
					pw.flush();
				}
				if (option == 1 && !line.contains(s)) {
					pw.println(line);
					pw.flush();
				}
				if (option == 2 && !line.contains(s) ) { //ste e' uguale alla option 1??
					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			br.close();
			
			System.out.println("ritratto " + s);
			
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return false;
			}
			if (!tempFile.renameTo(inFile)) {
				System.out.println("Could not rename file");
				return false;
			}
			return true;
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			return false;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// LEGGO IL FILE RIGA PER RIGA //ste da eliminare?
	public static ArrayList<String> readfile(String str) {
		ArrayList<String> righe = new ArrayList<String>();
		try {
			BufferedReader myInput = new BufferedReader(new InputStreamReader(System.in));
			DataInputStream in = new DataInputStream(new FileInputStream(str));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null)
				righe.add(strLine);
			in.close();
		} catch (Exception e) {
			System.err.println("Errore: " + e.getMessage());
		}
		return righe;
	}

	// LEGGO I FATTI DA KNOWLEDGE
	public static ArrayList<String> readfileFact(String str) {
		ArrayList<String> righe = new ArrayList<String>();
		int flag = 0;
		try {
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader myInput = new BufferedReader(reader);
			FileInputStream fstream = new FileInputStream(str);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				if (!strLine.contains("%") && !strLine.contains(":-")
						&& !strLine.contains("rule")
						&& !strLine.contains("rfact") && !strLine.contains("-")
						&& !strLine.contains("demandable")
						&& !strLine.contains("*") && strLine.contains("fact")) {
					righe.add(strLine);
				}
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Errore: " + e.getMessage());
		}
		return righe;
	}

	// CERCO UNA STRINGA NEI FILES
	public static String searchString(ArrayList<String> files, String str) {

		if(str.equals("r")){//caso in cui il predicato sia un risultato di un'operazione
			return "sensori";
		}
		
		
		try {
			for (int i = 0; i < files.size(); i++) {
				InputStreamReader reader = new InputStreamReader(System.in);
				BufferedReader myInput = new BufferedReader(reader);
				FileInputStream fstream = new FileInputStream(files.get(i));
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				String strLine;
				while ((strLine = br.readLine()) != null) {
					{
						if (strLine.contains(str)) {
							if (files.get(i).contains("intellicare"))
								return "intellicare";
							if (files.get(i).contains("ekman"))
								return "ekman";
							if (files.get(i).contains("butler"))
								return "butler";
							if (files.get(i).contains("smart_menu"))
								return "smart_menu";
							if (files.get(i).contains("s_kitchen"))
								return "kitchen";
							else
								return "sensori";
						}
					}
				}
				in.close();
			}
		} catch (Exception e) {
			System.err.println("Errore: " + e.getMessage());
		}
		return "sensori";
	}

	public static ArrayList<String> readDed(String argomentoconv, String file,
			ArrayList<String> argomenticonvsg) {
		ArrayList<String> array = new ArrayList<String>();
		try {

			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader myInput = new BufferedReader(reader);
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int flag = 0;
			while ((strLine = br.readLine()) != null) {
				{
					if (!strLine.contains("%") && !strLine.contains(":-")
							&& !strLine.contains("fact")
							&& !strLine.contains("rfact")
							&& !strLine.contains("-")
							&& !strLine.contains("demandable")
							&& !strLine.contains("*")
							&& strLine.contains("rule")) {
						String[] split = strLine.split(",");
						Fatto f;						
						if (split[1].contains(")"))
							f = new Fatto(split[1]);
						else
							f = new Fatto(split[1] + "," + split[2]);
						//CONSIDERA SOLO LE DEDUZIONI
						if(filtro.contains(f.getPred(split[1]))){
						if (f.getFact().contains(")")) {
							String[] argomenti = f.dividiArgomenti(f.getFact());
							if (!file.contains("ekman")
									&& !file.contains("intellicare")) {
								if (argomenti.length < 3
										&& argomentoconv.contains("stato")) {
									String predicatoconvertito = new String();
									for (int j = 0; j < argomenti.length; j++) {
										if (j == 0 && (argomenti[j].contains("SURVEY") 
												|| argomenti[j].contains("X")))
											predicatoconvertito = f.getFact().replace(argomenti[j], argomentoconv);
										else
											predicatoconvertito = predicatoconvertito.replace(argomenti[j], argomenti[j] + "_" + argomentoconv);
									}
									if (!predicatoconvertito.equals(""))
										array.add(predicatoconvertito.replace(" ", ""));
								}
							} else {
								if (argomentoconv.contains("sg")) {
									String predicatoconvertito = new String();
									for (int j = 0; j < argomenti.length; j++) {
										if (j == 0)
											predicatoconvertito = f.getFact().replace(argomenti[j], argomentoconv);
										else if (!argomenti[j].equals("Y")) {
											if (XML.isDoubleOrInt(argomenti[j]) == -1)
												predicatoconvertito = predicatoconvertito.replace(argomenti[j], argomenti[j] + "_" + argomentoconv.substring(2));
											else
												predicatoconvertito = predicatoconvertito.replace(argomenti[j], "c" + argomenti[j] + "_" + argomentoconv.substring(2));
										} else {
											for (int k = 0; k < argomenticonvsg
													.size(); k++) {
												if (!argomenticonvsg.get(k)
														.equals(argomentoconv)) {
													predicatoconvertito = predicatoconvertito.replace(argomenti[j], argomenticonvsg.get(k));
													array.add(predicatoconvertito.replace(" ", ""));
													flag = 1;
												}
											}
										}
									}
									if (!predicatoconvertito.equals("") && flag == 0)
										array.add(predicatoconvertito.replace(" ", ""));
								}
							}
						}
					}}
				}
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Errore: " + e.getMessage());
		}
		return array;
	}	
	
	public static boolean search(String file, String s) {
		int flag = 0;
		try {
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader myInput = new BufferedReader(reader);
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				if (strLine.contains(s))
						return true;
				}
			in.close();
		} catch (Exception e) {
			System.err.println("Errore: " + e.getMessage());
		}
		return false;
	}
	
	public static void writetimestamp(){
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
	   
		String survey=year+String.format("%02d", month)+String.format("%02d", day)+String.format("%02d", hour)+String.format("%02d", minute)+String.format("%02d", second);

		writeInKnowledge("Relay","fact("+idnewfact+",timestamp("+survey+"),1)");
		idnewfact++;
		writeInKnowledge("Relay","fact("+idnewfact+",anno("+survey+","+year+"),1)");
		idnewfact++;
		writeInKnowledge("Relay","fact("+idnewfact+",mese("+survey+","+month+"),1)");
		idnewfact++;
		writeInKnowledge("Relay","fact("+idnewfact+",giorno("+survey+","+day+"),1)");
		idnewfact++;
		writeInKnowledge("Relay","fact("+idnewfact+",ora("+survey+","+hour+"),1)");
		idnewfact++;
		writeInKnowledge("Relay","fact("+idnewfact+",minuti("+survey+","+minute+"),1)");
		idnewfact++;
		writeInKnowledge("Relay","fact("+idnewfact+",secondi("+survey+","+second+"),1)");
		idnewfact++;
		
		ArrayList<String> timestampArray=countTimestamp();
		
		int k=0;
		if(timestampArray.size()>=MainFrame.ntimestamp) {
			for(int i=timestampArray.size()-1;i>=0;i--){
				k++;
				if(k>MainFrame.ntimestamp)
					retractFromFile(timestampArray.get(i),1);
			}
		}
		
		if(MainFrame.idtimestamp != null) {
			writeInKnowledge("Relay","fact("+idnewfact+",nexttimestamp("+survey+","+MainFrame.idtimestamp+"),1)");
			idnewfact++;
		}
		
		MainFrame.idtimestamp=survey;
		retractFromFile("ultimots",1);
		writeInKnowledge("Relay","fact("+idnewfact+",ultimots("+survey+"),1)");
		idnewfact++;
	}
	
	public static ArrayList<String> countTimestamp(){
		ArrayList<String> timestamp=new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(facts_path));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.contains(",timestamp(")) {
					int r1=line.indexOf("(")+1;
					int r2=line.indexOf(")")+1;
					String s=line.substring(r1, r2);
										
					int r3 = s.indexOf("(") + 1;
					int r4 = s.indexOf(")");
					String s1 = s.substring(r3, r4);

					timestamp.add(s1);
				}
			}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return timestamp;
	}
	
	private String prologEscapeString(String stringa) {
		// sostituisci apici con doppi apici per fare l'esame
		stringa = stringa.replace("'", "''");
		
		// controlla se contiene spazi
		if(stringa.contains(" ")) {
			stringa = "'" + stringa +"'";
        }
		
		return stringa;
	}
		
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////                            METODI NON UTILIZZATI                        ///////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
	
//	public static void setPaths(String kP, String fP) {
//		knowledge_path=kP;
//		facts_path=fP;
//	}
//	
//	public void resetKnowledge() { //ste per compatibilita', da rimuovere?
//		resetKnowledge(facts_path);
//	}
//	
//	// SCRIVO NEL FILE KNOWLEDGE RIGA PER RIGA CAMBIANDO L ID DELLA REGOLA
//	public void writeFact(ArrayList<String> lineFact) {
//		try {
//			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(facts_path)));
//			for (int j = 0; j < lineFact.size(); j++) {
//				String stringa = lineFact.get(j).toString();
//				out.println(stringa);
//			}
//			out.close();
//		} catch (IOException e) {
//			// exception handling left as an exercise for the reader
//		}
//	}
//	
//	// CANCELLO NELLA BASE DI CONOSCENZA TENENDO CONTO DEL TIMESTAMP, 
//	// option=1 cerco un'intera riga(in genere fatti), option=0 cerco una riga
//	// che contiene la stringa, 	
//	public static boolean retractinkowledgeTimestamp() {
//		try {
//			File inFile = new File("new_fact");
//			if (!inFile.isFile()) {
//				System.out.println("Parameter is not an existing file");
//				return false;
//			}
//			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
//			ArrayList<Long> timestamparray=getTimestamp();
//			BufferedReader br = new BufferedReader(new FileReader("new_fact"));
//			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
//			String line = null;
//			while ((line = br.readLine()) != null) {
//				boolean delete=false;
//				for(int i=0;i<(timestamparray.size()-MainFrame.ntimestamp);i++)			
//				if (line.contains("t"+String.valueOf(timestamparray.get(i)))) {
//					delete=true;
//					break;
//				}
//				if(!delete) {
//					pw.println(line);
//					pw.flush();
//				}
//			}
//			pw.close();
//			br.close();
//			if (!inFile.delete()) {
//				System.out.println("Could not delete file");
//				return false;
//			}
//			if (!tempFile.renameTo(inFile)) {
//				System.out.println("Could not rename file");
//				return false;
//			}
//			return true;
//		} catch (FileNotFoundException ex) {
//			ex.printStackTrace();
//			return false;
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			return false;
//		}
//	}
//	
//	public static ArrayList<Long> getTimestamp(){
//		 ArrayList<Long> timestamparray=new  ArrayList<Long> ();
//		 try {
//			BufferedReader br = new BufferedReader(new FileReader("new_fact"));
//			String line = null;
//				while ((line = br.readLine()) != null) {
//					if (line.contains("timestamp(")) {
//						String stringa=new String();
//						 stringa=line.substring(line.indexOf("timestamp(")+10,line.indexOf(")"));
//						// stringa=stringa.substring(stringa.indexOf("(")+1,stringa.indexOf(","));
//						Long l=Long.valueOf(stringa);
//						timestamparray.add(l);
//					}
//				}
//				br.close();
//				Collections.sort(timestamparray);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return timestamparray;
//	}
//	
//	public static String getUltimoTimestamp(){
//		String ultimots = null;
//		 try {
//			BufferedReader br = new BufferedReader(new FileReader("new_fact"));
//			String line = null;
//				while ((line = br.readLine()) != null) {
//					if (line.contains("ultimots(")) {
//						String stringa=new String();
//						 stringa=line.substring(line.indexOf("ultimots(")+9,line.indexOf(")"));
//						// stringa=stringa.substring(stringa.indexOf("(")+1,stringa.indexOf(","));
//						 ultimots=stringa;
//						 break;
//					}
//				}
//				br.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ultimots;
//	}
//	
//	// CANCELLO NELLA BASE DI CONOSCENZA, 
//	// option=1 cerco un'intera riga(in genere fatti), option=0 cerco una riga
//	// che contiene la stringa, option 2= cerco una riga che contiene due stringhe /
//	//Modifica inserita da Morisco-Petrera: possibilita' di cercare 2 stringhe.
//	public static boolean retractinkowledge2(String s, String r, int option) {
//		try {
//			File inFile = new File("new_fact");
//			if (!inFile.isFile()) {
//				System.out.println("Parameter is not an existing file");
//				return false;
//			}
//			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
//			BufferedReader br = new BufferedReader(new FileReader("new_fact"));
//			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
//			String line = null;
//			while ((line = br.readLine()) != null) {
//				if (option == 0 && !line.equals(s + ".")) {
//					pw.println(line);
//					pw.flush();
//				}
//				if (option == 1 && !line.contains(s)) {
//					pw.println(line);
//					pw.flush();
//				}
//				if (option == 2)
//					if (!line.contains(s) || !line.contains(r)) {
//					pw.println(line);
//					pw.flush();
//				}
//			}
//			pw.close();
//			br.close();
//			if (!inFile.delete()) {
//				System.out.println("Could not delete file");
//				return false;
//			}
//			if (!tempFile.renameTo(inFile)) {
//				System.out.println("Could not rename file");
//				return false;
//			}
//			return true;
//		} catch (FileNotFoundException ex) {
//			ex.printStackTrace();
//			return false;
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			return false;
//		}
//	}
//	
//	// CANCELLO NELLA BASE DI CONOSCENZA, 
//	// option=1 cerco un'intera riga(in genere fatti), option=0 cerco una riga
//	// che contiene la stringa, option 2= cerco una riga che contiene due stringhe /
//	//Modifica inserita da Morisco-Petrera: possibilit� di cercare 3 stringhe.
//	public static boolean retractinkowledge3(String s,String t, String r, int option) {
//		try {
//			File inFile = new File("new_fact");
//			if (!inFile.isFile()) {
//				System.out.println("Parameter is not an existing file");
//				return false;
//			}
//			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
//			BufferedReader br = new BufferedReader(new FileReader("new_fact"));
//			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
//			String line = null;
//
//			while ((line = br.readLine()) != null) {
//				if (option == 0 && !line.equals(s + ".")) {
//					pw.println(line);
//					pw.flush();
//				}
//				if (option == 1 && !line.contains(s)) {
//					pw.println(line);
//					pw.flush();
//				}
//				if (option == 2)
//					if (!line.contains(s) || !line.contains(r) || !line.contains(t)) {
//					pw.println(line);
//					pw.flush();
//				}
//			}
//			pw.close();
//			br.close();
//			if (!inFile.delete()) {
//				System.out.println("Could not delete file");
//				return false;
//			}
//			if (!tempFile.renameTo(inFile)) {
//				System.out.println("Could not rename file");
//				return false;
//			}
//			return true;
//
//		} catch (FileNotFoundException ex) {
//			ex.printStackTrace();
//			return false;
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			return false;
//		}
//	}
//	
//	// LEGGO I NUOVI FATTI DA KNOWLEDGE
//	public static ArrayList<String> readfileNewFact(String str) {
//		ArrayList<String> righe = new ArrayList<String>();
//		int flag = 0;
//		try {
//			InputStreamReader reader = new InputStreamReader(System.in);
//			BufferedReader myInput = new BufferedReader(reader);
//			FileInputStream fstream = new FileInputStream(str);
//			DataInputStream in = new DataInputStream(fstream);
//			BufferedReader br = new BufferedReader(new InputStreamReader(in));
//			String strLine;
//			while ((strLine = br.readLine()) != null) {
//				if (strLine.equals("%New deduct facts from inference"))
//					flag = 1;
//				if (flag == 2) {
//					righe.add(strLine);
//					//righe.add("\n"); Modifica effettuata da morisco-petrera: questo comando interferisce con la size di righe
//				}
//				if (flag==1) flag++; //Modifica effettuata da morisco-petrera per non inserire all'interno dell'arraylist la riga
//				                     // %New deduct facts from inference
//			}
//			in.close();
//		} catch (Exception e) {
//			System.err.println("Errore: " + e.getMessage());
//		}
//		return righe;
//	}
//	
//	public static void writeTUNfile(String etichetta, String condizioni,String argomenti) {
//
//		try {
//
//			PrintWriter out = new PrintWriter(new BufferedWriter(
//					new FileWriter("apprendimento/apprendimento.tun", true)));
//			out.println(etichetta + ":-pred_fitt("+ argomenti+"cost_fitt),"+ condizioni + "\n");
//			out.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	//ELEMENTI TEMPORANEI	
//	public static boolean TEMP() {
//		try {
//			File inFile = new File("apprendimento/apprendimentocopy.tun");
//			if (!inFile.isFile()) {
//				System.out.println("Parameter is not an existing file");
//				return false;
//			}
//			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
//			BufferedReader br = new BufferedReader(new FileReader("apprendimento/apprendimento.tun"));
//			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
//			String line = null;
//
//			//ArrayList<String> nomi=XML.ottieninomiXML();
//			while ((line = br.readLine()) != null) {
//				   if(!line.equals("")){
//                    int ind=line.indexOf(":-");             
//                    int inds=line.indexOf("survey");
//                    if(XML.isDoubleOrInt(line.substring(inds+6,inds+8))==0)
//                    	inds=inds+6+2+1;
//                    else
//                    	inds=inds+6+1+1;
//                    int k=1;
//                    String s=new String();
//                    do{
//                    	s=line.substring(inds,inds+k);
//                    	k++;
//                    }while(!s.contains(")"));
//                    s=s.substring(0,s.length()-1);
//                    Fatto f;
//                    String[] argomenti;
//                    String pred;
//                    int i;
//                    int j=0;
//                   
//                    //CODA
//                    String [] sl=s.split("_");
//                    String ssing=sl[0]+"_"+sl[1];
//                    String sub=line.substring(ind);
//                    String [] sotto=sub.split("\\),");
//			        for(i=1;i<sotto.length;i++){
//			        	sotto[i]=sotto[i]+")";
//			        	 f=new Fatto(sotto[i]);
//			        	argomenti=f.dividiArgomenti(sotto[i]);
//			        	 pred=f.getPred(sotto[i]);
//			        	 j=0;
//			        	if(argomenti.length>1 && argomenti[j].contains("sg")){
//			        		if(j==0 && argomenti[j+1].contains("sg"))
//				        		line=line.replace(pred+"("+argomenti[j]+","+argomenti[j+1],pred+"("+argomenti[j]+","+argomenti[j+1]+","+s);
//			        		else
//			        			line=line.replace(pred+"("+argomenti[j]+","+argomenti[j+1],pred+"("+argomenti[j]+","+ssing+","+argomenti[j+1]);
//			        		String predarg=pred+"("+argomenti[j]+","+ssing+",";
//			        		for(j=1;j<argomenti.length;j++){			        	
//			        			if(!argomenti[j].contains("sg")){	
//			        				line=line.replace(predarg+argomenti[j]+",",predarg+"val_"+ pred +"_"+j+"_"+argomenti[0]+"_"+s+",");
//			        			line=line.replace(predarg+argomenti[j]+")",predarg+"val_"+ pred +"_"+j+"_"+argomenti[0]+"_"+s+")");	        				
//			        			line=line.replace(argomenti[j], "val_"+ pred +"_"+j+"_"+argomenti[0]+"_"+s);
//			        			}
//			        			predarg=predarg+argomenti[j]+",";
//			        		}
//			        	} else {
//			        		if(argomenti.length==1 && argomenti[0].contains("sg") && !search("apprendimento/db.xml",pred)) {
//			        			line=line.replace(pred+"("+argomenti[0]+")",pred+"("+argomenti[0]+","+s+")");
//			        		}
//			        	}
//			        }
//			        //TESTA
//                   sub =line.substring(1,ind-1);
//                   sotto=sub.split("\\),");
//                    for(i=0;i<sotto.length;i++){
//                    	sotto[i]=sotto[i]+")";
//			        	f=new Fatto(sotto[i]);
//			        	argomenti=f.dividiArgomenti(sotto[i]);
//			        	pred=f.getPred(sotto[i]);
//			        	if(argomenti.length==1 && argomenti[j].contains("sg") && !search("apprendimento/db.xml",pred)){		        				        	
//		        			line=line.replace(pred+"("+argomenti[j]+")",pred+"("+argomenti[j]+","+s+")");	        
//		        		}
//			        	else
//			        		if(argomenti.length>1 && argomenti[j].contains("sg"))
//				        		if(j==0 && argomenti[j+1].contains("sg"))
//					        		line=line.replace(pred+"("+argomenti[j]+","+argomenti[j+1],pred+"("+argomenti[j]+","+argomenti[j+1]+","+s);				        
//                    }
//			        pw.println(line);
//					pw.flush();
//				   }
//				   else
//					   pw.println("");
//			}
//			pw.close();
//			br.close();
//
//			if (!inFile.delete()) {
//				System.out.println("Could not delete file");
//				return false;
//			}
//			if (!tempFile.renameTo(inFile)) {
//				System.out.println("Could not rename file");
//				return false;
//			}
//			return true;
//		} catch (FileNotFoundException ex) {
//			ex.printStackTrace();
//			return false;
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			return false;
//		}
//	}
//	
//	public static void numeraKB() throws IOException{
//		int num=1;
//		for(int i=0; i< MIinterprolog.kbSystems.size();i++){
//			File tempFile = new File("kb_"+MIinterprolog.kbSystems.get(i));
//			if(tempFile.exists())
//			tempFile.delete();
//			tempFile.createNewFile();
//			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
//			BufferedReader br = new BufferedReader(new FileReader(
//					"backupKB/kb_"+MIinterprolog.kbSystems.get(i)));
//			String line = null;
//			while ((line = br.readLine()) != null) {
//				if ((line.contains("rule("))&&(line.charAt(6)==',')&&(!(line.contains("%")))){line="rule("+num+line.substring(6); num++;}//fine if
//				else if ((line.contains("rule("))&&(line.charAt(7)==',')&&(!(line.contains("%")))){line="rule("+num+line.substring(7); num++;}//fine if
//				else if ((line.contains("rule("))&&(line.charAt(8)==',')&&(!(line.contains("%")))){line="rule("+num+line.substring(8); num++;}//fine if
//				pw.println(line);
//				pw.flush();
//				line=null;
//			}//fine while
//			pw.close();
//			br.close();	
//		}
//	}
//	
//	public static boolean replaceinkowledge(String sostituire, String sostituto) {
//		try {
//			File inFile = new File("new_fact");
//			if (!inFile.isFile()) {
//				System.out.println("Parameter is not an existing file");
//				return false;
//			}
//			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
//			BufferedReader br = new BufferedReader(new FileReader("new_fact"));
//			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
//			String line = null;
//			while ((line = br.readLine()) != null) {
//				if (line.contains(sostituire)) {
//					pw.println(line.replace(sostituire, sostituto));
//					pw.flush();
//				} else {
//					pw.println(line);
//					pw.flush();
//				}
//			}
//			pw.close();
//			br.close();
//			if (!inFile.delete()) {
//				System.out.println("Could not delete file");
//				return false;
//			}
//			if (!tempFile.renameTo(inFile)) {
//				System.out.println("Could not rename file");
//				return false;
//			}
//			return true;
//		} catch (FileNotFoundException ex) {
//			ex.printStackTrace();
//			return false;
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			return false;
//		}
//	}
}

