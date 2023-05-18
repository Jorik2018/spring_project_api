package gob.regionancash.project.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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


   // @Scheduled(fixedDelay = 10000/*fixedRate = 1000*/)
	//@Transactional
	public void scheduleFixedDelayTask() {
		/*List<Project> l=projectRepository.findProjectToUpdate();
		l.stream()
				///.filter((e)->e.getInfobrasCode()==null)
				.findFirst().ifPresent((e)->{
			if(e.getInfobrasCode()==null){
				InfoObraProject infoObraProject=infoObraService.getProject(e.getInfobras());
				e.setInfobrasCode(infoObraProject.getCode());
				e.setDescription(infoObraProject.getDescription());
				
				e.setInfobrasUpdatedAt(new Date());
			}else{
				Map projectMap=infoObraService.getProjectMap(e.getInfobrasCode());
				if(projectMap!=null){
					Map datosGenerales=(Map)projectMap.get("1. Datos generales");
					if(datosGenerales!=null){
						e.setResident((String)datosGenerales.get("RESIDENTE"));
						e.setContract((String)datosGenerales.get("CONTRATISTA"));
					}
				}
			}
			projectRepository.save(e);
			System.out.println(e);
		});*/
	}
    
}
