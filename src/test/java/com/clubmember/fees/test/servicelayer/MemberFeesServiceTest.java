package com.clubmember.fees.test.servicelayer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.clubmember.fees.entity.MemberFeesEntity;
import com.clubmember.fees.repository.MemberFeesRepository;
import com.clubmember.fees.service.MemberFeesServiceImpl;
//import org.mockito.BDD


@RunWith(MockitoJUnitRunner.class)
public class MemberFeesServiceTest {
	
	@Mock
	private MemberFeesRepository memberFeesRepository;
	
	@InjectMocks
	private MemberFeesServiceImpl memberService;
	
	private MemberFeesEntity memberFeesEntity;
	
	@Before
	public void setup()
	{
		memberFeesEntity = new MemberFeesEntity();
		memberFeesEntity.setMemberId("KCATEST-1001");
		memberFeesEntity.setYear(2023);
		memberFeesEntity.setJanFees(20.0);
		memberFeesEntity.setDecFees(20.0);
	}
	
	
	//@DisplayName("JUnit Test for SaveOrUpdate method")
	@Test
	public void saveOrUpdateMemberFeesTest()
	{
		
		when(memberFeesRepository.saveAndFlush(memberFeesEntity)).thenReturn(memberFeesEntity);
		
		MemberFeesEntity memFeesResult = memberService.saveOrUpdateMemberFees(memberFeesEntity);
		System.out.println("Data: "+memFeesResult);
		assertThat(memFeesResult).isNotNull();
		assertEquals("KCATEST-1001", memFeesResult.getMemberId());
	}

}
