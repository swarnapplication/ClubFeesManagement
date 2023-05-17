package com.clubmember.fees.service;

import java.util.List;

import com.clubmember.fees.entity.MemberFeesEntity;

public interface MemberFeesService {

	public MemberFeesEntity saveOrUpdateMemberFees(MemberFeesEntity memberFeesEntity);
	public List<MemberFeesEntity> getMemberFeesDetailsByMemberID(String memberID);
	public MemberFeesEntity getMemberFeesEntityByMemberIDAndYear(String memberId,Integer year);
	public MemberFeesEntity getMemberFeesDetailsForLastYearByMemberID(String memberId);
	
	public void saveMemberFeesStructure(List<MemberFeesEntity> feesList);
	public Integer getMaxYearByMemberId(String memberId);
}
