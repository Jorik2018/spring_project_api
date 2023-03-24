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
@Table(name = "project_executed")
@EntityListeners(AuditingEntityListener.class)
public class ProjectExecuted implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "project_id")
    private int projectId;
    @Basic(optional = false)
    @NotNull
    private int detailId;
    private Integer days;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private Double quantity;
    @Column(name = "progress")
    private Double progress;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "budget")
    private Double budget;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "registered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registered;
    @Column(name = "people_id")
    private Integer peopleId;
    @Column(name = "scheduled_advance")
    private Float scheduledAdvance;

    @Transient
    private Object ext;

}
