package com.springboot_core.springboot_core_02.service;

import com.springboot_core.springboot_core_02.Coach;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CricketCoach implements Coach {

    public CricketCoach(){
        System.out.println("In Constructor : " + getClass().getSimpleName());
    }




    @Override
    public String getWorkoutDetails() {
        return "Play CoverDrive for 45 mins";
    }

    @Override
    public int getCoachId() {
        return 101;
    }
    //    //defining init method for bean
//    @PostConstruct
//    public void doStartupMethod(){
//        System.out.println("Doing Startup Methods : " + getClass().getSimpleName());
//    }
//    //defining destroy method for bean
//    @PreDestroy
//    public void doCleanupMethod(){
//        System.out.println("Doing Cleanup Methods : " + getClass().getSimpleName());
//    }
}
