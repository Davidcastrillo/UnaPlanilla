/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanilla2.service;

import cr.ac.una.unaplanilla2.model.EmpleadoDto;
import cr.ac.una.unaplanilla2.model.Empleados;
import cr.ac.una.unaplanilla2.util.EntityManagerHelper;
import cr.ac.una.unaplanilla2.util.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class EmpleadoService {
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;
    
    public Respuesta getUsuario(String usuario, String clave) {
        try {      
            Query query = em.createNamedQuery("Empleados.findByUsuarioClave",Empleados.class);
            query.setParameter("Usuario", usuario);
            query.setParameter("Clave", clave);
            EmpleadoDto empleado = new EmpleadoDto((Empleados)query.getSingleResult());
            return new Respuesta(true, "", "", "Empleados", empleado);
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un usuario con las credenciales ingresadas.", "getUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el usuario.", "getUsuario NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + usuario + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario " + ex.getMessage());
        }
    }
    
    
    public Respuesta guardarEmpleado(EmpleadoDto empleadoDto){
        try {
            et = em.getTransaction();
            et.begin();
            Empleados empleado;
            if (empleadoDto.getId() != null && empleadoDto.getId() > 0){
                empleado = em.find(Empleados.class, empleadoDto.getId());
                if (empleado == null){
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el empleado a modificar.", "guardarEmpleado NoResultException");
                }
                empleado.actualizarEmpleado(empleadoDto);
                empleado = em.merge(empleado);
            } else{
                empleado = new Empleados(empleadoDto);
                em.persist(empleado);
            }            
            et.commit();
            return new Respuesta(true, "", "", "Empleados", new EmpleadoDto(empleado));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error guardando el empleado.", ex);
            return new Respuesta(false, "Error guardando el empleado.", "guardarEmpleado " + ex.getMessage());
            
          
          
        
    }
    }
    public Respuesta getEmpleado(Long id){
        try {
            Query query =em.createNamedQuery("Empleados.findByEmpId",Empleados.class);
            query.setParameter("Id", id);
            EmpleadoDto empleado = new EmpleadoDto((Empleados)query.getSingleResult());
            return new Respuesta(true, " ", "", "Empleados", empleado);    
        }  catch (NoResultException ex) {
            return new Respuesta(false, "No existe un empleado con el código ingresado.", "getEmpleado NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el empleado.", "getEmpleado NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el empleado.", "getEmpleado " + ex.getMessage());
        }
       
        
    }
    public Respuesta EliminarEmpleado(Long Id){
        try {
            et = em.getTransaction();
            et.begin();
            Empleados empleado;
            if (Id != null && Id > 0){
                empleado = em.find(Empleados.class, Id);
                if (empleado == null){
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el empleado a eliminar.", "eliminarEmpleado NoResultException");
                }
                em.remove(empleado);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar el empleado a eliminar.", "eliminarEmpleado NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar el empleado porque tiene relaciones con otros registros.", "eliminarEmpleado " + ex.getMessage());
            }
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, "Error eliminando el empleado.", ex);
            return new Respuesta(false, "Error eliminando el empleado.", "eliminarEmpleado " + ex.getMessage());
        }
    }
}
    

