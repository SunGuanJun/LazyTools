package Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hzsunguanjun on 2017/6/16.
 */
public class App2 {
    public static void main(String args[]){
        List<String> stringList = new ArrayList<>();
        stringList.add("aaaa");
        stringList.add("bbb");
        stringList.add("cccc");
        stringList.add("dddd");

        for (Iterator iterator = stringList.iterator(); iterator.hasNext();){
            String tmp = (String) iterator.next();
            System.out.println(tmp);
            if (tmp.equals("cccc")){
                iterator.remove();
            }
        }

        for (Iterator iterator = stringList.iterator(); iterator.hasNext();){
            String tmp = (String) iterator.next();
            System.out.println(tmp);
        }
    }
}
