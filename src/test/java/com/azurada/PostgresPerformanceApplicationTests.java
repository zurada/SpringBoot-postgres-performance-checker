package com.azurada;

import com.azurada.service.PerformanceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PostgresPerformanceApplicationTests {

    @Autowired
    private PerformanceService performanceService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void logInsertingExecutionTime() {
        performanceService.insertTransactions(5000);
    }

    @Test
    public void givenTwoEntitiesWithStringOrLongIds_whenFindingOneById_logExecutionTime() {
        int count = 5;
        int entries = 0;
        List<String> allStringIds = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            entries += count;
            allStringIds.addAll(performanceService.insertTransactions(count));
            performanceService.ignoreFirstFinding();
            log.info("Entries in db: {}", entries);
            for (int j = 0; j < 10; j++) {
                log.info("Test number {}:", j);
                log.info("Object with long: {}", performanceService.findOneById((long) generateRandomInt(entries)));
                log.info("Object with String: {}", performanceService.findOneById(allStringIds.get(generateRandomInt(entries))));
            }
        }
    }

    private Integer generateRandomInt(Integer range) {
        return ThreadLocalRandom.current().nextInt(1, range);
    }

}
