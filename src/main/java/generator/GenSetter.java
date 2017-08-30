package generator;

import org.apache.commons.lang.StringUtils;

/**
 * Created by hzsunguanjun on 2017/3/10.
 */
public class GenSetter {
    public static void main(String[] args){
        String varName = "skuInfoVO";
        String str = "private long id;\n" +
                "\n" +
                "     // 商品id\n" +
                "    private long itemId;\n" +
                "\n" +
                "     // 商品名称\n" +
                "    private String itemName;\n" +
                "\n" +
                "     // 条形码\n" +
                "    private String barCode;\n" +
                "\n" +
                "     // 商品规格描述\n" +
                "    private String characterDesc;\n" +
                "\n" +
                "     // 仓库id\n" +
                "    private long storeHouseId;\n" +
                "\n" +
                "     // 是否为组合装，1代表是，0代表不是\n" +
                "    private int isCombine;";


        String[] strArray = str.split("\n");
        String note = "";
        String[] strArray2;
        for (String tmp : strArray){
            if (tmp.contains("//") && tmp.contains(";")){
                strArray2 = tmp.trim().split("//");
                note = strArray2[1];
                strArray2 = tmp.trim().split(" ");
                System.out.println(varName + ".set" + StringUtils.capitalize(strArray2[2].substring(0, strArray2[2].length()-1)) + "(); //" + note);
            }
            else if (tmp.contains("//")){
                note = tmp.trim().substring(2);
            }
            else if (tmp.contains(";")){
                strArray2 = tmp.trim().split(" ");
                System.out.println(varName + ".set" + StringUtils.capitalize(strArray2[2].substring(0, strArray2[2].length()-1)) + "(); //" + note);
            }
        }
    }
}
