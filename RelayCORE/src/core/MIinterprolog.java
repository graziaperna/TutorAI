package core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.declarativa.interprolog.PrologEngine;
//import com.declarativa.interprolog.TermModelc; //UTILIZZATO DA INTERPROLOG PRESENTE SU RELAY;
import com.declarativa.interprolog.TermModel; //UTILIZZATO DA INTERPORLOG PRESO DA INTELEX(FUNZIONANTE)
import com.declarativa.interprolog.YAPSubprocessEngine;

import reasoningEngine.ReasoningEngineFactory;

/**
 * Questa classe interagisce con il motore inferenziale Prolog per l'esecuzione dei tre tipi di inferenza.
 * 
 * @author Davide Lofrese, Luca Musti
 */
public class MIinterprolog {	
	public static int idnewfact=100000;
	//   public final static String path="/usr/local/bin/yap"; //se non inserisco il path ne usa uno di default?
	//   public final static String path="C:\\Program Files (x86)\\Yap\\bin\\yap"; //se non inserisco il path ne usa uno di default?
	
	/**
	 * Oggetto contenente il motore inferenziale Prolog.
	 */
	static PrologEngine engine; 
	public static ArrayList<String> kbSystems= new ArrayList<String>();
	public static String metod;

	/**
	 * Istanzia un oggetto MIinterprolog.
	 *
	 * @param path Percorso dell'eseguibile del motore inferenziale Yap
	 */	
	public MIinterprolog(String path){
		super();
		engine= new YAPSubprocessEngine(path,true);	
	}
	
	/**
	 * Istanzia un oggetto MIinterprolog.
	 *
	 * @param engine Oggetto del motore inferenziale
	 */
	public MIinterprolog(PrologEngine engine){
		super();
		this.engine= engine;	
	}

	public static ArrayList<String> getTPL(){
		ArrayList<String> list=new ArrayList<String>();
		String goal = "nonDeterministicGoal(LiT+LiP+LiL,getTPL(LiT,LiP,LiL),ListModel)"; 
		TermModel solutionVars = (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);
		String sol=solutionVars.toString().replace("+", ",");
		sol=sol.replace("[", "");
		sol=sol.replace("]", "");
		String [] sols = sol.split(",");
		for(int i=0; i<sols.length;i++) {
			try {
				double d = Double.parseDouble(sols[i]);
				list.add("tmstmp"+sols[i]);
			} catch(NumberFormatException nfe) {  
				list.add(sols[i]);
			}
		}  
		return list;
	}

	public static String translateToInthelexTune(){
		engine.deterministicGoal("startTranslateToInthelexTune(Head)");
		String goal = "nonDeterministicGoal(Head,testa(Head),ListModel)"; 
		TermModel solutionVars = (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);
		String c=solutionVars.toString();
		c=c.replace("[[","[");
		c=c.replace("]]","]");
		engine.deterministicGoal("retract(testa(Head))");
		engine.deterministicGoal("retract(headFact(_,_))");
		return c;
	}

	/**
	 * Carica un file Prolog (base di conoscenza o modulo) nel motore inferenziale.
	 *
	 * @param nameMI Path del file Prolog da caricare
	 * @return true se il caricamento è stato effettuato, false altrimenti
	 */
	public static boolean loadMi(String nameMI) {
		return engine.consultAbsolute(new File(nameMI));
	}

	/**
	 * Avvia l'inferenza Forward.
	 *
	 * @param rulesPath Lista Prolog contenente i path dei file della conoscenza
	 * @param kbPath Path del file dove vengono memorizzati i fatti dedotti
	 * @return true se l'inferenza termina con successo, false altrimenti
	 */
	public static boolean runMIonServer_multikb(String rulesPath, String kbPath) {
		try {
			if (engine.deterministicGoal("runonserver(" + rulesPath + ",'" + kbPath + "')")) {
				return true;
			} else {
				System.out.println("Run MI Seva on Server failed");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void incrementIdnewFact() {  
		idnewfact++;
	}

	public static int getIdnewFact() {  
		return idnewfact;  
	}

	/**
	 * Avvia l'inferenza Rete.
	 *
	 * @return true se l'inferenza termina con successo, false altrimenti
	 */
	public static boolean startRete() {
		boolean app=engine.deterministicGoal("compile");
		boolean app2= engine.deterministicGoal("initialize");
		boolean app3= engine.deterministicGoal("go");
		return app && app2 && app3;

	}

	/**
	 * Avvia l'inferenza Rete.
	 *
	 * @param factsPath Path del file dove vengono memorizzati i fatti dedotti
	 * @param kbsPath Lista Prolog contenente i path dei file della conoscenza
	 * @return true se l'inferenza termina con successo, false altrimenti
	 */
	public static boolean startReteWithPaths(String factsPath, String kbsPath) {
		boolean app  = engine.deterministicGoal("consult('" + factsPath + "'), reconsult(" + kbsPath + ")");
		boolean app1 = engine.deterministicGoal("compile, initialize, go");
		return app && app1;
	}

	/**
	 * Avvia la spiegazione dei nuovi fatti dedotti tramite inferenza Forward.
	 *
	 * @return true se la spiegazione termina con successo, false altrimenti
	 */
	public static boolean startExplainer() {
		return engine.deterministicGoal("startExplainer");
	}

	public static boolean startReinizialize() {
		return engine.deterministicGoal("startReinizialize");
	}

	public static boolean startloaderFuzzi(String kb) {
		return engine.deterministicGoal("loaderfuzzifier('"+kb+"')");
	}

	public static boolean asserisci(String s){
		return engine.deterministicGoal("assert("+s+")");
	}

	public static boolean ritratta(String s){
		return engine.deterministicGoal("retract("+s+")");
	}
	
	/**
	 * Avvia l'inferenza Backward.
	 *
	 * @param obiettivo	Obiettivo da provare
	 * @return true se l'inferenza termina con successo, false altrimenti
	 */
	public static boolean startBackward(String obiettivo) {
		return engine.deterministicGoal("backward_java(" + obiettivo + ")");
	}
	
	/**
	 * Legge una domanda da porre all'utente durante l'inferenza Backward.
	 *
	 * @return Dizionario con cinque coppie chiave-valore:
	 * <ul>
	 * 	<li><b>question</b>: stringa con il testo della domanda
	 * 	<li><b>goal</b>: stringa con l'obiettivo della domanda
	 * 	<li><b>why</b>: stringa con la motivazione della domanda
	 * 	<li><b>menu</b>: lista di stringhe con le possibili risposte alla domanda<br>
	 *	&emsp;&emsp;<i>Nota</i>: per domande con risposta affermativa o negativa contiene solo una stringa vuota</li>
	 *	<li><b>patient</b>: stringa con il nome del paziente<br>
	 *	&emsp;&emsp;<i>Nota</i>: per domande con risposta affermativa o negativa questa chiave non è presente nel dizionario</li>
	 * </ul>
	 */
	public static HashMap<String, Object> getQuestion() {
		String goal = "nonDeterministicGoal(Question+Goal+Why,asking(Question,Goal,Why),ListModel)"; 
		TermModel questionTerm = (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);
		
		if (questionTerm.toString().equals("[]")) {
			// there is no yes/no question, check if there is a multiple answers question
			goal = "nonDeterministicGoal(Question+Goal+Why+Menu+Patient,asking_multiple(Question,Goal,Why,Menu,Patient),ListModel)";
			questionTerm = (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);
			 
			if (questionTerm.toString().equals("[]")) {
				// there is no multiple question
				// inference ends without more deductions
				return null;
			}
		}

		String[] questionSplitted = questionTerm.toString().replace("[", "").replace("]", "").split("\\+");
		
		HashMap<String, Object> question = new HashMap<>();
		question.put("question", questionSplitted[0]); // the question text (e.g. "Dove accusa dolore?")
		question.put("goal", questionSplitted[1]); // the question goal (e.g. "localizzazione(bob, dente_singolo)")
		question.put("why", questionSplitted[2]); // the question why explanation
		
		ArrayList<String> menuList = new ArrayList<>();
		
		if (questionSplitted.length > 3) {
			// multiple answers question			
			String[] menuSplitted = questionSplitted[3].split(",");				
			for (String menuOption : menuSplitted) {
				// add each answer to menuList
				menuList.add(menuOption);
			}
			
			question.put("patient", questionSplitted[4]); // the patient (e.g. "bob"), only needed for multiple answers questions
		} else {
			// yes/no question, add empty item to menuList
			menuList.add("");
		}
		
		question.put("menu", menuList);
		
		return question;
	}
	
	/**
	 * Legge l'obiettivo provato durante l'inferenza Backward.
	 *
	 * @return Stringa contenente l'obiettivo provato
	 */
	public static String getDedGoal() {
		String goal = "nonDeterministicGoal(Goal,ded_goal(Goal),ListModel)"; 
		TermModel dedGoalTerm = (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);
		return dedGoalTerm.toString().replace("[", "").replace("]", "");
	}
	
	/**
	 * Legge la traccia della deduzione ottenuta dopo l'inferenza Backward.
	 *
	 * @return Stringa contenente la traccia
	 */
	public static String getTrace() {
		String goal = "nonDeterministicGoal(Trace,ded_trace(Trace),ListModel)"; 
		TermModel traceTerm = (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);
		return traceTerm.toString().replace("[", "").replace("]", "");
	}

	/**
	 * Legge i fatti dedotti durante l'inferenza Rete.
	 *
	 * @return Lista con i fatti dedotti
	 */
	public static ArrayList<String> getdedfacts(){
		ArrayList<String> list=new ArrayList<String>();
		String goal = "nonDeterministicGoal(Idf+F+Cf,fact_ded(Idf,F,Cf),ListModel)"; 
		TermModel solutionVars = (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);
		String sol=solutionVars.toString().replace("[", "");
		sol=sol.replace("]", "");
		String [] sols= sol.split(",");
		for(int i=0; i<sols.length;i++) {
			String soluzione=sols[i];
			if(soluzione.contains("(") && soluzione.contains(")"))
				list.add("fact("+sols[i].replace("+", ",")+").\n");
			else
				if(soluzione.contains("(")) {
					do {
						i++;
						soluzione=soluzione.concat(","+sols[i]);		   		
					} while(!soluzione.contains(")"));
					list.add("fact("+soluzione.replace("+", ",")+").\n");
				}
		}
		return list;
	}

	/**
	 * Legge le spiegazioni dei fatti dedotti durante l'inferenza Forward.
	 *
	 * @return Lista con le spiegazioni dei fatti dedotti
	 */
	public static ArrayList<String> getexplaindedfacts() {   
		ArrayList<String> list = new ArrayList<String>();

		String goal = "nonDeterministicGoal(IdInfRule+CertDeduct,inferedrule(IdInfRule,CertDeduct),ListModel)"; 
		TermModel solutionVars = (TermModel) (engine.deterministicGoal(goal, "[ListModel]")[0]);

		ArrayList<String> inferedrules = new ArrayList<String>();
		getChildren(solutionVars,inferedrules);

		for(int i=0; i<inferedrules.size();i++) {
			String[] idInfRule_certDeduct = inferedrules.get(i).split("<>");
			String idInfRule = idInfRule_certDeduct[0];
			String certDeduct = idInfRule_certDeduct[1];

			goal = "nonDeterministicGoal(IdR,tracker(IdR, " + idInfRule + "),ListModel)"; 
			String idR = ((TermModel) engine.deterministicGoal(goal, "[ListModel]")[0]).getChildren()[0].toString();

			goal = "nonDeterministicGoal(DedFact+UsedFacts,rule(" + idR + ",DedFact, UsedFacts,_,_),ListModel)"; 
			solutionVars = (TermModel)(engine.deterministicGoal(goal, "[ListModel]")[0]);

			if(!solutionVars.toString().equals("[]")) {
				ArrayList<String> rules = new ArrayList<String>();
				getChildren(solutionVars,rules);

				for(int j=0; j<rules.size();j++) {
					String[] dedFact_usedFacts = rules.get(j).split("<>");
					String dedFact = dedFact_usedFacts[0].toString();    				

					goal = "nonDeterministicGoal(CertUFact,used_fact(_," + dedFact + ",CertUFact),ListModel)"; 
					solutionVars = (TermModel) (engine.deterministicGoal(goal, "[ListModel]")[0]);		

					if(!solutionVars.toString().equals("[]")) {
						Object[] explainer = engine.deterministicGoal("explainer(" + dedFact + ",_,DFactExp,_)", "[string(DFactExp)]");

						if(!explainer.toString().equals("[]")) {	   
							String showExplain = explainer[0].toString();
							int certRule = (int) Math.ceil(Float.valueOf(certDeduct) * 100);

							//SE E' STATO GIA SPIEGATO
							if(!list.contains(showExplain + " (certezza " + certRule + "%)" + ":"))
								list.add(showExplain + " (certezza " + certRule + "%)" + ":");

							String usedFactstring = dedFact_usedFacts[1].toString();    	    				
							usedFactstring = usedFactstring.replace("and([", "");
							usedFactstring = usedFactstring.replace("or([", "");
							usedFactstring = usedFactstring.replace("])", "");
							String[] usedFacts = usedFactstring.split(",");

							for(int k=0; k<usedFacts.length;k++) {
								if(!isNumber(usedFacts[k])){    								
									writingExplain(list,usedFacts[k]);
								}
							}
						}
					}
				}
			}
		}

		return list;
	}    
	
	public static void writingExplain(ArrayList<String> list, Object objectBody) {  
		String goal = "nonDeterministicGoal(Certainty, used_fact(_," + objectBody + ",Certainty), ListModel)";
		String certainty = ((TermModel) engine.deterministicGoal(goal, "[ListModel]")[0]).getChildren()[0].toString();

		if(certainty != null) {
			Object[] explainer = engine.deterministicGoal("explainer(" + objectBody + ",_,FactExp,_)", "[string(FactExp)]");

			if(explainer!=null && !explainer.toString().equals("[]")){
				//estraggo la spiegazione
				String showExplain = explainer[0].toString();
				//estraggo il valore di certezza                     
				int certRule = (int) Math.ceil(Float.valueOf(certainty).floatValue() * 100); 

				list.add(" - " + showExplain + " (certezza " + certRule + "%)");                     
			}
		}
	}

	/**
	 * Verifica se una stringa è un numero.
	 *
	 * @return true se la stringa è un numero, false altrimenti
	 */
	public static boolean isNumber(String string) {
		try {
			Float.parseFloat(string);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static void getChildren (TermModel solutions, ArrayList<String> risultati) {  
		TermModel []figli= solutions.getChildren();
		if(solutions.getChildren().length>0){
			if(!risultati.contains(figli[0].toString().replace("+", "<>")))
				risultati.add(figli[0].toString().replace("+", "<>"));
			if(!figli[1].toString().equals("[]"))
				getChildren(figli[1], risultati );  
		}

	}

	/**
	 * Reinizializza il motore inferenziale a seguito dell'inferenza Forward.
	 *
	 * @return true se la reinizializzazione è stata eseguita con successo, false altrimenti
	 */
	public boolean refresh() {
		return engine.deterministicGoal("refresh");		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////                            METODI NON UTILIZZATI                        ///////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
	
//	public static String translateToInthelexBody(){
//		engine.deterministicGoal("startTranslateToInthelexBody(Body)");
//		 String goal = "nonDeterministicGoal(Body,corpo(Body),ListModel)"; 
//			TermModel solutionVars = (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);
//			String c=solutionVars.toString();
//			c=c.replace("[[","");
//			c=c.replace("]]","");
//		return c;
//		//return 	engine.deterministicGoal("startTranslateToInthelexBody(Body)");
//
//		//return hasSolution("startTranslateToInthelexBody");	
//	
//   }
//	
//	public static boolean translateToInthelex(String file){
//		return 	engine.deterministicGoal("startTranslateToInthelex('"+file+"')");
//	}
//	
//	public static String translateToInthelexClassify(){
//		engine.deterministicGoal("startTranslateToInthelexClassify(Head)");
//		 String goal = "nonDeterministicGoal(Head,testa(Head),ListModel)"; 
//			TermModel solutionVars = (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);
//			String c=solutionVars.toString();
//			c=c.replace("[[","[");
//			c=c.replace("]]","]");
//			engine.deterministicGoal("retract(testa(Head))");
//		return c;
//	}
//	
//	public static boolean reloadMi(String nameMI) {
//		 return engine.consultAbsolute(new File(nameMI));
//	}
//	
//	public static boolean runMIonServer(String rulesPath, String kbPath) {
//		try {
//			if (engine.deterministicGoal("runonserver('" + rulesPath + "','" + kbPath + "')")) {
//				return true;
//			} else {
//				System.out.println("Run MI Seva on Server failed");
//				return false;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//    public static void incrementIdnewFact(int i) {  
//				idnewfact=idnewfact+i;        
//         }
//	
//	public static boolean startMiFuzzi(String kb) {
//		return 	engine.deterministicGoal("startMiFuzzi('"+kb+"')");
//	}
//	
//	public static boolean startGie() {
//		return 	engine.deterministicGoal("startGie");
//	}
//		
//	public static boolean startDefuzzi() {
//		return engine.deterministicGoal("startDefuzziandSave");
//	}
//	
//	public static boolean hasSolution(String command) {
//		return engine.deterministicGoal(command);
//	}
//	
//	public static ArrayList<String> getfinal_adjustment(){
//		ArrayList<String> list=new ArrayList<String>();
//		String goal = "nonDeterministicGoal(Istance+DedFact+Regulation,final_adj(Istance,DedFact,Regulation),ListModel)"; 
//		TermModel solutionVars = (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);
//		String sol=solutionVars.toString().replace("[", "");
//		 sol=sol.replace("]", "");
//		 String [] sols= sol.split(",");
//		for(int i=0; i<sols.length;i++) {
//   			list.add("final_adjustment("+sols[i].replace("+", ",")+")\n");
//		}  
//        return list;
//	}
//	
//	//Recupero l'id massimo dei fatti asseriti
//		public static int getMaxidf(){
//			int i=MIinterprolog.idnewfact;
//			Object[] bindings = engine.deterministicGoal("maxidf(M)", "[string(M)]");
//			if(bindings.length>0) {
//				String res=bindings[0].toString().replace("[", "");
//				res=res.replace("]", "");
//				i=Integer.valueOf(res);
//			}
//				return i;		
//		}
//
//		//ATTENZIONE: metodo mai testato, verificarne il funzionamento
//	    public static ArrayList<String> getexplaindedfacts2() {   
//	        ArrayList<String> list=new ArrayList<String> ();
//	        String goal = "nonDeterministicGoal(IdInfRule+CertDeduct,inferedrule(IdInfRule,CertDeduct),ListModel)"; 
//			TermModel solutionVars = (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);
//			ArrayList<String> inferedrules= new ArrayList<String>();
//			getChildren(solutionVars,inferedrules);
//			 for(int i=0; i<inferedrules.size();i++) {
//				   String [] IdInfRule_CertDeduct= inferedrules.get(i).split("<>");
//				   String IdInfRule= IdInfRule_CertDeduct[0];
//				   String CertDeduct =  IdInfRule_CertDeduct[1];
//			       goal = "nonDeterministicGoal(DedFact+UsedFacts,exp_rule(" + IdInfRule+ ",DedFact, UsedFacts,_,_),ListModel)"; 
//			       solutionVars= (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);
//			       if(!solutionVars.toString().equals("[]")){
//			       ArrayList<String> rules= new ArrayList<String>();
//			       getChildren(solutionVars,rules);
//			       for(int j=0; j<rules.size();j++){
//			    	String [] DedFact_UsedFacts= rules.get(j).split("<>");
//			    String DedFact=DedFact_UsedFacts[0].toString();
//			    String UsedFactstring=DedFact_UsedFacts[1].toString();
//			    UsedFactstring=UsedFactstring.replace("and([", "");
//			    UsedFactstring=UsedFactstring.replace("or([", "");
//			    UsedFactstring=UsedFactstring.replace("])", "");
//			    String [] UsedFacts = UsedFactstring.split("\\),");
//				       goal = "nonDeterministicGoal(CertUFact,used_fact(_,"+ DedFact+",CertUFact),ListModel)"; 
//				       solutionVars= (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);			   
//				       	if(!solutionVars.toString().equals("[]")){
//				  
//				            Object[] explainer = engine.deterministicGoal(
//									"explainer(" +DedFact + ",_,DFactExp,X)",
//									"[string(DFactExp)]");
//				            
//				            if(!explainer.toString().equals("[]")){	   
//				            String showExplain = explainer[0].toString();
//				        	int certRule = (int) Math.ceil(Float.valueOf(CertDeduct) * 100); 
//				        	//SE E' STATO GIA SPIEGATO
//				        	if(!list.contains(showExplain + " (certezza " + certRule + "%)" + ":"))
//				            list.add(showExplain + " (certezza " + certRule + "%)" + ":");
//				            for(int k=0; k<UsedFacts.length;k++)
//				            	if(!isNumber(UsedFacts[k])){
//				            	if(k<UsedFacts.length-1)
//				            		UsedFacts[k]=UsedFacts[k].concat(")");
//				            	writingExplain2(list,UsedFacts[k]);
//				            	}
//				            }
//				       }
//					   }
//				   }
//				}
//	        return list;
//	        }
//	    
//	  //ATTENZIONE: metodo mai testato, verificarne il funzionamento 
//	    public static void writingExplain2(ArrayList<String> list,Object objectBody) {  
//	    	 String goal = "nonDeterministicGoal(NC,get_condition(" + objectBody + ",NC),ListModel)"; 
//	 		TermModel solutionVars = (TermModel)(engine.deterministicGoal(goal,"[ListModel]")[0]);
//	 		ArrayList<String> conditions= new ArrayList<String>();
//	 		getChildren(solutionVars,conditions);
//	 		 for(int i=0; i<conditions.size();i++)
//	 			{
//	        Object[] used_fact = engine.deterministicGoal(
//	       		 "used_fact(_," + objectBody + ",Certainty)",
//						"[string(Certainty)]");
//	            if(used_fact!=null && !used_fact.toString().equals("[]")){	
//	           	 Object[] explainer = engine.deterministicGoal(
//	               		 "explainer(" + objectBody + ",_,FactExp,X)",
//	   						"[string(FactExp)]");          
//	           	   if(explainer!=null && !explainer.toString().equals("[]")){
//	                    //estraggo la spiegazione
//	                    String showExplain =explainer[0].toString();
//	                    //estraggo il valore di certezza                     
//	                    int certRule = (int) Math.ceil(Float.valueOf(used_fact[0].toString()).floatValue() * 100); 
//	                    //mostro a terminale
//	                    
//	                  list.add(" - " + showExplain + " (certezza " + certRule + "%)");                     
//	                    }
//	           	   
//	                }   else{
//	                	Object[] explainer = engine.deterministicGoal(
//	                      		 "explainer(" + objectBody + ",_,FactExp,X)",
//	          						"[string(FactExp)]");          
//	                  	   if(explainer!=null && !explainer.toString().equals("[]")){
//	                           //estraggo la spiegazione
//	                           String showExplain =explainer[0].toString();
//	                     
//	                           //mostro a terminale
//	                           list.add(" - " + showExplain);     
//	                }
//	            }  
//	            }     
//	            //list.add("\n\n");
//	    }
//
//    //PRENDO DURATA
//    public static String getDurata(){
//    	String stringdurata=new String("--");
//		Object[] bindings = engine.deterministicGoal(
//				"sec_elapsed(Round_sec)",
//				"[string(Round_sec)]");
//		if(bindings.length>0){
//			String res=bindings[0].toString().replace("[", "");
//			res=res.replace("]", "");
//			stringdurata="Durata elaborazione: " +res+ " secondi";
//		}
//    	
//    	return stringdurata;
//    }
}
