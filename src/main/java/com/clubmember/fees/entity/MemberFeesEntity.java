package com.clubmember.fees.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="club_member_fees")
@IdClass(FeesID.class)
public class MemberFeesEntity {

	
	@Id
	@Column(name="memberID")
	private String memberId;
	
	@Id
	@Column(name="YEAR")
	private Integer year;
	
	@Column(name="JANUARY")
	private Double janFees;
	
	@Column(name="FEBRUARY")
	private Double febFees;
	
	@Column(name="MARCH")
	private Double marchFees;
	
	@Column(name="APRIL")
	private Double aprilFees;
	
	@Column(name="MAY")
	private Double mayFees;
	
	@Column(name="JUNE")
	private Double juneFees;
	
	@Column(name="JULY")
	private Double julyFees;
	
	@Column(name="AUGUST")
	private Double augFees;
	
	@Column(name="SEPTEMBER")
	private Double septFees;
	
	@Column(name="OCTOBER")
	private Double octFees;
	
	@Column(name="NOVEMBER")
	private Double novFees;
	
	@Column(name="DECEMBER")
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
