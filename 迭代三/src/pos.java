/**
 * Created by cyd on 2016/1/5.
 */
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class pos {

    public static class pocs {
        public pocs()
        {}
        public String count(List<goods> index,List<String> item) {
            java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年/MM月/dd日 hh:mm:ss");
            String time=sdf.format(new Date(System.currentTimeMillis()));
            double total = 0;
            double total1=0;
            String str = "";
            String str1 = "";
            String str2 = "";
            str = str + "打印时间: "+time+"\n- - - - - - - - - - - - - -\n";
            for(int i=0;i<index.size();i++)
            {
                if(item.get(1).compareTo("false")==0) {
                    if (index.get(i).promotion.equals("null")) {
                        str = str + "名称：" + index.get(i).name + ",数量:" + index.get(i).num + index.get(i).unit + ",单价："
                                + df.format(index.get(i).price) + "(元)，小计：" +
                                df.format(index.get(i).price * index.get(i).num * index.get(i).discount) + "(元)\n";
                        total = total + index.get(i).price * index.get(i).num;
                        total1 = total1 + index.get(i).price * index.get(i).num * index.get(i).discount;
                    } else {
                        if (index.get(i).num % 3 == 0) {
                            str = str + "名称：" + index.get(i).name + ",数量:" + index.get(i).num + index.get(i).unit + ",单价："
                                    + df.format(index.get(i).price) + "(元)，小计：" +
                                    df.format(index.get(i).price * index.get(i).num * 2 / 3) + "(元)\n";
                            total = total + index.get(i).price * index.get(i).num;
                            total1 = total1 + index.get(i).price * index.get(i).num * 2 / 3;
                        } else if (index.get(i).num % 3 == 1) {
                            str = str + "名称：" + index.get(i).name + ",数量:" + index.get(i).num + index.get(i).unit + ",单价："
                                    + df.format(index.get(i).price) + "(元)，小计：" +
                                    df.format(index.get(i).price * (index.get(i).num - 1) * 2 / 3 + index.get(i).price) + "(元)\n";
                            total = total + index.get(i).price * index.get(i).num;
                            total1 = total1 + index.get(i).price * (index.get(i).num - 1) * 2 / 3 + index.get(i).price;
                        } else if (index.get(i).num % 3 == 2) {
                            str = str + "输入商品件数错误，请检查后重新输入！\n";
                            return str;
                        }
                        str1 = str1 + "名称：" + index.get(i).name + ",数量：" + (int) index.get(i).num / 3 + index.get(i).unit + "\n";
                    }
                }
                else
                {
                    str = str + "名称：" + index.get(i).name + ",数量:" + index.get(i).num + index.get(i).unit + ",单价："
                            + df.format(index.get(i).price) + "(元)，小计：" +
                            df.format(index.get(i).price * index.get(i).num * index.get(i).discount*index.get(i).vipDiscount) + "(元)\n";
                    total = total + index.get(i).price * index.get(i).num;
                    total1 = total1 + index.get(i).price * index.get(i).num * index.get(i).discount*index.get(i).vipDiscount;
                }
            }
            str=str+"- - - - - - - - - - - - - -\n" +
                    "挥泪赠送商品：\n"+str1+"- - - - - - - - - - - - - -\n";
            str=str+"总计："+df.format(total1)+"(元)\n"+"节省："+df.format(total-total1)+"(元)\n***************************";
            if(item.get(1).compareTo("false")==0||item.size()==2)
            {
                str2=str2+"***商店购物清单***\n- - - - - - - - - - - - - -\n";
            }
            else
            {
                int point=Integer.valueOf(item.get(2)).intValue();
                if(0<=point&&point<=200)        point=point+(int)total1/5;
                else if(200<point&&point<=500)          point=point+(int)total1/5*3;
                else if(point>500)          point=point+(int)total1/5*5;
                item.set(2,String.valueOf(point));
                str2=str2+"***商店购物清单***\n会员编号 ："+item.get(0)+"   会员积分  ："+point+"分\n- - - - - - - - - - - - - -\n";
            }
            str=str2+str;
            if(item.size()==3)     this.changed(item);
            return str;
        }
        public void changed(List<String> item)
        {
            String mess=this.readJson("C:\\Users\\cyd\\Desktop\\迭代三\\src\\data1");
            JSONObject jsonobject =  JSONObject.fromObject(mess);
            Iterator it = jsonobject.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                JSONObject JS = jsonobject.getJSONObject(key);
                if(JS.get("name").toString().compareTo(item.get(0))==0)
                {
                    JS.put("point",Integer.valueOf(item.get(2)).intValue());
                    this.writeJSon("C:\\Users\\cyd\\Desktop\\迭代三\\src\\data1",jsonobject.toString());
                }
            }

        }
        public  void writeJSon(String path,String  mess)
        {
            FileWriter fw = null;
            try {
                fw = new FileWriter(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter out = new PrintWriter(fw);
            out.write(mess+"\n/*{\"USER001\":{\"name\":\"USER 001\",\"isVip\":true},\"USER002\":{\"name\":\"USER 002\",\"isVip\":false},\"USER003\":{\"name\":\"USER 003\",\"isVip\":true}}");
            out.println();
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.close();
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
                thing.barcode=index.get(k);
                thing.name = JS.get("name").toString();
                thing.unit = JS.get("unit").toString();
                thing.price = Double.parseDouble(JS.get("price").toString());
                if(JS.get("discount")!=null) {
                    thing.discount = Double.parseDouble(JS.get("discount").toString());
                }
                if(JS.get("promotion")!=null){
                    thing.promotion=JS.get("promotion").toString();
                }
                if(JS.get("vipDiscount")!=null)
                {
                    thing.vipDiscount= Double.parseDouble(JS.get("vipDiscount").toString());
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
            mess=mess.substring(1,mess.length()-1);
            String str[];
            str=mess.split(",");
            List<String> item =new ArrayList<String>();
            for(int i=0;i<str.length;i++)
            {
                item.add(str[i].substring(1,str[i].length()-1));
            }
            return item;
        }
        public  List<String> getUser(String mess)
        {
            JSONObject jsonarray = JSONObject.fromObject(mess);
            List<String> item = new ArrayList<String>();
            item.add(jsonarray.get("user").toString());
            JSONArray JS = jsonarray.getJSONArray("items");
            item.add(JS.toString());
            return item;
        }
        public List<String> IsVip(String user)
        {
            String mess=this.readJson("C:\\Users\\cyd\\Desktop\\迭代三\\src\\data1");
            JSONObject jsonobject =  JSONObject.fromObject(mess);
            List<String> item = new ArrayList<String>();
            JSONObject JS = jsonobject.getJSONObject(user);
            if(!JS.isEmpty()) {
                item.add(JS.get("name").toString());
                item.add(JS.get("isVip").toString());
                if(JS.size()==3)
                    item.add(JS.get("point").toString());
            }
            else
            {
                item.add("nouser");
            }
            return item;
        }
        public List<String> Adduser( )
        {
            List<String> item = new ArrayList<String>();
            Scanner sc=new Scanner(System.in);
            System.out.print("请输入会员信息\n");
            String user=sc.next();
            String name=sc.next();
            String isVip=sc.next();
            String mess=this.readJson("C:\\Users\\cyd\\Desktop\\迭代三\\src\\data1");
            JSONObject jsonobject =  JSONObject.fromObject(mess);
            JSONObject JS2= new JSONObject();
            JS2.put("name",name);
            JS2.put("isVip",isVip);
            JS2.put("point",0);
            jsonobject.put(user,JS2);
            this.writeJSon("C:\\Users\\cyd\\Desktop\\迭代三\\src\\data1",jsonobject.toString());
            item.add(name);
            item.add(isVip);
            item.add("0");
            return  item;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        pocs fir = new pocs();
        String mess = fir.readJson("C:\\Users\\cyd\\Desktop\\迭代三\\src\\data2");
        String mass = fir.readJson("C:\\Users\\cyd\\Desktop\\迭代三\\src\\data3");
        List<String> item = new ArrayList<String>();
        List<String> item1 = new ArrayList<String>();
        List<String> item2 = new ArrayList<String>();
        item = fir.getUser(mess);
        item1=fir.IsVip(item.get(0));
        if(item1.get(0).compareTo("nouser")==0)
        {
            System.out.print("该顾客尚未成为会员，请问是否需要添加为会员?(Y/N)\n");
            String choose = sc.next();
            if(choose.compareTo("Y")==0||choose.compareTo("y")==0)
            {
                item1=fir.Adduser();
            }
            else
            {
                item1.set(0,"非会员");
                item1.add("false");
            }
        }
        item2=fir.find(item.get(1));
        List<goods> index = new ArrayList<goods>();
        index = fir.fetch( item2,mass);
        index = fir.compared(index);
        System.out.print(fir.count(index,item1));
    }

}







