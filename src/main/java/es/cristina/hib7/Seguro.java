package es.cristina.hib7;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "seguro")

public class Seguro implements Serializable {
    @Id
    //@GenericGenerator(name = "gen", strategy = "increment")
    //@GeneratedValue(generator = "gen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSeguro")
    private int idSeguro;
    @Embedded
    private NIF nif;
    private String nombre;
    private String ape1;
    private String ape2;
    private int edad;
    @Enumerated(EnumType.ORDINAL)
    private Sexo sexo;
    @Column(name = "casado")
    private boolean casado;
    private int numHijos;
    private boolean embarazada;
    @Embedded
    private Coberturas coberturas;
    @Embedded
    private Enfermedades enfermedades;
    private java.util.Date fechaCreacion;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSeguro")
    @OrderColumn(name = "idx")
    private List<AsistenciaMedica> asistenciasMedicas;

    public Seguro() {

    }

    public Seguro(NIF nif, String nombre, String ape1, String ape2, int edad, Sexo sexo, boolean casado, int numHijos, boolean embarazada, Coberturas coberturas, Enfermedades enfermedades, java.util.Date fechaCreacion) {
        this.nif = nif;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.edad = edad;
        this.sexo = sexo;
        this.casado = casado;
        this.numHijos = numHijos;
        this.embarazada = embarazada;
        this.coberturas = coberturas;
        this.enfermedades = enfermedades;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public NIF getNif() {
        return nif;
    }

    public void setNif(NIF nif) {
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public boolean isCasado() {
        return casado;
    }

    public void setCasado(boolean casado) {
        this.casado = casado;
    }

    public int getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(int numHijos) {
        this.numHijos = numHijos;
    }

    public boolean isEmbarazada() {
        return embarazada;
    }

    public void setEmbarazada(boolean embarazada) {
        this.embarazada = embarazada;
    }

    public Coberturas getCoberturas() {
        return coberturas;
    }

    public void setCoberturas(Coberturas coberturas) {
        this.coberturas = coberturas;
    }

    public Enfermedades getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(Enfermedades enfermedades) {
        this.enfermedades = enfermedades;
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
}
