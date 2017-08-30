package generator;

import org.apache.commons.lang.StringUtils;

/**
 * Created by hzsunguanjun on 2017/3/30.
 */
public class GenConstructor {

    public static void main(String[] args){
        String className = "FinPaymentRequirementBasicVO";
        String str = "private Long id;\n" +
                "\n" +
                "    //请款单号\n" +
                "    private String paymentNum;\n" +
                "\n" +
                "    //请款单类型（0：货到付款请款单，1：款到发货请款单，2：进口税金请款单，3：清关费请款单）\n" +
                "    private Integer paymentType;\n" +
                "\n" +
                "    //员工工号\n" +
                "    private String employeeNum;\n" +
                "\n" +
                "    //用户ID\n" +
                "    private Long userId;\n" +
                "\n" +
                "    //操作人用户uid, 也即用户邮箱名\n" +
                "    private String uid;\n" +
                "\n" +
                "    //用户名\n" +
                "    private String realName;\n" +
                "\n" +
                "    //费用报销公司（采购单中的采购主体)\n" +
                "    private String company;\n" +
                "\n" +
                "    //提交时间\n" +
                "    private Long opTime;\n" +
                "\n" +
                "    //成本中心\n" +
                "    private String costCenter;\n" +
                "\n" +
                "    //所属部门\n" +
                "    private String department;\n" +
                "\n" +
                "    //框架合同编号\n" +
                "    private String contractNo;\n" +
                "\n" +
                "    //补充协议编号\n" +
                "    private String agreementNo;\n" +
                "\n" +
                "    //采购单号\n" +
                "    private String purchaseOrder;\n" +
                "\n" +
                "    //供应商编号\n" +
                "    private String supplierId;\n" +
                "\n" +
                "    //供应商名称（需与供应商营业执照名称一致）\n" +
                "    private String supplierName;\n" +
                "\n" +
                "    //报关单号\n" +
                "    private String customsDeclarationOrder;\n" +
                "\n" +
                "    //转关单号\n" +
                "    private String transitOrder;\n" +
                "\n" +
                "    //所属权限小组\n" +
                "    private String group;\n" +
                "\n" +
                "    //备注\n" +
                "    private String remark;\n" +
                "\n" +
                "    //出纳\n" +
                "    private String cashier;\n" +
                "\n" +
                "    //复核\n" +
                "    private String checkedBy;\n" +
                "\n" +
                "    //批准\n" +
                "    private String approvedBy;\n" +
                "\n" +
                "    private String accountName; // 户名\n" +
                "\n" +
                "    //开户总行\n" +
                "    private String headBank;\n" +
                "\n" +
                "    private String bank; // 开户行\n" +
                "\n" +
                "    //开户行省\n" +
                "    private String province;\n" +
                "\n" +
                "    //开户行市\n" +
                "    private String city;\n" +
                "\n" +
                "    private String accountNo; // 账号\n" +
                "\n" +
                "    //开户行地址，非大陆地区\n" +
                "    private String bankAddress;\n" +
                "\n" +
                "    //开户行国际代码，非大陆地区\n" +
                "    private String bankCode;\n" +
                "\n" +
                "    //结算方式(1:预付-货到付款,2:预付-货到付款,3:预付+月结-按采购入库单,4:预付+月结-按销售出库单)\n" +
                "    private Integer settleMode;\n" +
                "\n" +
                "    //中间款支付方式(1:按发货批次分批付尾款,2:全部到货后一次性付尾款)\n" +
                "    private Integer midPayMode;\n" +
                "\n" +
                "    //预付款比例\n" +
                "    private Integer advancePayRate;\n" +
                "\n" +
                "    //中间款比例\n" +
                "    private Integer midPayRate;\n" +
                "\n" +
                "    //尾款比例\n" +
                "    private Integer finalPayRate;\n" +
                "\n" +
                "    //尾款扣留天数\n" +
                "    private Integer finalPayDetain;\n" +
                "\n" +
                "    private Integer settlementDate; // 结算日（1,2,3...）（settle_mode为2或3时必填）\n" +
                "\n" +
                "    private Long createTime;    //生成时间\n" +
                "\n" +
                "    private BigDecimal yxPayed;     //严选已付供应商金额\n" +
                "\n" +
                "    private BigDecimal yxShouldPay; //严选待付供应商金额\n" +
                "\n" +
                "    private BigDecimal supplierShouldPay;   //供应商应付严选金额\n" +
                "\n" +
                "    private Integer termType; //账款类型(1:预付款，2：中间款，3：尾款，4：中间预付款,5:税金，6：清关费，7：尾款利息）\n" +
                "\n" +
                "    private String term; //合同条款（预付款、中间款、尾款,中间预付款,税金，清关费，尾款利息）\n" +
                "\n" +
                "    private String currency; //币种\n" +
                "\n" +
                "    //核销方式（0:按预付款比例核销预付款，1:按100%核销预付款）\n" +
                "    private Integer verificationMode;\n" +
                "\n" +
                "    private Integer state;\n" +
                "\n" +
                "    private Integer stateResult;\n" +
                "\n" +
                "    private Integer nextState;\n" +
                "\n" +
                "    private Integer frontendState;  //前端展示的状态当前状态(20:等待提交审核,30:等待内控审批,40:等待领导审批1(严蓉)，50:等待领导审批2(如晶),60:等待总监审批,70:等待财务审批,80:等待关务审批,90:等待财务税率审核,200:等待支付完成，300：付款完成，301：审批不通过)\n" +
                "\n" +
                "    private Boolean ccfForkFlag;    //清关费是否有不确定的款项，如果有，则分裂成两个请款单。不确认的款项需置为0\n" +
                "\n" +
                "    //税金税率是否有变动（0：否，1：是）\n" +
                "    private Integer rateChangeFlag;\n" +
                "\n" +
                "    //实际关税税率\n" +
                "    private BigDecimal actualCustomsRate;\n" +
                "\n" +
                "    //实际消费税税率\n" +
                "    private BigDecimal actualConsumptionRate;\n" +
                "\n" +
                "    //实际增值税税率\n" +
                "    private BigDecimal actualAddedValueRate;\n" +
                "\n" +
                "    //实际关税费用\n" +
                "    private BigDecimal actualCustoms;\n" +
                "\n" +
                "    //实际消费税费用\n" +
                "    private BigDecimal actualConsumption;\n" +
                "\n" +
                "    //实际增值税费用\n" +
                "    private BigDecimal actualAddedValue;\n" +
                "\n" +
                "    //采购单总取消金额\n" +
                "    private BigDecimal totalCanceled;";

        String[] strArray = str.split("\n");
        String note = "";
        String[] strArray2;
        String fieldName;
        System.out.println("public " + className + "(" + className + " obj){");
        for (String tmp : strArray){
            if (tmp.contains("//") && tmp.contains(";")){
                strArray2 = tmp.trim().split("//");
                note = strArray2[1];
                strArray2 = tmp.trim().split(" ");
                fieldName = strArray2[2].substring(0, strArray2[2].length()-1);
                System.out.println("\tthis." + fieldName + " = obj.get" + StringUtils.capitalize(fieldName) + "(); //" + note);
            }
            else if (tmp.contains("//")){
                note = tmp.trim().substring(2);
            }
            else if (tmp.contains(";")){
                strArray2 = tmp.trim().split(" ");
                fieldName = strArray2[2].substring(0, strArray2[2].length()-1);
                System.out.println("\tthis." + fieldName + " = obj.get" + StringUtils.capitalize(fieldName) + "(); //" + note);
            }
        }
        System.out.println("}");
    }
}
