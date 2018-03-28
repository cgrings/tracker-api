package io.cgrings.receiver.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.cgrings.receiver.service.ContactService;
import io.cgrings.tracker.model.ContactInput;

@RestController
public class ContactController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContactService contactService;

	@PostMapping(value = "/api/contact", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> receive(@RequestBody final ContactInput contactInput) {
	    this.contactService.sendToQueue(contactInput);
	    return new ResponseEntity<>(HttpStatus.CREATED);
	}

}