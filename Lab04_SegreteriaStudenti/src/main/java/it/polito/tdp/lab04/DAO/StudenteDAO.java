package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
