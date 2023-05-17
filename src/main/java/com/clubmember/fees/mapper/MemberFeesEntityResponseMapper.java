package com.clubmember.fees.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import com.clubmember.fees.entity.MemberFeesEntity;
import com.clubmember.fees.model.MemberFeesEntityResponseModel;

@Component
@Mapper(componentModel = "spring")
public interface MemberFeesEntityResponseMapper {

	
	
@Mappings({
		
		@Mapping(target = "memberId",source = "memberInfo.memberId"),
		@Mapping(target = "year",source = "memberInfo.year"),
		@Mapping(target = "janFees",source = "memberInfo.janFees"),
		@Mapping(target = "febFees",source = "memberInfo.febFees"),
		@Mapping(target = "marchFees",source = "memberInfo.marchFees"),
		@Mapping(target = "aprilFees",source = "memberInfo.aprilFees"),
		@Mapping(target = "mayFees",source = "memberInfo.mayFees"),
		@Mapping(target = "juneFees",source = "memberInfo.juneFees"),
		@Mapping(target = "julyFees",source = "memberInfo.julyFees"),
		@Mapping(target = "augFees",source = "memberInfo.augFees"),
		@Mapping(target = "septFees",source = "memberInfo.septFees"),
		@Mapping(target = "octFees",source = "memberInfo.octFees"),
		@Mapping(target = "novFees",source = "memberInfo.novFees"),
		@Mapping(target = "decFees",source = "memberInfo.decFees")
		
		})
@BeanMapping(qualifiedByName = "memberFeesRespModelMap")	
public MemberFeesEntityResponseModel getMemberFeesResponseModel(MemberFeesEntity memberInfo);







}
