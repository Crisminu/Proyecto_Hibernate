package es.cristina.hib7;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Persistencia {
    /*Lanza una consulta que nos retorne todos los seguros que hay en la base de datos.Haz que se muestren en la pantalla.
Lanza una consulta que nos retorne solo las columnas NIF y Nombre de todos los seguros que hay en la base de datos.Haz que se muestren en pantalla.
Lanza una consulta que nos retorne sólo la columna NIF de todos los seguros que hay en la base de datos .Haz que se muestren en pantalla.
Lanza una consulta que nos retorne sólo el NIF para el seguro con nombre “Marcos Tortosa Oltra”. Usa el método uniqueResult() y 3 parámetros con nombre para el nombre y los apellidos.
Crea una consulta con nombre llamada ”grandesGastos”. Esta consulta retornará aquellas AsistenciaMedica con importe mayor o igual a 10.000 €.
Ejecuta la consulta con nombre ”grandesGastos” y muestra el idAsistenciaMedica en pantalla.
Lanza una consulta que retorne el idAsistenciaMedica de todas las asistencias médicas cuyo saldo esté entre 2.000 y 5.000 euros.Usa parámetros por posición para los valores 2000 y 5000.Haz que se muestren en pantalla.
    * */
    protected SessionFactory sf;
    protected void configuracion() {
        final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure("hibernate7.cfg.xml").build();
        try {
            sf = new MetadataSources(registro).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registro);
        }
    }
    public void queryTodosSeguros(){
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Seguro";
            Query query = session.createQuery(hql);
            List<Seguro> seguros = query.list();
            for (Seguro seguro : seguros) {
                System.out.println("Id: " + seguro.getIdSeguro());
                System.out.println("Nombre: " + seguro.getNombre());
                System.out.println("Primer Apellido: " + seguro.getApe1());
                System.out.println("Segundo Apellido: " + seguro.getApe2());
                System.out.println("Edad: " + seguro.getEdad());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
    public void querySeguroNifyNombre(){
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT nif, nombre FROM Seguro";
            Query query = session.createQuery(hql);
            List<Object[]> seguros = query.list();
            for (Object[] seguro : seguros) {
                System.out.println("NIF: " + seguro[0]);
                System.out.println("Nombre: " + seguro[1]);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
    public void querySeguroNif(){
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT nif FROM Seguro";
            Query query = session.createQuery(hql);
            List<NIF> nifs = query.list();
            for (NIF nif : nifs) {
                System.out.println("NIF: " + nif.getNif());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
    public void queryMarcosNif() {
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT nif FROM Seguro WHERE nombre = :nombre AND ape1 = :ape1 AND ape2 = :ape2";

            Query query = session.createQuery(hql);
            query.setParameter("nombre", "Marcos");
            query.setParameter("ape1", "Tortosa");
            query.setParameter("ape2", "Oltra");

            NIF nif = (NIF) query.uniqueResult();
            System.out.println(nif);
            {
        } tx.commit();
         } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void grandesGastos(){
        //Crea una consulta con nombre llamada ”grandesGastos”. Esta consulta retornará aquellas AsistenciaMedica con importe mayor o igual a 10.000 €.
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM AsistenciaMedica WHERE importe>10000";
            Query query = session.createQuery(hql);
            List<AsistenciaMedica> asistencias = query.list();
            for (AsistenciaMedica asistenciaMedica : asistencias) {
                System.out.println("Id: " + asistenciaMedica.getIdAsistenciaMedica()+" Importe: " +asistenciaMedica.getImporte());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
    public void importeminmax(){
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT idAsistenciaMedica FROM AsistenciaMedica WHERE importe BETWEEN ?1 and ?2";

            Query query = session.createQuery(hql);
            query.setParameter(1, 2000);
            query.setParameter(2, 5000);

            List<Integer> result = query.list();

            for (Integer id : result) {
                System.out.println("idAsistenciaMedica: " + id);
            }
             tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void sumaTodosImportes(){
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT sum(importe) FROM AsistenciaMedica";

            Query query = session.createQuery(hql);
            BigDecimal suma = (BigDecimal) query.uniqueResult();
            System.out.println(suma);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    private void mediaImportes() {
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT avg(importe) FROM AsistenciaMedica";

            Query query = session.createQuery(hql);
            Double suma = (Double) query.uniqueResult();
            System.out.println(suma);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void cantidadSeguros(){
        //Lanza una consulta que calcule cuantos seguros hay. Haz que se muestren en pantalla.
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT COUNT(*) FROM Seguro";

            Query query = session.createQuery(hql);
            long cantidad = (long) query.uniqueResult();
            System.out.println(cantidad);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    private void asisPorSeguro() {
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT s.idSeguro, s.nombre, SIZE(s.asistenciasMedicas) FROM Seguro s");
            List<Object[]> result = query.list();
            for (Object[] row : result) {
                System.out.println("ID del seguro: " + row[0] + ", Nombre del seguro: " + row[1] + ", Cantidad de asistencias médicas: " + row[2]);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    private void alergias(){
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT enfermedades.nombreAlergia FROM Seguro");
            List<Object> alergias = query.list();
            for (Object alergia : alergias) {
                System.out.println("Alergia: " + alergia);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    private void mostrarAsistencia(){
        //Lanza una consulta que retorne todos los seguros. Haz que se muestre en pantalla el idAsistenciaMedica de cada asistencia médica (No debes lanzar otra consulta).
        // Comprueba cuántas consultas lanza Hibernate internamente.
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT s FROM Seguro s");
            List<Seguro> seguros = query.list();
            for (Seguro seguro: seguros) {
                for(AsistenciaMedica asistenciaMedica: seguro.getAsistenciasMedicas()){
                    System.out.println(asistenciaMedica.getIdAsistenciaMedica());
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    private void mostrarAsistenciaOptimizado(){
        //Repite la consulta anterior pero debes optimizarlas para minimizar el número de consultas que lanza Hibernate.
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT s FROM Seguro s LEFT JOIN FETCH s.asistenciasMedicas");
            List<Seguro> seguros = query.list();
            for (Seguro seguro: seguros) {
                for(AsistenciaMedica asistenciaMedica: seguro.getAsistenciasMedicas()){
                    System.out.println(asistenciaMedica.getIdAsistenciaMedica());
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    private void nativa(){
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createNativeQuery("SELECT * FROM Seguro");
            List<Object[]> seguros = query.list();
            for (Object[] seguro : seguros) {
                System.out.println("Id: " + seguro[0]);
                System.out.println("Nombre: " + seguro[1]);
                System.out.println("Primer Apellido: " + seguro[2]);
                System.out.println("Segundo Apellido: " + seguro[3]);
                System.out.println("Edad: " + seguro[4]);
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    protected void exit() {
        sf.close();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Persistencia persistencia = new Persistencia();
        persistencia.configuracion();
        int opcion;
        boolean acceso = true;
        while(acceso){
            System.out.println("Hibernate 6. Anotaciones");
            System.out.println("1. Consulta de todos los seguros");
            System.out.println("2. Consulta seguros por NIF y Nombre ");
            System.out.println("3. Consulta seguros por NIF");
            System.out.println("4. Consulta Marcos NIF");
            System.out.println("5. Consulta gastos > 10000");
            System.out.println("6. Consulta gastos entre 5000 y 2000");
            System.out.println("7. Suma de todos los gastos");
            System.out.println("8. Media de todos los gastos");
            System.out.println("9. Cantidad de seguros");
            System.out.println("10. Consulta cantidad de asistencias medicas para cada seguro");
            System.out.println("11. Consulta alergias");
            System.out.println("12. Consultar id de asistencias medicas");
            System.out.println("13. Consultar id de asistencias medicas optimizado");
            System.out.println("14. Lista nativa de seguro");
            System.out.println("15. Salir");
            System.out.print("Introduzca una opción: ");
            opcion = sc.nextInt();
            switch (opcion){
                case 1 -> {
                    persistencia.queryTodosSeguros();
                }
                case 2 -> {
                    persistencia.querySeguroNifyNombre();
                }
                case 3 -> {
                    persistencia.querySeguroNif();
                }
                case 4 -> {
                    persistencia.queryMarcosNif();
                }
                case 5 -> {
                    persistencia.grandesGastos();
                }
                case 6 -> {
                    persistencia.importeminmax();
                }
                case 7 -> {
                    persistencia.sumaTodosImportes();
                }
                case 8 -> {
                    persistencia.mediaImportes();
                }
                case 9 -> {
                    persistencia.cantidadSeguros();
                }
                case 10 ->{
                    persistencia.asisPorSeguro();
                }
                case 11 -> {
                    persistencia.alergias();
                }
                case 12 -> {
                    persistencia.mostrarAsistencia();
                }
                case 13 -> {
                    persistencia.mostrarAsistenciaOptimizado();
                }
                case 14 -> {
                    persistencia.nativa();
                }
                case 15 -> {
                    acceso = false;
                    System.out.println("Adiós");
                }
                default -> System.out.println("Introduzca una opción válida");
            }
        }


        persistencia.exit();
    }


}
