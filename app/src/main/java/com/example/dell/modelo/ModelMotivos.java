package com.example.dell.modelo;



/**
 * Created by aalvarado on 26/10/2016.
 */
public class ModelMotivos {

    private int id;
    private String descripcion;

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

    public String toString() {
        return "ModelMotivos{" +
                "id=" + id +
                ", descripcion=" + descripcion +
                '}';
    }

}
