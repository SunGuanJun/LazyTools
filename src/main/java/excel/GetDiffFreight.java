package excel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hzsunguanjun on 2017/5/23.
 */
public class GetDiffFreight {
    /**
     * 解析TXT文件
     * 获取各仓库运费不等的采购单号
     */
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader("E:\\pms\\freight4.txt"));

        String line ;
        String[] strArray;
        BigDecimal freight1 = BigDecimal.ZERO;
        BigDecimal freight2;
        Set<String> purchaseSet = new HashSet<>();

        Map<String, BigDecimal> poSkuFreMap = new HashMap<>();
        String key ;
        while ((line = reader.readLine()) != null){
            strArray = line.split("\\|");
            key = strArray[1].trim() + "-" + strArray[2].trim();
            freight2 = new BigDecimal(strArray[3].trim());

            freight1 = poSkuFreMap.get(key);
            if (freight1 == null){
                poSkuFreMap.put(key, freight2);
            }
            else{
//                System.out.println(freight1 + " " + freight2);
                if (freight1.compareTo(freight2) != 0){
                    purchaseSet.add(strArray[1].trim());
                }
            }
        }

        for (String purchaseOrder:purchaseSet){
            System.out.println(purchaseOrder);
        }
    }
}
