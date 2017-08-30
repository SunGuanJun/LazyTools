package excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hzsunguanjun on 2017/2/15.
 */
public class ExcelExportGenUtil {

    public static void genExport(String filePath) throws Exception{
        //创建一个excel
        Workbook wb = WorkbookFactory.create(new File(filePath));

        CellRangeAddress cellRangeAddress = null;
        Cell cell = null;

        //获取sheet数
        int sheetNum = wb.getNumberOfSheets();

        StringBuffer code = new StringBuffer();
        code.append("public Workbook export(){\n\r");
        code.append("\t//创建一个excel\n" );
        code.append("\tWorkbook workbook = new HSSFWorkbook();\n\n");

        //循环生成全部sheet信息
        for (int i=0; i<sheetNum; i++) {
            //获取sheet
            String sheetName = wb.getSheetName(i);
            Sheet sheet = wb.getSheetAt(i);
            code.append("\t//创建一个sheet\n");
            code.append("\tSheet sheet = wb.createSheet(\"" + sheetName + "\");\n\n");
            code.append("\tint currentRow = 0;\n");

            //获取合并单元格信息
            int[][] cellCombinedIndexArray = getCombinedCellInfo(sheet);
            //获取行数
            int rowNum = sheet.getLastRowNum() + 1;
            //获取列数
            int columnNum = sheet.getRow(0).getPhysicalNumberOfCells();
            //开始扫描，将excel模板中的信息转化为代码
            for (int j = 0; j < rowNum; j++) {
                boolean inListFlag = false;
                String preStr = "";
                for (int k = 0; k < columnNum; k++) {
                    if (cellCombinedIndexArray[j][k] == -1) {
                        //一个单元格的情况
                        cell = sheet.getRow(j).getCell(k);
                        CellValueVo cellValueVo = getCellValue(getCellValue(cell));

                        if (cellValueVo.isNormalFlag()){
                            //固定值，固定值不允许出现在列表中
                            code.append("\tcell = sheet.");
                            code.append( k == 0 ? "createRow(" : "getRow(");
                            code.append( "currentRow).createCell(" + k + ");\n");
                            code.append("\tcell.setCellValue(" + cellValueVo.getValue() + ");\n");
                            code.append("\tcell.setCellStyle(style1);\n\n");
                        }
                        else{
                            //包含变量
                            //是否是列表
                            if (cellValueVo.isListFlag()){
                                preStr = "\t";
                                inListFlag = true;
                                code.append("\tfor(Object " + cellValueVo.getVarName() + " : " + cellValueVo.getListName() + "){\n");
                            }
                            //添加注释
                            if (cellValueVo.getAnnotation() != null){
                                code.append(preStr + "\t//" + cellValueVo.getAnnotation() + "\n");
                            }
                            //为单元格赋值
                            code.append(preStr + "\tcell = sheet.");
                            code.append(k == 0 ? "createRow(" : "getRow(");
                            code.append("currentRow).createCell(" + k + ");\n");
                            code.append(preStr + "\tcell.setCellValue(" + cellValueVo.getValue() + ");\n");
                            code.append(preStr + "\tcell.setCellStyle(style1);\n\n");
                        }
                    } else {
                        //合并单元格的情况
                        cellRangeAddress = sheet.getMergedRegion(cellCombinedIndexArray[j][k]);
                        if (j == cellRangeAddress.getFirstRow() && k == cellRangeAddress.getFirstColumn()) {
                            //如果该单元格是合并区域第一行第一列的单元格
                            cell = sheet.getRow(j).getCell(k);
                            CellValueVo cellValueVo = getCellValue(getCellValue(cell));

                            //目前来看，合并单元格不会出现在列表中，因此暂不考虑列表情况

                            //添加注释
                            if (cellValueVo.getAnnotation() != null){
                                code.append(preStr + "\t//" + cellValueVo.getAnnotation() + "\n");
                            }
                            code.append("\tExcelUtil.createMergeRegion(sheet, " + cellValueVo.getValue() + "\n");
                            code.append("\t\tstyle1, new CellRangeAddress(" + cellRangeAddress.getFirstRow() + ", " +
                                cellRangeAddress.getLastRow() + ", " + cellRangeAddress.getFirstColumn() + ", " +
                                cellRangeAddress.getLastColumn() + "));\n");

                        }
                    }
                }
                code.append(preStr + "currentRow++;\n");
                //如果是列表结束的那一行
                if (inListFlag) {
                    code.append("\t}\n\n");
                }
            }
        }
        code.append("\n\treturn wb;\n}");
//        System.out.println(code);

        //输出
        BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));
        writer.write(code.toString());
        writer.close();
    }

    /**
     * 获取Excel表格中合并单元格的信息
     * @param sheet
     * @return
     */
    public static int[][] getCombinedCellInfo(Sheet sheet){
        //获取行数
        int rowNum = sheet.getLastRowNum() + 1;
        //获取列数
        int columnNum = sheet.getRow(0).getPhysicalNumberOfCells();
        //获取sheet中合并单元格的数量
        int sheetMergeCount = sheet.getNumMergedRegions();
        CellRangeAddress cellRangeAddress = null;

        //因为获取到的合并单元格列表是乱序的，随机的
        int[][] cellCombinedIndexArray = new int[rowNum][columnNum];
        for (int i=0; i<rowNum; i++){
            for (int j=0; j<columnNum; j++){
                cellCombinedIndexArray[i][j] = -1;
            }
        }

        //获取合并单元格的信息
        for(int i = 0 ; i < sheetMergeCount ; i++){
            //获取第i个合并单元格
            cellRangeAddress = sheet.getMergedRegion(i);
            int firstColumn = cellRangeAddress.getFirstColumn();
            int lastColumn = cellRangeAddress.getLastColumn();
            int firstRow = cellRangeAddress.getFirstRow();
            int lastRow = cellRangeAddress.getLastRow();

            //将合并单元格中的单元格标记上该合并单元格的index
            for (int j=firstRow; j<=lastRow; j++){
                for (int k=firstColumn; k<=lastColumn; k++){
                    cellCombinedIndexArray[j][k] = i;
                }
            }
        }

        return cellCombinedIndexArray;
    }


    /**
     * 获取单元格内容,不管是什么类型,统一转成string
     * @author youzhihao
     */
    public static String getCellValue(Cell cell) {
        if (cell == null)
            return "";
        String result = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                        .getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = DateUtil
                        .getJavaDate(value);
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 31) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                    double value = cell.getNumericCellValue();
                    Date date = DateUtil
                        .getJavaDate(value);
                    result = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#");
                    }
                    result = format.format(value);
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString();
                break;
        }
        return result;
    }

    public static CellValueVo getCellValue(String cellValue){
        CellValueVo vo = new CellValueVo();
        String preValue = null;
        String varName = null;

        if (cellValue == null || cellValue.equals("")){
            vo.setNormalFlag(true);
            vo.setValue("\"\"");
            return vo;
        }

        if (cellValue.contains("$")) {
            //如果包含$,说明是变量
            int pos1 = cellValue.indexOf("$");  //$为变量开头标识
            int pos2 = cellValue.indexOf("#");  //#为注释开头标识

            if (pos1 > 0) {
                preValue = cellValue.substring(0, pos1);
            }
            if (pos2 > 0) {
                varName = cellValue.substring(pos1 + 1, pos2);
                vo.setAnnotation(cellValue.substring(pos2 + 1));
            } else {
                varName = cellValue.substring(pos1 + 1);
            }

            //[为变量分割标识
            String[] varArray = varName.split("\\[");
            int len = varArray.length;
            varName = varArray[len-1];
            int dataType = 0;
            switch (varArray[0]){
                case "L":
                    //列表
                    vo.setListFlag(true);
                    vo.setListName(varArray[len-2]);
                    vo.setVarName(varName.substring(0, varName.indexOf(".")));
                    break;
                case "SUM":
                    //列表中，求和并合并单元格
                    vo.setSumFlag(true);
                    break;
                case "DATE":
                    //日期类型数据
                    dataType = 1;
                    break;
                case "MONEY":
                    //金额
                    dataType = 2;
                    break;
                default:
                    break;
            }

            if (len > 2){
                switch (varArray[1]){
                    case "DATE":
                        //日期类型数据
                        dataType = 1;
                        break;
                    case "MONEY":
                        //金额
                        dataType = 2;
                        break;
                    default:
                        break;
                }
            }

            switch (dataType){
                case 1:
                    //日期类型
                    varName = "DateUtils.parseLongToString(" + varName + ", \"yyyy-MM-dd\")";
                    break;
                case 2:
                    //金额类型
                    varName = "String.format(\"%.2f\", " + varName + ".doubleValue())";
                    break;
                default:
                    break;
            }

            if (preValue == null){
                vo.setValue(varName);
            }
            else {
                vo.setValue("\"" + preValue + "\"" + varName);
            }
        }
        else{
            //普通值
            vo.setNormalFlag(true);
            vo.setValue("\"" + cellValue + "\"");
        }

        return vo;
    }

}
