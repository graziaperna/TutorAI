package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import gui.MainFrame;
import service.RW;




public class XML {
	private static final int HashMap = 0;
	Document doc;
	int soggetto1;
	int soggetto2;
	int sensore;

	public XML() {
		// TODO ATTNEZIONE, potrebbe andare in loop arrivati a 100 soggetti!!!!
		Random randomGenerator = new Random();
		do {
			this.soggetto1 = randomGenerator.nextInt(100);
			do {
				this.soggetto2 = randomGenerator.nextInt(100);
			} while (soggetto1 == soggetto2);
		} while (searchXMLid(String.valueOf(soggetto1), "apprendimento/db.xml")
				&& searchXMLid(String.valueOf(soggetto2),
						"apprendimento/db.xml"));
		this.sensore = randomGenerator.nextInt(100);
	}

	/**
	 * permette di richiamare la randomizzazione anche all'interno del codice
	 * 
	 * @return
	 */
	 int getNuovoIdSoggetto() {
		// TODO ATTNEZIONE, potrebbe andare in loop arrivati a 1000 soggetti!!!!
		Random randomGenerator = new Random();
		int nuovoId = 0;
		do {
			nuovoId = randomGenerator.nextInt(1000);
		} while (searchXMLid(String.valueOf(nuovoId), "apprendimento/db.xml"));
		return nuovoId;
	}
	
	public String getStringIdPersona(String nome) {

		String id=new String("");
		SAXBuilder builder = new SAXBuilder();
		Document doc;
		try {
			doc = builder.build(new File("apprendimento/db.xml"));
			Element rootElement = doc.getRootElement();
			List children = rootElement.getChildren();
			Iterator iterator = children.iterator();
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				
				if (element.getAttribute("nome").getValue().contains(nome))
					return element.getName();
			}
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	return id;
	}
	
	
	public void createXML() {
		try {
			PrintWriter writer = new PrintWriter(
					"apprendimento/apprendimento.xml");
			writer.print("");
			writer.close();
			Element rootElement = new Element("Root");
			Document document = new Document(rootElement);
			Element condizioni = new Element("Condizioni");
			rootElement.addContent(condizioni);
			Element etichetta = new Element("Etichetta");
			rootElement.addContent(etichetta);
			converFact(condizioni);
			XMLOutputter outputter = new XMLOutputter();
			outputter.output(document, new FileOutputStream(
					"apprendimento/apprendimento.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	public void converFact(Element condizioni) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		String pattern = "HH";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		long time = date.getTime();
		cal.setTimeInMillis(time);
		int year = cal.get(Calendar.YEAR);
		int month = (cal.get(Calendar.MONTH)) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		// int hour = cal.get(Calendar.HOUR);
		int hour = Integer.parseInt(sdf.format(time));
		int minute = cal.get(Calendar.MINUTE);
		String stato = "_" + year + "_" + month + "_" + day + "_" + hour + "_" + minute;
		// saveXMLstatodb(stato);
		ArrayList<String> files = new ArrayList<String>();
		ArrayList<String> arrayfact = RW.readfileFact("new_fact"); // modifica Morisco Petrera

		String nome1 = new String();
		String nome2 = new String();
		String nomesg1 = new String();
		String nomesg2 = new String();
		int numero = 0;
		for (int i = 0; i < arrayfact.size(); i++) {
			// System.out.println(arrayfact.get(i));
			Fatto fatto = new Fatto(arrayfact.get(i));
			String p = fatto.getPredicatointero();
			String pred = fatto.getPred(p);
			String[] argomenti = fatto.dividiArgomenti(p);
			String nome = new String();

			if (fatto.getCertezza() == 0)
				continue; // salta i predicati con certezza 0 e continua con i
							// successivi cicli

			String res = RW.searchString(files, pred);

			if (pred.equals("r")) {// caso in cui il predicato sia un risultato
									// di un'operazione
				for (int arg = 0; arg < argomenti.length; arg++) {
					if (argomenti[arg].contains("[")) {
						argomenti[arg] = argomenti[arg].replace("[", "");
					}
					if (argomenti[arg].contains("]")) {
						argomenti[arg] = argomenti[arg].replace("]", "");
					}
				}

				continue; // se si vuole inserire r tra i predicati da
							// apprendere, commentare il <<continue;>>
			}

			 /*else if (arrayfact.get(i).contains("soggetto2(")) {
					nomesg2 = "sg";
					int id = searchXMLname(argomenti[0], "apprendimento/db.xml");
					if (id != -1) {
						numero = id;
						soggetto2 = id;
					} else {
						numero = soggetto2;
						saveXMLiddb("sg" + numero, argomenti[0]);
					}
					nome2 = argomenti[0];
					nome = nomesg2;
				} else if (!nome1.equals("")
						&& arrayfact.get(i).contains(nome1)) {
					nomesg1 = "sg";
					nome = nomesg1;
					numero = soggetto1;
				} else if (!nome2.equals("")
						&& arrayfact.get(i).contains(nome2)) {
					nome = nomesg2;
					numero = soggetto2;
				}
				saveXMLnodocondizioni(pred, argomenti, nome,
						String.valueOf(numero), condizioni);
			} else if (res.equals("intellicare") || res.equals("kitchen")) {
				int id = searchXMLname(argomenti[0], "apprendimento/db.xml");
				if (id != -1) {
					numero = id;
					soggetto1 = id;
				} else {
					numero = soggetto1;
					saveXMLiddb("sg" + numero, argomenti[0]);
				}
				nome1 = argomenti[0];
				nome = "sg";
				saveXMLnodocondizioni(pred, argomenti, nome,
						String.valueOf(numero), condizioni);
 */
			
				if (res.equals("smart_menu")) {
				nome = "stato_s";

				// /*
				if (pred.equals("cliente")) {
					nomesg1 = "sg";
					int id = searchXMLname(argomenti[0], "apprendimento/db.xml");

					if (id != -1) {
						numero = id;
						soggetto1 = id;
					} else {
						numero = getNuovoIdSoggetto();
						saveXMLiddb("sg" + numero, argomenti[0]);
					}
					nome1 = argomenti[0];
					nome = nomesg1;
					saveXMLnodocondizioni(pred, argomenti, nome,
							String.valueOf(numero), condizioni);

				} else // */
				if (pred.equals("temperaturaTavolo")) {
					try {
						int temp = Integer.parseInt(argomenti[1]);
						// ufunctinput(58,temp_esterna,[molto_bassa,bassa,medio_bassa,medio_alta,alta,molto_alta],[[[0,5],6],[5,[6,10],11],[10,[11,24],25],[24,[25,30],31],[30,[31,40],41],[40,[41,100]]]).
						if (temp < 6) {
							argomenti[1] = "molto_bassa";
						} else if ((temp >= 6) && (temp < 11)) {
							argomenti[1] = "bassa";
						} else if ((temp >= 11) && (temp < 25)) {
							argomenti[1] = "medio_bassa";
						} else if ((temp >= 25) && (temp < 31)) {
							argomenti[1] = "medio_alta";
						} else if ((temp >= 31) && (temp < 41)) {
							argomenti[1] = "alta";
						} else if (temp >= 41) {
							argomenti[1] = "molto_alta";
						}
					} catch (NumberFormatException e) {
						argomenti[1] = "valore_errato";
						e.printStackTrace();
					}

					saveXMLnodocondizioni(pred, argomenti, "stato_s", stato,
							condizioni);

					/*
					 * }else if(cond){ TODO inserire controlli su stati numerici
					 * di predicati da discretizzare
					 */
				} else {// tutte gli altri predicati di smart menu

					// al 2� CARICA/DEDUCI i fatti dedotti, vengono presi come
					// iniziali. ecco perch� vengono presi anche r(X,ID)
					saveXMLnodocondizioni(pred, argomenti, "stato_s", stato,
							condizioni);
				}

			} 
				else					
					if (arrayfact.get(i).contains("persona(")) {

							int id = searchXMLname(argomenti[0], "apprendimento/db.xml");
							if (id != -1) {
								numero = id;					
							} else {
								numero = getNuovoIdSoggetto();
								saveXMLiddb("pers" + numero, argomenti[0]);
							}				
							nome = "pers";
							saveXMLnodocondizioni(pred, argomenti, nome,
									String.valueOf(numero), condizioni);
						}
					else					
						if (arrayfact.get(i).contains("survey")) {
							saveXMLnodocondizioni(pred, argomenti, "stato_s", stato,
									condizioni);
						}
						else {
				saveXMLnodocondizioni(pred, argomenti, getStringIdPersona(argomenti[0]), getStringIdPersona(argomenti[0]).replace("pers", ""),
						condizioni);
			}

		}
	}

	public boolean searchXMLapprendimento(String stringa, String file,
			String nodopadre) {

		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(file));
			Element rootElement = doc.getRootElement();
			Element categoriaElement = rootElement.getChild(nodopadre);
			List children = categoriaElement.getChildren();
			Iterator iterator = children.iterator();
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				if (element.getName().contains(stringa))
					return true;
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean searchXMLid(String stringa, String file) {

		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(file));
			Element rootElement = doc.getRootElement();
			List children = rootElement.getChildren();
			Iterator iterator = children.iterator();
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				if (element.getName().contains(stringa))
					return true;
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public int countnodeXML(String stringa, String file) {
		int i = 0;
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(file));
			Element rootElement = doc.getRootElement();
			List children = rootElement.getChildren();
			Iterator iterator = children.iterator();
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				if (element.getName().contains(stringa))
					i++;
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;

	}

	public static int searchXMLname(String stringa, String file) {

		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(file));
			Element rootElement = doc.getRootElement();
			List children = rootElement.getChildren();
			Iterator iterator = children.iterator();
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				String nome = element.getAttribute("nome").getValue();
				if (nome != null && nome.equals(stringa))
					return Integer.parseInt(element.getName().substring(4));//4 perchè pers(...)
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;

	}

	public void saveXMLiddb(String id, String nome) {

		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File("apprendimento/db.xml"));
			Element rootElement = doc.getRootElement();
			Element idelem = new Element("pers"+id);
			Attribute nomeattrib = new Attribute("nome", nome);
			idelem.setAttribute(nomeattrib);
			rootElement.addContent(idelem);
			XMLOutputter outputter = new XMLOutputter();
			outputter.output(doc, new FileOutputStream("apprendimento/db.xml"));

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void saveXMLstatodb(String stato) {

		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File("apprendimento/db.xml"));
			Element rootElement = doc.getRootElement();
			Element idelem = new Element(stato);
			rootElement.addContent(idelem);
			XMLOutputter outputter = new XMLOutputter();
			outputter.output(doc, new FileOutputStream("apprendimento/db.xml"));

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void saveXMLnodocondizioni(String pred, String[] argomenti,
			String nome, String numero, Element condizioni) {
		Element predelement = new Element(pred);
		condizioni.addContent(predelement);
		for (int j = 0; j < argomenti.length; j++) {		
			if (isDoubleOrInt(argomenti[j]) == -1
					&& condizioni.getChild(argomenti[j]) == null && j == 0
					&& argomenti[j].contains("survey")) {
				Element agomelement = new Element(argomenti[j]);
				condizioni.addContent(agomelement);
				Element argomelementnumero2 = new Element(nome
						+ argomenti[j].substring(6) + numero);
				agomelement.addContent(argomelementnumero2);
				agomelement.setAttribute("argomento", "a");
			}
			if (isDoubleOrInt(argomenti[j]) == -1
					&& condizioni.getChild(argomenti[j]) == null && j == 0 && !nome.equals("")) {
				Element agomelement = new Element(argomenti[j]);
				condizioni.addContent(agomelement);
				Element argomelementnumero2 = new Element(nome + numero);
				agomelement.addContent(argomelementnumero2);
				agomelement.setAttribute("argomento", "a");
			}
			if (isDoubleOrInt(argomenti[j]) == -1 && j == 0
					&& !argomenti[j].contains("survey") && !nome.equals(""))
				argomenti[j] = nome;
			if (j > 0 && isDoubleOrInt(argomenti[j]) == -1) {
				if (searchXMLname((argomenti[j]), "apprendimento/db.xml") == -1) {						
					if(condizioni.getChild(argomenti[j])==null){
						Element agomelement = new Element(argomenti[j]);
					condizioni.addContent(agomelement);
					Element argomelementnumero2 = new Element(argomenti[j]
							+ numero);
					agomelement.addContent(argomelementnumero2);
					agomelement.setAttribute("argomento", "a");}
				}

			}
			if (argomenti[j].contains("survey")) {
				String nodo = nome + argomenti[j].substring(6) + numero;
				Element argomelementnumero1 = new Element(nodo);
				predelement.addContent(argomelementnumero1);
			} else if (j > 0 && isDoubleOrInt(argomenti[j]) == 0) {
				Element argomelementnumero1 = new Element(pred.substring(0, 4)
						+ j + "_" + argomenti[j] + numero);
				predelement.addContent(argomelementnumero1);
			}
			if (!argomenti[j].contains("survey")
					&& isDoubleOrInt(argomenti[j]) == -1) {
				int n;
				if(nome.equals("")){
					Element agomelement = new Element(pred);			
					agomelement.addContent(new Element(argomenti[j]));
					condizioni.addContent(agomelement);
				}else
				if (j > 0
						&& (n = searchXMLname((argomenti[j]),
								"apprendimento/db.xml")) != -1) {
					Element argomelementnumero1 = new Element("sg" + n);
					predelement.addContent(argomelementnumero1);
				} else {
					if (argomenti[j].contains("pers")){
						Element argomelementnumero1 = new Element(argomenti[j]);
						predelement.addContent(argomelementnumero1);}
					else
					if (isDoubleOrInt(argomenti[j]) == -1) {
						Element argomelementnumero1 = new Element(argomenti[j]
								+ numero);
						predelement.addContent(argomelementnumero1);
					}
				}
			}

		}
	}

	public static void deleteXMLnode(String stringa) {

		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(
					"apprendimento/apprendimento.xml"));
			Element rootElement = doc.getRootElement();
			Element etichettaElement = rootElement.getChild("Etichetta");
			etichettaElement.removeChild(stringa);
			XMLOutputter outputter = new XMLOutputter();
			outputter.output(doc, new FileOutputStream(
					"apprendimento/apprendimento.xml"));
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean addXMLnodeEtichetta(String pred, String[] argomenti,
			String option) {

		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(
					"apprendimento/apprendimento.xml"));
			Element rootElement = doc.getRootElement();
			Element etichettaElement = rootElement.getChild("Etichetta");
			Element etichettaPred = new Element(pred);
			if (etichettaElement.getChild(pred) != null)
				etichettaElement.removeChild(pred);
			etichettaElement.addContent(etichettaPred);
			for (int i = 0; i < argomenti.length; i++) {
				Element argomentElement = new Element(argomenti[i]);
				etichettaPred.addContent(argomentElement);
			}
			if (option.equals("not")) {
				Attribute optionattrib = new Attribute("option", option);
				etichettaPred.setAttribute(optionattrib);
			}
			XMLOutputter outputter = new XMLOutputter();
			outputter.output(doc, new FileOutputStream(
					"apprendimento/apprendimento.xml"));
			return true;
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static String searchXMLequivalentapprend(String stringa) {

		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(
					"apprendimento/apprendimento.xml"));
			Element rootElement = doc.getRootElement();
			Element etichettaElement = rootElement.getChild("Condizioni");
			List children = etichettaElement.getChildren();
			Iterator iterator = children.iterator();
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				if (element.getName().equals(stringa)) {
					List childrenEticEquivalente = element.getChildren();
					Iterator iteratorEticEquivalente = childrenEticEquivalente
							.iterator();
					if (iteratorEticEquivalente.hasNext()) {
						Element elementEticEquivalente = (Element) iteratorEticEquivalente
								.next();
						return elementEticEquivalente.getName();
					}
				}

			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static String ottienicondizioniXML() {
		String condizioni = new String();
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(
					"apprendimento/apprendimento.xml"));
			Element rootElement = doc.getRootElement();
			Element etichettaElement = rootElement.getChild("Condizioni");
			List children = etichettaElement.getChildren();
			Iterator iterator = children.iterator();
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				condizioni = condizioni + element.getName() + "(";
				List childrenEticEquivalente = element.getChildren();
				Iterator iteratorEticEquivalente = childrenEticEquivalente
						.iterator();
				while (iteratorEticEquivalente.hasNext()) {
					Element elementEticEquivalente = (Element) iteratorEticEquivalente
							.next();
					condizioni = condizioni + elementEticEquivalente.getName()
							+ ",";
				}
				condizioni = condizioni.substring(0, condizioni.length() - 1)
						+ "),";
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		condizioni = condizioni.substring(0, condizioni.length() - 1) + ".";
		return condizioni;
	}

	public static Hashtable<String, String> ottieniargomentiXMLtable() {
		Hashtable<String, String> argomenti = new Hashtable<String, String>();
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(
					"apprendimento/apprendimento.xml"));
			Element rootElement = doc.getRootElement();
			Element etichettaElement = rootElement.getChild("Condizioni");
			List children = etichettaElement.getChildren();
			Iterator iterator = children.iterator();
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				if (element.getAttribute("argomento") != null) {
					List childrenEticEquivalente = element.getChildren();
					Iterator iteratorEticEquivalente = childrenEticEquivalente
							.iterator();
					Element elementEticEquivalente = (Element) iteratorEticEquivalente
							.next();
					argomenti.put(elementEticEquivalente.getName(),
							element.getName());
				}
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return argomenti;
	}

	public static ArrayList<String> ottieninomiXML() {
		ArrayList<String> nomi = new ArrayList<String>();
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File("apprendimento/db.xml"));
			Element rootElement = doc.getRootElement();
			List children = rootElement.getChildren();
			Iterator iterator = children.iterator();
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				List childrenEticEquivalente = element.getChildren();
				Iterator iteratorEticEquivalente = childrenEticEquivalente
						.iterator();
				Element elementEticEquivalente = (Element) iteratorEticEquivalente
						.next();
				nomi.add(elementEticEquivalente.getName());
			}

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nomi;
	}

	public static String ottienietichettaXML() {
		String etichetta = new String();
		String etichettapred = new String();
		int contatore = 0;
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(
					"apprendimento/apprendimento.xml"));
			Element rootElement = doc.getRootElement();
			Element etichettaElement = rootElement.getChild("Etichetta");
			List children = etichettaElement.getChildren();
			Iterator iterator = children.iterator();

			while (iterator.hasNext()) {
				etichettapred = new String();
				Element element = (Element) iterator.next();
				if (element.getAttribute("option") != null)
					etichettapred = "neg(" + etichettapred + element.getName()
							+ "(";
				else
					etichettapred = etichettapred + element.getName() + "(";
				List childrenEticEquivalente = element.getChildren();
				Iterator iteratorEticEquivalente = childrenEticEquivalente
						.iterator();
				while (iteratorEticEquivalente.hasNext()) {
					Element elementEticEquivalente = (Element) iteratorEticEquivalente
							.next();
					etichettapred = etichettapred
							+ elementEticEquivalente.getName() + ",";
				}

				if (element.getAttribute("option") != null)
					etichettapred = etichettapred.substring(0,
							etichettapred.length() - 1)
							+ ")),";
				else
					etichettapred = etichettapred.substring(0,
							etichettapred.length() - 1)
							+ "),";
				etichetta = etichetta + etichettapred;

				contatore++;
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		etichetta = etichetta.substring(0, etichetta.length() - 1);
		if (contatore > 1)
			etichetta = "[" + etichetta + "]";
		return etichetta;
	}

	public static String ottieniargomentietichettaXML() {
		String stringa = new String();
		String etichetta = new String();
		String etichettapred = new String();
		int contatore = 0;
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(
					"apprendimento/apprendimento.xml"));
			Element rootElement = doc.getRootElement();
			Element etichettaElement = rootElement.getChild("Etichetta");
			List children = etichettaElement.getChildren();
			Iterator iterator = children.iterator();

			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				List childrenEticEquivalente = element.getChildren();
				Iterator iteratorEticEquivalente = childrenEticEquivalente
						.iterator();
				while (iteratorEticEquivalente.hasNext()) {
					Element elementc = (Element) iteratorEticEquivalente.next();
					if (!stringa.contains(elementc.getName()))
						stringa = stringa + elementc.getName() + ",";
				}
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stringa;
	}

	public static int isDoubleOrInt(String num) {
		try {
			Integer.parseInt(num);
			return 0;
		} catch (Exception e) {
			try {
				Double.parseDouble(num);
				return 1;
			} catch (Exception e1) {
				return -1;
			}
		}
	}

	// procedure aggiunte da morisco petrera:

	/*
	 * questa procedura unifica in un file tun unico gli esempi tivi e
	 * negativi alternandoli.
	 */
	public static File UnificaFileTun(File inFilePos, File inFileNeg) {
		// File inFile = new File("file/input/tun_pos.txt");
		// File inFile2 = new File("file/input/tun_neg.txt");

		File outFile = new File("file/output/tun.txt");
		try {

			if (!inFilePos.isFile()) {
				System.out.println("file pos non esiste!");
			}
			if (!inFileNeg.isFile()) {
				System.out.println("file neg non esiste");
			}

			BufferedReader brpos = new BufferedReader(new FileReader(
					inFilePos.getAbsolutePath()));
			BufferedReader brneg = new BufferedReader(new FileReader(
					inFileNeg.getAbsolutePath()));

			PrintWriter pw = new PrintWriter(new FileWriter(outFile));

			String linepos = null;
			String lineneg = null;
			while (((linepos = brpos.readLine()) != null)
					&& ((lineneg = brneg.readLine()) != null)) {
				if ((linepos.length() > 5) && (lineneg.length() > 5)) {
					pw.println(linepos);
					pw.println("\n\r");
					pw.println(lineneg);
					pw.println("\n\r");
				}
				pw.flush();
				linepos = null;
				lineneg = null;
			}// fine while
			pw.close();
			brpos.close();
			brneg.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return outFile;
	}

	// questa procedura serve a creare le cartelle ed i file che servono alle
	// prodedure per
	// eseguire la tecnica del 10-fold cross-validation

	public static void crea_file_folder() {
		String Dir = "";
		boolean crea = false;
		for (int i = 1; i <= 10; i++) {
			Dir = "file/output/" + Integer.toString(i);
			crea = (new File(Dir)).mkdirs();
			if (!(crea))
				;// controlla se la cartella esiste o no
		}
		for (int i = 1; i < 11; i++) {
			File fileTest = new File("file/output/" + i + "/" + i + "-test.tst");
			File fileTraining = new File("file/output/" + i + "/" + i
					+ "-training.tun");
			try {
				PrintWriter pw = new PrintWriter(new FileWriter(
						fileTest.getAbsolutePath()));
				PrintWriter pw2 = new PrintWriter(new FileWriter(
						fileTraining.getAbsolutePath()));
				pw.close();
				pw2.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// questa procedura esegue la tecnica del 10-fold cross-validation. vuole in
	// input il file tun con esempi positivi e negativi
	public static void TenFolderCrossValidation(File inFile) {
		ArrayList<String> file = new ArrayList<String>();
		try {
			if (!inFile.isFile()) {
				System.out.println("File inesistente!");
			}
			BufferedReader br = new BufferedReader(new FileReader(
					inFile.getAbsolutePath()));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.length() > 5) {
					file.add(line);
				}
				line = null;
			}// fine while
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		int card_file = file.size();
		int split = card_file / 10;
		int parte = 1;

		for (int j = 0; j < card_file; j++) {
			parte = j / split;
			switch (parte) {
			case 0:
				scrivi_file(file.get(j), 1, "test");
				scrivi_file_tun(file.get(j), 1);
				break;
			case 1:
				scrivi_file(file.get(j), 2, "test");
				scrivi_file_tun(file.get(j), 2);
				break;
			case 2:
				scrivi_file(file.get(j), 3, "test");
				scrivi_file_tun(file.get(j), 3);
				break;
			case 3:
				scrivi_file(file.get(j), 4, "test");
				scrivi_file_tun(file.get(j), 4);
				break;
			case 4:
				scrivi_file(file.get(j), 5, "test");
				scrivi_file_tun(file.get(j), 5);
				break;
			case 5:
				scrivi_file(file.get(j), 6, "test");
				scrivi_file_tun(file.get(j), 6);
				break;
			case 6:
				scrivi_file(file.get(j), 7, "test");
				scrivi_file_tun(file.get(j), 7);
				break;
			case 7:
				scrivi_file(file.get(j), 8, "test");
				scrivi_file_tun(file.get(j), 8);
				break;
			case 8:
				scrivi_file(file.get(j), 9, "test");
				scrivi_file_tun(file.get(j), 9);
				break;
			case 9:
				scrivi_file(file.get(j), 10, "test");
				scrivi_file_tun(file.get(j), 10);
				break;
			}

		}
		System.out.println("Scrittura terminata correttamente");
	}

	// prodedura di servizio per scrivere un elemento su un file di test o di
	// training
	public static void scrivi_file(String elemento, int num_parte, String tipo) {
		String nome = "";
		if (0 == (tipo.compareTo("test")))
			nome = "-test.tst";
		else if (0 == (tipo.compareTo("training")))
			nome = "-training.tun";

		try {
			File inFile = new File("file/output/" + num_parte + "/" + num_parte
					+ nome);

			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
			}
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader br = new BufferedReader(new FileReader(
					inFile.getAbsolutePath()));

			PrintWriter pw = new PrintWriter(new FileWriter(
					tempFile.getAbsolutePath()));

			String line = null;
			while ((line = br.readLine()) != null) {
				pw.println(line);
				pw.flush();
				line = null;
			}// fine while
			pw.println(elemento);
			pw.println("\r\n");
			pw.flush();

			pw.close();
			br.close();

			if (!inFile.delete()) {
				System.out.println("Could not delete file");
			}
			if (!tempFile.renameTo(inFile)) {
				System.out.println("Could not rename file");
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();

		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	// procedura di servizio che scrive un esempio in tutti i file di
	// training.tun tranne in quello indicato
	// da num parte, in cui l'esempio sar� inserito nel file test.tst
	public static void scrivi_file_tun(String elemento, int num_parte) {
		for (int i = 1; i < 11; i++) {
			if (i != num_parte)
				scrivi_file(elemento, i, "training");
		}
	}

	public static boolean controlla(int cartella) {
		String lineTst = null;
		String lineTraining = null;
		boolean controllo = false;
		File fileTest = new File("file/output/" + cartella + "/" + cartella
				+ "-test.tst");
		File fileTraining = new File("file/output/" + cartella + "/" + cartella
				+ "-training.tun");
		try {
			BufferedReader brTst = new BufferedReader(new FileReader(
					fileTest.getAbsolutePath()));
			BufferedReader brTraining = new BufferedReader(new FileReader(
					fileTraining.getAbsolutePath()));
			while ((lineTst = brTst.readLine()) != null) {
				while ((lineTraining = brTraining.readLine()) != null) {
					if (0 == (lineTst.compareTo(lineTraining))) {
						controllo = true;
						System.out.println("linea 1:" + lineTst);
						System.out.println("linea 2:" + lineTraining);
						System.out.println("\n\r");
					}
					lineTraining = null;
				}// fine while
				lineTst = null;
			}// fine while
			brTst.close();
			brTraining.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return controllo;
	}
	
	/*
	public static void loadXMLMappa(String path) {
		// INIZIO LETTURA XML //

				try {

					File fXmlFile = new File(path);
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					org.w3c.dom.Document doc =  dBuilder.parse(fXmlFile);
					 doc.getDocumentElement().normalize();

					// CARICAMENTO OGGETTI //

					NodeList List = ((org.w3c.dom.Document) doc).getElementsByTagName("Oggetto");
					

					for (int temp = 0; temp < List.getLength(); temp++) {

						Node nNode = List.item(temp);
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
							// System.out.println("nomeOggetto : " +
							// getTagValue("nomeOggetto", eElement));

							Oggetto tempOgg = new Oggetto();

							tempOgg.setTipo(getTagValue("tipoOggetto",
									eElement));
							tempOgg.setNome_oggetto(getTagValue("nomeOggetto",
									eElement));
							tempOgg.setCodiceOggetto(Integer
									.parseInt(getTagValue("codiceOggetto",
											eElement)));

							if (!" ".equals(getTagValue("descrizioneOggetto",
									eElement)))
								tempOgg.setDescrizione(getTagValue(
										"descrizioneOggetto", eElement));
							else
								tempOgg.setDescrizione("");

							if (getTagValue("indossabile", eElement).equals(
									"true"))
								tempOgg.setIndossabile(true);
							else
								tempOgg.setIndossabile(false);

							if (getTagValue("visibile", eElement)
									.equals("true"))
								tempOgg.setVisibile(true);
							else
								tempOgg.setVisibile(false);

							if (getTagValue("trasportabile", eElement).equals(
									"true")){
								tempOgg.setTrasportabile(true);
								
							}
							else
								tempOgg.setTrasportabile(false);

							if (getTagValue("usabile", eElement).equals("true"))
								tempOgg.setUsabile(true);
							else
								tempOgg.setUsabile(false);

							if (getTagValue("mobile", eElement).equals("true"))
								tempOgg.setMobile(true);
							else
								tempOgg.setMobile(false);

							if (getTagValue("luogo", eElement).equals(
									"Inventario")) {
								tempOgg.setLuogo_oggetto("Inventario");
								tempOgg.setMappa_oggetto("Invetario");
							} else {
								tempOgg.setLuogo_oggetto(getTagValue("luogo",
										eElement));
								tempOgg.setMappa_oggetto(getTagValue("mappa",
										eElement));
							}

							//MainFrame.oggetti.oggetti.add(tempOgg);
							MIinterprolog.incrementIdnewFact();
							RW.writeinkowledge("Carica mappa","fact("+MIinterprolog.getIdnewFact()+","+tempOgg.getTipo()+"("+tempOgg.getNome_oggetto()+"),1)");
							MIinterprolog.incrementIdnewFact();
							if(!tempOgg.getLuogo_oggetto().equals("Limbo"))
							{
								if (tempOgg.isMobile()){
									MIinterprolog.incrementIdnewFact();
								RW.writeinkowledge("Carica mappa","fact("+MIinterprolog.getIdnewFact()+","+"posizione("+MainFrame.idtimestamp+","+tempOgg.getLuogo_oggetto()+","+tempOgg.getNome_oggetto()+"),1)");
								}else{
									MIinterprolog.incrementIdnewFact();
									RW.writeinkowledge("Carica mappa","fact("+MIinterprolog.getIdnewFact()+","+"posizione("+tempOgg.getLuogo_oggetto()+","+tempOgg.getNome_oggetto()+"),1)");
							}
								MIinterprolog.incrementIdnewFact();}
									
						}
					}

					// FINE CARICAMENTO OGGETTI //
		

					// CARICAMENTO PERSONAGGI //

					 List = ((org.w3c.dom.Document) doc).getElementsByTagName("Personaggio");
				

					for (int temp = 0; temp < List.getLength(); temp++) {

						Node nNode = List.item(temp);
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;

							Personaggio tempPers = new Personaggio();

							tempPers.setCodicePersonaggio(Integer
									.parseInt(getTagValue("codicePersonaggio",
											eElement)));
							tempPers.setNome(getTagValue("nomePersonaggio",
									eElement));
							tempPers.setSesso(getTagValue("sessoPersonaggio",
									eElement).toUpperCase());
							tempPers.setLuogo_personaggio(getTagValue(
									"luogoPersonaggio", eElement));
							tempPers.setMappa_personaggio(getTagValue(
									"mappaPersonaggio", eElement));
							tempPers.setNascita_giorno(Integer
									.parseInt(getTagValue(
											"giornonascitaPersonaggio",
											eElement)));
							tempPers.setNascita_mese(Integer
									.parseInt(getTagValue(
											"mesenascitaPersonaggio", eElement)));
							tempPers.setNascita_anno(Integer
									.parseInt(getTagValue(
											"annonascitaPersonaggio", eElement)));
			
							
							if (getTagValue("gestibilePersonaggio", eElement).equals(
									"true")) {
								tempPers.setGestibile(true);
								} else {
								tempPers.setGestibile(false);

							}
							
							MainFrame.soggetti.soggetti.add(tempPers);
							MI.incrementIdnewFact();
							RW.writeinkowledge("Carica mappa","fact("+MI.getIdnewFact()+","+"persona("+tempPers.getNome()+"),1)");
							MI.incrementIdnewFact();
							if(!tempPers.getLuogo_personaggio().equals("Limbo"))
							{
								MI.incrementIdnewFact();
								RW.writeinkowledge("Carica mappa","fact("+MI.getIdnewFact()+","+"posizione("+MainFrame.idtimestamp+","+tempPers.getLuogo_personaggio()+","+tempPers.getNome()+"),1)");
								MI.incrementIdnewFact();}
							}
				
						}
					
					
					// CARICAMENTO PROCESSI //
					 List = ((org.w3c.dom.Document) doc).getElementsByTagName("Processo");
						
						HashMap<String,Processo> processi=	new HashMap<String,Processo>();

						for (int temp = 0; temp < List.getLength(); temp++) {

							Node nNode = List.item(temp);
							if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;

								Processo tempProc = new Processo();

								tempProc.setNomeprocesso(getTagValue("ID",
										eElement));
								NodeList list2=  eElement.getElementsByTagName("Luogo");
								HashMap<String,ArrayList<String>> attivitaluogo=	new HashMap<String,ArrayList<String>>();
								for (int temp2 = 0; temp2 < list2.getLength(); temp2++) {
									Node nNodel = list2.item(temp2);
									ArrayList<String> attivitlist=new ArrayList<String>();
									if (nNodel.getNodeType() == Node.ELEMENT_NODE) {
										NodeList list3= ((	org.w3c.dom.Element) nNodel).getChildNodes();
										String nomeLuogo="";
										for (int temp3 = 0; temp3 < list3.getLength(); temp3++) {
											Node nNodea = list3.item(temp3);
											if (nNodea.getNodeType() == Node.TEXT_NODE && nomeLuogo.equals("")) {
												nomeLuogo=nNodea.getNodeValue().trim();

											}
											if (nNodea.getNodeType() == Node.ELEMENT_NODE) {
												attivitlist.add(nNodea.getFirstChild().getNodeValue());
											
											}
										}
										attivitaluogo.put(nomeLuogo, attivitlist);
									
								}
								
									
								}
							
								tempProc.setAttivita(attivitaluogo);
								processi.put(tempProc.getNomeprocesso(), tempProc);
								
								
							}
							}
						MainFrame.processi.setProcessi(processi);
					
					
						}
					
				
			
		
				// FINE LETTURA XML //
			
		 catch (Exception e) {
			e.printStackTrace();
		}
		}
	*/
	
	/*
	public static void saveXMLMappa(String path) {
		// INIZIO SCRITTURA XML //

		try {

			File fXmlFile = new File(path);
			File fXmlFileBackup = new File(path.replace(".xml", "_backup.xml"));

			FileUtils.copyFile(fXmlFile, fXmlFileBackup);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc =  dBuilder.parse(fXmlFile);
			 doc.getDocumentElement().normalize();

			// CARICAMENTO OGGETTI //

			//NodeList List = ((org.w3c.dom.Document) doc).getElementsByTagName("Oggetto");
			 Node oggetti= doc.getElementsByTagName("Oggetti").item(0);
			 removeAllChild(oggetti);
			for (int temp = 0; temp < MainFrame.oggetti.oggetti.size(); temp++) {

				Oggetto next = MainFrame.oggetti.oggetti.get(temp);
				
				org.w3c.dom.Element Oggetto = doc.createElement("Oggetto");
				oggetti.appendChild(Oggetto);

				// Nome oggetto
				org.w3c.dom.Element nomeOggetto = doc.createElement("nomeOggetto");
				nomeOggetto.appendChild(doc.createTextNode(next
						.getNome_oggetto()));
				Oggetto.appendChild(nomeOggetto);

				// Codice
				org.w3c.dom.Element codiceOggetto = doc.createElement("codiceOggetto");
				codiceOggetto.appendChild(doc.createTextNode(Integer
						.toString(next.getCodiceOggetto())));
				Oggetto.appendChild(codiceOggetto);
				
				// Codice
				org.w3c.dom.Element tipoOggetto = doc.createElement("tipoOggetto");
				tipoOggetto.appendChild(doc.createTextNode(next.getTipo()));
				Oggetto.appendChild(tipoOggetto);

				// Descrizione oggetto
				org.w3c.dom.Element descOggetto = doc.createElement("descrizioneOggetto");
				if (!next.getDescrizione().equals(""))
					descOggetto.appendChild(doc.createTextNode(next
							.getDescrizione()));
				else
					descOggetto.appendChild(doc.createTextNode(" "));
				Oggetto.appendChild(descOggetto);

				// indossabile oggetto
				org.w3c.dom.Element indossabile = doc.createElement("indossabile");

				if (next.isIndossabile())
					indossabile.appendChild(doc.createTextNode("true"));
				else
					indossabile.appendChild(doc.createTextNode("false"));

				Oggetto.appendChild(indossabile);

				// visibile oggetto
				org.w3c.dom.Element visibile = doc.createElement("visibile");
				if (next.isVisibile())
					visibile.appendChild(doc.createTextNode("true"));
				else
					visibile.appendChild(doc.createTextNode("false"));
				Oggetto.appendChild(visibile);

				// trasportabile oggetto
				org.w3c.dom.Element trasportabile = doc.createElement("trasportabile");
				if (next.isTrasportabile()){				
					trasportabile.appendChild(doc.createTextNode("true"));
					
				}
				else
					trasportabile.appendChild(doc.createTextNode("false"));
				Oggetto.appendChild(trasportabile);

				// usabile oggetto
				org.w3c.dom.Element usabile = doc.createElement("usabile");
				if (next.isUsabile())
					usabile.appendChild(doc.createTextNode("true"));
				else
					usabile.appendChild(doc.createTextNode("false"));
				Oggetto.appendChild(usabile);

				// mobile oggetto
				org.w3c.dom.Element mobile = doc.createElement("mobile");
				if (next.isMobile())
					mobile.appendChild(doc.createTextNode("true"));
				else
					mobile.appendChild(doc.createTextNode("false"));
				Oggetto.appendChild(mobile);

				// luogo oggetto
				org.w3c.dom.Element luogo = doc.createElement("luogo");
				if (next.getLuogo_oggetto().equals("Inventario"))
					luogo.appendChild(doc.createTextNode("Inventario"));
				else {
					luogo.appendChild(doc.createTextNode(next
							.getLuogo_oggetto()));
					org.w3c.dom.Element mappa = doc.createElement("mappa");
					mappa.appendChild(doc.createTextNode(next
							.getMappa_oggetto()));
					Oggetto.appendChild(mappa);
				}
				Oggetto.appendChild(luogo);
			
				oggetti.appendChild(Oggetto);
			}
			// PERSONAGGI //
			 Node personaggi= doc.getElementsByTagName("Personaggi").item(0);
			 
			 removeAllChild(personaggi);
				for (int temp = 0; temp < MainFrame.soggetti.soggetti.size(); temp++) {

				Personaggio next =MainFrame.soggetti.soggetti.get(temp);

				org.w3c.dom.Element Personaggio = doc.createElement("Personaggio");
				

				// Codice personaggio
				org.w3c.dom.Element codicePersonaggio = doc
						.createElement("codicePersonaggio");
				codicePersonaggio.setTextContent(String.valueOf(next
						.getCodicePersonaggio()));
				Personaggio.appendChild(codicePersonaggio);
				// Nome personaggio
				org.w3c.dom.Element nomePersonaggio = doc.createElement("nomePersonaggio");
				nomePersonaggio.setTextContent(next.getNome());
				Personaggio.appendChild(nomePersonaggio);
				// Sesso personaggio
				org.w3c.dom.Element sessoPersonaggio = doc
						.createElement("sessoPersonaggio");
				sessoPersonaggio.setTextContent(next.getSesso());
				Personaggio.appendChild(sessoPersonaggio);
				// Giorno nascita personaggio
				org.w3c.dom.Element giornonPersonaggio = doc
						.createElement("giornonascitaPersonaggio");
				giornonPersonaggio.setTextContent(String.valueOf(next
						.getNascita_giorno()));
				Personaggio.appendChild(giornonPersonaggio);
				// Mese nascita personaggio
				org.w3c.dom.Element mesenPersonaggio = doc
						.createElement("mesenascitaPersonaggio");
				mesenPersonaggio.setTextContent(String.valueOf(next
						.getNascita_mese()+1));
				Personaggio.appendChild(mesenPersonaggio);
				// Anno nascita personaggio
				org.w3c.dom.Element annonPersonaggio = doc
						.createElement("annonascitaPersonaggio");
				annonPersonaggio.setTextContent(String.valueOf(next
						.getNascita_anno()));
				Personaggio.appendChild(annonPersonaggio);
				// Gestibile personaggio
				org.w3c.dom.Element gestibilePersonaggio = doc
						.createElement("gestibilePersonaggio");
				if(next.isGestibile())
				gestibilePersonaggio.setTextContent("true");
				else
					gestibilePersonaggio.setTextContent("false");
				Personaggio.appendChild(gestibilePersonaggio);
				// Mappa personaggio
				org.w3c.dom.Element mappaPersonaggio = doc
						.createElement("mappaPersonaggio");
				mappaPersonaggio.setTextContent(next.getMappa_personaggio());
				Personaggio.appendChild(mappaPersonaggio);
				// Luogo personaggio
				org.w3c.dom.Element luogoPersonaggio = doc
						.createElement("luogoPersonaggio");
				luogoPersonaggio.setTextContent(next.getLuogo_personaggio());
				Personaggio.appendChild(luogoPersonaggio);
				personaggi.appendChild(Personaggio);
			}
				// FINE SALVATAGGIO OGGETTI //
				
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);


						StreamResult result = new StreamResult();
							result = new StreamResult(new File(path));


						transformer.transform(source, result);
					
		}
			


			
	
 catch (Exception e) {
	e.printStackTrace();
}
}
	*/
	
	private static String getTagValue(String sTag, org.w3c.dom.Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}
	
	   public static void removeAllChild(Node node) 
	    {
		   while (node.hasChildNodes())
		        node.removeChild(node.getFirstChild());	    
	}
}
