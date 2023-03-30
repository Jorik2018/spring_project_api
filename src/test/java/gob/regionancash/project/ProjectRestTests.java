package gob.regionancash.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;

import gob.regionancash.project.model.Project;
import gob.regionancash.project.model.Project.ProjectBuilder;
import gob.regionancash.project.repository.ProjectRepository;
import gob.regionancash.project.service.InfoObraProject;
import gob.regionancash.project.service.InfoObraSchedule;
import gob.regionancash.project.service.InfoObraService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import static org.hamcrest.CoreMatchers.*;
@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
class ProjectRestTests {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectRepository projectRepository;
	
	private ObjectMapper objectMapper;

	private ArrayList<Project> data=new ArrayList();

	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		data.add(Project.builder().coordinator("ERIK").build());
		Mockito.when(projectRepository.findAll()).thenReturn(data);
		Mockito.when(projectRepository.findAll(Mockito.any(PageRequest.class)
			//PageRequest.of(0, 10)
			)).thenReturn(new PageImpl<Project>(data));
	}

	static String TOKEN="eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2V4YW1wbGUuY29tL2lzc3VlciIsInVwbiI6Impkb2VAcXVhcmt1cy5pbyIsImdyb3VwcyI6WyJVc2VyIiwiQWRtaW4iXSwidWlkIjo0NDUsImZ1bGxOYW1lIjoiREFWSUxBIFJPTUVSTyBIQU5TIENFU0FSIiwiZGlyZWN0b3J5Ijo0LCJ1c2VyIjoiNDQ1NjQ1OTEiLCJiaXJ0aGRhdGUiOiIyMDAxLTA3LTEzIiwiaWF0IjoxNjc4MjIzNzM4LCJleHAiOjE2NzgyMjczMzgsImp0aSI6ImJlNzRhMmQ5LTFlYjYtNDZmOS04NjBiLTVlN2E2OWYzODI3YyJ9.A4ea1igttKx-G0mVTf_DGZR36tR9vfUnejCxloO2FTWZIKKeAfLtae2Q4q8UvU-5IvB2BHClChPnXrbu28-i5D810josTdjQd8V75D6YObU6sQohZ7afnQrSphLBVb8BsRoyvjO0uh6h2cH41kRbdCDf-BhApRH6yyRg3MLO_SZIKSlh9bru6-t3XzachNZEcK5ulOEuJ7EohzKK4reH4fvqFAY38uDvoVolWIsXR74YIi9WFO3sp19j3QCo_ZPVhjMzV7ArqeSrPx1rTD1exc4RVQLgIjF6BD4GLPs02pBkhR8mgSgEovm8YOR4e7VU2evmTpNR7Wl4MHg__KQyZw";

	@Test
	void ProjectRestTest() throws JsonProcessingException, Exception {
		MvcResult mvcResult=mockMvc.perform(get("/0/10")
			.contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + TOKEN))
			//.andDo(print())
			.andExpect(status().isOk())
			//.andExpect(jsonPath("$.content",Matchers.hasSize(1)))
			//.andExpect(jsonPath("$.content[0].coordinator", is("ERIK")))
			.andExpect(content().string(containsString(objectMapper.writeValueAsString(data))))
			.andReturn()
			/* .andExpect(content().string(containsString(objectMapper.writeValueAsString(new HashMap() {{
				put("content", data);
			}}))))*/
			;
			/*ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
			verify(characterRepository).findAllPage(pageableCaptor.capture());
			PageRequest pageable = (PageRequest) pageableCaptor.getValue();

			assertThat(pageable).hasPageNumber(0);
			assertThat(pageable).hasPageSize(20);
			assertThat(pageable).hasSort("name", Sort.Direction.DESC);
			assertThat(pageable).hasSort("id", Sort.Direction.ASC);*/
	}

}
