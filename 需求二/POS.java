/**
 * Created by cyd on 2016/1/5.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class POS {
        public static void main(String[] args)
        {
            Scanner sc = new Scanner(System.in);
            List<GOODS> item =new ArrayList<GOODS>();
            List<GOODS> index=new ArrayList<GOODS>();
            sc.next();
            while(sc.next().compareTo("]")!=0)
            {
                GOODS thing = new GOODS();
                sc.next();
                thing.barcode=sc.next();
                thing.barcode=thing.barcode.substring(1, thing.barcode.length()-2);
                sc.next();
                thing.name=sc.next();
                thing.name=thing.name.substring(1, thing.name.length()-2);
                sc.next();
                thing.unit=sc.next();
                thing.unit=thing.unit.substring(1, thing.unit.length()-2);
                sc.next();
                thing.price=sc.nextDouble();
                sc.next();
                thing.discount=sc.nextDouble();
                item.add(thing);
                sc.next();
            }
            for(int i=0;i<item.size();i++)
            {
                if(index.size()==0) index.add(item.get(i));
                else
                {
                    int dex=0;
                    for(int j=0;j<index.size();j++)
                    {
                        if(index.get(j).barcode.toString().compareTo(item.get(i).barcode.toString())==0)
                        {
                            index.get(j).num++;
                            dex=1;
                            break;
                        }
                    }
                    if(dex==0)
                    {
                        index.add(item.get(i));
                    }
                }
            }
            java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00");
            double total=0;
            double total1=0;
            System.out.print("***商品购物清单***\n");
            for(int i=0;i<index.size();i++)
            {
                System.out.print("名称："+index.get(i).name+",数量:"+index.get(i).num+index.get(i).unit+",单价："
                        +df.format(index.get(i).price)+"(元)，小计："+
                        df.format(index.get(i).price*index.get(i).num*index.get(i).discount)+"(元)\n");
                total=total+index.get(i).price*index.get(i).num;
                total1=total1+index.get(i).price*index.get(i).num*index.get(i).discount;
            }
            System.out.print("- - - - - - - - - - - - - -\n");
            System.out.print("总计："+df.format(total)+"(元)\n"+"节省："+df.format(total1=total-total1)+"(元)\n***************************");
        }
    }
