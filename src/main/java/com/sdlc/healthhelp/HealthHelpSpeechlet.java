package com.sdlc.healthhelp;

import java.util.List;

/**
    Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.sdlc.healthhelp.HealthHelpManager;

/**
 * This sample shows how to create a simple speechlet for handling speechlet requests.
 */
public class HealthHelpSpeechlet implements Speechlet {
    private static final Logger log = LoggerFactory.getLogger(HealthHelpSpeechlet.class);
    private AmazonDynamoDBClient amazonDynamoDBClient;
    private HealthHelpManager healthHelpManager;

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        // any initialization logic goes here
        initializeComponents();
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
            throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return healthHelpManager.getLaunchResponse(request, session);
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        initializeComponents();
        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if("HealthHelp".equals(intentName)) {
        	return healthHelpManager.getHealthHelpIntentResponse(session);
        }
        if("AppointmentIntent".equals(intentName)) {
        	return healthHelpManager.getAppointmentResponse(session);
        }
        if("PatientIntent".equals(intentName)) {
        	return healthHelpManager.getPatientIntentResponse(session);
        }
        if("AddPatientTntent".equals(intentName)) {
        	return healthHelpManager.savePatientAppointmentIntentResponse(intent,session);
        }
        if("GetPatientTntent".equals(intentName)) {
        	return healthHelpManager.getpatientAppointmentIntentResponse(intent,session);
        }
        
        else if ("AMAZON.HelpIntent".equals(intentName)) {
            return healthHelpManager.getHelpResponse();
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }


	

	@Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        // any cleanup logic goes here
    }

    /**
     * Creates and returns a {@code SpeechletResponse} with a welcome message.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Welcome to the Health help, you can say hello test";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("HealthHelp");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    /**
     * Initializes the instance components if needed.
     */
    private void initializeComponents() {
        if (amazonDynamoDBClient == null) {
            amazonDynamoDBClient = new AmazonDynamoDBClient();
            healthHelpManager = new HealthHelpManager(amazonDynamoDBClient);
            
        }
    }
}
