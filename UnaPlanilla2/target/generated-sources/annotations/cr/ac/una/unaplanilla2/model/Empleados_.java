package cr.ac.una.unaplanilla2.model;

import cr.ac.una.unaplanilla2.model.Tipoplanillas;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-05-30T00:12:17", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Empleados.class)
public class Empleados_ { 

    public static volatile SingularAttribute<Empleados, String> Nombre;
    public static volatile SingularAttribute<Empleados, String> Papellido;
    public static volatile SingularAttribute<Empleados, String> Administrador;
    public static volatile SingularAttribute<Empleados, String> Usuario;
    public static volatile SingularAttribute<Empleados, String> Clave;
    public static volatile SingularAttribute<Empleados, String> Sapellido;
    public static volatile SingularAttribute<Empleados, String> Estado;
    public static volatile SingularAttribute<Empleados, String> Correo;
    public static volatile SingularAttribute<Empleados, Date> Fsalida;
    public static volatile SingularAttribute<Empleados, Long> Version;
    public static volatile SingularAttribute<Empleados, String> Genero;
    public static volatile SingularAttribute<Empleados, Long> Id;
    public static volatile SingularAttribute<Empleados, String> Cedula;
    public static volatile ListAttribute<Empleados, Tipoplanillas> tipoplanillasList;
    public static volatile SingularAttribute<Empleados, Date> Fingreso;

}