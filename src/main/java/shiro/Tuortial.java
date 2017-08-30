package shiro;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hzsunguanjun on 2017/6/8.
 */
public class Tuortial {
    private static final transient Logger log = LoggerFactory.getLogger(Tuortial.class);

    public static void main(String[] args) {
        log.info("My First Apache Shiro Application");

//        //1.
//        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//
//        //2.
//        SecurityManager securityManager = factory.getInstance();
//
//        //3.
//        SecurityUtils.setSecurityManager(securityManager);


        System.exit(0);
    }
}

