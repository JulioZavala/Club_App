package app.model;

public class Campo {

    private int id;
    private String descripcion;
    private int estado;
    private int tipo;
    private double costoHora;
    private Local local;

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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(double costoHora) {
        this.costoHora = costoHora;
    }

    public Local getId_local() {
        return id_local;
    }

    public void setId_local(Local id_local) {
        this.id_local = id_local;
    }
    
    
    
}
