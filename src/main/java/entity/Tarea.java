package entity;

public class Tarea {

    private int id;
    private String descripcion;
    private long fecha;
    private int seccion;

    public Tarea(int id, String descripcion, long fecha, int seccion) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.seccion = seccion;
    }

    public Tarea() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }
}
