package com.ymchen.incubatordemo.examples.disruptor.example2;


import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class DisruptorExample1 {
    public static void main(String[] args) {
        int buffSize = 1024;

        Disruptor<StringEvent> disruptor = new Disruptor<StringEvent>(StringEvent::new, buffSize, r -> {
            AtomicInteger index = new AtomicInteger();
            return new Thread(null, r, "disruptor-thread" + index);
        }, ProducerType.SINGLE, new YieldingWaitStrategy());


        //串行处理(依次)
       /* disruptor.handleEventsWith(((event, sequence, endOfBatch) -> log.info("handler1"))).handleEventsWith(((event,
                                                                                                               sequence, endOfBatch) -> log.info("handler2")));
*/
        //并行处理(同时)
        /*disruptor.handleEventsWith(((event, sequence, endOfBatch) -> log.info("handler1")), ((event, sequence,
                                                                                              endOfBatch) -> log
                .info("handler2")));*/

        //链式处理(1&2 与 3&4 并行)
        /*disruptor.handleEventsWith(new Handler1(),new Handler2());
        disruptor.handleEventsWith(new Handler3(),new Handler4());*/

        //单一消费
        //disruptor.handleEventsWithWorkerPool(new Handler1(),new Handler2());


        //菱形
        /**
         *    handler1 --> handler2
         *                           --> handler5
         *    handler3 --> handler4
         */
        Handler1 handler1 = new Handler1();
        Handler2 handler2 = new Handler2();
        Handler3 handler3 = new Handler3();
        Handler4 handler4 = new Handler4();
        Handler5 handler5 = new Handler5();

        disruptor.handleEventsWith(handler1, handler3);
        disruptor.after(handler1).handleEventsWith(handler2);
        disruptor.after(handler3).handleEventsWith(handler4);
        disruptor.after(handler2, handler4).handleEventsWith(handler5);


        disruptor.start();

        RingBuffer<StringEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(((event, sequence) -> event.setStr("hello world")));
    }
}
