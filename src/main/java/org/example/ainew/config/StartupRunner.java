package org.example.ainew.config;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StartupRunner  implements ApplicationListener<ContextRefreshedEvent> {

    private final RestTemplate restTemplate;

    @Autowired
    public StartupRunner(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Код для выполнения после запуска приложения
        String url = "http://localhost:8080/loader/single";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("Response from " + url + ": " + response);
    }
}