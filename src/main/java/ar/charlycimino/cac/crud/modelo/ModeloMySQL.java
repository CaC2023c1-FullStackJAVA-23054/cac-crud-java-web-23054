package ar.charlycimino.cac.crud.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class ModeloMySQL implements Modelo {

    @Override
    public List<Alumno> getAlumnos() {
        try {
            ArrayList<Alumno> alumnos = new ArrayList<>();
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM alumnos;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String mail = rs.getString("mail");
                String fechaNac = rs.getString("fechaNac");
                String fotoBase64 = rs.getString("fotoBase64");
                alumnos.add(new Alumno(id, nombre, apellido, mail, fechaNac, fotoBase64));
            }

            return alumnos;
        } catch (SQLException ex) {
            throw new RuntimeException("Error al leer alumno de MySQL", ex);
        }

    }

    @Override
    public Alumno getAlumno(int id) {
        throw new UnsupportedOperationException("No soportado aún...");
    }

    @Override
    public int addAlumno(Alumno alumno) {
        try {
            int cantRegsAfectados;
            String sql = "INSERT INTO alumnos VALUES (null, ?, ?, ?, ?, ?)";
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getMail());
            ps.setString(4, alumno.getFechaNacimiento());
            ps.setString(5, alumno.getFoto());
            cantRegsAfectados = ps.executeUpdate();
            return cantRegsAfectados;
        } catch (SQLException ex) {
            throw new RuntimeException("Error al crear alumno en MySQL", ex);
        }
    }

    @Override
    public int updateAlumno(Alumno alumno) {
        throw new UnsupportedOperationException("No soportado aún...");
    }

    @Override
    public int removeAlumno(int id) {
        throw new UnsupportedOperationException("No soportado aún...");
    }

}
