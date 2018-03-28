package io.cgrings.receiver.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.cgrings.receiver.service.PageService;
import io.cgrings.tracker.model.PageViewInput;

@RestController
public class PageController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PageService pageService;

	@PostMapping(value = "/api/page/hit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> receive(@RequestBody final PageViewInput pageViewInput) {
	    this.pageService.sendToQueue(pageViewInput);
	    return new ResponseEntity<>(HttpStatus.CREATED);
	}

}