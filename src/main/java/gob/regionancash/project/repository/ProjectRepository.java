package gob.regionancash.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gob.regionancash.project.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
