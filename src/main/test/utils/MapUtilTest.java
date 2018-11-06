package utils;

import domain.Student;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hzsunguanjun@corp.netease.com
 * @Date 2018/11/6
 */
public class MapUtilTest {

    private List<Student> studentList = MockUtil.genMockList(Student.class, 1000000);

    @Test
    public void extractByDeclaredFieldTest1(){
        long start = System.currentTimeMillis();
        Map<Long, List<Student>> studentMap = MapUtils.extractByDeclaredField(studentList, "id");
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void extractByDeclaredFieldTest2(){
        long start = System.currentTimeMillis();
        Map<Long, List<Student>> studentMap = new HashMap<>();
        for (Student student : studentList){
            MapUtils.putIfAbsent(studentMap, student.getId(), student);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
