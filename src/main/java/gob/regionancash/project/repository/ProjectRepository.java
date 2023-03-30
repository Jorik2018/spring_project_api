package gob.regionancash.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gob.regionancash.project.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query("SELECT p FROM Project p ORDER BY p.infobrasUpdatedAt ASC")
    abstract List<Project> findProjectToUpdate();

}
