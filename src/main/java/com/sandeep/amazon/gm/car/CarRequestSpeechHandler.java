package com.sandeep.amazon.gm.car;
import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class CarRequestSpeechHandler extends SpeechletRequestStreamHandler{
	private static final Set<String> supportedApplicationIds;
	static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        supportedApplicationIds = new HashSet<String>();
        supportedApplicationIds.add("[sandeep.alexa-001]");
    }

	public CarRequestSpeechHandler(Speechlet speechlet, Set<String> supportedApplicationIds) {
		super(new CarInstallSpeechlet(), supportedApplicationIds);
	}

}
