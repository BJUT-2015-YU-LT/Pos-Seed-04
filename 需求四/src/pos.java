/**
 * Created by cyd on 2016/1/5.
 */
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.io.File;
import java.util.*;

public class pos {

    public static class pocs {
        public pocs()
        {}
        public String count(List<goods> index) {
            java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
            double total = 0;
            double total1=0;
            String str = "";
            String str1 = "";
            str = str + "***商品购物清单***\n";
            for(int i=0;i<index.size();i++)
            {
                if(index.get(i).promotion.equals("null")) {
                    str = str + "名称：" + index.get(i).name + ",数量:" + index.get(i).num + index.get(i).unit + ",单价："
                            + df.format(index.get(i).price) + "(元)，小计：" +
                            df.format(index.get(i).price * index.get(i).num * index.get(i).discount) + "(元)\n";
                    total = total + index.get(i).price * index.get(i).num;
                    total1 = total1 + index.get(i).price * index.get(i).num * index.get(i).discount;
                }
                else
                {
                    if(index.get(i).num%3==0) {
                        str = str + "名称：" + index.get(i).name + ",数量:" + index.get(i).num + index.get(i).unit + ",单价："
                                + df.format(index.get(i).price) + "(元)，小计：" +
                                df.format(index.get(i).price * index.get(i).num *2/3) + "(元)\n";
                        total = total + index.get(i).price * index.get(i).num;
                        total1 = total1 + index.get(i).price * index.get(i).num * 2/3;
                    }
                    else if(index.get(i).num%3==1)
                    {
                        str = str + "名称：" + index.get(i).name + ",数量:" + index.get(i).num + index.get(i).unit + ",单价："
                                + df.format(index.get(i).price) + "(元)，小计：" +
                                df.format(index.get(i).price * (index.get(i).num-1) *2/3+index.get(i).price) + "(元)\n";
                        total = total + index.get(i).price * index.get(i).num;
                        total1 = total1 + index.get(i).price * (index.get(i).num-1) *2/3+index.get(i).price;
                    }
                    else if(index.get(i).num%3==2)
                    {
                        str=str+"输入商品件数错误，请检查后重新输入！\n";
                        return str;
                    }
                    str1=str1+"- - - - - - - - - - - - - -\n挥泪赠送商品：\n名称："+index.get(i).name+",数量："+(int)index.get(i).num/3+index.get(i).unit+"\n";
                }
            }
            str=str+str1+"- - - - - - - - - - - - - -\n";
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

        public List<goods> fetch(List<String> index,String message) {
            JSONObject jsonarray = JSONObject.fromObject(message);
            List<goods> item = new ArrayList<goods>();
            List<String> item2=new ArrayList<String>();

            for(int k=0;k<index.size();k++) {
                JSONObject JS = jsonarray.getJSONObject(index.get(k));
                goods thing = new goods();
                thing.barcode = index.get(k);
                thing.name = JS.get("name").toString();
                thing.unit = JS.get("unit").toString();
                thing.price = Double.parseDouble(JS.get("price").toString());
                if(JS.get("discount")!=null) {
                    thing.discount = Double.parseDouble(JS.get("discount").toString());
                }
                if(JS.get("promotion")!=null){
                    thing.promotion=JS.get("promotion").toString();
                }
                item.add(thing);
            }
            return item;
        }

        public List<goods> compared(List<goods> item) {
            List<goods> index=new ArrayList<goods>();
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
            public List<String> find (String mess)
            {
                Scanner sc = new Scanner(System.in);
                mess=mess.substring(1,mess.length()-1);
                String str[];
                str=mess.split(",");
                List<String> item =new ArrayList<String>();
                for(int i=0;i<str.length;i++)
                {
                      item.add(str[i].substring(3,str[i].length()-1));
                }
                return item;
            }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        pocs fir = new pocs();
        String mess = fir.readJson("C:\\Users\\cyd\\Desktop\\需求四\\src\\data2");
        String mass = fir.readJson("C:\\Users\\cyd\\Desktop\\需求四\\src\\data1");
        List<String> item = new ArrayList<String>();
        item = fir.find(mess);
        List<goods> index = new ArrayList<goods>();
        index = fir.fetch( item,mass);
        index = fir.compared(index);
        System.out.print(fir.count(index));
    }

}







