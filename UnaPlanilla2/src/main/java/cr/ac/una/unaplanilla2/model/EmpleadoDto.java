/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanilla2.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author David
 */
public class EmpleadoDto {
    public SimpleStringProperty id;
    public SimpleStringProperty nombre;
    public SimpleStringProperty pApellido;
    public SimpleStringProperty sApellido;
    public SimpleStringProperty cedula;
    public SimpleObjectProperty<String> genero;
    public SimpleStringProperty correo;
    public SimpleBooleanProperty administrador;
    public SimpleStringProperty usuario;
    public SimpleStringProperty clave;
    public ObjectProperty<LocalDate> fIngreso;
    public ObjectProperty<LocalDate> fSalida;
    public SimpleBooleanProperty estado;
    private Boolean modificado;
    

    public EmpleadoDto() {
        this.id = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.pApellido = new SimpleStringProperty();
        this.sApellido = new SimpleStringProperty();
        this.cedula = new SimpleStringProperty();
        this.genero = new SimpleObjectProperty("M");
        this.correo = new SimpleStringProperty();
        this.administrador = new SimpleBooleanProperty(false);
        this.usuario = new SimpleStringProperty();
        this.clave = new SimpleStringProperty();
        this.fIngreso = new SimpleObjectProperty();
        this.fSalida = new SimpleObjectProperty();
        this.estado = new SimpleBooleanProperty(true);
        this.modificado = false;
    }
    public EmpleadoDto(Empleado empleados) {
        this();
        this.id.set(empleados.getId().toString());
        this.nombre.set(empleados.getNombre());
        this.pApellido.set(empleados.getPapellido());
        this.sApellido.set(empleados.getSapellido());
        this.cedula.set(empleados.getCedula());
        this.genero.set(empleados.getGenero());
        this.correo.set(empleados.getCorreo());
        this.administrador.setValue(empleados.getAdministrador().equalsIgnoreCase("S"));
        this.usuario.set(empleados.getUsuario());
        this.clave.set(empleados.getClave());
        this.fIngreso.set(LocalDate.from(empleados.getFingreso().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
         if (empleados.getFsalida()!= null) {
            this.fSalida.set(LocalDate.from(empleados.getFsalida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
         }
        this.estado.setValue(empleados.getEstado().equalsIgnoreCase("A"));
    } 


    public Long getId() {
          if(id.get()!=null && !id.get().isEmpty())
            return Long.valueOf(id.get());
        else
            return null;
    }

    public void setId(Long id) {
        this.id.setValue(id.toString());
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getpApellido() {
        return pApellido.get();
    }

    public void setpApellido(String pApellido) {
        this.pApellido.set(pApellido);
    }

    public String getsApellido() {
        return sApellido.get();
    }

    public void setsApellido(String sApellido) {
        this.sApellido.set(sApellido);
    }

    public String getCedula() {
        return cedula.get();
    }

    public void setCedula(String cedula) {
        this.cedula.set(cedula);
    }

    public String getGenero() {
        return genero.get();
    }

    public void setGenero(String genero) {
        this.genero.set(genero);
    }

    public String getCorreo() {
        return correo.get();
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }

    public String getAdministrador() {
        return administrador.getValue()?"S":"N";
    }

    public void setAdministrador(String administrador) {
        this.administrador.setValue(administrador.equalsIgnoreCase("S"));
    }

    public String getUsuario() {
        return usuario.get();
    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }

    public String getClave() {
        return clave.get();
    }

    public void setClave(String clave) {
        this.clave.set(clave);
    }

    public LocalDate getfIngreso() {
        return fIngreso.getValue();
    }

    public void setfIngreso(LocalDate fIngreso) {
        this.fIngreso.setValue(fIngreso);
    }

    public LocalDate getfSalida() {
        return fSalida.getValue();
    }

    public void setfSalida(LocalDate fSalida) {
        this.fSalida.setValue(fSalida);
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public String getEstado() {
        return estado.getValue()?"A":"I";
    }

    public void setEstado(String estado) {
        this.estado.setValue(estado.equalsIgnoreCase("A"));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmpleadoDto other = (EmpleadoDto) obj;
        return Objects.equals(this.id.get(), other.id.get());
    }
    
    
    
    
}
