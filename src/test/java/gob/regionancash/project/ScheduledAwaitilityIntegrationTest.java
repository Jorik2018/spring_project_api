package gob.regionancash.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import gob.regionancash.project.component.Counter;
import org.awaitility.Durations;
import org.awaitility.Awaitility;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import java.time.Duration;


@SpringJUnitConfig(ScheduledConfig.class)
//@SpringJUnitConfig(ProjectApplication.class)
public class ScheduledAwaitilityIntegrationTest {

    @SpyBean 
    //@Autowired 
    private Counter counter;

    @Test
    public void whenWaitOneSecond_thenScheduledIsCalledAtLeastTenTimes() {
        Awaitility
        .waitAtMost(Duration.ofSeconds(4))
//.waitAtMost(Durations.ONE_SECOND)

            //.await().atMost(Durations.ONE_SECOND)
          .untilAsserted(() -> verify(counter, atLeast(10)).scheduled());
    }
}