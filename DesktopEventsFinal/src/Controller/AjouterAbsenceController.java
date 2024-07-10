/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Absence;
import Services.ServiceAbsence;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pablo
 */
public class AjouterAbsenceController implements Initializable {

    @FXML
    private Label ref_nom;
    @FXML
    private Label ref_prenom;
    @FXML
    private Label ref_matiere;
    @FXML
    private Label ref_date;
    @FXML
    private Label ref_NbAbsence;
    @FXML
    private TextField ref_nom_field;
    @FXML
    private TextField ref_prenom_field;
    @FXML
    private TextField ref_matiere_field;
    @FXML
    private DatePicker ref_date_field;
    @FXML
    private TextField ref_NbAbsence_field;
    @FXML
    private Pane mainpane ; 
    @FXML
    private TextField id;
    @FXML
    private Label ref_nom1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ref_date_field.setValue(LocalDate.now());
        ref_date_field.setDisable(true);
        
        
    
}

    @FXML
    private void ajout_absence_btn(ActionEvent event) throws IOException {
        
        if (!valide()) {} 
        else {
         Absence n =new Absence( ref_nom_field.getText(), ref_prenom_field.getText(), ref_matiere_field.getText(), Date.valueOf(ref_date_field.getValue()), parseInt(ref_NbAbsence_field.getText()),parseInt(id.getText()) );
        ServiceAbsence  SN =new ServiceAbsence() ;
        SN.ajouterAbsence(n);
        
         Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/AfficherAbsence.fxml"));
                 mainpane.getChildren().clear();

        mainpane.getChildren().add(parent);
        mainpane.toFront(); }
        // Stage stage=(Stage) mainpane.getScene().getWindow();
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/Views/AfficherAbsence.fxml"));
        //Scene scene = new Scene(root);
        //stage.setScene(scene);
        //stage.show();
//                Parent root;
//        try {
//            root = FXMLLoader.load(getClass().getResource("/Views/AfficherAbsence.fxml"));
//                    Scene scene = new Scene(root);
//        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(scene);
//        window.show();
//        } catch (IOException ex) {
//            Logger.getLogger(AfficherAbsenceController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }    
        
    
     private boolean validatorInt(String s) {
        Pattern p = Pattern.compile("[0-9]");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("Le champ nbAbsence n'est pas valide !");
            alert.showAndWait();
            return false;
        }

    }
                private boolean validatorString(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("nom invalide!");
            alert.showAndWait();
            return false;
        }

    }
                      private void warning(String txt) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(txt);
        Optional<ButtonType> action = alert.showAndWait();
    }
          private boolean validatorString1(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("prenom invalide!");
            alert.showAndWait();
            return false;
        }

    }
                    private boolean validatorString2(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("matiere invalide!");
            alert.showAndWait();
            return false;
        }

    }
       private boolean  validate(String s)
    
    
    {
    
    int i = -1  ; 
    try {
      i = Integer.parseInt(s) ;
      
      
    
    }
    catch (Exception e ) {} 
    if ( i == -1 ) {return false  ; } 
    if ( i>=0  ) {return true  ; }
    
    
    return false ; 
    
    }
                    private boolean valide() {
              if (validate(ref_NbAbsence_field.getText()) == false) {
            warning("Veuillez donner un nombre  valide !");
            return false;
        }
        if (validatorString(ref_nom_field.getText())==false) {
            warning("Veuillez donner un nom  valide !");
            return false;
        }
          if (validatorString1(ref_prenom_field.getText())==false) {
              warning("Veuillez donner un prenom  valide !");
            return false;
        }
            if (validatorString(ref_matiere_field.getText())==false) {
                warning("Veuillez donner une matiere  valide !");
            return false;
        }

        return true;
    }
    
        
        
        
        
    }

