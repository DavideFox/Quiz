package it.beije.quiz;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.beije.quiz.model.Domanda;
import it.beije.quiz.model.Risposta;
//import it.beije.quiz.model.Risposta;
//import it.beije.quiz.repository.DomandaRepository;
import it.beije.quiz.service.DomandaService;
import it.beije.quiz.service.RispostaService;

public class Utils {
	@Autowired
	private DomandaService domandaService;
	@Autowired
	private RispostaService rispostaService;
	
	/*
	 * Metodo che crea la lista (ordinata) degli elementi XML di un file XML generico
	 */
	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		
		NodeList nodeList = element.getChildNodes();
        Node node = null;
        for (int i = 0; i < nodeList.getLength(); i++) {
        	node = nodeList.item(i);
        	if (node.getNodeType() == Node.ELEMENT_NODE) {
        		childElements.add((Element)node);
        	}
        }
		return childElements;
	}

	
	/*
	 * Metodo per leggere le domande da un file XML, prima carica il file, ne trova tutti gli elementi
	 * e trova il testo delle varie domande pi� le possibili risposte di quella domanda, trovandone
	 * anche i vari capitoli, n� domanda, risposta/e esatta/e...
	 * Alla fine della lista risultante vengono aggiunti i vari entity domande cos� caricati.
	 */
	public void xmlToDatabase(String pathFile) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        // Load the input XML document, parse it and return an instance of the
	        // Document class.
	        Document document = builder.parse(new File(pathFile));
	        Element element = document.getDocumentElement();	        
	        System.out.println(element.getTagName());
	        List<Element> domande = Utils.getChildElements(element);
	        System.out.println(domande);        
	        List<Element> contenutoDomanda = null;
	        List<Element> elementiRisposta = null;
	        Element rispostePossibili = null;
	        for (Element domanda : domande) {
	        	contenutoDomanda = Utils.getChildElements(domanda);
		        String book = domanda.getAttribute("book");
		        int chapter = Integer.parseInt(domanda.getAttribute("chapter"));
		        int question = Integer.parseInt(domanda.getAttribute("question"));
		        String testo = contenutoDomanda.get(0).getTextContent();
		        //caricare le risposte possibili
		        rispostePossibili = contenutoDomanda.get(1);
		        String type = rispostePossibili.getAttribute("type");
		        elementiRisposta = Utils.getChildElements(rispostePossibili);
		        List<Risposta> risposte = new ArrayList<Risposta>();
		        for (Element risposta : elementiRisposta) {
		        	Risposta r = new Risposta();
		        	r.setLettera(risposta.getAttribute("value"));
		        	r.setRisposta(risposta.getTextContent());
		        	risposte.add(r);
		        }
		        String rispostaEsatta = contenutoDomanda.get(2).getTextContent();
		        String spiegazione = contenutoDomanda.get(3).getTextContent();
	        	Domanda d = new Domanda(book, chapter, question, testo, type, spiegazione);
	        	System.out.println(d);
	        	Domanda dSaved = domandaService.insert(d);
	        	for (Risposta r : risposte) {
	        		r.setIdDomanda(dSaved.getId());
	        		rispostaEsatta = rispostaEsatta.replace(" ", "").replace(",", "");
	        		if (rispostaEsatta.indexOf(r.getLettera()) < 0) {
	        			r.setCorretto(false);
	        		} else {
	        			r.setCorretto(true);
	        		}
	        		rispostaService.insert(r);
	        	}
	        }	        		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Formattazione testo: fa in modo che i vari "a capo" e le tab vengano rispettate anche in una pagina HTML
	 */
	public static String formattaTesto(String testo) {
		if (testo != null && testo.length() > 0) {
			testo = testo.replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		}
		
		return testo;
	}
	
	/*
	 * Vengono date le risposte date dagli utenti e la risposta esatta.
	 * Il risultato � se l'utente ha dato la risposta corretta.
	 */
	public static boolean controllaRisposta(String rispostaEsatta, String risposta) {
		rispostaEsatta = rispostaEsatta.replace(" ", "").replace(",", ""); // Bug fixato
		for (int i = 0; i < risposta.length(); i++) {
			char c = risposta.charAt(i);
			if (c == ' ' || c == ',') continue;
			if (rispostaEsatta.indexOf(c) < 0) {
				return false;//se non trovo la risposta termino il ciclo
			} else {
				//tolgo risposta esatta da elenco risposte esatte per evitare duplicati
				rispostaEsatta = rispostaEsatta.replace(Character.toString(c), "");
			}
		}
		
		return rispostaEsatta.length() == 0;
	}
	
	public static void main(String[] args) {
		Utils utils = new Utils();
		utils.xmlToDatabase("/temp/domande_cap1.xml");
	}

}
