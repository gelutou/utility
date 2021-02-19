package oliver.poi.easyexcel;


import oliver.poi.DemoData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Oliver
 * @date 2021/2/19 14:06
 * @description
 */
public class BaseWrite {

    @Test
    public void data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        System.out.println("list.size() = " + list.size());
    }
}
