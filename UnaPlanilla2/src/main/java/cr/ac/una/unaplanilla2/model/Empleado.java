/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanilla2.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalQueries;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author David
 */
@Entity
@Table(name = "PLAN_EMPLEADOS",schema = "una")
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByEmpId", query = "SELECT e FROM Empleado e WHERE e.Id = :Id"),
    @NamedQuery(name = "Empleado.findByUsuarioClave", query = "SELECT e FROM Empleado e WHERE (e.Usuario) like :Usuario and (e.Clave) like :Clave"),
  @NamedQuery(name = "Empleado.findByCedulaNombreApellidos", query = "SELECT e FROM Empleado e WHERE UPPER(e.Nombre) like :Nombre and UPPER(e.Cedula) like :Cedula and UPPER(e.Papellido) like :Papellido and UPPER(e.Sapellido) like :Sapellido", hints = @QueryHint(name = "eclipselink.refresh", value = "true")),
    /*@NamedQuery(name = "Empleado.findByEmpNombre", query = "SELECT e FROM Empleado e WHERE e.Nombre = :Nombre"),
    @NamedQuery(name = "Empleado.findByEmpPapellido", query = "SELECT e FROM Empleado e WHERE e.Papellido = :Papellido"),
    @NamedQuery(name = "Empleado.findByEmpSapellido", query = "SELECT e FROM Empleado e WHERE e.Sapellido = :Sapellido"),
    @NamedQuery(name = "Empleado.findByEmpCedula", query = "SELECT e FROM Empleado e WHERE e.Cedula = :Cedula"),
    @NamedQuery(name = "Empleado.findByEmpGenero", query = "SELECT e FROM Empleado e WHERE e.Genero = :Genero"),
    @NamedQuery(name = "Empleado.findByEmpCorreo", query = "SELECT e FROM Empleado e WHERE e.Correo = :Correo"),
    @NamedQuery(name = "Empleado.findByEmpAdministrador", query = "SELECT e FROM Empleado e WHERE e.Administrador = :Administrador"),
    @NamedQuery(name = "Empleado.findByEmpUsuario", query = "SELECT e FROM Empleado e WHERE e.Usuario = :Usuario"),
    @NamedQuery(name = "Empleado.findByEmpClave", query = "SELECT e FROM Empleado e WHERE e.Clave = :Clave"),
    @NamedQuery(name = "Empleado.findByEmpFingreso", query = "SELECT e FROM Empleado e WHERE e.Fingreso = :Fingreso"),
    @NamedQuery(name = "Empleado.findByEmpFsalida", query = "SELECT e FROM Empleado e WHERE e.Fsalida = :Fsalida"),
    @NamedQuery(name = "Empleado.findByEmpEstado", query = "SELECT e FROM Empleado e WHERE e.Estado = :Estado"),
  @NamedQuery(name = "Empleado.findByEmpVersion", query = "SELECT e FROM Empleado e WHERE e.empVersion = :empVersion")*/})
public class Empleado implements Serializable { 

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PLAM_EMPLEADOS_EMP_ID_GENERATOR", sequenceName = "PLAN_EMPLEADOS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAM_EMPLEADOS_EMP_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "EMP_ID")
    private Long Id;
    @Basic(optional = false)
    @Column(name = "EMP_NOMBRE")
    private String Nombre;
    @Basic(optional = false)
    @Column(name = "EMP_PAPELLIDO")
    private String Papellido;
    @Basic(optional = false)
    @Column(name = "EMP_SAPELLIDO")
    private String Sapellido;
    @Basic(optional = false)
    @Column(name = "EMP_CEDULA")
    private String Cedula;
    @Basic(optional = false)
    @Column(name = "EMP_GENERO")
    private String Genero;
    @Column(name = "EMP_CORREO")
    private String Correo;
    @Basic(optional = false)
    @Column(name = "EMP_ADMINISTRADOR")
    private String Administrador;
    @Column(name = "EMP_USUARIO")
    private String Usuario;
    @Column(name = "EMP_CLAVE")
    private String Clave;
    @Basic(optional = false)
    @Column(name = "EMP_FINGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date Fingreso;
    @Column(name = "EMP_FSALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date Fsalida;
    @Basic(optional = false)
    @Column(name = "EMP_ESTADO")
    private String Estado;
    @Basic(optional = false)
    @Column(name = "EMP_VERSION")
    private Long Version;
    @ManyToMany(mappedBy = "empleadosList", fetch = FetchType.LAZY)
    private List<TipoPlanillas> tipoplanillasList;

    public Empleado() {
    }

    public Empleado(Long empId) {
        this.Id = empId;
    }

    public Empleado(Long empId, String empNombre, String empPapellido, String empSapellido, String empCedula, String empGenero, String empAdministrador, Date empFingreso, String empEstado, Long empVersion) {
        this.Id = empId;
        this.Nombre = empNombre;
        this.Papellido = empPapellido;
        this.Sapellido = empSapellido;
        this.Cedula = empCedula;
        this.Genero = empGenero;
        this.Administrador = empAdministrador;
        this.Fingreso = empFingreso;
        this.Estado = empEstado;
        this.Version = empVersion;
    }

    public Empleado(EmpleadoDto empleadoDto){
        this.Id = empleadoDto.getId();
        actualizarEmpleado(empleadoDto);
    }
      
    public void actualizarEmpleado(EmpleadoDto empleadoDto){
        this.Nombre = empleadoDto.getNombre();
        this.Papellido = empleadoDto.getpApellido();
        this.Sapellido = empleadoDto.getsApellido();
        this.Cedula = empleadoDto.getCedula();
        this.Genero = empleadoDto.getGenero();
        this.Administrador = empleadoDto.getAdministrador();
        this.Fingreso = Date.from(empleadoDto.getfIngreso().atStartOfDay(ZoneId.systemDefault()).toInstant());
         if (empleadoDto.getfSalida()!= null) {
            this.Fsalida = Date.from(empleadoDto.getfSalida().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        this.Correo= empleadoDto.getCorreo();
        this.Usuario = empleadoDto.getUsuario();
        this.Clave = empleadoDto.getClave();
        this.Estado = empleadoDto.getEstado();
    }   
    
    public Long getId() {
        return Id;
    }

    public void setId(Long empId) {
        this.Id = empId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPapellido() {
        return Papellido;
    }

    public void setPapellido(String Papellido) {
        this.Papellido = Papellido;
    }

    public String getSapellido() {
        return Sapellido;
    }

    public void setSapellido(String Sapellido) {
        this.Sapellido = Sapellido;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getAdministrador() {
        return Administrador;
    }

    public void setAdministrador(String Administrador) {
        this.Administrador = Administrador;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public Date getFingreso() {
        return Fingreso;
    }

    public void setFingreso(Date Fingreso) {
        this.Fingreso = Fingreso;
    }

    public Date getFsalida() {
        return Fsalida;
    }

    public void setFsalida(Date Fsalida) {
        this.Fsalida = Fsalida;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Long getEmpVersion() {
        return Version;
    }

    public void setEmpVersion(Long empVersion) {
        this.Version = empVersion;
    }

    public List<TipoPlanillas> getTipoplanillasList() {
        return tipoplanillasList;
    }

    public void setTipoplanillasList(List<TipoPlanillas> tipoplanillasList) {
        this.tipoplanillasList = tipoplanillasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Id != null ? Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.Id == null && other.Id != null) || (this.Id != null && !this.Id.equals(other.Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.unaplanilla2.model.Empleados[ empId=" + Id + " ]";
    }
    
}
