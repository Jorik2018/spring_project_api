package gob.regionancash.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gob.regionancash.project.model.Project;
import gob.regionancash.project.repository.ProjectRepository;
import jakarta.persistence.EntityManager;

@Component
public class InfoObraSchedule {

	@Autowired
	InfoObraService infoObraService;

	@Autowired
	private ProjectRepository projectRepository;


    @Scheduled(fixedDelay = 5000/*fixedRate = 1000*/)
	//@Transactional
	public void scheduleFixedDelayTask() {
		List<Project> l=projectRepository.findAll();
		l.stream().filter((e)->e.getInfobrasCode()==null).findFirst().ifPresent((e)->{
			InfoObraProject infoObraProject=infoObraService.getProject(e.getInfobras());
			e.setInfobrasCode(infoObraProject.getCode());
			e.setDescription(infoObraProject.getDescription());
			projectRepository.save(e);
			System.out.println(e);
		});
	}
    
}
