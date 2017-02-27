package storage;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.amazon.speech.speechlet.Session;

/**
 * Represents a score keeper game.
 */
public final class HealthHelpApp {
    private Session session;
    private Appointment appData;
    HealthHelpDao  helpDao;
    HealthHelpApp item;

    private HealthHelpApp() {
    }

    /**
     * Creates a new instance of {@link ScoreKeeperGame} with the provided {@link Session} and
     * {@link ScoreKeeperGameData}.
     * <p>
     * To create a new instance of {@link ScoreKeeperGameData}, see
     * {@link ScoreKeeperGameData#newInstance()}
     * 
     * @param session
     * @param gameData
     * @return
     * @see ScoreKeeperGameData#newInstance()
     */
    public static HealthHelpApp newInstance(Session session, Appointment appData) {
    	HealthHelpApp app = new HealthHelpApp();
    	app.setSession(session);
    	app.setAppData(appData);
        return app;
    }

    protected void setSession(Session session) {
        this.session = session;
    }

    protected Session getSession() {
        return session;
    }

    protected Appointment getAppData() {
        return appData;
    }

   	/**
	 * @param appData the appData to set
	 */
	public void setAppData(Appointment appData) {
		this.appData = appData;
	}
	
	 public boolean hasAppointment() {
	        return !appData.getAppointmentDate().isEmpty();
	    }

	 
//	 public Appointment getAppointmentDate(Session session){
//		item= helpDao.getAppointment(session);
//		return item.getAppData() ;
//		 }
	 public void addPatient(String patientName) {
		 appData.setPtFrstNm(patientName);
	    }
	 
//	 public void savePatientAppointment(Session session,String patientName){
//	        Appointment patientAppData =addAppointmentForPatient(patientName,session.getSessionId());
//	        item.setAppData(patientAppData);
//	        helpDao.saveHealthHelpApp(item);
//		}
	 
	 
	 public Appointment addAppointmentForPatient(String patientName, String id) {
	       
	        appData.setPtFrstNm(patientName);
	        appData.setAppId(id);
	        appData.setAppointmentDate("11th mar 2017");
	        appData.setAppointmentDesc("monthly check up");
	        appData.setAppointmentType("Regular");
	        appData.setDoctorId("07"); 
	        appData.setPatientId("113");
	        appData.setPtDob("7th Jun 2001");
			return appData;
	    }
	
}
