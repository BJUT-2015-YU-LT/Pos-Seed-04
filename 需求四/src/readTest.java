import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cyd on 2016/1/10.
 */
public class readTest {

    @Test
    public void testFetch() throws Exception {
        List<goods> item=new ArrayList<goods>();
        List<String> item2=new ArrayList<String>();
        pos.pocs ped = new pos.pocs();
        String mess = ped.readJson("C:\\Users\\cyd\\Desktop\\需求四\\src\\data2");
        String mass = ped.readJson("C:\\Users\\cyd\\Desktop\\需求四\\src\\data1");
        item2 = ped.find(mess);
        item = ped.fetch( item2,mass);
        assertEquals( "可口可乐",item.get(0).name);
    }
}