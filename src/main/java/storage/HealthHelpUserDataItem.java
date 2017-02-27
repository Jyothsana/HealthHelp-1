package storage;

import java.io.IOException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@DynamoDBTable(tableName = "appointment-table")
public class HealthHelpUserDataItem {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    Appointment app = new Appointment();

    private String appId;
    private Appointment appData;
	
  
    @DynamoDBHashKey(attributeName="AppId")
	public String getAppId() {
		return appId;
	}

    /**
	 * @param appId the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

    @DynamoDBAttribute(attributeName = "appData")
   @DynamoDBMarshalling(marshallerClass = HealthHelpDataMarshaller.class)
    public Appointment getAppData() {
        return appData;
    }

    public void setAppData(Appointment appData) {
        this.appData = appData;
    }

    /**
     * A {@link DynamoDBMarshaller} that provides marshalling and unmarshalling logic for
     * {@link ScoreKeeperGameData} values so that they can be persisted in the database as String.
     */
    public static class HealthHelpDataMarshaller implements
            DynamoDBMarshaller<Appointment> {

        
        @Override
        public Appointment unmarshall(Class<Appointment> clazz, String value) {
            try {
                return OBJECT_MAPPER.readValue(value, new TypeReference<Appointment>() {
                });
            } catch (Exception e) {
                throw new IllegalStateException("Unable to unmarshall app data value", e);
            }
        }

		@Override
		public String marshall(Appointment appData) {
			// TODO Auto-generated method stub
			try {
				return OBJECT_MAPPER.writeValueAsString(appData);
			}
			catch (JsonProcessingException e) {
                throw new IllegalStateException("Unable to marshall game data", e);
            }
			catch (IOException e) {
                throw new IllegalStateException("Unable to marshall app data", e);
            }
			
		}

    }
}
  
    
    /*void setAppointmentDate(getAppData().);
	setAppointmentType();
	setAppointmentDesc();
	setPatientId();
	setPtDob();
	setPtFrstNm();*/

	