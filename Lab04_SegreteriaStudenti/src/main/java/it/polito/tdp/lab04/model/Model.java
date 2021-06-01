package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private StudenteDAO studenteDAO;
	private CorsoDAO corsoDAO;
	public Model(StudenteDAO studenteDAO, CorsoDAO corsoDAO) {
		super();
		this.studenteDAO = studenteDAO;
		this.corsoDAO = corsoDAO;
	}
	
	public List<Corso> getTuttiICorsi(){
		return corsoDAO.getTuttiICorsi();
	}
	
	public Studente getStudente(int matricola) {
		return studenteDAO.getStudente(matricola);
	}
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	
	

}

