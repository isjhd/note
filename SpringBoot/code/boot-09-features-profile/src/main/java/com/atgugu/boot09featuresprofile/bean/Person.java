package com.atgugu.boot09featuresprofile.bean;

/* @author  i-s-j-h-d
 * @version 1.0 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


public interface Person {

    String getName();
    Integer getAge();
}
