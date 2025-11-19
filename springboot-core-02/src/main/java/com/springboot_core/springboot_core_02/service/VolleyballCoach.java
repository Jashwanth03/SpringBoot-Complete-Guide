package com.springboot_core.springboot_core_02.service;

import com.springboot_core.springboot_core_02.Coach;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
//@Primary
public class VolleyballCoach implements Coach {

    public VolleyballCoach(){
        System.out.println("In Constructor : " + getClass().getSimpleName());
    }

    @Override
    public String getWorkoutDetails() {
        return "Practice serving and blocking drills for 40 mins";
    }

    @Override
    public int getCoachId() {
        return 103;
    }
}

