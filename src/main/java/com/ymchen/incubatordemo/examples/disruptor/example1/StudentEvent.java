package com.ymchen.incubatordemo.examples.disruptor.example1;


import lombok.Data;

@Data
public class StudentEvent {

    private Long studentId;

    private ContactInfo contactInfo;


    /**
     * gc
     */
    public void clear() {
        this.contactInfo = null;
    }

}
