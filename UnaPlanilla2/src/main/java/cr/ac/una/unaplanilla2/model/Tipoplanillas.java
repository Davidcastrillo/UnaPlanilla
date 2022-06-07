/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanilla2.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "PLAN_TIPOPLANILLAS",schema = "una")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoplanillas.findAll", query = "SELECT t FROM Tipoplanillas t"),
    @NamedQuery(name = "Tipoplanillas.findByTplaId", query = "SELECT t FROM Tipoplanillas t WHERE t.Id = :Id"),
    @NamedQuery(name = "Tipoplanillas.findbythings", query = "SELECT t FROM Tipoplanillas t WHERE UPPER(t.Codigo) like :Codigo and UPPER(t.Descripcion) like :Descripcion and UPPER(t.Plaxmes) like :Plaxmes ", hints = @QueryHint(name = "eclipselink.refresh", value = "true")),
   // @NamedQuery(name = "Tipoplanillas.findBylosempleados", query = "SELECT t FROM Tipoplanillas t Join t.Empleados e WHERE UPPER(t.Codigo) like :Codigo and  UPPER(e.Cedula) like :Cedula and UPPER(e.Id) like :Id"),
   /* @NamedQuery(name = "Tipoplanillas.findByTplaCodigo", query = "SELECT t FROM Tipoplanillas t WHERE t.Codigo = :Codigo"),
    @NamedQuery(name = "Tipoplanillas.findByTplaDescripcion", query = "SELECT t FROM Tipoplanillas t WHERE t.Descripcion = :Descripcion"),
    @NamedQuery(name = "Tipoplanillas.findByTplaPlaxmes", query = "SELECT t FROM Tipoplanillas t WHERE t.Plaxmes = :Plaxmes"),
    @NamedQuery(name = "Tipoplanillas.findByTplaAnoultpla", query = "SELECT t FROM Tipoplanillas t WHERE t.Anoultpla = :Anoultpla"),
    @NamedQuery(name = "Tipoplanillas.findByTplaMesultpla", query = "SELECT t FROM Tipoplanillas t WHERE t.Mesultpla = :Mesultpla"),
    @NamedQuery(name = "Tipoplanillas.findByTplaNumultpla", query = "SELECT t FROM Tipoplanillas t WHERE t.Numultpla = :Numultpla"),
    @NamedQuery(name = "Tipoplanillas.findByTplaEstado", query = "SELECT t FROM Tipoplanillas t WHERE t.Estado = :Estado"),
    @NamedQuery(name = "Tipoplanillas.findByTplaVersion", query = "SELECT t FROM Tipoplanillas t WHERE t.Version = :Version")*/})
public class Tipoplanillas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PLAM_TIPOPLANILLA_TPLA_ID_GENERATOR", sequenceName = "PLAN_TIPOPLANILLAS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAM_TIPOPLANILLA_TPLA_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "TPLA_ID")
    private Long Id;
    @Basic(optional = false)
    @Column(name = "TPLA_CODIGO")
    private String Codigo;
    @Basic(optional = false)
    @Column(name = "TPLA_DESCRIPCION")
    private String Descripcion;
    @Basic(optional = false)
    @Column(name = "TPLA_PLAXMES")
    private Integer Plaxmes;
    @Column(name = "TPLA_ANOULTPLA")
    private Integer Anoultpla;
    @Column(name = "TPLA_MESULTPLA")
    private Integer Mesultpla;
    @Column(name = "TPLA_NUMULTPLA")
    private Integer Numultpla;
    @Basic(optional = false)
    @Column(name = "TPLA_ESTADO")
    private String Estado;
    @Basic(optional = false)
    @Column(name = "TPLA_VERSION")
    private Integer Version;
    @JoinTable(name = "PLAN_EMPLEADOSPLANILLA", joinColumns = {
        @JoinColumn(name = "EXP_IDTPLA", referencedColumnName = "TPLA_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "EXP_IDEMP", referencedColumnName = "EMP_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Empleados> empleadosList;

    public Tipoplanillas() {
    }

    public Tipoplanillas(Long tplaId) {
        this.Id = tplaId;
    }

    public Tipoplanillas(Long tplaId, String tplaCodigo, String tplaDescripcion, Integer tplaPlaxmes, String tplaEstado, Integer tplaVersion) {
        this.Id = tplaId;
        this.Codigo = tplaCodigo;
        this.Descripcion = tplaDescripcion;
        this.Plaxmes = tplaPlaxmes;
        this.Estado = tplaEstado;
        this.Version = tplaVersion;
    }
    
    public Tipoplanillas(TipoplanillaDto tipoPlanillaDto) {
        this.Id = tipoPlanillaDto.getId();
        actualizarTipoPlanilla(tipoPlanillaDto);
    }

    public void actualizarTipoPlanilla(TipoplanillaDto tipoPlanillaDto) {
        this.Codigo = tipoPlanillaDto.getCodigo();
        this.Descripcion = tipoPlanillaDto.getDescripcion();
        this.Plaxmes = tipoPlanillaDto.getPlanillasPorMes();
        this.Anoultpla = tipoPlanillaDto.getAnoUltimaPlanilla();
        this.Mesultpla = tipoPlanillaDto.getMesUltimaPlanilla();
        this.Numultpla = tipoPlanillaDto.getNumeroUltimaPlanilla();
        this.Estado = tipoPlanillaDto.getEstado();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Integer getPlaxmes() {
        return Plaxmes;
    }

    public void setPlaxmes(Integer Plaxmes) {
        this.Plaxmes = Plaxmes;
    }

    public Integer getAnoultpla() {
        return Anoultpla;
    }

    public void setAnoultpla(Integer Anoultpla) {
        this.Anoultpla = Anoultpla;
    }

    public Integer getMesultpla() {
        return Mesultpla;
    }

    public void setMesultpla(Integer Mesultpla) {
        this.Mesultpla = Mesultpla;
    }

    public Integer getNumultpla() {
        return Numultpla;
    }

    public void setNumultpla(Integer Numultpla) {
        this.Numultpla = Numultpla;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Integer getVersion() {
        return Version;
    }

    public void setVersion(Integer Version) {
        this.Version = Version;
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
        hash += (Id != null ? Id.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString() {
        return "cr.ac.una.unaplanilla2.model.Tipoplanillas[ tplaId=" + Id + " ]";
    }
    
}
