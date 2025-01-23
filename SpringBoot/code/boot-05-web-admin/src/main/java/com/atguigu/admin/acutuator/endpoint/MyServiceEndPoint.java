package com.atguigu.admin.acutuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Component
@Endpoint(id = "myService")
public class MyServiceEndPoint {

    @ReadOperation
    public Map getDockerInfo() {
        // 端点的读操作
        return Collections.singletonMap("dockerInfo","docker started...");
    }

    @WriteOperation
    public void stopDocker() {
        System.out.println("docker stopped....");
    }

}
