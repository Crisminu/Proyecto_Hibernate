package es.cristina.hb2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Date;
import java.time.LocalDate;

public class Main {

    protected SessionFactory sf;

    protected void configuracion() {
        final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure("hibernate2.cfg.xml").build();
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

    protected void crearSeguro(){
        Seguro seguro = new Seguro();
        seguro.setNif("45689811A");
        seguro.setNombre("Miria");
        seguro.setApe1("Lopez");
        seguro.setApe2("Martínez");
        seguro.setEdad(30);
        seguro.setNumHijos(2);
        seguro.setFechaCreacion(Date.valueOf(LocalDate.now()));

        Session sesion = sf.openSession();
        sesion.beginTransaction();
        sesion.persist(seguro);
        sesion.getTransaction().commit();
        sesion.close();
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.configuracion();
        main.crearSeguro();
        main.exit();
    }

}
