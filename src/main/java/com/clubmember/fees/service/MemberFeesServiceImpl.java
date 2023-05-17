package com.clubmember.fees.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubmember.fees.entity.FeesID;
import com.clubmember.fees.entity.MemberFeesEntity;
import com.clubmember.fees.exception.ResourceNotFoundException;
import com.clubmember.fees.repository.MemberFeesRepository;


@Service
public class MemberFeesServiceImpl implements MemberFeesService{
	
	
	@Autowired
	private MemberFeesRepository memberFeesRepository;

	@Override
	public MemberFeesEntity saveOrUpdateMemberFees(MemberFeesEntity memberFeesEntity) {
		// TODO Auto-generated method stub
		return memberFeesRepository.saveAndFlush(memberFeesEntity);
	}

	@Override
	public List<MemberFeesEntity> getMemberFeesDetailsByMemberID(String memberID) {
		// TODO Auto-generated method stub
		
		
		return memberFeesRepository.getMemberFeesDetailsByMemberID(memberID)
				.orElseThrow(() -> new ResourceNotFoundException("MemberId: " + memberID + " does not exist"));
		
	}

	@Override
	public MemberFeesEntity getMemberFeesEntityByMemberIDAndYear(String memberId, Integer year) {
		// TODO Auto-generated method stub

		FeesID feesId = new FeesID();
		feesId.setMemberId(memberId);
		feesId.setYear(year);

		return memberFeesRepository.findById(feesId).orElseThrow(() -> new ResourceNotFoundException(
				"MemberId: " + memberId + "AND " + year + " combination does not exist"));
	}

	@Override
	public MemberFeesEntity getMemberFeesDetailsForLastYearByMemberID(String memberId) {
		// TODO Auto-generated method stub
		/*
		return memberFeesRepository.getMemberFeesDetailsForLastYearByMemberID(memberId)
				.orElseThrow(() -> new ResourceNotFoundException("MemberId: " + memberId + " does not exist"));
				*/
		
		Optional<MemberFeesEntity> memberFeesEntityOptional = memberFeesRepository
				.getMemberFeesDetailsForLastYearByMemberID(memberId);
		if (memberFeesEntityOptional.isPresent()) {
			return memberFeesEntityOptional.get();
		} else {
			return null;
		}
	}

	@Override
	public void saveMemberFeesStructure(List<MemberFeesEntity> feesList) {
		// TODO Auto-generated method stub
		memberFeesRepository.saveAll(feesList);
	}

	@Override
	public Integer getMaxYearByMemberId(String memberId) {
		// TODO Auto-generated method stub
		Optional<BigDecimal> maxYearOptional = memberFeesRepository.getMaxYearByMemberId(memberId);
		
		return maxYearOptional.isPresent()?maxYearOptional.get().intValue():0;
		
	}

}
