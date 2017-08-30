package excel;

/**
 * Created by hzsunguanjun on 2017/2/23.
 */
public class Main {
    /**
     *
     * @param args  args[0]  指定操作，import表示导入，export表示导出
     *              args[1]  指定文件路径
     */
    public static void main(String[] args) throws Exception{
        if (args.length != 2){
            System.out.println("please input correct params: import/export and filePath\n");
            throw new Exception("wrong param num");
        }

        switch (args[0]){
            case "import":
                ExcelImportGenUtil.genImport(args[1]);
                break;
            case "export":
                ExcelExportGenUtil.genExport(args[1]);
                break;
            default:
                System.out.println("please input correct params: import/export and filePath\n");
                break;
        }
    }
}
