package com.xatkit.google.assistant;

import com.xatkit.plugins.chat.ChatUtils;

public interface GoogleAssistantUtils extends ChatUtils {
	
	String GOOGLE_ASSISTANT_API_KEY = "xatkit.google.assistant.api.key";
	
	String GOOGLE_ASSISTANT_CONTEXT_KEY = "googleassistant";

	String GOOGLE_ASSISTANT_REQUEST_ID_CONTEXT_KEY = "request_id";
	
	String GOOGLE_ASSISTANT_SESSION_ID_CONTEXT_KEY = "session_id";
	
	String GOOGLE_ASSISTANT_USERID_CONTEXT_KEY = "user_id";

	String GOOGLE_ASSISTANT_INVOCATION_MESSAGE_KEY = "xatkit.google.assistant.invocationMessage"; //Key to retrieve default invocation welcome message

	String DEFAULT_GOOGLE_ASSISTANT_INVOCATION_MESSAGE = "Hello! Welcome to Xatkit! What can I do for you?";

	String GOOGLE_ASSISTANT_RESPONSE_NOT_FOUND_MESSAGE_KEY = "xatkit.google.assistant.responseNotFoundMessage"; //Key to retrieve default message to send to Alexa
	
	String DEFAULT_GOOGLE_ASSISTANT_RESPONSE_NOT_FOUND_MESSAGE = "Xatkit could not respond to your request, can you please try again?";
}
