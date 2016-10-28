/**
 * 
 */
package com.niro.test.repository;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.niro.domain.User;
import com.niro.domain.UserPersistentRememberMeToken;
import com.niro.repository.RememberMePersistentTokenRepository;
import com.niro.repository.SimplePersistentTokenRepository;
import com.niro.repository.UserRepository;

/**
 * This class represents the {@link RememberMePersistentTokenRepository} test class. * 
 * @author Olivier nirina
 * @since 1.0
 */
public class RememberMePersistentTokenRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    @Qualifier("rememberMePersistentTokenRepository")
    private PersistentTokenRepository persistentTokenRepository;
    
    @Autowired
    private SimplePersistentTokenRepository simplePersistentTokenRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void createNewToken(){
        User createdUSer = createUser();
        PersistentRememberMeToken token = new PersistentRememberMeToken(createdUSer.getUsername(), RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10), new Date());
        persistentTokenRepository.createNewToken(token);
        persistentTokenRepository.removeUserTokens(token.getUsername());
        userRepository.delete(createdUSer);
    }

    @Test
    public void updateToken(){
        User createdUSer = createUser();
        UserPersistentRememberMeToken createdToken = createToken(createdUSer);
        simplePersistentTokenRepository.findOneByUser(createdToken.getUser()).ifPresent(foundUser -> persistentTokenRepository.updateToken(foundUser.getSeries(), RandomStringUtils.randomAlphanumeric(10), new Date()));
        simplePersistentTokenRepository.delete(createdToken);
        userRepository.delete(createdUSer);
    }

    @Test
    public void getTokenForSeries(){
        User createdUSer = createUser();
        UserPersistentRememberMeToken createdToken = createToken(createdUSer);
        PersistentRememberMeToken token = persistentTokenRepository.getTokenForSeries(createdToken.getSeries());
        Assert.assertNotNull(token);
        persistentTokenRepository.removeUserTokens(token.getUsername());
        userRepository.delete(createdUSer);
    }
    
    private UserPersistentRememberMeToken createToken(User user){                
        UserPersistentRememberMeToken token = new UserPersistentRememberMeToken();
        token.setDate(new Date());
        token.setSeries(RandomStringUtils.randomAlphanumeric(10));
        token.setTokenValue(RandomStringUtils.randomAlphanumeric(10));        
        token.setUser(user);
        UserPersistentRememberMeToken createdToken = simplePersistentTokenRepository.save(token);
        Assert.assertNotNull(createdToken);
        Assert.assertNotNull(createdToken.getId());
        return createdToken;
    }
    
    private User createUser(){
        User user = new User();
        user.setUsername("user1");
        User createdUSer = userRepository.save(user);        
        Assert.assertNotNull(createdUSer);
        Assert.assertNotNull(createdUSer.getId());
        return createdUSer;
    }
    
}
