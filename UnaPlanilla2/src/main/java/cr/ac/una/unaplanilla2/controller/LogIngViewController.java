/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla2.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.unaplanilla2.service.EmpleadoService;
import cr.ac.una.unaplanilla2.util.AppContext;
import cr.ac.una.unaplanilla2.util.FlowController;
import cr.ac.una.unaplanilla2.util.Mensaje;
import cr.ac.una.unaplanilla2.util.Respuesta;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David
 */
public class LogIngViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imvFondo;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXPasswordField txtClave;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnIngresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {// este solo se ejecuta 1 vez.
        //Forma de redimencionar una imagen
       imvFondo.fitHeightProperty().bind(root.heightProperty());
       imvFondo.fitWidthProperty().bind(root.widthProperty()); 
    }    

    @Override
    public void initialize() { // Este se muestra cada vez que se abre la ventana 
        txtClave.clear();
        txtUsuario.clear();
        
        
        
    }

    @FXML
    private void onActionBtnCancelar(ActionEvent event) {
        ((Stage)btnCancelar.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnIngresar(ActionEvent event) {

      try {
            if (txtUsuario.getText() == null || txtUsuario.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de usuario", getStage(), "Es necesario digitar un usuario para ingresar al sistema.");
            } else if (txtClave.getText() == null || txtClave.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de usuario", (Stage) btnIngresar.getScene().getWindow(), "Es necesario digitar la clave para ingresar al sistema.");
            } else {
               EmpleadoService empleadoService = new EmpleadoService();
                Respuesta respuesta = empleadoService.getUsuario(txtUsuario.getText(), txtClave.getText());
                if(respuesta.getEstado()){
                    AppContext.getInstance().set("Usuario", respuesta.getResultado("Empleados"));
                    FlowController.getInstance().goMain();
                    getStage().close();
                } else {
                    new Mensaje().show(Alert.AlertType.ERROR, "Validación Usuario", respuesta.getMensaje());
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(LogIngViewController.class.getName()).log(Level.SEVERE, "Error ingresando.", ex);
        }
    }
    
}
