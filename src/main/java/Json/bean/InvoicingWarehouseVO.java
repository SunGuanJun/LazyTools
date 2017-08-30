package Json.bean;

/**
 * Created by hzsunguanjun on 2017/4/9.
 */
public class InvoicingWarehouseVO {

    private String warehouseCode; // 仓库编号

    private String warehouseName; // 仓库名称

    public InvoicingWarehouseVO() {
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
