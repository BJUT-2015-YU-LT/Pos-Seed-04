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
        pos.pocs pir = new pos.pocs();
        String mess = pir.readJson("C:\\Users\\cyd\\Desktop\\需求一\\src\\data1");
        List<goods> item = new ArrayList<goods>();
        item = pir.fetch(mess);
        List<goods> index = new ArrayList<goods>();
        index = pir.compared(index, item);
        assertEquals("***商品购物清单***\n" +
                "名称：可口可乐,数量:5瓶,单价：3.00(元)，小计：15.00(元)\n" +
                "名称：雪碧,数量:2瓶,单价：3.00(元)，小计：6.00(元)\n" +
                "名称：电池,数量:1个,单价：2.00(元)，小计：2.00(元)\n" +
                "- - - - - - - - - - - - - -\n" +
                "总计：23.00(元)\n" +
                "***************************",pir.count(index));
    }
}