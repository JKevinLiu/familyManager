package com.snill.fm.kafka;

import java.util.Map;
import com.snill.fm.utils.BeanUtils;
import org.apache.kafka.common.serialization.Deserializer;

public class DecodeingKafka implements Deserializer<Object> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        return BeanUtils.byte2Obj(data);
    }

    @Override
    public void close() {

    }
}

