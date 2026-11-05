package org.example;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main
{
    public static void main( String[] args )
    {

        //annotation based injection
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Student student1 = context.getBean("student", Student.class);
        Student student2 = context.getBean("student", Student.class);
        // Check if both references point to the SAME instance
        System.out.println(student1 == student2);

        student1.setName("John Doe");
        System.out.println(student2.getName());
        student1.getDetails();


    }

    }
