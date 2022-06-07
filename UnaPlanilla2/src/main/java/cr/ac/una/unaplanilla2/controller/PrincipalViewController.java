/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla2.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.unaplanilla2.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author David
 */
public class PrincipalViewController extends Controller implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private Label lblTitulo;
    @FXML
    private JFXButton btnEmpleado;
    @FXML
    private JFXButton btnTipoPlanilla;
    @FXML
    private JFXButton btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
    }

    @FXML
    private void OnActionBtnEmpleado(ActionEvent event) {
        FlowController.getInstance().goView("EmpleadosView");
    }

    @FXML
    private void OnActionBtnTipoPlanilla(ActionEvent event) {
         FlowController.getInstance().goView("TiposPlanillaView");
    }

    @FXML
    private void OnActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().salir();
    }
    
}
