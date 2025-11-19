package com.springboot_core.springboot_core_02.config;


import com.springboot_core.springboot_core_02.Coach;
import com.springboot_core.springboot_core_02.service.HandballCoach;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportsConfig {
    @Bean("handball") //custom beanId
    public Coach handballCoach(){ //same as BeanId ==> @Qualifier("handballCoach")
        return new HandballCoach();
    }
}
