/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package inventariomodule.inventario;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.beans.value.ObservableValue;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;      
import javafx.collections.ObservableList; 
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.scene.control.cell.PropertyValueFactory;
import inventariomodule.InventarioDAL.*;
import inventariomodule.inventarioBLL.*;

/**
 * FXML Controller class
 *
 * @author 
 */
public class INVENTARIO_GUI implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                // Listener: cada vez que cambie la pestaña seleccionada…
        tabPane.getSelectionModel().selectedItemProperty().addListener(this::onTabChanged);

        // Llamada inicial para que la visibilidad se ajuste al tab por defecto
        onTabChanged(null, null, tabPane.getSelectionModel().getSelectedItem());
        
//        colFecha.setVisible(false);
//        colHora.setVisible(false);
        
//        cbOrigen.setItems(FXCollections.observableArrayList(currentUser));  // “IBARRA” o “QUITO”
//        cbOrigen.getSelectionModel().selectFirst();
//        cbOrigen.setDisable(true);  // bloqueamos la edición

        // 2) Destino: se cargan desde la base de datos o un servicio
        // (simulamos aquí)
//        List<String> todosLosDestinos = List.of("Quito","Ibarra","Cuenca","Guayaquil");
//        cbDestino.setItems(FXCollections.observableArrayList(todosLosDestinos));
//
//        // 3) DatePickers con valores por defecto (opcional)
        //dpFechaDesde.setValue(LocalDate.now().minusDays(7));
        //dpFechaHasta.setValue(LocalDate.now());
//        
//        colcodViaje      .setCellValueFactory(new PropertyValueFactory<>("codViaje"));
//        colConductor     .setCellValueFactory(new PropertyValueFactory<>("RUC"));
//        colVehiculo      .setCellValueFactory(new PropertyValueFactory<>("codVehiculo"));
//        ColTerminal      .setCellValueFactory(new PropertyValueFactory<>("codCUM"));
//        colFecha         .setCellValueFactory(new PropertyValueFactory<>("fechaViaje"));
//        colHora          .setCellValueFactory(new PropertyValueFactory<>("horaViaje"));
//        colCiudadDestino .setCellValueFactory(new PropertyValueFactory<>("ciudadDestino"));
//        colPrecio        .setCellValueFactory(new PropertyValueFactory<>("precioViaje"));
        
        // Medicamento
        colcodIdMedicamento.setCellValueFactory(new PropertyValueFactory<>("Id_Medicamento"));
        colcodCUM.setCellValueFactory(new PropertyValueFactory<>("CUM"));
        colProveedor.setCellValueFactory(new PropertyValueFactory<>("Proveedor"));
        colNombreGenerico.setCellValueFactory(new PropertyValueFactory<>("Nombre_Generico"));
        colNombreComercial.setCellValueFactory(new PropertyValueFactory<>("Nombre_Comercial"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        colFormaFarmaceutica.setCellValueFactory(new PropertyValueFactory<>("FormaFarmaceutica"));
        colConcentracion.setCellValueFactory(new PropertyValueFactory<>("Concentracion"));
        colPresentacion.setCellValueFactory(new PropertyValueFactory<>("Presentacion"));

//        // Proveedor
        colcodProveedor.setCellValueFactory(new PropertyValueFactory("idProveedor"));
        colNombreProveedor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colRUC.setCellValueFactory(new PropertyValueFactory<>("ruc"));
        
//
//        // Pedido
//        colcodVehiculo.setCellValueFactory(new PropertyValueFactory<>("codVehiculo"));
//        colTerminalVehiculo.setCellValueFactory(new PropertyValueFactory<>("codCUM"));
//        colPlacaVehiculo.setCellValueFactory(new PropertyValueFactory<>("placaVehiculo"));
//        colCapacidadVehiculo.setCellValueFactory(new PropertyValueFactory<>("capacidadVehiculo"));
//        colCompañiaVehiculo.setCellValueFactory(new PropertyValueFactory<>("compañiaVehiculo"));
//        
//        // Pasajero
//        colPasCodigo.setCellValueFactory(new PropertyValueFactory<>("codPasajero"));
//        colViajePasajero.setCellValueFactory(new PropertyValueFactory<>("codViaje"));
//        colPasNombre.setCellValueFactory(new PropertyValueFactory<>("nombrePasajero"));
//        colPasApellido.setCellValueFactory(new PropertyValueFactory<>("apellidoPasajero"));
//        colPasTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoPasajero"));
//        colPasCedula.setCellValueFactory(new PropertyValueFactory<>("cedulaPasajero"));
//        colPasCorreo.setCellValueFactory(new PropertyValueFactory<>("correoPasajero"));
//        
    }    
    
    @FXML private Button btnSalir;
    @FXML private Button btnBuscar;
    @FXML private TabPane tabPane;
    @FXML private Tab tabMedicamentos, tabProveedores, tabPedidos, tabEntregas, tabHistorial;
    
    //@FXML private Button btnNuevo, btnEditar, btnVer;
//    @FXML private TableView<ViajeVista> tblViajes;
    
    /* 
    @FXML private TableColumn<ViajeVista, Integer> colcodViaje;
    @FXML private TableColumn<ViajeVista, Integer> colConductor;
    @FXML private TableColumn<ViajeVista, Integer> colVehiculo;
    @FXML private TableColumn<ViajeVista, Integer> ColTerminal;       // coincide con fx:id="ColTerminal"
    @FXML private TableColumn<ViajeVista, LocalDate> colFecha;
    @FXML private TableColumn<ViajeVista, LocalTime> colHora;
    @FXML private TableColumn<ViajeVista, String> colCiudadDestino;
    @FXML private TableColumn<ViajeVista, Double> colPrecio;
    */
    // Tabla Medicamentos
    @FXML private TableView<MedicamentoVista> tblTerminal;
    @FXML private TableColumn<MedicamentoVista, Integer> colcodIdMedicamento;
    @FXML private TableColumn<MedicamentoVista, String> colcodCUM;
    @FXML private TableColumn<MedicamentoVista, String> colProveedor;
    @FXML private TableColumn<MedicamentoVista, String> colNombreGenerico;
    @FXML private TableColumn<MedicamentoVista, String> colNombreComercial;
    @FXML private TableColumn<MedicamentoVista, Integer> colCantidad;
    @FXML private TableColumn<MedicamentoVista, String> colFormaFarmaceutica;
    @FXML private TableColumn<MedicamentoVista, String> colConcentracion;
    @FXML private TableColumn<MedicamentoVista, String> colPresentacion;
    
    @FXML private TableView<ProveedorVista> tblProveedor;
    @FXML private TableColumn<ProveedorVista, Integer> colcodProveedor;
    @FXML private TableColumn<ProveedorVista, String> colNombreProveedor;
    @FXML private TableColumn<ProveedorVista, String> colRUC;
    
    
    // Tabla Conductor
    //@FXML private TableView<ConductorVista> tblConductores;
    //@FXML private TableColumn<ConductorVista, Integer> colRUC;
    //@FXML private TableColumn<ConductorVista, Integer> colnombreProveedor;
    //@FXML private TableColumn<ConductorVista, String> colNombreConductor;
    //@FXML private TableColumn<ConductorVista, String> colApellidoConductor;
    //@FXML private TableColumn<ConductorVista, String> colLicenciaConductor;
    
    // Tabla Vehiculo
    //@FXML private TableView<VehiculoVista> tblVehiculo;
    //@FXML private TableColumn<VehiculoVista, Integer> colcodVehiculo;
    //@FXML private TableColumn<VehiculoVista, Integer> colTerminalVehiculo;
    //@FXML private TableColumn<VehiculoVista, String> colPlacaVehiculo;
    //@FXML private TableColumn<VehiculoVista, Integer> colCapacidadVehiculo;
    //@FXML private TableColumn<VehiculoVista, String> colCompañiaVehiculo;    
    
    // Tabla Pasajeros
    /*
    @FXML private TableView<PasajeroVista> tblPasajeros;
    @FXML private TableColumn<PasajeroVista,Integer>   colPasCodigo;
    @FXML private TableColumn<PasajeroVista,Integer>   colViajePasajero;
    @FXML private TableColumn<PasajeroVista,String>   colPasNombre;
    @FXML private TableColumn<PasajeroVista,String>   colPasApellido;
    @FXML private TableColumn<PasajeroVista,Integer>   colPasTelefono;
    @FXML private TableColumn<PasajeroVista,Integer>   colPasCedula;
    @FXML private TableColumn<PasajeroVista,String>   colPasCorreo;
    */
    //@FXML private DatePicker   dpFechaDesde;
    //@FXML private DatePicker   dpFechaHasta;
    /*
    @FXML private ComboBox<String> cbOrigen;
    @FXML private ComboBox<String> cbDestino;
    */
    
    // --- Tabla Viajes ---
    

    /*
    private String currentUser;

    public void setCurrentUser(String user) {
    this.currentUser = user;
            // 1) Poblamos el combo con ese único valor:
        ObservableList<String> datos = 
            FXCollections.observableArrayList(this.currentUser);
        cbOrigen.setItems(datos);

        // 2) Lo seleccionamos:
        cbOrigen.getSelectionModel().selectFirst();

        // 3) Lo deshabilitamos para que no se cambie
        cbOrigen.setDisable(true);
    configureColumns();  
    }*/

    
// private void onTabChanged(ObservableValue<? extends Tab> obs, Tab oldTab, Tab newTab) {
//        if (newTab == tabProveedores) {
//            // En “Viajes”: mostrar los tres
//            btnNuevo.setVisible(true);
//            btnEditar.setVisible(true);
//            btnVer.setVisible(true);
//        }
//        else if (newTab == tabHistorial) {
//            // En “Pasajeros”: solo Nuevo y Editar
//            btnNuevo.setVisible(true);
//            btnEditar.setVisible(true);
//            btnVer.setVisible(false);
//        }
//        else {
//            // En cualquier otra pestaña: ninguno
//            btnNuevo.setVisible(false);
//            btnEditar.setVisible(false);
//            btnVer.setVisible(false);
//        }
//
//        // Si quieres que desaparezcan totalmente (sin dejar espacio), haz también:
//        btnNuevo.setManaged(btnNuevo.isVisible());
//        btnEditar.setManaged(btnEditar.isVisible());
//        btnVer.setManaged(btnVer.isVisible());
//    }
    
    private void onTabChanged(ObservableValue<? extends Tab> obs, Tab oldTab, Tab newTab) {
    // — botones (ya lo tienes)
    
    
    boolean isViajes    = newTab == tabProveedores;
    boolean isPasajeros = newTab == tabHistorial;
    boolean isTerminal = newTab == tabMedicamentos;
    boolean isConductor = newTab == tabEntregas;
    boolean isVehiculo = newTab == tabPedidos;

    /*
    btnNuevo.setVisible(isViajes || isPasajeros);
    btnEditar.setVisible(isViajes || isPasajeros);
    btnVer   .setVisible(isViajes);*/

//    if (isViajes) {
//    
//     // 1) Llama al servicio
//    ViajeVistaService service = new ViajeVistaService();
//    List<ViajeVista> lista = service.obtenerTodosLosViajes();
//
//    // 2) Convierte a ObservableList
//    ObservableList<ViajeVista> data = FXCollections.observableArrayList(lista);
//
//    // 3) Asigna al TableView
//    tblViajes.setItems(data);
//    
//    }
//    
//    if (isVehiculo) {
//    
//     // 1) Llama al servicio
//    VehiculoVistaService service = new VehiculoVistaService();
//    List<VehiculoVista> lista = service.obtenerTodosLosVehiculos();
//
//    // 2) Convierte a ObservableList
//    ObservableList<VehiculoVista> data = FXCollections.observableArrayList(lista);
//
//    // 3) Asigna al TableView
//    tblVehiculo.setItems(data);
//    
//    }
    
    
    if (isTerminal) {

    // 1) Llama al servicio
    ConductorVistaService service = new ConductorVistaService();
    List<MedicamentoVista> lista = service.obtenerTodosLosConductores();

    // 2) Convierte a ObservableList
    ObservableList<MedicamentoVista> data = FXCollections.observableArrayList(lista);

    // 3) Asigna al TableView
    tblTerminal.setItems(data);
}
    
    
    if (isViajes) {

    // 1) Llama al servicio
    ProveedorVistaService service = new ProveedorVistaService();
    List<ProveedorVista> lista = service.obtenerTodosLosProveedores();

    // 2) Convierte a ObservableList
    ObservableList<ProveedorVista> data = FXCollections.observableArrayList(lista);

    // 3) Asigna al TableView
    tblProveedor.setItems(data);
}
    
    


//       if (isConductor) {
//    
//     // 1) Llama al servicio
//    ConductorVistaService service = new ConductorVistaService();
//    List<ConductorVista> lista = service.obtenerTodosLosConductores();
//
//    // 2) Convierte a ObservableList
//    ObservableList<ConductorVista> data = FXCollections.observableArrayList(lista);
//
//    // 3) Asigna al TableView
//    tblConductores.setItems(data);
//    
//    }
    // — “Viajes” ya lo tenías, ahora “Pasajeros”:
//    if (isPasajeros) {
//        if ("IBARRA".equals(currentUser)) {
//            // Sólo Código, Nombre, Apellido, Cédula
//            colPasCodigo.setVisible(true);
//            colPasNombre.setVisible(true);
//            colPasApellido.setVisible(true);
//            colPasCedula.setVisible(true);
//            // Oculta Teléfono y Correo
//            colPasTelefono.setVisible(false);
//            colPasCorreo  .setVisible(false);
//        } else {  // Quito (u otro rol) ve TODO
//            colPasCodigo.setVisible(true);
//            colPasNombre.setVisible(true);
//            colPasApellido.setVisible(true);
//            colPasCedula.setVisible(true);
//            colPasTelefono.setVisible(true);
//            colPasCorreo  .setVisible(true);
//        }
//            // 1) Llama al servicio
//    PasajeroVistaService service = new PasajeroVistaService();
//    List<PasajeroVista> lista = service.obtenerTodosLosPasajeros();
//
//    // 2) Convierte a ObservableList
//    ObservableList<PasajeroVista> data = FXCollections.observableArrayList(lista);
//
//    // 3) Asigna al TableView
//    tblPasajeros.setItems(data);
//    }
//    else {
//        // Si no es Pasajeros, opcionalmente oculta todas las columnas
//        colPasCodigo.setVisible(false);
//        colPasNombre.setVisible(false);
//        colPasApellido.setVisible(false);
//        colPasTelefono.setVisible(false);
//        colPasCedula.setVisible(false);
//        colPasCorreo.setVisible(false);
//    }
//}
//
//  private void configureColumns() {
//        if ("IBARRA".equals(currentUser)) {
//            // muestra Vehículo, oculta Fecha/Hora
//          
//            colFecha  .setVisible(false);
//            colHora   .setVisible(false);
//        } else if ("QUITO".equals(currentUser)) {
//            // muestra Fecha/Hora, oculta Vehículo
//           
//            colFecha  .setVisible(true);
//            colHora   .setVisible(true);
//        }
//     
//    }
  



    // 2) Llamada al servicio (aún por implementar)
//    List<Viaje> resultados = servicioViajes.buscar(
//        origen, destino, desde, hasta
//    );
    // servicioViajes.buscar(...) debería devolver List<Viaje>
//
//    // 3) Volcar al TableView
//    ObservableList<Viaje> modelo =
//        FXCollections.observableArrayList(resultados);
//    tblViajes.setItems(modelo);
}    @FXML private void handleSalir(ActionEvent event) {
        // Opción A: cerrar solo la ventana actual
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();

        // Opción B: terminar toda la aplicación
        // Platform.exit();
    }
    



    @FXML private void handleBuscar(ActionEvent event) {
    //LocalDate desde = dpFechaDesde.getValue();
    //LocalDate hasta = dpFechaHasta.getValue();
    // 1) Validación sencilla
    /*if (desde == null || hasta == null || desde.isAfter(hasta)) {
        new Alert(Alert.AlertType.WARNING,
            "Seleccione un rango de fechas válido."
        ).showAndWait();
        return;
    }*/}}



/* 


 @FXML private void handleVer(ActionEvent event) {
    try {
        // 1) Indica el FXML de la vista de detalle
        String vista = "ViajeInformacion.fxml";
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(vista)
        );
        // 2) Carga la interfaz
        Parent root = loader.load();

        // (Opcional) si luego quieres pasar datos:
        // ViajeInformacionController ctrl = loader.getController();
        // ctrl.setViaje(seleccionado);

        // 3) Crea un nuevo Stage para la ventana de detalle
        Stage detalleStage = new Stage();
        detalleStage.setTitle("Información del Viaje");
        detalleStage.setScene(new Scene(root));

        // 4) Hazla “propietaria” de la ventana actual (opcional)
        detalleStage.initOwner(
            ((Node)event.getSource()).getScene().getWindow()
        );
        // Si la quieres modal:
        // detalleStage.initModality(Modality.WINDOW_MODAL);

        // 5) Muestra la nueva ventana sin cerrar la principal
        detalleStage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR,
            "No se pudo abrir la pantalla de detalle:\n" + e.getMessage()
        ).showAndWait();
    }
}*/
//  @FXML private void handleNuevo(ActionEvent event) {
//        // 1) Decide qué FXML cargar
//        String fxml;
//        Tab selected = tabPane.getSelectionModel().getSelectedItem();
//        if (selected == tabProveedores) {
//            fxml = "ingreso_Viaje.fxml";
//        } else if (selected == tabHistorial) {
//            fxml = "ingreso_Pasajero.fxml";
//        } else {
//            // Si no es ni Viajes ni Pasajeros, ignoramos
//            return;
//        }
//
//        // 2) Carga y muestra en ventana modal
//        try {
//            FXMLLoader loader = new FXMLLoader(
//                getClass().getResource(fxml)
//            );
//            Parent root = loader.load();
//
//            Stage stage = new Stage();
//            stage.setTitle(
//                selected == tabProveedores ? "Nuevo Viaje" : "Nuevo Pasajero"
//            );
//            stage.setScene(new Scene(root));
//            stage.initOwner(((Node)event.getSource())
//                                 .getScene().getWindow());
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.showAndWait();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR,
//                "No se pudo abrir el formulario:\n" + e.getMessage()
//            ).showAndWait();
//        }
//    }

    
   
