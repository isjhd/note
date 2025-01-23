package com.atguigu.admin.acutuator.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Component
public class AppInfoContributor implements InfoContributor {


    @Override
    public void contribute(Info.Builder builder) {

        builder.withDetail("空气系","轻音少女")
                .withDetail("机甲番","全金属狂潮")
                .withDetails(Collections.singletonMap("world","666"));

    }
}
