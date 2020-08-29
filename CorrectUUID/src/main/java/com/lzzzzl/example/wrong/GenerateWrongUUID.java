package com.lzzzzl.example.wrong;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author: lzzzzl
 * @Date: 2020/8/29
 * @Desc: 生成错误UUID
 */
public class GenerateWrongUUID {

    /**
     * OD单号生成
     *
     * 订单号生成规则：OD + yyMMddHHmmssSSS + 5位数(商户ID3位数 + 随机数2位) 22位
     * @param merchId
     * @return
     */
    public static String getYYMMDDHHNumber(String merchId) {
        StringBuffer orderNo = new StringBuffer(new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()));
        if (StringUtils.isNotBlank(merchId)) {
            if (merchId.length() > 3) {
                orderNo.append(merchId.substring(0, 3));
            } else {
                orderNo.append(merchId);
            }
        }
        int orderLength = orderNo.toString().length();
        String randomNum = getRandomByLength(20 - orderLength);
        orderNo.append(randomNum);
        return orderNo.toString();
    }

    /**
     * 生成指定位数得随机数
     *
     * @param size
     * @return
     */
    private static String getRandomByLength(int size) {
        if (size > 8 || size < 1) {
            return "";
        }
        Random ne = new Random();
        StringBuffer endNumStr = new StringBuffer("1");
        StringBuffer staNumStr = new StringBuffer("9");
        for (int i = 1; i < size; i++) {
            endNumStr.append("0");
            staNumStr.append("0");
        }
        int randomNum = ne.nextInt(Integer.valueOf(staNumStr.toString())) + Integer.valueOf(endNumStr.toString());
        return String.valueOf(randomNum);
    }

}
