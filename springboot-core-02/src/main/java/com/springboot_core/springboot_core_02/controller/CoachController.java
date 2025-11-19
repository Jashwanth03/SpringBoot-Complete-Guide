package com.springboot_core.springboot_core_02.controller;

import com.springboot_core.springboot_core_02.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoachController{

    private Coach daCoach;
//    private Coach anotherCoach; //Used for bean Scope
//CONSTRUCTOR INJECTION INTERNALLY
//    Coach daCoach = new Coach();
//    CoachController daController = new CoachController(daCoach);
// this is constructor injection, which is done below

    //CODE STARTS HERE
    @Autowired
    public CoachController(@Qualifier("handball") Coach daCoach){
        System.out.println("In Constructor : " + getClass().getSimpleName());
        this.daCoach=daCoach;
    }

    @GetMapping("/dailyWorkout")
    public String getWorkoutDetails(){
        return daCoach.getWorkoutDetails();
    }

    @GetMapping("/coachId")
    public int getCoachId(){
        return daCoach.getCoachId();
    }

}
//BEAN SCOPE EXAMPLE CODE

//    @Autowired
//    public CoachController(@Qualifier("cricketCoach") Coach daCoach,
//                           @Qualifier("cricketCoach") Coach anotherCoach){
//        System.out.println("In Constructor : " + getClass().getSimpleName());
//        this.daCoach=daCoach;
//        this.anotherCoach = anotherCoach;
//    }
//    @GetMapping("/check") // BeanScope code method
//    public String checkBeanType(){
//        return "Comparing beans : daCoach == anotherCoach => " + (daCoach==anotherCoach);
//    }
