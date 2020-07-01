package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.UserAccountRepo;
import com.oc6ad.climbingproject.services.impl.UserAccountServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class UserAccountServiceTest {

  /*  @TestConfiguration
    static class ServiceTestConfig{
        @Bean
        public UserAccountService userAccountService() {
            return new UserAccountServiceImpl();
        }
    }*/

    @Autowired
    private UserAccountService userAccountService;
    @MockBean
    private UserAccountRepo userAccountRepo;

    @Before
    public void setup() {
        Mockito.when(userAccountRepo.findByLogin(Mockito.anyString())).thenReturn(new UserAccount().setFirstName("Antoine"));
    }
    @Test
    void addUserAccount() {
        Assertions.assertThat(true).isEqualTo(true);
    }

    @Test
    void loadUserByLogin() {
        UserAccount userAccount = userAccountService.loadUserByLogin("login");
        Assertions.assertThat(userAccount).isNotNull();
        Assertions.assertThat(userAccount.getFirstName()).isEqualTo("Antoine");
    }
}