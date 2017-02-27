package storage;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Patient")
public class Patient {
	private  int patientId;
	private String ptFrstNm;
	private String ptLtNm;
	private String ptEmail;
	private String ptDob;
	private String ptAddress;
	private int ptPhone;
	private int insId;

	/**
	 * @return the patientId
	 */
	@DynamoDBHashKey(attributeName="patientId")
	public int getpatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setpatientId(int patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the ptFrstNm
	 */
	@DynamoDBAttribute(attributeName="ptFrstNm")
	public String getptFrstNm() {
		return ptFrstNm;
	}
	/**
	 * @param ptFrstNm the ptFrstNm to set
	 */
	public void setptFrstNm(String ptFrstNm) {
		this.ptFrstNm = ptFrstNm;
	}
	/**
	 * @return the ptLtNm
	 */
	@DynamoDBAttribute(attributeName="ptLtNm")
	public String getptLtNm() {
		return ptLtNm;
	}
	/**
	 * @param ptLtNm the ptLtNm to set
	 */
	public void setptLtNm(String ptLtNm) {
		this.ptLtNm = ptLtNm;
	}
	/**
	 * @return the ptEmail
	 */
	@DynamoDBAttribute(attributeName="ptEmail")
	public String getptEmail() {
		return ptEmail;
	}
	/**
	 * @param ptEmail the ptEmail to set
	 */
	public void setptEmail(String ptEmail) {
		this.ptEmail = ptEmail;
	}
	/**
	 * @return the ptDob
	 */
	@DynamoDBAttribute(attributeName="ptDob")
	public String getptDob() {
		return ptDob;
	}
	/**
	 * @param ptDob the ptDob to set
	 */
	public void setptDob(String ptDob) {
		this.ptDob = ptDob;
	}
	/**
	 * @return the ptAddress
	 */
	@DynamoDBAttribute(attributeName="ptAddress")
	public String getptAddress() {
		return ptAddress;
	}
	/**
	 * @param ptAddress the ptAddress to set
	 */
	public void setptAddress(String ptAddress) {
		this.ptAddress = ptAddress;
	}
	/**
	 * @return the ptPhone
	 */
	@DynamoDBAttribute(attributeName="ptPhone")
	public int getptPhone() {
		return ptPhone;
	}
	/**
	 * @param ptPhone the ptPhone to set
	 */
	public void setptPhone(int ptPhone) {
		this.ptPhone = ptPhone;
	}
	/**
	 * @return the insId
	 */
	@DynamoDBAttribute(attributeName="InsId")
	public int getinsId() {
		return insId;
	}
	/**
	 * @param insId the insId to set
	 */
	public void setinsId(int insId) {
		this.insId = insId;
	}
	
}
