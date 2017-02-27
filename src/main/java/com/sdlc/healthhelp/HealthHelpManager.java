package com.sdlc.healthhelp;

import java.util.Map;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.slu.Slot.Builder;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import storage.Appointment;
import storage.HealthHelpApp;
import storage.HealthHelpDao;
import storage.HealthHelpDynamoDbClient;
import storage.HealthHelpTextUtil;

public class HealthHelpManager {
	HealthHelpDao  helpDao;
	HealthHelpApp app;
	Appointment appointment;
	private static final String PATIENT_NAMES = "patient";
	private static final String DATE_OF_BIRTH = "dob";
	private static final String PHONE_NUMBER = "phoneNo";
	private static final String ADDRESS="address";
	 
	public HealthHelpManager(final AmazonDynamoDBClient amazonDynamoDbClient) {
		HealthHelpDynamoDbClient dynamoDbClient =
                new HealthHelpDynamoDbClient(amazonDynamoDbClient);
		 helpDao= new HealthHelpDao(dynamoDbClient);
    }
	 /**
     * Creates and returns response for Launch request.
     *
     * @param request
     *            {@link LaunchRequest} for this request
     * @param session
     *            Speechlet {@link Session} for this request
     * @return response for launch request
     */
    public SpeechletResponse getLaunchResponse(LaunchRequest request, Session session) {
        // Speak welcome message and ask user questions
        // based on whether there are players or not.
        String speechText, repromptText;
        
            speechText = "HealthHelp, how can i help you?";
            repromptText ="Do you need to schedule an  appointment or renew a prescription ";
     
		

      return getAskSpeechletResponse(speechText, repromptText);
    }
    
    private SpeechletResponse getAskSpeechletResponse(String speechText, String repromptText) {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Session");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(repromptText);
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }


    public SpeechletResponse getHealthHelpIntentResponse(Session session) {
    	
        String speechText =
        		"Hello, How can i help you "; 
		return  getTellSpeechletResponse(speechText);

            }
    
    private SpeechletResponse getTellSpeechletResponse(String speechText) {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Session");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }
    
    public SpeechletResponse getHelpResponse() {
        String speechText = "welcome to healthhelp";

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
    
   
    public SpeechletResponse getAppointmentResponse(Session session) {
        // Speak welcome message and ask user questions
        // based on whether there are patients or not.
        String speechText, repromptText;

       
            speechText = "HealthHelp, Are you a new patient or old patient";
            repromptText =HealthHelpTextUtil. NEXT_HELP;
       
		

      return getAskSpeechletResponse(speechText, repromptText);
    }
    
   public SpeechletResponse getPatientIntentResponse( Session session) {
        
	   
           String speechText = "OK.If you a new patient? Please tell me your first name ";
           
             return getAskSpeechletResponse(speechText, speechText);
          
        
    }

   
    
   public SpeechletResponse savePatientAppointmentIntentResponse(Intent intent, Session session) {
	 
		 
	   Slot name = intent.getSlot(PATIENT_NAMES);
	  String patientName = name.getValue().toString();
	  Slot date = intent.getSlot(DATE_OF_BIRTH);
	  String dob = date.getValue().toString();
	  
	   
	   helpDao.saveHealthHelpApp(patientName,dob);

      String speechText = "An appointment is scheduled for"+patientName+" " + "and the date of birth is" + dob;
      String repromptText = HealthHelpTextUtil.NEXT_HELP;
     return getAskSpeechletResponse(speechText,repromptText);

  }
    
  public SpeechletResponse getpatientAppointmentIntentResponse(Intent intent, Session session){
	 String appDate= helpDao.fetchAppointment("2");
	  String speechText = "An appointment is scheduled on" + appDate;
	  String repromptText = HealthHelpTextUtil.NEXT_HELP;
	return getAskSpeechletResponse(speechText,repromptText);
  }
    
       
}
