package com.xatkit.google.assistant.platform.io;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xatkit.core.platform.io.IntentRecognitionHelper;
import com.xatkit.google.assistant.GoogleAssistantUtils;
import com.xatkit.google.assistant.platform.io.GoogleAssistantIntentProvider;
import com.xatkit.core.server.JsonRestHandler;
import com.xatkit.core.session.XatkitSession;
import com.xatkit.intent.RecognizedIntent;
import com.xatkit.plugins.chat.ChatUtils;

import fr.inria.atlanmod.commons.log.Log;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static java.util.Objects.nonNull;

public class GoogleAssistantRestHandler extends JsonRestHandler {

    private GoogleAssistantIntentProvider provider;

    public GoogleAssistantRestHandler(GoogleAssistantIntentProvider provider) {
        super();
        this.provider = provider;
    }

    @Override
    public JsonElement handleParsedContent(@Nonnull List<Header> headers, @Nonnull List<NameValuePair> params,
                                           @Nullable JsonElement jsonElement) {

        JsonObject contentObject = jsonElement.getAsJsonObject();

        // Gets request section
        String requestId = contentObject.get("responseId").getAsString();
        Log.info("Request ID: {0}", requestId);
        
        // The sessionId value to save in channel
        String sessionId = null;
        sessionId = contentObject.get("session").getAsString();
        Log.info("Session ID: {0}", sessionId);

        //Gets intent information
        String input = contentObject.get("originalDetectIntentRequest").getAsJsonObject()
									.get("payload").getAsJsonObject()
									.get("inputs").getAsJsonArray().get(0).getAsJsonObject()
									.get("rawInputs").getAsJsonArray().get(0).getAsJsonObject()
									.get("query").getAsString();
        
        XatkitSession session = provider.getRuntimePlatform().getXatkitCore().getOrCreateXatkitSession("googleassistant");

        RecognizedIntent intent = IntentRecognitionHelper.getRecognizedIntent(input, session,
                provider.getRuntimePlatform().getXatkitCore());

        String username = "";
        
        session.getRuntimeContexts().setContextValue(ChatUtils.CHAT_CONTEXT_KEY, 1,
        		GoogleAssistantUtils.CHAT_USERNAME_CONTEXT_KEY, username);
        session.getRuntimeContexts().setContextValue(ChatUtils.CHAT_CONTEXT_KEY, 1,
        		GoogleAssistantUtils.CHAT_CHANNEL_CONTEXT_KEY, sessionId);
        session.getRuntimeContexts().setContextValue(ChatUtils.CHAT_CONTEXT_KEY, 1,
        		GoogleAssistantUtils.CHAT_RAW_MESSAGE_CONTEXT_KEY, input);
        session.getRuntimeContexts().setContextValue(GoogleAssistantUtils.GOOGLE_ASSISTANT_CONTEXT_KEY, 1,
        		GoogleAssistantUtils.GOOGLE_ASSISTANT_REQUEST_ID_CONTEXT_KEY, requestId);
        session.getRuntimeContexts().setContextValue(GoogleAssistantUtils.GOOGLE_ASSISTANT_CONTEXT_KEY, 1,
        		GoogleAssistantUtils.GOOGLE_ASSISTANT_USERID_CONTEXT_KEY, "");
        session.getRuntimeContexts().setContextValue(GoogleAssistantUtils.GOOGLE_ASSISTANT_CONTEXT_KEY, 1,
        		GoogleAssistantUtils.CHAT_USERNAME_CONTEXT_KEY, username);
        session.getRuntimeContexts().setContextValue(GoogleAssistantUtils.GOOGLE_ASSISTANT_CONTEXT_KEY, 1,
        		GoogleAssistantUtils.GOOGLE_ASSISTANT_SESSION_ID_CONTEXT_KEY, sessionId);
        //Adds a context value to wait for the response, bound to the current sessionId
        session.getRuntimeContexts().setContextValue(sessionId, 1,
        		GoogleAssistantUtils.GOOGLE_ASSISTANT_REQUEST_ID_CONTEXT_KEY, requestId);
        
        provider.sendEventInstance(intent, session);

        JsonObject result = new JsonObject();
        JsonObject payload = new JsonObject();
        JsonObject google = new JsonObject();        
        JsonObject richResponse = new JsonObject();
        JsonArray items = new JsonArray();  
        JsonObject response = new JsonObject();
        JsonObject simpleResponse = new JsonObject();
        

        // Adds basic information
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }
        String responseMessage = "hello, this is Xatkit!";//this.provider.getRuntimePlatform().getMessage(requestId);
		Log.info("Request requested: {0}", requestId);
        if (nonNull(responseMessage)) {
            simpleResponse.addProperty("textToSpeech", responseMessage);
        } else {
        	simpleResponse.addProperty("textToSpeech", this.provider.getRuntimePlatform().getResponseNotFoundMessage());
        }
		
		response.add("simpleResponse",simpleResponse);

        // Adds sub-branches to final response
        items.add(response);
        richResponse.add("items", items);
        google.add("richResponse", richResponse);
        google.addProperty("expectUserResponse", true);
        payload.add("google", google);
        result.add("payload", payload);

        // Logs response
        Log.info("Sent {0}", result.toString());

        return result;
    }
}
