package gob.regionancash.project.model;

import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "project_member")
@EntityListeners(AuditingEntityListener.class)
public class ProjectMember implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    private long peopleId;
    @Basic(optional = false)
    @NotNull
    private int projectId;
    @Basic(optional = false)
    @NotNull
    private int positionId;
    @Basic(optional = false)
    @NotNull
    private boolean status;
    
}
