package com.clubmember.fees.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberFeesEntityResponseModel implements Serializable{

	


	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String memberId;
	
	
	private Integer year;
	
	
	private Double janFees;
	
	
	private Double febFees;
	
	
	private Double marchFees;
	
	
	private Double aprilFees;
	
	
	private Double mayFees;
	
	
	private Double juneFees;
	
	
	private Double julyFees;
	
	
	private Double augFees;
	
	
	private Double septFees;
	
	
	private Double octFees;
	
	
	private Double novFees;
	
	
	private Double decFees;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getJanFees() {
		return janFees;
	}

	public void setJanFees(Double janFees) {
		this.janFees = janFees;
	}

	public Double getFebFees() {
		return febFees;
	}

	public void setFebFees(Double febFees) {
		this.febFees = febFees;
	}

	public Double getMarchFees() {
		return marchFees;
	}

	public void setMarchFees(Double marchFees) {
		this.marchFees = marchFees;
	}

	public Double getAprilFees() {
		return aprilFees;
	}

	public void setAprilFees(Double aprilFees) {
		this.aprilFees = aprilFees;
	}

	public Double getMayFees() {
		return mayFees;
	}

	public void setMayFees(Double mayFees) {
		this.mayFees = mayFees;
	}

	public Double getJuneFees() {
		return juneFees;
	}

	public void setJuneFees(Double juneFees) {
		this.juneFees = juneFees;
	}

	public Double getJulyFees() {
		return julyFees;
	}

	public void setJulyFees(Double julyFees) {
		this.julyFees = julyFees;
	}

	public Double getAugFees() {
		return augFees;
	}

	public void setAugFees(Double augFees) {
		this.augFees = augFees;
	}

	public Double getSeptFees() {
		return septFees;
	}

	public void setSeptFees(Double septFees) {
		this.septFees = septFees;
	}

	public Double getOctFees() {
		return octFees;
	}

	public void setOctFees(Double octFees) {
		this.octFees = octFees;
	}

	public Double getNovFees() {
		return novFees;
	}

	public void setNovFees(Double novFees) {
		this.novFees = novFees;
	}

	public Double getDecFees() {
		return decFees;
	}

	public void setDecFees(Double decFees) {
		this.decFees = decFees;
	}
	
	
	
	

	
}
