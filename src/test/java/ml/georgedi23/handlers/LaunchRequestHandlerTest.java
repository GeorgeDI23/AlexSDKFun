package ml.georgedi23.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.*;
import com.amazon.ask.model.ui.Card;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class LaunchRequestHandlerTest {

    HandlerInput launchRequest;
    HandlerInput notLaunchRequest;

    @Before
    public void setUp(){
        RequestEnvelope requestEnvelope0 = RequestEnvelope.builder()
                .withRequest(LaunchRequest.builder().withTask(
                        Task.builder().withName("TEST").build()
                ).build()
                ).build();
        launchRequest = HandlerInput.builder().withRequestEnvelope(requestEnvelope0).build();

        Intent intent = Intent.builder().withName("test").build();
        RequestEnvelope requestEnvelope1 = RequestEnvelope.builder()
                .withRequest(IntentRequest.builder().withIntent(intent).build()).build();
        notLaunchRequest = HandlerInput.builder().withRequestEnvelope(requestEnvelope1).build();
    }

    @Test
    public void canHandleCanTest() {
        //Given - a launchRequest
        LaunchRequestHandler handler = new LaunchRequestHandler();

        //When
        boolean canHandle = handler.canHandle(launchRequest);

        //Then
        assertTrue(canHandle);
    }

    @Test
    public void canHandleCantTest() {
        //Given - a launchRequest
        LaunchRequestHandler handler = new LaunchRequestHandler();

        //When
        boolean canHandle = handler.canHandle(notLaunchRequest);

        //Then
        assertFalse(canHandle);
    }

    @Test
    public void handle() {
        //Given
        String expected = "hello, friend";
        LaunchRequestHandler handler = new LaunchRequestHandler();

        //When TODO - find a better way to test this
        Optional<Response> reply = handler.handle(launchRequest);
        Response response = reply.get();
        Card card = response.getCard();
        String returnCard = card.toString();
        Matcher matcher = Pattern.compile("(?<=content: ).{13}").matcher(card.toString());
        String actual = "";
        if(matcher.find()) actual = matcher.group();

        //Then
        assertEquals(expected, actual);
    }
}