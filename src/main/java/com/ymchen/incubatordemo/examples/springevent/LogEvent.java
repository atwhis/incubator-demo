package com.ymchen.incubatordemo.examples.springevent;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogEvent {
    private String msg;
}
