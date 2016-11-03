package com.example.dell.modelo;

import java.util.Date;

/**
 * Created by Dell on 23/10/2016.
 */
public class ModelCitas {

    private int id;
    private Date fecha;
    private String hora;
    private String clinica;
    private boolean cumplimiento;
    private int idUsuario;
    private int idMotivo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getClinica() {
        return clinica;
    }

    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    public boolean getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(boolean cumplimiento) {
        this.cumplimiento = cumplimiento;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(int idMotivo) {
        this.idMotivo = idMotivo;
    }

    public boolean isCumplimiento() {
        return cumplimiento;
    }

    @Override
    public String toString() {
        return "* id:" + id +
                ", fecha:" + fecha +
                ", hora:'" + hora + '\n' +
                "* clinica: '" + clinica +
                ", cumplimiento: " + cumplimiento +
                ", idUsuario: " + idUsuario +'\n' +
                "* idMotivo: " + idMotivo ;
    }
}
