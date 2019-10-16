package com.xatkit.google.assistant.platform.io;

import com.xatkit.google.assistant.platform.GoogleAssistantPlatform;
import com.xatkit.plugins.chat.platform.io.WebhookChatIntentProvider;

import org.apache.commons.configuration2.Configuration;

public class GoogleAssistantIntentProvider extends WebhookChatIntentProvider<GoogleAssistantPlatform, GoogleAssistantRestHandler> {

	private final static String ENDPOINT_URI = "/google/assistant/receiver";

	public GoogleAssistantIntentProvider(GoogleAssistantPlatform runtimePlatform, Configuration configuration) {
		super(runtimePlatform, configuration);
	}

	@Override
	public String getEndpointURI() {
		return ENDPOINT_URI;
	}

	@Override
	protected GoogleAssistantRestHandler createRestHandler() {
		return new GoogleAssistantRestHandler(this);
	}
}
