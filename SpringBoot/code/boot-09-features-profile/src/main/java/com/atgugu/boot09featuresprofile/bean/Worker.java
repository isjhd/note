package com.atgugu.boot09featuresprofile.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Profile("test")
@Component
@ConfigurationProperties("person")
@Data
public class Worker implements Person{

    private String name;
    private Integer age;

}
