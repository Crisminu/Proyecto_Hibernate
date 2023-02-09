package es.cristina.hib6;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class NIF {
    @Column(name = "nif")
    private String nif;
    public NIF() {
    }
    public NIF(String nif) {
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
    @Override
    public String toString() {
        return "NIF{" +
                "nif='" + nif + '\'' +
                '}';
    }
}
