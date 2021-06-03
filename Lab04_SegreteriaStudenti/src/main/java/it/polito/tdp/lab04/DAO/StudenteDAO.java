package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	
	public Studente getStudente(int matricola) {
		final String sql="SELECT * FROM studente where matricola= ? ";
		
		Studente studente=null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,matricola);

			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				studente= new Studente(matricola,rs.getString("cognome"),rs.getString("nome"),rs.getString("cds"));
			}
			conn.close();
         
		}
		catch(SQLException e){
			throw new RuntimeException("Errore Db", e);
		}
		return studente;
		
	}
	
	public List<Corso> getCorsiPerStudente(Studente studente){
		final String sql="SELECT * "
				+ "FROM iscrizione AS i, corso AS c "
				+ "WHERE i.codins=c.codins "
				+ "AND i.matricola= ? ";
			List<Corso> corsi= new LinkedList<Corso>();
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1,studente.getMatricola());

				ResultSet rs = st.executeQuery();	
				while(rs.next()) {
					Corso cors= new Corso(rs.getString("codins"),rs.getString("nome"),rs.getInt("crediti"),rs.getInt("pd"));
				    corsi.add(cors);
				}
				conn.close();
	         
			}
			catch(SQLException e){
				throw new RuntimeException("Errore Db", e);
			}	
				
		return corsi;		
				
	}
	
	public Boolean isStudenteIscrittoAlCorso(Studente studente, Corso corso) {
		final String sql = "SELECT * FROM iscrizione WHERE codins= ? AND matricola=? ";
		boolean iscritto=false;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			st.setInt(2,studente.getMatricola());

			ResultSet rs = st.executeQuery();	
			if(rs.next()) {
				iscritto= true;
			}
			conn.close();
         
		}
		catch(SQLException e){
			throw new RuntimeException("Errore Db", e);
		}
		return iscritto;
			
	}
	
	
	
	
	
	
	
}
