package com.example.dell.modelo;

/**
 * Created by Dell on 23/10/2016.
 */
public class ModelEcografias {

    private int id;
    private String imagen;
    private int mes;
    private int idCita;

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "* id: " + id +
                ", mes: " + mes + '\n' +
                "* imagen='" + imagen  + '\n' +
                "* idCita=" + idCita;
    }
}
