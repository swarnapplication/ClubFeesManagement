package com.clubmember.fees.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clubmember.fees.entity.FeesID;
import com.clubmember.fees.entity.MemberFeesEntity;

public interface MemberFeesRepository extends JpaRepository<MemberFeesEntity, FeesID>{

	
	
	@Query(value="SELECT MemberID,YEAR,JANUARY,FEBRUARY,MARCH,APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,"
			+ "NOVEMBER,DECEMBER FROM club_member_fees "
			+ "WHERE MemberID=?1",nativeQuery=true)
	public Optional<List<MemberFeesEntity>> getMemberFeesDetailsByMemberID(String memberId);
	
	
	@Query(value="SELECT MemberID,YEAR,JANUARY,FEBRUARY,MARCH,APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,"
			+ "NOVEMBER,DECEMBER FROM club_member_fees "
			+ "WHERE MemberID=?1 AND YEAR = (SELECT MAX(YEAR) FROM club_member_fees WHERE MemberID=?1)",nativeQuery=true)
	public Optional<MemberFeesEntity> getMemberFeesDetailsForLastYearByMemberID(String memberId);
	
	@Query(value="SELECT MAX(YEAR) FROM club_member_fees WHERE MemberID=?1",nativeQuery=true)
	public Optional<BigDecimal> getMaxYearByMemberId(String memberId);
	
	
}
