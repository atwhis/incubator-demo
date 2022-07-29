package com.ymchen.incubatordemo.examples.disruptor.example1;


import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class DisruptorExample1 {
    public static void main(String[] args) {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();
        // The factory for the event
        StudentEventFactory studentEventFactory = new StudentEventFactory();
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024 * 1024;

        //strategy
        //SleepingWaitStrategy  性能表现跟 BlockingWaitStrategy 差不多，对 CPU 的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景
        //BlockingWaitStrategy  最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现
        //YieldingWaitStrategy  性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于 CPU 逻辑核心数的场景中，推荐使用此策略

        Disruptor<StudentEvent> disruptor = new Disruptor<StudentEvent>(StudentEvent::new, bufferSize, r -> {
            AtomicInteger atomicInteger = new AtomicInteger(1);
            return new Thread(null, r, "disruptor-thread-" + atomicInteger.getAndIncrement());
        },
                ProducerType.SINGLE, new YieldingWaitStrategy());

        disruptor.handleEventsWith(((event, sequence, endOfBatch) -> log.info("studentId-->{}", event.getStudentId())
        )).then(new StudentEventClearHandler());


        disruptor.setDefaultExceptionHandler(new ExceptionHandler<StudentEvent>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, StudentEvent event) {
                log.error("handleEventException");
            }

            @Override
            public void handleOnStartException(Throwable ex) {
                log.error("handleOnStartException");
            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                log.error("handleOnShutdownException");
            }
        });

        disruptor.start();

        for (int i = 1; i <= 3; i++) {

            RingBuffer<StudentEvent> ringBuffer = disruptor.getRingBuffer();


            //1、translator
            ringBuffer.publishEvent(new StudentEventTranslator(), Long.parseLong(String.valueOf(i)));

            //2、
            /*long sequence = ringBuffer.next();
            try {
                StudentEvent studentEvent = ringBuffer.get(sequence);
                studentEvent.setStudentId(Long.parseLong(String.valueOf(i)));
            } finally {
                ringBuffer.publish(sequence);
            }*/
        }
    }
}
