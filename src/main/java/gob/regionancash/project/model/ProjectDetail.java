package gob.regionancash.project.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "project_detail")
@EntityListeners(AuditingEntityListener.class)
public class ProjectDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    private int projectId;
    private Integer parentId;
    private String description;
    private String um;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Column(name = "executed_end_date")
    @Temporal(TemporalType.DATE)
    private Date executedEndDate;
    @Column(name = "executed_start_date")
    @Temporal(TemporalType.DATE)
    private Date executedStartDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "budget")
    private Double budget;
    @Column(name = "executed_budget")
    private Double executedBudget;
    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "type_id")
    private Integer typeId;
    private Boolean executed;
    @Transient
    private Object ext;

}
