package es.cristina.hib4;

import java.sql.Date;

public class Main {
    Seguro seguro = new Seguro(321, "12345678Z", "Carlos", "Perez", "Olmo", 54, 1, new Date(System.currentTimeMillis()));
    AsistenciaMedica asistenciaMedica1=new AsistenciaMedica(321, seguro, "médico de cabecera", "Mislata");
    AsistenciaMedica asistenciaMedica2=new AsistenciaMedica(322, seguro, "Operación de bypass", "Sevilla");
}
