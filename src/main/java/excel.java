import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by hzsunguanjun on 2017/5/19.
 */
public class excel {

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader("E:\\bankList.txt"));
        String line ;
        while ((line = reader.readLine()) != null){
            System.out.println(line);

        }
    }
}
