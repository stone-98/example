package com.ocat.monitor.example.springbootactuator.labels;

import io.prometheus.client.Gauge;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @Description:
 * @Author: stone-98
 * @createTime: 2023年04月12日 14:26:27
 */
@Component
public class CustomInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("customInfo", Collections.singletonMap("hello", "world"));
    }
}
