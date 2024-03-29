/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.unaplanilla2.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.unaplanilla2.model.EmpleadoDto;
import cr.ac.una.unaplanilla2.model.TipoplanillaDto;
import cr.ac.una.unaplanilla2.service.EmpleadoService;
import cr.ac.una.unaplanilla2.service.TipoPlanillaService;
import cr.ac.una.unaplanilla2.util.Formato;
import cr.ac.una.unaplanilla2.util.Mensaje;
import cr.ac.una.unaplanilla2.util.Respuesta;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author David
 */
public class BusquedaViewController extends Controller implements Initializable {

    @FXML
    private VBox vbxParametros;
    @FXML
    private JFXButton btnfFiltrar;
    @FXML
    private Label lblTitulo;
    @FXML
    private TableView tbvResultados;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnCancelar;
    
    private EventHandler<KeyEvent> keyEnter;
     private  Object resultado; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // evento para hacer funcionar el boton de filtrar 
            keyEnter = (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnfFiltrar .fire();
            }
        };
    }    

    @Override
    public void initialize() {
        
    }

    @FXML
    private void OnActionBtnAceptar(ActionEvent event) {
      resultado = tbvResultados.getSelectionModel().getSelectedItem();
        getStage().close();
    }
    
    
    public void BuscarEmpleado() {
        try {
            //Titulo
            lblTitulo.setText("Busqueda Empleados");
            //TextFields//
            //Cedula//
            JFXTextField txtCed = new JFXTextField();
            txtCed.setLabelFloat(true);
            txtCed.setPromptText("Cedula");
            txtCed.setOnKeyPressed(keyEnter);
            txtCed.setTextFormatter(Formato.getInstance().cedulaFormat(20));
            //Nombre//
            JFXTextField txtNombre = new JFXTextField();
            txtNombre.setLabelFloat(true);
            txtNombre.setPromptText("Nombre");
            txtNombre.setOnKeyPressed(keyEnter);
            txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
            //Primer Apellido
            JFXTextField txtPapellido = new JFXTextField();
            txtPapellido.setLabelFloat(true);
            txtPapellido.setPromptText("Primer Apellido");
            txtPapellido.setOnKeyPressed(keyEnter);
            txtPapellido.setTextFormatter(Formato.getInstance().letrasFormat(20));
            //Segundo Apellido
            JFXTextField txtSapellido = new JFXTextField();
            txtSapellido.setLabelFloat(true);
            txtSapellido.setPromptText("Segundo Apellido");
            txtSapellido.setOnKeyPressed(keyEnter);
            txtSapellido.setTextFormatter(Formato.getInstance().letrasFormat(20));
            
            // Agregar el vbox de parametros de busqueda
            vbxParametros.getChildren().clear();
            vbxParametros.getChildren().add(txtCed);
            vbxParametros.getChildren().add(txtNombre);      
            vbxParametros.getChildren().add(txtPapellido);
            vbxParametros.getChildren().add(txtSapellido);
             
            //tableview resultados
           
            tbvResultados.getColumns().clear();
            tbvResultados.getItems().clear();
            
            TableColumn<EmpleadoDto, String> tbcId = new TableColumn<>("Id");
            tbcId.setPrefWidth(10);
            tbcId.setCellValueFactory(cd -> cd.getValue().id);

            TableColumn<EmpleadoDto, String> tbcCedula = new TableColumn<>("Cédula");
            tbcCedula.setPrefWidth(100);
            tbcCedula.setCellValueFactory(cd -> cd.getValue().cedula);

            TableColumn<EmpleadoDto, String> tbcNombre = new TableColumn<>("Nombre");
            tbcNombre.setPrefWidth(150);
            tbcNombre.setCellValueFactory(cd -> cd.getValue().nombre);

            TableColumn<EmpleadoDto, String> tbcPApellido = new TableColumn<>("Primer Apellido");
            tbcPApellido.setPrefWidth(150);
            tbcPApellido.setCellValueFactory(cd -> cd.getValue().pApellido);

            TableColumn<EmpleadoDto, String> tbcSApellido = new TableColumn<>("Segundo Apellido");
            tbcSApellido.setPrefWidth(150);
            tbcSApellido.setCellValueFactory(cd -> cd.getValue().sApellido);

            tbvResultados.getColumns().add(tbcId);
            tbvResultados.getColumns().add(tbcCedula);
            tbvResultados.getColumns().add(tbcNombre);
            tbvResultados.getColumns().add(tbcPApellido);
            tbvResultados.getColumns().add(tbcSApellido);
            tbvResultados.refresh();
            tbvResultados.getItems().clear();
              // Metodo del Boton de filtrar 
              // Se le setea un accion para que compla con la funcionalidad sin tenen que crear el acccion desde el Scene builder
              
            btnfFiltrar.setOnAction((ActionEvent event) -> {
                tbvResultados.getItems().clear();
                EmpleadoService service = new EmpleadoService();
                
                String cedula = "%" + txtCed.getText() + "%";

                String nombre = "%" + txtNombre.getText() + "%";

                String pApellido = "%" + txtPapellido.getText() + "%";

                String sApellido = "%" + txtSapellido.getText() + "%";

                Respuesta respuesta = service.getEmpleados(cedula.toUpperCase(), nombre.toUpperCase(), pApellido.toUpperCase(), sApellido.toUpperCase());

                if (respuesta.getEstado()) {
                    ObservableList<EmpleadoDto> empleados = FXCollections.observableList((List<EmpleadoDto>) respuesta.getResultado("Empleados"));
                    tbvResultados.setItems(empleados);
                    tbvResultados.refresh();
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Consultar empleados", getStage(), respuesta.getMensaje());
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(BusquedaViewController.class.getName()).log(Level.SEVERE, "Error consultando los empleados.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consultar empleados", getStage(), "Ocurrio un error consultado los empleados.");
        }
            
            
           
        
        
    }
      public void busquedaPlanillas() {
        try {
            lblTitulo.setText("Búsqueda de planillas");
            JFXTextField txtCod = new JFXTextField();
            txtCod.setLabelFloat(true);
            txtCod.setPromptText("Codigo");
            txtCod.setOnKeyPressed(keyEnter);
            txtCod.setTextFormatter(Formato.getInstance().cedulaFormat(40));

            JFXTextField txtDescripcion = new JFXTextField();
            txtDescripcion.setLabelFloat(true);
            txtDescripcion.setPromptText("Descripcion");
            txtDescripcion.setTextFormatter(Formato.getInstance().letrasFormat(30));
            txtDescripcion.setOnKeyPressed(keyEnter);

            JFXTextField txtPlanillaxMes = new JFXTextField();
            txtPlanillaxMes.setLabelFloat(true);
            txtPlanillaxMes.setPromptText("Planilla x Mes");
            txtPlanillaxMes.setTextFormatter(Formato.getInstance().cedulaFormat(30));
            txtPlanillaxMes.setOnKeyPressed(keyEnter);

            ////
            JFXTextField txtCedula = new JFXTextField();
            txtCedula.setLabelFloat(true);
            txtCedula.setPromptText("Cédula");
            txtCedula.setOnKeyPressed(keyEnter);
            txtCedula.setTextFormatter(Formato.getInstance().cedulaFormat(40));

            JFXTextField txtId = new JFXTextField();
            txtId.setLabelFloat(true);
            txtId.setPromptText("Id del Empleado");
            txtId.setOnKeyPressed(keyEnter);
            txtId.setTextFormatter(Formato.getInstance().cedulaFormat(40));
            vbxParametros.getChildren().clear();
            vbxParametros.getChildren().add(txtCod);
            vbxParametros.getChildren().add(txtDescripcion);
            vbxParametros.getChildren().add(txtPlanillaxMes);
            vbxParametros.getChildren().add(txtId);
            vbxParametros.getChildren().add(txtCedula);

            tbvResultados.getColumns().clear();
            tbvResultados.getItems().clear();

            TableColumn<TipoplanillaDto, String> tbcIdP = new TableColumn<>("Id Planilla");
            tbcIdP.setPrefWidth(100);
            tbcIdP.setCellValueFactory(cd -> cd.getValue().id);

            TableColumn<TipoplanillaDto, String> tbcCodP = new TableColumn<>("Cod Planilla");
            tbcCodP.setPrefWidth(100);
            tbcCodP.setCellValueFactory(cd -> cd.getValue().codigo);

            TableColumn<TipoplanillaDto, String> tbcDescrp = new TableColumn<>("Descripcion Planilla");
            tbcDescrp.setPrefWidth(100);
            tbcDescrp.setCellValueFactory(cd -> cd.getValue().descripcion);

            TableColumn<TipoplanillaDto, String> tbcPlaXmes = new TableColumn<>("Planilla por Mes");
            tbcPlaXmes.setPrefWidth(150);
            tbcPlaXmes.setCellValueFactory(cd -> cd.getValue().planillasPorMes);

            tbvResultados.getColumns().add(tbcIdP);
            tbvResultados.getColumns().add(tbcCodP);
            tbvResultados.getColumns().add(tbcDescrp);
            tbvResultados.getColumns().add(tbcPlaXmes);
            tbvResultados.refresh();
            btnfFiltrar.setOnMouseClicked((event) -> {
                tbvResultados.getItems().clear();
                TipoPlanillaService service = new TipoPlanillaService();
                String idP = "%" + tbcIdP.getText() + "%";

                String cod = "%" + txtCod.getText() + "%";

                String Descri = "%" + txtDescripcion.getText() + "%";

                String PlxM = "%" + txtPlanillaxMes.getText() + "%";

                String cedula = "%" + txtCedula.getText() + "%";

                String idEmp = "%" + txtId.getText() + "%";

                Respuesta respuesta = service.getTipoplanillas(cod.toUpperCase(), Descri.toUpperCase(), PlxM.toUpperCase(),idEmp.toUpperCase(),cedula.toUpperCase());

                if (respuesta.getEstado()) {
                    ObservableList<TipoplanillaDto> tipoplani = FXCollections.observableList((List<TipoplanillaDto>) respuesta.getResultado("TipoPlanillas"));
                    tbvResultados.setItems(tipoplani);
                    tbvResultados.refresh();
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Consultar empleados", getStage(), respuesta.getMensaje());
                }
            });

        } catch (Exception ex) {
            Logger.getLogger(BusquedaViewController.class.getName()).log(Level.SEVERE, "Error consultando los empleados.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consultar empleados", getStage(), "Ocurrio un error consultado los empleados.");
        }
    }


    @FXML
    private void OnActionBtnCancelar(ActionEvent event) {
        
    }

    @FXML
    private void OnMousePressedTvbResultados(MouseEvent event) {
           if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            OnActionBtnAceptar(null);
            
    }
    }

    public Object getResultado() {
        return resultado;
    }

    public void setResultado(Object resultado) {
        this.resultado = resultado;
    }
    
    
}
