package com.sdlc.test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.amazon.speech.speechlet.Session;
import com.sdlc.healthhelp.HealthHelpSpeechlet;
import com.sdlc.scheduling.Calendar;


public class TestHealthHelp {

	 @Rule
	    public ExpectedException exception = ExpectedException.none();

	    private HealthHelpSpeechlet speechlet;
	    private Calendar healthHelpCalendar;
	    private Session session;
	    
	    @Before
	    public void init() {
	        speechlet = new HealthHelpSpeechlet();
	        healthHelpCalendar = new Calendar();
	        session = ModelFactory.givenSession();
	    }
	    
	    @Test
	    public void onSessionStarted() throws Exception {
	        speechlet.onSessionStarted(ModelFactory.givenSessionStartedRequest(), session);
	    }

	@Test
	public void estHealthHelpSpeechlet() throws Exception {
		speechlet.onIntent(ModelFactory.givenIntentRequest("HelloWorldIntent"), session);
//		Assert.assertEquals(HealthHelpSpeechlet.getHelloWorld(), "Hello World");
	}
	
	@Test
	public void TestHealthHelpCalendar() throws Exception {
		String userName = "kiosk.healthhelp1";
		
		
//		healthHelpCalendar.viewEvents(userName1);
	healthHelpCalendar.viewEvents(userName);
//		healthHelpCalendar.insertEvent(userName);
	}
}
