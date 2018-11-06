package utils;

import domain.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hzsunguanjun@corp.netease.com
 * @Date 2018/11/6
 */
public class MockUtilTest {

    /**
     * genMockList 性能测试
     */
    @Test
    public void genMockListPTest(){
        long start = System.currentTimeMillis();
        MockUtil.genMockList(Student.class, 100000);
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void genMockListBenchPTest(){
        long start = System.currentTimeMillis();
        List<Student> studentList = new ArrayList();
        for (int i=0; i<100000; i++){
            Student student = new Student();
            student.setName("ss");
            student.setGender(1);
            student.setAge(1);
            student.setId(1L);
            studentList.add(student);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
