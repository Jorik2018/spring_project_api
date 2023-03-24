package gob.regionancash.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;

import gob.regionancash.project.model.Project;
import gob.regionancash.project.model.Project.ProjectBuilder;
import gob.regionancash.project.repository.ProjectRepository;
import gob.regionancash.project.service.InfoObraProject;
import gob.regionancash.project.service.InfoObraSchedule;
import gob.regionancash.project.service.InfoObraService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureDataJpa
//@Transactional
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class ProjectApplicationTests {

    @Autowired
    private ProjectRepository projectRepository;

	//@Autowired
	//private TestEntityManager tem;

    /*@Autowired
    private InfoObraSchedule ios;*/

	@Autowired
	private InfoObraService infoObraService;

	@Test
	void contextLoads() {
		assertEquals(1,1);
	}

	@Test
	void InfoObraServiceTest() {
		InfoObraProject infoObraProject=infoObraService.getProject(121964);
		assertEquals("Rggoi•", infoObraProject.getCode());
		assertEquals("Rggoi•",infoObraService.getProject( infoObraProject.getCode()));
	}

	//@Transactional
	//@BeforeAll
	@Test
	@Order(1)
	void insertProjects()  {
		ProjectBuilder pb=Project.builder();
		Long count=projectRepository.count();
		int j=0;
		for(int i=0;i<j;i++){
			projectRepository.save(pb.cui(i+1).infobras((long) (121959+i)).build());
		}
		assertEquals(j+count, projectRepository.count());
	}

	@Test
	@Order(2)
	void updateProjectsServiceTest() throws InterruptedException {
		//insertProjects() ;
		Thread.sleep(600L);
		for(Project p:projectRepository.findAll()){
			System.out.println(p.getId()+" "+p.getInfobras());
		}
		//assertEquals(3, em.createQuery("SELECT COUNT(p) FROM Project p",Number.class).getSingleResult().intValue());
	}

}
