/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Logica.Tree;
import Nodo.Nodo;
import PopUp.ConsultarPopUpController;
import PopUp.InsertarPopUpController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Felipe
 */
public class InicioController extends Application implements Initializable {

    public List<Nodo> listaPersonas;
    public List<String> listaPersonasUnico;
    @FXML
    private Pane panelImagen;

    @FXML
    void consultar(ActionEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/PopUp/ConsultarPopUp.fxml"));
            Parent root = (Parent) fxml.load();
            ConsultarPopUpController controlador = fxml.getController();
            controlador.iniciarPersonas(listaPersonas);
            Stage stage = new Stage();
            stage.setTitle("CONSULTAR");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void insertar(ActionEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/PopUp/InsertarPopUp.fxml"));

            Parent root = (Parent) fxml.load();
            InsertarPopUpController controlador = fxml.getController();
            controlador.setPersonas(listaPersonasUnico);

            Stage stage = new Stage();
            stage.setTitle("INSERTAR");
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    List<String> listaPersonasUnicoAux = controlador.devolverListaUnica();
                    List<Nodo> listaPersonasNodosAux = controlador.devolverListaNodos();
                    if (listaPersonasUnicoAux != null) {
                        for (String item : listaPersonasUnicoAux) {
                            if (listaPersonasUnico == null) {
                                listaPersonasUnico = new ArrayList<>();
                            }
                            listaPersonasUnico.add(item);
                        }
                    }

                    if (listaPersonasNodosAux != null) {
                        for (Nodo listaPersonasNodo : listaPersonasNodosAux) {
                            if (listaPersonas == null) {
                                listaPersonas = new ArrayList<Nodo>();
                            }
                            listaPersonas.add(listaPersonasNodo);
                        }
                    }
                }
            });
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void generarImagen() {
        try {
            Tree tree = new Tree();
            for (Nodo listaPersona : listaPersonas) {
                tree.insertTreeNode(listaPersona.getHijo(), listaPersona.getPadre());
                System.out.println("-------------------------");
                System.out.println("Padre : " + listaPersona.getPadre());
                System.out.println("Hijo : " + listaPersona.getHijo());
            }
            tree.graphTree(tree);

            TextArea textArea = new TextArea("Se generó la imagen en la ruta: " + System.getProperty("user.home") + "/tree.gif");
            textArea.setEditable(false);
            textArea.setWrapText(true);
            GridPane gridPane = new GridPane();
            gridPane.setMaxWidth(Double.MAX_VALUE);
            gridPane.add(textArea, 0, 0);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion");
            alert.getDialogPane().setContent(gridPane);
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaPersonas = new ArrayList<>();
        listaPersonasUnico = new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
        } catch (Exception e) {
        }
    }

}
