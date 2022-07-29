package com.ymchen.incubatordemo.examples.disruptor.example1;


import com.lmax.disruptor.EventFactory;

public class StudentEventFactory implements EventFactory<StudentEvent> {
    @Override
    public StudentEvent newInstance() {
        return new StudentEvent();
    }
}
