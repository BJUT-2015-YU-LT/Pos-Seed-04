import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cyd on 2016/1/5.
 */
public class readTest {

    @Test
    public void testReadJson() throws Exception {
        pos.pocs pir = new pos.pocs();
        String aim="";

        assertEquals(  "hello world!", pir.readJson("C:\\Users\\cyd\\Desktop\\需求一\\src\\text"));

    }
}