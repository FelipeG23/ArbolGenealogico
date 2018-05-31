/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PopUp;

import Nodo.Nodo;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author Felipe
 */
public class ConsultarPopUpController implements Initializable {

    public List<Nodo> listaPersonas;
    @FXML
    private JFXTextField inputNuevaPersona;

    public void iniciarPersonas(List<Nodo> personas) {
        try {
            this.listaPersonas = personas;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void consultarPersona(ActionEvent event) {
        try {
            String padre = "";
            String hijos = "";
            String texto = "";
            for (Nodo listaPersona : listaPersonas) {
                if (inputNuevaPersona.getText().equalsIgnoreCase(listaPersona.getHijo())) {
                    padre = listaPersona.getPadre();
                }
            }
            for (Nodo listaPersona : listaPersonas) {
                if (inputNuevaPersona.getText().equalsIgnoreCase(listaPersona.getPadre())) {
                    hijos += listaPersona.getHijo() + ",";
                }
            }
            if (!hijos.isEmpty()) {
                hijos = hijos.substring(0, hijos.length() - 1);
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Consulta familiar de " + inputNuevaPersona.getText());
            alert.setHeaderText("");
            if (padre.isEmpty()) {
                texto += "No tiene padre ";
            } else {
                texto += "Su padre es: " + padre;
            }
            if (hijos.isEmpty()) {
                texto += " y no tiene hijo(s): ";
            } else {
                if (hijos.split(",").length > 1) {
                    texto += " y sus hijos son: "+hijos;
                } else {
                    texto += " y su hijo es: "+hijos;
                }
            }
            alert.setContentText(texto);
            alert.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public List<Nodo> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Nodo> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public JFXTextField getInputNuevaPersona() {
        return inputNuevaPersona;
    }

    public void setInputNuevaPersona(JFXTextField inputNuevaPersona) {
        this.inputNuevaPersona = inputNuevaPersona;
    }

}
