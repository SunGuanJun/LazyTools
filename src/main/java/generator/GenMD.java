package generator;

/**
 * Created by hzsunguanjun on 2017/3/8.
 */
public class GenMD {
    public static void main(String[] args){
        String str = "//id\n" +
                "    private Long id;\n" +
                "\n" +
                "    //原因\n" +
                "    private String reason;\n" +
                "\n" +
                "    //原因代码\n" +
                "    private String reasonCode;\n" +
                "\n" +
                "    //原因等级,其他为0,一级为1\n" +
                "    private Integer level;";


        System.out.println("# 库内产品巡检信息\n" +
            "|| 参数 || 类型 || 说明 || 版本 ||");
        String[] strArray = str.split("\n");
        String note = "";
        String[] strArray2;
        for (String tmp : strArray){
            if (tmp.contains("//") && tmp.contains(";")){
                strArray2 = tmp.trim().split("//");
                note = strArray2[1];
                strArray2 = tmp.trim().split(" ");
                System.out.println("| " + strArray2[2].substring(0, strArray2[2].length()-1) + " | " + strArray2[1] + " | " + note + " | |");
            }
            else if (tmp.contains("//")){
                note = tmp.trim().substring(2);
            }
            else if (tmp.contains(";")){
                strArray2 = tmp.trim().split(" ");
                System.out.println("| " + strArray2[2].substring(0, strArray2[2].length()-1) + " | " + strArray2[1] + " | " + note + " | |");
            }
        }
    }
}
