package com.bnpp.demo.spring.config;

import com.bnpp.demo.spring.model.demo.Product;
import com.bnpp.demo.spring.service.DemoService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class StartupApplicationListener {

    @Autowired
    private DemoService demoService;

    public static boolean isStart = true;

    @EventListener
    public void appReady(ContextRefreshedEvent event) {
        if(isStart){
            System.out.println("test!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            isStart = false;
        }
    }

}
