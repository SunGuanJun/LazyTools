package excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by hzsunguanjun on 2017/2/17.
 */
public class ExcelImportGenUtil {

    public static void genImport(String filePath) throws Exception{
        //创建一个excel
        Workbook wb = WorkbookFactory.create(new File(filePath));
        Sheet sheet = wb.getSheetAt(0);
        Row row0 = sheet.getRow(0);
        Row row1 = sheet.getRow(1);

        //获取列数
        int columnNum = sheet.getRow(0).getPhysicalNumberOfCells();

        StringBuffer code = new StringBuffer();

        code.append("public List<Object> importDetail(Sheet sheet){\n");
        code.append("\tRow row = null;\n");
        code.append("\tCell cell = null;\n\n");
        code.append("\t//获取行数\n");
        code.append("\tint rowNum = sheet.getLastRowNum() + 1;\n");
        code.append("\t//获取列数\n");
        code.append("\tint columnNum = sheet.getRow(0).getPhysicalNumberOfCells();\n\n");
        code.append("\t//获取Excel头部信息\n");
        code.append("\tMap<String, Integer> titleMap = new HashMap<>();    //String存头部字段，int存索引\n");
        code.append("\trow = sheet.getRow(0);\n");
        code.append("\tfor (int i=0; i<columnNum; i++){\n");
        code.append("\t\tcell = row.getCell(i);\n");
        code.append("\t\ttitleMap.put(ExcelUtil.getCellValue(cell), i);\n");
        code.append("\t}\n\n");
        code.append("\t//获取Excel内容\n");
        code.append("\tList<Object> voList = new ArrayList<>();\n");
        code.append("\tObject vo = null;\n");
        code.append("\ttry{\n");
        code.append("\t\tfor (int i=1; i<rowNum; i++){\n");
        code.append("\t\t\trow = sheet.getRow(i);\n");
        code.append("\t\t\tvo = new Object();\n\n");

        //excel相关内容
        String preStr = "\t\t\t";
        String cellValue;
        String[] valueArr;
        String name;
        for (int i=0; i<columnNum; i++){
            name = ExcelExportGenUtil.getCellValue(row0.getCell(i));
            code.append(preStr + "//" + name +"\n");
            cellValue = ExcelExportGenUtil.getCellValue(row1.getCell(i));
            valueArr = cellValue.split("\\[");

            switch (valueArr[0]){
                case "LONG":
                    code.append(valueArr[1] + "(Long.valueOf(ExcelUtil.getCellValue(row.getCell(titleMap.get("+ name + ")))));\n");
                    break;
                case "INTEGER":
                    code.append(valueArr[1] + "(Integer.valueOf(ExcelUtil.getCellValue(row.getCell(titleMap.get("+ name + ")))));\n");
                    break;
                case "DATE":
                    code.append("String time = ExcelUtil.getCellValue(row.getCell(titleMap.get("+ name + ")));\n");
                    code.append(valueArr[1] + "(new Date(time).getTime());\n");
                    break;
                case "DOUBLE":
                    code.append(valueArr[1] + "(Double.valueOf(ExcelUtil.getCellValue(row.getCell(titleMap.get("+ name + ")))));\n");
                    break;
                default:
                    code.append(cellValue + "(ExcelUtil.getCellValue(row.getCell(titleMap.get("+ name + "))));\n");
                    break;
            }
        }

        code.append("\t\t}\n");
        code.append("\t} catch (Exception e){\n");
        code.append("\t\tint errorLine = row.getRowNum() + 1;\n");
        code.append("\t\tLogUtil.errorLog(LogUtil.getFun(), LogUtil.getTraceId(), \"库内巡检导入，第{}行出现错误\", errorLine);\n");
        code.append("\t\tthrow new ServiceException(\"导入第\" + errorLine + \"行数据时出错\");\n");
        code.append("\t}\n\n");
        code.append("\treturn voList;\n");
        code.append("}\n");

        //输出
        BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));
        writer.write(code.toString());
        writer.close();
    }

}
