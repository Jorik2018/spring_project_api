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
@Table(name = "position")
@EntityListeners(AuditingEntityListener.class)
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    private String name;
    @Size(max = 1)
    private String level;
    @Size(max = 6)
    @Column(name = "cod_pdt")
    private String codPdt;
    private Integer nivel;
    private Integer ordenFirma;
    @Size(max = 15)
    @Column(name = "abreviatura")
    private String acronym;
    @Size(max = 1)
    private boolean status=true;

}
