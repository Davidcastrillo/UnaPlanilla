package cr.ac.una.unaplanilla2.model;

import cr.ac.una.unaplanilla2.model.TipoPlanillas;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-06-12T12:50:05", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, String> Nombre;
    public static volatile SingularAttribute<Empleado, String> Papellido;
    public static volatile SingularAttribute<Empleado, String> Administrador;
    public static volatile SingularAttribute<Empleado, String> Usuario;
    public static volatile SingularAttribute<Empleado, String> Clave;
    public static volatile SingularAttribute<Empleado, String> Sapellido;
    public static volatile SingularAttribute<Empleado, String> Estado;
    public static volatile SingularAttribute<Empleado, String> Correo;
    public static volatile SingularAttribute<Empleado, Date> Fsalida;
    public static volatile SingularAttribute<Empleado, Long> Version;
    public static volatile SingularAttribute<Empleado, String> Genero;
    public static volatile SingularAttribute<Empleado, Long> Id;
    public static volatile SingularAttribute<Empleado, String> Cedula;
    public static volatile ListAttribute<Empleado, TipoPlanillas> tipoplanillasList;
    public static volatile SingularAttribute<Empleado, Date> Fingreso;

}