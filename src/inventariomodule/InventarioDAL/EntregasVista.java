package inventariomodule.InventarioDAL;

import java.time.LocalDateTime;

public class EntregasVista {
    private int idEgreso;
    private String proveedorNombre;
    private String hospitalNombre;
    private String medicamentoNombre;
    private LocalDateTime fechaEgreso;
    private int cantidad;
    private String observaciones;

    public EntregasVista(int idEgreso, String proveedorNombre, String hospitalNombre, String medicamentoNombre,
                       LocalDateTime fechaEgreso, int cantidad, String observaciones) {
        this.idEgreso = idEgreso;
        this.proveedorNombre = proveedorNombre;
        this.hospitalNombre = hospitalNombre;
        this.medicamentoNombre = medicamentoNombre;
        this.fechaEgreso = fechaEgreso;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
    }

    public int getIdEgreso() {
        return idEgreso;
    }

    public String getProveedorNombre() {
        return proveedorNombre;
    }

    public String getHospitalNombre() {
        return hospitalNombre;
    }

    public String getMedicamentoNombre() {
        return medicamentoNombre;
    }

    public LocalDateTime getFechaEgreso() {
        return fechaEgreso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }
}