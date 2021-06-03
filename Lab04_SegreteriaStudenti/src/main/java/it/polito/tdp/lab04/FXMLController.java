/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Model model;
	private List<Corso> corsi;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboCorso"
    private ComboBox<Corso> comboCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaNome"
    private Button btnCercaNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCerca"
    private Button btnCerca; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doCercaCorsi(ActionEvent event) {
    txtResult.clear();
    try {
    	int matricola= Integer.parseInt(txtMatricola.getText());
    	Studente studente= model.getStudente(matricola);
    	if(studente==null) {
    		txtResult.appendText("MATRICOLA INESISTENTE");
    		return;
    	}
    	List<Corso> corsi= model.getCorsiPerStudente(studente);
    	StringBuilder sb = new StringBuilder();

		for (Corso corso : corsi) {
     		sb.append(String.format("%-8s ", corso.getCodins()));
			sb.append(String.format("%-4s ", corso.getCrediti()));
			sb.append(String.format("%-45s ", corso.getNome()));
			sb.append(String.format("%-4s ", corso.getPd()));
			sb.append("\n");
		}
		txtResult.appendText(sb.toString());

    }
    catch (NumberFormatException e) {
		txtResult.setText("Inserire una matricola nel formato corretto.");
	} catch (RuntimeException e) {
		txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
	}


    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	try {
    		Corso corso= comboCorso.getValue();
    		if(corso==null) {
    			txtResult.appendText("SELEZIONARE UN CORSO");
    			return;
    		}
    		List<Studente> studenti= model.getStudentiIscrittiAlCorso(corso);
    		StringBuilder sb = new StringBuilder();

			for (Studente studente : studenti) {

				sb.append(String.format("%-10s ", studente.getMatricola()));
				sb.append(String.format("%-20s ", studente.getCognome()));
				sb.append(String.format("%-20s ", studente.getNome()));
				sb.append(String.format("%-10s ", studente.getCds()));
				sb.append("\n");
			}

			txtResult.appendText(sb.toString());
    	}
    	catch(RuntimeException e) {
    		txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
    	}

    }

    @FXML
    void doCercaNome(ActionEvent event) {
         txtResult.clear();
         txtNome.clear();
         txtCognome.clear();
         try {
        	 int matricola= Integer.parseInt(txtMatricola.getText());
        	 Studente studente= model.getStudente(matricola);
        	 if(studente ==null) {
        		 txtResult.appendText("MATRICOLA INESISTENTE:RIPROVARE");
        		 return;
        	 }
        	 txtNome.appendText(studente.getNome());
        	 txtCognome.appendText(studente.getCognome());
         }
         catch(NumberFormatException ne) {
        	 txtResult.appendText("INSERIRE MATRICOLA NEL FORMATO CORRETTO");     	 
         }
         catch(RuntimeException re) {
        	 txtResult.appendText("ERRORE DI CONNESSIONE AL DATABASE!");
         }
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	comboCorso.getSelectionModel().clearSelection();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setComboBox() {
    	corsi=model.getTuttiICorsi();
    	Collections.sort(corsi);
    	comboCorso.getItems().addAll(corsi);
    }
    
    public void setModel(Model model) {
    	this.model=model;
    	setComboBox();
    }
    
}
