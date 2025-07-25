package com.pm.analytics_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.pm.analyticsservice.AnalyticsServiceApplication;

@SpringBootTest(classes = AnalyticsServiceApplication.class, properties = "spring.profiles.active=test")
class AnalyticsServiceApplicationTests {
    @Test
    void contextLoads() {
    }
}