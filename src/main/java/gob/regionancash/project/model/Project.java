package gob.regionancash.project.model;

import java.io.Serializable;
import java.util.Date;

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

    @Size(max = 50)
    private String estadoInversion;

    @Size(max = 255)
    private String unidadEjecutora;

    private Integer cui;

    @Size(max = 255)
    private String province;

    private String district;

    private Integer snip;

    private Integer sisgedo;

    private Long infobras;

    private String infobrasCode;

    private String cadenaProductiva;

    private String category;

    @Size(max = 350)
    private String description;

    @Size(max = 255)
    private String rubroFinanciamiento;

    @Size(max = 100)
    private String cadenaFuncional;

    @Size(max = 255)
    private String unidadFormuladora;

    @Size(max = 255)
    private String unidadEvaluadora;

    @Size(max = 255)
    private String executingContractor;

    @Size(max = 40)
    private String ruc;

    private Integer activityId;

    @Column(name = "run_id")
    private Integer dispatchId;

    @Size(max = 255)
    private String tipoProceso;

    @Size(max = 255)
    private String provider;
    
    @Size(max = 255)
    private String contract;
    //PLAZO EJECUCIÓN cuadro
    private Integer limitDate;
    
    private String executionTime;
    
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Temporal(TemporalType.DATE)
    private Date actualReactivationDate;

    @Temporal(TemporalType.DATE)
    private Date announcementDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double fileAmount;

    private Double executionAmount;

    @Size(max = 255)
    private String executionMode;
    
    @Size(max = 255)
    private String extension;

    @Size(max = 255)
    private String supervision;

    private Double supervisionAmount;
    
    @Size(max = 100)
    private String investmentType;
    
    @Size(max = 255)
    private String resident;
    
    @Size(max = 255)
    private String coordinator;
    
    @Size(max = 255)
    private String montoValorizado;
    
    @Size(max = 255)
    private String saldoValorizar;
    @Size(max = 255)
    private String responsable;

    @Size(max = 600)
    private String investmentSituation;

    private Double physicalAdvance;

    @Temporal(TemporalType.DATE)
    private Date physicalAdvanceDate;

    private Double scheduledAdvance;

    private Double infobrasPim;

    private Double updatedCost;

    private Double devengadoAcumulado;

    private Double accrualBalance; //saldo_devengar 2023

    private Double pia;

    private String receptionCommittee;

    private String notebookType;

    private String missing_documents;

    private String certificated;

    private Double pim;

    private Double certification;

    private Double accrued;

    @Size(max = 600)
    private String comment;

    @Column(name = "m_01")
    private Double m01;

    @Column(name = "m_02")
    private Double m02;

    @Column(name = "m_03")
    private Double m03;

    @Column(name = "m_04")
    private Double m04;

    @Column(name = "m_05")
    private Double m05;

    @Column(name = "m_06")
    private Double m06;

    @Column(name = "m_07")
    private Double m07;

    @Column(name = "m_08")
    private Double m08;

    @Column(name = "m_09")
    private Double m09;

    @Column(name = "m_10")
    private Double m10;

    @Column(name = "m_11")
    private Double m11;

    @Column(name = "m_12")
    private Double m12;

    private Double total;

    private Double currentProgress;

    @Transient
    private Object ext;

    private Double lat;

    private Double lon;

    private Integer nid;

    private boolean canceled = false;

    private String status;

    private Double cofinanciamiento;

    private Double aporteAeo;

    @Temporal(TemporalType.DATE)
    private Date infobrasUpdatedAt;

}
