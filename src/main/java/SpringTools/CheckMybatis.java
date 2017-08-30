package SpringTools;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.NestedIOException;

import java.io.IOException;

/**
 * Created by hzsunguanjun on 2017/4/5.
 */
public class CheckMybatis extends SqlSessionFactoryBean{
    @Override
    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
        try{
            return super.buildSqlSessionFactory();
        } catch (NestedIOException e){
            e.printStackTrace();
            System.out.println("==================================================================================");
            System.out.println("==================================================================================");
            System.out.println("==================================================================================");
            System.out.println("==================================================================================");
            System.out.println("==================================================================================");
            System.out.println("==================================================================================");
            System.out.println("==================================================================================");
            System.out.println("==================================================================================");

            throw new NestedIOException("Failed to parse mapping resource: ", e);
        }finally {
            ErrorContext.instance().reset();
        }
    }
}
