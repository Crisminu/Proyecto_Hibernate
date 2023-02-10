package es.cristina.Introduccion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class PersistenciaSeguro {
    private Connection conn;

    public PersistenciaSeguro(Connection conn) {
        this.conn = conn;
    }


    //Insertará el seguro en la base de datos
    public void insertSeguro(Seguro seguro, Connection conn) throws IOException, SQLException {
        //insertamos una nuevo seguro

        String sqlFinal = "INSERT INTO seguro(nif, nombre, ape1, ape2, edad, fechaCreacion, numHijos) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sqlFinal);
        pstmt.setString(1,seguro.getNif());
        pstmt.setString(2,seguro.getNombre());
        pstmt.setString(3,seguro.getApe1());
        pstmt.setString(4,seguro.getApe2());
        pstmt.setInt(5,seguro.getEdad());
        pstmt.setDate(6,seguro.getFechaCreacion());
        pstmt.setInt(7,seguro.getNumHijos());
        pstmt.executeUpdate();
        pstmt.close();
    }
    public Seguro crearSeguro() throws SQLException, IOException {
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        int edad = 0;
        int numHijos = 0;
        int idSeguro = 0;
        String nif = null;
        String nombre = null;
        String ape1 = null;
        String ape2 = null;
        Date fechaCreacion = null;
        Seguro seguro;
        System.out.println("Insertar datos del seguro");
        System.out.println("Nombre");
        nombre = teclado.readLine();
        System.out.println("Apellido 1: ");
        ape1 = teclado.readLine();
        System.out.println("Apellido 2: ");
        ape2 = teclado.readLine();
        System.out.println("NIF: ");
        nif = teclado.readLine();
        System.out.println("Edad");
        edad = Integer.parseInt(teclado.readLine());
        System.out.println("Fecha de creación: ");
        fechaCreacion = Date.valueOf(LocalDate.now());
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formatter.format(fechaCreacion);
        System.out.println(fecha);

        System.out.println("Número de hijos: ");
        numHijos = Integer.parseInt(teclado.readLine());

        seguro = new Seguro(idSeguro, edad, numHijos, nif, nombre, ape1, ape2, fechaCreacion);

        return seguro;
    }
    //Obtendrá el seguro cuya clave primaria es idSeguro
    public Seguro getSeguro(int idSeguro, Connection conn) throws SQLException, ParseException {
        String sql = "SELECT * FROM Seguro WHERE idSeguro = ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idSeguro);
        ResultSet rs = pstmt.executeQuery();
        Seguro seguro;
        int edad = 0;
        int numHijos = 0;
        String nif = null;
        String nombre = null;
        String ape1 = null;
        String ape2 = null;
        Date fechaCreacion = null;
        while (rs.next()) {
            edad = rs.getInt("edad");
            numHijos = rs.getInt("numHijos");
            nif = rs.getString("nif");
            nombre = rs.getString("nombre");
            ape1 = rs.getString("ape1");
            ape2 = rs.getString("ape2");
            fechaCreacion = rs.getDate("fechaCreacion");
            System.out.println("Id del Seguro: " + idSeguro + "\n" + "Nombre: " + nombre + "\n" + "Primer apellido: " + ape1 + "\n" + "Segundo apellido: " + ape2 + "\n" + "Edad: " + edad + "\n" + "NIF: " + nif + "\n" + "Fecha de creación: " + fechaCreacion + "\n" +  "Número de hijos: " + numHijos);
        }
        seguro = new Seguro(idSeguro, edad, numHijos, nif, nombre, ape1, ape2, fechaCreacion);
        pstmt.close();
        return seguro;
    }
    //Actualizará en la base de datos el seguro
    public void updateSeguro(int idSeguro, Connection conn) throws IOException, SQLException {

            int edad = 0;
            int numHijos = 0;
            String nif = null;
            String nombre = null;
            String ape1 = null;
            String ape2 = null;
            Date fechaCreacion = null;
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            String sql = "UPDATE seguro SET nif=?, nombre=?, ape1=?, ape2=?, edad=?, fechaCreacion=?, numHijos=? WHERE idSeguro=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            System.out.println("Insertar datos del seguro");
            pstmt.setInt(8,idSeguro);
            //
            System.out.println("NIF");
            nif = teclado.readLine();
            pstmt.setString(1,nif);
            //
            System.out.println("Nombre: ");
            nombre = teclado.readLine();
            pstmt.setString(2,nombre);
            //
            System.out.println("Primer Apellido: ");
            ape1 = teclado.readLine();
            pstmt.setString(3,ape1);
            //
            System.out.println("Segundo Apellido: ");
            ape2 = teclado.readLine();
            pstmt.setString(4,ape2);
            //
            System.out.println("Edad: ");
            edad = Integer.parseInt(teclado.readLine());
            pstmt.setInt(5,edad);
            //
            System.out.println("Fecha de creación: ");
            fechaCreacion = Date.valueOf(LocalDate.now());
            System.out.println(fechaCreacion);
            pstmt.setDate(6,fechaCreacion);
            //
            System.out.println("Número de hijos");
            numHijos = Integer.parseInt(teclado.readLine());
            pstmt.setInt(7,numHijos);
            pstmt.executeUpdate();
            pstmt.close();

    }
    //Borrará de la base de datos el seguro cuya clave primaria sea idSeguro
    public void deleteSeguro(int idSeguro, Connection conn) throws SQLException {
        String sql = "DELETE FROM seguro WHERE idSeguro = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,idSeguro);
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("Seguro borrado");
    }
}
