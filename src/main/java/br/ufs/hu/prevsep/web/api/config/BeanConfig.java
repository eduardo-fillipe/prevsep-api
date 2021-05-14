package br.ufs.hu.prevsep.web.api.config;

import br.ufs.hu.prevsep.web.api.service.HelloWorldService;
import br.ufs.hu.prevsep.web.api.service.HelloWorldServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public HelloWorldService getHelloWorldService() {
        return new HelloWorldServiceImpl();
    }

}
