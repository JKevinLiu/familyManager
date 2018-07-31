package com.snill.fm.calc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalcFinishTask implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(CalcFinishTask.class);

    @Override
    public void run() {
        //计算完毕
        log.info("calc finish ...");
    }
}