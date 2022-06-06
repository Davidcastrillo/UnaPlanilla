/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla2.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.unaplanilla2.model.EmpleadoDto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author David
 */
public class TiposPlanillaController extends  Controller implements Initializable {

    @FXML
    private TabPane tbpTipoPlanilla;
    @FXML
    private Tab tbpTipoPlanillas;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXCheckBox chkActivo;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtDescripcion;
    @FXML
    private JFXTextField txtPlanillasMes;
    @FXML
    private Tab tbpInclusionEmpleados;
    @FXML
    private JFXTextField txtIdEmpleado;
    @FXML
    private JFXTextField txtNombreEmpleado;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private TableView<EmpleadoDto> tbvEmpleados;
    @FXML
    private TableColumn<EmpleadoDto,String> tbcCodigo;
    @FXML
    private TableColumn<EmpleadoDto, String> tbcNombre;
    @FXML
    private TableColumn<EmpleadoDto, Boolean> tbcEliminar;
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
    private void onKeyPressedTxtIdEmpleado(KeyEvent event) {
    }

    @FXML
    private void onActionBtnAgregar(ActionEvent event) {
    }

    @FXML
    private void onSelectionChangedTbpInclusionEmpleados(Event event) {
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
