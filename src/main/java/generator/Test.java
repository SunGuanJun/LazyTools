package generator;

import org.apache.poi.sl.draw.binding.STBlackWhiteMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hzsunguanjun on 2017/3/10.
 */

class ListItem0{
    List<ListItem1> itemList1;

    public List<ListItem1> getItemList1() {
        return itemList1;
    }

    public void setItemList1(List<ListItem1> itemList1) {
        this.itemList1 = itemList1;
    }
}
class ListItem1{
    List<ListItem2> itemList2;

    public List<ListItem2> getItemList2() {
        return itemList2;
    }

    public void setItemList2(List<ListItem2> itemList) {
        this.itemList2 = itemList;
    }
}

class ListItem2{
    List<String> item;

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }
}


public class Test {
    public static void main(String[] args){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("aa").append("bb");
        System.out.println(stringBuffer.substring(0, stringBuffer.length()-1));

//        BigDecimal a1 = new BigDecimal(0);
//        BigDecimal a2 = new BigDecimal(0.0);
//        System.out.println(a1.equals(a2));
//        System.out.println(a2.equals(BigDecimal.ZERO));
//        System.out.println(a1.compareTo(a2) == 0);

//        String str = "adsas,12,";
//        System.out.println(str.substring(str.indexOf(",")+1));


//        Calendar calendar = Calendar.getInstance();
//        int date = calendar.get(Calendar.DATE);     //当前日期
//        Long currentTime = calendar.getTimeInMillis();
//        calendar.add(Calendar.MONTH, -1);
//        Long aMonthAgo = calendar.getTimeInMillis();
//
//        System.out.println(aMonthAgo);
//        Syste.out.println(currentTime);

//        ListItem0 item0 = new ListItem0();
//        List<ListItem1> item1List = new ArrayList<>();
//        item0.setItemList1(item1List);
//        ListItem1 item1 = new ListItem1();
//        item1List.add(item1);
//        List<ListItem2> item2List = new ArrayList<>();
//        item1.setItemList2(item2List);
//        ListItem2 item2 = new ListItem2();
//        item2List.add(item2);
//        List<String> itemList = new ArrayList<>();
//        itemList.add("sss");
//        item2.setItem(itemList);
//
//
//        System.out.println(JSONObject.toJSONString(item0));

//        BigDecimal n1 = new BigDecimal("1.000");
//        BigDecimal n2 = new BigDecimal("2.0000");
//        BigDecimal n3 = n1.multiply(n2);
//        System.out.println(n3.setScale(2));

//        BigDecimal bd = new BigDecimal(1.0002);
//      System.out.println(bd.setScale(2));

//        Integer a = 2;
//
//        switch (a){
//            case 1 :
//                String str = "hello";
//                System.out.println(str);
//                break;
//            case 2 :
//                str = "world";
//                System.out.println(str);
//                break;
//        }




    }



    public static void test1(){

    }

    public static void test2(List<String> sourceList){
        sourceList.add("eeee");
    }

    public static void testList(){
        List<String> sourceList = new ArrayList<String>();
        sourceList.add("aaaa");
        sourceList.add("bbbb0");
        sourceList.add("cccc0");
        sourceList.add("dddd");

        List<String> newList = new ArrayList<String>();
        for (Iterator iterator = sourceList.iterator(); iterator.hasNext(); ){
            String str = (String)iterator.next();
            if (str.contains("0")){
                newList.add(str);
                iterator.remove();
            }
        }

        for (String str : sourceList){
            System.out.println(str);
        }
        System.out.println();
        for (String str : newList){
            System.out.println(str);
        }
    }

    public static void testCaledar(){
        Calendar calendar = Calendar.getInstance();
//        System.out.println(System.currentTimeMillis() - 24*60*60*1000);
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-1);
        System.out.println(calendar.getTime());
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-3);
        System.out.println(calendar.getTime());
//        System.out.println(calendar.getTimeInMillis());
    }

    public static int getInt (){
        return 1;
    }
}
