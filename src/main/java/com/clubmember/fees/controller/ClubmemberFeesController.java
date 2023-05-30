package com.clubmember.fees.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clubmember.fees.entity.FeesID;
import com.clubmember.fees.entity.MemberFeesEntity;
import com.clubmember.fees.exception.ResourceNotFoundException;
import com.clubmember.fees.mapper.MemberFeesEntityResponseMapper;
import com.clubmember.fees.model.MemberFeesAmountModel;
import com.clubmember.fees.model.MemberFeesEntityResponseModel;
import com.clubmember.fees.model.MemberFeesResponseModel;
import com.clubmember.fees.service.MemberFeesService;
import com.clubmember.fees.util.MemberFeesUtil;



@RestController
@RequestMapping("/club/memberfees")
public class ClubmemberFeesController {

	
	
	@Autowired
	private MemberFeesService memberFeesService;
	
	@Autowired
	private MemberFeesEntityResponseMapper memberFeesEntityResponseMapper;
	
	@Autowired
	private MemberFeesUtil memberFeesUtil;
	
	@Value("${club.member.per.month.fees}")
	private Double feesPerMonth;
	
	public static final String SUCCESS="SUCCESS";
	public static final String SUCCESSCODE = "200";
	
	public static final String FAILURE="FAILURE";
	public static final String FAILURECODE = "400";
	
	//MemberFeesResponseModel
	@GetMapping(value = "/getMemberFees/{memberId}",produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MemberFeesResponseModel> getMemberFees(@PathVariable("memberId") String memberId) 
	{
		MemberFeesResponseModel feesResp = new MemberFeesResponseModel();

		List<MemberFeesEntity> memberFeesHistoryDetailsList = memberFeesService
				.getMemberFeesDetailsByMemberID(memberId);
		if (!memberFeesHistoryDetailsList.isEmpty()) {
			List<MemberFeesEntityResponseModel> memberFeesList = memberFeesHistoryDetailsList.stream()
					.map(mem -> memberFeesEntityResponseMapper.getMemberFeesResponseModel(mem))
					.collect(Collectors.toList());
			feesResp.setMemberFeesEntityList(memberFeesList);
			feesResp.setStatusCode(SUCCESSCODE);
			feesResp.setStatusMessage(SUCCESS);
		}else {
			feesResp.setStatusCode(FAILURECODE);
			feesResp.setStatusMessage(FAILURE);
			return new ResponseEntity<>(feesResp, null, HttpStatus.NOT_FOUND);
			
		}

		return new ResponseEntity<>(feesResp, null, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/saveMemberFeesMultipleYear/", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> saveMemberFeesMultipleYear(@RequestBody MemberFeesAmountModel memberFees) {
		memberFeesService.saveMemberFeesStructure(
				memberFeesUtil.getMemberFessEntityAsList(memberFees.getMemberId(), memberFees.getMemberFeesAmount()));

		System.out.println("::: POST: saveMemberFeesMultipleYear() :::");
		return new ResponseEntity<>(SUCCESS, null, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping(value = "/saveMemberFeesSingleYear/{memberId}/{feesamount}/{year}",produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> saveMemberFees(@PathVariable("memberId") String memberId,@PathVariable("feesamount") Double feesamount,@PathVariable("year") Integer year) 
	{
		String resp="";
		if(feesamount>=120)
		{
			
			
			MemberFeesEntity memberFeesEntity = new MemberFeesEntity();
			memberFeesEntity.setMemberId(memberId);
			memberFeesEntity.setYear(year);
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
			
			MemberFeesEntity persistedOb = memberFeesService.saveOrUpdateMemberFees(memberFeesEntity);
			if(null!=persistedOb && persistedOb.getMemberId()!=null)
			{
				resp="Data Saved Successfully";
			}else {resp="Data Not Saved. Please try again";}
		}
		
		return new ResponseEntity<>(resp, null, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getMemberFeesByMemberIdAndYear/{memberId}/{year}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MemberFeesEntityResponseModel> getMemberFeesByMemberIdAndYear(
			@PathVariable("memberId") String memberId, @PathVariable("year") Integer year) {

		MemberFeesEntity respMemberFeesEntity = memberFeesService.getMemberFeesEntityByMemberIDAndYear(memberId, year);
		MemberFeesEntityResponseModel respModel = memberFeesEntityResponseMapper
				.getMemberFeesResponseModel(respMemberFeesEntity);
		return new ResponseEntity<>(respModel, null, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getMemberFeesForLastYearByMemberId/{memberId}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MemberFeesEntityResponseModel> getMemberFeesForLastYearByMemberId(
			@PathVariable("memberId") String memberId) {

		
		MemberFeesEntityResponseModel respModel = memberFeesEntityResponseMapper
				.getMemberFeesResponseModel(memberFeesService.getMemberFeesDetailsForLastYearByMemberID(memberId));;
		return new ResponseEntity<>(respModel, null, HttpStatus.OK);
	}
	
	
	//Following method is useful ....**** Important
	@GetMapping(value = "/getMemberFeesByYearRange2/{memberId}/{startingYear}/{endYear}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MemberFeesEntityResponseModel> getMemberFeesByYearRange2(
			@PathVariable("memberId") String memberId, @PathVariable("startingYear") Integer startingYear,
			@PathVariable("endYear") Integer endYear) throws ResourceNotFoundException,Exception {
		System.out.println(":::Inside of Testing Completable Future:::");
		CompletableFuture<MemberFeesEntity> memberFeesEntityCompletableFuture = memberFeesUtil
				.getMemberFeesDetailsByYearAsynchronusly(memberId, startingYear);
		MemberFeesEntity memberFeesEntity = new MemberFeesEntity();
		try {
			memberFeesEntity = memberFeesEntityCompletableFuture.get();
		} catch(ResourceNotFoundException re)
		{
			throw new ResourceNotFoundException("memberID/Year Combination not exist. Please enter correct value");
		}
		catch (Exception e) {
			throw new Exception("Some Error Occured. Please try after some time.");
		}

		return new ResponseEntity<>(memberFeesEntityResponseMapper.getMemberFeesResponseModel(memberFeesEntity), null,
				HttpStatus.OK);
	}
	
	
	

	@GetMapping(value = "/getMemberFeesByYearRange/{memberId}/{startingYear}/{endYear}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MemberFeesResponseModel> getMemberFeesByYearRange(
			@PathVariable("memberId") String memberId, @PathVariable("startingYear") Integer startingYear,
			@PathVariable("endYear") Integer endYear){
		System.out.println(":::Inside of Testing Completable Future:::");
		List<MemberFeesEntityResponseModel> MemberFeesEntityList = new ArrayList<>();
		MemberFeesResponseModel memberFeesResponseModel = new MemberFeesResponseModel();
		System.out.println("test1");
		Integer maxFeesYear = memberFeesService.getMaxYearByMemberId(memberId);
		System.out.println("test2");
		
		CompletableFuture<MemberFeesEntity> memberFeesEntityCompletableFuture_startingYear = new CompletableFuture<MemberFeesEntity>();
		
		CompletableFuture<MemberFeesEntity> memberFeesEntityCompletableFuture_midYear1 = new CompletableFuture<MemberFeesEntity>();
		CompletableFuture<MemberFeesEntity> memberFeesEntityCompletableFuture_midYear2 = new CompletableFuture<MemberFeesEntity>();
		
		CompletableFuture<MemberFeesEntity> memberFeesEntityCompletableFuture_endYear = new CompletableFuture<MemberFeesEntity>();
		
		
		memberFeesEntityCompletableFuture_startingYear = memberFeesUtil
				.getMemberFeesDetailsByYearAsynchronusly(memberId, startingYear);
		
		if(endYear<=maxFeesYear) {
		memberFeesEntityCompletableFuture_endYear = memberFeesUtil
				.getMemberFeesDetailsByYearAsynchronusly(memberId, endYear);
		}
		System.out.println("********************** TEST 1 **************");
		
		if((endYear>startingYear) && (endYear<=maxFeesYear) && (maxFeesYear>0))
		{
			int nextYear1 = startingYear+1;
			
			if(nextYear1<maxFeesYear) {
				memberFeesEntityCompletableFuture_midYear1 = memberFeesUtil
					.getMemberFeesDetailsByYearAsynchronusly(memberId, nextYear1);
			}
			System.out.println("********************** TEST 11 **************  ");
            int nextYear2 = nextYear1+1;
			
			if(nextYear2<maxFeesYear) {
				memberFeesEntityCompletableFuture_midYear2 = memberFeesUtil
					.getMemberFeesDetailsByYearAsynchronusly(memberId, nextYear2);
			}
			System.out.println("********************** TEST 111 **************  "+startingYear+" / "+endYear+" / "+nextYear1+" / "+nextYear2);;
			
		}
		MemberFeesEntity memberFeesEntity = new MemberFeesEntity();
		try {
			System.out.println("********************** TEST 12 ************** ");
			CompletableFuture
					.allOf(memberFeesEntityCompletableFuture_startingYear, memberFeesEntityCompletableFuture_endYear,
							memberFeesEntityCompletableFuture_midYear1,memberFeesEntityCompletableFuture_midYear2)
					.join();
			System.out.println("********************** TEST 13 **************");
			if (null != memberFeesEntityCompletableFuture_startingYear) {
				MemberFeesEntityList.add(memberFeesEntityResponseMapper
						.getMemberFeesResponseModel(memberFeesEntityCompletableFuture_startingYear.get()));

			}
			if (null != memberFeesEntityCompletableFuture_endYear) {
				MemberFeesEntityList.add(memberFeesEntityResponseMapper
						.getMemberFeesResponseModel(memberFeesEntityCompletableFuture_endYear.get()));

			}
			
			if (null != memberFeesEntityCompletableFuture_midYear1) {
				MemberFeesEntityList.add(memberFeesEntityResponseMapper
						.getMemberFeesResponseModel(memberFeesEntityCompletableFuture_midYear1.get()));

			}
			
			if (null != memberFeesEntityCompletableFuture_midYear2) {
				MemberFeesEntityList.add(memberFeesEntityResponseMapper
						.getMemberFeesResponseModel(memberFeesEntityCompletableFuture_midYear2.get()));

			}
			
			memberFeesResponseModel.setMemberFeesEntityList(MemberFeesEntityList);
			memberFeesResponseModel.setStatusCode("200");
			memberFeesResponseModel.setStatusMessage("Data Fetched Successfully");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(memberFeesResponseModel, null,
				HttpStatus.OK);
	}
	
	
	
}
