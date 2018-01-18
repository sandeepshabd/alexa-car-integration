package com.sandeep.amazon.gm.car;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;

public class CarInstallSpeechlet  implements SpeechletV2{
	private static final Logger log = LoggerFactory.getLogger(CarInstallSpeechlet.class);

	@Override
	public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> arg0) {
		log.info("onIntent");
		return null;
	}

	@Override
	public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> arg0) {
		log.info("onLaunch");
		return null;
	}

	@Override
	public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> arg0) {
		log.info("onSessionEnded");
		
	}

	@Override
	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> arg0) {
		log.info("onSessionStarted");
		
	}

}
