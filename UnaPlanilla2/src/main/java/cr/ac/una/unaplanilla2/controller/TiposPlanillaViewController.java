/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla2.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.unaplanilla2.model.EmpleadoDto;
import cr.ac.una.unaplanilla2.model.TipoplanillaDto;
import cr.ac.una.unaplanilla2.service.EmpleadoService;
import cr.ac.una.unaplanilla2.service.TipoPlanillaService;
import cr.ac.una.unaplanilla2.util.FlowController;
import cr.ac.una.unaplanilla2.util.Formato;
import cr.ac.una.unaplanilla2.util.Mensaje;
import cr.ac.una.unaplanilla2.util.Respuesta;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author David
 */
public class TiposPlanillaViewController extends  Controller implements Initializable {

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
     private TipoplanillaDto tipoPlanillaDto;
    private EmpleadoDto empleadoDto;
    
     List<Node> requeridos = new ArrayList<>();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtCodigo.setTextFormatter(Formato.getInstance().maxLengthFormat(4));
        txtDescripcion.setTextFormatter(Formato.getInstance().letrasFormat(40));
        txtPlanillasMes.setTextFormatter(Formato.getInstance().integerFormat());
        tipoPlanillaDto = new TipoplanillaDto();
        empleadoDto = new EmpleadoDto();
        nuevoTipoPlanilla();
        indicarRequeridos();
        
       tbcEliminar.setCellValueFactory((TableColumn.CellDataFeatures<EmpleadoDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));

        //Adding the Button to the cell
        tbcEliminar.setCellFactory((TableColumn<EmpleadoDto, Boolean> p) -> new ButtonCell());
 
        
     
        // TODO
    }    

    @Override
    public void initialize() {
    }

    @FXML
    private void onKeyPressedTxtId(KeyEvent event) {
       if (event.getCode() == KeyCode.ENTER && txtId.getText() != null && !txtId.getText().isEmpty()) {
            cargarTipoPlanilla(Long.valueOf(txtId.getText()));
        }
    }

    @FXML
    private void onKeyPressedTxtIdEmpleado(KeyEvent event) {
           if (event.getCode() == KeyCode.ENTER && !txtIdEmpleado.getText().isEmpty()){
            cargarEmpleado(Long.valueOf(txtIdEmpleado.getText()));
        } else if (event.getCode() == KeyCode.ENTER){
            new Mensaje().showModal(Alert.AlertType.ERROR, "Buscar empleado", getStage(), "Es necesario ingresar un id para buscar el empleado.");
        }
    }

    @FXML
    private void onActionBtnAgregar(ActionEvent event) {
           if (empleadoDto.getId() == null){
            new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar empleado", getStage(), "Es necesario consultar primero el empleado."); 
        } else {
            if(!tbvEmpleados.getItems().contains(empleadoDto)){
            empleadoDto.setModificado(true);
            tbvEmpleados.getItems().add(empleadoDto);
            tbvEmpleados.refresh(); 
            }
        }
        nuevoEmpleado();
    }

    @FXML
    private void onSelectionChangedTbpInclusionEmpleados(Event event) {
           if (tbpInclusionEmpleados.isSelected()) {
            if (tipoPlanillaDto.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Tipo planilla", getStage(), "Debe cargar el tipo de planilla al que desea agregar empleados.");
                tbpTipoPlanilla.getSelectionModel().select(tbpTipoPlanillas);
            }
        }
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
            if (tbpInclusionEmpleados.isSelected()) {
            nuevoEmpleado();
        } else if (tbpTipoPlanillas.isSelected()) {
            if (new Mensaje().showConfirmation("Limpiar tipo planilla", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
                nuevoTipoPlanilla();
            }
        }    
    }

    @FXML
    private void onActionBtnBuscar(ActionEvent event){
        BusquedaViewController busquedaController = (BusquedaViewController) FlowController.getInstance().getController("BusquedaView");
        busquedaController.busquedaPlanillas();
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(),true);
        TipoplanillaDto tipoPlanillaDto = (TipoplanillaDto) busquedaController.getResultado();
        if (tipoPlanillaDto != null){
            cargarTipoPlanilla(tipoPlanillaDto.getId());
        }

    }

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {
        try {
            if (tipoPlanillaDto.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tipo planilla", getStage(), "Debe cargar el tipo de planilla que desea eliminar.");
            } else {

                TipoPlanillaService service = new TipoPlanillaService();
                Respuesta respuesta = service.eliminarTipoPlanilla(tipoPlanillaDto.getId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tipo planilla", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar tipo planilla", getStage(), "Tipo planilla eliminado correctamente.");
                    nuevoTipoPlanilla();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TiposPlanillaViewController.class.getName()).log(Level.SEVERE, "Error eliminando el tipo planilla.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tipo planilla", getStage(), "Ocurrio un error eliminando el tipo planilla.");
        }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
                   try {
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tipo planilla", getStage(), invalidos);
            } else {

                TipoPlanillaService service = new TipoPlanillaService();
                Respuesta respuesta = service.guardarTipoPlanilla(tipoPlanillaDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tipo planilla", getStage(), respuesta.getMensaje());
                } else {
                    unbindTipoPlanilla();
                    tipoPlanillaDto = (TipoplanillaDto) respuesta.getResultado("TipoPlanilla");
                    bindTipoPlanilla(false);
                    nuevoEmpleado();
                    cargarEmpleados();
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar tipo planilla", getStage(), "Tipo planilla actualizado correctamente.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TiposPlanillaViewController.class.getName()).log(Level.SEVERE, "Error guardando el tipo de planilla.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tipo planilla", getStage(), "Ocurrio un error guardando el tipo de planilla.");
        }
    }

    private void nuevoTipoPlanilla() {
        unbindTipoPlanilla();
        tipoPlanillaDto = new TipoplanillaDto();
        bindTipoPlanilla(true);
        nuevoEmpleado();
        cargarEmpleados();
        txtId.clear();
        txtId.requestFocus();
        
    }

    private void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtCodigo, txtDescripcion, txtPlanillasMes));
        
    }

    private String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof JFXTextField && !((JFXTextField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && !((JFXPasswordField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXDatePicker && ((JFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXDatePicker) node).getAccessibleText();
                } else {
                    invalidos += "," + ((JFXDatePicker) node).getAccessibleText();
                }
                validos = false;
            } else if (node instanceof JFXComboBox && ((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((JFXComboBox) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }        
        
        
        
    }

    private void unbindTipoPlanilla() {
        txtId.textProperty().unbind();
        txtCodigo.textProperty().unbindBidirectional(tipoPlanillaDto.codigo);
        txtDescripcion.textProperty().unbindBidirectional(tipoPlanillaDto.descripcion);
        txtPlanillasMes.textProperty().unbindBidirectional(tipoPlanillaDto.planillasPorMes);
        chkActivo.selectedProperty().unbindBidirectional(tipoPlanillaDto.estado);
    }

    private void bindTipoPlanilla(boolean nuevo) {
        if (!nuevo) {
            txtId.textProperty().bind(tipoPlanillaDto.id);
        }
        txtCodigo.textProperty().bindBidirectional(tipoPlanillaDto.codigo);
        txtDescripcion.textProperty().bindBidirectional(tipoPlanillaDto.descripcion);
        txtPlanillasMes.textProperty().bindBidirectional(tipoPlanillaDto.planillasPorMes);
        chkActivo.selectedProperty().bindBidirectional(tipoPlanillaDto.estado);       
    }

    private void nuevoEmpleado() {
        unbindEmpleado();
        empleadoDto = new EmpleadoDto();
        bindEmpleado(true);
        txtIdEmpleado.clear();
        txtIdEmpleado.requestFocus();
    }

    private void cargarEmpleados() {
      if ( tipoPlanillaDto != null && tipoPlanillaDto.getEmpleados() != null ){
            tbvEmpleados.getItems().clear();
            tbvEmpleados.setItems(tipoPlanillaDto.getEmpleados());
            tbvEmpleados.refresh();
        }
    }

    private void cargarTipoPlanilla(Long id) {
        TipoPlanillaService service = new TipoPlanillaService();
        Respuesta respuesta = service.getTipoPlanilla(id);

        if (respuesta.getEstado()) {
            unbindTipoPlanilla();
            tipoPlanillaDto = (TipoplanillaDto) respuesta.getResultado("TipoPlanilla");
            bindTipoPlanilla(false);
            validarRequeridos();
            cargarEmpleados();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar tipo planilla", getStage(), respuesta.getMensaje());
        }
    }

    private void bindEmpleado(boolean nuevo) {
        if (!nuevo) {
            txtIdEmpleado.textProperty().bind(this.empleadoDto.id);
        }
        txtNombreEmpleado.textProperty().bindBidirectional(this.empleadoDto.nombre);
    }

    private void unbindEmpleado() {
        txtIdEmpleado.textProperty().unbind();        
        txtNombreEmpleado.textProperty().unbindBidirectional(this.empleadoDto.nombre);
    }

    private void cargarEmpleado(Long id) {
        EmpleadoService service = new EmpleadoService();
        Respuesta respuesta = service.getEmpleado(id);

        if (respuesta.getEstado()) {
            unbindEmpleado();
            empleadoDto = (EmpleadoDto) respuesta.getResultado("Empleados");
            bindEmpleado(false);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar empleado", getStage(), respuesta.getMensaje());
        }
        
        
        
    }
    
    
    private class ButtonCell extends TableCell<EmpleadoDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");

            cellButton.setOnAction((ActionEvent t) -> {
                EmpleadoDto emp = (EmpleadoDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                if (!emp.getModificado()){
                    tipoPlanillaDto.getEmpleadosEliminados().add(emp);
                }
                tbvEmpleados.getItems().remove(emp);
                tbvEmpleados.refresh();
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }
}
