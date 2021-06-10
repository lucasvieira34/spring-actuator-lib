package com.lucasvieira.customactuatorlib.application.service;

import com.lucasvieira.customactuatorlib.application.model.CountRequests;
import com.lucasvieira.customactuatorlib.infrastructure.repository.CustomTraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTraceEndpoint;
import org.springframework.stereotype.Service;

@Service
public class CustomActuatorService {

    @Autowired
    private CustomTraceRepository repository;

    public CountRequests countRequests(HttpTraceEndpoint httpTraceEndpoint) {

        Integer totalRequests = httpTraceEndpoint.traces().getTraces().size();

        Long okStatus = httpTraceEndpoint.traces().getTraces().stream()
                .filter(httpTrace -> httpTrace.getResponse().getStatus() == 200)
                .count();

        Long acceptedStatus = httpTraceEndpoint.traces().getTraces().stream()
                .filter(httpTrace -> httpTrace.getResponse().getStatus() == 202)
                .count();

        Long badRequestStatus = httpTraceEndpoint.traces().getTraces().stream()
                .filter(httpTrace -> httpTrace.getResponse().getStatus() == 400)
                .count();

        Long notFoundStatus = httpTraceEndpoint.traces().getTraces().stream()
                .filter(httpTrace -> httpTrace.getResponse().getStatus() == 404)
                .count();

        Long preconditionalStatus = httpTraceEndpoint.traces().getTraces().stream()
                .filter(httpTrace -> httpTrace.getResponse().getStatus() == 412)
                .count();

        Long internalStatus = httpTraceEndpoint.traces().getTraces().stream()
                .filter(httpTrace -> httpTrace.getResponse().getStatus() == 500)
                .count();

        reset();
        return CountRequests
                .builder()
                .totalRequests(totalRequests)
                .okStatus(okStatus)
                .acceptedStatus(acceptedStatus)
                .notFoundStatus(notFoundStatus)
                .badRequestStatus(badRequestStatus)
                .preconditionalStatus(preconditionalStatus)
                .internalStatus(internalStatus)
                .build();
    }

    private void reset() {
        repository.clear();
    }
}
