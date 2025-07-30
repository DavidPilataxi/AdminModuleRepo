
module InventarioDAL {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Exporta el paquete donde está tu clase App y controladores para que JavaFX pueda acceder
    exports inventariomodule.inventario;

    // Exporta el paquete con las clases DAL para que puedan ser usadas desde otros módulos si es necesario
    exports inventariomodule.InventarioDAL;

    // Abre los paquetes que usan reflexión (FXML) para que JavaFX pueda acceder a ellos en tiempo de ejecución
    opens com.mycompany.inventario to javafx.fxml;
    opens InventarioDAL to javafx.fxml;
}