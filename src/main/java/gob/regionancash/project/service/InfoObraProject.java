package gob.regionancash.project.service;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class InfoObraProject {

    private Long id;

    private String description;

    private String code;

    private String state;
    
}
