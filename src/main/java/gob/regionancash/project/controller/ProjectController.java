package gob.regionancash.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gob.regionancash.project.exception.NotFoundException;
import gob.regionancash.project.model.Project;
import gob.regionancash.project.repository.ProjectRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/project")
public class ProjectController {
    
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/{from}/{to}")
    public Page getAllProjectes(@PathVariable(value = "from") int from,
                                 @PathVariable(value = "to") int to,
                                 @RequestParam(name = "etapaProyecto", defaultValue = "Por Iniciar",required = false) String etapaProyecto) throws Exception {
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
    public ResponseEntity<Project> updateProject(@PathVariable(value = "id") Integer projectId, @Valid @RequestBody Project ProjectData) throws NotFoundException {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new NotFoundException("project no encontrada: " + projectId));

        Project updatedProject = projectRepository.save(project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteProject(@PathVariable(value = "id") Integer projectId) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new NotFoundException("project no encontrada: " + projectId));

        projectRepository.delete(project);
        Map<String, Boolean> response = new HashMap<>();
        response.put("message", Boolean.TRUE);
        return response;
    }

}
