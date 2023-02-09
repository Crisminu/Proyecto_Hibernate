package es.cristina.hb1;

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

    protected void crearSeguro(){
        Seguro1 seguro1 = new Seguro1();
        seguro1.setNif("11111345A");
        seguro1.setNombre("Eutiquio");
        seguro1.setApe1("Piernavieja");
        seguro1.setApe2("Campohermoso");
        seguro1.setEdad(80);
        seguro1.setNumHijos(3);
        seguro1.setFechaCreacion(Date.valueOf(LocalDate.now()));

        Session sesion = sf.openSession();
        sesion.beginTransaction();
        sesion.merge(seguro1);
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
