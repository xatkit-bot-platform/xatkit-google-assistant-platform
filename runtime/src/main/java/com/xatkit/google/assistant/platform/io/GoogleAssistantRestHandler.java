package com.xatkit.google.assistant.platform.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xatkit.core.platform.io.IntentRecognitionHelper;
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

        JsonObject result = new JsonObject();

        return result;
    }
}
