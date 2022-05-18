/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanilla2.service;

import cr.ac.una.unaplanilla2.model.EmpleadoDto;
import cr.ac.una.unaplanilla2.model.Empleados;
import cr.ac.una.unaplanilla2.util.EntityManagerHelper;
import cr.ac.una.unaplanilla2.util.Respuesta;
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
            query.setParameter("usuario", usuario);
            query.setParameter("clave", clave);
            EmpleadoDto empleado = new EmpleadoDto((Empleados)query.getSingleResult());
            return new Respuesta(true, "", "", "Empleado", empleado);
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
    
}
