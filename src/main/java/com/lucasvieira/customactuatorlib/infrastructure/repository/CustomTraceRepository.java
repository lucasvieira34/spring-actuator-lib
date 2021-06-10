package com.lucasvieira.customactuatorlib.infrastructure.repository;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CustomTraceRepository implements HttpTraceRepository {

    private boolean reverse = true;
    private final List<HttpTrace> traces = new LinkedList();

    @Override
    public List<HttpTrace> findAll() {
        synchronized(this.traces) {
            return new ArrayList(this.traces);
        }
    }

    @Override
    public void add(HttpTrace trace) {
        synchronized(this.traces) {
            if (this.reverse) {
                this.traces.add(0, trace);
            } else {
                this.traces.add(trace);
            }
        }
    }

    public void clear() {
        findAll();
        traces.clear();
    }
}
