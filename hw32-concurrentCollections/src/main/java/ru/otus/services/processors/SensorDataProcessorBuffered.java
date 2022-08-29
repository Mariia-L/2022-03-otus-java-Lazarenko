package ru.otus.services.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.lib.SensorDataBufferedWriter;
import ru.otus.api.SensorDataProcessor;
import ru.otus.api.model.SensorData;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class SensorDataProcessorBuffered implements SensorDataProcessor {
    private static final Logger log = LoggerFactory.getLogger(SensorDataProcessorBuffered.class);

    private final int bufferSize;
    private final SensorDataBufferedWriter writer;
    private final Set<SensorData> dataBuffer;
    private final ReentrantLock locker = new ReentrantLock();

    public SensorDataProcessorBuffered(int bufferSize, SensorDataBufferedWriter writer) {
        this.bufferSize = bufferSize;
        this.writer = writer;
        this.dataBuffer = new TreeSet<>(new Comparator<SensorData>() {
            @Override
            public int compare(SensorData o1, SensorData o2) {
                return o1.getMeasurementTime().compareTo(o2.getMeasurementTime());
            }
        });
    }

    @Override
    public void process(SensorData data) {

        if (data.getValue() == null || data.getValue().isNaN()) {
            return;
        }

        locker.lock();

        dataBuffer.add(data);

        if (dataBuffer.size() >= bufferSize) {
            flush();
        }

        locker.unlock();
    }

    public void flush() {
        try {
            locker.lock();
            if (dataBuffer.size() > 0) {

                writer.writeBufferedData(new ArrayList<>(dataBuffer));
                dataBuffer.clear();
            }
            locker.unlock();
        } catch (Exception e) {
            log.error("Ошибка в процессе записи буфера", e);
        }
    }

    @Override
    public void onProcessingEnd() {
        flush();
    }
}
