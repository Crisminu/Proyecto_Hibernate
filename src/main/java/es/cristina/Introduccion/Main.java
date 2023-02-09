package es.cristina.hib1jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/seguros", "root", "mibadat27")) {
            boolean acceso = true;
            int opcion = 0;
            Seguro seguro = null;
            PersistenciaSeguro ps = new PersistenciaSeguro(conn);

            //Crear un objeto static Connection con valor nulo
            System.out.println("Conectado a la base de datos");
            //Menú para los distintos métodos
            while (acceso) {
                System.out.println("Seleccione una opción");
                System.out.println("1. Crear Seguro");
                System.out.println("2. Leer Seguro");
                System.out.println("3. Actualizar Seguro");
                System.out.println("4. Borrar Seguro");
                System.out.println("5. Salir");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1 -> {
                        seguro = ps.crearSeguro();
                        ps.insertSeguro(seguro, conn);
                    }
                    case 2 -> {
                        System.out.println("Introduce el id del seguro a consultar: ");
                        int idSeguro = sc.nextInt();
                        ps.getSeguro(idSeguro, conn);
                    }
                    case 3 -> {
                        System.out.println("Indica el id del seguro a modificar: ");
                        int idSeguro = sc.nextInt();
                        ps.updateSeguro(idSeguro, conn);
                    }
                    case 4 -> {
                        System.out.println("Introduce el id del seguro a borrar: ");
                        int idSeguro = sc.nextInt();
                        ps.deleteSeguro(idSeguro, conn);
                    }
                    case 5 -> acceso = false;
                    default -> System.out.println("Inserte una opción valida");
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

