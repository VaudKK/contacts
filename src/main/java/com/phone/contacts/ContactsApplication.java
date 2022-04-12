package com.phone.contacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.regex.Pattern;

@SpringBootApplication
public class ContactsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactsApplication.class, args);
//        Pattern pattern = Pattern.compile("\\(251\\) ?[1-59]\\d{8}$");
//        String p = "\\(251\\) ?[1-59]\\d{8}$";
//        System.out.println("(251) 966002259".matches(p));
    }

}
