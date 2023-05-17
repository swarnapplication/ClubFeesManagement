package com.clubmember.fees.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberFeesResponseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	List<MemberFeesEntityResponseModel> MemberFeesEntityList;
	private String statusCode;
	private String statusMessage;


	public List<MemberFeesEntityResponseModel> getMemberFeesEntityList() {
		return MemberFeesEntityList;
	}


	public void setMemberFeesEntityList(List<MemberFeesEntityResponseModel> memberFeesEntityList) {
		MemberFeesEntityList = memberFeesEntityList;
	}


	public String getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public String getStatusMessage() {
		return statusMessage;
	}


	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	
	
	
	

}
