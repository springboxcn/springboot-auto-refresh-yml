package edu.maskleo.autorefreshyml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("edu.maskleo")
@SpringBootApplication
public class AutoRefreshYmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoRefreshYmlApplication.class, args);
    }

}
