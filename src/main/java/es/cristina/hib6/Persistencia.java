package es.cristina.hib5;

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
        final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure("hibernate5.cfg.xml").build();
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
            System.out.println("Base de datos 'seguros'");
            System.out.println("1. Añadir seguro");
            System.out.println("2. Listar seguros");
            System.out.println("3. Actualizar datos de seguro");
            System.out.println("4. Borrar seguro");
            System.out.println("5. Datos de ejemplo");
            System.out.println("6. Salir");
            System.out.print("Introduzca una opción: ");
            opcion = sc.nextInt();
            switch (opcion){
                case 1 -> {
                   persistencia.crearSeguro(sc);
                   System.out.println("Seguro creado");
                }
                case 2 -> {
                    System.out.println("Introduce el código de seguro:");
                    int idSeguro = sc.nextInt();
                    persistencia.leerSeguro(idSeguro);
                }
                case 3 -> {
                    System.out.println("Introduce el código del seguro a actualizar:");
                    int idSeguro = sc.nextInt();
                    persistencia.actualizarSeguro(idSeguro,sc);
                    System.out.println("Seguro actualizado");
                }
                case 4 -> {
                    System.out.print("Introduzca código de seguro: ");
                    int idSeguro = sc.nextInt();
                    persistencia.borrarSeguro(idSeguro);
                    System.out.println("Seguro borrado");
                }
                case 5 -> {
                    persistencia.datosEjemplo();
                }
                case 6 -> {
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
    protected void crearSeguro(Scanner sc){
        Seguro seguro = new Seguro();
        Session sesion = sf.openSession();
        sesion.beginTransaction();

        System.out.print("Introduzca el DNI: ");
        String nif = sc.next();
        //seguro.setNif(nif);

        System.out.print("Introduzca el nombre: ");
        String nombre = sc.next();
        seguro.setNombre(nombre);

        System.out.print("Introduzca primer apellido: ");
        String ape1 = sc.next();
        seguro.setApe1(ape1);

        System.out.print("Introduzca segundo apellido: ");
        String ape2 = sc.next();
        seguro.setApe2(ape2);

        System.out.print("Introduzca la edad: ");
        int edad = sc.nextInt();
        seguro.setEdad(edad);

        System.out.print("Introduzca el número de hijos: ");
        int numHijos = sc.nextInt();
        seguro.setNumHijos(numHijos);

        Date dateTime = new Date(System.currentTimeMillis());
        seguro.setFechaCreacion(dateTime);

        generateAsistencia(seguro, sc);
        sesion.persist(seguro);
        sesion.getTransaction().commit();
        sesion.close();

    }
    private void generateAsistencia(Seguro seguro, Scanner sc){
        List<AsistenciaMedica> asistenciasMedicas = new ArrayList<>();
        boolean acceso = true;
        while (acceso) {
            System.out.println("1. Añadir una asistencia médica");
            System.out.println("2. Salir");
            System.out.print("Elija una opcción: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    AsistenciaMedica asistenciaMedica = new AsistenciaMedica();
                    System.out.print("Introduzca descripción: ");
                    String descripcion = sc.next();
                    asistenciaMedica.setBreveDescripcion(descripcion);

                    System.out.print("Introduzca lugar de la asistencia: ");
                    String lugar = sc.next();
                    asistenciaMedica.setLugar(lugar);

                    asistenciaMedica.setSeguro(seguro);
                    asistenciasMedicas.add(asistenciaMedica);
                }
                case 2 -> acceso = false;
                default -> System.out.println("Introduzca una opción válida");
            }
            seguro.setAsistenciasMedicas(asistenciasMedicas);
        }
    }
    private void actualizarSeguro(int idSeguro, Scanner sc) {
        Seguro seguro = new Seguro(idSeguro);
        Session sesion = sf.openSession();
        sesion.beginTransaction();

        System.out.print("Introduzca el NIF: ");
        String nif = sc.next();
        //seguro.setNif(nif);

        System.out.print("Introduzca el nombre de pila: ");
        String nombre = sc.next();
        seguro.setNombre(nombre);

        System.out.print("Introduzca primer apellido: ");
        String ape1 = sc.next();
        seguro.setApe1(ape1);

        System.out.print("Introduzca segundo apellido: ");
        String ape2 = sc.next();
        seguro.setApe2(ape2);

        System.out.print("Introduzca la edad: ");
        int edad = sc.nextInt();
        seguro.setEdad(edad);

        System.out.print("Introduzca el número de hijos: ");
        int numHijos = sc.nextInt();
        seguro.setNumHijos(numHijos);


        sesion.merge(seguro);
        sesion.getTransaction().commit();
        sesion.close();
    }
    private void borrarSeguro(int idSeguro) {
        Session sesion = sf.openSession();
        Seguro seguro = new Seguro();
        seguro.setIdSeguro(idSeguro);
        sesion.beginTransaction();

        sesion.remove(seguro);

        sesion.getTransaction().commit();
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
