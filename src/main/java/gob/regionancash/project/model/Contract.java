package gob.regionancash.project.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "contract")
@EntityListeners(AuditingEntityListener.class)
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "people_id")
    private int peopleId;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fechaIni;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    private int companyId;
    private Integer dependencyId;
    @Column(name = "position_id")
    private Integer positionId;
    @Size(max = 6)
    @Column(name = "province_id")
    private String provinceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @Column(name = "employee_id")
    private Integer employeeId;
    @Size(max = 50)
    private String dependency;
    @Column(name = "charge")
    private Boolean charge;

}
