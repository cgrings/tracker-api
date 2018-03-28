package io.cgrings.receiver.service;

import io.cgrings.tracker.model.PageViewInput;

public interface PageService {

	void sendToQueue(final PageViewInput pageViewInput);

}
