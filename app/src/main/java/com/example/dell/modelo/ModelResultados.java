package com.example.dell.modelo;

import java.util.Date;

/**
 * Created by aalvarado on 31/10/2016.
 */
public class ModelResultados {

    private int id;
    private String adjunto;
    private Date fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String toString() {
        return "ModelResultados{" +
                "id=" + id +
                ", adjunto=" + adjunto+
                ", fecha=" + fecha+
                '}';
    }
}
