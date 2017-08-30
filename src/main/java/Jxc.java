import java.util.*;

/**
 * Created by hzsunguanjun on 2017/8/28.
 */
public class Jxc {

    public static void main(String[] args){
        Jxc jxc = new Jxc();

        jxc.cal();


    }

    public void cal(){
        String data = "8017\n" +
                "8000\n" +
                "7985\n" +
                "7962\n" +
                "7939\n" +
                "7919\n" +
                "7855\n" +
                "7802\n" +
                "7729\n" +
                "7585\n" +
                "7447\n" +
                "7343\n" +
                "7169\n" +
                "7017\n" +
                "6903\n" +
                "6769\n" +
                "6660\n" +
                "6509\n" +
                "6399\n" +
                "6312\n" +
                "6196\n" +
                "6095\n" +
                "6010\n" +
                "5879\n" +
                "5722\n" +
                "5617\n" +
                "5527\n" +
                "10869\n" +
                "10781\n" +
                "10711\n" +
                "10650\n" +
                "10555\n";

        String[] dataArray = data.split("\\n");
        for (int i=0; i<31; i++){
            System.out.println(Integer.valueOf(dataArray[i]) - Integer.valueOf(dataArray[i+1]));
        }
    }

    public void genSqls(){
        // 查看快照
        String sql1 = "select *, from_unixtime(SnapTime/1000) from TB_JXC_SKU_INVENTORY_DAILY_SNAP where SkuId=%d and StoreHouseId=%d and SnapTime>=1498838400000 and SnapTime<=1501516800000;";
        String sql1_start = "select EffectiveLeftCount from TB_JXC_SKU_INVENTORY_DAILY_SNAP where SkuId=%d and StoreHouseId=%d and SnapTime=1498838400000;";
        String sql1_end = "select EffectiveLeftCount from TB_JXC_SKU_INVENTORY_DAILY_SNAP where SkuId=%d and StoreHouseId=%d and SnapTime=1501516800000;";

        //查看总出库数
        String sql2 = "select sum(ActualOutEffectiveCount) from TB_JXC_OUTSTORE_TASK_INFO as a join TB_JXC_OUTSTORE_TASK_SKU as b on a.Id=b.OutStoreTaskId where b.SkuId=%d and a.StoreHouseId=%d and a.ActualOutTime<=1501516800000 and a.ActualOutTime>=1498838400000;";

        //查看总入库数
        String sql3 = "select sum(ActualInCount) from TB_JXC_INSTORE_TASK_INFO as a join TB_JXC_INSTORE_TASK_SKU as b on a.Id=b.InStoreTaskId where b.SkuId=%d and a.StoreHouseId=%d and a.ActualInTime<=1501516800000 and a.ActualInTime>=1498838400000;";

        //查看分销扣减数
        String sql4 = "select sum(CountDelta) from TB_JXC_OTHER_CHANNEL_OUT_FILE_INFO as a join TB_JXC_OUT_FILE_DETAIL as b on a.Id=b.FileId where b.SkuId=%d and b.StoreHouseFullName='%s' and a.OperateTime<=1501516800000 and a.OperateTime>=1498838400000;";

        //查看良次转换
        String sql5 = "select sum(Count) from TB_JXC_SKU_INVENTORY_TRANSITION as a join TB_JXC_SKU_INVENTORY_TRANSITION_SKUINFO AS b on a.Id=b.TransitionId where b.SkuId=%d and a.StoreHouseId=%d and a.OperateTime<=1501516800000 and a.OperateTime>=1498838400000 and TransitionStatus=2;";

        List<SkuWarehouse> skuWarehouseList = getSkuWarehouseList();
        Map<String, Integer> storeHouseMap = getStoreHouseIdMap();
        for (SkuWarehouse skuWarehouse : skuWarehouseList){
            System.out.println(String.format(sql1, skuWarehouse.getSkuId(), storeHouseMap.get(skuWarehouse.getWarehoueseName())));
            System.out.println(String.format(sql1_start, skuWarehouse.getSkuId(), storeHouseMap.get(skuWarehouse.getWarehoueseName())));
            System.out.println(String.format(sql1_end, skuWarehouse.getSkuId(), storeHouseMap.get(skuWarehouse.getWarehoueseName())));
            System.out.println(String.format(sql2, skuWarehouse.getSkuId(), storeHouseMap.get(skuWarehouse.getWarehoueseName())));
            System.out.println(String.format(sql3, skuWarehouse.getSkuId(), storeHouseMap.get(skuWarehouse.getWarehoueseName())));
            System.out.println(String.format(sql4, skuWarehouse.getSkuId(), skuWarehouse.getWarehoueseName()));
            System.out.println(String.format(sql5, skuWarehouse.getSkuId(), storeHouseMap.get(skuWarehouse.getWarehoueseName())));
            System.out.println();
        }

    }

    public Map<String, Integer> getStoreHouseIdMap(){
        Map<String, Integer> map = new HashMap<>();

        map.put("东莞顺丰仓", 1004002);
        map.put("华北发网仓", 1004003);
        map.put("3C产品仓", 89169519);
        map.put("杭州顺丰仓", 1004001);

        return map;
    }

    public List<SkuWarehouse> getSkuWarehouseList(){
        String skuWarehouseStr = getSkuWarehouseStr();
        String[] lines = skuWarehouseStr.split("\\n");
        List<SkuWarehouse> skuWarehouseList = new ArrayList<>();

        for (String line : lines){
            String[] words = line.split(",");
            SkuWarehouse skuWarehouse = new SkuWarehouse();
            skuWarehouse.setSkuId(Integer.valueOf(words[0]));
            skuWarehouse.setWarehoueseName(words[1]);
            skuWarehouseList.add(skuWarehouse);
        }
        return skuWarehouseList;

    }

    public String getSkuWarehouseStr(){
        return "1187261,东莞顺丰仓\n" +
                "1063071,华北发网仓\n" +
                "1156341,3C产品仓\n" +
                "1083012,华北发网仓\n" +
                "1221000,杭州顺丰仓\n" +
                "1150143,3C产品仓\n" +
                "1150008,华北发网仓\n" +
                "1143023,杭州顺丰仓\n" +
                "1235022,3C产品仓\n" +
                "1236032,3C产品仓\n" +
                "1236012,3C产品仓\n" +
                "1236010,3C产品仓\n" +
                "1236017,3C产品仓\n" +
                "1100065,华北发网仓\n" +
                "1237027,3C产品仓\n" +
                "1237026,3C产品仓\n" +
                "1237023,3C产品仓\n" +
                "1208025,华北发网仓\n" +
                "1245017,3C产品仓\n" +
                "1165126,3C产品仓\n" +
                "1165130,3C产品仓\n" +
                "1165066,3C产品仓\n" +
                "1165069,3C产品仓\n" +
                "1022024,华北发网仓";
    }

    public void genOutSqls(){
        List<String> timeList = getTimeList();
        //查看总出库数
        String sql2 = "select sum(ActualOutEffectiveCount) from TB_JXC_OUTSTORE_TASK_INFO as a join TB_JXC_OUTSTORE_TASK_SKU as b on a.Id=b.OutStoreTaskId where b.SkuId=1063071 and a.StoreHouseId=1004003 and a.ActualOutTime>=%s and a.ActualOutTime<=%s;";

        //查看总入库数
        String sql3 = "select sum(ActualInCount) from TB_JXC_INSTORE_TASK_INFO as a join TB_JXC_INSTORE_TASK_SKU as b on a.Id=b.InStoreTaskId where b.SkuId=1063071 and a.StoreHouseId=1004003 and a.ActualInTime>=%s and a.ActualInTime<=%s;";

        for (int i=0; i<31; i++){
            System.out.println(String.format(sql2, timeList.get(i), timeList.get(i+1)));
            System.out.println(String.format(sql3, timeList.get(i), timeList.get(i+1)));
            System.out.println();
        }

    }
    public List<String> getTimeList(){
        String times = "1498838452687\n" +
                "1498924853748\n" +
                "1499011250325\n" +
                "1499097650099\n" +
                "1499184054948\n" +
                "1499270451776\n" +
                "1499356854671\n" +
                "1499443252660\n" +
                "1499529656009\n" +
                "1499616048542\n" +
                "1499702444226\n" +
                "1499788852626\n" +
                "1499875254823\n" +
                "1499961649721\n" +
                "1500048057349\n" +
                "1500134451019\n" +
                "1500220852845\n" +
                "1500307247676\n" +
                "1500393654714\n" +
                "1500480056796\n" +
                "1500566456649\n" +
                "1500652855911\n" +
                "1500739253635\n" +
                "1500825652490\n" +
                "1500912045250\n" +
                "1500998457556\n" +
                "1501084853233\n" +
                "1501171256974\n" +
                "1501257657421\n" +
                "1501344046235\n" +
                "1501430454973\n" +
                "1501516855038";

        String[] timeArray = times.split("\\n");
        return Arrays.asList(timeArray);
    }

    private class SkuWarehouse {
        private Integer skuId;
        private String warehoueseName;

        public Integer getSkuId() {
            return skuId;
        }

        public void setSkuId(Integer skuId) {
            this.skuId = skuId;
        }

        public String getWarehoueseName() {
            return warehoueseName;
        }

        public void setWarehoueseName(String warehoueseName) {
            this.warehoueseName = warehoueseName;
        }
    }

}
