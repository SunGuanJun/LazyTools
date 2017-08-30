package Test;

/**
 * Created by hzsunguanjun on 2017/7/7.
 */
public class Finance2 {

    public static void main(String[] args){
        Finance2 app = new Finance2();
        Integer id = 339;

        String source = app.getSource();
        String[] lineArr = source.split("\\\n");
        for (String line : lineArr){
            String[] words = line.split(",");
            if (words.length > 1 ){
                if (!words[8].trim().equals("0")){
                    StringBuffer sb = new StringBuffer();
                    sb.append("insert into TB_YX_PMS_FIN_VERIFICATION values(" + id + ",");
                    sb.append(words[1] + ",");
                    sb.append(words[2] + ",");
                    sb.append(words[3] + ",");
                    sb.append(words[4] + ",");
                    sb.append(words[5] + ",");
                    sb.append(words[6] + ",");
                    sb.append(words[7] + ",");
                    sb.append(words[8] + ",");
                    sb.append(words[9] + ",");
                    sb.append(words[8] + ",");
                    sb.append(words[11] + ",");
                    sb.append(words[12] + ",");
                    sb.append(words[13] + ",");
                    sb.append(words[14] + ",");
                    sb.append(words[15] + ",");
                    sb.append(words[16]);
                    id++;
                    System.out.println(sb.toString());
                }
            }
            else {
                System.out.println();
            }

        }
    }

    public String getSource(){
        return "insert into TB_YX_PMS_FIN_VERIFICATION values(8, 0, 'unCreated', 'Isf-170421-000011', 'YC16120726-20170412-1', 1165206, '安抚巾', 0, 1860, 32643.00, 0, 0, 0, 0, 'admin', 1500341991194, NULL); \n" +
                "insert into TB_YX_PMS_FIN_VERIFICATION values(9, 0, 'unCreated', 'Isf-170421-000011', 'YC16120726-20170412-1', 1165207, '安抚巾', 0, 1860, 32643.00, 0, 0, 0, 0, 'admin', 1500341991194, NULL); \n" +
                "insert into TB_YX_PMS_FIN_VERIFICATION values(10, 0, 'unCreated', 'Isf-170421-000011', 'YC16120726-20170412-1', 1165208, '儿童摇铃', 0, 1858, 25993.42, 0, 0, 0, 0, 'admin', 1500341991194, NULL); \n" +
                "\n" +
                "insert into TB_YX_PMS_FIN_VERIFICATION values(5, 0, 'unCreated', 'Idgsf-170421-000011', 'YC16120726-20170412-1', 1165206, '安抚巾', 0, 620, 10881.00, 0, 0, 0, 0, 'admin', 1500341991194, NULL); \n" +
                "insert into TB_YX_PMS_FIN_VERIFICATION values(6, 0, 'unCreated', 'Idgsf-170421-000011', 'YC16120726-20170412-1', 1165207, '安抚巾', 0, 617, 10828.35, 0, 0, 0, 0, 'admin', 1500341991194, NULL); \n" +
                "insert into TB_YX_PMS_FIN_VERIFICATION values(7, 0, 'unCreated', 'Idgsf-170421-000011', 'YC16120726-20170412-1', 1165208, '儿童摇铃', 0, 620, 8673.80, 0, 0, 0, 0, 'admin', 1500341991194, NULL);";

    }
}
