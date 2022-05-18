/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanilla2.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author David
 */
@Entity
@Table(name = "PLAN_TIPOPLANILLAS")
@NamedQueries({
    @NamedQuery(name = "Tipoplanillas.findAll", query = "SELECT t FROM Tipoplanillas t"),
    @NamedQuery(name = "Tipoplanillas.findByTplaId", query = "SELECT t FROM Tipoplanillas t WHERE t.tplaId = :tplaId"),
    @NamedQuery(name = "Tipoplanillas.findByTplaCodigo", query = "SELECT t FROM Tipoplanillas t WHERE t.tplaCodigo = :tplaCodigo"),
    @NamedQuery(name = "Tipoplanillas.findByTplaDescripcion", query = "SELECT t FROM Tipoplanillas t WHERE t.tplaDescripcion = :tplaDescripcion"),
    @NamedQuery(name = "Tipoplanillas.findByTplaPlaxmes", query = "SELECT t FROM Tipoplanillas t WHERE t.tplaPlaxmes = :tplaPlaxmes"),
    @NamedQuery(name = "Tipoplanillas.findByTplaAnoultpla", query = "SELECT t FROM Tipoplanillas t WHERE t.tplaAnoultpla = :tplaAnoultpla"),
    @NamedQuery(name = "Tipoplanillas.findByTplaMesultpla", query = "SELECT t FROM Tipoplanillas t WHERE t.tplaMesultpla = :tplaMesultpla"),
    @NamedQuery(name = "Tipoplanillas.findByTplaNumultpla", query = "SELECT t FROM Tipoplanillas t WHERE t.tplaNumultpla = :tplaNumultpla"),
    @NamedQuery(name = "Tipoplanillas.findByTplaEstado", query = "SELECT t FROM Tipoplanillas t WHERE t.tplaEstado = :tplaEstado"),
    @NamedQuery(name = "Tipoplanillas.findByTplaVersion", query = "SELECT t FROM Tipoplanillas t WHERE t.tplaVersion = :tplaVersion")})
public class Tipoplanillas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "TPLA_ID")
    private BigDecimal tplaId;
    @Basic(optional = false)
    @Column(name = "TPLA_CODIGO")
    private String tplaCodigo;
    @Basic(optional = false)
    @Column(name = "TPLA_DESCRIPCION")
    private String tplaDescripcion;
    @Basic(optional = false)
    @Column(name = "TPLA_PLAXMES")
    private BigInteger tplaPlaxmes;
    @Column(name = "TPLA_ANOULTPLA")
    private BigInteger tplaAnoultpla;
    @Column(name = "TPLA_MESULTPLA")
    private BigInteger tplaMesultpla;
    @Column(name = "TPLA_NUMULTPLA")
    private BigInteger tplaNumultpla;
    @Basic(optional = false)
    @Column(name = "TPLA_ESTADO")
    private String tplaEstado;
    @Basic(optional = false)
    @Column(name = "TPLA_VERSION")
    private BigInteger tplaVersion;
    @JoinTable(name = "PLAN_EMPLEADOSPLANILLA", joinColumns = {
        @JoinColumn(name = "EXP_IDTPLA", referencedColumnName = "TPLA_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "EXP_IDEMP", referencedColumnName = "EMP_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Empleados> empleadosList;

    public Tipoplanillas() {
    }

    public Tipoplanillas(BigDecimal tplaId) {
        this.tplaId = tplaId;
    }

    public Tipoplanillas(BigDecimal tplaId, String tplaCodigo, String tplaDescripcion, BigInteger tplaPlaxmes, String tplaEstado, BigInteger tplaVersion) {
        this.tplaId = tplaId;
        this.tplaCodigo = tplaCodigo;
        this.tplaDescripcion = tplaDescripcion;
        this.tplaPlaxmes = tplaPlaxmes;
        this.tplaEstado = tplaEstado;
        this.tplaVersion = tplaVersion;
    }

    public BigDecimal getTplaId() {
        return tplaId;
    }

    public void setTplaId(BigDecimal tplaId) {
        this.tplaId = tplaId;
    }

    public String getTplaCodigo() {
        return tplaCodigo;
    }

    public void setTplaCodigo(String tplaCodigo) {
        this.tplaCodigo = tplaCodigo;
    }

    public String getTplaDescripcion() {
        return tplaDescripcion;
    }

    public void setTplaDescripcion(String tplaDescripcion) {
        this.tplaDescripcion = tplaDescripcion;
    }

    public BigInteger getTplaPlaxmes() {
        return tplaPlaxmes;
    }

    public void setTplaPlaxmes(BigInteger tplaPlaxmes) {
        this.tplaPlaxmes = tplaPlaxmes;
    }

    public BigInteger getTplaAnoultpla() {
        return tplaAnoultpla;
    }

    public void setTplaAnoultpla(BigInteger tplaAnoultpla) {
        this.tplaAnoultpla = tplaAnoultpla;
    }

    public BigInteger getTplaMesultpla() {
        return tplaMesultpla;
    }

    public void setTplaMesultpla(BigInteger tplaMesultpla) {
        this.tplaMesultpla = tplaMesultpla;
    }

    public BigInteger getTplaNumultpla() {
        return tplaNumultpla;
    }

    public void setTplaNumultpla(BigInteger tplaNumultpla) {
        this.tplaNumultpla = tplaNumultpla;
    }

    public String getTplaEstado() {
        return tplaEstado;
    }

    public void setTplaEstado(String tplaEstado) {
        this.tplaEstado = tplaEstado;
    }

    public BigInteger getTplaVersion() {
        return tplaVersion;
    }

    public void setTplaVersion(BigInteger tplaVersion) {
        this.tplaVersion = tplaVersion;
    }

    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tplaId != null ? tplaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoplanillas)) {
            return false;
        }
        Tipoplanillas other = (Tipoplanillas) object;
        if ((this.tplaId == null && other.tplaId != null) || (this.tplaId != null && !this.tplaId.equals(other.tplaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.unaplanilla2.model.Tipoplanillas[ tplaId=" + tplaId + " ]";
    }
    
}
