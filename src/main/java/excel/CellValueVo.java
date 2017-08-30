package excel;

public class CellValueVo {
    private String value;           //单元格中要显示的内容
    private String annotation;      //注释
    private String listName;        //列表名
    private String varName;         //变量名
    private boolean normalFlag;     //是否是普通值，即写死的字符串
    private boolean listFlag;       //是否是列表开头
    private boolean sumFlag;        //是否是列表中需要合并单元格的项

    public CellValueVo() {
        this.value = null;
        this.annotation = null;
        this.listName = null;
        this.varName = null;
        this.normalFlag = false;
        this.listFlag = false;
        this.sumFlag = false;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListName() {
        return listName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getVarName() {
        return varName;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public boolean isNormalFlag() {
        return normalFlag;
    }

    public void setNormalFlag(boolean normalFlag) {
        this.normalFlag = normalFlag;
    }

    public boolean isListFlag() {
        return listFlag;
    }

    public void setListFlag(boolean listFlag) {
        this.listFlag = listFlag;
    }

    public boolean isSumFlag() {
        return sumFlag;
    }

    public void setSumFlag(boolean sumFlag) {
        this.sumFlag = sumFlag;
    }

}