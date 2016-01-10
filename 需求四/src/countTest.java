import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cyd on 2016/1/10.
 */
public class countTest {

    @Test
    public void testCount() throws Exception {
        pos.pocs fir = new pos.pocs();
        String mess = fir.readJson("C:\\Users\\cyd\\Desktop\\需求四\\src\\data2");
        String mass = fir.readJson("C:\\Users\\cyd\\Desktop\\需求四\\src\\data1");
        List<String> item = new ArrayList<String>();
        item = fir.find(mess);
        List<goods> index = new ArrayList<goods>();
        index = fir.fetch( item,mass);
        index = fir.compared(index);
        assertEquals("***商品购物清单***\n" +
                "名称：可口可乐,数量:3瓶,单价：3.00(元)，小计：6.00(元)\n" +
                "- - - - - - - - - - - - - -\n" +
                "总计：6.00(元)\n" +
                "节省：3.00(元)\n" +
                "***************************",fir.count(index));
    }
}