package com.springboot_core.springboot_core_02.service;

import com.springboot_core.springboot_core_02.Coach;
import org.springframework.stereotype.Component;

@Component
public class BasketballCoach implements Coach {

    public BasketballCoach(){
        System.out.println("In Constructor : " + getClass().getSimpleName());
    }
    @Override
    public String getWorkoutDetails() {
        return "Practice free throws and dribbling for 50 mins";
    }

    @Override
    public int getCoachId() {
        return 104;
    }
}

