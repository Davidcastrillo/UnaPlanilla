/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.unaplanilla2.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.unaplanilla2.model.EmpleadoDto;
import cr.ac.una.unaplanilla2.util.Formato;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       BuscarEmpleado();
    }    

    @Override
    public void initialize() {
        
    }

    @FXML
    private void OnActionBtnAceptar(ActionEvent event) {
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
           
            
            
            
        } catch (Exception e) {
            System.out.println("jaja se callo xd");
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
    
}
