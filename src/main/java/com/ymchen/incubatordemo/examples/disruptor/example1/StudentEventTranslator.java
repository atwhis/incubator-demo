package com.ymchen.incubatordemo.examples.disruptor.example1;


import com.lmax.disruptor.EventTranslatorOneArg;

public class StudentEventTranslator implements EventTranslatorOneArg<StudentEvent, Long> {

    @Override
    public void translateTo(StudentEvent event, long sequence, Long studentId) {
        event.setStudentId(studentId);
    }
}
