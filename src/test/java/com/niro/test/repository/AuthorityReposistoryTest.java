package com.niro.test.repository;

import com.niro.config.DatabaseConfiguration;
import com.niro.domain.Authority;
import com.niro.repository.AuthorityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @author Olivier nirina
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@Rollback
public class AuthorityReposistoryTest {

    private @Autowired
    AuthorityRepository authorityRepository;

    @Test
    public void testCreateAuthority(){
        Authority existingAuthority = authorityRepository.findOne("ROLE_ANONYMOUS");
        if(existingAuthority == null){
            Authority authority = new Authority();
            authority.setValue("ROLE_ANONYMOUS");
            existingAuthority = authorityRepository.save(authority);
        }

        Assert.assertNotNull(existingAuthority);
        Assert.assertNotNull(existingAuthority.getValue());
    }
}
