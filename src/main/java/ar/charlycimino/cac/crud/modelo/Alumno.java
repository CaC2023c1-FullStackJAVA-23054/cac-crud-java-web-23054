package ar.charlycimino.cac.crud.modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class Alumno {

    private int id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String mail;
    private String foto;

    public Alumno() {
    }

    public Alumno(int id) {
        setId(id);
    }

    public Alumno(String nombre, String apellido, String mail, String fechaNacimiento) {
        this(0, nombre, apellido, mail, fechaNacimiento, "");
    }

    public Alumno(int id, String nombre, String apellido, String mail, String fechaNacimiento) {
        this(id, nombre, apellido, mail, fechaNacimiento, "");
    }

    public Alumno(int id, String nombre, String apellido, String mail, String fechaNacimiento, String foto) {
        setId(id);
        setApellido(apellido);
        setNombre(nombre);
        setMail(mail);
        setFechaNacimiento(fechaNacimiento);
        setFoto(foto);
    }

    public void setId(int id) {
        if (id < 0) {
            throw new RuntimeException("Valor para ID inconsistente");
        }
        this.id = id;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new RuntimeException("No se ha provisto un apellido");
        }
        this.apellido = apellido.trim();
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new RuntimeException("No se ha provisto un nombre");
        }
        this.nombre = nombre.trim();
    }

    public void setMail(String mail) {
        if (mail == null || mail.trim().isEmpty()) {
            throw new RuntimeException("No se ha provisto un mail");
        }
        this.mail = mail.trim();
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        if (fechaNacimiento == null || fechaNacimiento.trim().isEmpty()) {
            throw new RuntimeException("No se ha provisto una fecha de nacimiento");
        }
        try {
            LocalDate posibleFecha = LocalDate.parse(fechaNacimiento.trim());
            if (posibleFecha.isAfter(LocalDate.now())) {
                throw new RuntimeException("La fecha de nacimiento proveída es posterior al día de hoy");
            }
            this.fechaNacimiento = posibleFecha;
        } catch (DateTimeParseException ex) {
            throw new RuntimeException("La fecha de nacimiento proveída no es válida", ex);
        }
    }

    public void setFoto(String foto) {
        if (foto == null || foto.trim().isEmpty()) {
            foto = "assets/no-face.jpg";
        }
        this.foto = foto.trim();
    }

    public int getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public String getFoto() {
        return foto;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", apellido=" + apellido + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", foto=" + foto + '}';
    }

}
