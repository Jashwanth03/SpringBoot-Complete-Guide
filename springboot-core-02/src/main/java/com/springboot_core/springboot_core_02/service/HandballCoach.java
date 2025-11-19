package com.springboot_core.springboot_core_02.service;

import com.springboot_core.springboot_core_02.Coach;


//without annotation error occurs , so create a 3rd party file
public class HandballCoach implements Coach {

    public HandballCoach(){
        System.out.println("In Constructor : " + getClass().getSimpleName());
    }

    public String getWorkoutDetails() {
        return "Practice Handball shooting for 1 hour";
    }

    public int getCoachId() {
        return 102;
    }
}
