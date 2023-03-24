package gob.regionancash.project.model;

import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "project_infrastructure")
@EntityListeners(AuditingEntityListener.class)
public class ProjectInfrastructure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "infrastructure_id")
    private Integer infrastructureId;
    //@JoinColumn(name = "infrastructure_id", referencedColumnName = "id", insertable = false, updatable = false)
    //@ManyToOne(optional = false)
    //private Infrastructure infrastructure;

}
