package com.example.springbootdocker;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;
 
@RestController
@SpringBootApplication
public class SpringBootDockerApplication {
        private static final Logger logger = Logger.getLogger(SpringBootDockerApplication.class.getName());

        public static void main(String[] args) {
                logger.info("Starting Spring Boot application");
                SpringApplication.run(SpringBootDockerApplication.class, args);
        }

 
        @RequestMapping("/")
        public String home() {
                logger.info("Hello log!");
                return "Hello World!";
        }

        @RequestMapping("/one")
        public String one() {
                logger.info("Hello log One");
                return "Hello World One";
        }	

        @RequestMapping("/two")
        public String two() {
                logger.info("Hello log Two");
                return "Hello World Two";
        }

        @RequestMapping("/ver")
        public String ver() {
                logger.info("Hello a0.3");
                return "Hello World a0.3";
        } 

        @RequestMapping("/ms")
        public String ms() {
                logger.info("Hello ms latest 2");
                return "Hello World ms latest 2";
        }         

 
}