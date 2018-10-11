package com.example.accessenv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.StringJoiner;

@RestController
@RequestMapping("/env")
public class Controller {
    @Autowired
    private Environment env;
    @Value("${JAVA_HOME}")
    private String javaHome;

    @GetMapping("/show/java/home/from/config")
    public String javaHomeFromConfig() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(env.getProperty("your.configs.here.message"));
        joiner.add("JAVA_HOME = " + env.getProperty("your.configs.here.message"));
        joiner.add("comment = " + env.getProperty("your.configs.here.comment"));

        String result = joiner.toString();
        System.out.println(result);
        return result;
    }

    @GetMapping("/show/java/home/from/value/annotation")
    public String javaHomeFromValueAnnotation() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Environment variable using @Value annotation:");
        joiner.add("JAVA_HOME = " + javaHome);
        joiner.add("comment = " + env.getProperty("your.configs.here.comment"));

        String result = joiner.toString();
        System.out.println(result);
        return result;
    }

    @GetMapping("/show/environment/variable")
    public String usingSystem(@RequestParam String env) {
        String result = env + " " + System.getenv(env);
        System.out.println(result);
        return result;
    }

}
