package cr.ac.una.unaplanilla2.model;

import cr.ac.una.unaplanilla2.model.Empleados;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-05-30T00:00:26", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Tipoplanillas.class)
public class Tipoplanillas_ { 

    public static volatile SingularAttribute<Tipoplanillas, BigDecimal> tplaId;
    public static volatile SingularAttribute<Tipoplanillas, BigInteger> tplaAnoultpla;
    public static volatile ListAttribute<Tipoplanillas, Empleados> empleadosList;
    public static volatile SingularAttribute<Tipoplanillas, BigInteger> tplaVersion;
    public static volatile SingularAttribute<Tipoplanillas, BigInteger> tplaPlaxmes;
    public static volatile SingularAttribute<Tipoplanillas, String> tplaCodigo;
    public static volatile SingularAttribute<Tipoplanillas, String> tplaEstado;
    public static volatile SingularAttribute<Tipoplanillas, BigInteger> tplaMesultpla;
    public static volatile SingularAttribute<Tipoplanillas, BigInteger> tplaNumultpla;
    public static volatile SingularAttribute<Tipoplanillas, String> tplaDescripcion;

}