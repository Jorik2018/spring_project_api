package gob.regionancash.project;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import gob.regionancash.project.component.Counter;


@SpringJUnitConfig(ProjectApplication.class)
public class ScheduledIntegrationTest {


    @Autowired 
    Counter counter;

    @Test
    public void givenSleepBy100ms_whenGetInvocationCount_thenIsGreaterThanZero() 
      throws InterruptedException {
        Thread.sleep(100L);
        assertTrue(counter.getInvocationCount()>0);
        MatcherAssert.assertThat(counter.getInvocationCount(),Matchers.greaterThan(0));
    }
}