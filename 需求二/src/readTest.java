import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cyd on 2016/1/6.
 */
public class readTest {

    @Test
    public void testReadJson() throws Exception {
        POS.pocs pir = new POS.pocs();
        String aim="";

        assertEquals(  "Hello World!", pir.readJson("C:\\Users\\cyd\\Desktop\\需求二\\src\\text"));
    }
}