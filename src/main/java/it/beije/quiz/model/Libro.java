package it.beije.quiz.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import it.beije.quiz.Utils;

public class Libro {
	

//	public static final String PATH_INDEX_BOOKS = "C:\\Users\\Beije�\\git\\Quiz\\domande\\index.xml";
	private static final String LIB_PATH="C:\\Users\\Beije�\\git\\Quiz\\domande\\";
	
	private String checked="";

	private String idBook;
	private String title;
	private String nameDir;
	 
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getNameDir() {
		return nameDir;
	}
	
	public void setNameDir(String nameDir) {
		this.nameDir = nameDir;
	}
	
	public String getIdBook() {
		return idBook;
	}
	public void setIdBook(String idBook) {
		this.idBook = idBook;
	}
	
	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	public List<Domanda> caricaQuestions() {
		System.out.println("entroooo");
		List<Domanda> listaD = new ArrayList<Domanda>();
		File folder = new File(LIB_PATH+nameDir);
		System.out.println(LIB_PATH+nameDir);
		
		File[] listOfFiles = folder.listFiles();
		

		for (int i = 0; i < listOfFiles.length; i++) {
//			&& Pattern.matches("^.*\\.[xml]$", listOfFiles[i].getName())
			if (listOfFiles[i].isFile() ) {
				listaD.addAll(Utils.readFileDomande(listOfFiles[i].getPath()));				
			}
		}
		
		
		
		return listaD;
	}
}

