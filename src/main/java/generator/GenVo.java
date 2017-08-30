package generator;

/**
 * Created by hzsunguanjun on 2017/3/21.
 */
public class GenVo {
    public static void main(String[] args){
        String str = "| writeOffDetailVO  | [FIN_TransitWriteOffDetailVO|yanxuan:FIN_TransitWriteOffDetailVO] | 核销明细  |\n" +
                "| deliveredAmountVO  | [FIN_TransitDeliveredAmountVO|FIN_TransitDeliveredAmountVO] | 到货核销时，需传入到货金额信息  |\n" +
                "| vendorVO  | [FIN_VendorVO|yanxuan:FIN_VendorVO] | 供应商信息  |";

        String[] strArr = str.split("\\n");
        String varName;
        String varType;
        String note;
        StringBuffer sb = new StringBuffer();
        for (String tmp : strArr){
            tmp.replaceAll("\\\\", ""); //去掉多余的\
            String[] strArr2 = tmp.split("\\|");
            if (tmp.contains("[")){
                varName = strArr2[1];
                varType = strArr2[2].replaceAll("\\[","");
                if (varType.contains("<")){
                    varType += ">";
                }
                note = strArr2[4];
            }
            else {
                varName = strArr2[1];
                varType = strArr2[2];
                note = strArr2[3];
            }

            sb.append("private " + varType.trim() + " " + varName.trim() + "; //" + note.trim() + "\n");
        }

        System.out.println(sb);
    }
}
