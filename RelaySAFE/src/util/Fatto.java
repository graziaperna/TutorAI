package util;

import java.util.ArrayList;

public class Fatto {
String fact;
String predicatointero;
float certezza;

public Fatto(String fact) {
	super();
	this.fact = fact;
	predicatointero=estraiPred();
	certezza=estraiCert();
}


public float estraiCert() {
	String sc=new String();
	float c=-1;
	String[] liststring=fact.split(",");
	
	sc= liststring[liststring.length-1].replaceAll("\\s",""); //toglie i blank
	sc=sc.substring(0, sc.length()-2); //toglie la parte finale del costrutto fact, ossia viene eliminato ")."
	
	try{
		c = Float.parseFloat(sc);
	}catch (NumberFormatException e){
		c=-1;
	}
	
	return c;
}


public String estraiPred(){
	String f=new String();
	String[] liststring=fact.split(",");
	
	for(int i=0;i<liststring.length;i++){
		if((i>0)&&(i<liststring.length-1))
			if(i==1)
				f=f+liststring[i].replace(" ", "");
			else
				f=f+","+liststring[i].replace(" ", "");
	}
	return f;
}

public String getPred(String f){
	String[] elem=f.split("\\(");
	return elem[0];
}

public String[] dividiArgomenti(String f){
    String arg=f.substring(f.indexOf("(")+1,f.indexOf(")"));
	String[] argomenti=arg.split(",");
	return argomenti;
}

public  String[] removeLastArg(String[] input) {
	String[] result= new String[input.length-1];

    for(int i=0;i<input.length-1;i++)
    	result[i]=input[i];

    return result ;
}

public String getPredicatointero() {
	return predicatointero;
}
/**restituisce la certezza del fatto
 * @return la certezza del fatto, -1 se ci sono stati errori durante il ritrovamento della certezza
 */
public float getCertezza() {
	return certezza;
}

public String getFact() {
	return fact;
}


public String convertFact(String predicatointero){
	String[] argomenti=dividiArgomenti(predicatointero);
	
	if(argomenti.length==2 && (XML.searchXMLname((argomenti[1]),"apprendimento/db.xml")==-1)){
		String [] argaux = new String [1];
		predicatointero=predicatointero.replace("(survey", "_"+argomenti[1]+"(survey");
		predicatointero=predicatointero.substring(0,predicatointero.indexOf(",")+1);
		predicatointero=predicatointero.replace(",", ")");
		argaux[0]=argomenti[0];
		argomenti=argaux;
		
	}
	for(int i=0;i<argomenti.length;i++){
		String newString =predicatointero.replace(argomenti[i], XML.searchXMLequivalentapprend(argomenti[i]));	
		predicatointero=newString;
		
	}
	return predicatointero;
	
}


}
