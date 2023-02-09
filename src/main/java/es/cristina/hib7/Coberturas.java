package es.cristina.hib6;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Coberturas {
    @Column(name = "oftalmologia")
    private boolean oftalmologia;
    @Column(name = "dental")
    private boolean dental;
    @Column(name = "fecundacionInVitro")
    private boolean fecundacionInVitro;

    public Coberturas() {
    }
    public Coberturas(boolean oftalmologia, boolean dental, boolean fecundacionInVitro) {
        this.oftalmologia = oftalmologia;
        this.dental = dental;
        this.fecundacionInVitro = fecundacionInVitro;
    }

    public boolean getOftalmologia() {
        return oftalmologia;
    }

    public void setOftalmologia(boolean oftalmologia) {
        this.oftalmologia = oftalmologia;
    }

    public boolean getDental() {
        return dental;
    }

    public void setDental(boolean dental) {
        this.dental = dental;
    }

    public boolean getFecundacioninVitro() {
        return fecundacionInVitro;
    }

    public void setFecundacioninVitro(boolean fecundacioninVitro) {
        this.fecundacionInVitro = fecundacioninVitro;
    }
    @Override
    public String toString() {
        return "Coberturas{" +
                "oftalmologia=" + oftalmologia +
                ", dental=" + dental +
                ", fecundacionInVitro=" + fecundacionInVitro +
                '}';
    }
}
