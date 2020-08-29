import com.lzzzzl.example.right.GenerateRightIpUUID;
import com.lzzzzl.example.right.GenerateRightUUID;
import com.lzzzzl.example.wrong.GenerateWrongUUID;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: lzzzzl
 * @Date: 2020/8/29
 * @Desc:
 */

public class GenerateUUIDTest {

    @Test
    public void generateUUID1() {
        String orderId = GenerateWrongUUID.getYYMMDDHHNumber("1234");
        System.out.println(orderId);
    }

    @Test
    public void generateUUID2() {
        final String merchId = "12334";
        final List<String> orderNos = Collections.synchronizedList(new ArrayList<String>());
        IntStream.range(0, 100).parallel().forEach(i -> {
            orderNos.add(GenerateWrongUUID.getYYMMDDHHNumber(merchId));
        });
        List<String> filterOrderNos = orderNos.stream().distinct().collect(Collectors.toList());

        System.out.println("生成订单数: " + orderNos.size());
        System.out.println("过滤重复后订单数:  " + filterOrderNos.size());
        System.out.println("重复订单数: " + (orderNos.size() - filterOrderNos.size()));
    }

    @Test
    public void generateUUID3() {
        List<String> orderNos = Collections.synchronizedList(new ArrayList<String>());
        IntStream.range(0, 8000).parallel().forEach(i->{
            orderNos.add(GenerateRightUUID.generateOrderNo());
        });

        List<String> filterOrderNos = orderNos.stream().distinct().collect(Collectors.toList());

        System.out.println("生成订单数："+orderNos.size());
        System.out.println("过滤重复后订单数："+filterOrderNos.size());
        System.out.println("重复订单数："+(orderNos.size()-filterOrderNos.size()));
    }

    @Test
    public void generateUUID4() {
        String orderId = GenerateRightIpUUID.generateOrderNo();
        System.out.println(orderId);
    }
}
