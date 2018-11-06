package generator;

import domain.Mockable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hzsunguanjun@corp.netease.com
 * @Date 2018/11/6
 */
public class GenDataUtil {


    public static <T extends Mockable> List<T> genMockList(int size){
        List<T> result = new ArrayList<>();

        for (int i=0; i<size; i++){

        }

        return result;
    }

}
