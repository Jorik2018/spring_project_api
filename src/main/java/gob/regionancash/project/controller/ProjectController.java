package gob.regionancash.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;

import gob.regionancash.project.exception.NotFoundException;
import gob.regionancash.project.model.Project;
import gob.regionancash.project.repository.ProjectRepository;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("")
public class ProjectController {
    
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/{from}/{to}")
    //@PreAuthorize("hasAuthority('REGISTER_PROJECT_CIUDADANA')")
    public Page getAllProjectes(Authentication authentication, @PathVariable(value = "from") int from,
                                 @PathVariable(value = "to") int to,
                                 @RequestParam(name = "activo", required = false, defaultValue = "1") Integer activo,
                                 @RequestParam(name = "dependencia", required = false) Long dependenciaId,
                                 @RequestParam(name = "fecha", required = false) LocalDate fecha

    ) throws Exception {
            return projectRepository.findAll(PageRequest.of(from, to));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectesById(@PathVariable(value = "id") Integer projectId) throws NotFoundException {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new NotFoundException("project no encontrada :" + projectId));
        return ResponseEntity.ok().body(project);
    }

    @PostMapping("")
    public Project createProject(@Valid @RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('REGISTER_PROJECT_CIUDADANA')")
    public ResponseEntity<Project> updateProject(@PathVariable(value = "id") Integer projectId, @Valid @RequestBody Project ProjectData) throws NotFoundException {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new NotFoundException("project no encontrada: " + projectId));

        Project updatedProject = projectRepository.save(project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('REGISTER_PROJECT_CIUDADANA')")
    public Map<String, Boolean> deleteProject(@PathVariable(value = "id") Integer projectId) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new NotFoundException("project no encontrada: " + projectId));

        projectRepository.delete(project);
        Map<String, Boolean> response = new HashMap<>();
        response.put("message", Boolean.TRUE);
        return response;
    }

}
