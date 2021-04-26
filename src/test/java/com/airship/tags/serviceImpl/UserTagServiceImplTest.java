package com.airship.tags.serviceImpl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.airship.tags.repository.UserTagRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTagServiceImplTest {
	
	@Autowired
	private UserTagServiceImpl userTagServiceImpl;
	
	@MockBean
	private UserTagRepository userTagServiceRepo;
	
	
//	@Test
//	public void should_return_a_when_call_cleanAddAndRemoveTags_function{
//		Set<String> add = Stream.of("a","b")
//				  .collect(Collectors.toCollection(HashSet::new));
//	}

}
