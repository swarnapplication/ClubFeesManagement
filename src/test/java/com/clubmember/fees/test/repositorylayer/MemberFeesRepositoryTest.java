package com.clubmember.fees.test.repositorylayer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.clubmember.fees.entity.MemberFeesEntity;
import com.clubmember.fees.repository.MemberFeesRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberFeesRepositoryTest {

	@Autowired
	private MemberFeesRepository memberFeesRepository;
	
	private MemberFeesEntity memberFeesEntity;
	
	@Before
    public void setup(){
		memberFeesEntity = new MemberFeesEntity();
		memberFeesEntity.setMemberId("REPOTEST-1001");
		memberFeesEntity.setYear(2023);
		memberFeesEntity.setJanFees(20.0);
		memberFeesEntity.setDecFees(20.0);
	}
	
	
	@Test
	public void saveOrUpdateMemberFeesTest()
	{
		MemberFeesEntity memberFeesEntity2 = new MemberFeesEntity();
		memberFeesEntity2.setMemberId("REPOTEST-1001");
		memberFeesEntity2.setYear(2023);
		memberFeesEntity2.setJanFees(20.0);
		memberFeesEntity2.setDecFees(20.0);
		
		System.out.println("test001");
		//when(memberFeesRepository.saveAndFlush(memberFeesEntity2)).thenReturn(memberFeesEntity2);
		System.out.println("test002");
		MemberFeesEntity savedMemberFees = memberFeesRepository.saveAndFlush(memberFeesEntity2);
		System.out.println(":::::: TEST under saveOrUpdateMemberFeesTest:::  "+savedMemberFees);
		
		assertThat(savedMemberFees).isNotNull();
		assertEquals("REPOTEST-1001", savedMemberFees.getMemberId());
        //assertThat(savedMemberFees.getMemberId()).equals(savedMemberFees);
		
	}
}
