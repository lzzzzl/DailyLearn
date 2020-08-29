package com.lzzzzl.example.right;


import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lzzzzl
 * @Date: 2020/8/29
 * @Desc: 根据IP生成UUID
 */
public class GenerateRightIpUUID {

    /**
     * 订单号生成
     **/
    private static ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");
    private static final AtomicInteger SEQ = new AtomicInteger(1000);
    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddHHmmssSS");
    private volatile static String IP_SUFFIX = null;
    private static Logger LOGGER = LoggerFactory.getLogger(GenerateRightIpUUID.class);

    public static String generateOrderNo() {
        System.out.println("IN");
        LocalDateTime dateTime = LocalDateTime.now(ZONE_ID);
        if (SEQ.intValue() > 9999) {
            SEQ.getAndSet(1000);
        }
        return dateTime.format(DF_FMT_PREFIX) + getLocalIpSuffix() + SEQ.getAndIncrement();
    }

    private static String getLocalIpSuffix() {
        if (null != IP_SUFFIX) {
            return IP_SUFFIX;
        }

        try {
            synchronized (GenerateRightIpUUID.class) {
                if (null != IP_SUFFIX) {
                    return IP_SUFFIX;
                }
                InetAddress addr = InetAddress.getLocalHost();
                // 172.17.0.4 172.17.0.199
                String hostAddress = addr.getHostAddress();
                LOGGER.info("hostAddress: {}", hostAddress);
                if (null != hostAddress && hostAddress.length() > 4) {
                    String ipSuffix = hostAddress.trim().split("\\.")[3];
                    if (ipSuffix.length() == 2) {
                        IP_SUFFIX = ipSuffix;
                        LOGGER.info("IP_SUFFIX1: {}", IP_SUFFIX);
                        return IP_SUFFIX;
                    }
                    ipSuffix = "0" + ipSuffix;
                    IP_SUFFIX = ipSuffix.substring(ipSuffix.length() - 2);
                    LOGGER.info("IP_SUFFIX2: {}", IP_SUFFIX);
                    return IP_SUFFIX;
                }
//                IP_SUFFIX = RandomUtils.nextInt(10, 20) + "";
                return "";
            }
        } catch (Exception e) {
            System.out.println("获取IP失败" + e.getMessage());
            IP_SUFFIX = "";
            return IP_SUFFIX;
        }
    }


}
