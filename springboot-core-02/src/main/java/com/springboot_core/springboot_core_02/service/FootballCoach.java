package com.springboot_core.springboot_core_02.service;

import com.springboot_core.springboot_core_02.Coach;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
//@Lazy
public class FootballCoach implements Coach {

    public FootballCoach(){
        System.out.println("In Constructor : " + getClass().getSimpleName());
    }

    @Override
    public String getWorkoutDetails() {
        return "Practice dribbling and shooting for 60 mins";
    }

    @Override
    public int getCoachId() {
        return 102;
    }
}

