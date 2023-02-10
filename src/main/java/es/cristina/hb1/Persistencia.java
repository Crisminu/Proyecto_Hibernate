package es.cristina.hb1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Date;
import java.util.Scanner;

public class Persistencia {
    protected SessionFactory sf;

    protected void configuracion() {
        final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure("hibernate1.cfg.xml").build();
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

    protected void crearSeguro(Scanner sc){
        Seguro1 seguro = new Seguro1();
        Session sesion = sf.openSession();
        sesion.beginTransaction();

        System.out.print("Introduzca el DNI: ");
        String nif = sc.next();
        seguro.setNif(nif);

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

        sesion.persist(seguro);
        sesion.getTransaction().commit();
        sesion.close();

    }
    protected void leer(int idSeguro){
        Session sesion = sf.openSession();
        Seguro1 seguro = new Seguro1(idSeguro);
        if (sesion.get(Seguro1.class, idSeguro) != null) {
            seguro = sesion.get(Seguro1.class, idSeguro);
        }
        System.out.println("Código de seguro: " + seguro.getIdSeguro());
        System.out.println("NIF: " + seguro.getNif());
        System.out.printf("Nombre y apellidos: %s %s %s \n", seguro.getNombre(), seguro.getApe1(), seguro.getApe2()  );
        System.out.println("Edad: " + seguro.getEdad());
        System.out.println("Número de hijos: " + seguro.getNumHijos());
        System.out.println("Fecha de creación: " + seguro.getFechaCreacion());

        sesion.close();
    }

    private void borrar(int idSeguro) {
        Session session = sf.openSession();
        Seguro1 seguro = new Seguro1();
        seguro.setIdSeguro(idSeguro);

        session.beginTransaction();

        session.remove(seguro);

        session.getTransaction().commit();
        session.close();
    }
    protected void actualizar(int idSeguro, Scanner sc){
        Seguro1 seguro = new Seguro1(idSeguro);
        Session sesion = sf.openSession();
        sesion.beginTransaction();

        System.out.print("Introduzca el NIF: ");
        String nif = sc.next();
        seguro.setNif(nif);

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
    public static void main(String[] args) {
        Persistencia persistencia = new Persistencia();
        persistencia.configuracion();
        Scanner sc = new Scanner(System.in);
        boolean acceso = true;
        int opcion;
        while(acceso){
            System.out.println("Hibernate. Ejercicio 1 - cfg.xml y hbm.xml");
            System.out.println("1. Crear seguro");
            System.out.println("2. Leer datos seguro con id");
            System.out.println("3. Actualizar datos de seguro");
            System.out.println("4. Borrar seguro");
            System.out.println("5. Salir");
            System.out.println("Introduce una opción:");
            opcion = sc.nextInt();


            switch (opcion){
                case 1 ->{
                    //Crear seguro
                    persistencia.crearSeguro(sc);
                    System.out.println("Seguro creado");
                }
                case 2 ->{
                    //Leer seguro
                    System.out.println("Introduce el código de seguro:");
                    int idSeguro = sc.nextInt();
                    persistencia.leer(idSeguro);
                }
                case 3 ->{
                    //Actualizar seguro
                    System.out.println("Introduce el código del seguro a actualizar:");
                    int idSeguro = sc.nextInt();
                    persistencia.actualizar(idSeguro,sc);
                    System.out.println("Seguro actualizado");
                }
                case 4 ->{
                    //Borrar seguro
                    System.out.println("Introduce el código del seguro a borrar:");
                    int idSeguro = sc.nextInt();
                    persistencia.borrar(idSeguro);
                    System.out.println("Seguro borrado");
                }
                case 5 ->{
                    //Salir
                    acceso = false;
                    System.out.println("Adiós");
                }
                default -> System.out.println("Introduce una opción válida");
            }
        }
        persistencia.exit();
    }
}
