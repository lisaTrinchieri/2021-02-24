
/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.GiocatoreMigliore;
import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnGiocatoreMigliore"
    private Button btnGiocatoreMigliore; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="cmbMatch"
    private ComboBox<Match> cmbMatch; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.txtResult.clear();
    	Match m = this.cmbMatch.getValue();
    	
    	if(m== null)
    	{ this.txtResult.appendText("Seleziona un match!");
    	  return;
    	}
    	
    	this.model.creaGrafo(m);
    	txtResult.appendText("GRAFO CREATO\n");
    	this.txtResult.appendText("# vertici: "+this.model.getnVertici()+"\n");
    	this.txtResult.appendText("# archi: "+this.model.getNarchi()+"\n");
    }

    @FXML
    void doGiocatoreMigliore(ActionEvent event) {  
    	
    	this.txtResult.clear();
    	
    	if(this.model.getGrafo()==null)
    	{ this.txtResult.appendText("Il grafo non è stato creato");
  	      return;
  	     }
    	
    	GiocatoreMigliore g = this.model.getMigliore();
    	this.txtResult.appendText("Giocatore migliore : "+g.toString());
    		
    	
    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	
    	String num= this.txtN.getText();
    	int n= Integer.parseInt(num);
    	
    	this.model.simula(n);
    	
    	this.txtResult.appendText("Espulsi t1: "+this.model.getEx1()+"\n");
    	this.txtResult.appendText("Espulsi t2: "+this.model.getEx2()+"\n");
    	this.txtResult.appendText("Gol t1: "+this.model.getGol1()+"\n");
    	this.txtResult.appendText("Gol t1: "+this.model.getGol2()+"\n");

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGiocatoreMigliore != null : "fx:id=\"btnGiocatoreMigliore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMatch != null : "fx:id=\"cmbMatch\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbMatch.getItems().addAll(this.model.getTuttimatch());
    }
}
