package com.pbukki.creswave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CreswaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreswaveApplication.class, args);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String pass = passwordEncoder.encode("test16");
        System.out.println(pass);

    }

}
