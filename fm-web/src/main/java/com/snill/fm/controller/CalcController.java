package com.snill.fm.controller;

import com.snill.fm.bean.JsonResult;
import com.snill.fm.transfer.CalcParams;
import com.snill.fm.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {
    private static final Logger log = LoggerFactory.getLogger(CalcController.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/calc/lastPay", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> calc(@RequestBody CalcParams calcParams) {
        JsonResult r = new JsonResult();
        try {
            calcParams.setYearMonthList(DateUtils.getLastSIXMonth());
            kafkaTemplate.send("calcLastPay", "key", calcParams);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }

}