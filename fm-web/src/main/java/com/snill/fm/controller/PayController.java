package com.snill.fm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.snill.fm.bean.JsonResult;
import com.snill.fm.bean.PayMonth;
import com.snill.fm.service.PayMonthService;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PayController {
    private static Logger log = Logger.getLogger(PayController.class);

    @Reference
    private PayMonthService payMonthService;

    @RequestMapping(value = "pay/list/{userId}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> getHalfYear(@PathVariable("userId") Integer userId){
        JsonResult r = new JsonResult();
        try {
            List<PayMonth> payMonthList = payMonthService.getUserLastHalfYearPayMonth(userId);
            r.setResult(payMonthList);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }
}
