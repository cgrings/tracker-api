package io.cgrings.receiver.service;

import io.cgrings.tracker.model.ContactInput;

public interface ContactService {

	void sendToQueue(final ContactInput contactInput);

}
