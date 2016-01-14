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
        String mess = fir.readJson("C:\\Users\\cyd\\Desktop\\迭代三\\src\\data2");
        String mass = fir.readJson("C:\\Users\\cyd\\Desktop\\迭代三\\src\\data3");
        List<String> item = new ArrayList<String>();
        List<String> item1 = new ArrayList<String>();
        List<String> item2 = new ArrayList<String>();
        item = fir.getUser(mess);
        item1=fir.IsVip(item.get(0));
        item2=fir.find(item.get(1));
        List<goods> index = new ArrayList<goods>();
        index = fir.fetch( item2,mass);
        index = fir.compared(index);
        assertEquals("***商店购物清单***\n" +
                "会员编号 ：周昔月   会员积分  ：5分\n" +
                "- - - - - - - - - - - - - -\n" +
                "打印时间: 2016年/01月/14日 04:06:14\n" +
                "- - - - - - - - - - - - - -\n" +
                "名称：可口可乐,数量:1瓶,单价：3.00(元)，小计：2.70(元)\n" +
                "名称：雪碧,数量:2瓶,单价：3.00(元)，小计：4.56(元)\n" +
                "- - - - - - - - - - - - - -\n" +
                "挥泪赠送商品：\n" +
                "- - - - - - - - - - - - - -\n" +
                "总计：7.26(元)\n" +
                "节省：1.74(元)\n" +
                "***************************",fir.count(index,item1));
    }
}