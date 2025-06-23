package modelo;

import java.util.Date;

public class Celebracion {
    private final int id;
    private Date fecha;
    private String descripcion;
    private String pais;

    public Celebracion(int id, Date fecha, String descripcion, String pais) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.pais = pais;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
