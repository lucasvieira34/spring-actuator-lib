package com.lucasvieira.customactuatorlib.infrastructure.config;

import com.lucasvieira.customactuatorlib.application.service.CustomActuatorService;
import com.lucasvieira.customactuatorlib.infrastructure.repository.CustomTraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpExchangeTracer;
import org.springframework.boot.actuate.trace.http.HttpTraceEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SharedConfigurationReference {

    @Autowired
    private HttpExchangeTracer tracer;

    @Bean
    public CustomActuatorService customActuatorService() {
        return new CustomActuatorService();
    }

    @Bean
    public CustomTraceRepository customTraceRepository() {
        return new CustomTraceRepository();
    }

    @Bean
    public HttpTraceEndpoint httpTraceEndpoint() {
        return new HttpTraceEndpoint(customTraceRepository());
    }

    @Bean
    public TraceRequestFilter traceRequestFilter() {
        return new TraceRequestFilter(customTraceRepository(), tracer);
    }
}
