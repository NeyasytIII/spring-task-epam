package com.epamtask.aspect;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LoggingAspectTest.TestConfig.class)
class LoggingAspectTest {

    @Configuration
    @EnableAspectJAutoProxy(exposeProxy = true)
    @ComponentScan(basePackageClasses = LoggingAspect.class)
    static class TestConfig {
        @Bean
        TestService testService() {
            return new TestService();
        }
    }

    static class TestService {
        @Loggable
        public String doSomething(String arg) {
            return "Hello " + arg;
        }
    }

    @Autowired
    TestService testService;

    @Test
    void testLoggableAnnotation() {
        String result = testService.doSomething("World");
        assertEquals("Hello World", result);
    }
}