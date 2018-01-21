package com.sandeep.amazon.gm.car;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;

public class CarInstallSpeechlet  implements SpeechletV2{
	private static final Logger log = LoggerFactory.getLogger(CarInstallSpeechlet.class);
	private static final String APPS_SLOT = "Application";
	private static final String APP_KEY = "APP";

	@Override
	public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		log.info("onIntent");
		IntentRequest request = requestEnvelope.getRequest();
		Session session = requestEnvelope.getSession();
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
        		session.getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;
        if ("InstallAppIntent".equals(intentName)) {
            return installApp(intent, session);
        }else if ("WhatsMyAppStatusIntent".equals(intentName)) {
            return getAppStatus(intent);
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelp();
        } else if ("AMAZON.StopIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Goodbye");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else if ("AMAZON.CancelIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Goodbye");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else {
            String errorSpeech = "Sorry I cannot help you with that.";
            return newAskResponse(errorSpeech, errorSpeech);
        }
	}

	private SpeechletResponse installApp(Intent intent, Session session) {
		// TODO Auto-generated method stub
		Map<String, Slot> slots = intent.getSlots();
		String speechText, repromptText;
        // Get the color slot from the list of slots.
        Slot appToInstallSlot = slots.get(APPS_SLOT);
        if (appToInstallSlot != null) {
            // Store the user's favorite color in the Session and create response.
        	 	
            String appToInstall = appToInstallSlot.getValue();
            session.setAttribute(APP_KEY, appToInstall);
            speechText =
                    String.format("Java, I am going to install %s. You can ask me the status of the app by saying,  "
                            + " what is the status?", appToInstall);
            repromptText =
                    "You can ask me the status of the app by saying, what is the status?";

        } else {
            // Render an error since we don't know what the users favorite color is.
            speechText = "I'm not sure what your app is, please try again";
            repromptText =
                    "I'm not sure whatyour app is. You can ask me to install application like,  "
                            + "install Audiobooks, or, you can say exit..."
                            + "Now, what can I help you with?";
        }

		return null;
	}

	private SpeechletResponse getAppStatus(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> arg0) {
		log.info("onLaunch");
		return getHelp();
	}

	@Override
	public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> arg0) {
		log.info("onSessionEnded");
		
	}

	@Override
	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> arg0) {
		log.info("onSessionStarted");


		
	}
	
    private SpeechletResponse getHelp() {
        String speechOutput =
                "Java, You can ask me to install application like  "
                        + "install Audiobooks, or, you can say exit... "
                        + "Now, what can I help you with?";
        String repromptText =
                "You can say things like, install Audiobooks"
                        + ", or you can say exit... Now, what can I help you with?";
        return newAskResponse(speechOutput, repromptText);
    }
	
    private SpeechletResponse newAskResponse(String stringOutput, String repromptText) {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(stringOutput);

        PlainTextOutputSpeech repromptOutputSpeech = new PlainTextOutputSpeech();
        repromptOutputSpeech.setText(repromptText);
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutputSpeech);

        return SpeechletResponse.newAskResponse(outputSpeech, reprompt);
    }

}
