package com.full.dto;

import org.springframework.stereotype.Component;

@Component
public class SignUpDto {
	@Override
	public String toString() {
		return "SignUpDto [USERNAME=" + USERNAME + ", EMAIL=" + EMAIL + ", PASSWORD=" + PASSWORD + ", CONFIRMPASSWORD="
				+ CONFIRMPASSWORD + ", customerId=" + customerId + "]";
	}
	private String USERNAME;
	private String EMAIL;
	private String PASSWORD;
	private String CONFIRMPASSWORD;
	private String customerId;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getCONFIRMPASSWORD() {
		return CONFIRMPASSWORD;
	}
	public void setCONFIRMPASSWORD(String cONFIRMPASSWORD) {
		CONFIRMPASSWORD = cONFIRMPASSWORD;
	}

	

}
