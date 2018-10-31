package utils;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Author hzsunguanjun@corp.netease.com
 * @Date 2018/10/31
 */
public class ListUtilTest {

    @Test
    public void removeDuplicateTest(){
        List<Integer> a = Arrays.asList(1,1,2,2,3);
        List<Integer> b = ListUtil.removeDuplicate(a);
        System.out.println(JSON.toJSONString(b));
    }

}
