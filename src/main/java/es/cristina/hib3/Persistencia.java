package es.cristina.hib3;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    Seguro seguro = new Seguro(311, "12345678Z", "Juan", "Cano", "Morales", 38, 3, new Date(System.currentTimeMillis()));
AsistenciaMedica asistenciaMedica1=new AsistenciaMedica(311, seguro, "Ir al médico de cabecera por fiebre", "Valencia");
AsistenciaMedica asistenciaMedica2=new AsistenciaMedica(312, seguro, "Operacion de apendicitis", "Castellón");
}
