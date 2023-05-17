package com.clubmember.fees.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.clubmember.fees.entity.MemberFeesEntity;
import com.clubmember.fees.mapper.MemberFeesEntityResponseMapper;
import com.clubmember.fees.model.MemberFeesEntityResponseModel;
import com.clubmember.fees.service.MemberFeesService;

@Component
public class MemberFeesUtil {
	
	@Value("${club.member.per.month.fees}")
	private Double feesPerMonth;

	@Autowired
	private MemberFeesService memberFeesService;
	
	@Autowired
	private MemberFeesEntityResponseMapper memberFeesEntityResponseMapper;
	
	public MemberFeesEntityResponseModel getMemberFeesDetailsForLastYearByMemberID(String memberId) {
		return memberFeesEntityResponseMapper
				.getMemberFeesResponseModel(memberFeesService.getMemberFeesDetailsForLastYearByMemberID(memberId));
	}
	
	
	public List<MemberFeesEntity> getMemberFessEntityAsList(String memberId,Double totalAmount)
	{
		List<MemberFeesEntity> feesDataList = new ArrayList<>();
		
		double limityear_calculated=totalAmount/(12*feesPerMonth);
		int y=(int)limityear_calculated;
		
		Integer limitYear = Integer.parseInt(""+y);
		
		Integer startingYear = 0;
		
		MemberFeesEntity memberFeesEntityForMaxYear = memberFeesService.getMemberFeesDetailsForLastYearByMemberID(memberId);
		
		//memberFeesEntityForMaxYear == null?startingYear = 2023:startingYear = memberFeesEntityForMaxYear.getYear();
		
		if(null==memberFeesEntityForMaxYear)
		{
			startingYear = 2023;
		}
		else
		{
			startingYear = memberFeesEntityForMaxYear.getYear()+1;
		}
		
		Integer nextYear_in_loop = startingYear;
		System.out.println("********Test3: "+nextYear_in_loop);
		
		for(Integer i=1;i<=limitYear;i++)
		{
			System.out.println("Fees Year: "+nextYear_in_loop);
			MemberFeesEntity memberFeesEntity = new MemberFeesEntity();
			memberFeesEntity.setMemberId(memberId);
			memberFeesEntity.setYear(nextYear_in_loop);
			memberFeesEntity.setJanFees(feesPerMonth);
			memberFeesEntity.setFebFees(feesPerMonth);
			memberFeesEntity.setMarchFees(feesPerMonth);
			memberFeesEntity.setAprilFees(feesPerMonth);
			memberFeesEntity.setMayFees(feesPerMonth);
			memberFeesEntity.setJuneFees(feesPerMonth);
			memberFeesEntity.setJulyFees(feesPerMonth);
			memberFeesEntity.setAugFees(feesPerMonth);
			memberFeesEntity.setSeptFees(feesPerMonth);
			memberFeesEntity.setOctFees(feesPerMonth);
			memberFeesEntity.setNovFees(feesPerMonth);
			memberFeesEntity.setDecFees(feesPerMonth);
			feesDataList.add(memberFeesEntity);
			
			nextYear_in_loop = nextYear_in_loop+1;
		}
		
		
		
		
		return feesDataList;
	}
	
	
	public CompletableFuture<MemberFeesEntity> getMemberFeesDetailsByYearAsynchronusly(String memberId,Integer year)
	{
		CompletableFuture<MemberFeesEntity> feesDetailsByYear = CompletableFuture.supplyAsync(new Supplier<MemberFeesEntity>() {
			
			public MemberFeesEntity get() {
				return memberFeesService.getMemberFeesEntityByMemberIDAndYear(memberId, year);
			}
		});
		
		return feesDetailsByYear;
	}
}
