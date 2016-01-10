import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cyd on 2016/1/10.
 */
public class readTest {

    @Test
    public void testReadJson() throws Exception {
        pos.pocs ped = new pos.pocs();
        String mess = ped.readJson("C:\\Users\\cyd\\Desktop\\需求三\\src\\data2");

        assertEquals( "[" +
                "  \"ITEM000000\"," +
                "  \"ITEM000000\"," +
                "  \"ITEM000000\"," +
                "  \"ITEM000000\"," +
                "  \"ITEM000000\"," +
                "  \"ITEM000001\"," +
                "  \"ITEM000001\"," +
                "  \"ITEM000004\"" +
                "]",mess);
    }
}