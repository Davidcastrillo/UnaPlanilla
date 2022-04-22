/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla2.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author David
 */
public class EmpleadosViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtPapellido;
    @FXML
    private JFXTextField txtSapellido;
    @FXML
    private JFXTextField txtCedula;
    @FXML
    private JFXRadioButton rdbMasculino;
    @FXML
    private ToggleGroup tggGenero;
    @FXML
    private JFXRadioButton rdbFemenino;
    @FXML
    private JFXCheckBox chkAdministrador;
    @FXML
    private JFXCheckBox chkActivo;
    @FXML
    private JFXDatePicker dtpFIngreso;
    @FXML
    private JFXDatePicker dtpFSalida;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXPasswordField txtClave;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnGuardar;

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
    private void onKeyPressedTxtId(KeyEvent event) {
    }

    @FXML
    private void onActionChkAdministrador(ActionEvent event) {
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
    }

    @FXML
    private void onActionBtnBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
    }
    
}
