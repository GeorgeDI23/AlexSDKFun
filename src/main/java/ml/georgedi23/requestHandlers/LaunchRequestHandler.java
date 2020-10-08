package ml.georgedi23.requestHandlers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput anInput) {
        return anInput.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = "hello, friend";
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloFriend", speechText)
                .withReprompt(speechText)
                .build();
    }
}
