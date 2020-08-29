package com.lzzzzl.example.right;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lzzzzl
 * @Date: 2020/8/29
 * @Desc: 生成正确UUID
 */
public class GenerateRightUUID {

    /**
     * 订单号生成
     */
    private static final AtomicInteger SEQ = new AtomicInteger(1000);
    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddHHmmssSS");
    private static ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    public static String generateOrderNo() {
        LocalDateTime dateTime = LocalDateTime.now(ZONE_ID);
        if (SEQ.intValue() > 9999) {
            SEQ.getAndSet(1000);
        }
        return dateTime.format(DF_FMT_PREFIX) + SEQ.getAndIncrement();
    }

}
