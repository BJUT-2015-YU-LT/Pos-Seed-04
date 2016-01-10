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
        pos.pocs ped=new pos.pocs();
        String mess = ped.readJson("C:\\Users\\cyd\\Desktop\\需求四\\src\\data2");
        List<String> item = new ArrayList<String>();
        item = ped.find(mess);
        assertEquals( "ITEM000000",item.get(0));
    }
}