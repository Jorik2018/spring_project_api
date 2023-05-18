package gob.regionancash.project.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

//https://www.viamichelin.es/web/Hoteles?geoboundaries=-12.1568147,-77.2146606:-11.9298369,-76.8418121

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "prj_project")
@EntityListeners(AuditingEntityListener.class)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "estatus_seace")
    private String estadoProceso;

    /*1 = Por Iniciar ; 2 = En Ejecucion; 3 = Suspendidas - Paralizadas; 4 = Ejecucion por ARRCC; 5 = En Recepcion; 6 = En Liquidacion; 7 = Contrato Resuelto*/
    @Column(nullable = false)
    private String etapaProyecto;

    @Column(length = 10, nullable = false)
    private String cui;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String denominacionProyecto;

    @Column(length = 64)
    private String plazoEjecucion;

    @Column(columnDefinition = "decimal(18,2)")
    private String saldoPresupuestal;

    @Column(length = 64)
    private String pia;

    @Column(columnDefinition = "decimal(18,2)")
    private String montoInversion;

    @Column(columnDefinition = "TEXT")
    private String contratistaEjecutor;

    @Column(columnDefinition = "decimal(18,2)")
    private String montoContratoContratista;

    @Column(columnDefinition = "TEXT")
    private String observacionInfo;

    @Column(columnDefinition = "TEXT")
    private String observacionLocal;

    private LocalDate fechaInicioContractual;

    private LocalDate fechaCulminacionContractual;

    private LocalDate fechaReprogramadaCulminacion;

    @Column(length = 64)
    private String avanceFisico;

    @Column(columnDefinition = "TEXT")
    private String contratistaSupervisor;

    @Column(columnDefinition = "decimal(18,2)")
    private String montoContratistaSupervisor;

    @Column(length = 512)
    private String coordinador;

    @Column(length = 512)
    private String tipoCuaderno;

    @Column(columnDefinition = "decimal(18,2)")
    private String presupuestar;

    @Column(length = 4)
    private String certificado;

    private LocalDate fechaSuspension;

    @Column(length = 512)
    private String residente;

    @Column(length = 512)
    private String nroResolucion;

    private LocalDate fechaResolucion;

    @Column(length = 512)
    private String nroContrato;

    @Column(length = 512)
    private String presidente;

    @Column(length = 512)
    private String primerMiembro;

    @Column(length = 512)
    private String asesorTecnico;

    @Column(length = 512)
    private String tipoContrato;

    private LocalDate fechaInicioLiquidacion;

    private LocalDate fechaFinLiquidacion;

    @Column(length = 128)
    private String estadoLiquidacion;

    @Temporal(TemporalType.DATE)
    private Date infobrasUpdatedAt;

}
