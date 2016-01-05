import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cyd on 2016/1/6.
 */
public class countTest {

    @Test
    public void testCount() throws Exception {
        POS.pocs pis = new POS.pocs();
        String mess = pis.readJson("C:\\Users\\cyd\\Desktop\\需求二\\src\\data1");
        List<GOODS> item = new ArrayList<GOODS>();
        item = pis.fetch(mess);
        List<GOODS> index = new ArrayList<GOODS>();
        index = pis.compared(index, item);
        assertEquals( "***商品购物清单***\n" +
                "名称：可口可乐,数量:5瓶,单价：3.00(元)，小计：0.00(元)\n" +
                "名称：雪碧,数量:2瓶,单价：3.00(元)，小计：4.80(元)\n" +
                "名称：电池,数量:1个,单价：2.00(元)，小计：1.60(元)\n" +
                "- - - - - - - - - - - - - -\n" +
                "总计：6.40(元)\n" +
                "节省：16.60(元)\n" +
                "***************************",pis.count(index));
    }
}