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
@Table(name = "project_comment")
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
    @NamedQuery(name = "ProjectComment.findAll", query = "SELECT p FROM ProjectComment p")})
public class ProjectComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 350)
    @Column(name = "comment")
    private String comment;
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Column(name = "user_")
    private Integer user;
    @Basic(optional = false)
    @NotNull
    @Column(name = "canceled")
    private short canceled;

}
