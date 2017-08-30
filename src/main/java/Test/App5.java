package Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by hzsunguanjun on 2017/7/27.
 */
public class App5 {
    public static void main(String[] args){
        try {
            System.out.println(URLDecoder.decode("56iO6YeR57%20y05qy%252B5LmmLnBkZg%253D%253D", "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
