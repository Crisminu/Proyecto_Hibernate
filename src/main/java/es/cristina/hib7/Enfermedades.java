package es.cristina.hib7;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
@Embeddable
public class Enfermedades {
    @Column(name = "corazon")
    private boolean corazon;
    private boolean estomacal;
    private boolean rinyones;
    private boolean alergia;
    private String nombreAlergia;

    public Enfermedades() {
    }
    public Enfermedades(boolean corazon, boolean estomacal, boolean rinyones, boolean alergia, String nombreAlergia) {
        this.corazon = corazon;
        this.estomacal = estomacal;
        this.rinyones = rinyones;
        this.alergia = alergia;
        this.nombreAlergia = nombreAlergia;
    }

    public boolean isCorazon() {
        return corazon;
    }

    public void setCorazon(boolean corazon) {
        this.corazon = corazon;
    }

    public boolean isEstomacal() {
        return estomacal;
    }

    public void setEstomacal(boolean estomacal) {
        this.estomacal = estomacal;
    }

    public boolean isRinyones() {
        return rinyones;
    }

    public void setRinyones(boolean rinyones) {
        this.rinyones = rinyones;
    }

    public boolean isAlergia() {
        return alergia;
    }

    public void setAlergia(boolean alergia) {
        this.alergia = alergia;
    }

    public String getNombreAlergia() {
        return nombreAlergia;
    }

    public void setNombreAlergia(String nombreAlergia) {
        this.nombreAlergia = nombreAlergia;
    }
    @Override
    public String toString() {
        return "Enfermedades{" +
                "corazon=" + corazon +
                ", estomacal=" + estomacal +
                ", rinyones=" + rinyones +
                ", alergia=" + alergia +
                ", nombreAlergia='" + nombreAlergia + '\'' +
                '}';
    }
}
