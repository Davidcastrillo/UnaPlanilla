/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanilla2.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David
 */
@Entity
@Table(name = "PLA_EMPLEADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlaEmpleados.findAll", query = "SELECT p FROM PlaEmpleados p"),
    @NamedQuery(name = "PlaEmpleados.findById", query = "SELECT p FROM PlaEmpleados p WHERE p.id = :id"),
    @NamedQuery(name = "PlaEmpleados.findByEmpNombre", query = "SELECT p FROM PlaEmpleados p WHERE p.empNombre = :empNombre"),
    @NamedQuery(name = "PlaEmpleados.findByEmpApellidos", query = "SELECT p FROM PlaEmpleados p WHERE p.empApellidos = :empApellidos"),
    @NamedQuery(name = "PlaEmpleados.findByEmpFechaing", query = "SELECT p FROM PlaEmpleados p WHERE p.empFechaing = :empFechaing"),
    @NamedQuery(name = "PlaEmpleados.findByEmpEstado", query = "SELECT p FROM PlaEmpleados p WHERE p.empEstado = :empEstado"),
    @NamedQuery(name = "PlaEmpleados.findByEmpVersion", query = "SELECT p FROM PlaEmpleados p WHERE p.empVersion = :empVersion")})
public class PlaEmpleados implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "EMP_NOMBRE")
    private String empNombre;
    @Basic(optional = false)
    @Column(name = "EMP_APELLIDOS")
    private String empApellidos;
    @Basic(optional = false)
    @Column(name = "EMP_FECHAING")
    @Temporal(TemporalType.TIMESTAMP)
    private Date empFechaing;
    @Basic(optional = false)
    @Column(name = "EMP_ESTADO")
    private String empEstado;
    @Basic(optional = false)
    @Column(name = "EMP_VERSION")
    private BigInteger empVersion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salEmpid")
    private List<PlaHissalarios> plaHissalariosList;

    public PlaEmpleados() {
    }

    public PlaEmpleados(BigDecimal id) {
        this.id = id;
    }

    public PlaEmpleados(BigDecimal id, String empNombre, String empApellidos, Date empFechaing, String empEstado, BigInteger empVersion) {
        this.id = id;
        this.empNombre = empNombre;
        this.empApellidos = empApellidos;
        this.empFechaing = empFechaing;
        this.empEstado = empEstado;
        this.empVersion = empVersion;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getEmpNombre() {
        return empNombre;
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    public String getEmpApellidos() {
        return empApellidos;
    }

    public void setEmpApellidos(String empApellidos) {
        this.empApellidos = empApellidos;
    }

    public Date getEmpFechaing() {
        return empFechaing;
    }

    public void setEmpFechaing(Date empFechaing) {
        this.empFechaing = empFechaing;
    }

    public String getEmpEstado() {
        return empEstado;
    }

    public void setEmpEstado(String empEstado) {
        this.empEstado = empEstado;
    }

    public BigInteger getEmpVersion() {
        return empVersion;
    }

    public void setEmpVersion(BigInteger empVersion) {
        this.empVersion = empVersion;
    }

    @XmlTransient
    public List<PlaHissalarios> getPlaHissalariosList() {
        return plaHissalariosList;
    }

    public void setPlaHissalariosList(List<PlaHissalarios> plaHissalariosList) {
        this.plaHissalariosList = plaHissalariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaEmpleados)) {
            return false;
        }
        PlaEmpleados other = (PlaEmpleados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.unaplanilla2.model.PlaEmpleados[ id=" + id + " ]";
    }
    
}
