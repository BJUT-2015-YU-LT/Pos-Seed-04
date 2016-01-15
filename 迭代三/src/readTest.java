import net.sf.json.JSONObject;
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
        pos.pocs pir = new pos.pocs();
        String mess = pir.readJson("C:\\Users\\cyd\\Desktop\\迭代三\\src\\data1");
        JSONObject jsonObject = JSONObject.fromObject(mess);
        JSONObject JS = jsonObject.getJSONObject("USER001");
        assertEquals("USER001",JS.get("name").toString());
    }
}