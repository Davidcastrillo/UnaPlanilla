/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanilla2.service;

import cr.ac.una.unaplanilla2.model.EmpleadoDto;
import cr.ac.una.unaplanilla2.model.Empleado;
import cr.ac.una.unaplanilla2.model.TipoplanillaDto;
import cr.ac.una.unaplanilla2.model.TipoPlanillas;
import cr.ac.una.unaplanilla2.util.EntityManagerHelper;
import cr.ac.una.unaplanilla2.util.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
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
public class TipoPlanillaService {
    
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public Respuesta getTipoPlanilla(Long id) {
        try {
            Query qryTipoPlanilla = em.createNamedQuery("TipoPlanillas.findByTplaId", TipoPlanillas.class);
            qryTipoPlanilla.setParameter("Id", id);

            TipoPlanillas tipoPlanilla = (TipoPlanillas) qryTipoPlanilla.getSingleResult();
            TipoplanillaDto tipoPlanillaDto = new TipoplanillaDto(tipoPlanilla);
            for (Empleado emp : tipoPlanilla.getEmpleadosList()) {
                tipoPlanillaDto.getEmpleados().add(new EmpleadoDto(emp));
            }
            return new Respuesta(true, "", "", "TipoPlanilla", tipoPlanillaDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un tipo de planilla con el código ingresado.", "getTipoPlanilla NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el tipo de planilla.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el tipo de planilla.", "getTipoPlanilla NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el tipo de planilla.", "getTipoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta guardarTipoPlanilla(TipoplanillaDto tipoPlanillaDto) {
        try {
            et = em.getTransaction();
            et.begin();
            TipoPlanillas tipoPlanilla;
            if (tipoPlanillaDto.getId() != null && tipoPlanillaDto.getId() > 0) {
                tipoPlanilla = em.find(TipoPlanillas.class, tipoPlanillaDto.getId());
                if (tipoPlanilla == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró el tipo de planilla a modificar.", "guardarTipoPlanilla NoResultException");
                }
                tipoPlanilla.actualizarTipoPlanilla(tipoPlanillaDto);
                for (EmpleadoDto emp : tipoPlanillaDto.getEmpleadosEliminados()) {
                    tipoPlanilla.getEmpleadosList().remove(new Empleado(emp.getId()));
                }
                if (!tipoPlanillaDto.getEmpleados().isEmpty()) {
                    for (EmpleadoDto emp : tipoPlanillaDto.getEmpleados()) {
                        if (emp.getModificado()) {
                            Empleado empleado = em.find(Empleado.class, emp.getId());
                            empleado.getTipoplanillasList().add(tipoPlanilla); // al empleado se le asigna una planilla
                            tipoPlanilla.getEmpleadosList().add(empleado); // y a la planilla se le agrega un empleado 
                        }
                    }
                }
                tipoPlanilla = em.merge(tipoPlanilla);
            } else {
                tipoPlanilla = new TipoPlanillas(tipoPlanillaDto);
                em.persist(tipoPlanilla);
            }
            et.commit();
            tipoPlanillaDto = new TipoplanillaDto(tipoPlanilla);
            for (Empleado emp : tipoPlanilla.getEmpleadosList()) {
                tipoPlanillaDto.getEmpleados().add(new EmpleadoDto(emp));
            }
            return new Respuesta(true, "", "", "TipoPlanilla", tipoPlanillaDto);
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el tipo de planilla.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el tipo de planilla.", "guardarTipoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta getTipoplanillas(String codigo, String descripcion, String planillasMes, String id, String cedula) {
        try {
            if ("%%".equals(id) && "%%".equals(cedula)) {
                Query qryTipoPlanilla = em.createNamedQuery("TipoPlanillas.findbythings", TipoPlanillas.class);
                qryTipoPlanilla.setParameter("Codigo", codigo);
                qryTipoPlanilla.setParameter("Descripcion", descripcion);
                qryTipoPlanilla.setParameter("Plaxmes", planillasMes);
                List<TipoPlanillas> planillas = (List<TipoPlanillas>) qryTipoPlanilla.getResultList();
                List<TipoplanillaDto> planillasDto = new ArrayList<>();
                for (TipoPlanillas pla : planillas) {
                    planillasDto.add(new TipoplanillaDto(pla));
                }
                return new Respuesta(true, "", "", "TipoPlanillas", planillasDto);

            } else {// busqueda por los empleados vinculados o insertados en las tablas 

                Query qryTipoPlanilla = em.createNamedQuery("TipoPlanillas.findbyEmp", TipoPlanillas.class);
                qryTipoPlanilla.setParameter("Codigo", codigo);
                qryTipoPlanilla.setParameter("Id", id);
                qryTipoPlanilla.setParameter("Cedula", cedula);
                List<TipoPlanillas> planillas = (List<TipoPlanillas>) qryTipoPlanilla.getResultList();
                List<TipoplanillaDto> planillasDto = new ArrayList<>();
                for (TipoPlanillas pla : planillas) {
                    planillasDto.add(new TipoplanillaDto(pla));
                }
                return new Respuesta(true, "", "", "TipoPlanillas", planillasDto);
            }

        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un tipo de planilla con el código ingresado.", "getTipoPlanilla NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el tipo de planilla.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el tipo de planilla.", "getTipoPlanilla NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el tipo de planilla.", "getTipoPlanilla " + ex.getMessage());
        }

    }

    public Respuesta eliminarTipoPlanilla(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            TipoPlanillas tipoPlanilla;
            if (id != null && id > 0) {
                tipoPlanilla = em.find(TipoPlanillas.class, id);
                if (tipoPlanilla == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el tipo de planilla a eliminar.", "eliminarTipoPlanilla NoResultException");
                }
                em.remove(tipoPlanilla);
            } else {
                et.rollback();
                return new Respuesta(false, "Debe cargar el tipo de planilla a eliminar.", "eliminarTipoPlanilla NoResultException");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar el tipo de planilla porque tiene relaciones con otros registros.", "eliminarTipoPlanilla " + ex.getMessage());
            }
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el tipo de planilla.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el tipo de planilla.", "eliminarTipoPlanilla " + ex.getMessage());
        }
    }
    
}
