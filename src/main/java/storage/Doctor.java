package storage;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Doctor")
public class Doctor {
	private int doctorId;
	private String drFrstNm;
	private String drLtNm;
	private String drSpec;
	
	public Doctor(){
		
	}
	/**
	 * @return the doctorId
	 */
	@DynamoDBHashKey(attributeName="DoctorId")
	public int getDoctorId() {
		return doctorId;
	}
	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * @return the drFrstNm
	 */
	@DynamoDBAttribute(attributeName="DrFrstNm")
	public String getDrFrstNm() {
		return drFrstNm;
	}
	/**
	 * @param drFrstNm the drFrstNm to set
	 */
	public void setDrFrstNm(String drFrstNm) {
		this.drFrstNm = drFrstNm;
	}
	/**
	 * @return the drLtNm
	 */
	@DynamoDBAttribute(attributeName="DrLtNm")
	public String getDrLtNm() {
		return drLtNm;
	}
	/**
	 * @param drLtNm the drLtNm to set
	 */
	public void setDrLtNm(String drLtNm) {
		this.drLtNm = drLtNm;
	}
	/**
	 * @return the drSpec
	 */
	@DynamoDBAttribute(attributeName="DrSpec")
	public String getDrSpec() {
		return drSpec;
	}
	/**
	 * @param drSpec the drSpec to set
	 */
	public void setDrSpec(String drSpec) {
		this.drSpec = drSpec;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", drFrstNm=" + drFrstNm + ", drLtNm=" + drLtNm + ", drSpec=" + drSpec
				+ "]";
	}

}
