/**
 * Created by cyd on 2016/1/5.
 */
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.io.File;
import java.util.*;

public class POS {

    public static class pocs {
        public pocs()
        {}
        public String count(List<GOODS> index) {
            java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
            double total = 0;
            double total1 = 0;
            String str = "";
            str = str + "***商品购物清单***\n";
            for(int i=0;i<index.size();i++)
            {
               str=str+"名称："+index.get(i).name+",数量:"+index.get(i).num+index.get(i).unit+",单价："
                        +df.format(index.get(i).price)+"(元)，小计："+
                        df.format(index.get(i).price*index.get(i).num*index.get(i).discount)+"(元)\n";
                total=total+index.get(i).price*index.get(i).num;
                total1=total1+index.get(i).price*index.get(i).num*index.get(i).discount;
            }
            str=str+"- - - - - - - - - - - - - -\n";
            str=str+"总计："+df.format(total1)+"(元)\n"+"节省："+df.format(total1=total-total1)+"(元)\n***************************";
            return str;
        }


        public String readJson(String path) {
            //从给定位置获取文件
            File file = new File(path);
            BufferedReader reader = null;
            //返回值,使用StringBuffer
            StringBuffer data = new StringBuffer();
            //
            try {
                reader = new BufferedReader(new FileReader(file));
                //每次读取文件的缓存
                String temp = null;
                while ((temp = reader.readLine()) != null) {
                    data.append(temp);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //关闭文件流
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return data.toString();
        }

        public List<GOODS> fetch(String message) {
            JSONArray jsonarray = JSONArray.fromObject(message);
            List<GOODS> item = new ArrayList<GOODS>();
            for (int i = 0; i < jsonarray.size(); i++) {
                JSONObject JS = jsonarray.getJSONObject(i);
                GOODS thing = new GOODS();
                thing.barcode = JS.get("barcode").toString();
                thing.name = JS.get("name").toString();
                thing.unit = JS.get("unit").toString();
                thing.price = Double.parseDouble(JS.get("price").toString());
                thing.discount=Double.parseDouble(JS.get("discount").toString());
                item.add(thing);
            }
            return item;
        }

        public List<GOODS> compared(List<GOODS> index, List<GOODS> item) {
            for (int i = 0; i < item.size(); i++) {
                if (index.size() == 0) index.add(item.get(i));
                else {
                    int dex = 0;
                    for (int j = 0; j < index.size(); j++) {
                        if (index.get(j).barcode.toString().compareTo(item.get(i).barcode.toString()) == 0) {
                            index.get(j).num++;
                            dex = 1;
                            break;
                        }
                    }
                    if (dex == 0) {
                        index.add(item.get(i));
                    }
                }
            }
            return index;
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        pocs fir = new pocs();
        String mess = fir.readJson("C:\\Users\\cyd\\Desktop\\需求二\\src\\data1");
        List<GOODS> item = new ArrayList<GOODS>();
        item = fir.fetch(mess);
        List<GOODS> index = new ArrayList<GOODS>();
        index = fir.compared(index, item);
        System.out.print(fir.count(index));
    }
}
