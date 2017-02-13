/**
 * 
 */
package com.sdlc.scheduling;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.Events;

/**
 * @author SDLC-Consultant
 *
 */
public class Calendar {

	private static final String APPLICATION_NAME = "Health Help Google Calendar API";

	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/HealthHelpCalendar");

	private static FileDataStoreFactory DATA_STORE_FACTORY;
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	private static HttpTransport HTTP_TRANSPORT;

	private static final List<String> SCOPES = Arrays.asList(CalendarScopes.CALENDAR);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize(String username) throws IOException {
		// URL[] urls = ((URLClassLoader)
		// ClassLoader.getSystemClassLoader()).getURLs();
		// for (URL url : urls) {
		// System.out.println(url);
		// }

		InputStream in = Calendar.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();

		Credential credential = null;
		try {
			credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize(username);
		} catch (Exception e) {

		}

		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());

		return credential;
	}

	/**
	 * Build and return an authorized Calendar client service.
	 * 
	 * @return an authorized Calendar client service
	 * @throws IOException
	 */
	public static com.google.api.services.calendar.Calendar getCalendarService(String userName) throws IOException {

		Credential credential = authorize(userName);

		return new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	private static void viewCalenders(String userName) throws IOException {

		com.google.api.services.calendar.Calendar service = getCalendarService(userName);

		String pageToken = null;

		do {
			CalendarList calendarList = service.calendarList().list().setPageToken(pageToken).execute();
			List<CalendarListEntry> items = calendarList.getItems();

			for (CalendarListEntry calendarListEntry : items) {
				System.out.println("viewCalenders()." + calendarListEntry.getSummary() + " --&gt; "
						+ calendarListEntry.getId() + " --&gt; " + calendarListEntry.getAccessRole());
			}

			pageToken = calendarList.getNextPageToken();

		} while (pageToken != null);

	}

	public static void viewEvents(String userName) throws IOException {

		// Build a new authorized API client service.
		// Note: Do not confuse this class with the
		// com.google.api.services.calendar.model.Calendar class.

		com.google.api.services.calendar.Calendar service = getCalendarService(userName);

		// List the next 10 events from the primary calendar.
		DateTime now = new DateTime(System.currentTimeMillis());

		Events events = service.events().list("primary").setMaxResults(10).setTimeMin(now).setOrderBy("startTime")
				.setSingleEvents(true).execute();

		List<Event> items = events.getItems();

		if (items.size() == 0) {
			System.out.println("No upcoming events found.");
		} else {
			System.out.println("Upcoming events");
			for (Event event : items) {
				DateTime start = event.getStart().getDateTime();
				if (start == null) {
					start = event.getStart().getDate();
				}

				System.out.printf("%s (%s)\n", event.getSummary(), start);
			}
		}
	}
	
	public static void insertEvent(String userName) throws IOException {
		 
		com.google.api.services.calendar.Calendar service = getCalendarService(userName);
		 
		Event event = new Event()
		.setSummary("SDLC AD Staff Meeting")
		.setLocation("401 Liberty Ave #401, Pittsburgh, PA 15222")
		.setDescription("Staff Meeting");
		 
		DateTime startDateTime = new DateTime("2017-02-25T09:00:00-07:00");
		 
		EventDateTime start = new EventDateTime()
		.setDateTime(startDateTime)
		.setTimeZone("America/new_york");
		 
		event.setStart(start);
		DateTime endDateTime = new DateTime("2017-02-28T17:00:00-07:00");
		EventDateTime end = new EventDateTime()
		.setDateTime(endDateTime)
		.setTimeZone("America/new_york");
		event.setEnd(end);
		 
		String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
		event.setRecurrence(Arrays.asList(recurrence));
		EventAttendee[] attendees = new EventAttendee[] {
		new EventAttendee().setEmail(userName+"@gmail.com"),
//		new EventAttendee().setEmail("swebrin@example.com"),
		};
		 
		event.setAttendees(Arrays.asList(attendees));
		EventReminder[] reminderOverrides = new EventReminder[] {
		new EventReminder().setMethod("email").setMinutes(24 * 60),
		new EventReminder().setMethod("popup").setMinutes(10),
		};
		 
		Event.Reminders reminders = new Event.Reminders()
		.setUseDefault(false)
		.setOverrides(Arrays.asList(reminderOverrides));
		 
		event.setReminders(reminders);
		 
		String calendarId = "primary";
		event = service.events().insert(calendarId, event).execute();
		 
		System.out.printf("Event created: %s\n", event.getHtmlLink());
		 
		}

}
