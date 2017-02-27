package storage;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



@DynamoDBTable(tableName="appointment-table")
public class Appointment {
	private String appId;
	private String appointmentDate;
	private String appointmentType;
	private String appointmentDesc;
	private String doctorId;
	private String patientId;
	private String ptDob;
	private String ptFrstNm;
	
	public Appointment(){
		
	}
	
	public static Appointment newInstance(){
		Appointment newInstance = new Appointment();
		newInstance.setAppId(new String());
		newInstance.setAppointmentDate(new String());
		newInstance.setAppointmentDesc(new String());
		newInstance.setAppointmentType(new String());
		newInstance.setDoctorId(new String());
		newInstance.setPatientId(new String());
		newInstance.setPtDob(new String());
		newInstance.setPtFrstNm(new String());
		return newInstance;
		
	}
	
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
	
	/**
	 * @return the appointmentDate
	 */
	@DynamoDBAttribute(attributeName="Appointment Date")
	public String getAppointmentDate() {
		return appointmentDate;
	}
	/**
	 * @param appointmentDate the appointmentDate to set
	 */
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	/**
	 * @return the appointmentType
	 */
	@DynamoDBAttribute(attributeName="Appointment Type")
	public String getAppointmentType() {
		return appointmentType;
	}
	/**
	 * @param appointmentType the appointmentType to set
	 */
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	/**
	 * @return the appointmentDesc
	 */
	@DynamoDBAttribute(attributeName="Appointment Desc")
	public String getAppointmentDesc() {
		return appointmentDesc;
	}
	/**
	 * @param appointmentDesc the appointmentDesc to set
	 */
	public void setAppointmentDesc(String appointmentDesc) {
		this.appointmentDesc = appointmentDesc;
	}
	/**
	 * @return the doctorId
	 */
	@DynamoDBAttribute(attributeName="Doctor Id")
	public String getDoctorId() {
		return doctorId;
	}
	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * @return the patientId
	 */
	@DynamoDBAttribute(attributeName="Patient Id")
	public String getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the ptDob
	 */
	@DynamoDBAttribute(attributeName="PtDob")
	public String getPtDob() {
		return ptDob;
	}
	/**
	 * @param ptDob the ptDob to set
	 */
	public void setPtDob(String ptDob) {
		this.ptDob = ptDob;
	}
	/**
	 * @return the ptFrstNm
	 */
	@DynamoDBAttribute(attributeName="PtFrstNm")
	public String getPtFrstNm() {
		return ptFrstNm;
	}
	/**
	 * @param ptFrstNm the ptFrstNm to set
	 */
	public void setPtFrstNm(String ptFrstNm) {
		this.ptFrstNm = ptFrstNm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Appointment [ appointmentDate=" + appointmentDate + ", appointmentType="
				+ appointmentType + ", appointmentDesc=" + appointmentDesc + ", doctorId=" + doctorId + ", patientId="
				+ patientId + ", ptDob=" + ptDob + ", ptFrstNm=" + ptFrstNm + "]";
	}

	
	
}
