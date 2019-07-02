package com.paula9t9.taskmaster;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TaskmasterApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
public class TaskRepositoryIntegrationTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    TaskRepository repository;

    private static final String EXPECTED_TITLE = "Lab 26";
    private static final String EXPECTED_DESCRIPTION = "Do the things";

    @Before
    public void setUp() throws Exception {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Task.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        dynamoDBMapper.batchDelete(repository.findAll());
    }

    @Test
    public void readWriteTestCase() {
        Task lab = new Task("Lab 26", "Do the things");
        repository.save(lab);

        List<Task> result = (List<Task>) repository.findAll();

        assertTrue("Repository should not be empty", result.size() > 0);
        assertTrue("Contains task with expected title", result.get(0).getTitle().equals(EXPECTED_TITLE));
//        assertTrue("Contains task with expected description", result.get(0).getTitle().equals(EXPECTED_DESCRIPTION));
    }

    @Test
    public void testMath(){
        assertTrue("should be true",true);
    }
}
