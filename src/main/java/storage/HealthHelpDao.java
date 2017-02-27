package storage;




import com.amazon.speech.speechlet.Session;

/**
 * Contains the methods to interact with the persistence layer for ScoreKeeper in DynamoDB.
 */
public class HealthHelpDao {
    private final HealthHelpDynamoDbClient dynamoDbClient;
    private Session session;

    public HealthHelpDao(HealthHelpDynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    /**
     * Reads and returns the {@link ScoreKeeperGame} using user information from the session.
     * <p>
     * Returns null if the item could not be found in the database.
     * 
     * @param session
     * @return
     */
//    public HealthHelpApp getAppointment(Session session) {
//    	HealthHelpUserDataItem item = new HealthHelpUserDataItem();
//        item.setAppId(session.getUser().getUserId());
//
//        item = dynamoDbClient.loadItem(item);
//
//        if (item == null) {
//            return null;
//        }
//
//        return HealthHelpApp.newInstance(session, item.getAppData());
//    }
    
    
    public String fetchAppointment(String id){
    	Appointment app = new Appointment();
    	app.setAppId(id);
    	app = dynamoDbClient.loadItem(app);
    	if(app == null){
    		return null;
    	}
    	return app.getAppointmentDate();
    }

    /**
     * Saves the {@link ScoreKeeperGame} into the database.
     * 
     * @param game
     */
    public void saveHealthHelpApp(String patientFrstName, String dob) {

    	Appointment app = new Appointment();
    	Patient pt = new Patient();
    	app.setAppId("5");
    	app.setAppointmentDate("12th mar 2017");
    	app.setAppointmentDesc("Physical checkup");
    	app.setAppointmentType("Regular");
    	app.setDoctorId("05");
    	app.setPatientId("132");
    	app.setPtDob(dob);
    	app.setPtFrstNm(patientFrstName);
//    	pt.setptFrstNm(patientFrstName);
//    	pt.setptDob(dob);
//    	pt.setpatientId(Integer.parseInt(app.getPatientId()));
        dynamoDbClient.saveItem(app);
       // dynamoDbClient.saveItem1(pt);
    }

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	
}
