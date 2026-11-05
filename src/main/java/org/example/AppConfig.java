package org.example;

import org.example.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example")
public class AppConfig {

    @Bean
    public Address address() {
        Address address = new Address();
        address.setCity("Addis Ababa");
        address.setCountry("Ethiopia");
        return address;
    }

    @Bean
    public Subject math() {
        Subject subject = new Subject();
        subject.setSubjectName("Maths");
        return subject;
    }

    @Bean
    public Subject java() {
        Subject subject = new Subject();
        subject.setSubjectName("Java");
        return subject;
    }
}