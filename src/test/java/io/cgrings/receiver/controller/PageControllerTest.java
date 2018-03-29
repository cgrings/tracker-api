package io.cgrings.receiver.controller;

import java.time.ZonedDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.cgrings.receiver.Application;
import io.cgrings.tracker.model.PageViewInput;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class PageControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void receiverTest() throws Exception {
        final PageViewInput request = new PageViewInput("foo", "bar.html", ZonedDateTime.now());
        final ResponseEntity<Void> response = this.restTemplate.postForEntity("/api/page/hit", request, Void.class);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}
