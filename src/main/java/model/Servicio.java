/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author LAB704-00
 */
public class Servicio {
    
    private Integer id ;
    private String descripcion ;    
    private Double costo_hora;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCosto_hora() {
        return costo_hora;
    }

    public void setCosto_hora(Double costo_hora) {
        this.costo_hora = costo_hora;
    }
    
}
