package es.cristina.hb1;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "seguro")
public class Seguro implements Serializable {
    @Id
    @Column(name = "idSeguro", unique = true)
    private int idSeguro;
    @Column(name = "edad", nullable = false)
    private int edad;
    @Column(name = "numHijos", nullable = false)
    private int numHijos;
    @Column(name = "nif", nullable = false)
    private String nif;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "ape1", nullable = false)
    private String ape1;
    @Column(name = "ape2", nullable = false)
    private String ape2;
    @Column(name = "fechaCreacion", nullable = false)
    private Date fechaCreacion;

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
