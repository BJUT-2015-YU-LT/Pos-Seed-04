import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cyd on 2016/1/10.
 */
public class findTest {

    @Test
    public void testFind() throws Exception {
        pos.pocs fir=new pos.pocs();
        String mess = fir.readJson("C:\\Users\\cyd\\Desktop\\迭代三\\src\\data2");
        List<String> item = new ArrayList<String>();
        item=fir.getUser(mess);
        assertEquals("ITEM000000",fir.find(item.get(1)).get(0));
    }
}