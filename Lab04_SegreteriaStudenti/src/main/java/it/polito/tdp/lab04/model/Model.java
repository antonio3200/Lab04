package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private StudenteDAO studenteDAO;
	private CorsoDAO corsoDAO;
	public Model() {
		this.studenteDAO = new  StudenteDAO();
		this.corsoDAO = new CorsoDAO();
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
	
	public List<Corso> getCorsiPerStudente(Studente studente){
		return studenteDAO.getCorsiPerStudente(studente);
	}
	public Boolean isStudenteIscrittoAlCorso(Studente studente,Corso corso) {
		return studenteDAO.isStudenteIscrittoAlCorso(studente, corso);
	}
	

}

