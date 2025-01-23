package com.atgugu.boot09featuresprofile.controller;


import com.atgugu.boot09featuresprofile.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/* @author  i-s-j-h-d
 * @version 1.0 */
@RestController
public class HelloController {

    @Value("${person.name:李四}")
    private String name;

    @Autowired
    private Person person;

    @Value("${MAVEN_HOME}")
    private String msg;

    @Value("${os.name}")
    private String osName;

    @GetMapping("/person")
    public Person person(){
        return person;
    }

    @GetMapping("/msg")
    public String getMsg(){
        return msg + "==>" + osName;
    }

}
