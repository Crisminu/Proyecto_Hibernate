package es.cristina.hib7;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "asistenciamedica")
@NamedQuery(name = "grandesGastos", query = "from AsistenciaMedica a where importe >= 10000")
public class AsistenciaMedica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAsistenciaMedica")
    private int idAsistenciaMedica;
    @ManyToOne
    @JoinColumn(name = "idSeguro")
    private Seguro seguro;
    private String breveDescripcion;
    private String lugar;
    private String explicacion;
    @Enumerated(EnumType.STRING)
    private TipoAsistencia tipoAsistencia;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Temporal(TemporalType.TIME)
    private Date hora;
    private BigDecimal importe;

    public AsistenciaMedica() {
    }

    public AsistenciaMedica(Seguro seguro, String breveDescripcion, String lugar, String explicacion, TipoAsistencia tipoAsistencia, Date fecha, Date hora, BigDecimal importe) {
        this.seguro = seguro;
        this.breveDescripcion = breveDescripcion;
        this.lugar = lugar;
        this.explicacion = explicacion;
        this.tipoAsistencia = tipoAsistencia;
        this.fecha = fecha;
        this.hora = hora;
        this.importe = importe;
    }
    public AsistenciaMedica(int idAsistenciaMedica, Seguro seguro, String breveDescripcion, String lugar) {
        this.idAsistenciaMedica = idAsistenciaMedica;
        this.seguro = seguro;
        this.breveDescripcion = breveDescripcion;
        this.lugar = lugar;
    }

    public TipoAsistencia getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(TipoAsistencia tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    public int getIdAsistenciaMedica() {
        return idAsistenciaMedica;
    }

    public void setIdAsistenciaMedica(int idAsistenciaMedica) {
        this.idAsistenciaMedica = idAsistenciaMedica;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public String getBreveDescripcion() {
        return breveDescripcion;
    }

    public void setBreveDescripcion(String breveDescripcion) {
        this.breveDescripcion = breveDescripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }



}
