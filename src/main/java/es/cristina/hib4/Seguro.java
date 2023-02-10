package es.cristina.hib4;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "seguro")

public class Seguro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSeguro")
    private int idSeguro;
    private int edad;
    private int numHijos;

    private String nif;
    private String nombre;
    private String ape1;
    private String ape2;
    private Date fechaCreacion;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="IdSeguro")
    @OrderColumn(name="idx")


    private List<AsistenciaMedica> asistenciasMedicas;

    public Seguro(){

    }
    public Seguro(int idSeguro, String nif, String nombre, String ape1, String ape2, int edad, int numHijos, Date fechaCreacion) {
        this.idSeguro = idSeguro;
        this.edad = edad;
        this.numHijos = numHijos;
        this.nif = nif;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.fechaCreacion = fechaCreacion;
    }

    public Seguro(int idSeguro) {
        this.idSeguro = idSeguro;
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

    public List<AsistenciaMedica> getAsistenciasMedicas() {
        return asistenciasMedicas;
    }

    public void setAsistenciasMedicas(List<AsistenciaMedica> asistenciasMedicas) {
        this.asistenciasMedicas = asistenciasMedicas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seguro seguro = (Seguro) o;
        return idSeguro == seguro.idSeguro && edad == seguro.edad && numHijos == seguro.numHijos && Objects.equals(nif, seguro.nif) && Objects.equals(nombre, seguro.nombre) && Objects.equals(ape1, seguro.ape1) && Objects.equals(ape2, seguro.ape2) && Objects.equals(fechaCreacion, seguro.fechaCreacion) && Objects.equals(asistenciasMedicas, seguro.asistenciasMedicas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSeguro, edad, numHijos, nif, nombre, ape1, ape2, fechaCreacion, asistenciasMedicas);
    }


}
