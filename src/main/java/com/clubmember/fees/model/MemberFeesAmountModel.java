package com.clubmember.fees.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberFeesAmountModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private Double memberFeesAmount;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Double getMemberFeesAmount() {
		return memberFeesAmount;
	}
	public void setMemberFeesAmount(Double memberFeesAmount) {
		this.memberFeesAmount = memberFeesAmount;
	}
	
	
}
