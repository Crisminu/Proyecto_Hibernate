package es.cristina.hib1jdbc;

import java.sql.Date;

public class Seguro {
    private int idSeguro, edad, numHijos;
    private String nif, nombre, ape1, ape2;
    private Date fechaCreacion;

    public Seguro(int idSeguro, int edad, int numHijos, String nif, String nombre, String ape1, String ape2, Date fechaCreacion) {
        this.idSeguro = idSeguro;
        this.edad = edad;
        this.numHijos = numHijos;
        this.nif = nif;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(int numHijos) {
        this.numHijos = numHijos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
