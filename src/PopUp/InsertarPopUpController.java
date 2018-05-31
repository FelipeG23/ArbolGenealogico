/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PopUp;

import Nodo.Nodo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class InsertarPopUpController implements Initializable {

    @FXML
    private JFXComboBox<String> comboPadres;
    @FXML
    private JFXTextField inputNuevaPersona;

    public List<Nodo> listaPersonas;
    public List<String> listaPersonasUnico;
    @FXML
    private JFXButton botonInsertar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public List<Nodo> devolverListaNodos() {
        return listaPersonas;
    }

    public List<String> devolverListaUnica() {
        return listaPersonasUnico;
    }

    @FXML
    public void insertarPersona(ActionEvent event) {
        try {
            System.out.println("Nueva Persona : " + inputNuevaPersona.getText());
            System.out.println("Padre : " + comboPadres.getValue());
            String padre = "";
            String hijo = "";
            if (comboPadres.getValue() == null) {
                padre = "";
                hijo = inputNuevaPersona.getText();

            } else {
                padre = comboPadres.getValue();
                hijo = inputNuevaPersona.getText();
            }

            if (listaPersonas == null) {
                listaPersonas = new ArrayList<>();
            }
            if (listaPersonasUnico == null) {
                listaPersonasUnico = new ArrayList<>();
            }
            Nodo element = new Nodo();
            if (padre.equalsIgnoreCase("...")) {
                element.setPadre("");
            } else {
                element.setPadre(padre);
            }
            element.setHijo(hijo);
            listaPersonas.add(element);
            listaPersonasUnico.add(hijo);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPersonas(List<String> lista) {
        if (lista != null) {
            for (String item : lista) {
                comboPadres.getItems().add(item);
            }
        }

        comboPadres.setPromptText("Seleccione un padre");

    }
}
