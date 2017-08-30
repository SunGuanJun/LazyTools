package Test;

import sun.awt.image.BadDepthException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hzsunguanjun on 2017/7/28.
 */
public class Adj {

    public static void main(String[] args){
        Adj app = new Adj();
        Map<String, BigDecimal> excelMap = app.getExcelDataMap(app.getExcelData());
        List<Po> poList = app.getPoList(app.getDbData());

        System.out.println("\n\n\ndata -----start-----");

        BigDecimal actualMoney, adjMoney;
        for (Po po : poList){
            actualMoney = excelMap.get(po.getPurchaseOrder());
            if (actualMoney != null){
                adjMoney = actualMoney.subtract(po.getTheoryMoney());
                if (po.getActualMoney().compareTo(actualMoney) != 0){
                    System.out.println(String.format("update TB_YX_PMS_FIN_PAYMENT_DETAIL set adjustMoney=%s, actualApplyMoney=%s where paymentNum='%s';",
                            adjMoney, actualMoney, po.getPaymentNum()));

                    if (adjMoney.compareTo(BigDecimal.ZERO) == 0){
                        System.out.println(String.format("delete from TB_YX_PMS_FIN_ADJUST where rkey='%s' and keyType=1;", po.getPaymentNum()));
                    }
                    else {
                        System.out.println(String.format("update TB_YX_PMS_FIN_ADJUST set adjustMoney = %s where rkey='%s' and keyType=1;",
                                adjMoney, po.getPaymentNum()));
                    }
                }
                else {
                    System.out.println(po.getPurchaseOrder() + " 实际请款金额无差异");
                }

            }
            else {
                System.out.println(po.getPurchaseOrder() + " 实际请款金额为null");
            }

        }

        System.out.println("data -----end-----");
    }


    public Map<String, BigDecimal> getExcelDataMap(String rawData){
        Map<String, BigDecimal> excelMap = new HashMap<>();
        String lines[] = rawData.split("\\\n");
        System.out.println("\n\n\ntest excel data -----start-----");
        for (String line : lines){
            String words[] = line.split(",");
            excelMap.put(words[0], new BigDecimal(words[1].trim()));
            System.out.println(excelMap.get(words[0]));
        }
        System.out.println("test excel data -----end-----");

        return excelMap;
    }

    public List<Po> getPoList(String rawData){
        List<Po> poList = new ArrayList<>();

        String[] lines = rawData.split("\\\n");
        System.out.println("\n\n\ntest db data -----start-----");
        for (String line : lines){
            String[] words = line.split("\\|");

            Po po = new Po();
            po.setPurchaseOrder(words[1].trim());
            po.setPaymentNum(words[2].trim());
            po.setTheoryMoney(new BigDecimal(words[3].trim()));
            po.setAdjMoney(new BigDecimal(words[4].trim()));
            po.setActualMoney(new BigDecimal(words[5].trim()));
            poList.add(po);

            System.out.println(po.toString());
        }
        System.out.println("test db data -----end-----");

        return poList;
    }

    private String getDbData(){
        return "| YC17030294-20170424-2   | PMSP-YC17030294-3  |        101008.80 |        0.20 |        101009.00 |\n" +
                "| YC16120683-20170425-1   | PMSP-YC16120683-2  |         81820.00 |    81820.00 |        163640.00 |\n" +
                "| YC17030319-20170426-1   | PMSP-YC17030319-3  |        533901.60 |        0.40 |        533902.00 |\n" +
                "| YC17030319-20170426-2   | PMSP-YC17030319-4  |        372499.20 |       -0.20 |        372499.00 |\n" +
                "| YC17040395-20170425-36  | PMSP-YC17040395-3  |         17671.50 |        0.50 |         17672.00 |\n" +
                "| YC17030155-20170426-2   | PMSP-YC17030155-4  |        190321.32 |       -0.32 |        190321.00 |\n" +
                "| YC17030155-20170426-3   | PMSP-YC17030155-5  |        427234.41 |       -0.41 |        427234.00 |\n" +
                "| YC16060062-20170425-72  | PMSP-YC16060062-1  |         29047.20 |       -0.20 |         29047.00 |\n" +
                "| YC17030266-20170426-1   | PMSP-YC17030266-5  |        230699.70 |        0.30 |        230700.00 |\n" +
                "| YC17040452-20170428-1   | PMSP-YC17040452-2  |       2039105.52 |        0.48 |       2039106.00 |\n" +
                "| YC17030319-20170430-1   | PMSP-YC17030319-5  |        243669.60 |        0.40 |        243670.00 |\n" +
                "| YC17030319-20170430-2   | PMSP-YC17030319-6  |        243878.40 |       -0.40 |        243878.00 |\n" +
                "| YC17030319-20170430-3   | PMSP-YC17030319-7  |        243878.40 |       -0.40 |        243878.00 |\n" +
                "| YC17030241-20170503-133 | PMSP-YC17030241-6  |         57182.40 |       -0.40 |         57182.00 |\n" +
                "| YC16120683-20170504-1   | PMSP-YC16120683-3  |         44800.00 |    44800.00 |         89600.00 |\n" +
                "| YC16120683-20170508-1   | PMSP-YC16120683-4  |         97906.00 |    97906.00 |        195812.00 |\n" +
                "| YC17030241-20170508-126 | PMSP-YC17030241-7  |        145821.60 |        0.40 |        145822.00 |\n" +
                "| YC17040448-20170510-2   | PMSP-YC17040448-5  |         57382.80 |        0.20 |         57383.00 |\n" +
                "| YC17040448-20170510-3   | PMSP-YC17040448-6  |         52311.90 |        0.10 |         52312.00 |\n" +
                "| YC16050021-20170508-74  | PMSP-YC16050021-1  |         30730.56 |        0.44 |         30731.00 |\n" +
                "| YC16120738-20170503-89  | PMSP-YC16120738-1  |        101875.20 |       -0.20 |        101875.00 |\n" +
                "| YC17030191-20170504-1   | PMSP-YC17030191-1  |        492183.43 |       -0.43 |        492183.00 |\n" +
                "| YC16060116-20170306-2   | PMSP-YC16060116-2  |        263236.50 |       -0.50 |        263236.00 |\n" +
                "| YC16060129-20170307-1   | PMSP-YC16060129-1  |        608990.40 |       -0.40 |        608990.00 |\n" +
                "| YC16060192-20170310-3   | PMSP-YC16060192-1  |          6134.40 |       -0.40 |          6134.00 |\n" +
                "| YC16070226-20170314-84  | PMSP-YC16070226-1  |         40748.70 |        0.30 |         40749.00 |\n" +
                "| YC16060192-20170314-70  | PMSP-YC16060192-2  |         82938.72 |        0.28 |         82939.00 |\n" +
                "| YC16070226-20170328-87  | PMSP-YC16070226-3  |         31109.40 |       -0.40 |         31109.00 |\n" +
                "| YC16060192-20170321-45  | PMSP-YC16060192-3  |          7270.56 |        0.44 |          7271.00 |\n" +
                "| YC16060192-20170328-69  | PMSP-YC16060192-4  |          9692.35 |       -0.35 |          9692.00 |\n" +
                "| YC16120726-20170412-1   | PMSP-YC16120726-1  |         58701.60 |        0.40 |         58702.00 |\n" +
                "| YC17020086-20170323-2   | PMSP-YC17020086-6  |        720900.00 |  -390000.00 |        330900.00 |\n" +
                "| YC17040371-20170331-1   | PMSP-YC17040371-1  |        653500.00 |  -261400.00 |        392100.00 |\n" +
                "| YC17030245-20170401-1   | PMSP-YC17030245-3  |        154112.40 |       -0.40 |        154112.00 |\n" +
                "| YC17030319-20170401-7   | PMSP-YC17030319-11 |        147882.60 |        0.40 |        147883.00 |\n" +
                "| YC17030319-20170401-8   | PMSP-YC17030319-12 |        147882.60 |        0.40 |        147883.00 |\n" +
                "| YC17030319-20170401-11  | PMSP-YC17030319-13 |        865893.60 |        0.40 |        865894.00 |\n" +
                "| YC17040405-20170410-85  | PMSP-YC17040405-1  |         81757.44 |       -0.44 |         81757.00 |\n" +
                "| YC17020058-20170416-1   | PMSP-YC17020058-1  |         58162.50 |       -0.50 |         58162.00 |\n" +
                "| YC17030144-20170417-95  | PMSP-YC17030144-1  |       1622780.64 |  -270463.64 |       1352317.00 |\n" +
                "| YC17030319-20170417-101 | PMSP-YC17030319-14 |        424960.20 |       -0.20 |        424960.00 |\n" +
                "| YC17030241-20170417-105 | PMSP-YC17030241-8  |         39009.60 |        0.40 |         39010.00 |\n" +
                "| YC17030336-20170417-126 | PMSP-YC17030336-4  |        456652.44 |       -0.44 |        456652.00 |\n" +
                "| YC17030306-20170427-3   | PMSP-YC17030306-1  |         14437.50 |        0.50 |         14438.00 |\n" +
                "| YC16070276-20170103-1   | PMSP-YC16070276-1  |         48000.00 |   -36000.00 |         12000.00 |\n" +
                "| YC17030335-20170315-1   | PMSP-YC17030335-6  |        259440.00 |  -140100.00 |        119340.00 |\n" +
                "| YC17030337-20170322-1   | PMSP-YC17030337-1  |         33868.80 |        0.20 |         33869.00 |\n" +
                "| YC17030242-20170418-1   | PMSP-YC17030242-5  |         23348.16 |       -0.16 |         23348.00 |\n" +
                "| YC17020117-20170417-86  | PMSP-YC17020117-4  |          4762.80 |        0.20 |          4763.00 |\n" +
                "| YC17030248-20170420-1   | PMSP-YC17030248-2  |        118922.40 |       -0.40 |        118922.00 |\n" +
                "| YC16060062-20170419-3   | PMSP-YC16060062-2  |        645214.50 |       -0.50 |        645214.00 |\n" +
                "| YC16060062-20170417-62  | PMSP-YC16060062-3  |        729744.60 |        0.40 |        729745.00 |\n" +
                "| YC17030268-20170420-1   | PMSP-YC17030268-1  |        255000.00 |   -30000.00 |        225000.00 |\n" +
                "| YC17030159-20170428-2   | PMSP-YC17030159-1  |        169781.47 |       -0.47 |        169781.00 |\n" +
                "| YC16060192-20170411-6   | PMSP-YC16060192-6  |         13640.40 |       -0.40 |         13640.00 |\n" +
                "| YC17040371-20170512-12  | PMSP-YC17040371-5  |         14152.32 |       -0.32 |         14152.00 |\n" +
                "| YC17030163-20170512-48  | PMSP-YC17030163-2  |        102471.91 |        0.09 |        102472.00 |\n" +
                "| YC17030253-20170512-10  | PMSP-YC17030253-1  |          2350.08 |       -0.08 |          2350.00 |\n" +
                "| YC16120703-20170227-27  | PMSP-YC16120703-1  |         24132.60 |        0.40 |         24133.00 |\n" +
                "| YC16120729-20170328-115 | PMSP-YC16120729-1  |         12036.96 |        0.04 |         12037.00 |\n" +
                "| YC17030186-20170323-33  | PMSP-YC17030186-3  |          6946.56 |        0.44 |          6947.00 |\n" +
                "| YC17030186-20170331-42  | PMSP-YC17030186-4  |          4036.08 |       -0.08 |          4036.00 |\n" +
                "| YC17030242-20170512-17  | PMSP-YC17030242-10 |         15035.33 |       -0.33 |         15035.00 |\n" +
                "| YC17040424-20170512-19  | PMSP-YC17040424-16 |         23364.72 |        0.28 |         23365.00 |";
    }

    private String getExcelData(){
        return "YC16050021-20170508-74,30730.56\n" +
                "YC16060062-20170425-72,29047.2\n" +
                "YC16120683-20170425-1,163640\n" +
                "YC16120683-20170504-1,89600\n" +
                "YC16120683-20170508-1,195812\n" +
                "YC17030155-20170426-2,190321.33\n" +
                "YC17030155-20170426-3,427234.42\n" +
                "YC17030159-20170428-2,169781.48\n" +
                "YC17030241-20170503-133,57182.4\n" +
                "YC17030241-20170508-126,145821.6\n" +
                "YC17030266-20170426-1,230699.7\n" +
                "YC17030294-20170424-2,101008.8\n" +
                "YC17030319-20170426-1,533901.6\n" +
                "YC17030319-20170426-2,372499.2\n" +
                "YC17030319-20170430-1,243669.6\n" +
                "YC17030319-20170430-2,243878.4\n" +
                "YC17030319-20170430-3,243878.4\n" +
                "YC17040395-20170425-36,17671.5\n" +
                "YC17040448-20170510-2,57382.8\n" +
                "YC17040448-20170510-3,52311.9\n" +
                "YC17040452-20170428-1,2039105.52\n" +
                "YC17030241-20170503-133,57182.4\n" +
                "YC17030241-20170508-126,145821.6\n" +
                "YC17030191-20170504-1,492183.43\n" +
                "YC16120738-20170503-89,101875.2\n" +
                "YC16050021-20170508-74,30730.56\n" +
                "YC17030144-20170417-95,1352317.2\n" +
                "YC17020117-20170417-86,4762.8\n" +
                "YC17030268-20170420-1,225000\n" +
                "YC17020058-20170416-1,58162.5\n" +
                "YC16060129-20170307-1,608990.22\n" +
                "YC17030241-20170417-105,39009.6\n" +
                "YC17030336-20170417-126,456652.44\n" +
                "YC17030245-20170401-1,154112.4\n" +
                "YC17030337-20170322-1,33868.8\n" +
                "YC17030242-20170418-1,23348.16\n" +
                "YC17020086-20170323-2,330900\n" +
                "YC17030319-20170401-11,865893.6\n" +
                "YC17030319-20170401-7,147882.6\n" +
                "YC17030319-20170401-8,147882.6\n" +
                "YC17030319-20170417-101,424960.2\n" +
                "YC16070226-20170314-84,40748.7\n" +
                "YC16070226-20170328-87,31109.4\n" +
                "YC16070276-20170103-1,12000\n" +
                "YC16060192-20170310-3,6134.4\n" +
                "YC16060192-20170314-70,82938.72\n" +
                "YC16060192-20170321-45,7270.56\n" +
                "YC16060192-20170328-69,9692.352\n" +
                "YC16060116-20170306-2,263236.5\n" +
                "YC17030248-20170420-1,118922.4\n" +
                "YC17040405-20170410-85,81757.44\n" +
                "YC16120726-20170412-1,58701.6\n" +
                "YC16060062-20170417-62,729744.6\n" +
                "YC16060062-20170419-3,645214.5\n" +
                "YC17040371-20170331-1,392100\n" +
                "YC17030335-20170315-1,119340\n" +
                "YC17020058-20170416-1,58162.5\n" +
                "YC17040405-20170410-85,81757.44\n" +
                "YC17030319-20170430-1,243669.6\n" +
                "YC17030144-20170417-95,1352317.2\n" +
                "YC17040448-20170510-3,52311.9\n" +
                "YC17040448-20170510-2,57382.8\n" +
                "YC17030337-20170322-1,33868.8\n" +
                "YC17040395-20170425-36,17671.5\n" +
                "YC17030319-20170401-7,147882.6\n" +
                "YC17030319-20170430-3,243878.4\n" +
                "YC17030319-20170430-2,243878.4\n" +
                "YC17030319-20170426-2,372499.2\n" +
                "YC17030319-20170417-101,424960.2\n" +
                "YC17030319-20170426-1,533901.6\n" +
                "YC17030306-20170427-3,14437.5\n" +
                "YC16060192-20170310-3,6134.4\n" +
                "YC16060192-20170321-45,7270.56\n" +
                "YC16060192-20170328-69,9692.352\n" +
                "YC16060192-20170314-70,82938.72\n" +
                "YC17040452-20170428-1,2039105.52\n" +
                "YC16060062-20170425-72,29047.2\n" +
                "YC16060062-20170425-72,29047.2\n" +
                "YC16060062-20170419-3,645214.5\n" +
                "YC16060062-20170419-3,645214.5\n" +
                "YC16060062-20170417-62,729744.6\n" +
                "YC16060062-20170417-62,729744.6\n" +
                "YC17040371-20170331-1,392100\n";
    }


    class Po{
        String purchaseOrder;
        String paymentNum;
        BigDecimal theoryMoney;
        BigDecimal adjMoney;
        BigDecimal actualMoney;

        public String getPurchaseOrder() {
            return purchaseOrder;
        }

        public void setPurchaseOrder(String purchaseOrder) {
            this.purchaseOrder = purchaseOrder;
        }

        public String getPaymentNum() {
            return paymentNum;
        }

        public void setPaymentNum(String paymentNum) {
            this.paymentNum = paymentNum;
        }

        public BigDecimal getTheoryMoney() {
            return theoryMoney;
        }

        public void setTheoryMoney(BigDecimal theoryMoney) {
            this.theoryMoney = theoryMoney;
        }

        public BigDecimal getAdjMoney() {
            return adjMoney;
        }

        public void setAdjMoney(BigDecimal adjMoney) {
            this.adjMoney = adjMoney;
        }

        public BigDecimal getActualMoney() {
            return actualMoney;
        }

        public void setActualMoney(BigDecimal actualMoney) {
            this.actualMoney = actualMoney;
        }

        @Override
        public String toString() {
            return "Po{" +
                    "purchaseOrder='" + purchaseOrder + '\'' +
                    ", paymentNum='" + paymentNum + '\'' +
                    ", theoryMoney=" + theoryMoney +
                    ", adjMoney=" + adjMoney +
                    ", actualMoney=" + actualMoney +
                    '}';
        }
    }
}
