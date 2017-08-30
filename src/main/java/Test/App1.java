package Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hzsunguanjun on 2017/5/23.
 */
public class App1 {
    public static void main(String[] args){
//        int date = calendar.get(Calendar.DATE);     //当前日期
//        Long currentTime = calendar.getTimeInMillis();
//        calendar.add(Calendar.MONTH, -1);
//        Long aMonthAgo = calendar.getTimeInMillis();


        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
//        int date = calendar.get(Calendar.DATE);     //当前日期
//        Long currentTime = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 000);
        System.out.println(calendar.getTime());

        System.out.println(calendar.getTime().getTime() - 24 * 60 * 60 * 1000L);


        //得到上月月初
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 000);
        Long beginTime = calendar.getTime().getTime();

        //得到上月月末
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Long endTime = calendar.getTime().getTime();

        System.out.println(beginTime);
        System.out.println(endTime);


        Set<String> inNumberSet1 = new HashSet<>();
        Set<String> inNumberSet2 = new HashSet<>();

        inNumberSet1.add("aaaa");
        inNumberSet1.add("ccc");
        inNumberSet1.add("ddd");


        inNumberSet2.add("aaaa");
        inNumberSet2.add("bbb");
        inNumberSet2.add("ddd");

        inNumberSet1.retainAll(inNumberSet2);

        System.out.println(inNumberSet1.toString());
    }
}
