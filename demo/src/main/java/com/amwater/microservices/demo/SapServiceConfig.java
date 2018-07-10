package com.amwater.microservices.demo;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amwater.rtc._000922.sapisu.buisnesstechnicalmasterdata.v5.AccountDetailQueryIBSYC;

@Configuration
public class SapServiceConfig {

    @Value("${sap.service.user}")
    private String username;

    @Value("${sap.service.password}")
    private String password;

    @Value("${service.address.accountDetailQuery}")
    private String accountDetailQueryServiceAddress;

    @Autowired
    private Bus bus;
    
    @Bean 
    public AccountDetailQueryIBSYC accountDetailQueryService() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(AccountDetailQueryIBSYC.class);
        jaxWsProxyFactoryBean.setAddress(accountDetailQueryServiceAddress);
        jaxWsProxyFactoryBean.setUsername(username);
        jaxWsProxyFactoryBean.setPassword(password);

        return (AccountDetailQueryIBSYC) jaxWsProxyFactoryBean.create();
    }
}
