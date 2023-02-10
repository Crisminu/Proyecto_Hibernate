package es.cristina.hib6;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Persistencia {
    protected SessionFactory sf;
    protected void configuracion() {
        final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure("hibernate6.cfg.xml").build();
        try {
            sf = new MetadataSources(registro).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registro);
        }
    }
    protected void exit() {
        sf.close();
    }

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        Persistencia persistencia = new Persistencia();
        persistencia.configuracion();
        int opcion;
        boolean acceso = true;
        while(acceso){
            System.out.println("Hibernate 6. Anotaciones");
            System.out.println("1. Añadir seguro");
            System.out.println("2. Listar seguros");
            System.out.println("3. Salir");
            System.out.print("Introduzca una opción: ");
            opcion = sc.nextInt();
            switch (opcion){
                case 1 -> {
                   persistencia.datosEjemplo();
                   System.out.println("Seguro creado");
                }
                case 2 -> {
                    System.out.println("Introduce el código de seguro:");
                    int idSeguro = sc.nextInt();
                    persistencia.leerSeguro(idSeguro);
                }
                case 5 -> {
                    acceso = false;
                    System.out.println("Fin de la ejecución");
                }
                default -> System.out.println("Introduzca una opción válida");
            }
        }
        persistencia.exit();
    }
    protected void leerSeguro(int idSeguro){
        Session sesion = sf.openSession();
        Seguro seguro = new Seguro(idSeguro);
        if (sesion.get(Seguro.class, idSeguro) != null) {
            seguro = sesion.get(Seguro.class, idSeguro);
        }
        System.out.println("Código de seguro: " + seguro.getIdSeguro());
        System.out.println("NIF: " + seguro.getNif());
        System.out.printf("Nombre y apellidos: %s %s %s \n", seguro.getNombre(), seguro.getApe1(), seguro.getApe2()  );
        System.out.println("Edad: " + seguro.getEdad());
        System.out.println("Número de hijos: " + seguro.getNumHijos());
        System.out.println("Fecha de creación: " + seguro.getFechaCreacion());

        for (AsistenciaMedica asistenciaMedica: seguro.getAsistenciasMedicas()) {
            System.out.println("Asistencia médica: ");
            System.out.println("Codigo de asistencia: " + asistenciaMedica.getIdAsistenciaMedica());
            System.out.println("Descripciónn de la asistencia: " + asistenciaMedica.getBreveDescripcion());
            System.out.println("Lugar de la asistencia: " + asistenciaMedica.getLugar());
        }
        sesion.close();
    }
    public void datosEjemplo() throws ParseException {

        Session sesion = sf.openSession();
        sesion.beginTransaction();

        SimpleDateFormat sdfFecha=new SimpleDateFormat("dd/MM/yyyy");
        sdfFecha.setLenient(false);
        SimpleDateFormat sdfHora=new SimpleDateFormat("HH:mm:ss");
        sdfHora.setLenient(false);
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        NIF nif = new NIF("36254784E");
        Coberturas coberturas = new Coberturas(false, true, false);
        Enfermedades enfermedades = new Enfermedades(true, false, true, false, null);
        Seguro seguro = new Seguro(nif, "Rosa", "Ramirez", "Arellano", 41, Sexo.Mujer, true, 1, false, coberturas, enfermedades, date);
        AsistenciaMedica asistenciaMedica1 = new AsistenciaMedica(seguro, "Golpe en el brazo", "Madrid", "Fractura del radio derecho de la mano debido a golpe contundente con el suelo. Se escayola el brazo", TipoAsistencia.Hospitalaria, sdfFecha.parse("31/12/2013"),sdfHora.parse("11:21:45"), new BigDecimal("700.31"));
        AsistenciaMedica asistenciaMedica2 = new AsistenciaMedica(seguro, "Fiebre alta", "Alzira", "El paciente presenta cuadro alto de fiabre con deficultad para respirar. Se recetan antibioticos.", TipoAsistencia.Ambulatoria, sdfFecha.parse("27/02/2013"), sdfHora.parse("12:34:16"), new BigDecimal("81.14"));
        List<AsistenciaMedica> asistenciasMedicas = new ArrayList<>();
        asistenciasMedicas.add(asistenciaMedica1);
        asistenciasMedicas.add(asistenciaMedica2);
        seguro.setAsistenciasMedicas(asistenciasMedicas);

        sesion.persist(seguro);

        sesion.getTransaction().commit();
        sesion.close();
    }

}
