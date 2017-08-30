package excel;

import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzsunguanjun on 2017/5/19.
 */
public class DB2Excel {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader("E:\\pms\\audit.txt"));
        //创建一个excel
        Workbook wb = WorkbookFactory.create(new File("E:\\pms\\audit.xlsx"));  //读入
        OutputStream os=new FileOutputStream("E:\\pms\\audit1.xlsx");   //写出
        Sheet sheet = wb.getSheetAt(0);

        String line ;
        String[] strArray;
        Row row;
        Cell cell;
        int rowNum = 1;

        //采购单状态
        Map<Integer, String> stateMap = new HashMap<>();
        stateMap.put(0, "草稿");
        stateMap.put(1, "提交审核");
        stateMap.put(1, "等待计划部审核");
        stateMap.put(16, "等待财务审核");
        stateMap.put(10, "等待主管确认");
        stateMap.put(11, "等待审批人1审核");
        stateMap.put(12, "等待审批人2审核");
        stateMap.put(13, "等待审批人3审核");
        stateMap.put(14, "等待供应商确认");
        stateMap.put(15, "执行中");


        //1:预付-货到付款,2:预付-款到发货,3:预付+月结-按采购入库单,4:预付+月结-按销售出库单
        Map<String, String> settleMap = new HashMap<>();
        settleMap.put("1", "预付-货到付款");
        settleMap.put("2", "预付-款到发货");
        settleMap.put("3", "预付+月结-按采购入库单");
        settleMap.put("4", "预付+月结-按销售出库单");
        settleMap.put("NULL", "");

        //中间款支付方式(1:按发货批次分批付尾款,2:全部到货后一次性付尾款)
        Map<String, String> midPayMap = new HashMap<>();
        midPayMap.put("1", "按发货批次分批付中间款");
        midPayMap.put("2", "全部到货后一次性付中间款");
        midPayMap.put("NULL", "");

        //核销方式（0:按预付款比例核销预付款，1:按100%核销预付款）
        Map<String, String> verifiedMap = new HashMap<>();
        verifiedMap.put("0", "按预付款比例核销预付款");
        verifiedMap.put("1", "按100%核销预付款");
        verifiedMap.put("NULL", "");

        //货权归属(1:严选,2:供应商)
        Map<String, String> ownMap = new HashMap<>();
        ownMap.put("1", "严选");
        ownMap.put("2", "供应商");
        ownMap.put("NULL", "");

        //审批单状态(0:接收采购需求，1:需求已确认，待回传考拉，2:跟单中，3:已关单)
        Map<String, String> auditStateMap = new HashMap<>();
        auditStateMap.put("0" , "接收采购需求");
        auditStateMap.put("1" , "需求已确认，待回传考拉");
        auditStateMap.put("2" , "跟单中");
        auditStateMap.put("3" , "已关单");


        // 设置excel
        while ((line = reader.readLine()) != null){
            strArray = line.split(",");
            sheet.createRow(rowNum).createCell(0).setCellValue(strArray[0].trim());
            sheet.getRow(rowNum).createCell(1).setCellValue(auditStateMap.get(strArray[1].trim()));    //状态
            sheet.getRow(rowNum).createCell(2).setCellValue(strArray[2].trim());    //严选供货数量
            sheet.getRow(rowNum).createCell(3).setCellValue(strArray[3].trim());    //商品ID
            sheet.getRow(rowNum).createCell(4).setCellValue(strArray[4].trim());    //skuID
            sheet.getRow(rowNum).createCell(5).setCellValue(strArray[5].trim());    //已入库数量
            sheet.getRow(rowNum).createCell(6).setCellValue(strArray[6].trim());    //待入库数量



//            sheet.createRow(rowNum).createCell(0).setCellValue(strArray[1].trim());
//            sheet.getRow(rowNum).createCell(1).setCellValue(strArray[2].trim());    //id
//            sheet.getRow(rowNum).createCell(2).setCellValue(strArray[3].trim());    //合同编号
//            sheet.getRow(rowNum).createCell(3).setCellValue(strArray[4].trim().equals("1") ? "生效" : "已失效");    //是否生效
//            sheet.getRow(rowNum).createCell(4).setCellValue(strArray[5].trim());    //是否首单
//            sheet.getRow(rowNum).createCell(5).setCellValue(settleMap.get(strArray[6].trim()));    //结算方式
//            sheet.getRow(rowNum).createCell(6).setCellValue(midPayMap.get(strArray[7].trim()));    //中间款支付方式
//            sheet.getRow(rowNum).createCell(7).setCellValue(strArray[8].trim());    //首
//            sheet.getRow(rowNum).createCell(8).setCellValue(strArray[9].trim());    //中
//            sheet.getRow(rowNum).createCell(9).setCellValue(strArray[10].trim());   //中预
//            sheet.getRow(rowNum).createCell(10).setCellValue(strArray[11].trim());  //尾款
//            sheet.getRow(rowNum).createCell(11).setCellValue(strArray[12].trim());  //尾款扣
//            sheet.getRow(rowNum).createCell(12).setCellValue(strArray[13].trim());  //月结
//            sheet.getRow(rowNum).createCell(13).setCellValue(strArray[14].trim());  //结算日
//            sheet.getRow(rowNum).createCell(14).setCellValue(verifiedMap.get(strArray[15].trim()));  //核销模式
//            sheet.getRow(rowNum).createCell(15).setCellValue(ownMap.get(strArray[16].trim()));  //货权归属

//            for (int i=2; i< strArray.length; i++){
//                sheet.getRow(rowNum).createCell(i-1).setCellValue(strArray[i].trim());
//            }
            rowNum++;
        }


        //写采购单excel
//        Set<String> poSet = new HashSet<>();
//        while ((line = reader.readLine()) != null){
//            poSet.add(line.trim());
//        }
//
//        String purchaseOrder;
//        for (int i=1; i<90; i++){
//            purchaseOrder = sheet.getRow(i).getCell(2).getStringCellValue();
//            System.out.println(purchaseOrder);
//            if (poSet.contains(purchaseOrder)){
//                sheet.getRow(i).createCell(3).setCellValue("成功");
//            }
//        }

//        System.out.println(poSet.size());

        wb.write(os);
        reader.close();
        wb.close();
    }
}
