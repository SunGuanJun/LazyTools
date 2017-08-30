package Json.bean;

import java.util.List;

public class InvoicingSkuInfoVO {

    private Long id;

    private Long itemId;

    private String itemName;

    private String barCode;

    private String characterDesc;

    private Long skuId;

    private Integer isNewSku;

    private List<InvoicingWarehouseVO> storeHouseList;

    public Integer getIsNewSku() {
        return isNewSku;
    }

    public void setIsNewSku(Integer isNewSku) {
        this.isNewSku = isNewSku;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCharacterDesc() {
        return characterDesc;
    }

    public void setCharacterDesc(String characterDesc) {
        this.characterDesc = characterDesc;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public List<InvoicingWarehouseVO> getStoreHouseList() {
        return storeHouseList;
    }

    public void setStoreHouseList(List<InvoicingWarehouseVO> storeHouseList) {
        this.storeHouseList = storeHouseList;
    }
}