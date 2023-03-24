package gob.regionancash.project.model;

import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "project_category")
@EntityListeners(AuditingEntityListener.class)
public class ProjectCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private Integer projectId;
    private Integer categoryId;
    @Size(max = 200)
    private String description;
    
}
